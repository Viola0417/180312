package util;
 
import java.sql.*;
 
public class DbUtil {
	
	private static Connection connection=null;
	
	static {
		try {
			//1.������������
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2.������ݿ������
            //jdbc:mysql://localhost:3306/testloc?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/test_system?serverTimezone" +
                            "=Asia/Shanghai&characterEncoding=utf-8&useSSL=false", "root", "970417jly");
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
