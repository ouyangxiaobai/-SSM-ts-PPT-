package dao.impl;

import java.sql.SQLException;
import java.util.List;

import model.Books;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import dao.BooksDao;









public class BooksDaoImpl extends HibernateDaoSupport implements  BooksDao{


	public void deleteBean(Books Books) {
		this.getHibernateTemplate().delete(Books);
		
	}

	public void insertBean(Books Books) {
		this.getHibernateTemplate().save(Books);
		
	}

	@SuppressWarnings("unchecked")
	public Books selectBean(String where) {
		List<Books> list = this.getHibernateTemplate().find("from Books " +where);
		if(list.size()==0){
			return null;
		}
		return list.get(0);
	}

	public int selectBeanCount(String where) {
		long count = (Long)this.getHibernateTemplate().find("select count(*) from Books "+where).get(0);
		return (int)count;
	}

	@SuppressWarnings("unchecked")
	public List<Books> selectBeanList(final int start,final int limit,final String where) {
		return (List<Books>)this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(final Session session)throws HibernateException, SQLException {				
				List<Books> list = session.createQuery("from Books "+where)
				.setFirstResult(start)
				.setMaxResults(limit)
				.list();
				return list;
			}
		});
	}

	public void updateBean(Books Books) {
		this.getHibernateTemplate().update(Books);
		
	}
	
	
}
