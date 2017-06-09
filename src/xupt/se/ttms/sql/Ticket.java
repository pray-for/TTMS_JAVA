package xupt.se.ttms.sql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import xupt.se.util.PlayStatisticsInformation;
import xupt.se.util.StudioStatisticsInformation;
import xupt.se.util.TicketInformation;

import java.text.*;


/**
 * 数据库中 对票的操作 具体的实现方法
 * 
 * 
 */
public class Ticket {

	/**
	 * 票的查询后的list
	 */
	private List<TicketInformation> ticketInformations = new ArrayList<TicketInformation>();
	private List<PlayStatisticsInformation> playStatistics = new ArrayList<PlayStatisticsInformation>();
	private List<StudioStatisticsInformation> studioStatistics = new ArrayList<StudioStatisticsInformation>();
	/**
	 * 数据库定义
	 */
	private MovieConnection connection = null;

	/**
	 * 票的添加  
	 * @param seat_id
	 * @param sched_id
	 * @param ticket_price
	 * @param ticket_status
	 * @return
	 */
	public boolean ticketAdd(String seat_id, String sched_id,
			String ticket_price, String ticket_status) {
		connection = new MovieConnection(); // 连接数据库
		String sql = "insert into ticket(seat_id,sched_id,ticket_price,ticket_status) "
				+ "values('"
				+ seat_id
				+ "','"
				+ sched_id
				+ "','"
				+ ticket_price + "','" + ticket_status + "')";
		if (connection.insertTable(sql)) {
			System.out.println("票信息添加成功");
			connection.close();
			return true;
		} else {
			System.out.println("票信息添加失败");
		}
		connection.close();
		return false;
	}
	
	/**
	 * 买票，添加座位以及票的状态
	 * @param seat_id
	 * @param sched_id
	 * @param price
	 * @return
	 */
	public boolean TicketSellAdd(int seat_id, int sched_id, String price){
		connection = new MovieConnection();//连接数据库
		float ticket_price = Float.parseFloat(price);
		Timestamp timestamp = new Timestamp(new Date().getTime());
		String sql = "insert into ticket(seat_id,sched_id,ticket_price,ticket_status,ticket_locked_time)" 
				+ "values(" + seat_id + "," + sched_id + "," + ticket_price + "," 
				+ "1" + ",'"+ timestamp + "')";
//		System.out.println(sql);
		if(connection.insertTable(sql)){
			System.out.println("票信息添加成功");
			connection.close();
			return true;
		}else{
			System.out.println("票信息添加失败");
		}
		connection.close();
		return false;
	}
	
	/**
	 * 根据   演出计划的id 删除票
	 * @param id
	 * @return
	 */
	public boolean deleteTicket(String id){
		connection = new MovieConnection(); // 连接数据库
		String sql = "DELETE FROM ticket WHERE sched_id = '"+id+"'";
		if (connection.deleteTable(sql)) {
			connection.close();
			return true;
		}
		connection.close();
		return false;
	}
	
	/**
	 * 用剧目id查找电影票   将其以list的形式保存起来
	 * @param sched_id
	 * @return
	 */
	public List<TicketInformation> prinfPlay(String sched_id) {
		connection = new MovieConnection(); // 连接数据库
		//数据库查询语句
		String sql = "SELECT * FROM ticket WHERE sched_id = '"+sched_id+"'";
		if (connection.findTable(sql) != null) {
			System.out.println("影票查找成功");
			getTicket((connection.findTable(sql)));
		} else {
			System.out.println("影票查找失败");
		}
		connection.close();
		return ticketInformations;
	}
	
	/**
	 * 用剧目id查找电影票   将其以list的形式保存起来
	 * @param sched_id
	 * @return
	 */
	public List<TicketInformation> prinfmaipiao(String sched_id) {
		connection = new MovieConnection(); // 连接数据库
		//数据库查询语句
		String sql = "SELECT * FROM ticket WHERE sched_id = '"+sched_id+"' and " +
				"ticket_status = '1'";
		if (connection.findTable(sql) != null) {
			System.out.println("影票查找成功");
			getTicket((connection.findTable(sql)));
		} else {
			System.out.println("影票查找失败");
		}
		connection.close();
		return ticketInformations;
	}
	
