package DBunit;

import org.dbunit.database.IDatabaseConnection;


import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.AfterClass;

import org.junit.BeforeClass;
import org.junit.Test;

import basic.Database;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;


public class TestDBunit {
	static IDatabaseConnection connection = null;
	RecordDAO recordDAO = new RecordDAO();
	static int n = 50;
	
	@BeforeClass
	public static void backupTableData() 
			throws ClassNotFoundException, DatabaseUnitException, SQLException, FileNotFoundException, IOException{
		connection = new DatabaseConnection(DBManager.getConnection());
		IDataSet data = connection.createDataSet();
		FlatXmlDataSet.write(data, new FileOutputStream("dataset.xml"));
	}
	
	//test insert
	@SuppressWarnings("deprecation")
	@Test
	public void testRecordInsert() 
			throws ClassNotFoundException, SQLException, ExecuteInsertException, ExecuteQueryException, NotFoundDataException{
		Record re = new Record();
		re.setTimes(51);
		re.setNumberOfMember(5);
		re.setNumberOfDraws(40);
		re.setWinner("PlayerYou");
		re.setRounds(90);
		re.setScoreOfPlayerYou(10);
		re.setScoreOfPlayerAI1(12);
		re.setScoreOfPlayerAI2(10);
		re.setScoreOfPlayerAI3(8);
		re.setScoreOfPlayerAI4(10);
		
		RecordDAO.insertRecord(re);
		Record recordTwo = RecordDAO.queryRecord(51);
		assertNotNull(recordTwo);
		assertEquals(re.getTimes(), recordTwo.getTimes());
		assertEquals(re.getNumberOfMembers(), recordTwo.getNumberOfMembers());
		assertEquals(re.getNumberOfDraws(), recordTwo.getNumberOfDraws());
		assertEquals(re.getWinner(), recordTwo.getWinner());
		assertEquals(re.getScoreOfPlayerYou(), recordTwo.getScoreOfPlayerYou());
		assertEquals(re.getScoreOfPlayerAI1(), recordTwo.getScoreOfPlayerAI1());
		assertEquals(re.getScoreOfPlayerAI2(), recordTwo.getScoreOfPlayerAI2());
		assertEquals(re.getScoreOfPlayerAI3(), recordTwo.getScoreOfPlayerAI3());
		assertEquals(re.getScoreOfPlayerAI4(), recordTwo.getScoreOfPlayerAI4());
	}
	
	//test delete
	@Test(expected=NotFoundDataException.class)
	public void testRecordDelete() 
			throws ClassNotFoundException, SQLException, ExecuteDeleteException, ExecuteQueryException, NotFoundDataException{
		RecordDAO.deleteRecord(1);
		Record re = RecordDAO.queryRecord(1);
		assertNull(re);
	}
	
	//test update
	@Test
	public void testRecordUpdate()
			throws ClassNotFoundException, SQLException, ExecuteUpdateException, ExecuteQueryException, NotFoundDataException{
		Record re = new Record();
		re.setTimes(2);
		re.setNumberOfMember(5);
		re.setNumberOfDraws(50);
		re.setWinner("PlayerAI1");
		re.setRounds(100);
		re.setScoreOfPlayerYou(11);
		re.setScoreOfPlayerAI1(12);
		re.setScoreOfPlayerAI2(9);
		re.setScoreOfPlayerAI3(8);
		re.setScoreOfPlayerAI4(10);
		
		RecordDAO.updateRecord(re);
		Record recordTwo = RecordDAO.queryRecord(2);
		assertNotNull(recordTwo);
		
		assertEquals(re.getTimes(), recordTwo.getTimes());
		assertEquals(re.getNumberOfMembers(), recordTwo.getNumberOfMembers());
		assertEquals(re.getNumberOfDraws(), recordTwo.getNumberOfDraws());
		assertEquals(re.getWinner(), recordTwo.getWinner());
		assertEquals(re.getScoreOfPlayerYou(), recordTwo.getScoreOfPlayerYou());
		assertEquals(re.getScoreOfPlayerAI1(), recordTwo.getScoreOfPlayerAI1());
		assertEquals(re.getScoreOfPlayerAI2(), recordTwo.getScoreOfPlayerAI2());
		assertEquals(re.getScoreOfPlayerAI3(), recordTwo.getScoreOfPlayerAI3());
		assertEquals(re.getScoreOfPlayerAI4(), recordTwo.getScoreOfPlayerAI4());
	}
	
