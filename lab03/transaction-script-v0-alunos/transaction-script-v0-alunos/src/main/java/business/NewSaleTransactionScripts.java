package business;

import facade.exceptions.ApplicationException;

import java.util.Optional;

import dataaccess.CustomerRowDataGateway;
import dataaccess.SaleRowDataGateway;

import java.util.Date;

public class NewSaleTransactionScripts {
	public Date date;

	public int newSale(int vat) throws ApplicationException {
			 
			Optional<CustomerRowDataGateway> customer = CustomerRowDataGateway.getCustomerByVATNumber(vat);
			
			int clientId = customer.get().getCustomerId();
			SaleRowDataGateway newSale = new SaleRowDataGateway(clientId, new Date());
			newSale.insert();
			return newSale.getId();
		}


}
