package com.LeightonApp.MyThrift.rest;

import com.LeightonApp.MyThrift.entity.*;
import com.LeightonApp.MyThrift.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;




@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
public class restController {

    private UserService userService;
    private StoreService storeService;
    private ItemService itemService;

    private CategoryService categoryService;
    private SaleService saleService;
    private CustomerService customerService;

    @Autowired
    public restController(UserService theUserService, StoreService theStoreService, ItemService theItemService, CategoryService theCategoryService, SaleService theSaleService, CustomerService theCustomer)
    {
        userService = theUserService;
        storeService = theStoreService;
        itemService = theItemService;
        categoryService = theCategoryService;
        saleService = theSaleService;
        customerService = theCustomer;
    }

    // expose home endpoint "/"
    @GetMapping("/")
    public String sayHello()
    {
        return "Hello World!";
    }

    @GetMapping("/workout")
    public String workout()
    {
        return "run World!";
    }

    @GetMapping("/users")
    public List<User> getUsers()
    {
        return userService.findAll();
    }

    @GetMapping("/test-connection")
    public String testConnection() {
        return "Connection successful!";
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticateUser(@RequestBody UserLogin userLogin) {
        Optional<Store> authenticatedStore = storeService.authenticateUser(userLogin.getUsername(), userLogin.getPassword());

        if (authenticatedStore.isPresent()) {
            return ResponseEntity.ok(authenticatedStore.get());
        } else {
            return ResponseEntity.status(401).body("Authentication failed");
        }
    }

    @PostMapping("/authenticate2") // Customer Authentication
    public ResponseEntity<?> authenticateUser2(@RequestBody UserLogin userLogin) {
        Optional<Customer> authenticatedCustomer = customerService.authenticateUser(userLogin.getUsername(), userLogin.getPassword());

        if (authenticatedCustomer .isPresent()) {
            return ResponseEntity.ok(authenticatedCustomer.get());
        } else {
            return ResponseEntity.status(401).body("Authentication failed");
        }
    }

    public static class UserLogin {
        private String username;
        private String password;

        // Getters and Setters
        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }


    @PutMapping("/{username}/add")
    public ResponseEntity<String> addItem(@PathVariable String username,
                                          @RequestParam("name") String name,
                                          @RequestParam("desc") String desc,
                                          @RequestParam("category") String category,
                                          @RequestParam("price") double price,
                                          @RequestParam("image") MultipartFile image) {
        // Save the image to the specified directory
        String directory = "C:/Users/ljlco/OneDrive - McGill University/springBootDev/MyThrift/imgs/" + username;
        try {
            Path directoryPath = Paths.get(directory);
            if (!Files.exists(directoryPath)) {
                Files.createDirectories(directoryPath);
            }
            Path filePath = Paths.get(directory, image.getOriginalFilename());
            Files.write(filePath, image.getBytes());
        } catch (IOException e) {
            return new ResponseEntity<>("Error saving image", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // Create a new item and save it using the itemService
        String status = "Just Added";
        Store store = storeService.findUser(username).get();
        String itemPath = image.getOriginalFilename();
        Item item = new Item(name, desc, categoryService.findOrAddCategory(category), price, status, store, itemPath);
        itemService.save(item);
        return new ResponseEntity<>("Item added successfully", HttpStatus.OK);
    }

    @GetMapping("/{username}/items")
    public ResponseEntity<List<ItemDTO>> getItems(@PathVariable String username) {
        Store store = storeService.findUser(username).orElseThrow(() -> new RuntimeException("Store not found"));
        List<Item> items = itemService.findByStore(store);
        List<ItemDTO> itemDTOs = items.stream().map(item -> {
            ItemDTO dto = new ItemDTO(item, store);
            return dto;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(itemDTOs);
    }

    @GetMapping("/{username}/{imagePath:.+}")
    public ResponseEntity<Resource> getImage(@PathVariable String username, @PathVariable String imagePath) {
        try {
            Path path = Paths.get("C:/Users/ljlco/OneDrive - McGill University/springBootDev/MyThrift/imgs", username, imagePath);
            Resource resource = new UrlResource(path.toUri());

            if (resource.exists() || resource.isReadable()) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (IOException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }


    @GetMapping("/stores/{imagePath:.+}")
    public ResponseEntity<Resource> getStoreImage(@PathVariable String imagePath) {
        try {
            Path path = Paths.get("C:/Users/ljlco/OneDrive - McGill University/springBootDev/MyThrift/imgs/users", imagePath);
            Resource resource = new UrlResource(path.toUri());

            if (resource.exists() || resource.isReadable()) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (IOException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }


    private MediaType determineMediaType(String fileName) {
        if (fileName.toLowerCase().endsWith(".jpg") || fileName.toLowerCase().endsWith(".jpeg")) {
            return MediaType.IMAGE_JPEG;
        } else if (fileName.toLowerCase().endsWith(".png")) {
            return MediaType.IMAGE_PNG;
        } else if (fileName.toLowerCase().endsWith(".gif")) {
            return MediaType.IMAGE_GIF;
        }
        return MediaType.APPLICATION_OCTET_STREAM;
    }

    private String getImageUrl(String username, String imagePath) {
        Path path = Paths.get("C:/Users/ljlco/OneDrive - McGill University/springBootDev/MyThrift/imgs", username, imagePath);
        return path.toString();
    }

    @GetMapping("/{username}/sales")
    public ResponseEntity<List<SaleDTO>> getSales(@PathVariable String username)
    {
        Store store = storeService.findUser(username).orElseThrow(() -> new RuntimeException("Store not found"));
        List<Sale> sales = saleService.findByStore(store);

        List<SaleDTO> saleDTOs = sales.stream().map(sale -> {
            SaleDTO dto = new SaleDTO();
            dto.setName(sale.getItem().getName());
            dto.setDesc(sale.getItem().getDescription());
            dto.setCategory(sale.getItem().getCategory().toString());
            dto.setStatus(sale.getStatus());
            dto.setBuyername(sale.getCustomer().getUser().getName());
            dto.setPrice(sale.getItem().getPrice());
            dto.setItemId(sale.getItemID());
            dto.setStoreId(sale.getStoreID());
            dto.setCustomerId(sale.getCustomerID());
            return dto;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(saleDTOs);
    }

    @GetMapping("/{username}/orders")
    public ResponseEntity<List<SaleDTO>> getOrders(@PathVariable String username)
    {
        Customer customer = customerService.findByUsername(username).orElseThrow(() -> new RuntimeException("Store not found"));
        List<Sale> sales = saleService.findByCustomer(customer);

        List<SaleDTO> saleDTOs = sales.stream().map(sale -> {
            SaleDTO dto = new SaleDTO();
            dto.setName(sale.getItem().getName());
            dto.setDesc(sale.getItem().getDescription());
            dto.setCategory(sale.getItem().getCategory().toString());
            dto.setStatus(sale.getStatus());
            dto.setBuyername(sale.getCustomer().getUser().getName());
            dto.setStorename(sale.getStore().getUser().getName());
            dto.setPrice(sale.getItem().getPrice());
            return dto;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(saleDTOs);
    }


    @GetMapping("/stores")
    public ResponseEntity<List<StoreDTO>> getStores() {
        List<Store> stores = storeService.findAll();

        List<StoreDTO> storeDTOs = stores.stream().map(store -> {
            StoreDTO dto = new StoreDTO();
            dto.setAddress(store.getAddress());
            dto.setImagePath(store.getImagePath());
            dto.setUsername(store.getUser().getUsername());
            dto.setName(store.getUser().getName());
            return dto;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(storeDTOs);
    }

    @GetMapping("/items/{username}/{itemId}")
    public ResponseEntity<ItemDTO> getItemById(@PathVariable String username,@PathVariable int itemId) {
        Item item = itemService.findById(itemId).orElseThrow(() -> new RuntimeException("Item not found"));
        ItemDTO dto = new ItemDTO(item, storeService.findUser(username).get());
        dto.setName(item.getName());
        dto.setId(item.getId());
        dto.setDesc(item.getDescription());
        dto.setCategory(item.getCategory().toString());
        dto.setPrice(item.getPrice());
        dto.setImageUrl(username + '/' + item.getImagePath());
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/sales/{storeId}/{customerId}/{itemId}/complete")
    public ResponseEntity<String> completeSale(@PathVariable int storeId, @PathVariable int customerId, @PathVariable int itemId) {
        SaleId saleId = new SaleId(storeId, customerId, itemId);
        saleService.completeSale(saleId);
        return ResponseEntity.ok("Sale completed successfully");
    }



}
