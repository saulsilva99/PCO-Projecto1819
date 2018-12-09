package fcul.pco.eurosplit.domain;

import java.util.ArrayList;

public class Split {
	private User owner;
	private int id;
	private String event;
	private final ArrayList<Expense> expenses;
	
	public Split(User owner) {
		this.owner= owner;
		expenses = new ArrayList<>();
	}
	public Split(User owner, int id, String evento) {
		this.owner= owner;
		this.id=id;
		this.event=evento;
		expenses = new ArrayList<>();
	}
	
	public void setEvent(String event) {
		this.event=event;
	}
	public User getOwner() {
		return owner;
	}
	public String getPurpose() {
		return event;
	}
	public void addExpense(Expense e) {
		expenses.add(e);
	}

	@Override
	public String toString() {
		return id + "-" + owner.getEmail() + "-" + event + "-" + id + ":" + expenses.size();
	}
	
	public Split fromString(String s) {
		
		String[] expenseArray = s.split("-");
		int id = Integer.parseInt(expenseArray[0]);
		String email = expenseArray[1];
		String event = expenseArray[2];
		int size = Integer.parseInt(expenseArray[3].split(":")[1]);
		String totalSplit = id+"-"+email+"-"+"-"+"-"+event+"-"+id+":"+size;

		return null;
	}	
	
}
