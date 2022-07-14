package model;


import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

//图书分类
@Entity
@Table(name="t_Fenlei")
public class Fenlei {
	

	@Id
	@GeneratedValue
	private int id;//主键
	
    private String name;//分类名
	
	private Date createtime;//添加时间

	private int fenleilock;//删除状态  0表示未删除 1表示已删除
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getFenleilock() {
		return fenleilock;
	}

	public void setFenleilock(int fenleilock) {
		this.fenleilock = fenleilock;
	}
	

	
}
