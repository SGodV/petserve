package petserve.control;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import petserve.itf.IProductInformationManager;
import petserve.model.BeanProducts_information;
import petserve.model.BeanProducts_types;
import petserve.model.BeanUser_information;
import petserve.util.BaseException;
import petserve.util.HibernateUtil;

public class ProductInformationManager implements IProductInformationManager {

	@Override
	public List<BeanProducts_information> loadProductInformation(int typeCode) throws BaseException {
		// TODO Auto-generated method stub
		Session session=HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		String hql = "from BeanProducts_information where type_code=?";
		Query qry = session.createQuery(hql);
		qry.setParameter(0, typeCode);
		List<BeanProducts_information> result = qry.list();
		transaction.commit();
		return result;
	}

	@Override
	public void addPdtIfm(int typeCode, String pdtName, String brand, float retailPrice, String pdtCode, float price)
			throws BaseException {
		// TODO Auto-generated method stub
		if(pdtName==null || "".equals(pdtName))
			throw new BaseException("商品名称不能为空");
		if(brand==null || "".equals(brand))
			throw new BaseException("商品品牌不能为空");
		if(pdtCode==null || "".equals(pdtCode))
			throw new BaseException("商品条码不能为空");
		
		Session session=HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		String hql = "from BeanProducts_information where product_name=?";
		Query qry = session.createQuery(hql);
		qry.setParameter(0, pdtName);
		BeanProducts_information pi = (BeanProducts_information)qry.uniqueResult();
        if(pi!=null)
			throw new BaseException("商品已经存在");
        BeanProducts_information pdtIfm = new BeanProducts_information();
        pdtIfm.setType_code(typeCode);
        pdtIfm.setProduct_name(pdtName);
        pdtIfm.setBrand(brand);
        pdtIfm.setRetail_price(retailPrice);
        pdtIfm.setProduct_code(pdtCode);
        pdtIfm.setPrice(price);
		session.save(pdtIfm);
		transaction.commit();
	}

	@Override
	public void changeType(int pdtId, int typeCode) throws BaseException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changePdtName(int pdtId, String pdtName) throws BaseException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changeBrand(int pdtId, String brand) throws BaseException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changePdtCode(int pdtId, String pdtCode) throws BaseException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changePrice(int pdtId, String price) throws BaseException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletePdtIfm(int pdtId) throws BaseException {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		BeanProducts_information pdtIfm = (BeanProducts_information)session.get(BeanProducts_information.class, pdtId);
		session.delete(pdtIfm);
		transaction.commit();
	}

}
