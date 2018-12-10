package fcul.pco.eurosplit.domain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class SplitCatalog {
	private Map<User, List<Split>> StorageSplits;
	public static SplitCatalog instance;

	private SplitCatalog() {
		StorageSplits = new HashMap<User, List<Split>>();
	}

	public ArrayList<Split> getUserSplits(User currentUser)  {
		System.out.println("cuurnte "+ StorageSplits);
		ArrayList<Split> arraySplit = new ArrayList<Split>();
		List<Split> ListaDoUser = StorageSplits.get(currentUser);
		System.out.println("ListaDoUser "+ ListaDoUser);
		if(ListaDoUser != null) {
			for (Split sp : ListaDoUser) {
				arraySplit.add(sp);
			}
		}
		return arraySplit;
	}
	public Map<User, List<Split>> getSplits() {
		return StorageSplits;
	}

	public void addAnotherUserSplit(User u, Split sp) {
		ArrayList<Split> splitsFromUser = new ArrayList<>();
		if(getUserSplits(u) != null) {
			splitsFromUser = getUserSplits(u);
		}else {
			splitsFromUser = new ArrayList<Split>();
		}
		splitsFromUser.add(sp);
		StorageSplits.put(sp.getOwner(), splitsFromUser);
		System.out.println("sss "+StorageSplits);
	}
	public Split getSplitUserById(User u, int id) {
		List<Split> splitsUser = StorageSplits.get(u);
		Split splituser = null;
		for(Split s: splitsUser) {
			if(id == s.getId()) {
				splituser = s;
			}
		}
		if(splituser ==null) {
			System.out.println("There is no number associated with that event");
		}
		return splituser;
	}

	public static SplitCatalog getInstance() {
		boolean testeInstance = Objects.isNull(instance);// Class objectos java 8
		if (testeInstance) {
			return new SplitCatalog();
		} else {
			return instance;
		}
	}

	public void save() throws IOException {
		fcul.pco.eurosplit.persistence.SplitCatalog.save(StorageSplits);
	}

	public void load() throws IOException {
		StorageSplits = fcul.pco.eurosplit.persistence.SplitCatalog.load();
	}

}
