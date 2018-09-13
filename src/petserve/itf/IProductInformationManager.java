package petserve.itf;

import java.util.List;

import petserve.model.BeanProducts_information;
import petserve.util.BaseException;

public interface IProductInformationManager {
	public List<BeanProducts_information> loadProductInformation(int typeCode) throws BaseException;
	public void addPdtIfm(int typeCode, String pdtName, String brand, float retailPrice, String pdtCode, float price) throws BaseException;
	public void changeType(int pdtId, int typeCode) throws BaseException;
	public void changePdtName(int pdtId, String pdtName) throws BaseException;
	public void changeBrand(int pdtId, String brand) throws BaseException;
	public void changePdtCode(int pdtId, String pdtCode) throws BaseException;
	public void changePrice(int pdtId, String price) throws BaseException;
	public void deletePdtIfm(int pdtId) throws BaseException;
}