	/**
	 * 获取票  放到list中
	 * @param resultSet
	 */
	public void getTicket(ResultSet resultSet) {

		try {
			ticketInformations = new ArrayList<TicketInformation>();
			TicketInformation ticketInformation;
			while (resultSet.next()) {
				ticketInformation = new TicketInformation();
				//剧目id
				ticketInformation.setTicket_id(Integer.valueOf((resultSet.getString(1))));
				//类型id
				ticketInformation.setSeat_id(Integer.valueOf((resultSet.getString(2))));
				//语言
				ticketInformation.setSched_id(Integer.valueOf((resultSet.getString(3))));
				//paly名称
				ticketInformation.setTicket_price(resultSet.getString(4));
				//介绍
				ticketInformation.setTicket_status(Integer.valueOf((resultSet.getString(5))));
				
				ticketInformations.add(ticketInformation);
			}// 显示数据
			resultSet.close();// 关闭连接
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("ticket表信息数据查询出错");
		}
	}
	
	/**
	 * 根据座位的id  和  演出计划的id  对票的状态进行修改
	 * @param ticket_status
	 * @param seat_id
	 * @param sched_id
	 * @return
	 */
	public boolean ticketXiuGai(int ticket_status,int seat_id,
			int sched_id){
		connection = new MovieConnection(); // 连接数据库
		//数据库的操作语句
//		String sql = "UPDATE ticket SET ticket_status = '"+ticket_status
//				+"' WHERE seat_id = '"+seat_id+"' and sched_id = '"+sched_id+"'";
		String sql = "UPDATE ticket SET ticket_status = "+ticket_status
				+ "WHERE seat_id = "+seat_id+ "and sched_id = "+sched_id;
		if (connection.updateTable(sql)) {
			connection.close();
			return true;
		}
		connection.close();
		return false;
	}

	/**
	 * 退票操作
	 * @param ticket_id
	 * @return
	 */
	public boolean ticketTuipiao(String ticket_id){
		connection = new MovieConnection(); // 连接数据库
		//数据库的操作语句
		String sql = "UPDATE ticket SET ticket_status = '0'"
				+" WHERE ticket_id = '"+ticket_id+"'";
		if (connection.updateTable(sql)) {
			connection.close();
			return true;
		}
		connection.close();
		return false;
	}
	
	/**
	 * 卖票操作，通过ticket_id修改ticket_status
	 * @param ticket_id
	 * @return
	 */
	public boolean ticketSell(int ticket_id){
		connection = new MovieConnection();
		String sql = "UPDATE ticket SET ticket_status ="+1+
				" WHERE ticket_id="+ticket_id;
		if(connection.updateTable(sql)){
			connection.close();
			return true;
		}
		connection.close();
		return false;
	}
	
	/**
	 * 通过seat_id和sched_id 获取ticket_status
	 * @param seat_id
	 * @param sched_id
	 * @return
	 */
	public int printfTicketStatus(int seat_id, int sched_id){
		connection = new MovieConnection();//连接数据库
		//查询数据库
		int status = 0;
		String sql = "SELECT ticket_status FROM ticket WHERE seat_id="+seat_id
				+" AND sched_id="+sched_id;
//		System.out.println(sql);
		if(connection.findTable(sql) != null){
			System.out.println("ticket表读取成功");
			status = getTicketStatus((connection.findTable(sql)));
			connection.close();
			return status;
		}else{
			System.out.println("ticket表中没有数据");
		}
		connection.close();
		return status;
	}
	
	/**
	 * 查找ticket表中的票的状态 ticket_status
	 * @param resultSet
	 * @return
	 */
	public int getTicketStatus(ResultSet resultSet){
		int status = 0;
		try{
			while(resultSet.next()){
				status = resultSet.getInt(1);
			}
			resultSet.close();
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("ticket_status查询出错");
		}
		return status;
	}
	
	/**
	 * 通过seat_id和sched_id 获取ticket_id
	 * @param seat_id
	 * @param sched_id
	 * @return
	 */
	public int printfTicketId(int seat_id, int sched_id){
		connection = new MovieConnection();//连接数据库
		//查询数据库
		int ticket_id = 0;
		String sql = "SELECT ticket_id FROM ticket WHERE seat_id="+seat_id
				+" AND sched_id="+sched_id;
//		System.out.println(sql);
		if(connection.findTable(sql) != null){
			System.out.println("ticket表读取成功");
			ticket_id = getTicketStatus((connection.findTable(sql)));
			connection.close();
			return ticket_id;
		}else{
			System.out.println("ticket表中没有数据");
		}
		connection.close();
		return ticket_id;
	}
	
