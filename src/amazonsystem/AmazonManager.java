package amazonsystem;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import amazonsystem.AmazonCredit.PaymentType;


public class AmazonManager {

    // Scanner object to handle user input
    private Scanner input = new Scanner(System.in);

    // AmazonProductList object to handle the product-related operations
    private AmazonProductList productList = new AmazonProductList();

    /**
     * List to store the products available in the Amazon system.
     * This list contains all products that can be loaded, displayed, and searched within the system.
     */
    private List<AmazonProduct> products = new ArrayList<AmazonProduct>();

    /**
     * List to store the customers of the Amazon system.
     * This list contains all the customers who have been added to the system.
     */
    private List<AmazonCustomer> customers = new ArrayList<AmazonCustomer>();

    /**
     * The main entry point of the application.
     * Initializes an AmazonManager instance and starts the management system.
     *
     * @param args Command-line arguments (unused).
     * @throws AmazonException If there is an issue with system execution.
     */
    public static void main(String[] args) throws AmazonException {
        // Create an instance of AmazonManager
        AmazonManager manage = new AmazonManager();

        // Start managing the Amazon system
        manage.manageAmazonSystem();
    }

    /**
     * Manages the main functionality of the Amazon system by displaying a menu and handling user input.
     * This method enters a loop, prompting the user to select an option from the menu. The user's input 
     * is used to trigger corresponding actions such as loading products, adding customers, processing payments, etc.
     * The loop continues until the user chooses to exit the system by selecting the "Q" option.
     *
     * Each menu option is mapped to a specific method that performs a distinct function. If the user provides
     * an invalid input, an exception is thrown, and the error message is displayed. The method handles various
     * user operations, including managing products, customers, credits, wish lists, shopping carts, and comments.
     *
     * @throws AmazonException If an invalid menu option is selected or an error occurs while executing any operation.
     */
    public void manageAmazonSystem() throws AmazonException {

        AmazonManager use = new AmazonManager();
        String option = "";

        // Loop continues until user chooses to quit (option "Q")
        while (!option.equals("Q")) {
            use.showMenu();  // Display the menu to the user

            try {

                // Get the user's choice and convert it to uppercase 
                option = input.nextLine().toUpperCase();

                // Handle the user selection based on the selected menu option
                switch (option) {

                    case "A":
                        use.loadProductList();  // Load products from a file
                        break;
                    case "B":
                        use.showProductList();  // Show all available products
                        break;
                    case "C":
                        use.searchInProducts();  // Search for products by query
                        break;
                    case "D":
                        use.addCustomer();  // Add a new customer
                        break;
                    case "E":
                        use.showCustomers();  // Show the list of all customers
                        break;
                    case "F":
                        use.addCreditToCustomer();  // Add credit to a customer's account
                        break;
                    case "G":
                        use.showCreditFromCustomer();  // Show a customer's credit balance
                        break;
                    case "H":
                        use.addProductInWishList();  // Add a product to the customer's wish list
                        break;
                    case "I":
                        use.removeProductFromWishlist();  // Remove a product from the wish list
                        break;
                    case "J":
                        use.showWishList();  // Display the customer's wish list
                        break;
                    case "K":
                        use.addProductInCart();  // Add a product to the shopping cart
                        break;
                    case "L":
                        use.removeProductFromCart();  // Remove a product from the shopping cart
                        break;
                    case "M":
                        use.showProductsInCart();  // Show all products currently in the cart
                        break;
                    case "N":
                        use.payCart();  // Process the payment for items in the cart
                        break;
                    case "O":
                        use.addCommentToProduct();  // Add a comment to a product
                        break;
                    case "P":
                        use.showComments();  // Show all comments for products
                        break;
                    case "Q":
                        use.exit();  // Exit the application
                        break;
                    default:
                        throw new AmazonException("AmazonException: Invalid input - type a letter from A - Q!");
                }
            } catch (AmazonException e) {
                // Print the error message if an exception is caught
                System.out.println(e.getLocalizedMessage());
            }
        }
    }


    /**
     * Displays the Amazon Manager menu to the user.
     * This method prints a formatted menu that presents various options for the user to interact with the Amazon system. 
     * The options are categorized into product, customer, credit, wishlist, cart, and comment actions, allowing the user 
     * to perform tasks such as loading products, adding customers, managing credit, and interacting with the shopping cart and wishlist.
     * The menu also includes an option to exit the system.
     * The user is prompted to select an option to proceed with the desired action.
     */
    public void showMenu() {
        System.out.print("===========================================================================\n" +
                "||  ****   ****        ****   ****  ****              ALGONQUIN COLLEGE  ||\n" +
                "|| **  ** **     **   **  ** **  ** ** **            COURSE: OOP/CST8152 ||\n" +
                "|| ****** **     **   **  ** **  ** ****                 PROF: PAULO     ||\n" +
                "|| **  **  ****        ****   ****  **                TERM: FALL / 2024  ||\n" +
                "===========================================================================\n" +
                "||                      [Menu A2 - Amazon Manager]                       ||\n" +
                "===========================================================================\n" +
                "||                                  || USER                              ||\n" +
                "||                                  || Credit options .............,.... ||\n" +
                "|| ADMIN                            || [F] Add credit to customer        ||\n" +
                "||                                  || [G] Show credits from customer    ||\n" +
                "|| Product options ................ || Wishlist options ................ ||\n" +
                "|| [A] Load product list            || [H] Add product in wishlist       ||\n" +
                "|| [B] Show product list            || [I] Remove product from wishlist  ||\n" +
                "|| [C] Search products              || [J] Show products from wishlist   ||\n" +
                "||                                  || Cart options .................... ||\n" +
                "|| Customer options ............... || [K] Add product in cart           ||\n" +
                "|| [D] Add customer                 || [L] Remove product from cart      ||\n" +
                "|| [E] Show customers               || [M] Show products from cart       ||\n" +
                "||                                  || [N] Buy products from cart        ||\n" +
                "||                                  || Comment options ................. ||\n" +
                "|| ................................ || [O] Comment products bought       ||\n" +
                "||             [Q] Exit             || [P] List comments from products   ||\n" +
                "===========================================================================\n"+
                "                            Choose an option: "); 
    }

	
    /**
     * Terminates the application by displaying an exit message.
     * This method prints a message indicating the end of the application,
     * along with the names of the authors. It serves as a 
     * final output to the user when they choose to exit the Amazon Manager system.
     * The method does not perform any further actions and only prints the exit message.
     */
    public void exit() {
        System.out.println("===========================================================================");
        System.out.println("||              [End of Application (Authors: David Jacob)]              ||");
        System.out.print("===========================================================================");    
    }

