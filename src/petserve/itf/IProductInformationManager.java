package petserve.itf;

import java.util.List;

import petserve.model.BeanProducts_information;
import petserve.util.BaseException;

public interface IProductInformationManager {
	public List<BeanProducts_information> loadProductInformation() throws BaseException;
}