	/**
	 * 通过sched_id和ticket_status=1获取ticket_id
	 * @param sched_id
	 * @return
	 */
	public List<PlayStatisticsInformation> StatisticsPlay(int sched_id){
		connection = new MovieConnection();//连接数据库
		String sql = "SELECT ticket_id FROM ticket WHERE sched_id="+sched_id
				+" AND ticket_status="+1;
//		System.out.println(sql);
		if(connection.findTable(sql) != null){
			System.out.println("ticket表读取成功");
			getPlayTicketId((connection.findTable(sql)));
			connection.close();
		}else{
			System.out.println("ticket表中没有数据");
		}
		connection.close();
		return playStatistics;
	}
	
	/**
	 * 通过sched_id和ticket_status=1获取ticket_id
	 * @param sched_id
	 * @return
	 */
	public List<StudioStatisticsInformation> StatisticsStudio(int sched_id){
		connection = new MovieConnection();//连接数据库
		String sql = "SELECT ticket_id FROM ticket WHERE sched_id="+sched_id
				+" AND ticket_status="+1;
		if(connection.findTable(sql) != null){
			System.out.println("ticket表读取成功");
			getStudioTicketId((connection.findTable(sql)));
			connection.close();
		}else{
			System.out.println("ticket表中没有数据");
		}
		connection.close();
		return studioStatistics;
	}
	
	/**
	 * 获取ticket_id
	 * @param resultSet
	 */
	public void getPlayTicketId(ResultSet resultSet){
		try{
			playStatistics = new ArrayList<PlayStatisticsInformation>();
			PlayStatisticsInformation playStatisticsInformation;
			while(resultSet.next()){
				playStatisticsInformation = new PlayStatisticsInformation();
				//销售数量
				playStatisticsInformation.setTicket_id(resultSet.getInt(1));
				playStatistics.add(playStatisticsInformation);
			}
			resultSet.close();
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println("ticket读取出错");
		}
	}
	
	/**
	 * 获取ticket_id
	 * @param resultSet
	 */
	public void getStudioTicketId(ResultSet resultSet){
		try{
			studioStatistics = new ArrayList<StudioStatisticsInformation>();
			StudioStatisticsInformation studioStatisticsInformation;
			while(resultSet.next()){
				studioStatisticsInformation = new StudioStatisticsInformation();
				//销售数量
				studioStatisticsInformation.setTicket_id(resultSet.getInt(1));
				studioStatistics.add(studioStatisticsInformation);
			}
			resultSet.close();
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println("ticket读取出错");
		}
	}
	
	/**
	 * 查找ticket表中票的id ticket_id
	 * @param resultSet
	 * @return
	 */
	public int getTicketId(ResultSet resultSet){
		int ticket_id = 0;
		try{
			while(resultSet.next()){
				ticket_id = resultSet.getInt(1);
			}
			resultSet.close();
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("ticket_id查询出错");
		}
		return ticket_id;
	}
	