    /**
     * Loads the product list from a specified file into the system.
     * This method prompts the user to enter the name of a file containing product data.
     * If no file name is provided, it defaults to a pre-defined file ("Sample-Amazon-Products-v2.csv").
     * It then attempts to load the products from the file into the product list. 
     * If any exception occurs during the loading process, the user is prompted to try again with a valid file.
     * 
     * @throws AmazonException If there is an error while reading the file or creating the product list.
     */
    public void loadProductList() throws AmazonException {
        boolean isValid = true;
        while (isValid) {
            try {
                System.out.print(
                        "Name of file to create ProductList (default file will be added if no input is given): ");
                
                String file = input.nextLine();

                if (file.isEmpty()) {
                    file = "resources\\Sample-Amazon-Products-v2.csv";
                    isValid = false;
                }
                
                products.addAll(productList.createList(file));
                
                System.out.println("[products loaded.]");
                
            } catch (AmazonException e) {
                System.out.println("AmazonProductException: " + e.getLocalizedMessage());
            }
        }
    }

	
    /**
     * Displays the list of products loaded into the system.
     * This method prints the contents of the product list by calling the printList method 
     * of the productList object to output the details of each product.
     */
    public void showProductList() {
        System.out.println("PRODUCTLIST .........");
        productList.printList();     
    }

    /**
     * Searches for products in the product list based on a user-provided query string.
     * This method prompts the user to enter a search term, and if the search term is not empty, 
     * it initiates the search process. If the search term is empty, an exception is thrown,
     * and the user is prompted to enter a valid search query.
     * 
     * @throws AmazonException If the search query is empty, an exception is thrown to alert the user.
     */
    public void searchInProducts() throws AmazonException {
        String query = "";
        boolean isValid = true;

        while (isValid) {
            try {
                
                System.out.print("Search: ");
                
                query = input.nextLine();
                if (query.isEmpty()) {
                    isValid = true;
                    throw new AmazonException("AmazonProductException: Search cannot be empty!");
                } else {
                    isValid = false;
                }
            } catch (AmazonException e) {
                
                System.out.println(e.getLocalizedMessage());
                
            }
        }
        productList.search(query);
    }

  	
  	
    /**
     * Adds a new customer to the system after validating the customer ID, name, and address.
     * This method ensures that the customer ID is unique and positive, the name and address 
     * are non-empty, and if any validation fails, the user is prompted to re-enter the details.
     * The customer information is added to the customers list.
     *
     * @throws AmazonException If any input is invalid, such as an invalid customer ID, empty name, 
     *                         or empty address.
     */
    public void addCustomer() throws AmazonException {
        String id = "";
        String name = "";
        String address = "";
        
        // Customer ID input validation loop
        while (true) {
            try {
                System.out.print("Enter the Customer ID: ");
                id = input.nextLine();

                if (!AmazonUtil.isValidInt(id)) {
                    throw new AmazonException("AmazonException: ID must be positive and an Integer!");
                }
                
                int parsedCustomerId = Integer.parseInt(id);

                // Check if the customer ID already exists
                for (AmazonCustomer c : customers) {
                    int currentCustomerId = c.getId();
                    if (parsedCustomerId == currentCustomerId) {
                        throw new AmazonException("AmazonException: The Customer already exists, enter a new id!");
                    }
                }

                break; // If valid, exit the loop

            } catch (AmazonException e) {
                System.out.println(e.getLocalizedMessage());
            }
        }

        // Customer name input validation loop
        while (true) {
            try {
                System.out.print("Enter the Customer Name: ");
                name = input.nextLine();

                if (!AmazonUtil.isValidString(name)) {
                    throw new AmazonException("AmazonException: Customer name cannot be empty!");
                }

                break; // If valid, exit the loop

            } catch (AmazonException e) {                
                System.out.println(e.getLocalizedMessage());
            }
        }

        // Customer address input validation loop
        while (true) {
            try {
                System.out.print("Enter the Customer Address: ");
                address = input.nextLine();

                if (!AmazonUtil.isValidString(address)) {
                    throw new AmazonException("AmazonException: Customer address cannot be empty!");
                }

                break; // If valid, exit the loop

            } catch (AmazonException e) {
                System.out.println(e.getLocalizedMessage());
            }
        }
        
        // Create a new customer and add to the customer list
        String [] data = {id, name, address};
        customers.add(AmazonCustomer.createAmazonCustomer(data));
    }

    /**
     * Prints the list of customers in the system.
     * If the customers list is empty, a message is displayed indicating that there are no customers.
     * Otherwise, it prints the details of each customer by calling the overridden toString method of 
     * the AmazonCustomer class.
     */
    public void showCustomers() {
    	
        if(customers.isEmpty()) {
            System.out.println("There are no customers loaded.");
            return;
        }
        
        System.out.println("[Printing customers ...]");
        for(AmazonCustomer c : customers) {
            System.out.println(c);
        }
    }

