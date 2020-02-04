package DBunit;

public class ExecuteInsertException extends Exception {
	public ExecuteInsertException() {
		System.err.println("Something wrong in inserting!");
	}
}
