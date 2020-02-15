package basic;
import java.sql.*;
import java.util.ArrayList;


public class Database {
	protected static Connection conn = null;
	protected static Statement stmt = null;
	protected static ResultSet rs = null;

	
	//connect to the database
	public static void connection() {
		try {
			Class.forName("org.postgresql.Driver");
			// Class.forName("org.postgresql.Driver");
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
			return;
		}

		String url = "jdbc:postgresql://52.24.215.108:5432/Lifeordeath";
		String user = "Lifeordeath";
		String password = "Lifeordeath";

//		String url = "jdbc:postgresql://yacata.dcs.gla.ac.uk:5432/m_19_2479926y";
//		String user = "m_19_2479926y";
//		String password = "2479926y";
		
		try {
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.createStatement();
		}catch (Exception e) {
			System.err.println("Connection failed...");
			e.printStackTrace();
			return;
		}
		
	}
	
	//close 
	public static void close() {
		try{
			stmt.close();
			conn.close();
		}catch(Exception e) {
			System.err.println("Something wrong in closing connection");
			e.printStackTrace();
			return;
		}
	}
	
	//if table is not exist in the database, then create a table named record
	public static void createTable() {
		try{
			stmt.execute("create table if not exists record (Times integer,NumberOfMembers integer,NumberOfDraws integer,Winner varchar(64),Rounds integer,coreOfPlayerYou integer,ScoreOfPlayerAI1 integer,ScoreOfPlayerAI2 integer,ScoreOfPlayerAI3 integer,ScoreOfPlayerAI4 integer,primary key(Times));");
		}catch(Exception e) {
			System.err.println("Something wrong in creating table");
			e.printStackTrace();
			return;
		}
	}
	
	//insert data
	//data is an array including times, numberOfMembers, numberOfDraws, winner, rounds, 
	//	scoreOfPlayerYou, scoreOfPlayerAI1, scoreOfPlayerAI2, scoreOfPlayerAI3, scoreOfPlayerAI4
	public static void insert(ArrayList<Integer> data) {
		try {
			PreparedStatement preparedStatement = null;
			preparedStatement = conn.prepareStatement("insert into record values (?,?,?,?,?,?,?,?,?,? )");
			preparedStatement.setInt(1,data.get(0));
			preparedStatement.setInt(2,data.get(1));
			preparedStatement.setInt(3,data.get(2));
			switch (data.get(3)){
				case 0:
					preparedStatement.setString(4,"PlayerYou");
					break;
				case 1:
					preparedStatement.setString(4,"PlayerAI1");
					break;
				case 2:
					preparedStatement.setString(4,"PlayerAI2");
					break;
				case 3:
					preparedStatement.setString(4,"PlayerAI3");
					break;
				case 4:
					preparedStatement.setString(4,"PlayerAI4");
					break;
			}
			preparedStatement.setInt(5,data.get(4));
			preparedStatement.setInt(6,data.get(5));
			preparedStatement.setInt(7,data.get(6));
			preparedStatement.setInt(8,data.get(7));
			preparedStatement.setInt(9,data.get(8));
			preparedStatement.setInt(10,data.get(9));
			preparedStatement.execute();

		}catch(Exception e) {
			System.err.println("Something wrong in inserting data");
			e.printStackTrace();
			return;
		}
	}
	
	//Number of games
	public static int getNumberOfGames() {
		int numberOfGames = 0;
		try {
			rs = stmt.executeQuery("select count(*) from record");
			rs.next();
			numberOfGames = rs.getInt(1);
			return numberOfGames;
		}catch(Exception e) {
			System.err.println("Something wrong in getting number of games");
			e.printStackTrace();
			return numberOfGames;
		}
	}
	
	//Number of human wins
	public static int getNumberOfHumanWins() {
		int numberOfHumanWins = 0;
		try {
			rs = stmt.executeQuery("select count(*) from record where winner = 'PlayerYou'");
			rs.next();
			numberOfHumanWins = rs.getInt(1);
			return numberOfHumanWins;
		}catch(Exception e) {
			System.err.println("Something wrong in getting number of human wins");
			e.printStackTrace();
			return numberOfHumanWins;
		}
	}
	
	//Number of AI wins
	public static int getNumberOfAIWins() {
		int numberOfAIWins = 0;
		try {
			rs = stmt.executeQuery("select count(*) from record "
					+ "where winner = 'PlayerAI1'"
					+ "or winner = 'PlayerAI2'"
					+ "or winner = 'PlayerAI3'"
					+ "or winner = 'PlayerAI4'");
			rs.next();
			numberOfAIWins = rs.getInt(1);
			return numberOfAIWins;
		}catch(Exception e) {
			System.err.println("Something wrong in getting number of AI wins");
			e.printStackTrace();
			return numberOfAIWins;
		}
	}
	
	//average number of draws
	public static double getAverageNumberOfDraws() {
		double averageNumberOfDraws = 0.0;
		try {
			rs = stmt.executeQuery("select AVG(NumberOfDraws) from record");
			rs.next();
			averageNumberOfDraws = rs.getInt(1);
			return averageNumberOfDraws;
		}catch(Exception e) {
			System.err.println("Something wrong in getting average number of draws");
			e.printStackTrace();
			return averageNumberOfDraws;
		}
	}
	
	//longest game
	public static int getLongestGame() {
		int longestGame = 0;
		try {
			rs = stmt.executeQuery("select max(Rounds) from record");
			rs.next();
			longestGame = rs.getInt(1);
			return longestGame;
		}catch(Exception e) {
			System.err.println("Something wrong in getting longest game");
			e.printStackTrace();
			return longestGame;
		}
	}
	
	//query

	public static String printString() {
		String result = "Number of Games: " + getNumberOfGames() + "\n"
				+ "Number of Human Wins: " + getNumberOfHumanWins() + "\n"
				+ "Number of AI Wins: " + getNumberOfAIWins() + "\n"
				+ "Average Number of Draws: " + getAverageNumberOfDraws() + "\n"
				+ "Longest Game: " + getLongestGame() + "\n\n\n";
		return result;
	}
}


