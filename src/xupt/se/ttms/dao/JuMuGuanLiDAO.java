package xupt.se.ttms.dao;


import java.sql.ResultSet;

import xupt.se.util.DBUtil;

public class JuMuGuanLiDAO
{

    // @Override
    public int insert(int playtype, int yuyantype, String name, String introduce, String length, String price)
    {
        try
        {
            String sql = "insert into play (play_type_id,play_lang_id,play_name,play_introduction,play_length,play_ticket_price) values ( "
                    + playtype + ",'" + yuyantype + "','" + name + "','" + introduce + "','" + length + "','" + price
                    + "')";
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

        return 0;
    }

    // @Override
    public int update(String playid, int playtype, int yuyantype, String name, String introduce, String length,
            String price)
    {
        int rtn = 0;
        try
        {
            String sql = "update play set play_type_id =" + playtype + ",play_lang_id='" + yuyantype + "',play_name ='"
                    + name + "',play_introduction ='" + introduce + "',play_length ='" + length
                    + "',play_ticket_price ='" + price + "' where play_id='" + playid + "'";
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

    // @Override
    public int delete(String Play_id)
    {
        int rtn = 0;
        try
        {
            String sql = "delete from play ";
            sql += " where play_id = " + Play_id;
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

    // @Override
    public int select(Object[][] obj)
    {
        try
        {
            int i = 0;
            String sql = "select * from play";
            DBUtil db = new DBUtil();
            db.openConnection();
            ResultSet lists = db.execQuery(sql);
            DataDictDAO data = new DataDictDAO();
            while (lists.next())
            {
                obj[i][0] = lists.getString("play_id");
                Object type = lists.getString("play_type_id");
                obj[i][1] = data.printfDictName(Integer.parseInt(String.valueOf(type)));
                Object language = lists.getString("play_lang_id");
                obj[i][2] = data.printfDictName(Integer.parseInt(String.valueOf(language)));
                obj[i][3] = lists.getString("play_name");
                obj[i][4] = lists.getString("play_introduction");
                obj[i][5] = lists.getString("play_length");
                obj[i][6] = lists.getString("play_ticket_price");
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
