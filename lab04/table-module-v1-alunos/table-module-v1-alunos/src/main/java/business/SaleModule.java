package business;

import dataaccess.Persistence;
import dataaccess.SaleTableDataGateway;
import facade.exceptions.ApplicationException;

public class SaleModule extends TableModule {

	private SaleTableDataGateway table;

	/**
	 * Constructs a sale module given the persistence repository
	 * 
	 * @param persistence The persistence repository
	 */
	public SaleModule (Persistence persistence) {
		super(persistence);
		table = persistence.saleTableGateway;
	}
	
	/**
	 * Add a new sale.
	 * Notice the interaction with the Customer module. We use the
	 * customer module to get the customer id of the customer with
	 * a given VAT number. 
	 * 
	 * @param vat The VAT number of the customer the sale belongs to
	 * @return The internal sale id so that products may be added to the sale
	 * @throws ApplicationException When there is no customer with the given
	 * VAT number or when there is an unexpected error add the sale.
	 */
	public int newSale (int vat) throws ApplicationException {
		// TODO: program me!
		return 0;
	}
	
	/**
	 * @param saleId The sale id to check if it is closed
	 * @return If the sale is closed
	 * @throws ApplicationException When the sale does not exist or some obscure
	 * error has occurred.
	 */
	public boolean isClosed(int saleId) throws ApplicationException {
		// TODO: program me!
		return false;
	}
	
	/**
	 * Add a product to a sale.
	 * Mind the usage of the Product module to get the product id from the product code
	 * and to further update its stock existence.
	 * 
	 * @param saleId The sale id to add a product to
	 * @param productCode The product to be added to the sale
	 * @param qty The quantity sold of the product
	 * @throws ApplicationException When the sale is closed, the product code does not
	 * exist or some internal error occurred while saving the data.
	 */
	public void addProductToSale(int saleId, int productCode, double qty) 
			throws ApplicationException {
		// TODO: program me!
	}
	
	/**
	 * @param saleId The sale to compute the discount.
	 * @return Compute the discount of the sale. 
	 * @throws ApplicationException When some referential integrity problem occurs. This
	 * might happen if a foreign key is not found, for instance.
	 */
	public double getSaleDiscount (int saleId) throws ApplicationException {
		// TODO: program me!
		return 0;
	}

	
	/**
	 * @param saleId The sale to get the customer id from
	 * @return The customer id of the sale
	 * @throws ApplicationException In case an error occurs when retrieving the
	 * information from the database.
	 */
	public int getCustomerId (int saleId) throws ApplicationException {
		// TODO: program me!
		return 0;
	}
}
