package business;

import dataaccess.ConfigurationTableDataGateway;
import dataaccess.Persistence;
import dataaccess.PersistenceException;
import dataaccess.TableData;
import dataaccess.TableData.Row;
import facade.exceptions.ApplicationException;

/**
 * A table module for the application settings.
 * See remarks in the Customer class.
 * 
 * @author fmartins
 * @version 1.1 (5/10/2014)
 *
 */
class ApplicationSettingsModule extends TableModule {
		
	private ConfigurationTableDataGateway table;

	/**
	 * Constructs a ApplicationSettings module given the persistence repository
	 * 
	 * @param persistence The persistence repository
	 */
	ApplicationSettingsModule(Persistence persistence) {
		super(persistence);
		table = persistence.configurationTableGateway;
	}

	/**
	 * @return The sale's amount threshold for being eligible for a
	 * total sale discount.
	 * @throws ApplicationException When there is no application settings data.
	 */
	double getAmountThreshold() throws ApplicationException {
        try {
            return table.readAmountThreshold(getDoubleFieldFromAppSettings());
        } catch (PersistenceException e) {
            throw new ApplicationException("Error reading the amount threshold", e);
        }
    }
	
	/**
	 * @return The discount percentage to apply for the total sale
	 * @throws ApplicationException When there is no application settings data.
	 */
	double getAmountThresholdPercentage() throws ApplicationException {
        try {
            return table.readTotalAmountPercentage(getDoubleFieldFromAppSettings());
        } catch (PersistenceException e) {
            throw new ApplicationException("Error reading the amount threshold percentage", e);
        }
    }

	/**
	 * @return The discount percentage to apply to products marked as eligible.
	 * @throws ApplicationException When there is no application settings data.
	 */
	double getEligiblePercentage() throws ApplicationException {
        try {
            return table.readEligiblePercentage(getDoubleFieldFromAppSettings());
        } catch (PersistenceException e) {
            throw new ApplicationException("Error reading the eligible percentage", e);
        }
    }
	
	/**
	 * @return The row with the application settings
	 * @throws ApplicationException When there is no application settings data.
	 */
	private Row getDoubleFieldFromAppSettings () throws ApplicationException {
		try {
			TableData td = table.loadConfiguration();
			if (!td.isEmpty()) 
				return td.iterator().next();
			else 
				throw new ApplicationException("Internal error accessing the application settings");
		} catch (PersistenceException e) {
			throw new ApplicationException("Internal error accessing the application settings", e);
		}
	}
}
