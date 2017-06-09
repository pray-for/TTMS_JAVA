package xupt.se.util;
/**
 * 票的信息
 * @author zhangjiawen
 *
 */
public class TicketInformation {

	// 票的id
	private int ticket_id;
	// 座位的id
	private int seat_id;
	// 演出计划的id'
	private int sched_id;
	// 票的价格
	private String ticket_price;
	// 票的状态
	private int ticket_status;//0为待销售，1为锁定，9为卖出

	public int getTicket_id() {
		return ticket_id;
	}

	public void setTicket_id(int ticket_id) {
		this.ticket_id = ticket_id;
	}

	public int getSeat_id() {
		return seat_id;
	}

	public void setSeat_id(int seat_id) {
		this.seat_id = seat_id;
	}

	public int getSched_id() {
		return sched_id;
	}

	public void setSched_id(int sched_id) {
		this.sched_id = sched_id;
	}

	public String getTicket_price() {
		return ticket_price;
	}

	public void setTicket_price(String ticket_price) {
		this.ticket_price = ticket_price;
	}

	public int getTicket_status() {
		return ticket_status;
	}

	public void setTicket_status(int ticket_status) {
		this.ticket_status = ticket_status;
	}

}
