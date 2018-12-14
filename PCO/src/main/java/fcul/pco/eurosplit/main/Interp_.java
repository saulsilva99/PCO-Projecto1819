package fcul.pco.eurosplit.main;

import fcul.pco.eurosplit.domain.Expense;
import fcul.pco.eurosplit.domain.Split;
import fcul.pco.eurosplit.domain.User;
import fcul.pco.eurosplit.domain.Date;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author tl
 */
public class Interp_ {

	/**
	 * Contains the string that is correspond to interpreter's prompt. It is printed
	 * on the console. The prompt is updated by the setPrompt() method.
	 */
	private String prompt;
	/**
	 * The input of the interpreter
	 */
	private final Scanner input;
	/**
	 * Contains the current user (after user creation or after login).
	 */
	private User currentUser;

	/**
	 * Contains the current Split
	 */
	private Split currentSplit;

	private int contadorExpenses = Start.getExpenseCatalog().getAllExpenses();

	/**
	 *
	 * @param input
	 */
	public Interp_(Scanner input) {
		prompt = ApplicationConfiguration.DEFAULT_PROMPT;
		this.input = input;
	}

	/**
	 * Main interpreter command
	 *
	 * @param command
	 * @param input
	 */
	public void execute(String command, Scanner input) {
		switch (command) {
		case "help":
			help();
			break;
		case "new user":
			makeNewUser(input);
			break;
		case "show users":
			showUsers();
			break;
		case "login":
			login(input);
			break;
		case "new split":
			makeNewSplit(input);
			break;
		case "select split":
			selectSplit(input);
			break;
		case "new expense":
			makeNewExpense(input);
			break;
		case "balance":
			printBalance();
			break;
		case "quit":
			quit();
			break;
		default:
			System.out.println("Unknown command. [" + command + "]");
		}
	}
	
	/**
	 * comando para mostrar aos utilizadores quais as opcoes disponiveis
	 */
	private void help() {
		System.out.println("help: show commands information.");
		System.out.println("new user: create a new account.");
		System.out.println("show users: show the list of registred users.");
		System.out.println("new split: create a new split.");
		System.out.println("select split: select a split.");
		System.out.println("new expense: add an expense to current split.");
		System.out.println("balance: print the balance of the current split.");
		System.out.println("login: log a user in.");
		System.out.println("quit: terminate the program.");
	}
	
	/**
	 * Criacao de uma String com as creddencias do User com: 
	 * "usarname-nome"
	 * @param input
	 */
	private void makeNewUser(Scanner input) {
		System.out.print("User Name: ");
		String nome = input.nextLine();
		System.out.print("Email Address: ");
		String username = input.nextLine();
		String total = username + "-" + nome;

		makeNewUser(input, total);
	}
	
	/**
	 * Guarda os catalogos Todos para os respetivos ficheiros.
	 */
	private void quit() {
		save();
	}

	/**
	 * Mostra os utilizadores correntes que estao de momento no catalogo 
	 * que por sua vez também estão no ficheiro.
	 */
	private void showUsers() {
		List<User> usersArray = Start.getUserCatalog().usersToArray();
		List<List<String>> tabela = new ArrayList<List<String>>();
		for (User u : usersArray) {
			ArrayList<String> linhaTabela = new ArrayList<String>();
			linhaTabela.add(u.getName());
			linhaTabela.add(u.getEmail());
			tabela.add(linhaTabela);
		}
		System.out.println(fcul.pco.eurosplit.domain.Table.tableToString(tabela));
	}

	/**
	 * Autenticacao do Usuario
	 * Usuario tem de inserir um nome existe e email Existente.
	 * @param input
	 */
	private void login(Scanner input) {

		System.out.print("Username: ");
		String username = input.nextLine();
		User user = Start.getUserCatalog().getUserWithNameSingle(username);
		if (!(user == null)) {
			System.out.print("Emaill: ");
			String email = input.nextLine();
			currentUser = Start.getUserCatalog().getUserById(email);
			currentSplit = null;
			if (currentUser == null) {
				System.out.println("User not found.");
			}
		} else {
			System.out.println("User not found.");
		}
		setPrompt();
	}
	
