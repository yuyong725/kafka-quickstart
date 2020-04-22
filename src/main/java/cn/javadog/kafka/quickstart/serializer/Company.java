package cn.javadog.kafka.quickstart.serializer;

/**
 * @author 余勇
 * @date 2020年04月21日 19:19:00
 */
public class Company {

	private String name;
	private String address;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Company(String name, String address) {
		this.name = name;
		this.address = address;
	}

	public Company() {
	}

	@Override
	public String toString() {
		return "Company{" +
			"name='" + name + '\'' +
			", address='" + address + '\'' +
			'}';
	}
}
