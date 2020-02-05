package DBUnit;

public class ExecuteDeleteException extends Exception {
	public ExecuteDeleteException() {
		System.err.println("Something wrong in deleting!");
	}
}
