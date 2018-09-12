package petserve.control;

import java.sql.Date;

import petserve.itf.IAppointmentManager;
import petserve.model.BeanAppointment;
import petserve.util.BaseException;

public class AppointmentManager implements IAppointmentManager {

	@Override
	public void submitAppointment(int petid, int productid) throws BaseException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cancelAppointment(int appointmentid) throws BaseException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BeanAppointment selectAppointment(int appointmentid) throws BaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void changeTime(int appointmentid, Date begintime) throws BaseException {
		// TODO Auto-generated method stub
		
	}

}
