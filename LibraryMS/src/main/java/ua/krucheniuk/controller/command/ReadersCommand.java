package ua.krucheniuk.controller.command;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.krucheniuk.constants.Path;
import ua.krucheniuk.entity.Order;
import ua.krucheniuk.entity.User;
import ua.krucheniuk.service.LibrarianService;


public class ReadersCommand implements Command {
	
    LibrarianService librarianService;

	public ReadersCommand(LibrarianService librarianService) {
		this.librarianService = librarianService;
	}


	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		List <User> readers = librarianService.getInfoAboutAllReaders();
		request.setAttribute("readers", readers);
		List<Order> currentOrders = librarianService.getHandledBookOrders();
		Map<Integer, Integer> readerDebt = librarianService.countDebt(currentOrders);
		request.setAttribute("readerDebt", readerDebt);
		
        return Path.READERS_PAGE;
    }
	

}
