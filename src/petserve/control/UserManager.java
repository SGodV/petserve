package petserve.control;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import petserve.itf.IUserManager;
import petserve.model.BeanUser_information;
import petserve.util.BaseException;
import petserve.util.HibernateUtil;

public class UserManager implements IUserManager {

	@Override
	public BeanUser_information reg(String userPhone, String pwd, String pwd2, String userName) throws BaseException {
		// TODO Auto-generated method stub
		
		return null;
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
