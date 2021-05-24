package jp.co.sample.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Employee;
import jp.co.sample.form.UpdateEmployeeForm;
import jp.co.sample.service.EmployeeService;

/**
 * 従業員のControllerクラスです.
 * 
 * @author nhson
 *
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {

	/**
	 * 従業員の情報をアップデートフォームのセットアップメソッドです．
	 * 
	 * @return
	 */
	@ModelAttribute
	private UpdateEmployeeForm setUpUpdateEmployeeForm() {
		return new UpdateEmployeeForm();
	}

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
	
	/**
	 * @param id
	 * @param model
	 * @return　idを持っている従業員の詳細情報を表示する画面
	 */
	@RequestMapping("/showDetail")
	public String showDetail(String id, Model model) {
		Employee employee = employeeService.showDetail(Integer.parseInt(id));
		model.addAttribute("employee", employee);
		System.out.println(id);
		return "employee/detail";
	}
	
	/**
	 * @param form
	 * @return 従業員の情報一覧表示する画面
	 */
	@RequestMapping("/update")
	public String update(UpdateEmployeeForm form) {
		
		Employee employee = new Employee();
		employee = employeeService.showDetail(Integer.parseInt(form.getId()));
		employee.setDependentsCount(Integer.parseInt(form.getDependentsCount()));
		employeeService.update(employee);
		return "redirect:/employee/showList";
	}
}
