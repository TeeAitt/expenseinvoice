package hh.swd20.expenseinvoice.webcontroller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	
	/************************* RESTful services below ****************************
	 */
	
	// This REST function will get all the vat percentages.
	@RequestMapping(value="/vats", method=RequestMethod.GET)
	public @ResponseBody List<Vat> vatListRest() {
		return (List<Vat>) vatRepository.findAll();
	}
	
	// This REST function will get a vat percentage by its id.
	@RequestMapping(value="/vats/{id}", method=RequestMethod.GET)
	public @ResponseBody Optional<Vat> findVatRest(@PathVariable("id") Long vatId) {
		return vatRepository.findById(vatId);
	}
	
	// This REST function will save a new vat percentage.
	@RequestMapping(value="/vats", method=RequestMethod.POST)
	public @ResponseBody Vat saveVatRest(@RequestBody Vat vat) {
		return vatRepository.save(vat);
	}

}
