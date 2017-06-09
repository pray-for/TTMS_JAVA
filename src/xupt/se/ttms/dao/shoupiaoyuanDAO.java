package xupt.se.ttms.dao;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import xupt.se.ttms.model.Employee;
import xupt.se.ttms.model.Studio;
import xupt.se.util.DBUtil;


public class shoupiaoyuanDAO {
	public int insert(String gonghao,String name,String dianhua,String dizhi,String youxiang){
		try{
			String sql="insert into employee(emp_no,emp_name,emp_tel_num,emp_addr,emp_email)"
					+"values('"+gonghao+"','"+name+"','"+dianhua+"','"+dizhi+"','"+youxiang+"')";
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
	public int update(String ID,String gonghao,String name,String dianhua,String dizhi,String youxiang){
		int rtn=0;
		try{
			String sql="update employee set emp_no='"+gonghao+"',emp_name='"+name+"',emp_tel_num='"+dianhua+"',emp_addr='"+dizhi+"',emp_email='"+youxiang+"' where emp_id="+ID;
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
	public int delete(String emp_id){
		int rtn=0;
		try{
			String sql = "delete from employee";
			sql += " where  emp_id = " + emp_id;
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
            String sql = "select * from employee order by emp_id";
            DBUtil db = new DBUtil();
            db.openConnection();
            ResultSet lists = db.execQuery(sql);
            while (lists.next())
            {
                obj[i][0] = lists.getString("emp_id");
                obj[i][1] = lists.getString("emp_no");
                obj[i][2] = lists.getString("emp_name");
                obj[i][3] = lists.getString("emp_tel_num");
                obj[i][4] = lists.getString("emp_addr");
                obj[i][5] = lists.getString("emp_email");
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
}
//	public List<Employee> select(String condt) {
//		List<Employee> employee = null;
//		employee=new LinkedList<Employee>();
//		try {
//			String sql = "select employee_id, employee_no, employee_name, employee_tel_num, emp_addr,employee_email from employee ";
//			condt.trim();
//			if(!condt.isEmpty())
//				sql+= " where " + condt;
//			DBUtil db = new DBUtil();
//			if(!db.openConnection()){
//				System.out.print("fail to connect database");
//				return null;
//			}
//			ResultSet rst = db.execQuery(sql);
//			if (rst!=null) {
//				while(rst.next()){
//					Employee stu=new Employee();
//					stu.setId(rst.getString("emp_id"));
//					stu.setName(rst.getString("emp_name"));
//					stu.setTel(rst.getString("emp_tel_num"));
//					stu.setaddr(rst.getString("emp_addr"));
//					stu.setemail(rst.getString("emp_email"));
//					employee.add(stu);
//				}
//			}
//			db.close(rst);
//			db.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		finally{
//			
//		}
//		
//		return employee;
//	}
//}

