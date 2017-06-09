package xupt.se.ttms.dao;


import java.sql.ResultSet;

import xupt.se.util.DBUtil;

public class zuoweiDAO {
	public int insert(String studio,String row,String col){
		try{
			String sql="insert into seat(studio_id,seat_row,seat_column)"
					+"values('"+studio+"','"+row+"','"+col+"')";
			System.out.println(sql);
			DBUtil db=new DBUtil();
            db.openConnection();
            int lists = db.execCommand(sql);
            db.close();
            db.close();
            return 1;
		}catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}
	public int update(String ID,String studio,String row,String col){
		int rtn=0;
		try{
			String sql="update seat set studio_id='"+studio+"',seat_row='"+row+"',seat_column='"+col+"' where seat_id="+ID;
            System.out.println(sql);
            DBUtil db = new DBUtil();
            db.openConnection();
            int lists = db.execCommand(sql);
            db.close();
            db.close();
            return 1;
		}catch(Exception e){
			e.printStackTrace();
		}
		return rtn;
	}
	public int delete(String seat_id){
		int rtn=0;
		try{
			String sql = "delete from seat";
			sql += " where  seat_id = " + seat_id;
			DBUtil db = new DBUtil();
			db.openConnection();
			rtn=db.execCommand(sql);
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtn;	
	}
    public int select(Object[][] obj)
    {
    	int i = 0;
        try
        {
//            i = 0;
            String sql = "select * from seat order by seat_id";
            DBUtil db = new DBUtil();
            db.openConnection();
            ResultSet lists = db.execQuery(sql);
            while (lists.next())
            {
                obj[i][0] = lists.getString("seat_id");
                obj[i][1] = lists.getString("studio_id");
                obj[i][2] = lists.getString("seat_row");
                obj[i][3] = lists.getString("seat_column");
                i++;
            }
            db.close();
            db.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {

        }
        return i;
    }
    public int selectrow(String studio_id)
    {
    	int rtn=0;
    	try{
    		String sql="select studio_row_count from studio";
    		sql += " where  studio_id = " + studio_id;
    		DBUtil db=new DBUtil();
    		db.openConnection();
    		ResultSet lists = db.execQuery(sql);
    		if(lists.next())
    			{rtn=lists.getInt(1);}
    		db.close();
    	}catch (Exception e) {
			e.printStackTrace();
		}
		return rtn;	
    }
    public int selectcol(String studio_id)
    {
    	int rtn=0;
    	try{
    		String sql="select studio_col_count from studio";
    		sql += " where  studio_id = " + studio_id;
    		DBUtil db=new DBUtil();
    		db.openConnection();
    		ResultSet lists = db.execQuery(sql);
    		if(lists.next())
    			{rtn=lists.getInt(1);}
    		db.close();
    	}catch (Exception e) {
			e.printStackTrace();
		}
		return rtn;	
    }
    public int[] selectStudio()
    {
    	int i = 0;
    	int[] obj=new int[10];
        try
        {
//            i = 0;
            String sql = "select studio_id from studio";
            DBUtil db = new DBUtil();
            db.openConnection();
            ResultSet lists = db.execQuery(sql);
            while (lists.next())
            {
                obj[i] = Integer.parseInt(lists.getString("studio_id"));
                i++;
            }
            db.close();
            db.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {

        }
        return obj;
    }
}
