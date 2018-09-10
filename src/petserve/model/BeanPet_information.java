package petserve.model;

public class BeanPet_information {
	private int user_id;
	private int pet_id;
	private String pet_name;
	private String name;
	private byte[] picture;
	private short appointment_state;
	private short age;
	private short healthy;
	
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getPet_id() {
		return pet_id;
	}
	public void setPet_id(int pet_id) {
		this.pet_id = pet_id;
	}
	public String getPet_name() {
		return pet_name;
	}
	public void setPet_name(String pet_name) {
		this.pet_name = pet_name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public byte[] getPicture() {
		return picture;
	}
	public void setPicture(byte[] picture) {
		this.picture = picture;
	}
	public short isAppointment_state() {
		return appointment_state;
	}
	public void setAppointment_state(short appointment_state) {
		this.appointment_state = appointment_state;
	}
	public short getAge() {
		return age;
	}
	public void setAge(short age) {
		this.age = age;
	}
	public short getHealthy() {
		return healthy;
	}
	public void setHealthy(short healthy) {
		this.healthy = healthy;
	}
}
