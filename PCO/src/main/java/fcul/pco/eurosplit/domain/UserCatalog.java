package fcul.pco.eurosplit.domain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Objectivo da Class é armazenar Instancias
 * de Usuarios.
 * @author: Saul Silva e Joao Paiva
 */
public class UserCatalog {
	/**
	 * Contem um dicionario que guarda instancia de usuarios
	 * com uma chave que é o respectivo email
	 */
	private Map<String, User> StorageUsers;

	/**
	 * Inicializa um constructor com a Instancia StorageUsers vazia.
	 */
	public UserCatalog() {
		StorageUsers = new HashMap<String, User>();
	}
 
	/**
	 * Adiciona um usuario para a instancia StorageUsers.
	 * 
	 * @param u. Eh uma instancia de User. 
	 * Requires: Instancia de User deste genero
	 * User(nome,email)
	 */
	public void addUser(User u) {
		StorageUsers.put(u.getEmail(), u);
	}

	/**
	 * Descobrir se existe um usuario com determinado email.
	 * 
	 * @param id. Eh uma String de email. 
	 * Requires: id ser um email valido. 
	 * Ensures: Devolve true se ele existir, caso constrário Falso.
	 */
	public boolean hasUserWithId(String id) {
		if (StorageUsers.get(id) != null) {
			return true;
		} else
			return false;
	}

	/**
	 * Descobrir se existe um usuario com determinado email.
	 * 
	 * @param id Eh uma String de email e tem de ser valida. 
	 * @return Devolve o Objecto se o utilizador existir, caso contrario
	 * devolve um valor null
	 */
	public User getUserById(String id) {
		if (hasUserWithId(id)) {
			StorageUsers.get(id).toString();// necessário colocar em toString();
			return StorageUsers.get(id);
		} else {
			return null;
		}
	}
	/**
	 * Verifica se um utilizador ja existe atraves do
	 * seu email na instancia de StorageUsers 
	 * @param id - String com email de um Utilizador.
	 * @return Devolve um utilizador se existir em StorageUsers,
	 * caso contrario null.
	 */
	public User checkUserExistsById(String id) {
		if (!hasUserWithId(id)) {
			StorageUsers.get(id).toString();// necessário colocar em toString();
			return StorageUsers.get(id);
		} else {
			return null;
		}
	}
	/**
	 * Verifica se um utilizador ja existe atraves do
	 * seu nome na instancia de StorageUsers 
	 * @param name - Nome de um utilizador
	 * @return Devolve um utilizador se existir em StorageUsers,
	 * caso contrario null.
	 */
	public User getUserWithNameSingle(String name) {
		User user = null;
		for (User u : StorageUsers.values()) {
			if (u.getName().equals(name)) {
				user = u;
			}
		}
		return user;
	}
	/**
	 * Procura da Instancia de StorageUsers se ja
	 * existe um ou mais utilizadores com determinado nome.
	 * @param name - String com nome de um utilizador
	 * @return Devolve um ArrayList<User> com a qty de 
	 * User encontrados com esse nome.
	 */
	public ArrayList<User> getUsersWithName(String name){
		ArrayList<User> userList = new ArrayList<User>();
		for(User u: StorageUsers.values()) {
			if(u.getName().equals(name)) {
				userList.add(u);
			}
		}
		return userList;
	}
	
	
	/**
	 * Objectivo é organizar todos os utilizadores que estao
	 * na instancia de StorageUser por ordem crescente.
	 * @return Devolve um ArrayList<User> com todos os 
	 * utilizadores organizados por ordem crescente.
	 */
	public ArrayList<User> usersToArray() {
		ArrayList<User> l = new ArrayList<User>();
		l.addAll(StorageUsers.values());
		Collections.sort(l);
		return l;
	}

	/**
	 * Chama a camada de Persistência que também 
	 * tem o UserCatalog e o metodo save(). 
	 */
	public void save() throws IOException {
		fcul.pco.eurosplit.persistence.UserCatalog.save(StorageUsers);
	}

	/**
	 * Chama a camada de Persistência que também tem um UserCatalog e o método load()
	 * e carrega para a instancia de StorageUsers todos os Utilizadores 
	 * que estao no ficheiro.
	 */
	public void load() throws IOException {
		StorageUsers = fcul.pco.eurosplit.persistence.UserCatalog.load();
	}
}
