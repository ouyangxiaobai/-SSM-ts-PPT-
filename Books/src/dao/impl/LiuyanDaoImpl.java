package dao.impl;
import java.sql.SQLException;
import java.util.List;

import model.Liuyan;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import dao.LiuyanDao;

public class LiuyanDaoImpl extends HibernateDaoSupport implements LiuyanDao {

	

	public void deleteBean(Liuyan bean) {
		this.getHibernateTemplate().delete(bean);
		
	}

	public void insertBean(Liuyan bean) {
		this.getHibernateTemplate().save(bean);
		
	}

	@SuppressWarnings("unchecked")
	public Liuyan selectBean(String where) {
		List<Liuyan> list = this.getHibernateTemplate().find("from Liuyan "+where);
		if(list.size()==0){
			return null;
		}
		return list.get(0);
	}

	public long selectBeanCount(final String where) {
		long count = (Long)this.getHibernateTemplate().find(" select count(*) from Liuyan  "+where).get(0);
		return count;
	}

	@SuppressWarnings("unchecked")
	public List<Liuyan> selectBeanList(final int start,final int limit,final String where) {
		return (List<Liuyan>)this.getHibernateTemplate().executeFind(new HibernateCallback(){

			public Object doInHibernate(final Session session) throws HibernateException, SQLException {
				List<Liuyan> list = session.createQuery(" from Liuyan"+where).setFirstResult(start).setMaxResults(limit).list();
				return list;
			}
		});
		
	}

	public void updateBean(Liuyan bean) {
		this.getHibernateTemplate().update(bean);
		
	}
	
	
	
	
	
	
	
}
