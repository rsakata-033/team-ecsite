package jp.co.internous.kabuki.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.internous.kabuki.model.domain.MstUser;
import jp.co.internous.kabuki.model.form.UserForm;
import jp.co.internous.kabuki.model.mapper.MstUserMapper;
import jp.co.internous.kabuki.model.session.LoginSession;


@Controller
@RequestMapping("/kabuki/user")
public class UserController {
	
	@Autowired
	MstUserMapper mstUserMapper; 
	
	@Autowired
	private LoginSession loginSession;
	
	@RequestMapping("/")
	public String index(Model m) {
		
		m.addAttribute("loginSession",loginSession);
		
		return "register_user";
	}
	
	@ResponseBody
	@RequestMapping("/duplicatedUserName")
	public boolean duplicatedUserName(@RequestBody UserForm f) {
		int count = mstUserMapper.findCountByUserName(f.getUserName());
		return count > 0;
	}
	
	@RequestMapping("/register")
	@ResponseBody
	public boolean register(@RequestBody UserForm f) {
		MstUser user = new MstUser(f);
		
		int count = mstUserMapper.insert(user);
		
		return count > 0;
	}
	
}