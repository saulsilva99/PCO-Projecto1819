package fcul.pco.eurosplit.main;

import fcul.pco.eurosplit.domain.Expense;
import fcul.pco.eurosplit.domain.Split;
import fcul.pco.eurosplit.domain.User;
import fcul.pco.eurosplit.domain.UserCatalog;
import fcul.pco.eurosplit.domain.Date;
import fcul.pco.eurosplit.domain.Table;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

	private void makeNewUser(Scanner input) {
		System.out.print("User Name: ");
		String nome = input.nextLine();
		System.out.print("Email Address: ");
		String username = input.nextLine();
		String total = username + "-" + nome;

		makeNewUser(input, total);
	}

	private void quit() {
		save();
	}

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

	private void login(Scanner input) {

		System.out.print("Username: ");
		String username = input.nextLine();

		if (Start.getUserCatalog().getUserWithNameSingle(username) == null) {
			System.out.print("Emaill: ");
			String email = input.nextLine();
			currentUser = Start.getUserCatalog().getUserById(email);
			currentSplit = null;
			if (currentUser == null) {
				System.out.println("User not found.");
			}
		}
		setPrompt();
	}

	private void makeNewSplit(Scanner input) {
		if (!(currentUser == null)) {
			currentSplit = new Split(currentUser);
			System.out.println("For what event is this split ? (i.e. «trip to Madrid», «house expenses», etc...");
			String EventAssociated = input.nextLine();
			currentSplit.setEvent(EventAssociated);
			Start.getSplitCatalog().addAnotherUserSplit(currentUser, currentSplit);
			save();
			setPrompt();
		}

	}

	private void selectSplit(Scanner input) {
		if (!getPrompt().equals("EuroSplit")) {
			int numeroDeSplits = 0;
			System.out.print("Name of split’s owner ? ");
			String name = input.nextLine();
			User u = selectUser(input, name);
			ArrayList<Split> userSplits = Start.getSplitCatalog().getUserSplits(u);
			System.out.println("askldjasl "+ userSplits);
			for (Split s : userSplits) {
				System.out.println(s.getId() + "  " + s.getPurpose());
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
		setPrompt();
	}

	private void printBalance() {
		// TODO
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
		System.err.println("Error saving Expense Catalog."); }
		try {
			Start.getSplitCatalog().save();
		} catch (IOException ex) {
			System.err.println("Error saving Split Catalog.");
		}
	}

	private void makeNewExpense(Scanner input) {
		if (!(currentSplit == null)) {
			Date dt = Date.now();

			System.out.print("Expense made by you (" + currentUser + ") What did you pay for ?");
			String descriptionPaid = input.nextLine();
			System.out.print("How much did you pay ? («no one» to terminate)");
			double price = Double.parseDouble(input.nextLine());
			String namePaid = "";
			Expense despesa = new Expense(descriptionPaid, price,currentUser, dt);
		
			while (!(namePaid.equals("no one"))) {
				System.out.print("Who did you pay for: («no one» to terminate)");
				namePaid = input.nextLine();
				if (!(namePaid.equals("no one"))) {
					User u = selectUser(input, namePaid);
					despesa.addPaidFor(u);
					currentSplit.addExpense(despesa);
					Start.getExpenseCatalog().addExpense(despesa);
				}
			}
		} else {
			System.out.println("You need to have a Split.");
		}
	}

	public String getPrompt() {
		return prompt;
	}

	public void setPrompt() {
		if (currentUser == null) {
			this.prompt = ApplicationConfiguration.DEFAULT_PROMPT;
		}

		else if (currentSplit == null) {
			this.prompt = currentUser.getName();
		} else {
			this.prompt = currentUser.getName() + "." + currentSplit.getPurpose();
		}
	}

	String nextToken() {
		String in;
		System.out.print(prompt + "> ");
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
	}

	private boolean askYNQuestion(Scanner input, String question) {
		System.out.print(question + "? (y/n):");
		String answer = input.nextLine();
		while (!(answer.equalsIgnoreCase("Y") || answer.equalsIgnoreCase("N"))) {
			System.out.print(question + "? (y/n):");
			answer = input.nextLine();
		}
		return answer.equalsIgnoreCase("Y");
	}

}
