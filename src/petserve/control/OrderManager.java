package petserve.control;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import petserve.itf.IOrderManager;
import petserve.model.BeanOrder_information;
import petserve.model.BeanProducts_information;
import petserve.model.BeanUser_information;
import petserve.util.BaseException;
import petserve.util.HibernateUtil;

public class OrderManager implements IOrderManager {

	@Override
	public void submitOrder(int product_id, int quantity, float order_price) throws BaseException {
		// TODO Auto-generated method stub
		Session session=HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        BeanOrder_information oi = new BeanOrder_information();
        oi.setProduct_id(product_id);
        oi.setQuantity(quantity);
        oi.setBegin_time(new java.sql.Date(System.currentTimeMillis()));
        oi.setOrder_price(order_price*quantity);
        oi.setTransfer_state(0);
        oi.setUser_id(BeanUser_information.currentLoginUser.getUser_id());
		session.save(oi);
		transaction.commit();
	}

	@Override
	public void cancelOrder(int order_id) throws BaseException {
		// TODO Auto-generated method stub
		Session session=HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        BeanOrder_information oi = (BeanOrder_information)session.get(BeanOrder_information.class, order_id);
		session.delete(oi);
		transaction.commit();
	}

	@Override
	public void changeQuantity(int order_id, int quantity) throws BaseException {
		// TODO Auto-generated method stub
		Session session=HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        BeanOrder_information oi = (BeanOrder_information)session.get(BeanOrder_information.class, order_id);
        oi.setQuantity(quantity);
		session.update(oi);
		transaction.commit();
	}

	@Override
	public List<BeanOrder_information> selectOrder(int userid) throws BaseException {
		// TODO Auto-generated method stub
		Session session=HibernateUtil.getSession();
		String hql = "from BeanOrder_information where user_id=?";
		Query qry = session.createQuery(hql);
		qry.setParameter(0, userid);
        List<BeanOrder_information> loi = qry.list();
		return loi;
	}

	@Override
	public void finishOrder(int order_id) throws BaseException {
		// TODO Auto-generated method stub
		Session session=HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        BeanOrder_information oi = (BeanOrder_information)session.get(BeanOrder_information.class, order_id);
        oi.setTransfer_state(1);
        oi.setEnd_time(new java.sql.Date(System.currentTimeMillis()));
		session.update(oi);
		transaction.commit();
	}
	
}
