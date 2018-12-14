package fcul.pco.eurosplit.domain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author: Saul Silva e Joao Paiva
 */
public class SplitCatalog {
	private Map<User, List<Split>> StorageSplits;
	public static SplitCatalog instance;

	/**
	 * Constructor sem parametros que inicializa uma instancia
	 * StorageSplits.
	 */
	private SplitCatalog() {
		StorageSplits = new HashMap<User, List<Split>>();
	}
	
	/**
	 * Saber quantos splits e que determinado User criou.
	 * @param currentUser - Instancia de User e de utilizador no momento.
	 * @return Devolve ArrayList<Split> de um determinado usuario.
	 */
	public ArrayList<Split> getUserSplits(User currentUser)  {
		ArrayList<Split> arraySplit = new ArrayList<Split>();
		List<Split> ListaDoUser = StorageSplits.get(currentUser);
		if(ListaDoUser != null) {
			for (Split sp : ListaDoUser) {
				arraySplit.add(sp);
			}
		}
		return arraySplit;
	}
	/**
	 * Associar a um User as suas instancias de Splits para um
	 * Map<User, List<Split>>.
	 * 
	 * @param u - Instancia User no momento(current)
	 * @param sp - Instancia Split no momento(current)
	 */
	public void addAnotherUserSplit(User u, Split sp) {
		ArrayList<Split> splitsFromUser = new ArrayList<>();
		if(getUserSplits(u) != null) {
			splitsFromUser = getUserSplits(u);
		}else {
			splitsFromUser = new ArrayList<Split>();
		}
		splitsFromUser.add(sp);
		StorageSplits.put(sp.getOwner(), splitsFromUser);
	}
	
	/**
	 * Verifica se Split já existe no ficheiro/SplitCatalog.
	 * @param sp
	 * @return
	 */
	public boolean checkSplitExist(String splitName,User u) {
		for(Split sp: StorageSplits.get(u)) {
			if(sp.getPurpose().equals(splitName)) {
				return true;
			}			
		}
		return false;
	}
	
	/**
	 * Objectivo e procurar qual o numero do split que 
	 * esta associado a determinado utilizador.
	 * 
	 * Ex: User Sophia tem 4 Splits e queremos o 3 Split, no entanto
	 * essa despesa esta escrita na linha 10. Assim, o 
	 * metodo ira procurar o numero 3,e nao 10.
	 * @param u - Instancia User. 
	 * @param id - Inteiro
	 * @return Devolve um Split se o numero existir, caso contrario
	 * escreve na consola que não existe esse evento e devolve um 
	 * Split null.
	 */
	public Split getSplitUserById(User u, int id) {
		List<Split> splitsUser = StorageSplits.get(u);
		Split splituser = null;
		for(Split s: splitsUser) {
			if(id == s.getIdSplits()) {
				splituser = s;
			}
		}
		if(splituser ==null) {
			System.out.println("There is no number associated with that event");
		}
		return splituser;
	}
	/**
	 * Inicializa um constructor caso esteja vazio, caso contrario
	 * devolve a instancia que foi criada.
	 * @return Instancia do Constructor SplitCatalog
	 */
	public static SplitCatalog getInstance() {
		boolean testeInstance = Objects.isNull(instance);// Class objectos java 8
		if (testeInstance) {
			return new SplitCatalog();
		} else {
			return instance;
		}
	}

	/**
	 * Chama a camada de persistencia e envia uma instancia de tipo 
	 */
	public void save() throws IOException {
		fcul.pco.eurosplit.persistence.SplitCatalog.save(StorageSplits);
	}

	/**
	 * Chama a camada de Persistência que também tem um SplitCatalog e o método load()
	 * e carrega para a instancia de StorageSplits todos os splits 
	 * que estao no ficheiro.
	 */
	public void load() throws IOException {
		StorageSplits = fcul.pco.eurosplit.persistence.SplitCatalog.load();
		
	}

}
