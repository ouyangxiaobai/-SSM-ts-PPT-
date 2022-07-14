package dao;

import java.util.List;

import model.Borrow;


public interface BorrowDao  {
	
	
	
	public void insertBean(Borrow Borrow);
	
	public void deleteBean(Borrow Borrow);
	
	public void updateBean(Borrow Borrow);

	public Borrow selectBean(String where);
	
	public List<Borrow> selectBeanList(final int start, final int limit,final String where);
	
	public int selectBeanCount(final String where);
	
	
}
