package com.kingcall.jpa.entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/*
*
* gen by beetlsql 2017-03-18
*/
@Entity
@Getter
@Setter
@ToString
public class User implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id ;

    @Column(name = "department_id")
	private Integer departmentId ;
	//部门
   @Column
	private String name ;
	//创建时间
    @Column(name = "create_time")
	private Date createTime ;

	public User() {
	}

	public User(Integer department_id, String name, Date createTime) {
		this.departmentId = department_id;
		this.name = name;
		this.createTime = createTime;
	}
	public User(Integer id, Integer department_id, String name, Date createTime) {
		this.id = id;
		this.departmentId = department_id;
		this.name = name;
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", departmentId=" + departmentId +
				", name='" + name + '\'' +
				", createTime=" + createTime +
				'}';
	}
}
