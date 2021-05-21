package jp.co.sample.domain;

/**
 * Administratorのクラスです. <br>
 * Adminの情報を持つためのクラスです。
 * 
 * @author nhson
 * 
 */
public class Administrator {

	/** AdministratorのID */
	private Integer ID;
	/** Administratorの名前 */
	private String name;
	/** Administratorのメールアドレス */
	private String mailAddress;
	/** Administratorのパスワード */
	private String password;

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Administrator [ID=" + ID + ", name=" + name + ", mailAddress=" + mailAddress + ", password=" + password
				+ "]";
	}

}
