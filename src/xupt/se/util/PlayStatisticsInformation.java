package xupt.se.util;

public class PlayStatisticsInformation {
	private String play_name = null;
	private int countPlay;
	private int sumPlay;
	private int sched_id;
	private int ticket_id;
	
	public void setPlay_name(String play_name){
		this.play_name = play_name;
	}
	public String getPlay_name(){
		return play_name;
	}
	public void setCountPlay(int countPlay){
		this.countPlay = countPlay;
	}
	public int getCountPlay(){
		return countPlay;
	}
	public void setSumPlay(int sumPlay){
		this.sumPlay = sumPlay;
	}
	public int getSumPlay(){
		return sumPlay;
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
