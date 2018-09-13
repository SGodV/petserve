package petserve.control;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import petserve.itf.IProductTypeManager;
import petserve.model.BeanProducts_information;
import petserve.model.BeanProducts_types;
import petserve.model.BeanUser_information;
import petserve.util.BaseException;
import petserve.util.HibernateUtil;

public class ProductTypeManager implements IProductTypeManager {

	@Override
	public List<BeanProducts_types> loadProductType() throws BaseException {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		List<BeanProducts_types> result = session.createQuery("from BeanProducts_types").list();
		transaction.commit();
		return result;
	}

	@Override
	public void addPdtType(String typeName, String typeDescribe) throws BaseException {
		// TODO Auto-generated method stub
		if(typeName==null || "".equals(typeName))
			throw new BaseException("类型名称不能为空");
		
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		String hql = "from BeanProducts_types where type_name=?";
		Query qry = session.createQuery(hql);
		qry.setParameter(0, typeName);
		BeanProducts_types pt = new BeanProducts_types();
		pt = (BeanProducts_types)qry.uniqueResult();
        if(pt!=null)
			throw new BaseException("商品类型已经存在");
        BeanProducts_types pdtType = new BeanProducts_types();
		pdtType.setType_name(typeName);
		pdtType.setType_describe(typeDescribe);
		session.save(pdtType);
		transaction.commit();
	}

	@Override
	public void changePdtTypeName(int typeCode, String typeName) throws BaseException {
		// TODO Auto-generated method stub
		if(typeName==null || "".equals(typeName))
			throw new BaseException("类型名称不能为空");
		
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		BeanProducts_types pt = (BeanProducts_types)session.get(BeanProducts_types.class, typeCode);
		pt.setType_name(typeName);
		session.update(pt);
		transaction.commit();
	}

	@Override
	public void changePdtTypeDsc(int typeCode, String typeDescribe) throws BaseException {
		// TODO Auto-generated method stub
		if(typeDescribe==null || "".equals(typeDescribe))
			throw new BaseException("类型描述不能为空");
		
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		BeanProducts_types pt = (BeanProducts_types)session.get(BeanProducts_types.class, typeCode);
		pt.setType_describe(typeDescribe);
		session.update(pt);
		transaction.commit();
	}

	@Override
	public void deletePdtType(int typeCode) throws BaseException {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSession();
		String hql = "from BeanProducts_information where type_code=?";
		Query qry = session.createQuery(hql);
		qry.setParameter(0, typeCode);
		BeanProducts_information pi = new BeanProducts_information();
		pi = (BeanProducts_information)qry.uniqueResult();
        if(pi!=null)
			throw new BaseException("商品类型下面有商品条目，拒绝删除");
        Transaction transaction = session.beginTransaction();
		BeanProducts_types pt = (BeanProducts_types)session.get(BeanProducts_types.class, typeCode);
		session.delete(pt);
		transaction.commit();
	}

}
