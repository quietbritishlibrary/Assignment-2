package amazonsystem;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class AmazonProductList {
	
	// Constant for the number of columns in the CSV file
    private final int NUMCOLS = 10;

    // List to store the default title of the CSV file (e.g., headers)
    private ArrayList<String> DEFAULT_TITLE = new ArrayList<>();

    // List to store the best-selling Amazon products
    private ArrayList<AmazonProduct> products = new ArrayList<AmazonProduct>();

    /**
     * Creates a list of products by reading data from a CSV file.
     * @param csvFile The path to the CSV file
     * @throws AmazonProductException If there is an issue reading the file
     */
    public List<AmazonProduct> createList(String csvFile) throws AmazonException { 
        
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
        
        return products;
    }
    
    /**
     * Prints the list of products along with the default title (headers).
     * If the list is empty, a message is displayed.
     */
    
    public void printList() {
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
    
    /**
     * Adds a new product to the list of bestsellers.
     * @param p The product to be added
     * @throws AmazonProductException If the product data is invalid or empty
     */
    public void add(AmazonProduct p) throws AmazonException {
        try {    
            // Add the product to the list of bestsellers
            products.add(p);
        } catch (NullPointerException e) {
            // Handle case where the product is null
            throw new AmazonException("The data is empty!");
        } catch(NumberFormatException e) {
            // Handle case where there is a format error in the data
            throw new AmazonException("The number isn't in the right format!");
        }
    }
    
    /**
     * Finds a product by its index in the list.
     * @param index The index of the product
     * @return The AmazonProduct object at the specified index
     */
    public AmazonProduct findProductByIndex(int index){
        return products.get(index);
    }
   
    
    /**
     * Searches the list of products by a keyword or data string.
     * The search checks against various product properties (name, id, category, etc.).
     * @param data The search query string
     * @throws AmazonProductException If there is an error during the search
     */
    public void search(String data) throws AmazonException {
    	int productFound = 0;
    	
    	System.out.println("The products associated are: ");
    	
    	for(int i = 0; i < products.size();i++) {
    		
    		AmazonProduct product = products.get(i);
    		
    		if(product.getName().contains(data)) {
    			productFound++;   			
    			System.out.println(product);
    		
    			continue;
    		}if (String.valueOf(product.getId()).contains(data)){ 
    			productFound++;    			
    			System.out.println(product);
    			
    			continue;
    		}if(product.getLink().contains(data)) { 
    			productFound++;   		
    			System.out.println(product);
    		
    			continue;
    		}if(String.valueOf(product.getMain_category()).contains(data)) {
    			productFound++;    			
    			System.out.println(product);
    		
    			continue;
    		}if(String.valueOf(product.getSub_category()).contains(data)) {
    			productFound++;   		
    			System.out.println(product);
    		
    			continue;
    		}if(product.getUrl_Image().contains(data)) {
    			productFound++;    			
    			System.out.println(product);
    			
    			continue;
    		}if(String.valueOf(product.getRatings()).contains(data)) {
    			productFound++;    			
    			System.out.println( product);
    			
    			continue;
    		}if(String.valueOf(product.getNo_of_ratings()).contains(data)) {
    			productFound++;
    			System.out.println(product);
    			
    			continue;
    		}if(String.valueOf(product.getDiscount_Price()).contains(data)) {
    			productFound++;    		
    			System.out.println(product);
    			
    			continue;
    		}if(String.valueOf(product.getActual_Price()).contains(data)) {
    			productFound++;  		
    			System.out.println(product);
    			
    			continue;
    		}

    	}
    	
    	if(productFound == 0) {
    		
			System.out.println("search empty!");
			
		}
    	
    	
    }
    
    /**
     * Returns the number of products in the list.
     * @return The size of the product list
     */
    public int getSize() {
        return products.size();
    }

}
