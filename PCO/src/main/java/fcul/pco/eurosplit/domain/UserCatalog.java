package fcul.pco.eurosplit.domain;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UserCatalog {
	private Map<String, User> StorageUsers; 
	public UserCatalog() {
		StorageUsers = new HashMap<String, User>();
	}

	public void addUser(User u) {
		StorageUsers.put(u.getEmail(), u);
 
	}

	public boolean hasUserWithId(String id) {
		try {
			StorageUsers.get(id);
			return true;
		} catch (NullPointerException e) {
			return false;
		}
	}

	public User getUserById(String id) {
		try {
			return StorageUsers.get(id);
		} catch (NullPointerException e) {
			System.out.println("Outilizador");
		}
		return StorageUsers.get(id);
	}

	public Map<String, User> getUsers() {
		return StorageUsers;
	}
	
	/*
	 * chama a camada de Persistência que também tem o UserCatalog
	 * e o metodo save()
	 */
	public void save() throws IOException {
		fcul.pco.eurosplit.persistence.UserCatalog.save(StorageUsers);
		
	}
	/*
	 * Chamda a camada de Persistência que também tem o UserCatalog
	 * e o método load
	 */
	public void load() throws IOException{
		fcul.pco.eurosplit.persistence.UserCatalog.load();
	}
}
