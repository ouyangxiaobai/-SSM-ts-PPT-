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
@Table(name="t_Books")//图书
public class Books implements Serializable{

	private static final long serialVersionUID = -7141419035239709511L;

	private long id;
	
	private String bianhao;//图书编号
	
	private String name;//图书名
	
	private String miaoshu;//图书描述
	
	private int price;//图书价格
	
	private String cb_time;//出版时间
	
	private String cbs;//出版社
	
	private String zuozhe;//图书作者
	
	private String imgpath;//图书图片
	
	private Fenlei fenlei;//关联分类
	
	private Date createtime;
	
	private String status;//图书状态   已借，未借
	
	private String zt;//出售状态   （可购买、已售罄）
	
	private int bookslock;


	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	public String getMiaoshu() {
		return miaoshu;
	}

	public void setMiaoshu(String miaoshu) {
		this.miaoshu = miaoshu;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getBianhao() {
		return bianhao;
	}

	public void setBianhao(String bianhao) {
		this.bianhao = bianhao;
	}

	

	public String getCbs() {
		return cbs;
	}

	public void setCbs(String cbs) {
		this.cbs = cbs;
	}

	public String getZuozhe() {
		return zuozhe;
	}

	public void setZuozhe(String zuozhe) {
		this.zuozhe = zuozhe;
	}

	public String getImgpath() {
		return imgpath;
	}

	public void setImgpath(String imgpath) {
		this.imgpath = imgpath;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public int getBookslock() {
		return bookslock;
	}

	public void setBookslock(int bookslock) {
		this.bookslock = bookslock;
	}

	@ManyToOne
	@JoinColumn(name="fenleiid")
	public Fenlei getFenlei() {
		return fenlei;
	}

	public void setFenlei(Fenlei fenlei) {
		this.fenlei = fenlei;
	}

	public String getCb_time() {
		return cb_time;
	}

	public void setCb_time(String cbTime) {
		cb_time = cbTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getZt() {
		return zt;
	}

	public void setZt(String zt) {
		this.zt = zt;
	}

	

	
	

	
}