	/**
	 * 
	 * Cricao de um evento que esta associado a uma instancia Split.
	 * 
	 * @param input
	 */
	private void makeNewSplit(Scanner input) {
		if (!(currentUser == null)) {
			int UserSplits = Start.getSplitCatalog().getUserSplits(currentUser).size() + 1;
			currentSplit = new Split(currentUser);
			System.out.println("For what event is this split ? (i.e. «trip to Madrid», «house expenses», etc...");
			String evento = createAnotherSplit(input, input.nextLine(),currentUser);
			System.out.println("evento "+evento);
			currentSplit.setEvent(evento);
			currentSplit.setidSplits(UserSplits);
			Start.getSplitCatalog().addAnotherUserSplit(currentUser, currentSplit);
			save();
			setPrompt();
		}
	}
	
	/**
	 * Verificar se determinado split ja existe,caso contrario
	 * devolve a string que foi introduzida pelo Utilizador.
	 * @param input
	 * @param EventAssociated
	 * @param currentUser
	 * @return Devolve uma String que nao existe nos splits de um User.
	 */
	private String createAnotherSplit(Scanner input, String EventAssociated,User currentUser) {
		String evento = EventAssociated;
		if(Start.getSplitCatalog().checkSplitExist(EventAssociated,currentUser)) {
			System.out.println("Event with name [" + EventAssociated +"] already exists in your splits.");
			System.out.println("For what event is this split ? (i.e. «trip to Madrid», «house expenses», etc...");
			String Description = input.nextLine();
			createAnotherSplit(input,Description,currentUser);
		}
		return evento;
	}
	/**
	 * Utilizador pode escolher o numero de um do(s) Split(s) que criou anteriormente. 
	 * Porem e necessario que ja tenha criado pelo menos um Split.
	 * 
	 * @param input
	 */
	private void selectSplit(Scanner input) {
		if (!getPrompt().equals("EuroSplit")) {
			int numeroDeSplits = 0;
			System.out.print("Name of split’s owner ? ");
			String name = input.nextLine();

			User u = selectUser(input, name);
			ArrayList<Split> userSplits = Start.getSplitCatalog().getUserSplits(u);
			for (Split s : userSplits) {
				System.out.println(s.getIdSplits() + " " + s.getPurpose());
				numeroDeSplits += 1;
			}
			if (!(numeroDeSplits == 0)) {
				System.out.println("Select a split number:");
				int numeroEscolhido = Integer.parseInt(input.nextLine());
				currentSplit = Start.getSplitCatalog().getSplitUserById(u, numeroEscolhido);
			} else {
				System.out.println(u.getName() + " has have no split yet.");
			}
		} else {
			System.out.println("You must be logged in.");
			login(input);
		}

		save();
		setPrompt();
	}
	
