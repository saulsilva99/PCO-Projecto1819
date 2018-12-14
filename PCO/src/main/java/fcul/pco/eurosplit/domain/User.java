package fcul.pco.eurosplit.domain;

/**
 * Esta class representa uma Usuário composto
 * pelo seu Nome e Email
 * @author Saul Silva e João Paiva
 */
public class User implements Comparable<User> {
	private String email;
	private String nome;
	
	/**
	 * Cria uma Instancia de User com dois params.
	 * @param nome. Nome do usuario em formato String.
	 * @param email. Email do usuario em formato String.
	 */
	public User(String nome, String email) {
		this.email = email;
		this.nome = nome;
	}   
	
	/**
	 * Este metodo vai buscar o nome do User.
	 * A instancia user ter sido inicializada com nome e email.
	 * @return Devolve o nome da Instancia User em string.
	 */
	public String getName() {
		return nome;
	}
	
	/**
	 * Este metodo vai buscar o email do User. 
	 * A instancia user tem sido inicializa com o nome e email.
	 * @return Devolve o email da Instancia User.
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Transforma em formato String a Instancia User.
	 * A instancia User ter sido inicializa com nome e email
	 * @return Devolve uma string com o formato
	 */
	@Override
	public String toString() {
		return email + "#" + nome;
	} 
	
	/**
	 * Converte uma String para uma Instancia de User.
	 * @param s - String de tipo "emailDaPessoa#nomeDaPessoa"
	 * @return Devolve um objecto User
	 */
	public User fromString(String s) {
		email = s.split("#")[0];
		nome = s.split("#")[1];
		User usuario = new User(nome,email);
		return usuario;
	}
	
	/**
	 * Objectivo e depois fazer comparacoes de Usuarios
	 * para conseguir ser possivel ordena-los
	 */
	@Override
	public int compareTo(User o) {
		int value = nome.compareTo(o.nome);
		if(value == 0) { // no caso de os emails serem iguais.
			value = email.compareTo(o.email);
		}
		return value;
	}
	
}
