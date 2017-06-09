package xupt.se.util;
/**
 * 演出计划信息
 * @author zhangjiawen
 *
 */
public class ScheduleInformation {

	//计划  id
	private String sched_id;
	//演出厅id
	private String studio_id;
	//剧目id
	private String play_id;
	//演出时间
	private String sched_time;
	//票价
	private String sched_ticket_price;
	//时间id
	private String time_id;
	
	public String getSched_id() {
		return sched_id;
	}
	public void setSched_id(String sched_id) {
		this.sched_id = sched_id;
	}
	public String getStudio_id() {
		return studio_id;
	}
	public void setStudio_id(String studio_id) {
		this.studio_id = studio_id;
	}
	public String getPlay_id() {
		return play_id;
	}
	public void setPlay_id(String play_id) {
		this.play_id = play_id;
	}
	public String getSched_time() {
		return sched_time;
	}
	public void setSched_time(String sched_time) {
		this.sched_time = sched_time;
	}
	public String getSched_ticket_price() {
		return sched_ticket_price;
	}
	public void setSched_ticket_price(String sched_ticket_price) {
		this.sched_ticket_price = sched_ticket_price;
	}
	public String getTime_id() {
		return time_id;
	}
	public void setTime_id(String time_id) {
		this.time_id = time_id;
	}
	
}
