package xupt.se.util;
/**
 * 剧目信息
 * @author zhangjiawen
 *
 */
public class PlayInformation {

	private String play_id;		//剧目的 id
	private String play_type_id;		//类型的id
	private String play_lang_id;		//语言
	private String play_name;		//剧目姓名
	private String play_introduction;	//剧目的信息
	private String play_length;		//剧目的长度
	private String play_ticket_price;	//票的价格
	private String play_status;		//票的状态
	private String play_image;		//票的图片
	
	//具体的get  和   set方法
	public String getPlay_id() {
		return play_id;
	}
	public void setPlay_id(String play_id) {
		this.play_id = play_id;
	}
	public String getPlay_type_id() {
		return play_type_id;
	}
	public void setPlay_type_id(String play_type_id) {
		this.play_type_id = play_type_id;
	}
	public String getPlay_lang_id() {
		return play_lang_id;
	}
	public void setPlay_lang_id(String play_lang_id) {
		this.play_lang_id = play_lang_id;
	}
	public String getPlay_name() {
		return play_name;
	}
	public void setPlay_name(String play_name) {
		this.play_name = play_name;
	}
	public String getPlay_introduction() {
		return play_introduction;
	}
	public void setPlay_introduction(String play_introduction) {
		this.play_introduction = play_introduction;
	}
	public String getPlay_length() {
		return play_length;
	}
	public void setPlay_length(String play_length) {
		this.play_length = play_length;
	}
	public String getPlay_ticket_price() {
		return play_ticket_price;
	}
	public void setPlay_ticket_price(String play_ticket_price) {
		this.play_ticket_price = play_ticket_price;
	}
	public String getPlay_status() {
		return play_status;
	}
	public void setPlay_status(String play_status) {
		this.play_status = play_status;
	}
	public String getPlay_image() {
		return play_image;
	}
	public void setPlay_image(String play_image) {
		this.play_image = play_image;
	}
	
}
