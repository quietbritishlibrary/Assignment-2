package amazonsystem;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import amazonsystem.AmazonCredit.PaymentType;


public class AmazonManager {
	
	// objects
	Scanner input = new Scanner(System.in);
	AmazonProductList productList = new AmazonProductList();
     
    // lists
    private List <AmazonProduct> products = new ArrayList<AmazonProduct>();
	private List <AmazonCustomer> customers = new ArrayList<AmazonCustomer>();
	
	// main
	public static void main(String[] args) throws AmazonException {
		AmazonManager manage = new AmazonManager();
		manage.manageAmazonSystem();
		
	}

	
	public void manageAmazonSystem() throws AmazonException {

		AmazonManager use = new AmazonManager();
		String option = "";
		
		while (!option.equals("Q")) {
			use.showMenu();
			//System.out.print("                              Choose an option: ");

			try {

				option = input.nextLine().toUpperCase();

			

			// Handle the user selection based on menu choice
			switch (option) {
			
			case "A":
				use.loadProductList();;
				break;
			case "B":
				use.showProductList();
				break;
			case "C":
				use.searchInProducts();;
				break;
			case "D":
				use.addCustomer();
				break;
			case "E":
				use.showCustomers();
				break;
			case "F":
				use.addCreditToCustomer();
				break;
			case "G":
				use.showCreditFromCustomer();
				break;
			case "H":
				use.addProductInWishList();
				break;
			case "I":
				use.removeProductFromWishlist();
				break;
			case "J":
				use.showWishList();
				break;
			case "K":
				use.addProductInCart();
				break;
			case "L":
				use.removeProductFromCart();
				break;	
			case "M":
				use.showProductsInCart();
				break;
			case "N":
				use.payCart();
				break;
			case "O":
				use.addCommentToProduct();
				break;
			case "P":
				use.showComments();
				break;
			case "Q":
				use.exit();
				break;	
			default:
				
				throw new AmazonException("AmazonException: Invalid input - type a letter from A - Q!");
				
			}
			} catch (AmazonException e) {
				
			System.out.println(e.getLocalizedMessage());
			}
		}

		
	}
	

	
	
	/**
     * AmazonManager method used to display the menu
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
	
	public void exit() {
		System.out.println("===========================================================================");
		System.out.println("||              [End of Application (Authors: David Jacob)]              ||");
		System.out.print("===========================================================================");	
	}
	
	//load products from CSV onto the products list
	public void loadProductList() throws AmazonException {
		boolean isValid = true;
		while (isValid) {
			try {
				System.out.print(
						"Name of file to create ProductList (default file will be added if no input is given): ");
				
				String file = input.nextLine();

				if (file.isEmpty()) {
					file = "src\\amazonsystem\\Sample-Amazon-Products-v2.csv";
					isValid = false;
				}
				
				products.addAll(productList.createList(file));
				
				
				System.out.println("product loaded!");
				
			} catch (AmazonException e) {
				
				System.out.println("AmazonProductException: " + e.getLocalizedMessage());
				
			}
		}
	}
	
	//print contents of the products list
	public void showProductList() {
		System.out.println("PRODUCTLIST .........");
		productList.printList();     
	}
	
	
    
  //search for products with the given string
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
     * AmazonManager method used for adding a customer 
     */
  	public void addCustomer() throws AmazonException {
  		    String id = "";
  		    String name = "";
  		    String address = "";
  		    

  		    while (true) {
  		        try {
  		            System.out.print("Enter the Customer ID: ");
  		            id = input.nextLine();

  		            if (!AmazonUtil.isValidInt(id)) {
  		                throw new AmazonException("AmazonException: ID must be positive and an Integer!");
  		            }
  		            
  		            int parsedCustomerId = Integer.parseInt(id);
  		            
  		           // For-Each search loop to check validity of customer ID
  	  	          for (AmazonCustomer c : customers) {
  	 	                int currentCustomerId = c.getId();
  	 	                if (parsedCustomerId == currentCustomerId) {
  	 	                    throw new AmazonException("AmazonException: The Customer already exists, enter a new id!");
  	 	                }
  	 	            }
  	  	          
  		            

  		            break;

  		        } catch (AmazonException e) {
  		            System.out.println(e.getLocalizedMessage());
  		        }
  		    }

  		    while (true) {
  		        try {
  		            System.out.print("Enter the Customer Name: ");
  		            name = input.nextLine();

  		            if (!AmazonUtil.isValidString(name)) {
  		                throw new AmazonException("AmazonException: Customer name cannot be empty!");
  		            }

  		            break; 

  		        } catch (AmazonException e) {  		     
  		            System.out.println(e.getLocalizedMessage());
  		        }
  		    }

  		    while (true) {
  		        try {
  		            System.out.print("Enter the Customer Address: ");
  		            address = input.nextLine();

  		            if (!AmazonUtil.isValidString(address)) {
  		                throw new AmazonException("AmazonException: Customer address cannot be empty!");
  		            }

  		            break; 

  		        } catch (AmazonException e) {
  		            System.out.println(e.getLocalizedMessage());
  		        }
  		    }
  		    
  		    String [] data = {id,name,address};
  		    customers.add(AmazonCustomer.createAmazonCustomer(data));

  	}
  	
