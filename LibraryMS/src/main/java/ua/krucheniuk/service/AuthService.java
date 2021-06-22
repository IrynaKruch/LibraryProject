package ua.krucheniuk.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import ua.krucheniuk.dao.model.DaoFactory;
import ua.krucheniuk.entity.User;

public class AuthService {
	
    private static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private static final String  VALID_NAME_REGEX="[A-Za-zА-Яа-яІіЄєЇї']{3,30}( [A-Za-zА-Яа-яІіЄєЇї']{3,30})?";
    private static final String  VALID_LOGIN_REGEX="[A-Za-zА-Яа-яІіЄєЇї']{3,30}";
    private static final String VALID_PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{5,}$";
    private static final String  VALID_YEAR = "[0-9]{4}";
    private static final String VALID_NUMBER = "[0-9]{1,3}";

    
    private static final Logger log=Logger.getLogger(AuthService.class);

	
	
	private static volatile AuthService authService;

	DaoFactory factory = DaoFactory.getInstance();

	private AuthService(){

    }

    public static AuthService getInstance() {
    	AuthService localInstance = authService;
        if (localInstance == null) {
            synchronized (AuthService.class) {
                localInstance = authService;
                if (localInstance == null) {
                	authService = localInstance = new AuthService();
                }
            }
        }
        return localInstance;
    }

    public User login(String login, String password){
        return factory.createUserDao().login(login,password);
    }

    public String register(User user){
        return factory.createUserDao().create(user);
    }
	
    public String update(User user){
        return factory.createUserDao().update(user);
    }	
    
    public boolean checkName(String name){
        return name.matches(VALID_NAME_REGEX);
    }
    
    public boolean checkLogin(String login){
        return login.matches(VALID_LOGIN_REGEX);
    }
    
    public boolean checkPassword(String password){
        return password.matches(VALID_PASSWORD_REGEX);
    }

   
    public boolean checkEmail(String email){
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(email);
        return matcher.find();
    }
    public boolean checkYear(String year){
        return year.matches(VALID_YEAR);

    }
    public boolean checkQuantity(String quantity){
        return quantity.matches(VALID_NUMBER);

    }

	
}
