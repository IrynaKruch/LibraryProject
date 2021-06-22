package ua.krucheniuk.constants;

import java.util.ResourceBundle;

public class Messages {


    private static final String BUNDLE_NAME = "locale";
    private static final String EN = "en";
    private static final String UA  ="ua";
    private static final ResourceBundle MESSAGE_UA = ResourceBundle.getBundle(BUNDLE_NAME+"_"+UA);
    private static final ResourceBundle MESSAGE_EN = ResourceBundle.getBundle(BUNDLE_NAME+"_"+EN);

//    public static final String SERVLET_EXECPTION = "message.servletException";
//    public static final String IO_EXCEPTION = "message.IOexception";
    public static final String LOG_ERROR = "message.logError";
    public static final String EMAIL_ERROR="message.emailError";
    public static final String NAME_ERROR="message.nameError";
    public static final String LOGIN_ERROR="message.loginError";
    public static final String PASSWORD_ERROR="message.passwordError";
    public static final String REGISTER_SUCCESS = "message.registerSuccess";
    public static final String LOGIN_REPEAT = "message.loginRepeat";
    public static final String NO_COMMAND ="message.noCommand";
    public static final String ACCESS_DENIED = "message.accessDenied";
    public static final String UPDATE_USER = "message.updateUser";
    public static final String CANT_UPDATE_USER = "message.cantUpdateUser";
    public static final String ADDBOOK_SUCCESS = "message.addBookSuccess";  
    public static final String ADDBOOK_FAILER = "message.addBookFailer";
    public static final String ORDER_SUSSESS ="message.orderSuccess";
//    public static final String BOOK_ORDERED ="message.bookOrdered";
    public static final String BOOK_DELETED ="message.bookDeleted";
    public static final String CANT_DELETE_BOOK ="message.cannotDeleteBook";
//    public static final String CANT_DELETE_USER ="message.cannotDeleteUser";
//    public static final String USER_NEED_RETURN_BOOKS = "message.userNeedReturnBooks";
//    public static final String DAYS_OVERDUE ="tag.daysOverdue" ;
//    public static final String DAYS_LEFT="tag.daysLeft";


    public static ResourceBundle getInstance(String locale) {
        if (locale==null||locale.equals(EN)) {
            return MESSAGE_EN;
        }
        return MESSAGE_UA;
    }
}
