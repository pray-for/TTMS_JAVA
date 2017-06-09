package xupt.se.util;

public class StudioStatisticsInformation {
	private String studio_name = null;
	private int countStudio;
	private int sumStudio;
	private int sched_id;
	private int ticket_id;
	
	public void setStudio_name(String studio_name){
		this.studio_name = studio_name;
	}
	public String getStudio_name(){
		return studio_name;
	}
	public void setCountStudio(int countStudio){
		this.countStudio = countStudio;
	}
	public int getCountStudio(){
		return countStudio;
	}
	public void setSumStudio(int sumStudio){
		this.sumStudio = sumStudio;
	}
	public int getSumPlay(){
		return sumStudio;
	}
	public void setSched_id(int sched_id){
		this.sched_id = sched_id;
	}
	public int getSched_id(){
		return sched_id;
	}
	public void setTicket_id(int ticket_id){
		this.ticket_id = ticket_id;
	}
	public int getTicket_id(){
		return ticket_id;
	}
}
