package fcul.pco.persistence;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import fcul.pco.eurosplit.domain.User;

public class UserCatalog {
	/*
	 * Este metodo guarda para um ficheiro "user.dat"
	 * os utilizadores requeridos.
	 * 
	 */
	public static void save(Map<String, User> users) throws IOException {
		try {
			FileWriter writeToFile = new FileWriter(new File("pseudoBD/user.dat"));
			writeToFile.write("ola");
		}
		catch(IOException e){
			System.out.println("Caminho para o ficheiro não existe.");
		}
		
		
		
	}
	//public static Map<String,User> load(){
		
	//}
 

}