	/**
	 * Calcular o balanco corrente de despesas associadas
	 * a determinado evento.
	 */
	private void printBalance() {
		int currentId = currentSplit.getId();
		Start.getExpenseCatalog().setMapExpensesFromIdSplit(currentId);
		Start.getExpenseCatalog().setMapExpensesFromIdSplit(currentId);
		Map<Integer, List<Expense>> mapExpenses = Start.getExpenseCatalog().getMapExpensesFromIdSplit();
		Map<String, Integer> valores = new HashMap<>();
		int resto = 0;
		for (List<Expense> listaExp : mapExpenses.values()) {
			for (Expense exp : listaExp) {
				int total = (int) (exp.getDespesaValor());
				int totalBeneficiarios = exp.getPaidFor().size();
				int mediaMath = Math.floorDiv(total, totalBeneficiarios);
				int mediaResto = total % totalBeneficiarios;
				ArrayList<User> listOfUsers = exp.getPaidFor();
				String paidUser = exp.getUserPaidBy().getName();

				for (User user : listOfUsers) {
					String name = user.getName();
					if (valores.get(name) == null) {
						valores.put(name, 0); // inicializar a zero os valores
					}
					int oldPrice = valores.get(name);
					int newPrice;

					if (!(paidUser.equals(name))) {
						newPrice = oldPrice + (-1 * mediaMath);
					} else {
						newPrice = oldPrice + ((-1 * mediaMath) + total);
					}
					valores.put(name, newPrice);
				}
				resto = resto + mediaResto; // tentou usar a conatacao resto +=media, mas dava erro de variavel nao usada.
				int val = 0;
				// Nesta zona sao feitos os calculos para atribuir
				// aleatoriamente o resto.
				//
				while (val < 2) {
					int restoExpense = mediaResto / 2;
					User u = addRestToUserBalance(mediaResto / 2, listOfUsers);
					int userBalance = valores.get(u.getName()) - (restoExpense);

					valores.put(u.getName(), userBalance);
					val += 1;
				}
			}
			setPrompt();
			save();
		}
		try {
			System.out.println(fcul.pco.eurosplit.domain.Table.tableToString(changeMapToListLists(valores)));
		} catch (IndexOutOfBoundsException e) {
			System.out.println("there is no expenses to show balance.");
		}
	}
	
	/**
	 * Transformacao de um atributo de Map(String,Integer) que com usuario e saldo
	 * para ser usado no metodo tableToString.
	 * @param balanceUser
	 * @return Lista de Listas de String. A Lista de Strings sera composto pelo nome
	 * e saldo.
	 */
	public List<List<String>> changeMapToListLists(Map<String, Integer> balanceUser) {
		List<List<String>> tabela = new ArrayList<List<String>>();
		for (Map.Entry<String, Integer> entry : balanceUser.entrySet()) {
			List<String> linha = new ArrayList<>();
			linha.add(entry.getKey());
			linha.add(entry.getValue().toString());
			tabela.add(linha);
		}
		return tabela;

	}

	/**
	 * Tem como funcao remover ao saldo dos utilizadores o que sobra da divisao do
	 * preco total pelo numero de funcionarios a dividir por dois.
	 * 
	 * @param -resto - Numero Inteiro divido por 2
	 * @param -listOfUsers - ArrayList de utilizadores a escolher
	 * @return - User escolhido pelo metodo
	 */
	public User addRestToUserBalance(int resto, ArrayList<User> listOfUsers) {
		Random randomGenerator = new Random();
		int index = randomGenerator.nextInt(listOfUsers.size());
		return listOfUsers.get(index);
	}

	private void save() {
		try {
			Start.getUserCatalog().save();
		} catch (IOException ex) {
			System.err.println("Error saving User Catalog.");
		}
		try {
			Start.getExpenseCatalog().save();
		} catch (IOException ex) {
			System.err.println("Error saving Expense Catalog.");
		}
		try {
			Start.getSplitCatalog().save();
		} catch (IOException ex) {
			System.err.println("Error saving Split Catalog.");
		}
	}

	private void makeNewExpense(Scanner input) {
		if (!(currentSplit == null)) {
			Date dt = Date.now();

			System.out.print("Expense made by you (" + currentUser.getName() + ") What did you pay for ? ");
			String descriptionPaid = input.nextLine();
			System.out.print("How much did you pay ? («no one» to terminate)");
			double price = Double.parseDouble(input.nextLine());
			String namePaid = "";
			Expense despesa = new Expense(descriptionPaid, price, currentUser, dt);
			despesa.addPaidFor(currentUser);
			despesa.setIdSplit(currentSplit.getId());
			while (!(namePaid.equals("no one"))) {
				System.out.print("Who did you pay for: («no one» to terminate)");
				namePaid = input.nextLine();
				if (!(currentUser.equals(namePaid))) {

					if (!(namePaid.equals("no one"))) {
						User u = selectUser(input, namePaid);
						if (u == null) {
							System.out.println("User not found.");
						}
						despesa.addPaidFor(u);
						Start.getExpenseCatalog().setId(contadorExpenses);
						currentSplit.addExpense(despesa);
						currentSplit.incExpense();

						contadorExpenses++;
					}
				}
			}
			Start.getExpenseCatalog().addExpense(despesa);

		} else {
			System.out.println("You need to have a Split.");
		}
		save();
		setPrompt();
	}

