package ua.krucheniuk.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import ua.krucheniuk.controller.command.AddBookCommand;
import ua.krucheniuk.controller.command.Command;
import ua.krucheniuk.controller.command.DeleteBookCommand;
import ua.krucheniuk.controller.command.GetBookToReaderCommand;
import ua.krucheniuk.controller.command.GetOrdersCommand;
import ua.krucheniuk.controller.command.HomePageCommand;
import ua.krucheniuk.controller.command.LoginCommand;
import ua.krucheniuk.controller.command.LogoutCommand;
import ua.krucheniuk.controller.command.NoCommand;
import ua.krucheniuk.controller.command.OrderBookCommand;
import ua.krucheniuk.controller.command.ReaderForAdmin;
import ua.krucheniuk.controller.command.ReadersBooksCommand;
import ua.krucheniuk.controller.command.ReadersCommand;
import ua.krucheniuk.controller.command.RegisterCommand;
import ua.krucheniuk.controller.command.ReturnBookCommand;
import ua.krucheniuk.controller.command.SearchBookCommand;
import ua.krucheniuk.controller.command.SearchOrderCommand;
import ua.krucheniuk.controller.command.SetBookAmountCommand;
import ua.krucheniuk.controller.command.SetRoleCommand;
import ua.krucheniuk.controller.command.SortingCommand;
import ua.krucheniuk.controller.command.UpdateUserCommand;
import ua.krucheniuk.service.AuthService;
import ua.krucheniuk.service.LibrarianService;
import ua.krucheniuk.service.OrderService;
import ua.krucheniuk.service.SearchService;

public class ServletHelper {

	private final static Logger log = Logger.getLogger(ServletHelper.class);

	private static Map<String, Command> commands = new HashMap<String, Command>();

	static {
		commands.put("register", new RegisterCommand(new AuthService()));
		commands.put("catalogue", new HomePageCommand(new LibrarianService()));
		commands.put("login", new LoginCommand(new AuthService()));
		commands.put("logout", new LogoutCommand());
		commands.put("order", new OrderBookCommand(new OrderService()));
		commands.put("searchBook", new SearchBookCommand(new SearchService(), new AuthService()));
		commands.put("showOrders", new GetOrdersCommand(new LibrarianService()));
		commands.put("processOrder", new GetBookToReaderCommand(new LibrarianService()));
		commands.put("readerInfo", new ReadersBooksCommand(new OrderService(), new LibrarianService()));
		commands.put("setBookAmount", new SetBookAmountCommand(new LibrarianService()));
		commands.put("returnBook", new ReturnBookCommand(new LibrarianService()));
		commands.put("sortBooks", new SortingCommand(new SearchService()));
		commands.put("deleteBook", new DeleteBookCommand(new LibrarianService()));
		commands.put("updateUser", new UpdateUserCommand(new AuthService()));
		commands.put("readers", new ReadersCommand(new LibrarianService()));
		commands.put("readerforAdmin", new ReaderForAdmin(new LibrarianService(), new OrderService()));
		commands.put("addBook", new AddBookCommand(new LibrarianService(), new AuthService()));
		commands.put("setRole", new SetRoleCommand(new AuthService(), new LibrarianService()));
		commands.put("noCommand", new NoCommand());
		commands.put("searchOrders", new SearchOrderCommand(new SearchService(), new LibrarianService()));

	}

	public static Command get(String commandName) {
		Command command = commands.get(commandName);
		if (commandName == null || !commands.containsKey(commandName)) {
			log.info("Command not found, name --> " + commandName);
			return commands.get("noCommand");
		}

		return command;
	}

}
