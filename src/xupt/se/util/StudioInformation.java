package xupt.se.util;
/**
 * 演出厅信息
 * @author zhangjiawen
 *
 */
public class StudioInformation {
	
	private String studio_id;	//演出厅的id
	private String studio_name;	//演出厅的名称
	private String studio_row_count;		//演出厅的行号
	private String studio_col_count;		//演出厅的列好
	private String studio_introduction;		//演出厅的  具体的信息
	private String studio_isavailable;		//演出厅  是否可用
	
	public String getStudio_id() {
		return studio_id;
	}
	public void setStudio_id(String studio_id) {
		this.studio_id = studio_id;
	}
	public String getStudio_name() {
		return studio_name;
	}
	public void setStudio_name(String studio_name) {
		this.studio_name = studio_name;
	}
	public String getStudio_row_count() {
		return studio_row_count;
	}
	public void setStudio_row_count(String studio_row_count) {
		this.studio_row_count = studio_row_count;
	}
	public String getStudio_col_count() {
		return studio_col_count;
	}
	public void setStudio_col_count(String studio_col_count) {
		this.studio_col_count = studio_col_count;
	}
	public String getStudio_introduction() {
		return studio_introduction;
	}
	public void setStudio_introduction(String studio_introduction) {
		this.studio_introduction = studio_introduction;
	}
	public String getStudio_isavailable() {
		return studio_isavailable;
	}
	public void setStudio_isavailable(String studio_isavailable) {
		this.studio_isavailable = studio_isavailable;
	}
	

}
