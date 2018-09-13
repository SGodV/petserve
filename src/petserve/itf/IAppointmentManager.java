package petserve.itf;

import java.sql.Date;
import java.util.List;

import petserve.model.BeanAppointment;
import petserve.util.BaseException;

public interface IAppointmentManager {
	public void submitAppointment(int petid, int productid) throws BaseException;
	public void cancelAppointment(int appointmentid) throws BaseException;
	public List<BeanAppointment> selectAppointment(int userid) throws BaseException;
	public void finishTime(int appointmentid) throws BaseException;
}
