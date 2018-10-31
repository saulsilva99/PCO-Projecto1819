package facade.startup;

import application.CustomerService;
import application.SaleService;
import business.CustomerModule;
import business.SaleModule;
import dataaccess.Persistence;
import dataaccess.PersistenceException;
import facade.exceptions.ApplicationException;

public class SaleSys {

    private CustomerService customerService;
    private SaleService saleService;
    private Persistence persistence;

    public static final String DB_CONNECTION_STRING = "jdbc:derby:data/derby/cssdb";
    
    public void run() throws ApplicationException {
        // Connects to the database
        try {
        	persistence = new dataaccess.Persistence(DB_CONNECTION_STRING + ";create=false", "SaleSys", "");
            customerService = new CustomerService(new CustomerModule(persistence));
            saleService = new SaleService(new SaleModule(persistence));
        } catch (PersistenceException e) {
            throw new ApplicationException("Error connecting database", e);
        }
    }

    public void stopRun() {
        // Closes the database connection
        persistence.close();
    }

    public CustomerService getCustomerService() {
        return customerService;
    }

    public SaleService getSaleService() {
        return saleService;
    }
}
 