	public String getPrompt() {
		return prompt;
	}

	public void setPrompt() {
		if (currentUser == null) {
			this.prompt = ApplicationConfiguration.DEFAULT_PROMPT;
		} else if (currentSplit == null) {
			this.prompt = currentUser.getName();
		} else {
			this.prompt = currentUser.getName() + "." + currentSplit.getPurpose();
		}
	}

	String nextToken() {
		String in;
		System.out.print(prompt + "> "); // foi alterado
		System.out.flush();
		if (input.hasNextLine()) {
			in = input.nextLine();
			return in;
		} else {
			return "";
		}
	}

	private void makeNewUser(Scanner input, String name) {
		String email = name.split("-")[0];
		String nome = name.split("-")[1];
		try {
			Start.getUserCatalog().checkUserExistsById(email);
			System.out.println("A user with this email address already exists.");
		} catch (NullPointerException e) {
			currentUser = new User(nome, email);
			Start.getUserCatalog().addUser(currentUser);
			save();
			prompt = nome;
			System.out.print(prompt + "> ");
			String word = input.nextLine();
			execute(word, input);
		}
	}

	private User selectUser(Scanner input, String name) {
		List<User> list = Start.getUserCatalog().getUsersWithName(name);
		if (list.size() == 1)
			return list.get(0);
		int k;
		if (!list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				System.out.println(i + " " + list.get(i));
			}
			System.out.print("Select a user: ");
			k = Integer.parseInt(input.nextLine());
		} else {
			System.out.println("User not found.");
			System.out.print("Name: ");
			name = input.nextLine();
			return selectUser(input, name);
		}
		return list.get(k);
	}
	
	
	/**
	 * This method may be used to find a user in the catalog given its name, for
	 * example when we want to add "paidFor" users to an expense. The method
	 * receives a name. If there is only one user with this name, return that user.
	 * If there is no user with that name, give the opportunity to create a new
	 * user. The several users (with same name) are found, show the list and ask
	 * which one should be used.
	 *
	 * @param input
	 * @param name
	 * @return
	 */
	/* ---------------- NAO E USADO
	private User selectOrCreateUser(Scanner input, String name) {
		ArrayList<User> list = Start.getUserCatalog().getUsersWithName(name);
		if (list.isEmpty()) {
			System.out.println("There is no registred user with name " + name + ".");
			if (askYNQuestion(input, "Do you want to create user " + name)) {
				User theUser = currentUser;
				makeNewUser(input, name); // <-- write this method
				User newUser = currentUser;
				currentUser = theUser;
				setPrompt();
				return newUser;
			} else {
				// ask again:
				System.out.println("pff... Who did you pay for: ");
				return selectOrCreateUser(input, input.nextLine());
			}
		} else if (list.size() == 1) {
			return list.get(0);
		} else {
			int i = 0;
			for (User u : list) {
				System.out.print("(" + i + ") " + u.getName() + "[" + u.getEmail() + "] - ");
				i++;
			}
			System.out.println("Which " + name + " ? ");
			i = input.nextInt();
			return list.get(i);
		}
	}*/

	/* -----------Nao e usado
	private boolean askYNQuestion(Scanner input, String question) {
		System.out.print(question + "? (y/n):");
		String answer = input.nextLine();
		while (!(answer.equalsIgnoreCase("Y") || answer.equalsIgnoreCase("N"))) {
			System.out.print(question + "? (y/n):");
			answer = input.nextLine();
		}
		return answer.equalsIgnoreCase("Y");
	}*/

}
