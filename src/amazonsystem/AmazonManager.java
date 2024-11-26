package amazonsystem;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import amazonproducts.AmazonProduct;
import amazonproducts.AmazonProductException;
import amazonproducts.AmazonProductManager;


public class AmazonManager {
	
	Scanner input = new Scanner(System.in);
    private final int NUMCOLS = 10; // constant for number of columns in the CSV file
    
    // lists
    private List <AmazonProduct> products = new ArrayList<AmazonProduct>();
    private ArrayList<String> DEFAULT_TITLE = new ArrayList<>(); // list for storing the contents of the first column of the file
	private List<AmazonCustomer> customers = new ArrayList<AmazonCustomer>();
	
	public static void main(String[] args) throws AmazonException {
		AmazonManager manage = new AmazonManager();
		manage.loadProductList();
		manage.showProductList();
		

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
	
	
	/**
     * Creates a list of products by reading data from a CSV file.
     * @param csvFile The path to the CSV file
     * @throws AmazonProductException If there is an issue reading the file
     */
    public void createList(String csvFile) throws AmazonException { 
        
        try(BufferedReader reader = new BufferedReader(new FileReader(csvFile));) {
            // Read the first line to get the column headers and store it as the default title
            String title = reader.readLine();
            DEFAULT_TITLE.add(title);
            
            // Read each line from the CSV file and process the data into products
            String line = reader.readLine();
            String[] data = new String[NUMCOLS];
           
            while (line != null) {
                // Split each line into individual product data
                data = AmazonUtil.lineReader(line, 0);    
                // Create a new AmazonProduct object and add it to the bestsellers list
                AmazonProduct product = new AmazonProduct(data);                
                products.add(product);
                line = reader.readLine(); // Read the next line
            }
            
        } catch(FileNotFoundException e) {
            // Handle case where the file is not found
            throw new AmazonException("Error opening the file. " + e.getMessage());
        } catch (IOException e) {
            // Handle other IO exceptions
            throw new AmazonException("File not found! " +  e.getMessage());
        }  
    }
	
	
	
	
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
				createList(file);
				
				System.out.println("product loaded!");
				
			} catch (AmazonException e) {
				
				System.out.println("AmazonProductException: " + e.getLocalizedMessage());
				
			}
		}
	}
	
	
	public void showProductList() {
		System.out.println("PRODUCTLIST .........");
		
		
	        if(products.isEmpty()) {
	            // Inform the user if the list is empty
	            System.out.println("List is empty! Load a product list to view.");
	        } else {
	            // Print the default title (headers)
	            System.out.println(DEFAULT_TITLE);
	            // Print each product's details
	            for(AmazonProduct product : products) {
	                System.out.println(product);
	            }
	        }
	    
	}
}
