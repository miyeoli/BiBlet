package a.b.c.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/search")
public class SearchController {
	
	@GetMapping("/search")
	public String search(@RequestParam(defaultValue = "") String query, Model model) {
		model.addAttribute("query", query);
		return "search";
	}

}
