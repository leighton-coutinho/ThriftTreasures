package com.LeightonApp.MyThrift.rest;
import com.LeightonApp.MyThrift.dao.CategoryRepository;
import com.LeightonApp.MyThrift.entity.Customer;
import com.LeightonApp.MyThrift.entity.Item;
import com.LeightonApp.MyThrift.entity.Sale;
import com.LeightonApp.MyThrift.entity.Store;
import com.LeightonApp.MyThrift.service.CustomerService;
import com.LeightonApp.MyThrift.service.ItemService;
import com.LeightonApp.MyThrift.service.SaleService;
import com.LeightonApp.MyThrift.service.StoreService;
import com.stripe.Stripe;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.model.Event;
import com.stripe.model.checkout.Session;
import com.stripe.net.Webhook;
import com.stripe.param.checkout.SessionCreateParams;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Value("${stripe.secret}")
    private String stripeApiKey;

    @Value("${stripe.webhook.secret}")
    private String webhookSecret;

    private StoreService storeService;
    private ItemService itemService;
    private CustomerService customerService;
    private SaleService saleService;

    @Autowired
    public PaymentController(StoreService theStore, ItemService theItem, CustomerService theCustomer, SaleService theSale) {
        this.storeService = theStore;
        this.itemService = theItem;
        this.customerService = theCustomer;
        this.saleService = theSale;
    }

    @PostConstruct
    public void init() {
        Stripe.apiKey = this.stripeApiKey;
    }

    @PostMapping("/create-checkout-session")
    public ResponseEntity<Map<String, Object>> createCheckoutSession(@RequestParam String storeUsername, @RequestParam String itemName, @RequestParam long amount) {
        // Assume storeService.findUser returns an Optional<Store>
        Store store = storeService.findUser(storeUsername).orElseThrow(() -> new RuntimeException("Store not found"));
        String storeStripeAccount = store.getStripeAccount();

        SessionCreateParams params = SessionCreateParams.builder()
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl("http://localhost:3000/success?session_id={CHECKOUT_SESSION_ID}")
                .setCancelUrl("http://localhost:3000/cancel")
                .addLineItem(
                        SessionCreateParams.LineItem.builder()
                                .setPriceData(
                                        SessionCreateParams.LineItem.PriceData.builder()
                                                .setCurrency("cad")
                                                .setUnitAmount(amount * 100L) // need amount in cents
                                                .setProductData(
                                                        SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                                .setName(itemName)
                                                                .build()
                                                )
                                                .build()
                                )
                                .setQuantity(1L)
                                .build()
                )
                .setPaymentIntentData(
                        SessionCreateParams.PaymentIntentData.builder()
                                .setApplicationFeeAmount(200L) // $2 application fee in cents
                                .setTransferData(
                                        SessionCreateParams.PaymentIntentData.TransferData.builder()
                                                .setDestination(storeStripeAccount)
                                                .build()
                                )
                                .build()
                )
                .putMetadata("storeUsername", storeUsername)
                .putMetadata("customerUsername", "customeruser") // FOR NOW HARDCODE
                .putMetadata("itemName", itemName)
                .build();

        try {
            Session session = Session.create(params);
            Map<String, Object> responseData = new HashMap<>();
            responseData.put("id", session.getId());
            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/webhook")
    public ResponseEntity<String> handleStripeWebhook(@RequestBody String payload, @RequestHeader("Stripe-Signature") String sigHeader) {
        Event event;

        try {
            event = Webhook.constructEvent(payload, sigHeader, webhookSecret);
        } catch (SignatureVerificationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Webhook error: " + e.getMessage());
        }

        if ("checkout.session.completed".equals(event.getType())) {
            Session session = (Session) event.getData().getObject();
            // Handle the checkout.session.completed event

            //create a Sale
            String storeUsername = session.getMetadata().get("storeUsername");
            String customerUsername = session.getMetadata().get("customerUsername");
            String itemName = session.getMetadata().get("itemName");

            Store store = storeService.findUser(storeUsername).orElseThrow(() -> new RuntimeException("Store not found"));
            Customer customer = customerService.findByUsername(customerUsername).orElseThrow(() -> new RuntimeException("Customer not found"));
            Item item = itemService.findByName(itemName).orElseThrow(() -> new RuntimeException("Item not found"));

            Sale sale = new Sale(store, customer, item);
            saleService.saveSale(sale);
        }

        return ResponseEntity.ok("Success");
    }

    @GetMapping("/get-item-by-session")
    public ResponseEntity<ItemDTO> getItemBySession(@RequestParam String session_id) {
        try {
            Session session = Session.retrieve(session_id);
            String storeUsername = session.getMetadata().get("storeUsername");
            String itemName = session.getMetadata().get("itemName");

            Store store = storeService.findUser(storeUsername).orElseThrow(() -> new RuntimeException("Store not found"));
            Item item = itemService.findByName(itemName).orElseThrow(() -> new RuntimeException("Item not found"));

            ItemDTO itemDTO = new ItemDTO(item, store); // Assuming you have an ItemDTO class
            return ResponseEntity.ok(itemDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
