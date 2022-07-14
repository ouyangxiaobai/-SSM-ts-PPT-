package model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="t_Borrow")//图书借还表
public class Borrow implements Serializable{

	private static final long serialVersionUID = -7141419035239709511L;

	private long id;
	
	private String danhao;//本次借书单号
	
	private Books  books;//关联图书

	private User user;//关联用户
	
	private Date b_createtime;//借书时间
	
	private Date re_createtime;//到期时间
	
	private Date back_createtime;//还书时间
	
	private int stauts;//借书状态（0已借，1已还）
	
	private int botime;//借书天数
	
	private long retime;//逾期天数
	
	private long num;//逾期费用     逾期费是单日1元

	private int borrowlock;//删除状态
	
	
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDanhao() {
		return danhao;
	}

	public void setDanhao(String danhao) {
		this.danhao = danhao;
	}


	@ManyToOne
	@JoinColumn(name="userid")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne
	@JoinColumn(name="booksid")
	public Books getBooks() {
		return books;
	}

	public void setBooks(Books books) {
		this.books = books;
	}


	public int getBotime() {
		return botime;
	}

	public void setBotime(int botime) {
		this.botime = botime;
	}

	

	public long getRetime() {
		return retime;
	}

	public void setRetime(long retime) {
		this.retime = retime;
	}

	

	public int getBorrowlock() {
		return borrowlock;
	}

	public void setBorrowlock(int borrowlock) {
		this.borrowlock = borrowlock;
	}

	public Date getB_createtime() {
		return b_createtime;
	}

	public void setB_createtime(Date bCreatetime) {
		b_createtime = bCreatetime;
	}

	public Date getRe_createtime() {
		return re_createtime;
	}

	public void setRe_createtime(Date reCreatetime) {
		re_createtime = reCreatetime;
	}

	public Date getBack_createtime() {
		return back_createtime;
	}

	public void setBack_createtime(Date backCreatetime) {
		back_createtime = backCreatetime;
	}

	public long getNum() {
		return num;
	}

	public void setNum(long num) {
		this.num = num;
	}

	public int getStauts() {
		return stauts;
	}

	public void setStauts(int stauts) {
		this.stauts = stauts;
	}

	

	


	
	
}
