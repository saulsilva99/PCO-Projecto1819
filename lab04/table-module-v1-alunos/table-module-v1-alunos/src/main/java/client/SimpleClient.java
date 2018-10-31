package client;

import application.CustomerService;
import application.SaleService;
import facade.exceptions.ApplicationException;
import facade.startup.SaleSys;

/**
 * A simple application client that uses both services.
 *
 * @author fmartins
 * @version 1.2 (11/02/2015)
 */
public class SimpleClient {

	/**
	 * An utility class should not have public constructors
	 */
	private SimpleClient() {
	}

    /**
     * A simple interaction with the application services
     *
     * @param args Command line parameters
     */
	public static void main(String[] args) {
		
        SaleSys app = new SaleSys();
		try {
            app.run();
            
            // Access both available services
            CustomerService cs = app.getCustomerService();
            SaleService ss = app.getSaleService();

            // the interaction
            // adds a customer.
            cs.addCustomer(168027852, "Customer 1", 217500255, 2);

            // creates a new sale
            int sale = ss.newSale(168027852);

            // adds two products to the database
            ss.addProductToSale(sale, 123, 10);
            ss.addProductToSale(sale, 124, 5);
            	
            // gets the discount amounts
            double discount = ss.getSaleDiscount(sale);
            System.out.println(discount);

            app.stopRun();
		} catch (ApplicationException e) {
			System.out.println("Error: " + e.getMessage());
			// for debugging purposes only. Typically, in the application
			// this information can be associated with a "details" button when
			// the error message is displayed.
			if (e.getCause() != null) 
				System.out.println("Cause: ");
			e.printStackTrace();
		}
	}
}