package fcul.pco.eurosplit.persistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import fcul.pco.eurosplit.domain.Expense;
import fcul.pco.eurosplit.domain.Split;
import fcul.pco.eurosplit.domain.User;
import fcul.pco.eurosplit.main.ApplicationConfiguration;
import fcul.pco.eurosplit.main.Start;

public class SplitCatalog {

	public static void save(Map<User, List<Split>> splits) throws IOException {
		FileWriter writeToFile = new FileWriter(new File(ApplicationConfiguration.ROOT_DIRECTORY_Split));
		for (List<Split> listsplit : splits.values()) {
			for (Split split : listsplit) {
				writeToFile.write(split.toString() + "\n");
			}
		}
		writeToFile.close();
	}

	public static Map<User, List<Split>> load() throws FileNotFoundException {

		Map<User, List<Split>> hashMap = new HashMap<>();
		ArrayList<Split> array_lista = new ArrayList<>();
		@SuppressWarnings("resource")
		Scanner inputFromFile = new Scanner(new File(ApplicationConfiguration.ROOT_DIRECTORY_Split));
		while (inputFromFile.hasNext()) {
			String linha = inputFromFile.nextLine();
			String listaArray[] = linha.split("-"); 

			
			User user_dado = Start.getUserCatalog().getUserById(listaArray[1]);
			Split split = new Split(user_dado, Integer.parseInt(listaArray[0]), listaArray[2]);
			array_lista.add(split);

			hashMap.put(user_dado, array_lista);
		}
		return hashMap;

	}

}
