package fcul.pco.eurosplit.domain;

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
		StringBuilder sbValue = new StringBuilder();
		sbValue.append(Despesavalor);
		// Opcao: return Despesa + "@" + sbValue+ "@" 
		//				 + paidBy.getName() + "@" + 
		//				 + paidBy.getEmail()+ "@" +
		//				 + "Date";
		
		return "Despesa: " + Despesa + " Valor da Despesa: " + sbValue 
				+ " User Name: " + paidBy.getName()
				+ " User Email: " + paidBy.getEmail()
				+ " Date: " + "Falta depois por aqui a data";
	}
	public void fromString(String s) {
		StringBuilder sb = new StringBuilder(s);
		System.out.println(s.split("@"));	
	}
	
	
}