    /**
     * Adds credit to a specific customer based on the type of credit selected by the user.
     * The method allows users to add cash, check, or card credits, validates the entered data, 
     * and ensures the credit amount is positive. If the credit type already exists, the method 
     * updates the existing credit instead of adding a new one.
     * 
     * @throws AmazonException If any input is invalid, such as an invalid customer ID or credit type,
     *                         or if the credit value is non-positive.
     */
    public void addCreditToCustomer() throws AmazonException {
        String id = "";
        AmazonCustomer customer = null;
        String typeOfCredit = "";

        // Customer ID validation and search
        while (true) {
            try {
                
                if(customers.isEmpty()) {
                    throw new AmazonException("AmazonException: You must add a user before assigning credit.");
                }
                
                System.out.print("Enter the Customer ID: ");
                id = input.nextLine();

                if (!AmazonUtil.isValidInt(id)) {
                    throw new AmazonException("AmazonException: ID must be positive and an Integer!");
                }
                
                int parsedCustomerId = Integer.parseInt(id);

                // Find the customer with the provided ID
                for (AmazonCustomer c : customers) {
                    int currentCustomerId = c.getId();
                    if (parsedCustomerId == currentCustomerId) {
                        customer = c;
                        break;
                    }
                }
                
                if(customer == null) {
                    throw new AmazonException("AmazonException: Customer not found, try a different ID!");
                }

                break;

            } catch (AmazonException e) {
                System.out.println(e.getLocalizedMessage());
                if(customers.isEmpty()) {
                    return;
                }
            }
        }

        // Credit type and amount input validation loop
        while (true) {
            try {
                System.out.print("Enter the Type of credit ([1]: Cash, [2]: Check, [3]: Card): ");
                typeOfCredit = input.nextLine();

                AmazonCredit newCredit = null;
                boolean creditFound = false;

                // Switch to handle different types of credits
                switch (typeOfCredit) {
                    case "1": // Cash credit
                        System.out.print("Enter Cash value: ");
                        String cashAmount = input.nextLine();

                        if (!AmazonUtil.isValidFloat(cashAmount)) {
                            throw new AmazonException("AmazonException: Credit value must be positive!");
                        }

                        float parsedCashAmount = Float.parseFloat(cashAmount);

                        // Update existing cash credit if found
                        for (AmazonCredit existingCredit : customer.getCredits()) {
                            if (existingCredit.getType() == PaymentType.Cash) {
                                existingCredit.setAmount(existingCredit.getAmount() + parsedCashAmount);
                                System.out.println("Cash credit updated. New total: " + existingCredit.getAmount());
                                creditFound = true;
                                return;
                            }
                        }
                        
                        if(creditFound == false) {   
                            String[] cashData = {cashAmount};    
                            newCredit = AmazonCash.createCash(cashData);
                        }

                        break;

                    case "2": // Check credit
                        System.out.print("Enter Account Number: ");
                        String accountNumber = input.nextLine();
                        System.out.print("Enter Check value: ");
                        String checkAmount = input.nextLine();

                        if (!AmazonUtil.isValidFloat(checkAmount)) {
                            throw new AmazonException("AmazonException: Credit value must be positive!");
                        }

                        // Update existing check credit if found
                        for (AmazonCredit existingCredit : customer.getCredits()) {
                            if (existingCredit.getType() == PaymentType.Check) {
                                existingCredit.setAmount(existingCredit.getAmount() + Float.parseFloat(checkAmount));
                                System.out.println("Check credit updated. New total: " + existingCredit.getAmount());
                                creditFound = true;
                                return;
                            }
                        }
                        if(creditFound == false) {
                            String[] checkData = {accountNumber, checkAmount};  
                            newCredit = AmazonCheck.createCheck(checkData); 
                        }

                        break;

                    case "3": // Card credit
                        System.out.print("Enter Card Number: ");
                        String cardNumber = input.nextLine();
                        System.out.print("Enter Expiration Date (MM/YYYY): ");
                        String expiration = input.nextLine();
                        System.out.print("Enter Card value: ");
                        String cardAmount = input.nextLine();

                        if (!AmazonUtil.isValidFloat(cardAmount)) {
                            throw new AmazonException("AmazonException: Credit value must be positive!");
                        }

                        // Update existing card credit if found
                        for (AmazonCredit existingCredit : customer.getCredits()) {
                            if (existingCredit.getType() == PaymentType.Card) {
                                existingCredit.setAmount(existingCredit.getAmount() + Float.parseFloat(cardAmount));
                                System.out.println("Card credit updated. New total: " + existingCredit.getAmount());
                                creditFound = true;
                                return;
                            }
                        }
                        if(creditFound == false) {
                            String[] cardData = {cardNumber, expiration, cardAmount}; 
                            newCredit = AmazonCard.createCard(cardData);
                        }

                        break;

                    default:
                        throw new AmazonException("AmazonException: Invalid credit type selected!");
                }

                // Add new credit if created
                if (newCredit != null) {
                    customer.addCredit(newCredit);
                    System.out.println("Result: Credit added with success!");
                    break;
                }

            } catch (AmazonException e) {
                System.out.println(e.getLocalizedMessage());
            }
        }
    }

  	
    /**
     * AmazonManager method used to show the customer's credits.
     * This method prompts the user to enter a customer ID, validates it, and checks if the customer exists. 
     * If the customer is found, their credits are displayed. 
     * If no customers are added, an exception is thrown.
     */
    public void showCreditFromCustomer() {
        String id = "";
        AmazonCustomer customer = null;

        while (true) {
            try {

                if(customers.isEmpty()) {
                    throw new AmazonException("AmazonException: You must add a user before showing credit.");
                }

                System.out.print("Enter the Customer ID: ");
                id = input.nextLine();

                if (!AmazonUtil.isValidInt(id)) {
                    throw new AmazonException("AmazonException: ID must be positive and an Integer!");
                }

                int parsedCustomerId = Integer.parseInt(id);

                // For-Each search loop to find customer ID
                for (AmazonCustomer c : customers) {
                    int currentCustomerId = c.getId();
                    if (parsedCustomerId == currentCustomerId) {
                        customer = c;
                        break;
                    }
                }

                if(customer == null) {
                    throw new AmazonException("AmazonException: Customer not found, try a different ID!");
                }

                break;

            } catch (AmazonException e) {
                System.out.println(e.getLocalizedMessage());

                if(customers.isEmpty()) {
                    return;
                }
            }
        }

        customer.showCredits();
    }


