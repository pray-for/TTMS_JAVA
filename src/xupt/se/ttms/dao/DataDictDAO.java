package xupt.se.ttms.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import xupt.se.ttms.idao.iDataDictDAO;
import xupt.se.ttms.model.DataDict;
import xupt.se.util.DBUtil;

public class DataDictDAO implements iDataDictDAO
{

    public int insert(DataDict stu)
    {
        // TODO Auto-generated method stub

        try
        {
            String sql = "insert into data_dict( dict_parent_id,  dict_index , dict_name , dict_value )" + " values("
                    + stu.getSuperId() + ", " + stu.getIndex() + ", '" + stu.getName() + "' , '" + stu.getValue()
                    + "' )";
            DBUtil db = new DBUtil();
            db.openConnection();
            ResultSet rst = db.getInsertObjectIDs(sql);
            if (rst != null && rst.first())
            {
                stu.setId(rst.getInt(1));
            }
            db.close(rst);
            db.close();
            return 1;

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int update(DataDict stu)
    {
        // TODO Auto-generated method stub
        int rtn = 0;
        try
        {
            String sql = "update data_dict set " + " dict_parent_id =" + stu.getSuperId() + ", " + "  dict_index = "
                    + stu.getIndex() + ", " + " dict_name = '" + stu.getName() + "', " + " dict_value = '"
                    + stu.getValue() + "' ";

            sql += " where dict_id = " + stu.getId();
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

    @Override
    public int delete(int ID)
    {
        // TODO Auto-generated method stub
        int rtn = 0;
        try
        {
            String sql = "delete from  data_dict ";
            sql += " where dict_id = " + ID;
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

    @Override
    public List<DataDict> select(String condt)
    {
        // TODO Auto-generated method stub
        List<DataDict> ddictList = null;
        ddictList = new LinkedList<DataDict>();
        try
        {
            String sql = "select dict_id, dict_parent_id,  dict_index , dict_name , dict_value from data_dict ";
            condt.trim();
            if (!condt.isEmpty())
                sql += " where " + condt;

            DBUtil db = new DBUtil();
            if (!db.openConnection())
            {
                System.out.print("fail to connect database");
                return null;
            }
            ResultSet rst = db.execQuery(sql);

            if (rst != null)
            {
                while (rst.next())
                {
                    DataDict ddict = new DataDict();
                    ddict.setId(rst.getInt("dict_id"));
                    ddict.setSuperId(rst.getInt("dict_parent_id"));
                    ddict.setIndex(rst.getInt("dict_index"));
                    ddict.setName(rst.getString("dict_name"));
                    ddict.setValue(rst.getString("dict_value"));
                    ddictList.add(ddict);
                }
            }
            db.close(rst);
            db.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {

        }

        return ddictList;
    }

    public List<DataDict> findByID(int id)
    {
        return select(" dict_parent_id =" + id);

    }

    public List<DataDict> findSelfByID(int id)
    {
        return select(" dict_id =" + id);

    }

    public void findAllSonByID(List<DataDict> list, int id)
    {
        List<DataDict> childList = findByID(id);
        for (int i = 0; i < childList.size(); i++)
        {
            if (!hasChildren(childList.get(i).getId()))
                list.add(childList.get(i));
            else
            {
                findAllSonByID(list, childList.get(i).getId());
            }
        }
    }

    // 给定的节点是否有孩子节点
    public boolean hasChildren(int id)
    {
        List<DataDict> list = select(" dict_parent_id =" + id);
        return list.size() > 0 ? true : false;
    }

    public DataDict findSelfByName(String condt)
    {

        return select(" dict_name = '" + condt + "'").get(0);
    }

    public DataDict findName(String string)
    {

        return select(" dict_id = '" + string + "'").get(0);
    }

    public String printfDictName(int dict_id) throws Exception
    {
        DBUtil db = new DBUtil();// 连接数据库
        // 数据库查询语句

        if (!db.openConnection())
        {
            System.out.print("fail to connect database");
            return null;
        }

        String string = "";
        String sql = "SELECT dict_name FROM data_dict WHERE dict_id = " + dict_id;
        // System.out.println(sql);
        if (db.execQuery(sql) != null)
        {
            System.out.println("data_dict表读取成功");
            string = getDictName((db.execQuery(sql)));
            db.close();
            return string;
            // getData_Dict((connection.findTable(sql)));
        }
        else
        {
            System.out.println("data_dict表中没有数据");
        }
        db.close();
        return string;
    }

    public String getDictName(ResultSet resultSet)
    {
        String string = "";
        try
        {
            while (resultSet.next())
            {
                string = resultSet.getString(1);
            }
            resultSet.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println("dict_name查询出错");
        }
        return string;
    }

    public static void main(String[] args)
    {
        DataDictDAO dictDAO = new DataDictDAO();
        List<DataDict> list = dictDAO.findByID(1);

        List<DataDict> list1 = new ArrayList<DataDict>();

        dictDAO.findAllSonByID(list1, 0);
    }

}

