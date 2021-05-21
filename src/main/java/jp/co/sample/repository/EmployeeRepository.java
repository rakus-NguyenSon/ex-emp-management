package jp.co.sample.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Employee;

/**
 * EmployeeRepositoryです. <br>
 * SQLにあるemployeesテーブルを操作や情報を呼び出すためのクラスです。
 * 
 * @author nhson
 *
 */
@Repository
public class EmployeeRepository {

	private final static String EMPLOYEES_TABLE = "employees";

	/** SpringFrameworkにある機能を利用するための変数 */
	@Autowired
	private NamedParameterJdbcTemplate template;

	/** SQL row から Employee型に変更するための変数 */
	private static final RowMapper<Employee> EMPLOYEE_ROW_MAPPER = (rs, i) -> {
		Employee employee = new Employee();
		
		employee.setId(rs.getInt("id"));
		employee.setName(rs.getString("name"));
		employee.setImage(rs.getString("image"));
		employee.setGender(rs.getString("gender"));
		employee.setHireDate(rs.getDate("hire_date"));
		employee.setMailAddress(rs.getString("mail_address"));
		employee.setZipCode(rs.getString("zip_code"));
		employee.setAddress(rs.getString("address"));
		employee.setTelephone(rs.getString("telephone"));
		employee.setSalary(rs.getInt("salary"));
		employee.setCharacteristics(rs.getString("characteristics"));
		employee.setDependentsCount(rs.getInt("dependents_count"));
		return employee;
	};
	
	/**
	 * すべての従業員の情報を呼び出します。
	 * @return
	 */
	public List<Employee> findAll(){
		String sql = "SELECT id, name, image, gender, hire_date, mail_address, zip_code, address,"
				+ "telephone, salary, characteristics, dependents_count"
				+ " from " + EMPLOYEES_TABLE + ";";
				
		List<Employee> employees = template.query(sql, EMPLOYEE_ROW_MAPPER);
		return employees;
	}
	
	/**
	 * @param ID
	 * @return IDを持っている従業員の情報を呼び出します。
	 */
	public Employee load(Integer ID) {
		String sql = "SELECT id, name, image, gender, hire_date, mail_address, zip_code, address,"
				+ "telephone, salary, characteristics, dependents_count"
				+ " from " + EMPLOYEES_TABLE + " where id=:id;";
		
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", ID);
		
		try {
			Employee employee = template.queryForObject(sql, param, EMPLOYEE_ROW_MAPPER); // ←ここに実行処理を書く
			return employee;
		} catch (Exception e) {
			System.out.println("Can not load");
			e.printStackTrace();
		}
		return null;		
	}
	
	/**
	 * 従業員の情報を更新するメソッドです
	 * @param employee
	 */
	public void update(Employee employee) {
		String sql = "UPDATE " + EMPLOYEES_TABLE + " SET name=:name, image=:image, gender=:gender,"
				+ "hire_date=:hireDate, mail_address=:mailAddress, zip_code=:zipCode, address =:address,"
				+ "telephone =:telephone, salary=:salary, characterostics=:characteristics, "
				+ "dependents_count=:dependentsCount where id=:id;";
		SqlParameterSource param = new BeanPropertySqlParameterSource(employee);
		
		template.update(sql, param);
	}
}
