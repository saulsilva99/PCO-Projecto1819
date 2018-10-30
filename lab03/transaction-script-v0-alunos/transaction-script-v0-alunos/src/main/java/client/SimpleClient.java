package client;

import application.CustomerService;
import application.SaleService;
import facade.exceptions.ApplicationException;

/**
 * A simple application client that uses both services.
 *	
 * @author fmartins
 * @version 1.2 (11/02/2015)
 * 
 */
public final class SimpleClient {

	private SimpleClient() {
		// an utility class
	}
	
	/**
	 * A simple interaction with the application services
	 * 
	 * @param args Command line parameters
	 */
	public static void main(String[] args) {

		// Access both available services
		CustomerService cs = CustomerService.INSTANCE;
		SaleService ss = SaleService.INSTANCE;		
		
		// the interaction
		try {
			// adds a customer.
			cs.addCustomer(168027852, "Customer 1", 217500255, 2);

			// creates a new sale
			int sale = ss.newSale(168027852);

			// adds two products to the sale
			ss.addProductToSale(sale, 123, 10);
			ss.addProductToSale(sale, 124, 5);
			
			// gets the discount amounts
			double discount = ss.getSaleDiscount(sale);
			System.out.println(discount);
		} catch (ApplicationException e) {
			System.err.println("Error: " + e.getMessage());
			// for debugging purposes only. Typically, in the application
			// this information can be associated with a "details" button when
			// the error message is displayed.
			if (e.getCause() != null) 
				System.err.println("Cause: ");
			e.printStackTrace();
		}
	}
}
