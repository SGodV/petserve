package petserve.itf;

import java.util.List;

import petserve.model.BeanProducts_types;
import petserve.util.BaseException;

public interface IProductTypeManager {
	public List<BeanProducts_types> loadProductType() throws BaseException;
}
