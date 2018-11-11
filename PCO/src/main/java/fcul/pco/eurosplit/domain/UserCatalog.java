package fcul.pco.eurosplit.domain;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/*
 * Objectivo da Class é armazenar Instancias
 * de Usuarios.
 * @author: Saul Silva e Joao Paiva
 */
public class UserCatalog {
	private Map<String, User> StorageUsers;

	/*
	 * Inicializa um constructor com a Instancia StorageUsers vazia.
	 */
	public UserCatalog() {
		StorageUsers = new HashMap<String, User>();
	}

	/*
	 * Adiciona um usuario para a instancia StorageUsers.
	 * 
	 * @param u. Eh uma instancia de User. Requires: Instancia de User deste genero
	 * User(nome,email)
	 */
	public void addUser(User u) {
		StorageUsers.put(u.getEmail(), u);
	}

	/*
	 * Descobrir se existe um usuario com determinado email.
	 * 
	 * @param id. Eh uma String de email. Requires: id ser um email valido. Ensures:
	 * Devolve true se existir, caso constrário Falso.
	 */
	public boolean hasUserWithId(String id) {
		if (StorageUsers.get(id) != null) {
			return true;
		} else
			return false;
	}

	/*
	 * Descobrir se existe um usuario com determinado email.
	 * 
	 * @param id. Eh uma String de email. Requires: id ser um email valido. Ensures:
	 * Devolve o Objecto se o utilizador existe caso contrario levanta uma exceção
	 */
	public User getUserById(String id) throws NullPointerException {
		return StorageUsers.get(id);
	}

	/*
	 * chama a camada de Persistência que também tem o UserCatalog e o metodo save()
	 */
	public void save() throws IOException {
		fcul.pco.eurosplit.persistence.UserCatalog.save(StorageUsers);
	}

	/*
	 * Chama a camada de Persistência que também tem o UserCatalog e o método load
	 */
	public void load() throws IOException {
		StorageUsers = fcul.pco.eurosplit.persistence.UserCatalog.load();
	}
}
