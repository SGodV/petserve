package petserve.itf;

import java.util.List;

import petserve.model.BeanProducts_types;
import petserve.util.BaseException;


public interface IProductTypeManager {
	public List<BeanProducts_types> loadProductType() throws BaseException;
	public List<BeanProducts_types> loadServeType() throws BaseException;
	public void addPdtType(String typeName, String typeDescribe) throws BaseException;
	public void addServeType(String typeName, String typeDescribe) throws BaseException;
	public void changePdtTypeName(int typeCode, String typeName) throws BaseException;
	public void changePdtTypeDsc(int typeCode, String typeDescribe) throws BaseException;
	public void deletePdtType(int typeCode) throws BaseException;
}
