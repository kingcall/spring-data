package com.kingcall.jdbc.entity;
import java.io.Serializable;
import java.util.Date;

/*
*
* gen by beetlsql 2017-03-18
*/

public class User implements Serializable{
	private Integer id ;
	private Integer department_id ;
	//部门
	private String name ;
	//创建时间
	private Date createTime ;

	public User() {
	}

	public User(Integer department_id, String name, Date createTime) {
		this.department_id = department_id;
		this.name = name;
		this.createTime = createTime;
	}
	public User(Integer id,Integer department_id, String name, Date createTime) {
		this.id = id;
		this.department_id = department_id;
		this.name = name;
		this.createTime = createTime;
	}

	public Integer getId(){
		return  id;
	}
	public void setId(Integer id ){
		this.id = id;
	}

	public Integer getDepartmentId(){
		return  department_id;
	}
	public void setDepartmentId(Integer departmentId ){
		this.department_id = departmentId;
	}

	public String getName(){
		return  name;
	}
	public void setName(String name ){
		this.name = name;
	}

	public Date getCreateTime(){
		return  createTime;
	}
	public void setCreateTime(Date createTime ){
		this.createTime = createTime;
	}

	public void setDepartment_id(Integer department_id) {
		this.department_id = department_id;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", departmentId=" + department_id +
				", name='" + name + '\'' +
				", createTime=" + createTime +
				'}';
	}
}
