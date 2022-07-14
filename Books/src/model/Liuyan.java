package model;


import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

//留言
@Entity
@Table(name="t_Liuyan")
public class Liuyan {
	

	@Id
	@GeneratedValue
	private int id;//主键
	
	private String content;//留言内容
	private String recontent;//管理员回复
	@ManyToOne
	@JoinColumn(name="userid")
	private User user;//关联用户

	private Date createtime;//留言时间
	private Date recreatetime;//回复时间

	private int liuyanlock;//删除状态  0表示未删除 1表示已删除
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRecontent() {
		return recontent;
	}

	public void setRecontent(String recontent) {
		this.recontent = recontent;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getRecreatetime() {
		return recreatetime;
	}

	public void setRecreatetime(Date recreatetime) {
		this.recreatetime = recreatetime;
	}

	public int getLiuyanlock() {
		return liuyanlock;
	}

	public void setLiuyanlock(int liuyanlock) {
		this.liuyanlock = liuyanlock;
	}


	
}