	//test query
	@Test(expected=NotFoundDataException.class)
	public void testRecordQuery()
			throws ClassNotFoundException, SQLException, ExecuteQueryException, NotFoundDataException{
		Record re = new Record();
		re.setTimes(90);
		re.setNumberOfMember(4);
		re.setNumberOfDraws(50);
		re.setWinner("PlayerAI3");
		re.setRounds(100);
		re.setScoreOfPlayerYou(11);
		re.setScoreOfPlayerAI1(12);
		re.setScoreOfPlayerAI2(9);
		re.setScoreOfPlayerAI3(18);
		re.setScoreOfPlayerAI4(0);
		
		Record recordTwo = RecordDAO.queryRecord(90);
		assertNull(recordTwo);
	}
	
	//test method in Class Database
	//test insert method in Class Database
	//test insert
	@Test
	public void testInsertInClassDatabase()
			throws ClassNotFoundException, SQLException, ExecuteQueryException, NotFoundDataException, ExecuteInsertException{
		Record re = new Record();
		re.setTimes(109);
		re.setNumberOfMember(4);
		re.setNumberOfDraws(50);
		re.setWinner("PlayerAI3");
		re.setRounds(100);
		re.setScoreOfPlayerYou(11);
		re.setScoreOfPlayerAI1(12);
		re.setScoreOfPlayerAI2(9);
		re.setScoreOfPlayerAI3(18);
		re.setScoreOfPlayerAI4(0);
				
		ArrayList<Integer> dataList = new ArrayList<>(10);
		dataList.add(re.getTimes());
		dataList.add(re.getNumberOfMembers());
		dataList.add(re.getNumberOfDraws());
		switch (re.getWinner()){
		case "PlayerYou":
			dataList.add(0);
			break;
		case "PlayerAI1":
			dataList.add(1);
			break;
		case "PlayerAI2":
			dataList.add(2);
			break;
		case "PlayerAI3":
			dataList.add(3);
			break;
		case "PlayerAI4":
			dataList.add(4);
			break;
		}
		dataList.add(re.getRounds());
		dataList.add(re.getScoreOfPlayerYou());
		dataList.add(re.getScoreOfPlayerAI1());
		dataList.add(re.getScoreOfPlayerAI2());
		dataList.add(re.getScoreOfPlayerAI3());
		dataList.add(re.getScoreOfPlayerAI4());
				
		RecordDAO.insertInClassDatabase(dataList);
		Record recordTwo = RecordDAO.queryRecord(109);
		assertNotNull(recordTwo);
		assertEquals(re.getTimes(), recordTwo.getTimes());
		assertEquals(re.getNumberOfMembers(), recordTwo.getNumberOfMembers());
		assertEquals(re.getNumberOfDraws(), recordTwo.getNumberOfDraws());
		assertEquals(re.getWinner(), recordTwo.getWinner());
		assertEquals(re.getScoreOfPlayerYou(), recordTwo.getScoreOfPlayerYou());
		assertEquals(re.getScoreOfPlayerAI1(), recordTwo.getScoreOfPlayerAI1());
		assertEquals(re.getScoreOfPlayerAI2(), recordTwo.getScoreOfPlayerAI2());
		assertEquals(re.getScoreOfPlayerAI3(), recordTwo.getScoreOfPlayerAI3());
		assertEquals(re.getScoreOfPlayerAI4(), recordTwo.getScoreOfPlayerAI4());
	}
	
	//test get average number of draws
	@SuppressWarnings("deprecation")
	@Test
	public void testGetAverageNumberOfDraws() 
			throws ClassNotFoundException, SQLException, ExecuteQueryException, NotFoundDataException, ExecuteInsertException{
		Record re = new Record();
		re.setTimes(63);
		re.setNumberOfMember(4);
		re.setNumberOfDraws(50);
		re.setWinner("PlayerAI3");
		re.setRounds(100);
		re.setScoreOfPlayerYou(11);
		re.setScoreOfPlayerAI1(12);
		re.setScoreOfPlayerAI2(9);
		re.setScoreOfPlayerAI3(18);
		re.setScoreOfPlayerAI4(0);

		double originalNumber = RecordDAO.getAverageNumberOfDraws();
		int number = RecordDAO.getNumberOfGames();
		RecordDAO.insertRecord(re);
		double currentNumber = RecordDAO.getAverageNumberOfDraws();
		double error = 2;
		assertEquals(originalNumber*number+50,currentNumber*(number+1),error);
	}
	
