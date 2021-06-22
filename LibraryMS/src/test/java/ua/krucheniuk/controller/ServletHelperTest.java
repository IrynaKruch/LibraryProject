package ua.krucheniuk.controller;

import java.util.HashMap;
import java.util.Map;

import org.junit.*;

import ua.krucheniuk.controller.command.Command;
import ua.krucheniuk.controller.command.HomePageCommand;
import ua.krucheniuk.controller.command.LoginCommand;
import ua.krucheniuk.controller.command.NoCommand;
import ua.krucheniuk.controller.command.RegisterCommand;

public class ServletHelperTest {
	
	private static Map<String, Command> commands = new HashMap<String, Command>();

	static {
		commands.put("register", new RegisterCommand());
		commands.put("catalogue", new HomePageCommand());
		commands.put("login", new LoginCommand());
		}
	
	
	@Test
	public final void testGet() {
		Assert.assertTrue(ServletHelper.get("register") instanceof RegisterCommand);
	}
	
	@Test
	public final void noTestGet() {
		Assert.assertTrue(ServletHelper.get("something") instanceof NoCommand);
	}

}
