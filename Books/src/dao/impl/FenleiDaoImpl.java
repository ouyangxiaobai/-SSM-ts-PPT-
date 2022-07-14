package dao.impl;

import java.sql.SQLException;
import java.util.List;

import model.Fenlei;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import dao.FenleiDao;



public class FenleiDaoImpl extends HibernateDaoSupport implements  FenleiDao{


	public void deleteBean(Fenlei Fenlei) {
		this.getHibernateTemplate().delete(Fenlei);
		
	}

	public void insertBean(Fenlei Fenlei) {
		this.getHibernateTemplate().save(Fenlei);
		
	}

	@SuppressWarnings("unchecked")
	public Fenlei selectBean(String where) {
		List<Fenlei> list = this.getHibernateTemplate().find("from Fenlei " +where);
		if(list.size()==0){
			return null;
		}
		return list.get(0);
	}

	public int selectBeanCount(String where) {
		long count = (Long)this.getHibernateTemplate().find("select count(*) from Fenlei "+where).get(0);
		return (int)count;
	}

	@SuppressWarnings("unchecked")
	public List<Fenlei> selectBeanList(final int start,final int limit,final String where) {
		return (List<Fenlei>)this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(final Session session)throws HibernateException, SQLException {				
				List<Fenlei> list = session.createQuery("from Fenlei "+where)
				.setFirstResult(start)
				.setMaxResults(limit)
				.list();
				return list;
			}
		});
	}

	public void updateBean(Fenlei Fenlei) {
		this.getHibernateTemplate().update(Fenlei);
		
	}
	
	
}
