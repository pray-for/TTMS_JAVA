package xupt.se.ttms.dao;

import java.rmi.server.ObjID;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import xupt.se.ttms.model.Studio;
import xupt.se.util.DBUtil;
public class YanChuTinGuanLiDAO {
	//@override
	public int insert( String studio_name,String rowcount,String colcount,String introduction )
	{
		try{
			String sql = "insert into studio(studio_name,studio_row_count,studio_col_count,studio_introduction)values('"+studio_name+"',"+rowcount+","+colcount+",'"+introduction+"')";
			System.out.println(sql);
			DBUtil db=new DBUtil();
			db.openConnection();
			int lists =db.execCommand(sql);
			db.close();
			db.close();
			return 1;
		}
		
		catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}
	//@override
	public int update(String studio_name,String rowcount,String colcount,String introduction){
		int rtn=0;
		try{
			String sql="update studio set studio_name='"+studio_name+"',studio_row_count='"+rowcount+"',studio_col_count='"+colcount+"',studio_introduction='"+introduction+"'where studio_name='"+studio_name+"'";
		
			System.out.println(sql);
			DBUtil db=new DBUtil();
			db.openConnection();
			int lists=db.execCommand(sql);
			db.close();
			db.close();
			return 1;
		}
		
		catch (Exception e){
			
			
		}
		return rtn;
	}
	public int delete(String studio_name){
		int rtn=0;
		try {
			String sql="delete from studio where studio_name='"+ studio_name+"'";
			System.out.println(sql);
			DBUtil db =new DBUtil();
			db.openConnection();
			rtn=db.execCommand(sql);
			db.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return rtn;
	}
	public int select(Object[][] obj){
		int i=0;
		try
		{
			//int i=0;
			String sql="select * from studio order by studio_id";
			DBUtil db =new DBUtil();
			db.openConnection();
			ResultSet lists =db.execQuery(sql);
			while(lists.next()){
				obj[i][0]=lists.getString("studio_id");
				obj[i][1]=lists.getString("studio_name");
				obj[i][2]=lists.getString("studio_row_count");
				obj[i][3]=lists.getString("studio_col_count");
				obj[i][4]=lists.getString("studio_introduction");
				i++;
			}
			db.close(lists);
			db.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			
		}
		return i;
	}

}

