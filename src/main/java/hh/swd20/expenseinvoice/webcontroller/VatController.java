package hh.swd20.expenseinvoice.webcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hh.swd20.expenseinvoice.domain.Vat;
import hh.swd20.expenseinvoice.domain.VatRepository;

@Controller
public class VatController {
	
	@Autowired
	private VatRepository vatRepository;
	
	// This function prints all vat percentages on the vatlist page. It also prints an empty form for adding a new vat percentage.
	@RequestMapping("/vatlist")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String vat(Model model) {
		model.addAttribute("vats", vatRepository.findAll());
		model.addAttribute("vat", new Vat());
		return "vatlist";
	}
	
	// This function prints existing vat percentage information, that can then be edited.
	@RequestMapping(value="/edit_vat/{id}", method=RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN')")
	public String editVat(@PathVariable(value="id") Long vatId, Model model) {
		model.addAttribute("vat", vatRepository.findById(vatId));
		return "editvat";
	}
	
	// This function saves the added/edited vat percentage.
	@RequestMapping(value="/savevat", method=RequestMethod.POST)
	@PreAuthorize("hasAuthority('ADMIN')")
	public String saveVat(Vat vat) {
		vatRepository.save(vat);
		return "redirect:vatlist";
	}
	
	// This function deletes a vat percentage.
	@RequestMapping(value="/delete_vat/{id}", method=RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteVat(@PathVariable(value="id") Long vatId) {
		vatRepository.deleteById(vatId);
		return "redirect:../vatlist";
	}

}