  	/**
     * AmazonManager method used for printing the contents of the customers list
     */
  	public void showCustomers() {
  		if(customers.isEmpty()) {
  			System.out.println("There are no customers loaded");
  		}
  		System.out.println("[Printing customers ...]");
		for(AmazonCustomer c : customers) {
			System.out.println(c);
		}
  	}
  	
  	/**
     * AmazonManager method used for adding credit type and amounts to customers
  	 * @throws AmazonException 
     */
  	public void addCreditToCustomer() throws AmazonException {
  	    String id = "";
  	    int size = customers.size();
  	    AmazonCustomer customer = null;
  	    String typeOfCredit = "";
  	    
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
  	            
  	            	// For-Each search loop to find customer ID
    	          for (AmazonCustomer c : customers) {
   	                int currentCustomerId = c.getId();
   	                if (parsedCustomerId == currentCustomerId) {
   	                    customer = c;
   	                    break;
   	                }
   	            }
  	            
  	            if(customer == null) { // check if customer is null
  	            	throw new AmazonException("AmazonException: Customer not found, try a different ID!");
  	            }
  	            
  	            break; // customer found now leave while loop

  	        } catch (AmazonException e) {
  	            System.out.println(e.getLocalizedMessage());
  	          if(customers.isEmpty()) {
	            	return;
	            }
  	        }
  	    }
  	    
  	  
  	    while (true) {
  	        try {
  	            System.out.print("Enter the Type of credit ([1]: Cash, [2]: Check, [3]: Card): ");
  	            typeOfCredit = input.nextLine();
  	            
  	            AmazonCredit newCredit = null;
  	            boolean creditFound = false;

  	            switch (typeOfCredit) {
  	                case "1":
  	                    System.out.print("Enter Cash value: ");
  	                    String cashAmount = input.nextLine();
  	                    
  	                    if (!AmazonUtil.isValidFloat(cashAmount)) {
  	                        throw new AmazonException("AmazonException: Credit value must be positive!");
  	                    }
  	                    
  	                    float parsedCashAmount = Float.parseFloat(cashAmount);
  	                    
  	                  for (AmazonCredit existingCredit : customer.getCredits()) {
  	                    if (existingCredit.getType() == PaymentType.Cash) {
  	                        existingCredit.setAmount(existingCredit.getAmount() + parsedCashAmount);
  	                        System.out.println("Cash credit updated. New total: " + existingCredit.getAmount());
  	                        creditFound = true;
  	                        break;
  	                    }
  	                }
  	                 if(creditFound == false) {   
  	                    String[] cashData = {cashAmount};    
  	                    newCredit = AmazonCash.createCash(cashData);
  	                 }
  	                 
  	               break;

  	                case "2":
  	                    System.out.print("Enter Account Number: ");
  	                    String accountNumber = input.nextLine();
  	                    System.out.print("Enter Check value: ");
  	                    String checkAmount = input.nextLine();
  	                    
  	                    if (!AmazonUtil.isValidFloat(checkAmount)) {
  	                        throw new AmazonException("AmazonException: Credit value must be positive!");
  	                    }
  	                    
  	                  for (AmazonCredit existingCredit : customer.getCredits()) {
  	                    if (existingCredit.getType() == PaymentType.Check) {
  	                        existingCredit.setAmount(existingCredit.getAmount() + Float.parseFloat(checkAmount));
  	                        System.out.println("Check credit updated. New total: " + existingCredit.getAmount());
  	                        creditFound = true;
  	                        break;
  	                    }
  	                }
  	                    if(creditFound == false) {
  	                    String[] checkData = {accountNumber, checkAmount};  
  	                    newCredit = AmazonCheck.createCheck(checkData); 
  	                    }
  	                    
  	                  break;

  	                case "3":
  	                    System.out.print("Enter Card Number: ");
  	                    String cardNumber = input.nextLine();
  	                    System.out.print("Enter Expiration Date (MM/YYYY): ");
  	                    String expiration = input.nextLine();
  	                    System.out.print("Enter Card value: ");
  	                    String cardAmount = input.nextLine();  
  	                    
  	                    if (!AmazonUtil.isValidFloat(cardAmount)) {
  	                        throw new AmazonException("AmazonException: Credit value must be positive!");
  	                    }
  	                    
  	                  for (AmazonCredit existingCredit : customer.getCredits()) {
  	                    if (existingCredit.getType() == PaymentType.Card) {
  	                        existingCredit.setAmount(existingCredit.getAmount() + Float.parseFloat(cardAmount));
  	                        System.out.println("Card credit updated. New total: " + existingCredit.getAmount());
  	                        creditFound = true;
  	                        break;
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
  	            

  	            if (newCredit != null) {
  	                customer.addCredit(newCredit);
  	                System.out.println("Result: Credit added with success!");
  	                break; 
  	            }
  	            
  	          break;
  	            
  	        } catch (AmazonException e) {
  	            System.out.println(e.getLocalizedMessage());
  	           
  	        }
  	    }
  	}
  	
  	/**
     * AmazonManager method used to show the customers different credits
     */
  	public void showCreditFromCustomer() {
  		String id = "";
  	    int size = customers.size();
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
     * AmazonManager method used to add products to the wishlist
     */
  	public void addProductInWishList() {
  	    String customerId = "";
  	    String productId = "";
  	    int customerSize = customers.size();
  	    int productSize = products.size();
  	    AmazonCustomer customer = null;
  	    AmazonProduct product = null;

  	    while (true) {
  	        try {
  	        	
  	        	if(products.isEmpty() && customers.isEmpty()) {
  	        		throw new AmazonException("AmazonException: You must add both users and products before adding a product to your wishlist.");
  	        	}else if(products.isEmpty()) {
  	        		throw new AmazonException("AmazonException: There are no products to be added; please load products and try again.");
  	        	}else if(customers.isEmpty()) {
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
	        	}else if(products.isEmpty()) {
	        		return;
	        	}else if(customers.isEmpty()) {
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
     * AmazonManager method used to remove products from the wishlist
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
  	        	}else if(products.isEmpty()) {
  	        		throw new AmazonException("AmazonException: There are no products to be removed; please load products and try again.");
  	        	}else if(customers.isEmpty()) {
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
	        	}else if(products.isEmpty()) {
	        		return;
	        	}else if(customers.isEmpty()) {
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
     * AmazonManager method used to printing contents of a customers wishlist
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
  	        	}else if(products.isEmpty()) {
  	        		throw new AmazonException("AmazonException: There are no products to be added; please load products and try again.");
  	        	}else if(customers.isEmpty()) {
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
	        	}else if(products.isEmpty()) {
	        		return;
	        	}else if(customers.isEmpty()) {
	        		return;
	        	}
  	        }
  	    }

  	    // Get the Product
  	    while (true) {
  	        try {
  	            System.out.print("Enter the Product ID to buy from your cart: ");
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
  	
  	public void removeProductFromCart() {
  		String customerId = "";
  	    AmazonCustomer customer = null;
  	    String AmazonCartItemId = "";
  	    AmazonCartItem currentItem = null;
   	    
  	    
  	    while (true) {
  	        try {
  	        	
  	        	if(customers.isEmpty()) {
  	        		throw new AmazonException("AmazonException: You must add users before showing a the products in your cart.");
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
  	    
  	    
  	  // Get the Product
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

  	            break;  // Exit loop after finding the product

  	        } catch (AmazonException e) {
  	            System.out.println(e.getLocalizedMessage());
  	        }
  	    }
  	    
  	    
  	}


  	public void showProductsInCart() {
  	    String customerId = "";
  	    AmazonCustomer customer = null;
  	    
  	    
  	    while (true) {
  	        try {
  	        	
  	        	if(customers.isEmpty()) {
  	        		throw new AmazonException("AmazonException: You must add users before showing a the products in your cart.");
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
  	
  
  	
  	public void payCart() {
  		String customerId = "";
  	    AmazonCustomer customer = null;
  	    String typeOfCredit = "";
  	    boolean validPaymentType = false;
  	    
  	    
  	    while (true) {
  	        try {
  	        	
  	        	if(products.isEmpty() && customers.isEmpty()) {
  	        		throw new AmazonException("AmazonException: You must add both users and products before purchasing items from your cart.");
  	        	}else if(products.isEmpty()) {
  	        		throw new AmazonException("AmazonException: There are no items to be purchased; please load products and try again.");
  	        	}else if(customers.isEmpty()) {
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
  	          
  	          	if(customer.getCredits().isEmpty()) {
	        		throw new AmazonException("AmazonException: You must add credit before attempting to purchase items from your cart.");
	        	}

  	            break;

  	        } catch (AmazonException e) {
  	            System.out.println(e.getLocalizedMessage());
  	            
  	          if(products.isEmpty() && customers.isEmpty()) {
	        		return;
	        	}else if(products.isEmpty()) {
	        		return;
	        	}else if(customers.isEmpty()) {
	        		return;
	        	}else if(customer.getCredits().isEmpty()) {
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

  	        switch (typeOfCredit) {
  	            case "1":
  	                for (int i = 0; i < creditSize; i++) {
  	                    PaymentType creditType = customer.getCredits().get(i).getType();
  	                    boolean isCash = creditType == PaymentType.Cash;
  	                    if (isCash) {
  	                        AmazonCredit credit = customer.getCredits().get(i);
  	                        System.out.println("Payment type is cash");
  	                      if(customer.pay(credit) == false) {
	                        	throw new AmazonException("AmazonException: You dont have enough credits of cash, try a different payment method.");
	                        }
  	                        System.out.println("Customer credit updated: " + credit);
  	                        System.out.println("Cart empty - you can comment products now.");
  	                        validPaymentType = true;
  	                        break;
  	                    }else {
  	                    	throw new AmazonException("AmazonException: No credit found; add cash to customer and try again.");
  	                    }
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
  	                        	throw new AmazonException("AmazonException: You dont have enough credits on your check, try a different payment method.");
  	                        }
  	                        System.out.println("Customer credit updated: " + credit);
  	                        System.out.println("Cart empty - you can comment products now.");
  	                        validPaymentType = true;
  	                        break;
  	                    }else {
  	                    	throw new AmazonException("AmazonException: No credit found; add check to customer and try again.");
  	                    }
  	                }
  	            	return;

  	            case "3":
  	            	for (int i = 0; i < creditSize; i++) {
  	                    PaymentType creditType = customer.getCredits().get(i).getType();
  	                    boolean isCard = creditType == PaymentType.Card;
  	                    if (isCard) {
  	                        AmazonCredit credit = customer.getCredits().get(i);
  	                        System.out.println("Payment type is card");
  	                        
  	                      if(customer.pay(credit) == false ) {
	                        	throw new AmazonException("AmazonException: You dont have enough credits on your card, try a different payment method.");
	                        }
  	                        System.out.println("Customer credit updated: " + credit);
  	                        System.out.println("Cart empty - you can comment products now.");
  	                        validPaymentType = true;
  	                        break;
  	                    } else {
  	                    	throw new AmazonException("AmazonException: No credit found; add card to customer and try again.");
  	                    }
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
  	        	}else if(products.isEmpty()) {
  	        		throw new AmazonException("AmazonException: There are no items to comment; please load products and try again.");
  	        	}else if(customers.isEmpty()) {
  	        		throw new AmazonException("AmazonException: You must add a user before attempting to comment.");
  	        	} 
   	            
   	            System.out.print("Enter the Customer ID: ");
   	            customerId = input.nextLine();

   	            if (!AmazonUtil.isValidInt(customerId)) {
   	                throw new AmazonException("AmazonException: ID must be positive and an Integer!");
   	            }

   	            int parsedCustomerId = Integer.parseInt(customerId);

   	           
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
	        	}else if(products.isEmpty()) {
	        		return;
	        	}else if(customers.isEmpty()) {
	        		return;
	        	}else if(customer.getComments().isEmpty()) {
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
   	    
   	    System.out.println("Commenting product: [" + product.getName()   + "] ..."); //display chosen product
   	    
   	    System.out.print("Enter the comment: "); // prompt for comment
   	    comment = input.nextLine(); // assign entered comment
   	    
   	    System.out.print("Enter the stars: "); // prompt rating
   	    rating = input.nextLine(); // assign entered rating
   	    
   	    float ratingFloat = Float.parseFloat(rating);
   	    
   	    customer.setComment(product, comment, ratingFloat);
   	    
   	    System.out.println("Comment from customer " + customer );
   	    
  		
  	}
  	
  	public void showComments() {
  		 String customerId = "";
    	    String productId = "";
    	    AmazonCustomer customer = null;
    	   
    	    
    	    while (true) {
    	        try {
    	        	
    	        	if(products.isEmpty() && customers.isEmpty()) {
      	        		throw new AmazonException("AmazonException: You must add both users and products before displaying comments.");
      	        	}else if(products.isEmpty()) {
      	        		throw new AmazonException("AmazonException: There are no items to comment; please load products and try again.");
      	        	}else if(customers.isEmpty()) {
      	        		throw new AmazonException("AmazonException: You must add a user before attempting to display comments.");
      	        	} 
    	        	
    	            
    	            System.out.print("Enter the Customer ID: ");
    	            customerId = input.nextLine();

    	            if (!AmazonUtil.isValidInt(customerId)) {
    	                throw new AmazonException("AmazonException: ID must be positive and an Integer!");
    	            }

    	            int parsedCustomerId = Integer.parseInt(customerId);

    	           
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
      	        	}else if(products.isEmpty()) {
      	        		return;
      	        	}else if(customers.isEmpty()) {
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
  
  	





