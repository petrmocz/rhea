package cz.petrmo.rhea.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cz.petrmo.rhea.domain.User;
import cz.petrmo.rhea.domain.UserRepository;

// @Controller
// @RequestMapping(value = "users")
public class UserController {

	protected Logger		logger	= LoggerFactory.getLogger(getClass());

	@Autowired
	private UserRepository	userRepository;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String list(Model model) {
		final List<User> users = userRepository.findAll();
		model.addAttribute("userList", users);
		model.addAttribute("count", users.size());
		return "activiti/users";

	}
}
