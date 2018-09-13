package petserve.control;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import petserve.itf.IUserManager;
import petserve.model.BeanProducts_types;
import petserve.model.BeanUser_information;
import petserve.util.BaseException;
import petserve.util.BusinessException;
import petserve.util.HibernateUtil;

public class UserManager implements IUserManager {

	@Override
	public BeanUser_information reg(String userPhone, String pwd, String pwd2, String userName, String userEmail, String otherContact) throws BaseException {
		// TODO Auto-generated method stub
		if(userPhone==null || "".equals(userPhone))
			throw new BaseException("�ֻ����벻��Ϊ��");
		if(pwd==null || "".equals(pwd))
			throw new BaseException("���벻��Ϊ��");
		if(!pwd.equals(pwd2))
			throw new BaseException("�������������Ҫһ��");
		if(userName==null || "".equals(userPhone))
			throw new BaseException("�ǳƲ���Ϊ��");
		if(userEmail==null || "".equals(userEmail))
			throw new BaseException("���䲻��Ϊ��");
		if(userEmail.indexOf("@") <= 0)
			throw new BaseException("��������ȷ������");
		
		Session session = HibernateUtil.getSession();
		String hql = "from BeanUser_information where phone_number=?";
		Query qry = session.createQuery(hql);
		qry.setParameter(0, userPhone);
        BeanUser_information u = (BeanUser_information)qry.uniqueResult();
        if(u!=null)
			throw new BaseException("�û��Ѿ�����");
    	Transaction transaction = session.beginTransaction();
		BeanUser_information cu = new BeanUser_information();
		cu.setPhone_number(userPhone);
		cu.setPassword(pwd);
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
		if(adminPhone==null || "".equals(adminPhone))
			throw new BaseException("�û�������Ϊ��");
		if(pwd==null || "".equals(pwd))
			throw new BaseException("���벻��Ϊ��");
		if(!pwd.equals(pwd2))
			throw new BaseException("�������������Ҫһ��");
		
		Session session = HibernateUtil.getSession();
		String hql="from BeanUser_information where phone_number=?";
		Query qry=session.createQuery(hql);
		qry.setParameter(0, adminPhone);
        BeanUser_information u=(BeanUser_information)qry.uniqueResult();
        if(u!=null)
			throw new BaseException("�û��Ѿ�����");
		Transaction transaction = session.beginTransaction();
		BeanUser_information cu = new BeanUser_information();
		cu.setPhone_number(adminPhone);
		cu.setPassword(pwd);
		cu.setUser_name(adminName);
		cu.setAuthority((short) 0);
		session.save(cu);
		transaction.commit();
		return cu;
	}

	@Override
	public BeanUser_information login(String userid, String pwd) throws BaseException {
		// TODO Auto-generated method stub
		if(userid==null || "".equals(userid)) 
			throw new BaseException("�û�������Ϊ��");
		if(pwd==null || "".equals(pwd)) 
			throw new BaseException("���벻��Ϊ��");
		if(userid.length() != 11) 
			throw new BaseException("��������ȷ���ֻ�����");
		
		Session session = HibernateUtil.getSession();
        String hql="from BeanUser_information where phone_number=?";
        Query qry=session.createQuery(hql);
        qry.setParameter(0, userid);
        BeanUser_information u = new BeanUser_information();
		u = (BeanUser_information)qry.uniqueResult();
		if(u==null)
			throw new BaseException("�û�������");
		BeanUser_information us = new BeanUser_information();
		us.setPhone_number(userid);
		us.setPassword(u.getPassword());
		if(!us.getPassword().equals(pwd))
			throw new BaseException("�������");
		us.setEmail(u.getEmail());
		us.setOther_contact(u.getOther_contact());
		us.setUser_id(u.getUser_id());
		us.setUser_name(u.getUser_name());
		us.setAuthority(u.getAuthority());
		
		return us;
	}

	@Override
	public void changePwd(String oldPwd, String newPwd, String newPwd2)
			throws BaseException {
		// TODO Auto-generated method stub
		if(oldPwd==null || "".equals(oldPwd))
			throw new BaseException("�����벻��Ϊ��");
		if(!oldPwd.equals(BeanUser_information.currentLoginUser.getPassword()))
			throw new BaseException("�����벻��ȷ");
		if(newPwd==null || "".equals(newPwd))
			throw new BaseException("�����벻��Ϊ��");
		if(!newPwd.equals(newPwd2))
			throw new BaseException("�������������Ҫһ��");
		
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		BeanUser_information u = (BeanUser_information)session.get(BeanUser_information.class, BeanUser_information.currentLoginUser.getUser_id());
		u.setPassword(newPwd);
		session.update(u);
		BeanUser_information.currentLoginUser = (BeanUser_information)session.get(BeanUser_information.class, u.getUser_id());
		transaction.commit();
	}

	@Override
	public void changeUserName(String userName) throws BaseException {
		// TODO Auto-generated method stub
		if(userName==null || "".equals(userName))
			throw new BaseException("�ǳƲ���Ϊ��");
		
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		BeanUser_information u = (BeanUser_information)session.get(BeanUser_information.class, BeanUser_information.currentLoginUser.getUser_id());
		u.setUser_name(userName);
		session.update(u);
		BeanUser_information.currentLoginUser = (BeanUser_information)session.get(BeanUser_information.class, u.getUser_id());
		transaction.commit();
	}

	@Override
	public void changePhone(String userPhone) throws BaseException {
		// TODO Auto-generated method stub
		if(userPhone.length() != 11) 
			throw new BaseException("��������ȷ���ֻ�����");
		
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		BeanUser_information u = (BeanUser_information)session.get(BeanUser_information.class, BeanUser_information.currentLoginUser.getUser_id());
		u.setPhone_number(userPhone);
		session.update(u);
		BeanUser_information.currentLoginUser = (BeanUser_information)session.get(BeanUser_information.class, u.getUser_id());
		transaction.commit();
	}

	@Override
	public void changeEmail(String userEmail) throws BaseException {
		// TODO Auto-generated method stub
		if(userEmail.indexOf("@") <= 0)
			throw new BaseException("��������ȷ������");
			
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		BeanUser_information u = (BeanUser_information)session.get(BeanUser_information.class, BeanUser_information.currentLoginUser.getUser_id());
		u.setEmail(userEmail);
		session.update(u);
		BeanUser_information.currentLoginUser = (BeanUser_information)session.get(BeanUser_information.class, u.getUser_id());
		transaction.commit();
	}

	@Override
	public void changeOtherContect(String otherContect) throws BaseException {
		// TODO Auto-generated method stub
		if(otherContect==null || "".equals(otherContect))
			throw new BaseException("��ϵ��ʽ����Ϊ��");
		
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		BeanUser_information u = (BeanUser_information)session.get(BeanUser_information.class, BeanUser_information.currentLoginUser.getUser_id());
		u.setOther_contact(otherContect);
		session.update(u);
		BeanUser_information.currentLoginUser = (BeanUser_information)session.get(BeanUser_information.class, u.getUser_id());
		transaction.commit();
	}

	@Override
	public List<BeanUser_information> loadUser() throws BaseException {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSession();
		String hql = "from BeanUser_information where authority=1";
		Query qry = session.createQuery(hql);
		List<BeanUser_information> result = qry.list();
		return result;
	}

	@Override
	public List<BeanUser_information> selectUser(String name) throws BaseException {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSession();
		String hql = "from BeanUser_information where user_name like ? and authority=1";
		Query qry = session.createQuery(hql);
		qry.setParameter(0, "%"+name+"%");
		List<BeanUser_information> result = qry.list();
		return result;
	}

}
