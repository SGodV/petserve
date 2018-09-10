package petserve.itf;

import petserve.model.BeanUser_information;
import petserve.util.BaseException;

public interface IUserManager {
	public BeanUser_information reg(String userPhone, String pwd, String pwd2, String userName, String userEmail, String otherContact) throws BaseException;
	public BeanUser_information regAdmin(String adminPhone, String pwd, String pwd2, String adminName) throws BaseException;
	public BeanUser_information login(String userid, String pwd) throws BaseException;
	public void changePwd(String oldPwd,String newPwd, String newPwd2) throws BaseException;
	public void changeUserName(String userName) throws BaseException;
	public void changePhone(String userPhone) throws BaseException;
	public void changeEmail(String userEmail) throws BaseException;
	public void changeOtherContect(String otherContect) throws BaseException;
}
