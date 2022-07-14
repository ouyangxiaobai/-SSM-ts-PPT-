package dao;

import java.util.List;

import model.Books;


public interface BooksDao  {
	
	
	
	public void insertBean(Books Books);
	
	public void deleteBean(Books Books);
	
	public void updateBean(Books Books);

	public Books selectBean(String where);
	
	public List<Books> selectBeanList(final int start, final int limit,final String where);
	
	public int selectBeanCount(final String where);
	
	
}
