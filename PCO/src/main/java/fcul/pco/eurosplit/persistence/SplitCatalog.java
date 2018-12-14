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

import fcul.pco.eurosplit.domain.Split;
import fcul.pco.eurosplit.domain.User;
import fcul.pco.eurosplit.main.ApplicationConfiguration;
/**
 * @author: Saul Silva e Joao Paiva
 */
public class SplitCatalog {
	
	/**
	 * Este metodo tem como objectivo
	 * guarda por cada linha um Split num determinado ficheiro 
	 * no seguinte formato "id-email-descricao-idSplit:NumeroDespesasAssociadas";
	 * @param splits - Eh um dicionario de de lista de despesas Map<User, List<Split>>
	 */
	public static void save(Map<User, List<Split>> splits) throws IOException {
		FileWriter writeToFile = new FileWriter(new File(ApplicationConfiguration.ROOT_DIRECTORY_Split));
		for (List<Split> listsplit : splits.values()) {
			for (Split split : listsplit) {
				writeToFile.write(split.toString() + "\n");
			}
		}
		writeToFile.close(); 
	} 
 
	/**
	 * Objectivo de este metodo é ler um ficheiro e colocar numa instancia
	 * de Map para ser usada.
	 * @return: Devolve um dicionário com:
	 * key = User; 
	 * value = List de Split
	 */
	public static Map<User, List<Split>> load() throws FileNotFoundException {

		Map<User,List<Split>> mapSplitsFile = new HashMap<>();
		@SuppressWarnings("resource")
		Scanner inputFromFile = new Scanner(new File(ApplicationConfiguration.ROOT_DIRECTORY_Split));
		while (inputFromFile.hasNext()) {
			String linha = inputFromFile.nextLine();
			List<Split> splitsDoUser = new ArrayList<>();
			Split split = new Split();
			split = split.fromString(linha);
			
			if(mapSplitsFile.get(split.getOwner())==null) {
				splitsDoUser.add(split);
			}else {
				splitsDoUser = mapSplitsFile.get(split.getOwner());
				splitsDoUser.add(split);
			}

			mapSplitsFile.put(split.getOwner(), splitsDoUser);
		}
		return mapSplitsFile;

	}

}
