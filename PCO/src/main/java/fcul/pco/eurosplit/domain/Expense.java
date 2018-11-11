package fcul.pco.eurosplit.domain;

import java.text.ParseException;
import java.util.ArrayList;

/*
 * 
 */
public class Expense {
	private String Despesa; 
	private int Despesavalor;
	private User paidBy;
	private Date date; 
	private ArrayList<User> paidfor;
	
	public Expense(String d, int DValue,User paid,Date dt, ArrayList<User> paidfor){
		this.Despesa = d;
		this.Despesavalor = DValue;
		this.paidBy = paid;
		this.date = dt;	
		this.paidfor = new ArrayList<User>();
	}
	
	public Expense(String d, int DValue,User paid,Date dt){
		this.Despesa = d;
		this.Despesavalor = DValue;
		this.paidBy = paid;
		this.date = dt;	
	}
		
	public void addPaidFor(User u) {
		paidfor.add(u);
	}

	public String toString() {
		
		return Despesa + "-" + Despesavalor+ "-" 
						 + paidBy.getName() + "-"
						 + paidBy.getEmail() + "-"  
						 + "Date" + date.toString();
	}
	
	
	/*
	 * Este metodo recebe uma String s com o seguinte formato:
	 * A determinar
	 * @param s. Ser uma String que separa os valores por "-".
	 * Requires: String s estar com o formato x.
	 * Ensures: Devolve um objecto da propria class Expense.
	 */
	
	public Expense fromString(String s) throws ParseException {
		StringBuilder sb = new StringBuilder(s);
		
		//Valores para usar no objecto Expense
		String Despesa	= sb.toString().split("-")[0];
		String DespesaValor = sb.toString().split("-")[1];
		
		
		//Valores para a data
		String dataString = sb.toString().split("-")[4];
		Date dtObjt = fcul.pco.eurosplit.domain.Date.now();
		Date finalDate = dtObjt.fromString(dataString);
		
		//Criação da Instância User
		String Username = sb.toString().split("-")[2];
		String email = sb.toString().split("-")[3];
		User user = new User(Username, email);


		Expense exp = new Expense(Despesa,Integer.parseInt(DespesaValor),user, finalDate);
		return exp;
	}
}
