package DBUnit;

public class NotFoundDataException extends Exception {
	public NotFoundDataException() {
		System.err.println("No data was found!");
	}
}
