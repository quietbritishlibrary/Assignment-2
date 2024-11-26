package amazonsystem;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
		manage.loadProductList();
		manage.showProductList();
		manage.searchInProducts();

	}

	
	public void manageProductList() throws AmazonException {

		AmazonManager use = new AmazonManager();
		int option = -1;
		// Main loop for interacting with the user until they choose to exit
		while (option != 0) {
			option = -1;
			use.showMenu();

			try {

				option = Integer.parseInt(input.nextLine().replace(" ", ""));

			} catch (Exception e) {
				
				// If the user enters a non-integer, loop once again
			}

			// Handle the user selection based on menu choice
			switch (option) {
			
			case 0:
				use.exit();
				break;
			case 1:
				use.createProduct();
				break;
			case 2:
				use.showProductList();
				break;
			case 3:
				use.loadProductList();
				break;
			case 4:
				use.editProduct();
				break;
			case 5:
				use.deleteProduct();
				break;
			case 6:
				use.saveProductList();
				break;
			case 7:
				use.search();
				break;
			default:
				
				System.out.println("AmazonProductException: Invalid input - type a number between 0 and 7");
				
			}

		}
	}
	

	
	
	/**
     * AmazonManager method used to display the menu
     */
	public void showMenu() {
		System.out.println("===========================================================================\n" +
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
                "===========================================================================\n");
	}
	
	public void exit() {
		System.out.println("================================ \n" + "||    [Application ended]     || \n"
				+ "================================");
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
				productList.createList(file);
				
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
     * AmazonManager method used for adding a customer NOTE: TEST FUNCTION FOR AVOIDING DUPLICATES
     */
  	public void addCustomer() throws AmazonException {
  		    String id = "";
  		    String name = "";
  		    String address = "";
  		    String [] data = {id,name,address};
  		    int size = customers.size();
  		    

  		    while (true) {
  		        try {
  		            System.out.println("Enter the Customer ID: ");
  		            id = input.nextLine();

  		            if (!AmazonUtil.isValidInt(id)) {
  		                throw new AmazonException("AmazonException: ID must be positive and an Integer!");
  		            }
  		            
  		            int parsedInt = Integer.parseInt(id);
  		            
  		            for(int i = 0; i < size; i++) {
  		            	int customerId = customers.get(i).getId();
  		            	if(parsedInt == customerId) {
  		            		throw new AmazonException("AmazonException: ID can't be a duplicate!");
  		            	}
  		            }
  		            

  		            break;

  		        } catch (AmazonException e) {
  		            System.out.println(e.getLocalizedMessage());
  		        }
  		    }

  		    while (true) {
  		        try {
  		            System.out.println("Enter the Customer Name: ");
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
  		            System.out.println("Enter the Customer Address: ");
  		            address = input.nextLine();

  		            if (!AmazonUtil.isValidString(address)) {
  		                throw new AmazonException("AmazonException: Customer address cannot be empty!");
  		            }

  		            break; 

  		        } catch (AmazonException e) {
  		            System.out.println(e.getLocalizedMessage());
  		        }
  		    }
  		    
  		    customers.add(AmazonCustomer.createAmazonCustomer(data));

  	}
  	
  	public void showCustomers() {
  		System.out.println("[Printing customers ...]");
		for(AmazonCustomer c : customers) {
			System.out.println(c);
		}
  	}
  	
  	
}
