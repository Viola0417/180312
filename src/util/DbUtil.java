package util;
 
import java.sql.*;
 
public class DbUtil {
	
	private static Connection connection=null;
	
	static {
		try {
			//1.������������
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2.������ݿ������
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/test_system?serverTimezone" +
                            "=UTC&characterEncoding=utf-8&useSSL=false", "root", "970417jly");
            System.out.println("���ݿ��ѳɹ�����");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		return connection;
	}
}
