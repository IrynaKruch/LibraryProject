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
import ua.krucheniuk.controller.command.SetBookAmountCommand;
import ua.krucheniuk.controller.command.SetRoleCommand;
import ua.krucheniuk.controller.command.SortingCommand;
import ua.krucheniuk.controller.command.UpdateUserCommand;


public class ServletHelper {
	
    private final static Logger log = Logger.getLogger(ServletHelper.class);

	private static Map<String, Command> commands = new HashMap<String, Command>();

	static {
		commands.put("register", new RegisterCommand());
		commands.put("catalogue", new HomePageCommand());
		commands.put("login", new LoginCommand());
		commands.put("logout", new LogoutCommand());
		commands.put("order", new OrderBookCommand());
		commands.put("searchBook", new SearchBookCommand());
		commands.put("showOrders", new GetOrdersCommand());
		commands.put("processOrder", new GetBookToReaderCommand());
		commands.put("readerInfo", new ReadersBooksCommand());
		commands.put("setBookAmount", new SetBookAmountCommand());
		commands.put("returnBook", new ReturnBookCommand());
		commands.put("sortBooks", new SortingCommand());
		commands.put("deleteBook", new DeleteBookCommand());
		commands.put("updateUser", new UpdateUserCommand());
		commands.put("readers", new ReadersCommand());
		commands.put("readerforAdmin", new ReaderForAdmin());
		commands.put("addBook", new AddBookCommand());
		commands.put("setRole", new SetRoleCommand());
		commands.put("noCommand", new NoCommand());

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
