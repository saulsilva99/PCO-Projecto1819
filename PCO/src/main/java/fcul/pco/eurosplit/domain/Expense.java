package fcul.pco.eurosplit.domain;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
public class Expense {
	private String Despesa;
	private int Despesavalor;
	private User paidBy;
	private Date date;
	
	public Expense(String d, int DValue,User paid,Date dt){
		this.Despesa = d;
		this.Despesavalor = DValue;
		this.paidBy = paid;
		this.date = dt;	
	}
	
	
	public String toString() {
		String strDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
		 return Despesa + "@" + Despesavalor+ "@" 
						 + paidBy.getEmail() + "@"  
						 + paidBy.getName() + "@" 
						 + "Date" + strDate;
		
		//return "Despesa: " + Despesa + " Valor da Despesa: " + sbValue 
		//		+ " User Name: " + paidBy.getName()
		//		+ " User Email: " + paidBy.getEmail()
		//		+ " Date: " + "Falta depois por aqui a data";
	}
	public Expense fromString(String s) throws ParseException {
		StringBuilder sb = new StringBuilder(s);
		String Despesa	= sb.toString().split("@")[0];
		String DespesaValor = sb.toString().split("@")[1];
		String Username = sb.toString().split("@")[2];
		String email = sb.toString().split("@")[3];
		User user = new User(Username, email);
		
		SimpleDateFormat formatter =new SimpleDateFormat("yyyy-MM-dd");
		String dataString = sb.toString().split("@")[4].split("Date")[1];
		Date dt = formatter.parse(dataString);
		Expense exp = new Expense(Despesa,Integer.parseInt(DespesaValor),user, dt);
		return exp;
		
	}
	
	
}
