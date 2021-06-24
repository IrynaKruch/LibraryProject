package ua.krucheniuk.entity;

public class User implements Cloneable{
	
    private int id;
    private String fullname;
    private String email;
    private String login;
    private String password;

    public enum ROLE {
        READER, ADMIN, LIBRARIAN, BLOCKED, UNKNOWN
    }

    private ROLE role;
    
    
	public User() {
		
	}

	public User(String fullname, String email, String login, String password) {
		this.fullname = fullname;
		this.email = email;
		this.login = login;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role.toString();
	}

	public void setRole(String role) {
	switch (role) {
	case "ADMIN":
		this.role = ROLE.ADMIN;
		break;
	case "LIBRARIAN":
		this.role = ROLE.LIBRARIAN;
		break;
	case "READER":
		this.role = ROLE.READER;
		break;
	case "BLOCKED":
		this.role = ROLE.BLOCKED;
		break;
	default:
		this.role = ROLE.UNKNOWN;
		}
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", fullname=" + fullname + ", email=" + email + ", login=" + login + ", password="
				+ password + ", role=" + role + "]";
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
    
    
}
