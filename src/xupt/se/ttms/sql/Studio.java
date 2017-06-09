package xupt.se.ttms.sql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import xupt.se.util.StudioInformation;


/**
 * 演出厅表的  基本  数据库  操作
 *
 */
public class Studio {

	/**
	 * list为studio的获取的信息，sql查询后获取的list
	 */
	private List<StudioInformation> studio = new ArrayList<StudioInformation>();

	/**s
	 * 数据库定义
	 */
	
	private MovieConnection connection = null;
	
	/**
	 * studio 表的添加  
	 * @param name
	 * @param hang
	 * @param lie
	 * @param infor
	 * @param keyong
	 * @return
	 */
	public String studioAdd(String name,String hang,String lie,String infor
			,Boolean keyong){
		String id = "";
		connection = new MovieConnection(); // 连接数据库
		String sql = "insert into studio(studio_name,studio_row_count,studio_col_count," +
				"studio_introduction,studio_isavailable) values('"+name+"','"+hang+"','"+lie+"','"
				+infor+"','"+keyong+"')";
		if (connection.insertTable(sql)) {
			System.out.println("studio 添加成功");
			String sql_id = "SELECT studio_id\n" + "FROM studio";
			id = seatAddReturnId((connection.findTable(sql_id)));
			connection.close();
			return id;
		} else {
			System.out.println("studio 添加失败");
		}
		connection.close();
		return id;
	}
	
	/**
	 * studio  表 元素的修改 操作
	 * @param name
	 * @param hang
	 * @param lie
	 * @param infor
	 * @param keyong
	 * @param id
	 * @return
	 */
	public boolean studioXiuGai(String name,String hang,
			String lie,String infor,String keyong,String id){
		connection = new MovieConnection(); // 连接数据库
		//数据库的操作语句
		String sql = "UPDATE studio SET studio_name = '"+name+
				"',studio_row_count = '"+hang+"',studio_col_count = '"+lie+
				"',studio_introduction = '"+infor+"',studio_isavailable = '"+keyong+"'"
				+" WHERE studio_id = "+id;
		if (connection.updateTable(sql)) {
			connection.close();
			return true;
		}
		connection.close();
		return false;
	}
	
	/**
	 * 通过id  从   数据库中获取并且保存
	 * 获取得到list数组  然后进行包
	 * 读取多个 员工
	 * @param no
	 * @return
	 */
	public List<StudioInformation> prinfStudios() {
		connection = new MovieConnection(); // 连接数据库
		//数据库查询语句
		String sql = "SELECT * FROM studio";
		if (connection.findTable(sql) != null) {
			System.out.println("studio表读取成功");
			getStudio((connection.findTable(sql)));
		} else {
			System.out.println("studio表中没有数据");
		}
		connection.close();
		return studio;
	}
	
	/**
	 * 通过id  从   数据库中获取并且保存
	 * 获取得到list数组  然后进行包
	 * 读取单个 员工
	 * @param no
	 * @return
	 */
	public List<StudioInformation> prinfStudio(String studio_id) {
		connection = new MovieConnection(); // 连接数据库
		//数据库查询语句
		String sql = "SELECT * FROM studio WHERE studio_id = '"+studio_id+"'";
		if (connection.findTable(sql) != null) {
			System.out.println("studio表读取成功");
			getStudio((connection.findTable(sql)));
		} else {
			System.out.println("studio表中没有数据");
		}
		connection.close();
		return studio;
	}
	
	/**
	 * 获取studio表的信息 并且保存在list列表当中
	 * 
	 * 
	 * @param resultSet
	 * @return
	 */
	public void getStudio(ResultSet resultSet) {

		try {
			studio = new ArrayList<StudioInformation>();
			StudioInformation studioInformation;
			while (resultSet.next()) {
				studioInformation = new StudioInformation();
				//剧目id
				studioInformation.setStudio_id((resultSet.getString(1)));
				//名称
				studioInformation.setStudio_name((resultSet.getString(2)));
				
				studioInformation.setStudio_row_count((resultSet.getString(3)));
				
				studioInformation.setStudio_col_count((resultSet.getString(4)));
				
				studioInformation.setStudio_introduction((resultSet.getString(5)));
				
//				studioInformation.setStudio_isavailable((resultSet.getString(6)));
				
				studio.add(studioInformation);
			}// 显示数据
			resultSet.close();// 关闭连接
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("演出出厅表信息数据查询出错");
		}
	}
	
	/**
	 * 根据工号删除剧目的信息  列表
	 * @param id
	 * @return
	 */
	public boolean DeleteStudio(String id){
		connection = new MovieConnection(); // 连接数据库
		String sql = "DELETE FROM studio WHERE studio_id = '"+id+"'";
		if (connection.deleteTable(sql)) {
			connection.close();
			return true;
		}
		connection.close();
		return false;
	}
	
	/**
	 * 放映厅添加  成功时 返回放映厅  的 id  
	 * @param resultSet
	 * @return
	 */
	private String seatAddReturnId(ResultSet resultSet){
		String string = "";
		try {
			while (resultSet.next()) {
				string = resultSet.getString(1);
				//System.out.println(emp_password);
			}// 显示数据
			resultSet.close();// 关闭连接
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("添加完成 返回ID时出错");
		}
		return string;
	}
	
	
}
