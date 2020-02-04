package DBunit;

public class ExecuteUpdateException extends Exception {
	public ExecuteUpdateException() {
		System.err.println("Something wrong in updating!");
	}
}
