import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TestJDBC {

	public static Connection getConnection() throws SQLException,ClassNotFoundException{
		//加载JDBC的驱动
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String url = "jdbc:sqlserver://localhost:1433; DatabaseName=TTMS";
		String username = "sa";
		String password = "123";
		//创建与数据库类的连接实例
		Connection connection = DriverManager.getConnection(url, username, password);
		return connection;
	}
	public static <E> void main(String[] args){
		try{
			//获取连接实例connection，用connection创建Statement对象类实例sql_statement
			Connection connection = getConnection();
			Statement sql_statement = connection.createStatement();
//			//对seat表的验证
//			//向表中插入数据
//			sql_statement.executeUpdate("insert into seat values('1','8','10')");
//			//进行查询，用ResultSet类的对象，返回查询的结果
//			String query = "select * from seat";
//			ResultSet result = sql_statement.executeQuery(query);
//			//显示数据中seat表的内容
//			System.out.println("seat表的数据如下：");
//			//对获得的查询结果进行处理
//			while(result.next()){
//				int seat_id = result.getInt("seat_id");
//				int studio_id = result.getInt("studio_id");
//				int seat_row = result.getInt("seat_row");
//				int seat_column = result.getInt("seat_column");
//				//取得数据库中的数据
//				System.out.println(seat_id+" "+studio_id+" "+seat_row+" "+seat_column);
//			}
			
			//对play表的验证
			//进行查询，用ResultSet类的对象，返回查询的结果
			String query = "select * from play";
			ResultSet result = sql_statement.executeQuery(query);
//			List test = new ArrayList<E>();
//			System.out.println("result---"+result);
			//显示数据中seat表的内容
			System.out.println("play表的数据如下：");
			//对获得的查询结果进行处理
			while(result.next()){
				int a = result.getInt("play_id");
				int b = result.getInt("play_type_id");
				int c = result.getInt("play_lang_id");
				String d = result.getString("play_name");
				String e = result.getString("play_introduction");
				String f = result.getString("play_image");
				int g = result.getInt("play_length");
				int h = result.getInt("play_ticket_price");
				int i = result.getInt("play_status");
				//取得数据库中的数据
//				test.add(a);
//				test.add(b);
//				test.add(c);
//				test.add(d);
//				test.add(e);
//				test.add(f);
//				test.add(g);
//				test.add(h);
//				test.add(i);
//				System.out.println("test---"+test);
				System.out.println(a+" "+b+" "+c+" "+d+" "+e+" "+f+" "+g+" "+h+" "+i);
			}
//			System.out.println("test---"+test);
			
			//关闭连接和声明
			sql_statement.close();
			connection.close();
		}catch(ClassNotFoundException e){
			System.err.print("ClassNotFoundException:");
			System.err.println(e.getMessage());
		}catch(SQLException e){
			System.err.print("SQLException:");
			System.err.println(e.getMessage());
		}
	}
	
	
}