	//test get number of games
	@Test
	public void testGetNumberOfGames() 
			throws ClassNotFoundException, SQLException, ExecuteQueryException, NotFoundDataException, ExecuteInsertException{
		Record re = new Record();
		re.setTimes(42);
		re.setNumberOfMember(4);
		re.setNumberOfDraws(50);
		re.setWinner("PlayerAI3");
		re.setRounds(100);
		re.setScoreOfPlayerYou(11);
		re.setScoreOfPlayerAI1(12);
		re.setScoreOfPlayerAI2(9);
		re.setScoreOfPlayerAI3(18);
		re.setScoreOfPlayerAI4(0);
			
		
		int originalNumber = RecordDAO.getNumberOfGames();
		RecordDAO.insertRecord(re);
		int currentNumber = RecordDAO.getNumberOfGames();
		assertEquals(originalNumber+1, currentNumber);
	}
	
	//test get number of human wins
	@Test
	public void testGetNumberOfHumanWins() 
			throws ClassNotFoundException, SQLException, ExecuteQueryException, NotFoundDataException, ExecuteInsertException{
		Record re = new Record();
		re.setTimes(43);
		re.setNumberOfMember(4);
		re.setNumberOfDraws(50);
		re.setWinner("PlayerYou");
		re.setRounds(100);
		re.setScoreOfPlayerYou(11);
		re.setScoreOfPlayerAI1(12);
		re.setScoreOfPlayerAI2(9);
		re.setScoreOfPlayerAI3(18);
		re.setScoreOfPlayerAI4(0);
				
			
		int originalNumber = RecordDAO.getNumberOfHumanWins();
		RecordDAO.insertRecord(re);
		int currentNumber = RecordDAO.getNumberOfHumanWins();
		assertEquals(originalNumber+1, currentNumber);
	}
	
	//test get number of AI wins
	@Test
	public void testGetNumberOfAIWins() 
			throws ClassNotFoundException, SQLException, ExecuteQueryException, NotFoundDataException, ExecuteInsertException{
		Record re = new Record();
		re.setTimes(44);
		re.setNumberOfMember(4);
		re.setNumberOfDraws(50);
		re.setWinner("PlayerAI2");
		re.setRounds(100);
		re.setScoreOfPlayerYou(11);
		re.setScoreOfPlayerAI1(12);
		re.setScoreOfPlayerAI2(9);
		re.setScoreOfPlayerAI3(18);
		re.setScoreOfPlayerAI4(0);
			
		int originalNumber = RecordDAO.getNumberOfAIWins();
		RecordDAO.insertRecord(re);
		int currentNumber = RecordDAO.getNumberOfAIWins();
		assertEquals(originalNumber+1, currentNumber);
	}
	
	//test get rounds of longest game
	@Test
	public void testGetRoundsOfLongestGame() 
			throws ClassNotFoundException, SQLException, ExecuteQueryException, NotFoundDataException, ExecuteInsertException{
		int originalNumber = RecordDAO.getRoundsOfLongestGame();
		Record re = new Record();
		re.setTimes(47);
		re.setNumberOfMember(4);
		re.setNumberOfDraws(50);
		re.setWinner("PlayerAI2");
		re.setRounds(originalNumber+50);
		re.setScoreOfPlayerYou(11);
		re.setScoreOfPlayerAI1(12);
		re.setScoreOfPlayerAI2(9);
		re.setScoreOfPlayerAI3(18);
		re.setScoreOfPlayerAI4(0);
		
		RecordDAO.insertRecord(re);
		int currentNumber = RecordDAO.getRoundsOfLongestGame();
		assertEquals(originalNumber+50, currentNumber);
	}
	
	
	@SuppressWarnings("deprecation")
	@AfterClass
	public static void resumeTableData() 
			throws FileNotFoundException, IOException, DatabaseUnitException, SQLException{
		IDataSet data = new FlatXmlDataSetBuilder().build(new FileInputStream("dataset.xml"));
		DatabaseOperation.CLEAN_INSERT.execute(connection, data);
	}
}
