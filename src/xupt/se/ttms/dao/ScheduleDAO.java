package xupt.se.ttms.dao;


import java.sql.ResultSet;

import xupt.se.ttms.idao.iScheduleDAO;
import xupt.se.util.DBUtil;

public class ScheduleDAO implements iScheduleDAO
{

    public int insert(String schedid, Object studioid, Object playid, String time, String price)
    {
        // TODO Auto-generated method stub
        try
        {
            String sql = "insert into schedule (studio_id, play_id,sched_time, sched_ticket_price) values ( " + "'"
                    + studioid + "','" + playid + "','" + time + "','" + price + "')";
            DBUtil db = new DBUtil();
            db.openConnection();
            int lists = db.execCommand(sql);
            db.close();
            db.close();
            return 1;

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return 0;
    }

    public int update(String schedid, Object studioid, Object playid, String time, String price)
    {
        int rtn = 0;
        try
        {
            String sql = "update schedule set studio_id='" + studioid + "',play_id='" + playid + "',sched_time='" + time
                    + "',sched_ticket_price ='" + price + "' where sched_id='" + schedid + "'";
            System.out.println(sql);
            DBUtil db = new DBUtil();
            db.openConnection();
            int lists = db.execCommand(sql);
            db.close();
            db.close();
            return 1;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return rtn;
    }

    public int delete(String ID)
    {
        // TODO Auto-generated method stub
        int rtn = 0;
        try
        {
            String sql = "delete from  schedule ";
            sql += " where sched_id = " + ID;
            DBUtil db = new DBUtil();
            db.openConnection();
            rtn = db.execCommand(sql);
            db.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return rtn;
    }

    public int select(Object[][] obj)
    {

        try
        {
            int i = 0;
            String sql = "select * from schedule order by sched_id";
            DBUtil db = new DBUtil();
            db.openConnection();
            ResultSet lists = db.execQuery(sql);
            while (lists.next())
            {
                obj[i][0] = lists.getString("sched_id");
                obj[i][1] = lists.getString("studio_id");
                obj[i][2] = lists.getString("play_id");
                obj[i][3] = lists.getString("sched_time");
                obj[i][4] = lists.getString("sched_ticket_price");
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
        return 1;

    }

    public int findByID(Object[] obj)
    {
        try
        {
            int i = 0;
            String sql = "select * from studio";
            DBUtil db = new DBUtil();
            db.openConnection();
            ResultSet lists = db.execQuery(sql);
            while (lists.next())
            {
                obj[i] = lists.getString("studio_id");
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
        return 1;

    }

    public int findPlayByID(Object[] obj)
    {
        try
        {
            int i = 0;
            String sql = "select * from play";
            DBUtil db = new DBUtil();
            db.openConnection();
            ResultSet lists = db.execQuery(sql);
            while (lists.next())
            {
                obj[i] = lists.getString("play_id");
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
        return 1;

    }
}