    /**
     * AmazonManager method used to add products to a customer's wishlist.
     * This method prompts the user to enter both a customer ID and a product ID, validates them, 
     * and checks if both the customer and the product exist. 
     * If both are valid, the product is added to the customer's wishlist.
     * If no customers or products are added, or if either the customer or the product cannot be found, 
     * an exception is thrown.
     */
    public void addProductInWishList() {
        String customerId = "";
        String productId = "";
        AmazonCustomer customer = null;
        AmazonProduct product = null;

        while (true) {
            try {

                if(products.isEmpty() && customers.isEmpty()) {
                    throw new AmazonException("AmazonException: You must add both users and products before adding a product to your wishlist.");
                } else if(products.isEmpty()) {
                    throw new AmazonException("AmazonException: There are no products to be added; please load products and try again.");
                } else if(customers.isEmpty()) {
                    throw new AmazonException("AmazonException: You must add a user before adding a product to your wishlist.");
                }

                System.out.print("Enter the Customer ID: ");
                customerId = input.nextLine();

                if (!AmazonUtil.isValidInt(customerId)) {
                    throw new AmazonException("AmazonException: ID must be positive and an Integer!");
                }

                int parsedCustomerId = Integer.parseInt(customerId);

                // For-Each search loop to find customer ID
                for (AmazonCustomer c : customers) {
                    int currentCustomerId = c.getId();
                    if (parsedCustomerId == currentCustomerId) {
                        customer = c;
                        break;
                    }
                }

                if (customer == null) {
                    throw new AmazonException("AmazonException: Customer not found, try a different ID!");
                }
                break; // break once we're sure the customer isn't null

            } catch (AmazonException e) {
                System.out.println(e.getLocalizedMessage());

                if(products.isEmpty() && customers.isEmpty()) {
                    return;
                } else if(products.isEmpty()) {
                    return;
                } else if(customers.isEmpty()) {
                    return;
                }
            }
        }

        while (true) {
            try {
                System.out.print("Enter the Product ID to include in the Wishlist: ");
                productId = input.nextLine();

                if (!AmazonUtil.isValidInt(productId)) {
                    throw new AmazonException("AmazonException: ID must be positive and an Integer!");
                }

                int parsedProductId = Integer.parseInt(productId);

                // For-Each loop to find matching product ID
                for (AmazonProduct p : products) {
                    int currentProductId = p.getId();
                    if (parsedProductId == currentProductId) {
                        product = p;
                        break;
                    }
                }

                if (product == null) {
                    throw new AmazonException("AmazonException: Product not found, try a different ID!");
                }

                customer.addProductInWishList(product);
                System.out.println("[Product " + product.getId() + " added into customer " + customer.getId() + " wishlist]");

                break; 

            } catch (AmazonException e) {
                System.out.println(e.getLocalizedMessage());
            }
        }
    }


  	
    /**
     * AmazonManager method used to remove products from a customer's wishlist.
     * This method prompts the user to enter both a customer ID and a product ID, validates them, 
     * and checks if both the customer and the product exist. 
     * If both are valid, the product is removed from the customer's wishlist.
     * If no customers or products are added, or if either the customer or the product cannot be found, 
     * an exception is thrown.
     */
    public void removeProductFromWishlist() {
        String customerId = "";
        String productId = "";
        AmazonCustomer customer = null;
        AmazonProduct product = null;

        while (true) {
            try {
                if(products.isEmpty() && customers.isEmpty()) {
                    throw new AmazonException("AmazonException: You must add both users and products to remove a product from your wishlist.");
                } else if(products.isEmpty()) {
                    throw new AmazonException("AmazonException: There are no products to be removed; please load products and try again.");
                } else if(customers.isEmpty()) {
                    throw new AmazonException("AmazonException: You must add a user before removing a product from your wishlist.");
                }

                System.out.print("Enter the Customer ID: ");
                customerId = input.nextLine();

                if (!AmazonUtil.isValidInt(customerId)) {
                    throw new AmazonException("AmazonException: ID must be positive and an Integer!");
                }

                int parsedCustomerId = Integer.parseInt(customerId);

                // For-Each search loop to find customer ID
                for (AmazonCustomer c : customers) {
                    int currentCustomerId = c.getId();
                    if (parsedCustomerId == currentCustomerId) {
                        customer = c;
                        break;
                    }
                }

                if (customer == null) {
                    throw new AmazonException("AmazonException: Customer not found, try a different ID!");
                }
                break;

            } catch (AmazonException e) {
                System.out.println(e.getLocalizedMessage());

                if(products.isEmpty() && customers.isEmpty()) {
                    return;
                } else if(products.isEmpty()) {
                    return;
                } else if(customers.isEmpty()) {
                    return;
                }
            }
        }

        while (true) {
            try {
                System.out.print("Enter the Product ID to remove from the Cart: ");
                productId = input.nextLine();

                if (!AmazonUtil.isValidInt(productId)) {
                    throw new AmazonException("AmazonException: ID must be positive and an Integer!");
                }

                int parsedProductId = Integer.parseInt(productId);

                // For-Each loop to find matching product ID
                for (AmazonProduct p : products) {
                    int currentProductId = p.getId();
                    if (parsedProductId == currentProductId) {
                        product = p;
                        break;
                    }
                }

                if (product == null) {
                    throw new AmazonException("AmazonException: Product not found, try a different ID!");
                }
                break;

            } catch (AmazonException e) {
                System.out.println(e.getLocalizedMessage());
            }
        }

        customer.removeProductFromWishList(product);
        System.out.println("[Product " + product.getId() + " removed from wishlist from " + customer.getId() + " ]");
    }

