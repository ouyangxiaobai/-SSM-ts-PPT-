package dao.impl;

import java.sql.SQLException;
import java.util.List;

import model.Gonggao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import dao.GonggaoDao;









public class GonggaoDaoImpl extends HibernateDaoSupport implements  GonggaoDao{


	public void deleteBean(Gonggao Gonggao) {
		this.getHibernateTemplate().delete(Gonggao);
		
	}

	public void insertBean(Gonggao Gonggao) {
		this.getHibernateTemplate().save(Gonggao);
		
	}

	@SuppressWarnings("unchecked")
	public Gonggao selectBean(String where) {
		List<Gonggao> list = this.getHibernateTemplate().find("from Gonggao " +where);
		if(list.size()==0){
			return null;
		}
		return list.get(0);
	}

	public int selectBeanCount(String where) {
		long count = (Long)this.getHibernateTemplate().find("select count(*) from Gonggao "+where).get(0);
		return (int)count;
	}

	@SuppressWarnings("unchecked")
	public List<Gonggao> selectBeanList(final int start,final int limit,final String where) {
		return (List<Gonggao>)this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(final Session session)throws HibernateException, SQLException {				
				List<Gonggao> list = session.createQuery("from Gonggao "+where)
				.setFirstResult(start)
				.setMaxResults(limit)
				.list();
				return list;
			}
		});
	}

	public void updateBean(Gonggao Gonggao) {
		this.getHibernateTemplate().update(Gonggao);
		
	}
	
	
}
