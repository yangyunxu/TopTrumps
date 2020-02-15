package DBUnit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public  class DBManager {
	public static Connection getConnection() throws ClassNotFoundException,SQLException{
		Class.forName("org.postgresql.Driver");
		return DriverManager.getConnection("jdbc:postgresql://52.24.215.108:5432/Lifeordeath?useUnicode=true&nullCatalogMeansCurrent=true&characterEncoding=utf-8&useSSL=false", "Lifeordeath", "Lifeordeath");
	}
}
