package petserve.control;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import petserve.itf.IUserManager;
import petserve.model.BeanUser_information;
import petserve.util.BaseException;
import petserve.util.HibernateUtil;

public class UserManager implements IUserManager {

	@Override
	public BeanUser_information reg(String userPhone, String pwd, String pwd2, String userName, String userEmail, String otherContact) throws BaseException {
		// TODO Auto-generated method stub
		if(userPhone==null || "".equals(userPhone)) throw new BaseException("用户名不能为空");
		if(pwd==null || "".equals(pwd)) throw new BaseException("密码不能为空");
		if(!pwd.equals(pwd2)) throw new BaseException("两次输入的密码要一致");
		int phone = Integer.valueOf(userPhone).intValue();
		HibernateUtil hib=new HibernateUtil();
		Session session=hib.getSession();
		Transaction transaction = session.beginTransaction();
		String hql="from user_information where phone_number=" + userPhone;
        Query qry=session.createQuery(hql);
        BeanUser_information u=(BeanUser_information)qry.uniqueResult();
        if(u!=null)
		{
			throw new BaseException("用户已经存在");
		}
		BeanUser_information cu = new BeanUser_information();
		cu.setPhone_number(phone);
		cu.setPassward(pwd);
		cu.setUser_name(userName);
		cu.setEmail(userEmail);
		cu.setOther_contact(otherContact);
		cu.setAuthority((short) 1);
		session.save(cu);
		transaction.commit();
		return cu;
	}
	
	@Override
	public BeanUser_information regAdmin(String adminPhone, String pwd, String pwd2, String adminName) throws BaseException {
		// TODO Auto-generated method stub
		if(adminPhone==null || "".equals(adminPhone)) throw new BaseException("用户名不能为空");
		if(pwd==null || "".equals(pwd)) throw new BaseException("密码不能为空");
		if(!pwd.equals(pwd2)) throw new BaseException("两次输入的密码要一致");
		int phone = Integer.valueOf(adminPhone).intValue();
		HibernateUtil hib=new HibernateUtil();
		Session session=hib.getSession();
		Transaction transaction = session.beginTransaction();
		String hql="from user_information where phone_number=" + adminPhone;
        Query qry=session.createQuery(hql);
        BeanUser_information u=(BeanUser_information)qry.uniqueResult();
        if(u!=null)
		{
			throw new BaseException("用户已经存在");
		}
		BeanUser_information cu = new BeanUser_information();
		cu.setPhone_number(phone);
		cu.setPassward(pwd);
		cu.setUser_name(adminName);
		cu.setAuthority((short) 0);
		session.save(cu);
		transaction.commit();
		return cu;
	}

	@Override
	public BeanUser_information login(String userid, String pwd) throws BaseException {
		// TODO Auto-generated method stub
		int userPhone = Integer.valueOf(userid).intValue(); 
		HibernateUtil hib=new HibernateUtil();
		Session session=hib.getSession();
        String hql="from user_information where phone_number=" + userPhone;
        Query qry=session.createQuery(hql);
        BeanUser_information u=(BeanUser_information)qry.uniqueResult();

		if(u==null)
		{
			throw new BaseException("用户不存在");
		}
		BeanUser_information us=new BeanUser_information();
		us.setPhone_number(userPhone);
		us.setPassward(u.getPassward());
		if(!us.getPassward().equals(pwd)){
			throw new BaseException("密码错误");
		}
		us.setEmail(u.getEmail());
		us.setOther_contact(u.getOther_contact());
		us.setUser_id(u.getUser_id());
		us.setUser_name(u.getUser_name());
		us.setAuthority(u.getAuthority());
		
		return us;
	}

	@Override
	public void changePwd(BeanUser_information user, String oldPwd, String newPwd, String newPwd2)
			throws BaseException {
		// TODO Auto-generated method stub
		
	}

	

}
