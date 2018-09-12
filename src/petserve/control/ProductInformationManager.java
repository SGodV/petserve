package petserve.control;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import petserve.itf.IProductInformationManager;
import petserve.model.BeanProducts_information;
import petserve.model.BeanProducts_types;
import petserve.util.BaseException;
import petserve.util.HibernateUtil;

public class ProductInformationManager implements IProductInformationManager {

	@Override
	public List<BeanProducts_information> loadProductInformation() throws BaseException {
		// TODO Auto-generated method stub
		HibernateUtil hib=new HibernateUtil();
		Session session=hib.getSession();
		Transaction transaction = session.beginTransaction();
		List<BeanProducts_information> result = session.createQuery("from BeanProducts_information").list();
		transaction.commit();
		return result;
	}

}
