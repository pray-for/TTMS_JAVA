package xupt.se.ttms.sql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import xupt.se.util.Data_DictInformation;

public class Data_Dict {

	/**
	 * list为data_dict的获取的信息，sql查询后获取的list
	 */
	private List<Data_DictInformation> Data_Dict = new ArrayList<Data_DictInformation>();

	/**
	 * 数据库定义
	 */
	private MovieConnection connection = null;
	
	/**
	 * 通过dict_id获取数据字典表中的dict_name
	 * @param dict_id
	 * @return
	 */
	public String printfDictName(int dict_id){
		connection = new MovieConnection();//连接数据库
		//数据库查询语句
		String string = "";
		String sql = "SELECT dict_name FROM data_dict WHERE dict_id = "+dict_id;
//		System.out.println(sql);
		if(connection.findTable(sql) != null){
			System.out.println("data_dict表读取成功");
			string = getDictName((connection.findTable(sql)));
			connection.close();
			return string;
//			getData_Dict((connection.findTable(sql)));
		} else{
			System.out.println("data_dict表中没有数据");
		}
		connection.close();
		return string;
	}
	
	
	/**
	 * 获取数据字典的信息，并保存到list中
	 * @param resultSet
	 */
	public void getData_Dict(ResultSet resultSet){
		try{
			Data_Dict = new ArrayList<Data_DictInformation>();
			Data_DictInformation data_DictInformation;
			while(resultSet.next()){
				data_DictInformation = new Data_DictInformation();
				data_DictInformation.setDict_id(Integer.valueOf(resultSet.getString(0)));
				data_DictInformation.setDict_parent_id(Integer.valueOf(resultSet.getString(1)));
				data_DictInformation.setDict_index(Integer.valueOf(resultSet.getString(2)));
				data_DictInformation.setDict_name(resultSet.getString(3));
				data_DictInformation.setDict_value(resultSet.getString(4));
			}
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("data_dict表查询出错");
		}
	}
	
	/**
	 * 查找数据字典中的dict_name
	 * @param resultSet
	 * @return
	 */
	public String getDictName(ResultSet resultSet){
		String string = "";
		try{
			while(resultSet.next()){
				string = resultSet.getString(1);
			}
			resultSet.close();
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("dict_name查询出错");
		}
		return string;
	}
	
	//根据data_name找data_id  
		public  int  printfDictName1(String dict_name){
			connection = new MovieConnection();//连接数据库
			//数据库查询语句
			int dict_id = 0;
			String sql = "SELECT dict_id FROM data_dict WHERE dict_name ='"+dict_name+"'";
			System.out.println(sql);
			ResultSet resultSet = connection.findTable(sql);
			if(resultSet != null){
				System.out.println("data_dict表读取成功");
				System.out.println("333333333");
				dict_id = getDictID(resultSet);
				connection.close();
				return dict_id;
			} else{
				System.out.println("data_dict表中没有数据");
			}
			connection.close();
			return dict_id;
		}
		public int getDictID(ResultSet resultSet){
			int id = 0;
			try{
				while(resultSet.next()){
					id = resultSet.getInt(1);
				}
				resultSet.close();
			}catch(SQLException e){
				e.printStackTrace();
				System.out.println("dict_id查询出错");
			}
			return id;
		}
	
	
}
