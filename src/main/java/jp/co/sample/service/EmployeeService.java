package jp.co.sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;

import jp.co.sample.domain.Employee;
import jp.co.sample.form.UpdateEmployeeForm;
import jp.co.sample.repository.EmployeeRepository;

/**
 * 従業員のServiceクラス.<br>
 * @author nhson
 *
 */
@Service
@Transactional
public class EmployeeService {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	/**
	 * @return 全部の従業員情報を返します。
	 */
	public List<Employee> showList(){
		return employeeRepository.findAll();
	}
	
	/**
	 * @param ID 従業員のID。
	 * @return IDを持っている従業員の情報を返します。
	 */
	public Employee showDetail(Integer ID) {
		return employeeRepository.load(ID);
	}
	
	/**
	 * 従業員の情報を変更するメソッドです．
	 * @param employee
	 */
	public void update(Employee employee) {
		employeeRepository.update(employee);
	}
	
}
