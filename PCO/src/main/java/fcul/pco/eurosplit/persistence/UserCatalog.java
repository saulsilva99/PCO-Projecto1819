package fcul.pco.eurosplit.persistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import fcul.pco.eurosplit.domain.User;

public class UserCatalog {
	/*
	 * Este metodo guarda para um ficheiro "user.dat" 
	 * os utilizadores que existem no dicionário em string
	 * com o formato:
	 * -> "Name: nomeDaPessoa Email: emailDaPessoa"
	 */
	public static void save(Map<String, User> users) throws IOException {
		FileWriter writeToFile = new FileWriter(new File("./users.dat"));
		for (Map.Entry<String, User> entry : users.entrySet()) {
			writeToFile.write("Name: " + entry.getValue().getName().toString());
			writeToFile.write(" Email: " + entry.getValue().getEmail().toString());
			writeToFile.write("\n");
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
		
		Map<String,User> mapUserFile = new HashMap<String, User>();
		Scanner inputFromFile = new Scanner(new File("./users.dat"));
		while(inputFromFile.hasNextLine()) {
			User user = new User(); // tive de criar um constructor vazio é necessário?
			String linha = inputFromFile.nextLine();
			
			user.fromString(linha); // Ai está a razão do FromString()
			System.out.println(user.toString());
			mapUserFile.put(user.getEmail(),user);
		}
		return mapUserFile;

	}

}
