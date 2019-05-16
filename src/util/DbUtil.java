package util;
 
import java.sql.*;

/*
public class DbUtil {
	
	private static Connection connection=null;
	
	static {
		try {
			//1.加载驱动程序
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2.获得数据库的连接
            //jdbc:mysql://localhost:3306/testloc?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/test_system?serverTimezone" +
                            "=Asia/Shanghai&characterEncoding=utf-8&useSSL=false", "root", "970417jly");
            System.out.println("数据库已成功连接");
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
*/
public class DbUtil {
    static Connection con;
    //读取配置文件
    public static Connection getConnection(){
    	String driver = "com.mysql.cj.jdbc.Driver";
        
        try {
            //加载数据库驱动
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            //数据库连接
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/test_system?serverTimezone" +
                            "=Asia/Shanghai&characterEncoding=utf-8&useSSL=false", "root", "970417jly");
            System.out.println("数据库已成功连接");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return con;
    }
    /*
	public static Connection getConnection() {
		return con;
	}
	*/
}