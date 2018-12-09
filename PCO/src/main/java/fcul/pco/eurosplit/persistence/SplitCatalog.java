	package fcul.pco.eurosplit.persistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import fcul.pco.eurosplit.domain.Expense;
import fcul.pco.eurosplit.domain.Split;
import fcul.pco.eurosplit.domain.User;
import fcul.pco.eurosplit.main.ApplicationConfiguration;

public class SplitCatalog {

	public static void save(Map<User, List<Split>> splits) throws IOException {
		FileWriter writeToFile = new FileWriter(new File(ApplicationConfiguration.ROOT_DIRECTORY_Split));
		for (List<Split> split : splits.values()) {
			writeToFile.write(split.toString() + "\n");
		}
		writeToFile.close();
	}

	public static Map<User, List<Split>> load() throws FileNotFoundException {
		// 0-kaka-aklsdjaskljd-0:0
		Map<User, List<Split>> splits = new HashMap<User, List<Split>>();
		Scanner inputFromFile = new Scanner(new File(ApplicationConfiguration.ROOT_DIRECTORY_Split));
		while (inputFromFile.hasNextLine()) {
			
			String linha = inputFromFile.nextLine();
			int id = Integer.parseInt(linha.split("-")[0]);
			String nome = linha.split("-")[1];
			String email = linha.split("-")[2];
			User user = new User(nome, email);

			user.fromString(user.toString()); 
			//splits.put(user.getEmail(), user);
		}
		return null;
	}

}