	/**
	 * Play判断是否为当天时间
	 * @return
	 */
	public int TodayTimePlay(String b){
		connection = new MovieConnection();//连接数据库
		Calendar now = Calendar.getInstance();
		int x=now.get(Calendar.YEAR);
		int y=now.get(Calendar.MONTH) + 1;
		int z=now.get(Calendar.DAY_OF_MONTH);
		int a=0;
		String sql = "SELECT sum(ticket_price) AS 'sum' FROM ticket,schedule,play WHERE ticket.sched_id=schedule.sched_id AND schedule.play_id=play.play_id AND play.play_id =" +b;
		if(y<10 && y>0){
			if(z<9 && z>0){
				sql += " AND ticket_locked_time BETWEEN  '" +x+"0"+y+"0"+z +"' AND '" +x+"0"+y+"0"+(z+1)  +"'";
			}
			if(z==9){
				sql += " AND ticket_locked_time BETWEEN  '"+x+ "0"+y+"09' AND '" +x+"0"+y  +"10'";
			}
			if(z==31){
				sql += " AND ticket_locked_time BETWEEN  '"+x+ "0"+y+"31' AND '" +x+"0"+(y+1)  +"01'";
			}
		}else if(y==12){
			if(z<9 && z>0){
				sql += " AND ticket_locked_time BETWEEN  '" +x+y+"0"+z +"' AND '" +x+y+"0"+(z+1)  +"'";
			}
			if(z==9){
				sql += " AND ticket_locked_time BETWEEN  '"+x+ y+"09' AND '" +x+y  +"10'";
			}
			if(z==31){
				sql += " AND ticket_locked_time BETWEEN  '"+x+y+"31' AND '" +(x+1) +"0101'";
			}
		}else{
			if(z<9 && z>0){
				sql += " AND ticket_locked_time BETWEEN  '" +x+y+"0"+z +"' AND '" +x+y+"0"+(z+1)  +"'";
			}
			if(z==9){
				sql += " AND ticket_locked_time BETWEEN  '"+x+ y+"09' AND '" +x+y  +"10'";
			}
			if(z==31){
				sql += " AND ticket_locked_time BETWEEN  '"+x+y+"31' AND '" +x+(y+1)  +"01'";
			}
		}
//		System.out.println(sql);
		ResultSet rst = connection.findTable(sql);
		if (rst != null) {
			try {
				while (rst.next()) {
					a=rst.getInt("sum");
				}
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		if(connection.findTable(sql) != null){
			System.out.println("ticket表查询成功");
			connection.close();
			return a;
		}else{
			System.out.println("ticket表中没有数据");
		}
		connection.close();
		return a;
	}
	
	/**
	 * Studio判断是否为当天时间
	 * @return
	 */
	public int TodayTimeStudio(String b){
		connection = new MovieConnection();//连接数据库
		Calendar now = Calendar.getInstance();
		int x=now.get(Calendar.YEAR);
		int y=now.get(Calendar.MONTH) + 1;
		int z=now.get(Calendar.DAY_OF_MONTH);
		int a=0;
		String sql = "SELECT sum(ticket_price) AS 'sum' FROM ticket,schedule,studio WHERE ticket.sched_id=schedule.sched_id AND schedule.studio_id=studio.studio_id AND studio.studio_id =" +b;
		if(y<10 && y>0){
			if(z<9 && z>0){
				sql += " AND ticket_locked_time BETWEEN  '" +x+"0"+y+"0"+z +"' AND '" +x+"0"+y+"0"+(z+1)  +"'";
			}
			if(z==9){
				sql += " AND ticket_locked_time BETWEEN  '"+x+ "0"+y+"09' AND '" +x+"0"+y  +"10'";
			}
			if(z==31){
				sql += " AND ticket_locked_time BETWEEN  '"+x+ "0"+y+"31' AND '" +x+"0"+(y+1)  +"01'";
			}
		}else if(y==12){
			if(z<9 && z>0){
				sql += " AND ticket_locked_time BETWEEN  '" +x+y+"0"+z +"' AND '" +x+y+"0"+(z+1)  +"'";
			}
			if(z==9){
				sql += " AND ticket_locked_time BETWEEN  '"+x+ y+"09' AND '" +x+y  +"10'";
			}
			if(z==31){
				sql += " AND ticket_locked_time BETWEEN  '"+x+y+"31' AND '" +(x+1) +"0101'";
			}
		}else{
			if(z<9 && z>0){
				sql += " AND ticket_locked_time BETWEEN  '" +x+y+"0"+z +"' AND '" +x+y+"0"+(z+1)  +"'";
			}
			if(z==9){
				sql += " AND ticket_locked_time BETWEEN  '"+x+ y+"09' AND '" +x+y  +"10'";
			}
			if(z==31){
				sql += " AND ticket_locked_time BETWEEN  '"+x+y+"31' AND '" +x+(y+1)  +"01'";
			}
		}
//		System.out.println(sql);
		ResultSet rst = connection.findTable(sql);
		if (rst != null) {
			try {
				while (rst.next()) {
					a=rst.getInt("sum");
				}
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		if(connection.findTable(sql) != null){
			System.out.println("ticket表查询成功");
			connection.close();
			return a;
		}else{
			System.out.println("ticket表中没有数据");
		}
		connection.close();
		return a;
	}
	
	
	
}

