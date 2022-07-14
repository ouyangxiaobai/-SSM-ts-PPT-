package dao.impl;

import java.sql.SQLException;
import java.util.List;

import model.Borrow;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import dao.BorrowDao;









public class BorrowDaoImpl extends HibernateDaoSupport implements  BorrowDao{


	public void deleteBean(Borrow Borrow) {
		this.getHibernateTemplate().delete(Borrow);
		
	}

	public void insertBean(Borrow Borrow) {
		this.getHibernateTemplate().save(Borrow);
		
	}

	@SuppressWarnings("unchecked")
	public Borrow selectBean(String where) {
		List<Borrow> list = this.getHibernateTemplate().find("from Borrow " +where);
		if(list.size()==0){
			return null;
		}
		return list.get(0);
	}

	public int selectBeanCount(String where) {
		long count = (Long)this.getHibernateTemplate().find("select count(*) from Borrow "+where).get(0);
		return (int)count;
	}

	@SuppressWarnings("unchecked")
	public List<Borrow> selectBeanList(final int start,final int limit,final String where) {
		return (List<Borrow>)this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(final Session session)throws HibernateException, SQLException {				
				List<Borrow> list = session.createQuery("from Borrow "+where)
				.setFirstResult(start)
				.setMaxResults(limit)
				.list();
				return list;
			}
		});
	}

	public void updateBean(Borrow Borrow) {
		this.getHibernateTemplate().update(Borrow);
		
	}
	
	
}
