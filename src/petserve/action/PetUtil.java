package petserve.action;

import petserve.control.AppointmentManager;
import petserve.control.OrderManager;
import petserve.control.PetManager;
import petserve.control.ProductInformationManager;
import petserve.control.ProductTypeManager;
import petserve.control.UserManager;
import petserve.itf.IAppointmentManager;
import petserve.itf.IOrderManager;
import petserve.itf.IPetManager;
import petserve.itf.IProductInformationManager;
import petserve.itf.IProductTypeManager;
import petserve.itf.IUserManager;

public class PetUtil {
	public static IUserManager userManager=new UserManager();
	public static IPetManager petManager=new PetManager();
	public static IAppointmentManager appointmentManager=new AppointmentManager();
	public static IOrderManager orderManager=new OrderManager();
	public static IProductInformationManager productInformationManager=new ProductInformationManager();
	public static IProductTypeManager productTypeManager=new ProductTypeManager();
}