    /**
     * AmazonManager method used to print the contents of a customer's wishlist.
     * This method prompts the user to enter a customer ID, validates it, and checks if the customer exists.
     * If the customer is found, their wishlist is displayed.
     * If no customers are added, an exception is thrown.
     */
    public void showWishList() {
        String customerId = "";
        AmazonCustomer customer = null;

        while (true) {
            try {

                if(customers.isEmpty()) {
                    throw new AmazonException("AmazonException: You must add a user before displaying a wishlist.");
                }

                System.out.print("Enter the Customer ID: ");
                customerId = input.nextLine();

                if (!AmazonUtil.isValidInt(customerId)) {
                    throw new AmazonException("AmazonException: ID must be positive and an Integer!");
                }

                int parsedCustomerId = Integer.parseInt(customerId);

                // For-Each search loop to find customer ID
                for (AmazonCustomer c : customers) {
                    int currentCustomerId = c.getId();
                    if (parsedCustomerId == currentCustomerId) {
                        customer = c;
                        break;
                    }
                }

                if (customer == null) {
                    throw new AmazonException("AmazonException: Customer not found, try a different ID!");
                }
                break;

            } catch (AmazonException e) {
                System.out.println(e.getLocalizedMessage());
                if(customers.isEmpty()) {
                    return;
                }
            }
        }

        customer.showWishList();
    }

  	
    /**
     * AmazonManager method used to add a product to a customer's shopping cart.
     * This method prompts the user to input a customer ID, product ID, and quantity to add to the cart. 
     * It performs validation checks for valid IDs and quantity, and ensures that both a customer and a product exist before proceeding.
     * Once validated, the product is added to the customer's cart with the specified quantity.
     * If there are no customers or products added, or if the IDs provided cannot be found, an exception is thrown.
     */
    public void addProductInCart() {
        String customerId = "";
        String productId = "";
        String quantity = "";

        AmazonCustomer customer = null;
        AmazonProduct product = null;

        // Get the Customer
        while (true) {
            try {

                if(products.isEmpty() && customers.isEmpty()) {
                    throw new AmazonException("AmazonException: You must add both users and products before adding a product to your cart.");
                } else if(products.isEmpty()) {
                    throw new AmazonException("AmazonException: There are no products to be added; please load products and try again.");
                } else if(customers.isEmpty()) {
                    throw new AmazonException("AmazonException: You must add a user before adding a product to your cart.");
                }

                System.out.print("Enter the Customer ID: ");
                customerId = input.nextLine();

                if (!AmazonUtil.isValidInt(customerId)) {
                    throw new AmazonException("AmazonException: ID must be positive and an Integer!");
                }

                int parsedCustomerId = Integer.parseInt(customerId);

                // For-Each search loop to find customer ID
                for (AmazonCustomer c : customers) {
                    int currentCustomerId = c.getId();
                    if (parsedCustomerId == currentCustomerId) {
                        customer = c;
                        break;
                    }
                }

                if (customer == null) {
                    throw new AmazonException("AmazonException: Customer not found, try a different ID!");
                }

                break;  // Exit loop after finding the customer

            } catch (AmazonException e) {
                System.out.println(e.getLocalizedMessage());

                if(products.isEmpty() && customers.isEmpty()) {
                    return;
                } else if(products.isEmpty()) {
                    return;
                } else if(customers.isEmpty()) {
                    return;
                }
            }
        }

        // Get the Product
        while (true) {
            try {
                System.out.print("Enter the Product ID to add to your cart: ");
                productId = input.nextLine();

                if (!AmazonUtil.isValidInt(productId)) {
                    throw new AmazonException("AmazonException: ID must be positive and an Integer!");
                }

                int parsedProductId = Integer.parseInt(productId);

                // For-Each loop to find matching product ID
                for (AmazonProduct p : products) {
                    int currentProductId = p.getId();
                    if (parsedProductId == currentProductId) {
                        product = p;
                        break;
                    }
                }

                if (product == null) {
                    throw new AmazonException("AmazonException: Product not found, try a different ID!");
                }

                break;  // Exit loop after finding the product

            } catch (AmazonException e) {
                System.out.println(e.getLocalizedMessage());
            }
        }

        // Get the Quantity of Product to be added to Cart
        while (true) {
            try {
                System.out.print("Enter the number of items to put in cart: ");
                quantity = input.nextLine();

                if (!AmazonUtil.isValidInt(quantity)) {
                    throw new AmazonException("AmazonException: Quantity must be a positive integer!");
                }

                int quantityInt = Integer.parseInt(quantity);

                if (quantityInt <= 0) {
                    throw new AmazonException("AmazonException: Quantity must be greater than zero!");
                }

                // Create the AmazonCartItem and add it to the customer's cart
                AmazonCartItem cartItem = new AmazonCartItem(product, quantityInt);
                customer.addItemInCart(cartItem);  

                int maxLength = 30; 
                String productName = product.getName();
                String displayName;

                if (productName.length() > maxLength) { 
                    displayName = productName.substring(0, maxLength - 3) + "..."; 
                } else {
                    displayName = productName; // Use the full name if short enough
                }

                System.out.println("Cart updated: [" + quantityInt + " " + displayName + " added for customer " + customer.getId() + "]");

                break;  // Exit the loop after successfully adding to cart

            } catch (AmazonException e) {
                System.out.println(e.getLocalizedMessage());
            }
        }
    }

  	
    /**
     * AmazonManager method used to remove a product from a customer's shopping cart.
     * This method prompts the user for a customer ID and product ID, validates the inputs, and removes the product from the customer's cart.
     * If the customer or product cannot be found, an exception is thrown.
     * It also ensures that a customer is added to the system before performing any operations.
     * @throws AmazonException 
     */
    public void removeProductFromCart() {
        String customerId = "";
        AmazonCustomer customer = null;
        String AmazonCartItemId = "";
        AmazonCartItem currentItem = null;

        while (true) {
            try {

                if(customers.isEmpty()) {
                    throw new AmazonException("AmazonException: You must add users before removing the products in your cart.");
                }

                System.out.print("Enter the Customer id: ");
                customerId = input.nextLine();

                if (!AmazonUtil.isValidInt(customerId)) {
                    throw new AmazonException("AmazonException: ID must be positive and an Integer!");
                }

                int parsedCustomerId = Integer.parseInt(customerId);

                // For-Each search loop to find customer ID
                for (AmazonCustomer c : customers) {
                    int currentCustomerId = c.getId();
                    if (parsedCustomerId == currentCustomerId) {
                        customer = c;
                        break;
                    }
                }
                

                if (customer == null) {
                    throw new AmazonException("AmazonException: Customer not found!");
                }
                
                if(customer.getCart().getItems().isEmpty()) {
                	throw new AmazonException("AmazonException: There aren't any products in your cart to remove.");
                }

                break;

            } catch (AmazonException e) {
                System.out.println(e.getLocalizedMessage());

                if(customers.isEmpty()) {
                    return;
                }
            }
        }
        
       

        // Get the Product to remove from cart
        while (true) {
            try {
                System.out.print("Enter the ID of the product you'd like to remove from your cart: ");
                AmazonCartItemId = input.nextLine();

                if (!AmazonUtil.isValidInt(AmazonCartItemId)) {
                    throw new AmazonException("AmazonException: ID must be positive and an Integer!");
                }

                int parsedAmazonCartItemId = Integer.parseInt(AmazonCartItemId);
                List <AmazonCartItem> AmazonCartItems = customer.getCart().getItems();

                // For-Each loop to find matching product ID
                for (AmazonCartItem i : AmazonCartItems) {
                    int currentProductId = i.getProduct().getId();
                    if (parsedAmazonCartItemId == currentProductId) {
                        currentItem = i;
                        AmazonCartItems.remove(i);
                        System.out.println("Product removed.");
                        break;
                    }
                }

                if (currentItem == null) {
                    throw new AmazonException("AmazonException: Product not found, try a different ID!");
                }

                break;  // Exit loop after finding and removing the product

            } catch (AmazonException e) {
                System.out.println(e.getLocalizedMessage());
            }
        }
    }

