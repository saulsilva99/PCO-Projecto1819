package fcul.pco.eurosplit.domain;



public class User {
	private String email;
	private String nome;
	
	public User(String email, String nome) {
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
		return email + "@" + nome;
	}
	public User fromString(String s) {
		
		StringBuilder sb = new StringBuilder(s);

		String nome = sb.toString().split("@")[0]; 
		String email = sb.toString().split("@")[1];
		
		User usuario = new User(email,nome);
		
		return usuario;
	}
	
}
