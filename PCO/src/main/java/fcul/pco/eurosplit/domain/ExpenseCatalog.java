package fcul.pco.eurosplit.domain;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/*
 * Objectivo da Class é armazenar Instancias
 * de despesas.
 * @author: Saul Silva e Joao Paiva
 */
public class ExpenseCatalog {
	private Map<Integer, Expense> StorageExpenses;
	
	/*
	 * Inicializa um constructor com a Instancia StorageExpenses vazia.
	 * Integer  == id da despesa
	 * Expense == instância da despesa
	 */
	public ExpenseCatalog() {
		StorageExpenses = new HashMap<Integer, Expense>();
	}
	
	
	/*
	 * Adiciona um usuario para a instancia StorageUsers.
	 * 
	 * @param u. Eh uma instancia de User. Requires: Instancia de User deste genero
	 * User(nome,email)
	 */
	public void addExpense(Expense e) {
		//StorageExpenses.put(u.getEmail(), u);  < -- terminar 
	}
	
	
	
	/*
	 * Chama a camada de Persistência que também tem o ExpenseCatalog e o método load
	 */
	public void load() throws IOException {
		StorageExpenses = fcul.pco.eurosplit.persistence.ExpenseCatalog.load();
	}
	
	
	/*
	 * chama a camada de Persistência que também tem o ExpenseCatalog e o metodo save()
	 */
	public void save() throws IOException {
		fcul.pco.eurosplit.persistence.ExpenseCatalog.save(StorageExpenses);
	}
	
	
}
