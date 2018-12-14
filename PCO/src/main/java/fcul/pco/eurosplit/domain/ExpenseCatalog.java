package fcul.pco.eurosplit.domain;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Objectivo da Class é armazenar Instancias
 * de despesas.
 * @author: Saul Silva e Joao Paiva
 */
public class ExpenseCatalog {
	private Map<Integer, Expense> StorageExpenses;
	private Map<Integer, List<Expense>> splitsDic;
	private int id;
	
	
	/**
	 * Inicializa um constructor com a Instancia StorageExpenses vazia.
	 * e também uma instancia de splitsDic com a finalidade de guardar
	 * todas as despesas associadas um Id de Split.
	 */
	public ExpenseCatalog() {
		StorageExpenses = new HashMap<Integer, Expense>();
		splitsDic = new HashMap<>();
 
	}  
	
	/**
	 * Adiciona um usuario para a instancia StorageUsers.
	 * 
	 * @param u. Eh uma instancia de User. Requires: Instancia de User deste genero
	 * User(nome,email)
	 */
	public void addExpense(Expense e) {
		StorageExpenses.put(id, e);
	}
	
	/**
	 * Procurar atraves de um Id qual a despesa que corresponde
	 * a esse valor.
	 * @param id -Valor Inteiro que corresponde a uma despesa
	 * @return Devolve uma despesa que corresponde a um Id
	 */
	public Expense getExpenseById(int id) {
		return StorageExpenses.get(id);
	}
	/**
	 * Corresponde associar o Id a um valor que corresponde a um Split.
	 * @param id - Valor Inteiro de o numero de despesas.
	 */
	public void setId(int id) {
		this.id=id;
	}
	
	/**
	 * Objectivo eh saber quantas linhas existem no ficheiro de expense
	 * para quando se estiver a fazer uma escrita continuar acrescentar linhas
	 * @return Devolve um numero inteiro de linhas de um ficheiro Expense
	 */
	public int getAllExpenses() {
		return StorageExpenses.size();
	}
	/**
	 * Associa a um Map com a chave que corresponde a um Split atual e
	 * um value com uma lista de Instancias de Despesas para a instancia StorageExpenses.
	 * Objectivo e ser usado no metodo printBalance da class Interp_
	 * @param idSplit - Id que corresponde ao Split corrente.
	 */
	public void setMapExpensesFromIdSplit(int idSplit){
		List<Expense> listExp = new ArrayList<>();
		
		for(Expense e: StorageExpenses.values()) {
			if(e.getIdSplit() == idSplit){
				listExp.add(e);
			}
		}
		splitsDic.put(idSplit,listExp);
	}
	
	/**
	 * @param idSplit - Id que corresponde ao Split corrente.
	 * @return uma Instancia Map<Integer, List<Expense>>
	 */
	public Map<Integer, List<Expense>> getMapExpensesFromIdSplit() {
		return splitsDic;
	}
	
	/**
	 * @param idSplit - Id que corresponde ao Split corrente.
	 * @return uma Instancia Map<Integer, List<Expense>>
	 */
	
	public List<Expense> getMapExpensesFromIdSplit(int idSplit) {
		return splitsDic.get(idSplit);
	}
	
	/**
	 * Chama a camada de persistencia e envia uma instancia de tipo Map<Integer, Expense>
	 */
	public void save() throws IOException {
		fcul.pco.eurosplit.persistence.ExpenseCatalog.save(StorageExpenses);
	}
	
	/**
	 * Chama a camada de Persistência que também tem um ExpenseCatalog e o método load()
	 * e carrega para a instancia de StorageExpenses todas as despesas 
	 * que estao no ficheiro.
	 * @throws ParseException 
	 */
	public void load() throws IOException, ParseException {
		StorageExpenses = fcul.pco.eurosplit.persistence.ExpenseCatalog.load();
	}
}
