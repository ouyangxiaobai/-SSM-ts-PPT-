package dao;

import java.util.List;

import model.Fenlei;


public interface FenleiDao  {
	
	
	
	public void insertBean(Fenlei Fenlei);
	
	public void deleteBean(Fenlei Fenlei);
	
	public void updateBean(Fenlei Fenlei);

	public Fenlei selectBean(String where);
	
	public List<Fenlei> selectBeanList(final int start, final int limit,final String where);
	
	public int selectBeanCount(final String where);
	
	
}
