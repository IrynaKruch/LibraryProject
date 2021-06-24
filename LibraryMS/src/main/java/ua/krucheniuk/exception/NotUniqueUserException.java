package ua.krucheniuk.exception;

public class NotUniqueUserException extends Exception {

	private static final long serialVersionUID = 1L;
	
    private String loginData;

    public String getLoginData() {
        return loginData;
    }

    public NotUniqueUserException(String message, String loginData) {
        super(message);
        this.loginData = loginData;
    }


}
