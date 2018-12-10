package fcul.pco.eurosplit.domain;

import java.text.ParseException;
import java.util.ArrayList;


/**
 * Representacao de uma despesa 
 * @author Saul Silva e Joao Paiva
 */
public class Expense {
	private static int contador;
	private int id; 
	private double despesavalor;
	private User paidBy;
	private Date date; 
	private String description;
	private ArrayList<User> paidfor;
	
	/**
	 * @param description. Eh uma string de descricao da despesa
	 * @param paidBy. Instancia do usuario que pagou a despesa
	 * @param d. Instancia de Data do usuario que pagou.
	 * @param paidBy. Instancia de Usuario
	 * Requires: Respeitar os valores dos params. User existir, assim como
	 * data ser válida.
	 */
	public Expense(String description, double despesavalor, User paidBy,Date d) {
		this.description = description;
		this.despesavalor = despesavalor;
		this.id = contador;
		this.paidBy = paidBy;
		this.date = d;	
		this.paidfor = new ArrayList<User>();
		contador++;
	}
	
	public Expense(String description, double despesavalor, Date d) {
		this.description = description;
		this.despesavalor = despesavalor;
		this.id = contador;
		this.date = d;	
		this.paidfor = new ArrayList<User>();
		contador++;
	}
	
	/**
	 * @param id. numero unico da despesa e inteiro
	 * @param description. Eh uma string de descricao da despesa
	 * @param paidBy. Instancia do usuario que pagou a despesa
	 * @param d. Instancia de Data do usuario que pagou.
	 * @param paidBy. Instancia de Usuario
	 * Requires: Respeitar os valores dos params. User existir, assim como
	 * data ser válida.
	 */
	private Expense(int id, String description, double despesavalor, User paidBy,Date d) {
		this.description = description;
		this.id = contador;
		this.paidBy = paidBy;
		this.date = d;	
		this.paidfor = new ArrayList<User>();
		contador++;
	}
	
	/**
	 * Devolve um inteiro e valor unico do id de uma despesa.
	 */
	public int getDespesaId() {
		return id;
	}
	
	/**
	 * Adicionar um usuario a um arraylist<user>.
	 * @param u. Ser uma Instancia de User(nome,email).
	 * Requires: Uma instancia de User e usuário existir.
	 */
	public void addPaidFor(User u) {
		paidfor.add(u);
	}
	
	/**
	 * Devolve uma string que eh a descricao da instancia 
	 * Expense.
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Devolve uma Data da instancia Expense.
	 */
	public Date getDate() {
		return date;
	}
	
	/**
	 * Devolve um usuario que pagou a despesa 
	 * da instancia Expense.
	 */
	public User getUserPaidBy() {
		return paidBy;
	}
	
	/**
	 * Devolve um o valor da despesa pagou a despesa 
	 * da instancia Expense.
	 */
	public double getDespesaValor() {
		return despesavalor;
	}
	
	
	/**
	 * Converter o Objecto Expense para String.
	 * Com o seguinte formato:
	 * => "";
	 * Ensures: Devolve uma String com o formato em cima.
	 */
	@Override
	public String toString() {
		return id + "-" + getDescription()+ "-"
			   + getDespesaValor() + "-"
			   + paidBy.getEmail() + "-" 
		       + date.toString();

	}
	
	/**
	 * Objectivo será devolve uma instancia Expense derivada de
	 * uma String. Este metodo verá primeiro para o qual terá de 
	 * criado o tipo de constructor.
	 * @param s. Ser uma String que vai ser separa os valores por "-".
	 * Requires: String s estar separada preparada conforme o metodo
	 * toString(). O split por <"-"> da String só deve ter valores 
	 * entre 4 ou 5.
	 * Ensures: Devolve um objecto da propria class Expense.
	 */
	public Expense fromString(String s) throws ParseException {
		String[] tamanhoString = s.split("-");
		
		if(!(tamanhoString.length == 4)) {
			int id = Integer.parseInt(tamanhoString[0]);
			String description = tamanhoString[1];
			double despesaV = Double.parseDouble(tamanhoString[2]);
			User user = fcul.pco.eurosplit.main.Start.getUserCatalog().getUserById(paidBy.getEmail());
			Date data = date.fromString(tamanhoString[4]);
			return new Expense(id,description,despesaV,user,data);
		}
		String description = tamanhoString[0];
		double despesaV = Double.parseDouble(tamanhoString[1]);
		User user = fcul.pco.eurosplit.main.Start.getUserCatalog().getUserById(paidBy.getEmail());
		Date data = date.fromString(tamanhoString[3]);
		return new Expense(description,despesaV,user,data);

	}
}
