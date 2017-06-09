package xupt.se.util;
/**
 * 数据字典信息
 * @author zhangjiawen
 *
 */
public class Data_DictInformation {

	private int dict_id;//数据字典的编号
	private int dict_parent_id;//双亲结点的序号
	private int dict_index;//在同一双亲结点下的顺序编号
	private String dict_name;//子结点的name
	private String dict_value;//子节点的value
	
	//具体的get()和set()方法
	public int getDict_id(){
		return dict_id;
	}
	public void setDict_id(int dict_id){
		this.dict_id = dict_id;
	}
	public int getDict_parent_id(){
		return dict_parent_id;
	}
	public void setDict_parent_id(int dict_parent_id){
		this.dict_parent_id = dict_parent_id;
	}
	public int getDict_index(){
		return dict_index;
	}
	public void setDict_index(int dict_index){
		this.dict_index = dict_index;
	}
	public String getDict_name(){
		return dict_name;
	}
	public void setDict_name(String dict_name){
		this.dict_name = dict_name;
	}
	public String getDict_value(){
		return dict_value;
	}
	public void setDict_value(String dict_value){
		this.dict_value = dict_value;
	}
	
	
}
