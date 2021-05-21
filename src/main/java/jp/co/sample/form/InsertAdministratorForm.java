package jp.co.sample.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * InsertAdministratorのフォームクラスです.<br>
 * InsertAdminnistratorフォームから入力した値を受け取るクラスです
 * 
 * @author nhson
 *
 */
public class InsertAdministratorForm {

	/** 名前 */
	@NotBlank(message = "氏名を入力してください")
	private String name;
	/** メールアドレス */
	@Email(message = "メールアドレスを入力してください")
	private String mailAddress;
	/** パスワード */
	@NotBlank(message = "パスワードを入力してください")
	private String password;

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
		return "InsertAdministratorForm [name=" + name + ", mailAddress=" + mailAddress + ", password=" + password
				+ "]";
	}

}
