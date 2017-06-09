package xupt.se.ttms.sql;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import xupt.se.util.Schedule_TimeInformation;



/**
 * 从数据库中读取   time  的  时间 操作
 */
public class Schedule_Time {


	/**
	 * 从数据库中获取   timelist 
	 */
	private List<Schedule_TimeInformation> time = new ArrayList<Schedule_TimeInformation>();

	/**s
	 * 数据库定义
	 */
	
	private MovieConnection connection = null;
	
	/**
	 * 给定id  读取  time 列表
	 */
	
	public List<Schedule_TimeInformation> prinfTimes() {
		connection = new MovieConnection(); // 连接数据库
		//数据库查询语句
		String sql = "SELECT * FROM schedule_time";
		if (connection.findTable(sql) != null) {
			System.out.println("time表读取成功");
			getTime((connection.findTable(sql)));
		} else {
			System.out.println("time表中没有数据");
		}
		connection.close();
		return time;
	}
	
	/**
	 * 获取  时间 的 列表
	 * @param resultSet
	 */
	public void getTime(ResultSet resultSet) {

		try {
			time = new ArrayList<Schedule_TimeInformation>();
			Schedule_TimeInformation seatInformation;
			while (resultSet.next()) {
				seatInformation = new Schedule_TimeInformation();
				//时间id
				seatInformation.setTime_id((resultSet.getString(1)));
				//时间
				seatInformation.setTime((resultSet.getString(2)));
				
				time.add(seatInformation);
			}// 显示数据
			resultSet.close();// 关闭连接
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("时间的  信息查询出错！");
		}
	}
	
}
