package petserve.control;

import petserve.itf.IOrderManager;
import petserve.util.BaseException;

public class OrderManager implements IOrderManager {

	@Override
	public void submitOrder(int product_id, int quantity, float order_price, int transfer_state) throws BaseException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cancelOrder(int product_id) throws BaseException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changeQuantity(int product_id, int quantity) throws BaseException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void selectOrder(int product_id) throws BaseException {
		// TODO Auto-generated method stub
		
	}
	
}
