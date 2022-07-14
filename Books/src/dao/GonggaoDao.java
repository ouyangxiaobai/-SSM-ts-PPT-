package dao;

import java.util.List;

import model.Gonggao;


public interface GonggaoDao  {
	
	
	
	public void insertBean(Gonggao Gonggao);
	
	public void deleteBean(Gonggao Gonggao);
	
	public void updateBean(Gonggao Gonggao);

	public Gonggao selectBean(String where);
	
	public List<Gonggao> selectBeanList(final int start, final int limit,final String where);
	
	public int selectBeanCount(final String where);
	
	
}
