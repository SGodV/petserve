package petserve.control;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import petserve.itf.IPetManager;
import petserve.model.BeanPet_information;
import petserve.model.BeanUser_information;
import petserve.util.BaseException;
import petserve.util.HibernateUtil;

public class PetManager implements IPetManager {

	@Override
	public void addPet(String petName, String typeName, byte[] picture, short age, short healthy) throws BaseException {
		// TODO Auto-generated method stub
		if(petName==null || "".equals(petName))
			throw new BaseException("昵称不能为空");
		if(typeName==null || "".equals(typeName))
			throw new BaseException("学名不能为空");
		if(picture==null)
			throw new BaseException("请上传宠物图片");
		if(age==0)
			throw new BaseException("请填写宠物年龄");
		
		Session session = HibernateUtil.getSession();
		BeanPet_information p = new BeanPet_information();
		p.setPet_name(petName);
		p.setName(typeName);
		p.setPicture(picture);
		p.setAge(age);
		p.setHealthy(healthy);
		p.setUser_id(BeanUser_information.currentLoginUser.getUser_id());
		session.save(p);
	}

	@Override
	public void deletePet(int petid) throws BaseException {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		BeanPet_information aPet=(BeanPet_information)session.get(BeanPet_information.class, petid);
		session.delete(aPet);
		transaction.commit();
	}

	@Override
	public void changePetName(int petid, String petName) throws BaseException {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		BeanPet_information aPet=(BeanPet_information)session.get(BeanPet_information.class, petid);
		aPet.setPet_name(petName);
		session.update(aPet);
		transaction.commit();
	}

	@Override
	public void changeTypeName(int petid, String typeName) throws BaseException {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		BeanPet_information aPet=(BeanPet_information)session.get(BeanPet_information.class, petid);
		aPet.setName(typeName);
		session.update(aPet);
		transaction.commit();
	}
	
	@Override
	public void changePetAge(int petid, short age) throws BaseException {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		BeanPet_information aPet=(BeanPet_information)session.get(BeanPet_information.class, petid);
		aPet.setAge(age);
		session.update(aPet);
		transaction.commit();
	}

	@Override
	public void changePetHealthy(int petid, short healthy) throws BaseException {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		BeanPet_information aPet=(BeanPet_information)session.get(BeanPet_information.class, petid);
		aPet.setHealthy(healthy);
		session.update(aPet);
		transaction.commit();
	}

	@Override
	public BeanPet_information selectAppointment_state(int petid) throws BaseException {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSession();
		BeanPet_information aPet=(BeanPet_information)session.get(BeanPet_information.class, petid);
		session.update(aPet);
		return aPet;
	}

	@Override
	public void changePetPicture(int petid, byte[] picture) throws BaseException {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		BeanPet_information aPet=(BeanPet_information)session.get(BeanPet_information.class, petid);
		aPet.setPicture(picture);
		session.update(aPet);
		transaction.commit();
	}

	@Override
	public int getPetidByName(String name) throws BaseException {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSession();
		String hql = "from BeanPet_information where pet_name=?";
		Query qry = session.createQuery(hql);
		qry.setParameter(0, name);
		BeanPet_information pet = (BeanPet_information)qry.uniqueResult();
		return pet.getPet_id();
	}

	@Override
	public List<BeanPet_information> loadPetByUser(int userid) throws BaseException {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSession();
		String hql = "from BeanPet_information where user_id=?";
		Query qry = session.createQuery(hql);
		qry.setParameter(0, userid);
		List<BeanPet_information> lpet = qry.list();
		return lpet;
	}

	@Override
	public List<BeanPet_information> loadAllPet() throws BaseException {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSession();
		String hql = "from BeanPet_information";
		Query qry = session.createQuery(hql);
		List<BeanPet_information> lpet = qry.list();
		return lpet;
	}

}
