package jp.co.sample.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Administrator;

/**
 * AdminstratorRepositoryです. <br>
 * SQLにあるadministratorsテーブルを操作や情報を呼び出すためのクラスです。
 * 
 * @author nhson
 *
 */
@Repository
public class AdministratorRepository {

	/** テーブル名 */
	private final static String ADMIN_TABLE = "administrators";

	/** SpringFrameworkにある機能を利用するための変数 */
	@Autowired
	private NamedParameterJdbcTemplate template;

	/** SQL row から Administrator型に変更するための変数 */
	private static final RowMapper<Administrator> ADMINISTRATOR_ROW_MAPPER = (rs, i) -> {
		Administrator admin = new Administrator();
		admin.setID(rs.getInt("id"));
		admin.setMailAddress(rs.getString("mail_address"));
		admin.setPassword(rs.getString("password"));
		admin.setName(rs.getString("name"));
		return admin;
	};

	/**
	 * @param administrator
	 * @return Administrator
	 * 
	 * 新しいAdministratorを追加するメソッドです
	 * IDを付けの追加したAdministratorは戻り値となります。
	 */
	public Administrator insert(Administrator administrator) {

		String sql = "INSERT INTO " + ADMIN_TABLE + " (name, mail_address, password) values"
				+ "(:name,:mailAddress,:password);";
		SqlParameterSource param = new BeanPropertySqlParameterSource(administrator);

		KeyHolder keyHolder = new GeneratedKeyHolder();
		String[] keyColumnNames = { "id" };

		template.update(sql, param, keyHolder, keyColumnNames);
		administrator.setID(keyHolder.getKey().intValue());

		return administrator;
	}

	/**
	 * @param mailAddress
	 * @param password
	 * @return　Administrator
	 * 
	 * AdministratorのmailAddressとpasswordにより、すべての情報を呼び出すメソッドです
	 * mailAddress、passwordいずれか合わない場合はnullを返却します。
	 */
	public Administrator findByMailAddressAndPassword(String mailAddress, String password) {

		String sql = "Select id, name, mail_address, password from " + ADMIN_TABLE
				+ " Where mail_address=:email AND password =:password;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("email", mailAddress).addValue("password",
				password);

		List<Administrator> administrators = template.query(sql, param, ADMINISTRATOR_ROW_MAPPER);
		if(administrators.size()!=0) {
			return administrators.get(0);
		}
		return null;
	}
}
