package jp.co.sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Employee;
import jp.co.sample.service.EmployeeService;

/**
 * 従業員のControllerクラスです.
 * @author nhson
 *
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	/**
	 * @param model
	 * @return 全部の従業員を表示する画面
	 */
	@RequestMapping("/showList")
	public String showList(Model model) {
		List<Employee> employees = employeeService.showList();
		model.addAttribute("employees", employees);
		return "employee/list";
	}
}
