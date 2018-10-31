package application;

import business.SaleModule;
import facade.exceptions.ApplicationException;

/**
 * Handles sales' transactions. 
 * Requests are dispatched and handled by table modules,
 * following Martin Fowler's Table Module pattern. 
 * 
 * @author fmartins
 * @version 1.1 (5/10/2014)
 *	
 */
public class SaleService {	

	/**
	 * A sale module
	 */
	private SaleModule saleModule;

	/**
	 * Constructs a Sale service given the persistence repository
	 * 
	 * @param persistence The persistence repository
	 */
	public SaleService(SaleModule saleModule) {
		this.saleModule = saleModule;
	}

	
	/**
	 * Starts a new sale for a given customer identified by its VAT number
	 * 
	 * @param vat The VAT number of the customer the sale belongs to
	 * @return The id of the new sale. This id is useful for adding products 
	 * to the sale and for computing the sale's total, discount, etc.
	 * @throws ApplicationException When the customer with the given VAT number
	 * does not exist.
	 */
	public int newSale (int vat) throws ApplicationException {
		// TODO: program me!
		return 0;
	}

	
	/**
	 * Adds a product to an open sale.
	 * 
	 * @param saleId The sale's id.
	 * @param productCode The product's code.
	 * @param qty The amount to sell.
	 * @throws ApplicationException When the product with a given product code 
	 * does not exist, when the Sale is closed, or when there is not enough quantity
	 * (qty) of the product in stock. 
	 * 
	 * @pre: qty > 0
	 */
	public void addProductToSale (int saleId, int productCode, double qty) throws ApplicationException {
		// TODO: program me!
	}
	
	
	/**
	 * Computes the discount amount for a sale (based on the discount type of the customer).
	 * 
	 * @param saleId The sale's id
	 * @return The discount amount
	 * @throws ApplicationException When the sale id does not exist, or there is an internal
	 * integrity error, such as the discount id associated with the sale is not found, etc.
	 */
	public double getSaleDiscount (int saleId) throws ApplicationException {
		// TODO: program me!
		return 0;
	}
}
