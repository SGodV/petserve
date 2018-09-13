package petserve.control;

import java.sql.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import petserve.itf.IAppointmentManager;
import petserve.model.BeanAppointment;
import petserve.model.BeanOrder_information;
import petserve.model.BeanUser_information;
import petserve.util.BaseException;
import petserve.util.HibernateUtil;

public class AppointmentManager implements IAppointmentManager {

	@Override
	public void submitAppointment(int petid, int productid) throws BaseException {
		// TODO Auto-generated method stub
		Session session=HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        BeanAppointment apt = new BeanAppointment();
        apt.setProduct_id(productid);
        apt.setPet_id(petid);
        apt.setOrder_begin_time(new java.sql.Date(System.currentTimeMillis()));
        apt.setCircumstance("进行中");
		session.save(apt);
		transaction.commit();
	}

	@Override
	public void cancelAppointment(int appointmentid) throws BaseException {
		// TODO Auto-generated method stub
		Session session=HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        BeanAppointment apt = (BeanAppointment)session.get(BeanAppointment.class, appointmentid);
        session.delete(apt);
		transaction.commit();
	}

	@Override
	public  List<BeanAppointment> selectAppointment(int userid) throws BaseException {
		// TODO Auto-generated method stub
		Session session=HibernateUtil.getSession();
		String hql = "from BeanAppointment where user_id=?";
		Query qry = session.createQuery(hql);
		qry.setParameter(0, userid);
        List<BeanAppointment> lapt = qry.list();
		return lapt;
	}

	@Override
	public void finishTime(int appointmentid) throws BaseException {
		// TODO Auto-generated method stub
		Session session=HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        BeanAppointment apt = (BeanAppointment)session.get(BeanAppointment.class, appointmentid);
        apt.setCircumstance("已完成");
        apt.setOrder_end_time(new java.sql.Date(System.currentTimeMillis()));
        session.update(apt);
		transaction.commit();
	}

}
