package xupt.se.util;
/**
 * 雇员信息
 * @author zhangjiawen
 *
 */
public class EmployeeInformation {

	private int emp_id;//序号
	private String emp_No = null;	//工号
	private String emp_Name = null;		//姓名
	private String emp_Password = null;		//密码
	private String emp_Tel_Num = null;		//电话
	private String emp_Addr = null;			//住址
	private String emp_Email = null;		//邮箱

	
	public void setEmp_id(int emp_id){
		this.emp_id = emp_id;
	}
	
	public void setEmp_Addr(String emp_Addr) {
		this.emp_Addr = emp_Addr;
	}
	
	public void setEmp_Email(String emp_Email) {
		this.emp_Email = emp_Email;
	}
	
	public void setEmp_Name(String emp_Name) {
		this.emp_Name = emp_Name;
	}
	
	public void setEmp_No(String emp_No) {
		this.emp_No = emp_No;
	}
	
	public void setEmp_Password(String emp_Password) {
		this.emp_Password = emp_Password;
	}
	
	public void setEmp_Tel_Num(String emp_Tel_Num) {
		this.emp_Tel_Num = emp_Tel_Num;
	}
	public int getEmp_id(){
		return emp_id;
	}
	
	public String getEmp_Addr() {
		return emp_Addr;
	}
	
	public String getEmp_Email() {
		return emp_Email;
	}
	
	public String getEmp_Name() {
		return emp_Name;
	}
	
	public String getEmp_No() {
		return emp_No;
	}
	
	public String getEmp_Password() {
		return emp_Password;
	}
	
	public String getEmp_Tel_Num() {
		return emp_Tel_Num;
	}
	
	
}

