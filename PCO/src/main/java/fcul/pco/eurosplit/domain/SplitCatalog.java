package fcul.pco.eurosplit.domain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class SplitCatalog {
	private Map<User, List<Split>> StorageSplits;
	private List<Split> listSplit;
	public static SplitCatalog instance;

	private SplitCatalog() {
		StorageSplits = new HashMap<User, List<Split>>();
		listSplit = new ArrayList<Split>();
	}

	public ArrayList<Split> getUserSplits(User currentUser) {
		ArrayList<Split> arraySplit = new ArrayList<Split>();
		List<Split> ListaDoUser = StorageSplits.get(currentUser);
		for (Split sp : ListaDoUser) {
			arraySplit.add(sp);
		}

		return arraySplit;
	}
	public Map<User, List<Split>> getSplits() {
		return StorageSplits;
	}

	public void addSplit(Split sp) {
		listSplit.add(sp);
		StorageSplits.put(sp.getOwner(), listSplit);
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
