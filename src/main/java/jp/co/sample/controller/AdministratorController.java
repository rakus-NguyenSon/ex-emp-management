package jp.co.sample.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Administrator;
import jp.co.sample.form.InsertAdministratorForm;
import jp.co.sample.form.LoginForm;
import jp.co.sample.service.AdministratorService;

/**
 * AdminstratorController クラスです. <br>
 * Adminstratorに関する処理を実装するクラスです。
 * @author nhson
 *
 */
@Controller
@RequestMapping("/")
public class AdministratorController {
	
	@Autowired
	private AdministratorService administratorService;
	
	/**
	 * InsertAdministratorFormをセットアップするメソッドです。
	 * @return
	 */
	@ModelAttribute
	public InsertAdministratorForm setUpIsertAdministratorForm() {
		return new InsertAdministratorForm();
	}
	/**
	 * ログインフォームをセットアップするメソッドです.
	 */
	@ModelAttribute public LoginForm setUpLoginForm() {
		return new LoginForm();
	}

	/**
	 * ログインページに移動する
	 * @return
	 */
	@RequestMapping("/")
	public String toLogin(Model model) {
		return "administrator/login";
	}

	@RequestMapping("/toInsert")
	public String toInsert(Model model) {
		return "administrator/insert";
	}
	
	/**
	 * Administratorを追加するメソッドです．
	 * @param form InsertAdministratorフォーム
	 * @param result 入力値チェック用
	 * @param model　
	 * @return
	 */
	@RequestMapping("/insert")
	public String insert(@Validated InsertAdministratorForm form, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return toInsert(model);
		}
		Administrator administrator = new Administrator();
		BeanUtils.copyProperties(form, administrator);
		administratorService.insert(administrator);
		return "administrator/insert";	
	}
	
	@RequestMapping("/login")
	public String login(LoginForm form, Model model) {
		
		
		Administrator administrator = administratorService.login(form.getMailAddress(), form.getPassword());
		if (administrator==null) {			
			model.addAttribute("Error","メールアドレスまたはパスワードが不正です");
			System.out.println("Can not login");
			return toLogin(model);
			
			//return "redirect:/"; 
		} 
		return "employee/list"; //return "forward:/employee/showList";
			
	}
}
