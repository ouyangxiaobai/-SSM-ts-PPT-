package model;


import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

//公告
@Entity
@Table(name="t_Gonggao")
public class Gonggao {
	

	@Id
	@GeneratedValue
	private int id;//主键
	
	
    private String biaoti;//公告标题
	
	private String content;//公告内容
	
	private Date createtime;//添加时间

	private int gonggaolock;//删除状态  0表示未删除 1表示已删除
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getGonggaolock() {
		return gonggaolock;
	}

	public void setGonggaolock(int gonggaolock) {
		this.gonggaolock = gonggaolock;
	}

	public String getBiaoti() {
		return biaoti;
	}

	public void setBiaoti(String biaoti) {
		this.biaoti = biaoti;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	

	
}
