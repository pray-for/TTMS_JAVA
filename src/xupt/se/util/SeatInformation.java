package xupt.se.util;
/**
 * 座位信息
 * @author zhangjiawen
 *
 */
public class SeatInformation {

	/**
	 * 座位id
	 */
	private String seat_id;
	/**
	 * 所属演出厅的id
	 */
	private String studio_id;
	/**
	 * 座位的行号
	 */
	private String seat_row;
	/**
	 * 座位的列好
	 */
	private String seat_column;
	/**
	 * 座位  的状态
	 */
	private String seat_status;
	
	public String getSeat_id() {
		return seat_id;
	}
	public void setSeat_id(String seat_id) {
		this.seat_id = seat_id;
	}
	public String getStudio_id() {
		return studio_id;
	}
	public void setStudio_id(String studio_id) {
		this.studio_id = studio_id;
	}
	public String getSeat_row() {
		return seat_row;
	}
	public void setSeat_row(String seat_row) {
		this.seat_row = seat_row;
	}
	public String getSeat_column() {
		return seat_column;
	}
	public void setSeat_column(String seat_column) {
		this.seat_column = seat_column;
	}
	public String getSeat_status() {
		return seat_status;
	}
	public void setSeat_status(String seat_status) {
		this.seat_status = seat_status;
	}
	
}
