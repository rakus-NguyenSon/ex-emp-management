package jp.co.sample.controller;

import javax.servlet.http.HttpSession;

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
 * 管理者のController クラスです. <br>
 * @author nhson
 *
 */
@Controller
@RequestMapping("/")
public class AdministratorController {
	
	@Autowired
	private HttpSession session;
	
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
	 * @return　ログインページ
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
	 * 管理者を追加するメソッドです．
	 * @param form InsertAdministratorフォーム
	 * @param result 入力値チェック用
	 * @param model　ページデータスコープ
	 * @return　ログイン画面
	 */
	@RequestMapping("/insert")
	public String insert(@Validated InsertAdministratorForm form, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return toInsert(model);
		}
		Administrator administrator = new Administrator();
		BeanUtils.copyProperties(form, administrator);
		administratorService.insert(administrator);
		return "redirect:/";	
	}
	
	/**
	 *　ログインの処理
	 * @param form 
	 * @param model
	 * @return データベースに管理者がない場合はログイン画面に戻ります。
	 * そうではない場合は従業員一覧画面に移動します。
	 */
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
