package DBunit;
import java.sql.*;
import java.util.ArrayList;

public class RecordDAO {
	
	//basic method: update, delete, insert query
	//method to update 
		private Record re = null;
		public static void updateRecord(Record re) 
				throws SQLException, ClassNotFoundException,ExecuteUpdateException{
			Connection conn = null;
			try {
				conn = DBManager.getConnection();
				
				PreparedStatement pst = conn.prepareStatement("UPDATE record SET numberOfMembers=?,numberOfDraws=?,winner=?,rounds=?,scoreOfPlayerYou=?,scoreOfPlayerAI1=?,scoreOfPlayerAI2=?,scoreOfPlayerAI3=?,scoreOfPlayerAI4=? WHERE times=?");
				pst.setInt(1,re.getNumberOfMembers());
				pst.setInt(2,re.getNumberOfDraws());
				pst.setString(3,re.getWinner());
				pst.setInt(4,re.getRounds());
				pst.setInt(5,re.getScoreOfPlayerYou());
				pst.setInt(6,re.getScoreOfPlayerAI1());
				pst.setInt(7,re.getScoreOfPlayerAI2());
				pst.setInt(8,re.getScoreOfPlayerAI3());
				pst.setInt(9,re.getScoreOfPlayerAI4());
				pst.setInt(10,re.getTimes());
				
				if(pst.executeUpdate()<=0) {
					throw new ExecuteUpdateException();
				}
			}finally {
				conn.close();
			}
		}
		
		//method to delete
		public static void deleteRecord(int times) 
				throws SQLException, ClassNotFoundException,ExecuteDeleteException, NotFoundDataException{
			Connection conn = null;
			try {
				conn = DBManager.getConnection();
				
				PreparedStatement pst = conn.prepareStatement("DELETE FROM record WHERE times=?");
				pst.setInt(1,times);
				
				if(pst.executeUpdate()<=0) {
					throw new NotFoundDataException();
				}
			}finally {
				conn.close();
			}
		}
		
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
		
		//method used to collect data in this program
		//method insert in the Class Database is slightly different from the method above
		public static void insertInClassDatabase(ArrayList<Integer> dataList) 
				throws SQLException, ClassNotFoundException,ExecuteInsertException{
			Connection conn = null;
			try {
				conn = DBManager.getConnection();
				
				PreparedStatement pst = conn.prepareStatement("INSERT INTO record VALUES(?,?,?,?,?,?,?,?,?,?)");
				pst.setInt(1,dataList.get(0));
				pst.setInt(2,dataList.get(1));
				pst.setInt(3,dataList.get(2));
				switch (dataList.get(3)){
				case 0:
					pst.setString(4,"PlayerYou");
					break;
				case 1:
					pst.setString(4,"PlayerAI1");
					break;
				case 2:
					pst.setString(4,"PlayerAI2");
					break;
				case 3:
					pst.setString(4,"PlayerAI3");
					break;
				case 4:
					pst.setString(4,"PlayerAI4");
					break;
			}
				pst.setInt(5,dataList.get(4));
				pst.setInt(6,dataList.get(5));
				pst.setInt(7,dataList.get(6));
				pst.setInt(8,dataList.get(7));
				pst.setInt(9,dataList.get(8));
				pst.setInt(10,dataList.get(9));
				
				if(pst.executeUpdate()<=0) {
					throw new ExecuteInsertException();
				}
			}finally {
				conn.close();
			}
		}
		//method to get number of games
		public static int getNumberOfGames() 
				throws SQLException, ClassNotFoundException,ExecuteQueryException,NotFoundDataException{
			int numberOfGames = 0;
			Connection conn = null;
			try {
				conn = DBManager.getConnection();
				
				PreparedStatement pst = conn.prepareStatement("SELECT COUNT(*) FROM record");
				ResultSet su = pst.executeQuery();
				if(su.next()) {
					numberOfGames = su.getInt(1);
					return numberOfGames;
				}
				throw new NotFoundDataException();
			}finally {
				conn.close();
			}
		}
		
		//method to get number of human wins
		public static int getNumberOfHumanWins() 
				throws SQLException, ClassNotFoundException,ExecuteQueryException,NotFoundDataException{
			int numberOfHumanWins = 0;
			Connection conn = null;
			try {
				conn = DBManager.getConnection();
				
				PreparedStatement pst = conn.prepareStatement("SELECT COUNT(*) FROM record WHERE winner='PlayerYou'");
				ResultSet su = pst.executeQuery();
				if(su.next()) {
					numberOfHumanWins = su.getInt(1);
					return numberOfHumanWins;
				}
				throw new NotFoundDataException();
			}finally {
				conn.close();
			}
		}
		
		//method to get number of AI wins
		public static int getNumberOfAIWins() 
				throws SQLException, ClassNotFoundException,ExecuteQueryException,NotFoundDataException{
			int numberOfAIWins = 0;
			Connection conn = null;
			try {
				conn = DBManager.getConnection();
				
				PreparedStatement pst = conn.prepareStatement("SELECT COUNT(*) FROM record WHERE winner='PlayerAI1' OR winner='PlayerAI2' OR winner='PlayerAI3' OR winner='PlayerAI4'");
				ResultSet su = pst.executeQuery();
				if(su.next()) {
					numberOfAIWins = su.getInt(1);
					return numberOfAIWins;
				}
				throw new NotFoundDataException();
			}finally {
				conn.close();
			}
		}
		
		//method to get average number of draws
		public static double getAverageNumberOfDraws() 
				throws SQLException, ClassNotFoundException,ExecuteQueryException,NotFoundDataException{
			double averageNumberOfDraws = 0.0;
			Connection conn = null;
			try {
				conn = DBManager.getConnection();
				
				PreparedStatement pst = conn.prepareStatement("SELECT COUNT(*), SUM(NumberOfDraws) FROM record");
				ResultSet su = pst.executeQuery();
				if(su.next()) {
					if(su.getInt(1)==0) {
						return 0.0;
					}
					averageNumberOfDraws = su.getInt(2)/su.getInt(1);
					return averageNumberOfDraws;
				}
				throw new NotFoundDataException();
			}finally {
				conn.close();
			}
		}
		
		//method to get rounds of longest game
		public static int getRoundsOfLongestGame() 
				throws SQLException, ClassNotFoundException,ExecuteQueryException,NotFoundDataException{
			int roundsOfLongestGame = 0;
			Connection conn = null;
			try {
				conn = DBManager.getConnection();
				
				PreparedStatement pst = conn.prepareStatement("SELECT MAX(Rounds) FROM record");
				ResultSet su = pst.executeQuery();
				if(su.next()) {
					roundsOfLongestGame = su.getInt(1);
					return roundsOfLongestGame;
				}
				throw new NotFoundDataException();
			}finally {
				conn.close();
			}
		}
}
