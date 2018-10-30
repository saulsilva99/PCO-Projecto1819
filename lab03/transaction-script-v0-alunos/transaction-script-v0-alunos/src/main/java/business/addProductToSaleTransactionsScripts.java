package business;
import dataaccess.SaleProductRowDataGateway;
public class addProductToSaleTransactionsScripts {
	
	public void addProductToSale(int saleId, int productCode, double qty) {
		SaleProductRowDataGateway saleProd = new SaleProductRowDataGateway(saleId,productCode,qty);
		
	}

}
