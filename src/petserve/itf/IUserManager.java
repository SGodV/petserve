package petserve.itf;

import petserve.model.BeanUser_information;
import petserve.util.BaseException;

public interface IUserManager {
	public BeanUser_information reg(String userPhone, String pwd, String pwd2, String userName) throws BaseException;
	public BeanUser_information login(String userid, String pwd) throws BaseException;
	public void changePwd(BeanUser_information user, String oldPwd,String newPwd, String newPwd2) throws BaseException;
}
