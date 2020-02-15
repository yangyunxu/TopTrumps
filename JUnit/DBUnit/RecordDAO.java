package DBUnit;
import java.sql.*;
import java.util.ArrayList;

import basic.Database;

public class RecordDAO {
	private static Connection connection = null;
	private static Statement statement = null;
	
	//basic method: update, delete, insert query
	//method to update 
		private Record re = null;

		
		//method to insert
		public static void insertRecord(Record re) 
				throws SQLException, ClassNotFoundException,ExecuteInsertException{
			Connection conn = null;
			try {
				conn = DBManager.getConnection();
				
				PreparedStatement pst = conn.prepareStatement("INSERT INTO record VALUES(?,?,?,?,?,?,?,?,?,?)");
				pst.setInt(1,re.getTimes());
				pst.setInt(2,re.getNumberOfMembers());
				pst.setInt(3,re.getNumberOfDraws());
				pst.setString(4,re.getWinner());
				pst.setInt(5,re.getRounds());
				pst.setInt(6,re.getScoreOfPlayerYou());
				pst.setInt(7,re.getScoreOfPlayerAI1());
				pst.setInt(8,re.getScoreOfPlayerAI2());
				pst.setInt(9,re.getScoreOfPlayerAI3());
				pst.setInt(10,re.getScoreOfPlayerAI4());
				
				if(pst.executeUpdate()<=0) {
					throw new ExecuteInsertException();
				}
			}finally {
				conn.close();
			}
		}
		
		//method to query
		public static Record queryRecord(int times) 
				throws SQLException, ClassNotFoundException,ExecuteQueryException,NotFoundDataException{
			Connection conn = null;
			try {
				conn = DBManager.getConnection();
				
				PreparedStatement pst = conn.prepareStatement("SELECT * FROM record WHERE times=?");
				pst.setInt(1,times);
				ResultSet su = pst.executeQuery();
				if(su.next()) {
					Record record = new Record();
					record.setTimes(su.getInt(1));
					record.setNumberOfMember(su.getInt(2));
					record.setNumberOfDraws(su.getInt(3));
					record.setWinner(su.getString(4));
					record.setRounds(su.getInt(5));
					record.setScoreOfPlayerYou(su.getInt(6));
					record.setScoreOfPlayerAI1(su.getInt(7));
					record.setScoreOfPlayerAI2(su.getInt(8));
					record.setScoreOfPlayerAI3(su.getInt(9));
					record.setScoreOfPlayerAI4(su.getInt(10));
					return record;
				}
				throw new NotFoundDataException();
			}finally {
				conn.close();
			}
		}
		
		//method used in the Class Database
		//connection in the Class Database
		public static void connection() 
				throws ClassNotFoundException, SQLException{
			Database.connection();
		}
		
		//close connection in the Class Database
		public static void close() 
				throws ClassNotFoundException, SQLException{
			Database.close();
		}
		
		//method insert in the Class Database is slightly different from the method above
		public static void insertInClassDatabase(ArrayList<Integer> dataList) 
				throws SQLException, ClassNotFoundException,ExecuteInsertException{
				Database.connection();
				Database.insert(dataList);
				Database.close();
		}
		//method to get number of games
		public static int getNumberOfGames() 
				throws SQLException, ClassNotFoundException,ExecuteQueryException,NotFoundDataException{
			Database.connection();
			int result = Database.getNumberOfGames();
			Database.close();
			return result;
		}
		
		//method to get number of human wins
		public static int getNumberOfHumanWins() 
				throws SQLException, ClassNotFoundException,ExecuteQueryException,NotFoundDataException{
			Database.connection();
			int result = Database.getNumberOfHumanWins();
			Database.close();
			return result;
		}
		
		//method to get number of AI wins
		public static int getNumberOfAIWins() 
				throws SQLException, ClassNotFoundException,ExecuteQueryException,NotFoundDataException{
			Database.connection();
			int result = Database.getNumberOfAIWins();
			Database.close();
			return result;
		}
		
		//method to get average number of draws
		public static double getAverageNumberOfDraws() 
				throws SQLException, ClassNotFoundException,ExecuteQueryException,NotFoundDataException{
			Database.connection();
			double result = Database.getAverageNumberOfDraws();
			Database.close();
			return result;
		}
		
		//method to get rounds of longest game
		public static int getRoundsOfLongestGame() 
				throws SQLException, ClassNotFoundException,ExecuteQueryException,NotFoundDataException{
			Database.connection();
			int result = Database.getLongestGame();
			Database.close();
			return result;
		}
}
