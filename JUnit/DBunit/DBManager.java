package DBunit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public  class DBManager {
	public static Connection getConnection() throws ClassNotFoundException,SQLException{
		Class.forName("com.mysql.cj.jdbc.Driver");
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/localhost?useUnicode=true&nullCatalogMeansCurrent=true&characterEncoding=utf-8&useSSL=false", "root", "YYX97sheng");
	}
}
