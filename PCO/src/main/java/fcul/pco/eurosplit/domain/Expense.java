package fcul.pco.eurosplit.domain;

import java.text.ParseException;
import java.util.ArrayList;

/*
 * Representacao de uma despesa 
 * @author Saul Silva e Joao Paiva
 */
public class Expense {
	private String Despesa; 
	private int Despesavalor;
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
	public Expense(String d, int DValue,User paid,Date dt, ArrayList<User> paidfor){
		this.Despesa = d;
		this.Despesavalor = DValue;
		this.paidBy = paid;
		this.date = dt;	
		this.paidfor = new ArrayList<User>();
	}
	
	/*
	 * Proposito para testes. Poderá ter de ser apagada.
	 */
	public Expense(String d, int DValue,User paid,Date dt){
		this.Despesa = d;
		this.Despesavalor = DValue;
		this.paidBy = paid;
		this.date = dt;	
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
		return Despesa + "-" + Despesavalor+ "-" 
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
		String Despesa	= s.split("-")[0];
		String DespesaValor = s.split("-")[1];
		
		//Valores para a data
		String dataString = s.split("-")[4];
		Date dtObjt = fcul.pco.eurosplit.domain.Date.now();
		Date finalDate = dtObjt.fromString(dataString);
		
		//Criação da Instância User
		String Username = s.split("-")[2];
		String email = s.split("-")[3];
		User user = new User(Username, email);

		Expense exp = new Expense(Despesa,Integer.parseInt(DespesaValor),user, finalDate);
		return exp;
	}
}
