package petserve.itf;

import java.util.List;

import petserve.model.BeanOrder_information;
import petserve.util.BaseException;

public interface IOrderManager {
	public void submitOrder(int product_id, int quantity, float order_price) throws BaseException;
	public void cancelOrder(int order_id) throws BaseException;
	public void finishOrder(int order_id) throws BaseException;
	public void changeQuantity(int order_id, int quantity) throws BaseException;
	public List<BeanOrder_information> selectOrder(int userid) throws BaseException;
}
