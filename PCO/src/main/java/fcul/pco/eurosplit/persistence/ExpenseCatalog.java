package fcul.pco.eurosplit.persistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import fcul.pco.eurosplit.domain.Date;
import fcul.pco.eurosplit.domain.Expense;
import fcul.pco.eurosplit.domain.User;
import fcul.pco.eurosplit.main.ApplicationConfiguration;
import fcul.pco.eurosplit.main.Start;

public class ExpenseCatalog {
	/*
	 * Este metodo guarda para um ficheiro "expenses.dat" 
	 * as despesas que existem no dicionário em Integer
	 * com o formato:
	 * "emailDaPessoa-nomeDaPessoa"
	 */
	public static void save(Map<Integer, Expense> expenses) throws IOException {
		FileWriter writeToFile = new FileWriter(new File(ApplicationConfiguration.ROOT_DIRECTORY_Expenses));
		System.out.println("expensessss " + expenses.toString());
		for (Expense expense:  expenses.values()) {
			writeToFile.write(expense.toString() + "\n");
		}
		writeToFile.close();
	}
	
	@SuppressWarnings("null")
	public static Map<Integer, Expense> load() throws FileNotFoundException {
		Date date;
		Map<Integer,Expense> mapExpenseFile = new HashMap<>();
		@SuppressWarnings("resource")
		Scanner inputFromFile = new Scanner(new File(ApplicationConfiguration.ROOT_DIRECTORY_Expenses));
		while(inputFromFile.hasNextLine()) {
			
			String linha = inputFromFile.nextLine();
			
			//transformações para usar no Objecto Expense
			
			int id = Integer.parseInt(linha.split("-")[0]);
			String description = linha.split("-")[1];
			Double valor = Double.parseDouble(linha.split("-")[2]);
			String email = linha.split("-")[3];
			System.out.println("data " + linha.split("-")[4]);
			
			//date.fromString(linha.split("-")[4]);
			 
			 String dataString = linha.split("-")[4];
			 String[] arrayDate= dataString.split("/");
			 int year = Integer.parseInt(arrayDate[0]);
			int month = Integer.parseInt(arrayDate[1]);
			int day = Integer.parseInt(arrayDate[2]);
			int hours = Integer.parseInt(arrayDate[3]);
			int minuts = Integer.parseInt(arrayDate[4]);
			 date = new Date(year,month,day,hours,minuts);
			 
			 
			System.out.println(date);
			User u= Start.getUserCatalog().getUserById(email);
			Expense expense = new Expense(description,valor,u,date);
			mapExpenseFile.put(id,expense);
	
		}
		return mapExpenseFile;

	}
	
}
