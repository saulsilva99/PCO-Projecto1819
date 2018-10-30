package application;

import facade.exceptions.ApplicationException;
import business.NewSaleTransactionScripts;

/**
 * A sale service. The purpose of this class is to provide access to the
 * business layer, hiding its implementation from the clients. Clients should
 * never interact directly with the business layer, so this very simple
 * singleton offers a method to add a new sale, add a product to the sale, and
 * to obtain the sale discount. As you will see in future, different
 * implementation of the business and data access layer will not change this
 * interface and, consequently, the way clients interact with the application.
 * 
 * @author fmartins
 * @version 1.1 (16/2/2017)
 */

public enum SaleService {
	INSTANCE;

	/**
	 * Add a new sale to a customer with a giving VAT number.
	 * 
	 * @param vat The VAT number of the customer purchasing goods.
	 * @return The sale identification. The id must be used when adding products to
	 *         the sale.
	 * @throws ApplicationException In case the sale could not be created.
	 */
	NewSaleTransactionScripts saleTS;

	public int newSale(int vat) throws ApplicationException {
		saleTS = new NewSaleTransactionScripts();

		return saleTS.newSale(vat);
	}

	/**
	 * Add a product to a given sale.
	 * 
	 * @param saleId      The id of the sale to add the product to.
	 * @param productCode The code of the product to be added to the sale.
	 * @param qty         The quantity being purchased.
	 * @throws ApplicationException In case the product could not be added to the
	 *                              sale. See lower level exception for detailed
	 *                              information.
	 */
	public void addProductToSale(int saleId, int productCode, int qty) throws ApplicationException {
		saleTS.addProductToSale(saleId, productCode, qty);
	}

	/**
	 * @param saleId The id of the sale the obtain the discount total
	 * @return The total discount for a sale with id saledId.
	 * @throws ApplicationException In case the sale does not exists.
	 */
	public double getSaleDiscount(int saleId) throws ApplicationException {
		// TODO: complete!
		return 0;
	}
}
