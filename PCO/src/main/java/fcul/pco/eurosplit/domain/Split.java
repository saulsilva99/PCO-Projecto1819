package fcul.pco.eurosplit.domain;

import java.util.ArrayList;
import fcul.pco.eurosplit.main.Start;

/**
 * @author: Saul Silva e Joao Paiva
 */
public class Split {
	private static int contador = 1;
	private User owner;
	private int idSplits;
	private int id; 
	private String event;
	private final ArrayList<Expense> expenses;
	private int incExpense  = 0;
	
	public Split() {
		expenses = new ArrayList<>();
	}
	/**
	 * Constructor de um split que inicializa o atributo owner, o arraList expenses e o Id.
	 * @param owner
	 */
	public Split(User owner) {
		this.owner= owner;
		expenses = new ArrayList<>();
		this.id=contador;
		contador++; 
		
	}
	/**
	 * Constructor inicializa tambem atributo expenses.
	 * @param owner - Instancia User
	 * @param id - Inteiro para o valor do Split
	 * @param evento - descricao do Split "trip to la"
	 */
	public Split(User owner,int id,String evento) {
		this.owner = owner;
		this.event=evento;
		this.id=contador;
		this.idSplits=id;
		expenses = new ArrayList<>();
		contador++;
	}
	
	/**
	 * 
	 * @return numero inteiro referente ao Id do split.
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * 
	 * @return Devolve um inteiro com o numero todas de despesas
	 */
	public int getExpenses() {
		return expenses.size();
	}
	/**
	 * @return Devolve o User da Instancia corrente.
	 */
	public User getOwner() {
		return owner;
	}
	/**
	 * 
	 * @return Devolve a descricao da noticia corrente.
	 */
	public String getPurpose() {
		return event;
	}
	
	/**
	 * Objectivo e procurar qual o numero do split que 
	 * esta associado a determinado utilizador.
	 * 
	 * Ex: User Sophia tem 4 Splits e queremos o 3 Split, no entanto
	 * essa despesa esta escrita na linha 10. Assim, o 
	 * metodo ira procurar o numero 3,e nao 10.
	 * @return Devolve o numero inteiro do split associado 
	 */
	public int getIdSplits() { // metodo necessario na class ExpenseCatalog
		return idSplits; // 
	}
	
	/**
	 * Adiciona uma despesa a um ArrayList<Expense>
	 * @param e - Instancia de Expense
	 */
	public void addExpense(Expense e) {
		expenses.add(e);
	}
	
	/**
	 * 
	 * @return Numero inteiro que é igual ao nº despesas que determinado split contem.
	 */
	public int getincExpense() {
		return incExpense;
	}
	
	/**
	 * Associar o numero de despesas associas ah instancia de split corrente.
	 * @param idSplits - Inteiro referente ao numero de despesas associadas ao split
	 */
	public void setidSplits(int idSplits) {
		this.idSplits=idSplits;
	}
	
	/**
	 * Associar o numero de despesas que determinado Split tem.
	 * E importante usar este metodo, pois na leitura de um ficheiro e necessario saber
	 * quantas despesas existem.
	 * @param expensesConnected
	 */
	public void setIncExpense(int expensesConnected) {
		this.incExpense=expensesConnected;
	}
	
	/**
	 * Atribui a Instancia do constructor uma descricao do split.
	 * @param event
	 */
	public void setEvent(String event) {
		this.event=event;
	} 
	
	public void incExpense() {
		this.incExpense+=1;
	}
	

	/**
	 * Objectivo eh escrever este metodo para mais tarde ser guardado
	 * num ficheiro com o formato: "id-email-descricao-IdSplitUnico:numeroInteiroDasDespesas"
	 */
	@Override
	public String toString() {
		return id + "-" + owner.getEmail() + "-" + event + "-" + idSplits + ":" +incExpense;
	}
	/**
	 * Este metodo sera usado para converter uma string lida de um ficheiro
	 * para uma instancia de Split.
	 * @param s - String recebida de uma linha de um ficheiro
	 * @return
	 */
	public Split fromString(String s) {
		String[] expenseArray = s.split("-");
		int idLinha = Integer.parseInt(expenseArray[0]);
		String email = expenseArray[1];
		User u = Start.getUserCatalog().getUserById(email);
		String event = expenseArray[2];
		int idSplit = Integer.parseInt(expenseArray[3].split(":")[0]);
		int expenses = Integer.parseInt(expenseArray[3].split(":")[1]);
		Split split = new Split(u,idLinha,event);
		split.setIncExpense(expenses);
		split.setidSplits(idSplit);

		return split;
	}		
}
