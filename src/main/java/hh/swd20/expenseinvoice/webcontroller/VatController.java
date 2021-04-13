package hh.swd20.expenseinvoice.webcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import hh.swd20.expenseinvoice.domain.VatRepository;

@Controller
public class VatController {
	
	@Autowired
	private VatRepository vatRepository;
	
	@RequestMapping("/vatlist")
	private String vat(Model model) {
		model.addAttribute("vats", vatRepository.findAll());
		return "vatlist";
	}
	

}
