package util;

import java.io.File;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class test01 {
	//HttpServletRequest request = ServletActionContext.getRequest();
	//HttpServletResponse response = ServletActionContext.getResponse();
	public static void main(String[] args) {
		
	
		HttpServletResponse response = ServletActionContext.getResponse();
		//	List<Shiti> list = new ArrayList<Shiti>();
			File importExcel = new File("D:\\Book1.xls");
		     //这里确认这个文件是存在的,当然可以判断一下.用file.exists().看其是否存在,   
		     /**(第一步完成)*/  
		        
		     //(第二:)然后就先来连接数据库吧.同样是对同一个数据库操作,只是这里新增加一个表.见最下面.   
		     try{   
		         //数据库连接   
		          Class.forName("com.mysql.jdbc.Driver").newInstance();   
		          Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/books_db", "root", "123456");
		          PreparedStatement prep = conn.prepareStatement("insert into t_shiti (wenti,daan,shitilock) values (?,?,?)");   
		          System.out.println("数据库连接成功");
		         //这里是插入,用这个来的比较方便,所以就直接用这个接口来操作吧.   
		         //System.out.println("Success");   
		         /**(第二步完成)*/  
		            
		            
		         //(第三步:)操作Excel文件读出里面的数据,其实思想是一样的,就是普通思维,   
		         //获取Excel文件---->读取每一个工作单元(就跟前一个导入一样的是每一个整体表格)---->读取(一个表格)每一行的内容------(读取完成.)   
		            
		         //首先是获取文件,也就是上面的Excel文件,这里是或者这个Excel文件.然后就是对之进行操作.   
		          Workbook workBook = Workbook.getWorkbook(importExcel);   
		          System.out.println("创建workBook成功");  
		         //其次就是获取这个Excel文件的工作表格.这里就基本处理,当然可以不用数组形式,因为只有第一个工作表格有数据,其他两个没有,   
		         //但是为了一般化,就仍然这样操作.   
		          Sheet[] sheet = workBook.getSheets();   
		          System.out.println("创建sheet成功");  
		         //这里大家看一下API就能够知道,其实可以通过参数来获得哪一个表格就行了,   
		         //比如Sheet sheet = workBook.getSheet(1);   
		            
		            
		         //这里就到了最后了,这里的最后就是对于工作表格的最后,既然是用数组来得到的,当然会对每一个工作表格进行读取操作,   
		         //这里的读取就是读取每一个工作表格的每一行数据.   
		         int sheet_i_num = 0;//获取工作表格的行数   
		          String wenti = "";   
		          String daan = "";//用来得到每一个单元格的内容,下面用到.   
		          String shitilock = "0";
		         
		          System.out.println("自定义成功");  
		         if(sheet!=null&&sheet.length>0){//判断一下   
		             for(int sheetNum=0;sheetNum < sheet.length; sheetNum++){//获得有多少个工作表格,对每一个操作.   
		                 //(3.1这里首先要得到要读取的工作表格有多少行.)   
		                  sheet_i_num = sheet[sheetNum].getRows();   
		                  System.out.println("获取行数成功");  
		                    
		                 //接下来就是对每一行进行的去数据了,此处从rowNum = 1开始,第一行一般是标题   
		                 for(int rowNum = 1; rowNum < sheet_i_num ; rowNum++){   
		                     //这里就开始对每一个单元格进行操作了.   
		                     //显然,sheet[]第一个参数就是哪一个工作表格,然后getRow的就是哪一行.然后就赋值给Cell进行操作.   
		                      Cell[] cells = sheet[sheetNum].getRow(rowNum);  
		                 //     Shiti shiti=new Shiti();
		                      System.out.println("开始执行循环成功");  
		                     //这里就开始读出每一行的数据了,这里不做其他的判断,比如,数据是整数否,是否超出字符串长度,是否为空等等,   
		                  //    shiti.setWenti(cells[0].getContents());
		                   //   shiti.setDaan(cells[1].getContents());
		                    //  shiti.setShitilock(Integer.parseInt((cells[2].getContents())));
		                      wenti= cells[0].getContents();   
		                      daan = cells[1].getContents();//这里就是对每一个列来获取,cells就是把这一行的某一列赋值给你所要操作的值.   
		                      shitilock  =cells[2].getContents();
		                      System.out.println("获取每个参数成功");  
	                        //  list.add(shiti); 
		                      System.out.println("每个定义的属性插入成功");
		                     //数据是取到了,然后就是直接插入到数据库当中了,   
		                      prep.setString(1, wenti);   
		                      prep.setString(2, daan);   
		                      prep.setInt(3, Integer.parseInt(shitilock)); 
		                     
		                      prep.executeUpdate();   
		                      System.out.println("插入成功");   
		                  }   
		              }   
		          }   
		       
		        response.setCharacterEncoding("utf8");
		 		PrintWriter writer = response.getWriter();
		 		writer.print("<script  language='javascript'>alert('插入成功');window.location.href='method!shitilist'; </script>");
		 		
		          workBook.close();   
		          prep.close();   
		          conn.close();   
		      }catch(Exception e){   
		          System.out.println("插入失败");   
		      }   
		     finally{   
		            
		      }   
}
}

