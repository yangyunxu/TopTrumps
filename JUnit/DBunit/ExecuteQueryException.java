package DBunit;

public class ExecuteQueryException extends Exception {
	public ExecuteQueryException() {
		System.err.println("Something wrong in querying!");
	}
}
