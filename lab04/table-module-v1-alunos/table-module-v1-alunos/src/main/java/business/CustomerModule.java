package business;

import java.util.logging.Level;
import java.util.logging.Logger;

import dataaccess.CustomerTableDataGateway;
import dataaccess.Persistence;
import dataaccess.PersistenceException;
import dataaccess.TableData;
import dataaccess.TableData.Row;
import datatypes.DiscountType;
import facade.exceptions.ApplicationException;

/**
 * The table module of a customer. I choose to implement it as a regular instance,
 * but it could be implemented as a singleton, instead. The singleton implementation
 * could be simpler but may complicate things further down has things get more
 * complex.Please check Martin Fowler's enterprise pattern book for details on 
 * other plausible implementations. 
 * 
 * Remarks:
 * 1. Since Java does not have a powerful database abstraction as .NET C# has (DataSet)
 * I'm going to use the persistence repository to act with the same role as DataSet. 
 * 
 * 2. Notice the use of the Layer Supertype pattern 
 * (see http://martinfowler.com/eaaCatalog/layerSupertype.html). All module classes
 * extend the TableModule class and pass it the persistence repository. Later we will
 * use extensively a style of programming called dependency injection. 
 * (see, for instance, http://en.wikipedia.org/wiki/Dependency_injection). This
 * is the role of the persistence parameter in the constructor.
 * 
 * @author fmartins
 * @version 1.2 (2/10/2015)
 *
 */
public class CustomerModule extends TableModule {
	
	private CustomerTableDataGateway table;

	/**
	 * an exception logger
	 */
	private static Logger log = Logger.getLogger(CustomerModule.class.getCanonicalName());

	/**
	 * Constructs a customer module given the persistence repository
	 * 
	 * @param persistence The persistence repository
	 */
	public CustomerModule (Persistence persistence) {
		super(persistence);
		table = persistence.customerTableGateway;
	}

	/**
	 * Creates a new customer given its VAT number, its designation, phone contact, and discount type.
	 * 
	 * @param vat The customer's VAT number
	 * @param designation The customer's designation
	 * @param phoneNumber The customer's phone number
     * @param discountCode The type of discount applicable for the customer
   	 * @throws ApplicationException When VAT number is invalid, or the customer exists, or there is
	 * an internal referential integrity error.
	 */
	public void addCustomer (int vat, String designation, int phoneNumber, 
			int discountCode) throws ApplicationException {
		// Checks if it is a valid VAT number
		if (!isValidVAT (vat))
			throw new ApplicationException ("Invalid VAT number: " + vat);

		// Checks if the discount code is valid. 
		if (discountCode <= 0 || discountCode > DiscountType.values().length)
			throw new ApplicationException ("Invalid Discount Code: " + discountCode);

		// Checks that there is no other customer with the same VAT number 
		if (existsCustomer(vat))
			throw new ApplicationException ("Customer with VAT number " + vat + " already exists!");

		try {
			// If the customer does not exists, add it to the database 
			table.insert(vat, designation, phoneNumber, DiscountType.values()[discountCode-1]);
		} catch (PersistenceException e) {
			throw new ApplicationException ("Internal error adding a custumer", e);
		}
	}

	/**
	 * @param vat The VAT number of the customer to check if it exists
	 * @return If a customer exists
	 */
	private boolean existsCustomer(int vat) {
		try {
			return !table.findByVATNumber(vat).isEmpty();
		} catch (PersistenceException e) {
			log.log(Level.SEVERE, "error checking the existence of a customer", e);
			return false;
		} 
	}
	
	/**
	 * @param vat The VAT number of the customer to get its internal id number
	 * @return The customerId of the customer with the vat number 
	 * @throws ApplicationException When the customer is not found or when some 
	 * obscure database internal error occurs (not in the this version of the 
	 * example) 
	 */
	public int getCustomerId (int vat) throws ApplicationException {
		try {
			TableData td = table.findByVATNumber(vat);
			if (!td.isEmpty()) {
				Row firstRow = td.iterator().next();
				return table.readID(firstRow);
			} else 
				throw new ApplicationException("Customer with VAT number " + vat + " does not exist.");
		} catch (PersistenceException e) {
			throw new ApplicationException ("Internal error obtaining customer with VAT number " + vat, e);
		} 
	}

	
	/**
	 * @param customerId The customerId to get the discount type of
	 * @return The DiscountType associated with the customer with customerId id
	 * @throws ApplicationException When the customer with customerId is not found or when there is 
	 * some obscure internal error (not on this example version; wait until a real database comes
	 * into play).
	 */
	public DiscountType getDiscountType (int customerId) throws ApplicationException {
		try {
			TableData td = table.find(customerId);
			if (!td.isEmpty())
				return table.readDiscountType(td.iterator().next());
			else 
				throw new ApplicationException("Internal error obtaining customer with id " + customerId + ".");
		} catch (PersistenceException e) {
			throw new ApplicationException ("Internal error obtaining customer with id " + customerId + ".", e);
		} 
	}
	

	/**
	 * Checks if a VAT number is valid.
	 * 
	 * @param vat The number to be checked.
	 * @return Whether the VAT number is valid. 
	 */
	private boolean isValidVAT(int vat) {
		// If the number of digits is not 9, error!
		if (vat < 100000000 || vat > 999999999)
			return false;
		
		// If the first number is not 1, 2, 5, 6, 8, 9, error!
		int firstDigit = vat / 100000000;
		if (firstDigit > 2 && firstDigit < 8 &&
			firstDigit != 5 && firstDigit != 6)
			return false;
		
		return vat % 10 == mod11(vat);
	}


	/**
	 * @param num The number to compute the modulus 11.
	 * @return The modulus 11 of num.
	 */
	private int mod11(int num) {
		// Checks the congruence modules 11.
		int sum = 0;
		int quocient = num / 10;
		
		for (int i = 2; i < 10 && quocient != 0; i++) {
			sum += quocient % 10 * i;
			quocient /= 10;
		}
		
		int checkDigitCalc = 11 - sum % 11;
		return checkDigitCalc == 10 ? 0 : checkDigitCalc;
	}
}