    /**
     * AmazonManager method used to display the products in a customer's shopping cart.
     * This method prompts the user for a customer ID, validates it, and then displays the contents of the customer's shopping cart.
     * If the customer cannot be found, an exception is thrown.
     */
    public void showProductsInCart() {
        String customerId = "";
        AmazonCustomer customer = null;

        while (true) {
            try {

                if(customers.isEmpty()) {
                    throw new AmazonException("AmazonException: You must add users before showing the products in your cart.");
                }

                System.out.print("Enter the Customer id: ");
                customerId = input.nextLine();

                if (!AmazonUtil.isValidInt(customerId)) {
                    throw new AmazonException("AmazonException: ID must be positive and an Integer!");
                }

                int parsedCustomerId = Integer.parseInt(customerId);

                // For-Each search loop to find customer ID
                for (AmazonCustomer c : customers) {
                    int currentCustomerId = c.getId();
                    if (parsedCustomerId == currentCustomerId) {
                        customer = c;
                        break;
                    }
                }

                if (customer == null) {
                    throw new AmazonException("AmazonException: Customer not found!");
                }

                break;

            } catch (AmazonException e) {
                System.out.println(e.getLocalizedMessage());

                if(customers.isEmpty()) {
                    return;
                }
            }
        }

        customer.showCart();
    }

  
  	
    /**
     * AmazonManager method used to process the payment for a customer's shopping cart.
     * The method prompts the user to input a customer ID and validates it.
     * It checks if the customer has sufficient credits and a valid payment method before proceeding with the purchase.
     * Available payment methods include cash, check, and card. If the customer lacks sufficient credits for the selected payment method,
     * an exception is thrown.
     */
    public void payCart() {
        String customerId = "";
        AmazonCustomer customer = null;
        String typeOfCredit = "";
        boolean validPaymentType = false;
        boolean isEmpty = false;

        while (true) {
            try {

                if(products.isEmpty() && customers.isEmpty()) {
                    throw new AmazonException("AmazonException: You must add both users and products before purchasing items from your cart.");
                } else if(products.isEmpty()) {
                    throw new AmazonException("AmazonException: There are no items to be purchased; please load products and try again.");
                } else if(customers.isEmpty()) {
                    throw new AmazonException("AmazonException: You must add a user before attempting to purchase items.");
                }

                System.out.print("Enter the Customer id: ");
                customerId = input.nextLine();

                if (!AmazonUtil.isValidInt(customerId)) {
                    throw new AmazonException("AmazonException: ID must be positive and an Integer!");
                }

                int parsedCustomerId = Integer.parseInt(customerId);

                // For-Each search loop to find customer ID
                for (AmazonCustomer c : customers) {
                    int currentCustomerId = c.getId();
                    if (parsedCustomerId == currentCustomerId) {
                        customer = c;
                        break;
                    }
                }
                
                if (customer == null) {
                    throw new AmazonException("AmazonException: Customer not found!");
                }

                if(customer.getCredits().isEmpty()) {
                	isEmpty = true;
                    throw new AmazonException("AmazonException: You must add credit before attempting to purchase items from your cart.");
                }

                break;

            } catch (AmazonException e) {
                System.out.println(e.getLocalizedMessage());

                if(products.isEmpty() && customers.isEmpty()) {
                    return;
                } else if(products.isEmpty()) {
                    return;
                } else if(customers.isEmpty()) {
                    return;
                } else if(isEmpty == true) {
                    return;
                }
            }
        }

        AmazonCart cart = customer.getCart();
        if (cart == null || cart.getItems().isEmpty()) {
            System.out.println("Cart is empty. You cannot pay for an empty cart.");
            return;
        }

        float totalAmount = cart.calcSubTotal();
        System.out.println("Total amount: " + totalAmount);

        while (true) {
            try {
                System.out.print("Enter the payment method ([1]: Cash, [2]: Check, [3]: Card): ");
                typeOfCredit = input.nextLine();
                int creditSize = customer.getCredits().size();
                boolean paymentFound = false;

                switch (typeOfCredit) {
                    case "1":
                        for (int i = 0; i < creditSize; i++) {
                            PaymentType creditType = customer.getCredits().get(i).getType();
                            boolean isCash = creditType == PaymentType.Cash;
                            if (isCash) {
                                AmazonCredit credit = customer.getCredits().get(i);
                                System.out.println("Payment type is cash");
                                if(customer.pay(credit) == false) {
                                    throw new AmazonException("AmazonException: You don't have enough credits of cash, try a different payment method.");
                                }
                                System.out.println("Customer credit updated: " + credit);
                                System.out.println("Cart empty - you can comment products now.");
                                validPaymentType = true;
                                paymentFound = true;
                                break;
                            } 
                        }
                        
                        if(paymentFound == false) {
                            throw new AmazonException("AmazonException: No credit found; add cash to customer and try again.");
                        }
                        
                        return;

                    case "2":
                        for (int i = 0; i < creditSize; i++) {
                            PaymentType creditType = customer.getCredits().get(i).getType();
                            boolean isCheck = creditType == PaymentType.Check;
                            if (isCheck) {
                                AmazonCredit credit = customer.getCredits().get(i);
                                System.out.println("Payment type is check");
                                if(customer.pay(credit) == false) {
                                    throw new AmazonException("AmazonException: You don't have enough credits on your check, try a different payment method.");
                                }
                                System.out.println("Customer credit updated: " + credit);
                                System.out.println("Cart empty - you can comment products now.");
                                validPaymentType = true;
                                break;
                            } 
                        }
                        
                        if(paymentFound == false) {
                            throw new AmazonException("AmazonException: No credit found; add check to customer and try again.");
                        }
                        return;

                    case "3":
                        for (int i = 0; i < creditSize; i++) {
                            PaymentType creditType = customer.getCredits().get(i).getType();
                            boolean isCard = creditType == PaymentType.Card;
                            if (isCard) {
                                AmazonCredit credit = customer.getCredits().get(i);
                                System.out.println("Payment type is card");

                                if(customer.pay(credit) == false) {
                                    throw new AmazonException("AmazonException: You don't have enough credits on your card, try a different payment method.");
                                }
                                System.out.println("Customer credit updated: " + credit);
                                System.out.println("Cart empty - you can comment products now.");
                                validPaymentType = true;
                                break;
                            } 
                        }
                        
                        if(paymentFound == false) {
                            throw new AmazonException("AmazonException: No credit found; add card to customer and try again.");
                        }
                        
                        return;

                    default:
                        throw new AmazonException("AmazonException: Invalid credit type selected!");
                }

            } catch (AmazonException e) {
                System.out.println(e.getLocalizedMessage());

                if(validPaymentType == false) {
                    return;
                }
            }
        }
    }

