package fcul.pco.eurosplit.domain;

import java.lang.reflect.Array;

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
	public void fromString(String s) {
		StringBuilder sb = new StringBuilder(s);
		String[] arrayUserString = new String[2];
		arrayUserString[0]= sb.toString().split("@")[0];
		System.out.println(sb.toString().split("@")[0]); 
		
	}
	
}
