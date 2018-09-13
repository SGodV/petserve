package petserve.itf;

import java.util.List;

import petserve.model.BeanPet_information;
import petserve.util.BaseException;

public interface IPetManager {
	public void addPet(String petName, String typeName, byte[] picture, short age, short healthy) throws BaseException;
	public void deletePet(int petid) throws BaseException;
	public void changePetName(int petid, String petName) throws BaseException;
	public void changeTypeName(int petid, String typeName) throws BaseException;
	public void changePetAge(int petid, short age) throws BaseException;
	public void changePetHealthy(int petid, short healthy) throws BaseException;
	public BeanPet_information selectAppointment_state(int petid) throws BaseException;
	public int getPetidByName(String name) throws BaseException;
	public void changePetPicture(int petid, byte[] picture) throws BaseException;
	public List<BeanPet_information> loadPetByUser(int userid) throws BaseException;
	public List<BeanPet_information> loadAllPet() throws BaseException;
}