    /**
     * AmazonManager method that allows a customer to add a comment and rating to a product.
     * It ensures that there are both users and products in the system, and the customer has purchased products before commenting.
     * The method validates the customer and product IDs, and accepts a comment and rating for the product.
     * If the input is valid, the comment is added to the product by the customer.
     */
    public void addCommentToProduct() {
        String customerId = "";
        String productId = "";
        AmazonCustomer customer = null;
        AmazonProduct product = null;
        String comment = "";
        String rating = "";

        while (true) {
            try {

                if(products.isEmpty() && customers.isEmpty()) {
                    throw new AmazonException("AmazonException: You must add both users and products before commenting.");
                } else if(products.isEmpty()) {
                    throw new AmazonException("AmazonException: There are no items to comment; please load products and try again.");
                } else if(customers.isEmpty()) {
                    throw new AmazonException("AmazonException: You must add a user before attempting to comment.");
                }

                System.out.print("Enter the Customer ID: ");
                customerId = input.nextLine();

                if (!AmazonUtil.isValidInt(customerId)) {
                    throw new AmazonException("AmazonException: ID must be positive and an Integer!");
                }

                int parsedCustomerId = Integer.parseInt(customerId);

                // For-Each search loop to find customer ID
                for (AmazonCustomer c : customers) {
                    int currentCustomerId = c.getId();
                    if (parsedCustomerId == currentCustomerId) {
                        customer = c;
                        break;
                    }
                }

                if (customer == null) {
                    throw new AmazonException("AmazonException: Customer not found, try a different ID!");
                }

                if(customer.getComments().isEmpty()) {
                    throw new AmazonException("AmazonException: This customer has no items to comment; buy products from cart and try again.");
                }

                break;

            } catch (AmazonException e) {
                System.out.println(e.getLocalizedMessage());

                if(products.isEmpty() && customers.isEmpty()) {
                    return;
                } else if(products.isEmpty()) {
                    return;
                } else if(customers.isEmpty()) {
                    return;
                } else if(customer.getComments().isEmpty()) {
                    return;
                }
            }
        }

        while (true) {
            try {

                System.out.print("Enter the ID of the product you want to comment on: ");
                productId = input.nextLine();

                if (!AmazonUtil.isValidInt(productId)) {
                    throw new AmazonException("AmazonException: ID must be positive and an Integer!");
                }

                int parsedProductId = Integer.parseInt(productId);

                // For-Each loop to find product ID
                for (AmazonProduct p : products) {
                    int currentProductId = p.getId();
                    if (parsedProductId == currentProductId) {
                        product = p;
                        break;
                    }
                }

                if (product == null) {
                    throw new AmazonException("AmazonException: Product not found, try a different ID!");
                }
                
                break;

            } catch (AmazonException e) {
                System.out.println(e.getLocalizedMessage());
            }
        }

        System.out.println("Commenting product: [" + product.getName() + "] ..."); // Display chosen product

        System.out.print("Enter the comment: "); // Prompt for comment
        comment = input.nextLine(); // Assign entered comment

        System.out.print("Enter the stars: "); // Prompt for rating
        rating = input.nextLine(); // Assign entered rating

        float ratingFloat = Float.parseFloat(rating);

        customer.setComment(product, comment, ratingFloat);

        System.out.println("Comment from customer " + customer);
    }

  	
    /**
     * AmazonManager method that displays all comments made by a customer.
     * It ensures that there are both users and products in the system before attempting to display the comments.
     * The method validates the customer ID and displays the comments associated with the customer.
     */
    public void showComments() {
        String customerId = "";
        AmazonCustomer customer = null;

        while (true) {
            try {

                if(products.isEmpty() && customers.isEmpty()) {
                    throw new AmazonException("AmazonException: You must add both users and products before displaying comments.");
                } else if(products.isEmpty()) {
                    throw new AmazonException("AmazonException: There are no items to comment; please load products and try again.");
                } else if(customers.isEmpty()) {
                    throw new AmazonException("AmazonException: You must add a user before attempting to display comments.");
                }

                System.out.print("Enter the Customer ID: ");
                customerId = input.nextLine();

                if (!AmazonUtil.isValidInt(customerId)) {
                    throw new AmazonException("AmazonException: ID must be positive and an Integer!");
                }

                int parsedCustomerId = Integer.parseInt(customerId);

                // For-Each search loop to find customer ID
                for (AmazonCustomer c : customers) {
                    int currentCustomerId = c.getId();
                    if (parsedCustomerId == currentCustomerId) {
                        customer = c;
                        break;
                    }
                }

                if (customer == null) {
                    throw new AmazonException("AmazonException: Customer not found, try a different ID!");
                }
                break;

            } catch (AmazonException e) {
                System.out.println(e.getLocalizedMessage());

                if(products.isEmpty() && customers.isEmpty()) {
                    return;
                } else if(products.isEmpty()) {
                    return;
                } else if(customers.isEmpty()) {
                    return;
                }
            }
        }

        customer.showComments();
    }

  	
  	/////////////////////////////////////////////////THE SBA METHODS////////////////////////////////////////////////
  	
  	
  	public void save(String fileName, List<AmazonCustomer> customers) {
  	    try (FileWriter file = new FileWriter(fileName)) {


  	        file.write("SHOWING AMAZON DATA .....................\n");

  	        for (AmazonCustomer c : customers) {
  	            file.write(c + "\n");

  	            file.write("- Credit List:\n");
  	            file.write(c.getCredits() + "\n");

  	            if (c.getWishlist().isEmpty()) {
  	                file.write("- Wish list: [No wish list]\n");
  	            } else {
  	                file.write("- Wishlist:\n");
  	                int i = 0;
  	                for (AmazonProduct p : c.getWishlist()) {
  	                    file.write("  - Wishlist [" + i++ + "]: " + p.getName() + "\n");
  	                }
  	            }

  	            if (c.getCart().getItems().isEmpty()) {
  	                file.write("- Cart: [No items]\n");
  	            } else {
  	                file.write("- Cart:\n");
  	                file.write(c.getCart().toString() + "\n");
  	            }

  	            if (c.getComments().isEmpty()) {
  	                file.write("- Comments: [No comments]\n");
  	            } else {
  	                file.write("- Comments:\n");
  	                int i = 0;
  	                for (AmazonComment comment : c.getComments()) {
  	                    file.write("  - Comment[" + i++ + "]: " + comment + "\n");
  	                }
  	            }

  	            file.write("\n");
  	        }


  	    } catch (IOException e) {
  	        System.out.println("Error saving the file: " + e.getMessage());
  	    }
  	}

  	
  	 public void load(String fileName) {
  	    try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
  	        String line;
  	        AmazonCustomer currentCustomer = null;
  	        AmazonProduct  currentProduct = null;
  	        AmazonCartItem currentItem = null;
  	        AmazonCredit credit = null;
  	        

  	        while ((line = reader.readLine()) != null) {
  	            
  	            if (line.startsWith("- Customer:")) {
  	            	String [] data = new String[3];
  	            	currentCustomer = AmazonCustomer.createAmazonCustomer(data = AmazonUtil.lineReader(line, 0));
  	            	System.out.println(currentCustomer);
  	                customers.add(currentCustomer);
  	            }
  	            
  	           
  	            if (line.startsWith("- Credit [")) {
  	            	String [] data = new String[2];
  	                customers.add(currentCustomer);
  	                
  	               credit = AmazonCash.createCash(data = AmazonUtil.lineReader(line, 0));
  	                currentCustomer.addCredit(credit);
  	            }

  	       
  	            if (line.startsWith("- Wishlist [")) {
  	            	String [] data = new String[10];
  	            	currentProduct = AmazonProduct.createAmazonProduct(data = AmazonUtil.lineReader(line, 0));
  	                currentCustomer.addProductInWishList(currentProduct);
  	            }

  	         
  	            if (line.startsWith("- Item[")) {
  	            	String [] data = new String[10];
  	            	currentProduct = AmazonProduct.createAmazonProduct(data = AmazonUtil.lineReader(line, 0));
  	            	currentItem = new AmazonCartItem(currentProduct,currentItem.getQuantity());
  	                currentCustomer.addItemInCart(currentItem);
  	            }

  	            
  	            if (line.startsWith("- Comment[")) {
  	            	String [] data = new String[3];
  	            	currentProduct = AmazonProduct.createAmazonProduct(data = AmazonUtil.lineReader(line, 0));
  	            	AmazonComment comment = new AmazonComment(currentProduct);
  	                currentCustomer.addComment(comment);
  	            }
  	        }

  	    } catch (IOException e) {
  	        System.out.println("Error loading the file: " + e.getMessage());
  	    }
  	    
  	}

  	
  	public void show(List<AmazonCustomer> customers) {
  		
  		System.out.println("SHOWING AMAZON DATA .....................");
  		for (AmazonCustomer c : customers) {
  	        System.out.println( c); 
  	        System.out.println("- Credit List:");
  	        System.out.println(c.getCredits());
  	        if(c.getWishlist().isEmpty()) {
  	        	System.out.println("- Wish list: [No wish list]");
  	        }else {
  	        	int i = 0;
  	        	for(AmazonProduct p: c.getWishlist()) {
  	        		System.out.println("- Wishlist [" + i++ +"]:" + p.getName());
  	        	}
  	        }
  	        
  	        if(c.getCart().getItems().isEmpty()) {
  	        	System.out.println("- Cart:[No items]");
  	        }else {
  	        	System.out.println("- Cart:");
  	        	System.out.println(c.getCart());
  	        }
  	        if(c.getComments().isEmpty()) {
  	        	System.out.println("- Comments: [No comments]");
  	        }else {
  	        	System.out.println("- Comments: ");
  	        	int i = 0;
  	        	for(AmazonComment comment: c.getComments()) {
  	        	System.out.println(" - Comment[" + i++ + "]: " + comment);	
  	        	}
  	        }
  		}
  		
  		
  		
  		
  	}
  	
  	
  	

}