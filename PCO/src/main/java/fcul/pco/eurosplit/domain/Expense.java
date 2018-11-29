package fcul.pco.eurosplit.domain;

import java.text.ParseException;
import java.util.ArrayList;


/*
 * Representacao de uma despesa 
 * @author Saul Silva e Joao Paiva
 */
public class Expense {
	private static int contador;
	private int id; 
	private int despesavalor;
	private User paidBy;
	private Date date; 
	private ArrayList<User> paidfor;
	
	/*
	 * @param d. Eh uma string com o nome da despesa ?
	 * @param DValue. Valor da Despesa em inteiro.
	 * @param paidBy. Instancia do usuario que pagou a despesa
	 * @param dt. Instancia de Data do usuario que pagou.
	 * @param paidfor. ArrayList de Usuario.
	 * Requires: Respeitar os valores dos params. User existir, assim como
	 * data ser válida.
	 */
	public Expense(User paidUser,Date dt, ArrayList<User> paidfor){
		this.id = contador;
		this.paidBy = paidUser;
		this.date = dt;	
		this.paidfor = new ArrayList<User>();
		this.contador++;
	}
	
	
	/*
	 * Devolve o Id de uma despesa.
	 */
	public int getDespesaId() {
		return id;
	}
	
	/*
	 * Adicionar um usuario a um arraylist<user>.
	 * @param u. Ser uma Instancia de User(nome,email).
	 * Requires: Uma instancia de User e usuário existir.
	 */
	public void addPaidFor(User u) {
		paidfor.add(u);
	}
	
	
	/*
	 * Converter o Objecto Expense para String.
	 * Com o seguinte formato:
	 * => "<Despesa>-<Despesavalor>-<paidBy.getName()>-<paidBy.getEmail()>-<Date><date.toString()>";
	 * Ensures: Devolve uma String com o formato em cima.
	 */
	public String toString() {
		return id + "-" + despesavalor+ "-" 
						 + paidBy.getName() + "-"
						 + paidBy.getEmail() + "-"  
						 + date.toString();
	}
	
	
	/*
	 * Este metodo recebe uma String s com o seguinte formato:
	 * A determinar
	 * @param s. Ser uma String que separa os valores por "-".
	 * Requires: String s estar com o formato x.
	 * Ensures: Devolve um objecto da propria class Expense.
	 */
	public Expense fromString(String s) throws ParseException {
		//Valores para usar no objecto Expense
		String email = s.split("-")[0];
		User user = fcul.pco.eurosplit.main.Start.getUserCatalog().getUserById(email);
		
		//Valores para a data
		String dataString = s.split("-")[4];// isto ainda não está correcto!
		Date dtObjt = fcul.pco.eurosplit.domain.Date.now();
		Date finalDate = dtObjt.fromString(dataString);
		
		Expense exp = new Expense(user, finalDate,paidfor); //mal
		return exp;
	}
}
