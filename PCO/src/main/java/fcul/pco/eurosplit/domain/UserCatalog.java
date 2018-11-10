package fcul.pco.eurosplit.domain;

import java.util.HashMap;
import java.util.Map;

public class UserCatalog {
	private Map<String, User> StorageUsers;

	public UserCatalog() {
		StorageUsers = new HashMap<String, User>();
	}

	public void addUser(User u) {
		if(!hasUserWithId(u.getEmail())) {
			StorageUsers.put(u.getEmail(), u);
		}
	}
	
	public boolean hasUserWithId(String id) {
		try{
			StorageUsers.get(id);
			return true;
		}
		catch(NullPointerException e){
				return false;			
		}
	}

	public User getUserById(String id) {
		try{
			return StorageUsers.get(id);
		}
		catch(NullPointerException e){
				System.out.println("Não existe nenhum utilizador com essa chave");			
		}
		return StorageUsers.get(id);
	}
}
