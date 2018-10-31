package application;

import business.CustomerModule;
import facade.exceptions.ApplicationException;

/**
 * Handles customer transactions. 
 * Requests are dispatched and handled by table modules.
 * Read Martin Fowler's Table Module pattern. 
 * 
 * @author fmartins
 * @version 1.1 (5/10/2014)
 */
public class CustomerService {
	
	/**
	 * A customer module
	 */
	private CustomerModule customerModule;

	/**
	 * Constructs a customer service given the persistence repository
	 * 
	 * @param persistence The persistence repository
	 */
	public CustomerService(CustomerModule customerModule) {
		this.customerModule = customerModule;
	}
	
	
	/**
	 * Adds a new customer.
	 * 
	 * @param vat The VAT of the customer
	 * @param designation The customer's name
	 * @param phoneNumber The customer's phone 
	 * @param discountCode The customer's discount code
	 * @throws ApplicationException When the VAT number is invalid (we check according 
	 * to the Portuguese legislation), when it is already in the database, 
	 * or when is some unexpected persistence error.
	 */
	public void addCustomer (int vat, String designation, int phoneNumber, 
			int discountCode) throws ApplicationException {
		customerModule.addCustomer(vat, designation, phoneNumber, discountCode);
	}
}