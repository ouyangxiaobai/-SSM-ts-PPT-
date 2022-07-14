package action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import model.Books;
import model.Borrow;
import model.Fenlei;
import model.Gonggao;
import model.Liuyan;

import model.User;

import org.apache.struts2.ServletActionContext;
import util.Pager;
import util.Util;

import com.opensymphony.xwork2.ActionSupport;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import dao.BooksDao;
import dao.BorrowDao;
import dao.FenleiDao;
import dao.GonggaoDao;
import dao.LiuyanDao;

import dao.UserDao;

public class ManageAction extends ActionSupport{
	
	
	private static final long serialVersionUID = -4304509122548259589L;
	
	private UserDao userDao;
	
	private GonggaoDao gonggaoDao;
	private FenleiDao fenleiDao;
	private BooksDao booksDao;
	private BorrowDao borrowDao;
	private LiuyanDao liuyanDao;
	
	private String url = "./";
	
	
	public LiuyanDao getLiuyanDao() {
		return liuyanDao;
	}

	public void setLiuyanDao(LiuyanDao liuyanDao) {
		this.liuyanDao = liuyanDao;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
	

	public BorrowDao getBorrowDao() {
		return borrowDao;
	}

	public void setBorrowDao(BorrowDao borrowDao) {
		this.borrowDao = borrowDao;
	}

	public FenleiDao getFenleiDao() {
		return fenleiDao;
	}

	public void setFenleiDao(FenleiDao fenleiDao) {
		this.fenleiDao = fenleiDao;
	}

	public GonggaoDao getGonggaoDao() {
		return gonggaoDao;
	}

	public void setGonggaoDao(GonggaoDao gonggaoDao) {
		this.gonggaoDao = gonggaoDao;
	}

	
	public BooksDao getBooksDao() {
		return booksDao;
	}

	public void setBooksDao(BooksDao booksDao) {
		this.booksDao = booksDao;
	}

	//程序入口界面
	public String index(){
		HttpServletRequest request = ServletActionContext.getRequest();
		Util.init(request);
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(user==null){
			return "success2";
		}else{
			return "success1";
		}
	}
	
	
	//用户登陆操作
	public void login() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = userDao.selectBean(" where username='"+username+"' and password='"+password+"' and  userlock=0 ");
		if(user!=null){
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			response.setCharacterEncoding("gbk");
			PrintWriter writer = response.getWriter();
			writer.print("<script  language='javascript'>alert('登陆成功');window.location.href='index.jsp'; </script>");
		}else{
			response.setCharacterEncoding("gbk");
			PrintWriter writer = response.getWriter();
			writer.print("<script  language='javascript'>alert('用户名或者密码错误');window.location.href='login.jsp'; </script>");
		}

	}
	
