package petserve.itf;

import java.sql.Date;

import petserve.model.BeanAppointment;
import petserve.util.BaseException;

public interface IAppointmentManager {
	public void submitAppointment(int petid, int productid) throws BaseException;
	public void cancelAppointment(int appointmentid) throws BaseException;
	public BeanAppointment selectAppointment(int appointmentid) throws BaseException;
	public void changeTime(int appointmentid, Date begintime) throws BaseException;
}
