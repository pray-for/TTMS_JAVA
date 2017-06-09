import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class TTMS{
public static void main(String[] args) {


String url="jdbc:sqlserver://localhost:1433; DatabaseName=TTMS";

String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";

        

Statement st;

Connection con;




try{ 



Class.forName(driver);//加载驱动

}

catch(ClassNotFoundException event){System.out.print("无法创建驱动程式实体!");}



try{

 

con=DriverManager.getConnection(url,"sa","123");

con.setAutoCommit(true);

System.out.println("已经连接到数据库...");

st=con.createStatement();

       ResultSet rs=st.executeQuery("SELECT * from studio");

    

      while(rs.next())

     {   String name=rs.getString("studio_name"); 

           System.out.println(name);

         }



  

   st.close();

        con.close(); 

    }

    catch(SQLException e1) {System.out.println("异常"+e1);}

  } 

}
