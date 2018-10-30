package business;

import dataaccess.CustomerRowDataGateway;
import datatypes.DiscountType;
import facade.exceptions.ApplicationException;

/**
 * Handles customer transactions. 
 * Each public method implements a transaction script.
 * 
 * @author fmartins
 * @version 1.3 (6/03/2016)
 * 
 */
public class CustomerTransactionScripts {
	
	/**
	 * Adds a new customer. It checks that there is no other customer in the system
	 * with the same VAT number.
	 * 
	 * @param vat The VAT number of the customer
	 * @param denomination The customer's designation
	 * @param phoneNumber The customer's phone  
	 * @param discountType The type of discount applicable to the customer
	 * @throws ApplicationException When the VAT number is invalid (we check it according 
	 * to the portuguese legislation).
	 */
	public void addCustomer (int vat, String denomination, int phoneNumber, 
			int discountCode) throws ApplicationException {
		// Checks if it is a valid VAT number
		if (!isValidVAT (vat))
			throw new ApplicationException ("Invalid VAT number: " + vat);
		
		// Checks if the discount code is valid. 
		if (discountCode <= 0 || discountCode > DiscountType.values().length)
			throw new ApplicationException ("Invalid Discount Code: " + discountCode);
		
		// Checks that denomination and phoneNumber and filled in
	    if (!isFilled (denomination) || phoneNumber == 0)
	      throw new ApplicationException(
	             "Both denomination and phoneNumber must be filled");
		
		// Checks that there is no other customer with the same VAT number. 
		// Notice that this approach needs extra control in order to be used 
		// in a concurrent scenario. For now we keep it simple!
		if (!CustomerRowDataGateway.getCustomerByVATNumber(vat).isPresent()) {
		    // add the customer to the system 
			CustomerRowDataGateway newCustomer = new CustomerRowDataGateway (vat, denomination, 
					phoneNumber, DiscountType.values()[discountCode-1]);
			newCustomer.insert();
		}
	}

	
	/**
	 * Checks if a VAT number is valid.
	 * 
	 * @param vat The number to be checked.
	 * @return Whether the VAT number is valid. 
	 */
	boolean isValidVAT(int vat) {
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
	
	/**
	 * Checks is a string is filled
	 * 
	 * @param value The String to check
	 * @return true if the string is not empty (and not null!)
	 */
	private boolean isFilled(String value) {
		return value != null && !value.isEmpty();
	}

}
