package xupt.se.ttms.sql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import xupt.se.util.SeatInformation;



/**
 * 数据库中  的  seat表的一些操作   
 *
 */
public class Seat {
	
	/**
	 * list为seat的获取的信息，sql查询后获取的list
	 */
	private List<SeatInformation> seat = new ArrayList<SeatInformation>();

	/**s
	 * 数据库定义
	 */
	
	private MovieConnection connection = null;
	
	/**
	 * 座位 的  添加  信息
	 * @param studio_id
	 * @param hang
	 * @param lie
	 * @param zhuangtai
	 * @return
	 */
	public boolean seatAdd(String studio_id,String hang,String lie,
			String zhuangtai){
		
		connection = new MovieConnection(); // 连接数据库
		String sql = "insert into seat(studio_id,seat_row,seat_column," +
				"seat_status) values('"+studio_id+"','"+hang+"','"+lie+"','"
				+zhuangtai+"')";
		if (connection.insertTable(sql)) {
			System.out.println("seat 添加成功");
			connection.close();
			return true;
		} else {
			System.out.println("seat 添加失败");
		}
		connection.close();
		return false;
	}

	/**
	 * 根据id删除座位的信息  列表
	 * @param id
	 * @return
	 */
	public boolean DeleteSeat(String id){
		connection = new MovieConnection(); // 连接数据库
		String sql = "DELETE FROM seat WHERE studio_id = '"+id+"'";
		if (connection.deleteTable(sql)) {
			connection.close();
			return true;
		}
		connection.close();
		return false;
	}
	
	
	/**
	 * 通过id  从   数据库中获取并且保存
	 * 获取得到list数组  然后进行包
	 * 读取单个 座位
	 * @param no
	 * @return
	 */
	public List<SeatInformation> prinfSeats(String studio_id) {
		connection = new MovieConnection(); // 连接数据库
		//数据库查询语句
		String sql = "SELECT * FROM seat WHERE studio_id = '"+studio_id+"'";
		if (connection.findTable(sql) != null) {
			System.out.println("seat表读取成功");
			getSeat((connection.findTable(sql)));
		} else {
			System.out.println("seat表中没有数据");
		}
		connection.close();
		return seat;
	}
	
	/**
	 * 根据座位的行  列  及其演出厅的id查找票的id
	 * @param studio_id
	 * @param seat_row
	 * @param seat_column
	 * @return
	 */
	public String prinfPiaoSeat(String studio_id,
			String seat_row,String seat_column) {
		connection = new MovieConnection(); // 连接数据库
		//数据库查询语句
		String string = "";
		String sql = "SELECT seat_id FROM seat WHERE studio_id = '"+studio_id+"' and seat_row" +
				" = '"+seat_row+"' and seat_column = '"+seat_column+"'";
		if (connection.findTable(sql) != null) {
			System.out.println("seat表读取成功");
			string = getseatID((connection.findTable(sql)));
			connection.close();
			return string;
		} else {
			System.out.println("seat表中没有数据");
		}
		connection.close();
		return "";
	}
	/**
	 * 查找座位的id
	 * @param resultSet
	 * @return
	 */
	public String getseatID(ResultSet resultSet) {
		String string = "";
		try {
			while (resultSet.next()) {
				
				string = resultSet.getString(1);
				
			}// 显示数据
			resultSet.close();// 关闭连接
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("座位的  信息查询出错！");
		}
		return string;
	}
	/**
	 * 根据seat id返回  座位信息
	 * @param seat_id
	 * @return
	 */
	public List<SeatInformation> prinfSeat(String seat_id) {
		connection = new MovieConnection(); // 连接数据库
		//数据库查询语句
		String sql = "SELECT * FROM seat WHERE seat_id = '"+seat_id+"'";
		if (connection.findTable(sql) != null) {
			System.out.println("seat表读取成功");
			getSeat((connection.findTable(sql)));
		} else {
			System.out.println("seat表中没有数据");
		}
		connection.close();
		return seat;
	}
	
	/**
	 * 获取paly表的信息 并且保存在list列表当中
	 * 
	 * 
	 * @param resultSet
	 * @return
	 */
	public void getSeat(ResultSet resultSet) {

		try {
			seat = new ArrayList<SeatInformation>();
			SeatInformation seatInformation;
			while (resultSet.next()) {
				seatInformation = new SeatInformation();
				//剧目id
				seatInformation.setSeat_id((resultSet.getString(1)));
				//演出厅id
				seatInformation.setStudio_id((resultSet.getString(2)));
				//座位  行
				seatInformation.setSeat_row((resultSet.getString(3)));
				//座位  列
				seatInformation.setSeat_column((resultSet.getString(4)));
				//座位状态
				seatInformation.setSeat_status((resultSet.getString(5)));
				
				seat.add(seatInformation);
			}// 显示数据
			resultSet.close();// 关闭连接
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("座位的  信息查询出错！");
		}
	}
	
	/**
	 * 座位修改  的 具体信息
	 * @param studio_id
	 * @param seat_row
	 * @param seat_column
	 * @param seat_status
	 * @param seat_id
	 * @return
	 */
	public boolean seatXiuGai(String studio_id,String seat_row,
			String seat_column,String seat_status){
		connection = new MovieConnection(); // 连接数据库
		//数据库的操作语句
		String sql = "UPDATE seat SET seat_status = '"+seat_status+"'"+
				" WHERE studio_id= '"+studio_id+
				"' and seat_row = '"+seat_row+"' and seat_column = '"+
				seat_column+"'";
		if (connection.updateTable(sql)) {
//			System.out.println("studio_id= '"+studio_id+
//					"' and seat_row = '"+seat_row+"' and seat_column = '"+
//					seat_column+"'");
			System.out.println("修改成功");
			connection.close();
			return true;
		}
		connection.close();
		return false;
	}
	
	
	/**
	 * 获取seat表的信息 并且保存在list列表当中
	 * 
	 * 
	 * @param resultSet
	 * @return
	 */
	public String getSeatStatus(ResultSet resultSet) {

		try {
			
			while (resultSet.next()) {
				connection.close();
				return resultSet.getString(1);
			}// 显示数据
			resultSet.close();// 关闭连接
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("座位的  信息查询出错！");
		}
		return null;
	}
	/**
	 * 查找  座位的状态
	 * @param studio_id
	 * @param seat_row
	 * @param seat_column
	 * @return
	 */
	public String prinfSeatStatus(String studio_id,String seat_row,String seat_column) {
		connection = new MovieConnection(); // 连接数据库
		//数据库查询语句
		String sql = "SELECT seat_status FROM seat WHERE studio_id = '"+studio_id+"' and" +
				" seat_row = '"+seat_row+"' and seat_column = '"+seat_column+"'";
		if (connection.findTable(sql) != null) {
			System.out.println("seat表读取成功");
			connection.close();
			return getSeatStatus(((connection.findTable(sql))));
		} else {
			System.out.println("seat表中没有数据");
		}
		connection.close();
		return "0";
	}
	
}
