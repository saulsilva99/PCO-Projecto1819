package fcul.pco.eurosplit.domain;

/*
 * Esta class representa uma Usuário composto
 * pelo seu Nome e Email
 * @author Saul Silva e João Paiva
 */
public class User {
	private String email;
	private String nome;
	
	/*
	 * Cria uma Instancia de User com dois params.
	 * @param nome. Nome do usuario em formato String.
	 * @param email. Email do usuario em formato String.
	 */
	public User(String nome, String email) {
		this.email = email;
		this.nome = nome;
	} 
	
	/*
	 * Este metodo vai buscar o nome do User.
	 * Requires: A instancia user ter sido inicializada 
	 * com nome e email.
	 * Ensures: Devolve o nome da Instancia User em string.
	 */
	public String getName() {
		return nome;
	}
	
	/*
	 * Este metodo vai buscar o email do User.
	 * Requires: A instancia user tem sido inicializa 
	 * com o nome e email.
	 * Ensures: Devolve o email da Instancia User.
	 */
	public String getEmail() {
		return email;
	}
	
	/*
	 * Transforma em formato String a Instancia User
	 * Requires: A instancia User ter sido inicializa com 
	 * nome e email.
	 * Ensures: Devolve uma string com o formato
	 * => "<email>+<nome>";
	 */
	public String toString() {
		return email + "-" + nome;
	} 
	
	/*
	 * Recebe uma String com o formato:
	 * "<emailDaPessoa>-<nomeDaPessoa>".
	 *  Ensures: Devolve um objecto User
	 */
	public User fromString(String s) {
		email = s.split("-")[0];
		nome = s.split("-")[1];
		User usuario = new User(nome,email);
		return usuario;
	}
	
}
