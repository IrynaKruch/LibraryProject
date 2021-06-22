package ua.krucheniuk.exception;

public class WrongInputException extends Exception {

	private static final long serialVersionUID = 1L;

		private String input;
		
		public String getInputData() {
			return input;
		}
	public WrongInputException ( String input) {
		this.input = input;
		
	}
	
}
