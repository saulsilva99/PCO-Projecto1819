package fcul.pco.eurosplit.domain;



public class User {
	private String email;
	private String nome;
	
	public User() {
		
	}
	public User(String nome, String email) {
		this.email = email;
		this.nome = nome;
	} 
	public String getName() {
		return nome;
	}
	public String getEmail() {
		return email;
	}
	
	public String toString() {
		return email + "+" + nome;
	} 
	
	/*
	 * 
	 */
	public User fromString(String s) {
		StringBuilder sb = new StringBuilder(s);
		email = sb.toString().split(" ")[3];
		nome = sb.toString().split(" ")[1];
		User usuario = new User(nome,email);
		
		return usuario;
	}
	
}