	//用户退出操作
	public String loginout() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		this.setUrl("login.jsp");
		return SUCCESS;
	}
	
	
	//跳转到修改密码页面
	public String changepwd() {
		this.setUrl("user/password.jsp");
		return SUCCESS;
	}
	
	//修改密码操作
	public void changepwd2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		HttpSession session = request.getSession();
		User u = (User)session.getAttribute("user");
		String password1 = request.getParameter("password1");
		String password2 = request.getParameter("password2");
		User bean = userDao.selectBean(" where username= '"+u.getUsername()+"' and password= '"+password1+"'");
		if(bean!=null){
			bean.setPassword(password2);
			userDao.updateBean(bean);
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
			response
					.getWriter()
					.print(
							"<script language=javascript>alert('修改成功');</script>");
		}else{
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
			response
					.getWriter()
					.print(
							"<script language=javascript>alert('用户名或者密码错误');</script>");
		}
	}
	
	
	//用户注册操作
	public void register() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();	
		HttpServletResponse response = ServletActionContext.getResponse();
		
		String username = java.net.URLDecoder.decode(request.getParameter("username"), "utf-8");
		String password = java.net.URLDecoder.decode(request.getParameter("password"), "utf-8");
		String truename = java.net.URLDecoder.decode(request.getParameter("truename"), "utf-8");
		String telephone = java.net.URLDecoder.decode(request.getParameter("telephone"), "utf-8");
		String jiguan = java.net.URLDecoder.decode(request.getParameter("jiguan"), "utf-8");
		String address = java.net.URLDecoder.decode(request.getParameter("address"), "utf-8");
		String xingbie = java.net.URLDecoder.decode(request.getParameter("xingbie"), "utf-8");
		String age = java.net.URLDecoder.decode(request.getParameter("age"), "utf-8");
		
		
		User user = userDao.selectBean(" where  username='"+username+"' and userlock=0");
		
		if(user==null){
			user = new User();
			user.setCreatetime(new Date());
			user.setPassword(password);
			user.setTruename(truename);
			user.setUsername(username);
			user.setTelephone(telephone);
			user.setJiguan(jiguan);
			user.setAddress(address);
			user.setXingbie(xingbie);
			user.setAge(age);
			user.setRole(2);
			user.setBianhao(new Date().getTime()+"");
			userDao.insertBean(user);
			response.setCharacterEncoding("utf-8");
			response.getWriter().write("注册成功！您的用户名"+user.getUsername()+"");
			
		}else{
			response.setCharacterEncoding("utf-8");
			response.getWriter().write("该用户名已经存在，请重新注册！");
		}
	}
	
	
	/*******************************管理员权限*****************************************/
	

	
	
	//用户信息管理
	public String userlist(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String username=request.getParameter("username");
        StringBuffer sb = new StringBuffer();
        sb.append(" where ");
        if(username!=null&&!"".equals(username) ){
        	sb.append("username like '%"+username+"%'");
        	sb.append(" and ");
        	request.setAttribute("username", username);
        }
        sb.append(" role=2 ");
        sb.append(" and ");
        sb.append(" userlock=0 order by id ");
		int currentpage = 1;
		int pagesize = 10;
		if(request.getParameter("pagenum") != null){
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		String where =sb.toString();
		long total = userDao.selectBeanCount(where.replaceAll(" order by id desc", ""));
		List<User> list = userDao.selectBeanList(currentpage-1, pagesize, where);
		request.setAttribute("list", list);
		String pagerinfo = Pager.getPagerNormal((int)total, pagesize, currentpage, "method!userlist", "共有"+total+"条记录");
		request.setAttribute("pagerinfo", pagerinfo);
		this.setUrl("user/userlist.jsp");
		return SUCCESS;
	}
	
	
	//用户信息管理
	public String userlist2(){
		HttpServletRequest request = ServletActionContext.getRequest();
		int currentpage = 1;
		int pagesize = 10;
		if(request.getParameter("pagenum") != null){
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");	
		String where =" where userlock=0 and id="+user.getId()+" order by id desc "; 
		List<User> list = userDao.selectBeanList((currentpage - 1) * pagesize, pagesize, where);
		request.setAttribute("list", list);
		this.setUrl("user/userlist2.jsp");
		return SUCCESS;
	}
	
	//删除用户操作
	public void userdelete() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String id = request.getParameter("id");
		User bean =userDao.selectBean(" where id= "+id);
		bean.setUserlock(1);
		userDao.updateBean(bean);
		response.setCharacterEncoding("utf8");
		PrintWriter writer = response.getWriter();
		writer.print("<script  language='javascript'>alert('提交成功');window.location.href='method!userlist'; </script>");
		
	}
	
	//跳转到更新用户页面
	public String userupdate(){
		HttpServletRequest request = ServletActionContext.getRequest(); //**
		String id = request.getParameter("id");
		User bean =userDao.selectBean(" where id= "+id);
		request.setAttribute("bean", bean);
		this.setUrl("user/userupdate.jsp");
		return SUCCESS;
	}

	
	//更新用户操作
	public void userupdate2() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String truename = request.getParameter("truename");
		String telephone = request.getParameter("telephone");
		String jiguan = request.getParameter("jiguan");
		String address = request.getParameter("address");
		String xingbie = request.getParameter("xingbie");
		String age = request.getParameter("age");
		String id = request.getParameter("id");
		User bean =userDao.selectBean(" where id= "+id);
		bean.setTruename(truename);
		bean.setTelephone(telephone);
		bean.setJiguan(jiguan);
		bean.setAddress(address);
		bean.setAge(age);
		bean.setXingbie(xingbie);	
		userDao.updateBean(bean);
		response.setCharacterEncoding("utf8");
		PrintWriter writer = response.getWriter();
		writer.print("<script  language='javascript'>alert('提交成功');window.location.href='method!userlist2'; </script>");
		
	}
	
	
	
	
	/*公告*/
	//公告列表
	public String gonggaolist(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String biaoti = request.getParameter("biaoti");
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if(biaoti !=null &&!"".equals(biaoti)){
			sb.append(" biaoti like '%"+biaoti+"%' ");
			sb.append(" and ");

			request.setAttribute("biaoti", biaoti);
		}
		
		sb.append(" gonggaolock=0 order by id desc ");

		String where = sb.toString();
		int currentpage = 1;
		int pagesize = 10;
		if(request.getParameter("pagenum") != null){
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		
		long total = gonggaoDao.selectBeanCount(where.replaceAll("order by id desc", ""));
		List<Gonggao> list = gonggaoDao.selectBeanList(((currentpage-1)*pagesize)*pagesize, pagesize, where);
		request.setAttribute("list", list);
		String pagerinfo = Pager.getPagerNormal((int)total, pagesize, currentpage, "method!gonggaolist", "共有"+total+"条记录");
		request.setAttribute("pagerinfo", pagerinfo);
		this.setUrl("gonggao/gonggaolist.jsp");
		return SUCCESS;
	}
	
	
	//跳转到添加公告页面
	public String gonggaoadd(){
		this.setUrl("gonggao/gonggaoadd.jsp");
		return SUCCESS;
	}
	
	
	//添加公告操作
	public void gonggaoadd2() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String biaoti = request.getParameter("biaoti");
		String content = request.getParameter("content");
		Gonggao bean = new Gonggao();
		bean.setBiaoti(biaoti);
		bean.setContent(content);
		bean.setCreatetime(new Date());
		gonggaoDao.insertBean(bean);
		response.setCharacterEncoding("utf-8");response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = response.getWriter();
		writer.print("<script  language='javascript'>alert('提交成功');window.location.href='method!gonggaolist'; </script>");
		
	}
	
	
	
	//删除公告操作
	public void gonggaodelete() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String id = request.getParameter("id");
		Gonggao bean =gonggaoDao.selectBean(" where id= "+id);
		bean.setGonggaolock(1);
		gonggaoDao.updateBean(bean);
		response.setCharacterEncoding("utf-8");response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = response.getWriter();
		writer.print("<script  language='javascript'>alert('提交成功');window.location.href='method!gonggaolist'; </script>");
		
	}
	
	//跳转到更新公告页面
	public String gonggaoupdate(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String id = request.getParameter("id");
		Gonggao bean =gonggaoDao.selectBean(" where id= "+id);
		request.setAttribute("bean", bean);
		this.setUrl("gonggao/gonggaoupdate.jsp");
		return SUCCESS;
	}
	
	
	//更新公告操作
	public void gonggaoupdate2() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String biaoti = request.getParameter("biaoti");
		String content = request.getParameter("content");
		String id = request.getParameter("id");
		Gonggao bean =gonggaoDao.selectBean(" where id= "+id);
		bean.setBiaoti(biaoti);
		bean.setContent(content);
		gonggaoDao.updateBean(bean);
		response.setCharacterEncoding("utf-8");response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = response.getWriter();
		writer.print("<script  language='javascript'>alert('提交成功');window.location.href='method!gonggaolist'; </script>");
		
	}
	
	//跳转到查看公告页面
	public String gonggaoupdate3(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String id = request.getParameter("id");
		Gonggao bean =gonggaoDao.selectBean(" where id= "+id);
		request.setAttribute("bean", bean);
		this.setUrl("gonggao/gonggaoupdate3.jsp");
		return SUCCESS;
	}
	

	
	//公告列表
	public String gonggaolist2(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String biaoti = request.getParameter("biaoti");
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");
		if(biaoti !=null &&!"".equals(biaoti)){
			sb.append(" biaoti like '%"+biaoti+"%' ");
			sb.append(" and ");
			request.setAttribute("biaoti", biaoti);
		}
		sb.append(" gonggaolock=0 order by id desc ");
		String where = sb.toString();
		int currentpage = 1;
		int pagesize = 10;
		if(request.getParameter("pagenum") != null){
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		long total = gonggaoDao.selectBeanCount(where.replaceAll("order by id desc", ""));
		List<Gonggao> list = gonggaoDao.selectBeanList(((currentpage-1)*pagesize)*pagesize, pagesize, where);
		request.setAttribute("list", list);
		String pagerinfo = Pager.getPagerNormal((int)total, pagesize, currentpage, "method!gonggaolist2", "共有"+total+"条记录");
		request.setAttribute("pagerinfo", pagerinfo);
		this.setUrl("gonggao/gonggaolist2.jsp");
		return SUCCESS;
	}
	
	
	
	
	
	//图书分类管理
	public String fenleilist(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String name=request.getParameter("name");
		String shuxing=request.getParameter("shuxing");
        StringBuffer sb = new StringBuffer();
        sb.append(" where ");
        if(name!=null&&!"".equals(name) ){
        	sb.append("name like '%"+name+"%'");
        	sb.append(" and ");
        	request.setAttribute("name", name);
        }
        if(shuxing!=null&&!"".equals(shuxing) ){
        	sb.append("shuxing like '%"+shuxing+"%'");
        	sb.append(" and ");
        	request.setAttribute("shuxing", shuxing);
        }
        sb.append("  fenleilock=0 order by id ");
		int currentpage = 1;
		int pagesize = 10;
		if(request.getParameter("pagenum") != null){
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		String where =sb.toString();
		long total = fenleiDao.selectBeanCount(where.replaceAll(" order by id desc", ""));
		List<Fenlei> list = fenleiDao.selectBeanList((currentpage-1)*pagesize, pagesize, where);
		request.setAttribute("list", list);
		String pagerinfo = Pager.getPagerNormal((int)total, pagesize, currentpage, "method!fenleilist", "共有"+total+"条记录");
		request.setAttribute("pagerinfo", pagerinfo);
		this.setUrl("fenlei/fenleilist.jsp");
		return SUCCESS;
	}
	
	
	//删除图书分类操作
	public void fenleidelete() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String id = request.getParameter("id");
		Fenlei bean =fenleiDao.selectBean(" where id= "+id);
		bean.setFenleilock(1);
		fenleiDao.updateBean(bean);
		response.setCharacterEncoding("utf8");
		PrintWriter writer = response.getWriter();
		writer.print("<script  language='javascript'>alert('提交成功');window.location.href='method!fenleilist'; </script>");
		
	}
		
	
	//跳转到添加图书分类
	public String fenleiadd(){
		this.setUrl("fenlei/fenleiadd.jsp");
		return SUCCESS;
	}
	
	
	//添加图书分类操作
	
	public void fenleiadd2()throws IOException{
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		String name=request.getParameter("name");
		Date createtime=new Date();	
		Fenlei  bean=fenleiDao.selectBean(" where name='"+name+"' and fenleilock=0 ");
		if(bean==null){
		bean=new Fenlei();
		bean.setName(name);
		bean.setCreatetime(createtime);
		fenleiDao.insertBean(bean);
		response.setCharacterEncoding("utf8");
		PrintWriter writer=response.getWriter();
		writer.print("<script language='javascript'>alert('提交成功');window.location.href='method!fenleilist'; </script> ");
		}else{
			response.setCharacterEncoding("utf8");
			PrintWriter writer=response.getWriter();
			writer.print("<script language='javascript'>alert('该分类名已存在，请重新添加');window.location.href='method!fenleilist'; </script> ");
		}
	   }
	
	
	//跳转到更新图书分类页面
	public String fenleiupdate(){
		HttpServletRequest request = ServletActionContext.getRequest(); 
		String id = request.getParameter("id");
		Fenlei bean =fenleiDao.selectBean(" where id= "+id);
		request.setAttribute("bean", bean);
		this.setUrl("fenlei/fenleiupdate.jsp");
		return SUCCESS;
	}

	
	//更新图书分类操作
	public void fenleiupdate2() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String name=request.getParameter("name");
		Date createtime=new Date();	
		String id = request.getParameter("id");
		Fenlei bean =fenleiDao.selectBean(" where id= "+id);
		bean.setName(name);
		bean.setCreatetime(createtime);
		fenleiDao.updateBean(bean);
		response.setCharacterEncoding("utf8");
		PrintWriter writer = response.getWriter();
		writer.print("<script  language='javascript'>alert('提交成功');window.location.href='method!fenleilist'; </script>");
		
	}
	
	//图书管理
	public String bookslist(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String name=request.getParameter("name");
		String bianhao=request.getParameter("bianhao");
		String fenlei=request.getParameter("fenlei");
        StringBuffer sb = new StringBuffer();
        sb.append(" where ");
        if(name!=null&&!"".equals(name) ){
        	sb.append("name like '%"+name+"%'");
        	sb.append(" and ");
        	request.setAttribute("name", name);
        }
        if(bianhao!=null&&!"".equals(bianhao) ){
        	sb.append("bianhao like '%"+bianhao+"%'");
        	sb.append(" and ");
        	request.setAttribute("bianhao", bianhao);
        }
        if(fenlei!=null&&!"".equals(fenlei) ){
        	sb.append("fenlei.id like '%"+fenlei+"%'");
        	sb.append(" and ");
        	request.setAttribute("fenlei", fenlei);
        }
        sb.append("  bookslock=0 order by id desc");
		int currentpage = 1;
		int pagesize = 10;
		if(request.getParameter("pagenum") != null){
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		String where =sb.toString();
		long total = booksDao.selectBeanCount(where.replaceAll(" order by id desc", ""));
		List<Books> list = booksDao.selectBeanList((currentpage-1)*pagesize, pagesize, where);
		request.setAttribute("list", list);
		List<Fenlei> list2 = fenleiDao.selectBeanList(0, 999, " where fenleilock=0 ");
		request.setAttribute("list2", list2);
		String pagerinfo = Pager.getPagerNormal((int)total, pagesize, currentpage, "method!bookslist", "共有"+total+"条记录");
		request.setAttribute("pagerinfo", pagerinfo);
		this.setUrl("books/bookslist.jsp");
		return SUCCESS;
	}
	
	
	//删除图书操作
	public void booksdelete() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String id = request.getParameter("id");
		Books bean =booksDao.selectBean(" where id= "+id);
		bean.setBookslock(1);
		booksDao.updateBean(bean);
		response.setCharacterEncoding("utf8");
		PrintWriter writer = response.getWriter();
		writer.print("<script  language='javascript'>alert('提交成功');window.location.href='method!bookslist'; </script>");
		
	}
		
	
	 private File uploadfile;
		
		
	 public File getUploadfile() {
			return uploadfile;
		}

		
	 public void setUploadfile(File uploadfile) {
			this.uploadfile = uploadfile;
		}
	
	//跳转到添加图书
	public String booksadd(){
		HttpServletRequest request=ServletActionContext.getRequest();
		List<Fenlei> list = fenleiDao.selectBeanList(0, 999, " where fenleilock=0 ");
		request.setAttribute("list", list);
		this.setUrl("books/booksadd.jsp");
		return SUCCESS;
	}
	
	
	//添加图书操作
	public void booksadd2()throws IOException{
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		String name=request.getParameter("name");
		String miaoshu=request.getParameter("miaoshu");
		Integer price = Integer.parseInt(request.getParameter("price"));
		String cb_time=request.getParameter("cb_time");
		String cbs=request.getParameter("cbs");
		String zuozhe=request.getParameter("zuozhe");
		String fenlei=request.getParameter("fenlei");
		String zt=request.getParameter("zt");
		//上传商品图片
		String savapath = "D:/software/tomcat/apache-tomcat-8.5.45/webapps/Books/images/";
		String time = Util.getTime2();
		String imgpath = time+".jpg";
		File file = new File(savapath+imgpath);
		Util.copyFile(uploadfile, file);
		
		Date createtime=new Date();	
		Books  bean=new Books();
		bean.setBianhao(new Date().getTime()+"");
		bean.setName(name);
		bean.setMiaoshu(miaoshu);
		bean.setPrice(price);
		bean.setCb_time(cb_time);
		bean.setCbs(cbs);
		bean.setZuozhe(zuozhe);
		bean.setFenlei(fenleiDao.selectBean(" where id="+fenlei));
	    bean.setImgpath(imgpath);
		bean.setCreatetime(createtime);
        bean.setStatus("未借");
        bean.setZt(zt);
		booksDao.insertBean(bean);
		response.setCharacterEncoding("utf8");
		PrintWriter writer=response.getWriter();
		writer.print("<script language='javascript'>alert('提交成功');window.location.href='method!bookslist'; </script> ");

	   }
	
	
	//跳转到更新图书页面
	public String booksupdate(){
		HttpServletRequest request = ServletActionContext.getRequest(); 
		String id = request.getParameter("id");
		Books bean =booksDao.selectBean(" where id= "+id);
		request.setAttribute("bean", bean);
		List<Fenlei> list = fenleiDao.selectBeanList(0, 999, " where fenleilock=0 ");
		request.setAttribute("list", list);
		this.setUrl("books/booksupdate.jsp");
		return SUCCESS;
	}

	
	//更新图书操作
	public void booksupdate2() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String name=request.getParameter("name");
		String miaoshu=request.getParameter("miaoshu");
		Integer price = Integer.parseInt(request.getParameter("price"));
		String cb_time=request.getParameter("cb_time");
		String cbs=request.getParameter("cbs");
		String zuozhe=request.getParameter("zuozhe");
		String fenlei=request.getParameter("fenlei");
		String zt=request.getParameter("zt");
		Date createtime=new Date();	
		String id = request.getParameter("id");
		Books bean =booksDao.selectBean(" where id= "+id);
		bean.setBianhao(new Date().getTime()+"");
		bean.setName(name);
		bean.setMiaoshu(miaoshu);
		bean.setPrice(price);
		bean.setCb_time(cb_time);
		bean.setCbs(cbs);
		bean.setZuozhe(zuozhe);
		 bean.setZt(zt);
		bean.setFenlei(fenleiDao.selectBean(" where id="+fenlei));
		bean.setCreatetime(createtime);
		booksDao.updateBean(bean);
		response.setCharacterEncoding("utf8");
		PrintWriter writer = response.getWriter();
		writer.print("<script  language='javascript'>alert('提交成功');window.location.href='method!bookslist'; </script>");
		
	}
	
	//跳转到图书详情页面
	public String booksupdate3(){
		HttpServletRequest request = ServletActionContext.getRequest(); 
		String id = request.getParameter("id");
		Books bean =booksDao.selectBean(" where id= "+id);
		request.setAttribute("bean", bean);
		this.setUrl("books/booksupdate3.jsp");
		return SUCCESS;
	}
	
	
	//图书借阅
	public String borrowlist(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String name=request.getParameter("name");
		String bianhao=request.getParameter("bianhao");
		String cbs=request.getParameter("cbs");
		String zuozhe=request.getParameter("zuozhe");
		String fenlei=request.getParameter("fenlei");
		
        StringBuffer sb = new StringBuffer();
        sb.append(" where ");
        if(name!=null&&!"".equals(name) ){
        	sb.append("name like '%"+name+"%'");
        	sb.append(" and ");
        	request.setAttribute("name", name);
        	Books b=booksDao.selectBean(" where name like '%"+name+"%'  ");
        	List<Books> bookslist = booksDao.selectBeanList(0, 2, " where fenlei="+b.getFenlei().getId()+"   " );//同类别推荐
    		request.setAttribute("bookslist", bookslist);
        }
        if(bianhao!=null&&!"".equals(bianhao) ){
        	sb.append("bianhao like '%"+bianhao+"%'");
        	sb.append(" and ");
        	request.setAttribute("bianhao", bianhao);
        	Books b=booksDao.selectBean(" where bianhao like '%"+bianhao+"%'  ");
        	List<Books> bookslist = booksDao.selectBeanList(0, 2, " where fenlei="+b.getFenlei().getId()+"   " );//同类别推荐
        	request.setAttribute("bookslist", bookslist);
        }
        if(fenlei!=null&&!"".equals(fenlei) ){
        	sb.append("fenlei.id like '%"+fenlei+"%'");
        	sb.append(" and ");
        	request.setAttribute("fenlei", fenlei);
        }
        if(cbs!=null&&!"".equals(cbs) ){
        	sb.append("cbs like '%"+cbs+"%'");
        	sb.append(" and ");
        	request.setAttribute("cbs", cbs);
        }
        if(zuozhe!=null&&!"".equals(zuozhe) ){
        	sb.append("zuozhe like '%"+zuozhe+"%'");
        	sb.append(" and ");
        	request.setAttribute("zuozhe", zuozhe);
        }
        sb.append("  bookslock=0 order by id desc");
		int currentpage = 1;
		int pagesize = 10;
		if(request.getParameter("pagenum") != null){
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		String where =sb.toString();
		long total = booksDao.selectBeanCount(where.replaceAll(" order by id desc", ""));
		List<Books> list = booksDao.selectBeanList((currentpage-1)*pagesize, pagesize, where);
		request.setAttribute("list", list);
		List<Fenlei> list2 = fenleiDao.selectBeanList(0, 999, " where fenleilock=0 ");
		request.setAttribute("list2", list2);
		
		
		
		
		String pagerinfo = Pager.getPagerNormal((int)total, pagesize, currentpage, "method!borrowlist", "共有"+total+"条记录");
		request.setAttribute("pagerinfo", pagerinfo);
		this.setUrl("borrow/borrowlist.jsp");
		return SUCCESS;
	}
	
	
	//跳转到借书页面
	public String borrowadd(){
		HttpServletRequest request = ServletActionContext.getRequest(); 
		String id = request.getParameter("id");
		Books bean =booksDao.selectBean(" where id= "+id);
		request.setAttribute("bean", bean);
		this.setUrl("borrow/borrowadd.jsp");
		return SUCCESS;
	}


	
	//添加借书操作
	public void borrowadd2() throws IOException, java.text.ParseException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");	
		String b_createtime=request.getParameter("b_createtime");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date=sdf.parse(b_createtime); 
		Integer botime = Integer.parseInt(request.getParameter("botime"));
		
		String id = request.getParameter("id");
		Borrow bean =new Borrow();
		bean.setDanhao(new Date().getTime()+"");
		bean.setUser(user);
		bean.setBooks(booksDao.selectBean(" where id="+id));
		bean.setB_createtime(date);
		bean.setBotime(botime);
		Date newDate = addDate(bean.getB_createtime(), bean.getBotime()); 
        bean.setRe_createtime(newDate);
		borrowDao.insertBean(bean);	
		Books b=booksDao.selectBean(" where id="+bean.getBooks().getId());
		b.setStatus("已借");
		booksDao.updateBean(b);
		response.setCharacterEncoding("utf8");
		PrintWriter writer = response.getWriter();
		writer.print("<script  language='javascript'>alert('提交成功');window.location.href='method!borrowlist'; </script>");
		
	}
	
	
	//图书借还列表
	public String ret_borrowlist(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");	
        StringBuffer sb = new StringBuffer();
        sb.append(" where ");
        if(user.getRole()==1){
          	 sb.append("  borrowlock=0  and books.status='已借' and stauts=0 order by id desc");
         }
        if(user.getRole()==2){
        	 sb.append("  borrowlock=0  and userid="+user.getId()+" and books.status='已借'  and stauts=0 order by id desc");
        }
		int currentpage = 1;
		int pagesize = 10;
		if(request.getParameter("pagenum") != null){
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		String where =sb.toString();
		long total = borrowDao.selectBeanCount(where.replaceAll(" order by id desc", ""));
		List<Borrow> list = borrowDao.selectBeanList((currentpage-1)*pagesize, pagesize, where);
		request.setAttribute("list", list);
		String pagerinfo = Pager.getPagerNormal((int)total, pagesize, currentpage, "method!ret_borrowlist", "共有"+total+"条记录");
		request.setAttribute("pagerinfo", pagerinfo);
		this.setUrl("borrow/ret_borrowlist.jsp");
		return SUCCESS;
	}
	
	/**时间添加*/
	public static Date addDate(Date d,long day) throws ParseException {
		  long time = d.getTime(); 
		  day = day*24*60*60*1000; 
		  time+=day; 
		  return new Date(time);
}
	
	
	//添加还书操作
	public void ret_borrowupdate() throws IOException, java.text.ParseException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String id = request.getParameter("id");
		Borrow bean =borrowDao.selectBean(" where id="+id);

		//计算逾期天数
		java.util.Date now = new Date();
		long l=bean.getRe_createtime().getTime()-now.getTime();
		long day=l/(24*60*60*1000);
		if(day>=0){
			 bean.setRetime(0);
			 bean.setNum(0);
		}
		else if(day<0){
			bean.setRetime(day);
	        bean.setNum(day);  
		} 
		bean.setBack_createtime(now);
		bean.setStauts(1);
		borrowDao.updateBean(bean);
		
		Books b=booksDao.selectBean(" where id="+bean.getBooks().getId());
		b.setStatus("未借");
		booksDao.updateBean(b);
		response.setCharacterEncoding("utf8");
		PrintWriter writer = response.getWriter();
		writer.print("<script  language='javascript'>alert('提交成功');window.location.href='method!ret_borrowlist'; </script>");
		
	}
	
	//历史借书记录(用户)
	public String user_borrowlist(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");	
        StringBuffer sb = new StringBuffer();
        sb.append(" where ");
        
        sb.append("  borrowlock=0  and userid="+user.getId()+"  order by id ");
		int currentpage = 1;
		int pagesize = 10;
		if(request.getParameter("pagenum") != null){
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		String where =sb.toString();
		long total = borrowDao.selectBeanCount(where.replaceAll(" order by id desc", ""));
		List<Borrow> list = borrowDao.selectBeanList((currentpage-1)*pagesize, pagesize, where);
		request.setAttribute("list", list);
		String pagerinfo = Pager.getPagerNormal((int)total, pagesize, currentpage, "method!user_borrowlist", "共有"+total+"条记录");
		request.setAttribute("pagerinfo", pagerinfo);
		this.setUrl("borrow/user_borrowlist.jsp");
		return SUCCESS;
	}
	
	
	//历史借书记录(管理员)
	public String admin_borrowlist(){
		HttpServletRequest request = ServletActionContext.getRequest();
		
        StringBuffer sb = new StringBuffer();
        sb.append(" where ");
        
        sb.append("  borrowlock=0   order by id desc");
		int currentpage = 1;
		int pagesize = 10;
		if(request.getParameter("pagenum") != null){
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		String where =sb.toString();
		long total = borrowDao.selectBeanCount(where.replaceAll(" order by id desc", ""));
		List<Borrow> list = borrowDao.selectBeanList((currentpage-1)*pagesize, pagesize, where);
		request.setAttribute("list", list);
		String pagerinfo = Pager.getPagerNormal((int)total, pagesize, currentpage, "method!admin_borrowlist", "共有"+total+"条记录");
		request.setAttribute("pagerinfo", pagerinfo);
		this.setUrl("borrow/admin_borrowlist.jsp");
		return SUCCESS;
	}
	
	//逾期管理(管理员)
	public String yuqi_borrowlist(){
		HttpServletRequest request = ServletActionContext.getRequest();
		
        StringBuffer sb = new StringBuffer();
        sb.append(" where ");
        
        sb.append("  borrowlock=0  and retime<0  order by id desc");
		int currentpage = 1;
		int pagesize = 10;
		if(request.getParameter("pagenum") != null){
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		String where =sb.toString();
		long total = borrowDao.selectBeanCount(where.replaceAll(" order by id desc", ""));
		List<Borrow> list = borrowDao.selectBeanList((currentpage-1)*pagesize, pagesize, where);
		request.setAttribute("list", list);
		String pagerinfo = Pager.getPagerNormal((int)total, pagesize, currentpage, "method!yuqi_borrowlist", "共有"+total+"条记录");
		request.setAttribute("pagerinfo", pagerinfo);
		this.setUrl("borrow/yuqi_borrowlist.jsp");
		return SUCCESS;
	}
	
	/**********留言*********/
	//留言信息列表
	public String liuyanlist() throws IOException{ 
		HttpServletRequest request = ServletActionContext.getRequest();
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");
		sb.append(" liuyanlock=0  order by id desc ");
		String where = sb.toString();
		int currentpage = 1;
		int pagesize = 10;
		if(request.getParameter("pagenum") != null){
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		long total = liuyanDao.selectBeanCount(where.replaceAll("order by id desc", ""));
		List<Liuyan> list = liuyanDao.selectBeanList((currentpage-1)*pagesize, pagesize, where);
		request.setAttribute("list", list);
		String pagerinfo = Pager.getPagerNormal((int)total, pagesize, currentpage, "method!liuyanlist", "共有"+total+"条记录");
		request.setAttribute("pagerinfo", pagerinfo);
		this.setUrl("liuyan/liuyanlist.jsp");
		return SUCCESS;
	}
	
	//跳转到管理员回复页面
	public String liuyanupdate(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String id = request.getParameter("id");
		Liuyan bean =liuyanDao.selectBean(" where id= "+id);
		request.setAttribute("bean", bean);
		this.setUrl("liuyan/liuyanupdate.jsp");
		return SUCCESS;
	}
	
	//添加回复操作
	public void liuyanupdate2() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String id = request.getParameter("id");
		String recontent = request.getParameter("recontent");
		Liuyan bean = liuyanDao.selectBean(" where id="+id);
		bean.setRecontent(recontent);
		bean.setRecreatetime(new Date());
		liuyanDao.updateBean(bean);
		response.setCharacterEncoding("utf-8");response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = response.getWriter();
		writer.print("<script  language='javascript'>alert('提交成功');window.location.href='method!liuyanlist'; </script>");
		
	}
	
	//删除留言操作
	public void liuyandelete() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String id = request.getParameter("id");
		Liuyan bean =liuyanDao.selectBean(" where id= "+id);
		bean.setLiuyanlock(1);
		liuyanDao.updateBean(bean);
		response.setCharacterEncoding("utf-8");response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = response.getWriter();
		writer.print("<script  language='javascript'>alert('提交成功');window.location.href='method!liuyanlist'; </script>");
		
	}
	
	
	//留言信息列表（用户）
	public String sy_liuyan() throws IOException{ 
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");	
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");
		sb.append(" liuyanlock=0  and user="+user.getId()+"  order by id desc ");
		String where = sb.toString();
		int currentpage = 1;
		int pagesize = 5;
		if(request.getParameter("pagenum") != null){
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		long total = liuyanDao.selectBeanCount(where.replaceAll("order by id desc", ""));
		List<Liuyan> list = liuyanDao.selectBeanList((currentpage-1)*pagesize, pagesize, where);
		request.setAttribute("list", list);
		String pagerinfo = Pager.getPagerNormal((int)total, pagesize, currentpage, "method!sy_liuyan", "共有"+total+"条记录");
		request.setAttribute("pagerinfo", pagerinfo);
		this.setUrl("liuyan/sy_liuyan.jsp");
		return SUCCESS;
	}
	
	
	//添加留言操作（用户）
	public void liuyanadd() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");	
		String content = request.getParameter("content");
		Liuyan bean = new Liuyan();
		bean.setContent(content);
		bean.setUser(user);
		bean.setCreatetime(new Date());
		liuyanDao.insertBean(bean);
		response.setCharacterEncoding("utf-8");response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = response.getWriter();
		writer.print("<script  language='javascript'>alert('提交成功');window.location.href='method!sy_liuyan'; </script>");
		
	}
	
	
	
	
}
