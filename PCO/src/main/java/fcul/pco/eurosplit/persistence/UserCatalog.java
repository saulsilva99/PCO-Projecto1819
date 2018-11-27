package fcul.pco.eurosplit.persistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import fcul.pco.eurosplit.domain.User;
import fcul.pco.eurosplit.main.ApplicationConfiguration;

public class UserCatalog {
	/*
	 * Este metodo guarda para um ficheiro "user.dat" 
	 * os utilizadores que existem no dicionário em string
	 * com o formato:
	 * -> "Name: nomeDaPessoa Email: emailDaPessoa"
	 */
	ApplicationConfiguration appConf = new ApplicationConfiguration();
	 
	
	public static void save(Map<String, User> users) throws IOException {
		ApplicationConfiguration appConf = new ApplicationConfiguration();
		FileWriter writeToFile = new FileWriter(new File(appConf.ROOT_DIRECTORY));
		for (User u:  users.values()) {
			writeToFile.write(u.toString() + "\n");
		}
		writeToFile.close();
	}
	/*
	 * Objectivo de este metodo é ler um ficheiro users.dat
	 * e tratar da informação dos usuários.
	 * Ensures: Devolve um dicionário com uma key = email
	 * e value = User(nome,email)
	 */
	public static Map<String,User> load() throws FileNotFoundException {
		ApplicationConfiguration appConf = new ApplicationConfiguration();
		Map<String,User> mapUserFile = new HashMap<String, User>();
		Scanner inputFromFile = new Scanner(new File(appConf.ROOT_DIRECTORY));
		while(inputFromFile.hasNextLine()) {
			
			String linha = inputFromFile.nextLine();
			
			//transformações para usar no Objecto User
			String nome = linha.split("-")[1];
			String email = linha.split("-")[0];
			User user = new User(nome,email);

			user.fromString(user.toString()); // Ai está a razão do FromString()
			mapUserFile.put(user.getEmail(),user);
		}
		return mapUserFile;
	}

}
