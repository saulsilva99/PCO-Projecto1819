package fcul.pco.eurosplit.domain;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Objects;
import fcul.pco.eurosplit.main.Start;


/**
 * Representacao de uma despesa 
 * @author Saul Silva e Joao Paiva
 */
public class Expense {
	private int idSplit; //corresponde ao id do Split
	private double despesavalor;
	private User paidBy;
	private Date date; 
	private String description; 
	private ArrayList<User> paidfor;
	public static Expense instance;
	
	private Expense() {
		
	}
	/**
	 * Respeitar os valores dos params. User existir, assim como
	 * data ser válida.
	 * @param description. Eh uma string de descricao da despesa
	 * @param paidBy. Instancia do usuario que pagou a despesa
	 * @param d. Instancia de Data do usuario que pagou.
	 */ 
	public Expense(String description, double despesavalor, User paidBy,Date d) {
		this.description = description; 
		this.despesavalor = despesavalor;
		this.paidBy = paidBy;
		this.date = d;	
		this.paidfor = new ArrayList<User>();
	}
	/**
	 * 
	 * @param description - Descricao da despesa
	 * @param despesavalor - Valor de uma despesa
	 * @param d - Instancia de Date
	 */
	public Expense(String description, double despesavalor, Date d) {
		this.description = description;
		this.despesavalor = despesavalor;
		this.date = d;	
		this.paidfor = new ArrayList<User>();
	}
	/**
	 * Inicializa um constructor caso esteja vazio, caso contrario
	 * devolve a instancia que foi criada.
	 * @return Instancia do Constructor SplitCatalog
	 */
	public static Expense getInstance() {
		boolean testeInstance = Objects.isNull(instance);// Class objectos java 8
		if (testeInstance) {
			return new Expense();
		} else {
			return instance;
		}
	}
	
	/**
	 * Coloca o valor da despesa igual ao do seu split correspondente.
	 * @param idSplit - Valor Split correspondente.
	 */
	public void setIdSplit(int idSplit) {
		this.idSplit=idSplit;
	}
	/**
	 * @return devolve o numero do split de uma despesa que
	 * corresponde a um split.
	 */
	public int getIdSplit() {
		return idSplit;
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
	 * Este metodo permite descobrir os utilizadores
	 * que estiverem associados a uma despesa.
	 * @return Devolve um ArrayList<User> 
	 */
	public ArrayList<User> getPaidFor(){
		return paidfor;
	}
	
	/**
	 * Este metodo permite remover os caracteres "[" e "]" de uma string
	 * e agrega-lo para uma instancia de ArrayList<User> paidfor;
	 * @param s - String com o formato "[email#nome,email#nome,..]"
	 */
	public void getListUsersFromString(String s){
		String str = s.replace("[","").replace("]","");
		String[] arrayString = str.split(",");
		ArrayList<User> userList= new ArrayList<>();
		
		for(int i=0;i < arrayString.length; i++){
			String nome = arrayString[i].split("#")[1];
			String email = arrayString[i].split("#")[0];
			User u= new User(nome,email);
			userList.add(u);
		}
		 this.paidfor = userList;
	}


	/**
	 * Converter o Objecto Expense para String.
	 * Com o seguinte formato: "id-descricao-[email#nome,email#nome,..]-data"
	 * Ensures: Devolve uma String com o formato em cima.
	 */
	@Override
	public String toString() {
		return idSplit+ "-" + getDescription()+ "-"
			   + getDespesaValor() + "-"
			   + paidfor.toString() + "-" 
		       + date.toString();
	}
	
	/**
	 * Objectivo será devolver uma instancia Expense derivada de
	 * uma String. Este metodo verá primeiro para o qual terá de 
	 * criado o tipo de constructor.
	 * @param s. Ser uma String que vai ser separa os valores por "-".
	 * String s estar separada preparada conforme o metodo
	 * toString(). O split por <"-"> da String só deve ter valores 
	 * entre 4 ou 5.
	 * @return Devolve um objecto da propria class Expense.
	 */
	public Expense fromString(String linha) throws ParseException {
		
		int idSplit= Integer.parseInt(linha.split("-")[1]);
		String description = linha.split("-")[2];
		Double valor = Double.parseDouble(linha.split("-")[3]);
		String email = linha.split("-")[4].split("#")[0].replace("[", "");
		String dataString = linha.split("-")[5];
		
		String[] arrayDate = dataString.split("/");
		int year = Integer.parseInt(arrayDate[0]);
		int month = Integer.parseInt(arrayDate[1]);
		int day = Integer.parseInt(arrayDate[2]);
		int hours = Integer.parseInt(arrayDate[3]);
		int minuts = Integer.parseInt(arrayDate[4]);
		
		date = new Date(year, month, day, hours, minuts);

		User u = Start.getUserCatalog().getUserById(email);
		Expense expense = new Expense(description, valor, u, date);
		expense.setIdSplit(idSplit);
		expense.getListUsersFromString(linha.split("-")[4]);
		expense.setIdSplit(idSplit);
		return expense;
	}
}
