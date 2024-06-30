package com.LeightonApp.MyThrift.rest;
import com.LeightonApp.MyThrift.entity.Store;
import com.LeightonApp.MyThrift.service.StoreService;
import com.stripe.Stripe;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Value("${stripe.secret}")
    private String stripeApiKey;
    private StoreService storeService;

    @Autowired
    public PaymentController(StoreService theStore) {
        this.storeService = theStore;
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
                .setSuccessUrl("http://localhost:3000/success")
                .setCancelUrl("http://localhost:3000/cancel")
                .addLineItem(
                        SessionCreateParams.LineItem.builder()
                                .setPriceData(
                                        SessionCreateParams.LineItem.PriceData.builder()
                                                .setCurrency("cad")
                                                .setUnitAmount(amount * 100L) // Amount in cents
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
}
