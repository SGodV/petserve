package petserve.itf;

import petserve.util.BaseException;

public interface IOrderManager {
	public void submitOrder(int product_id, int quantity, float order_price, int transfer_state) throws BaseException;
	public void cancelOrder(int product_id) throws BaseException;
	public void changeQuantity(int product_id, int quantity) throws BaseException;
	public void selectOrder(int product_id) throws BaseException;
}
