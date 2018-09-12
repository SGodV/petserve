package petserve.control;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import petserve.itf.IProductTypeManager;
import petserve.model.BeanProducts_types;
import petserve.util.BaseException;
import petserve.util.HibernateUtil;

public class ProductTypeManager implements IProductTypeManager {

	@Override
	public List<BeanProducts_types> loadProductType() throws BaseException {
		// TODO Auto-generated method stub
		HibernateUtil hib=new HibernateUtil();
		Session session=hib.getSession();
		Transaction transaction = session.beginTransaction();
		List<BeanProducts_types> result = session.createQuery("from BeanProducts_types").list();
		transaction.commit();
		return result;
	}

}
