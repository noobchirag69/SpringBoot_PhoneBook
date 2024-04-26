package com.chirag.PhoneBookApp.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.chirag.PhoneBookApp.model.Contact;
import com.chirag.PhoneBookApp.model.ContactDTO;
import com.chirag.PhoneBookApp.repo.ContactRepo;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/contacts")
public class ContactController {

	@Autowired
	private ContactRepo contactRepo;

	@GetMapping({ "", "/" })
	public String getContacts(Model model) {
		List<Contact> contactList = contactRepo.findAll(Sort.by(Sort.Direction.ASC, "name"));
		model.addAttribute("contactList", contactList);
		return "contacts/index";
	}

	@GetMapping("/create")
	public String getAddPage(Model model) {
		ContactDTO contactDTO = new ContactDTO();
		model.addAttribute("contactDTO", contactDTO);
		return "contacts/create";
	}

	@PostMapping("/create")
	public String addContact(@Valid @ModelAttribute ContactDTO contactDTO, BindingResult result) {
		if (result.hasErrors())
			return "contacts/create";

		Date currentTime = new Date();
		Contact newContact = new Contact();

		newContact.setName(contactDTO.getName());
		newContact.setPhone(Long.parseLong(contactDTO.getPhone()));
		newContact.setEmail(contactDTO.getEmail());
		newContact.setCreationTime(currentTime);

		try {
			contactRepo.save(newContact);
		} catch (Exception exception) {
			return "Exception: " + exception.getMessage();
		}

		return "redirect:/contacts";

	}

	@GetMapping("/edit")
	public String getEditPage(Model model, @RequestParam long id) {

		try {

			Contact contact = contactRepo.findById(id).get();
			model.addAttribute("contact", contact);

			ContactDTO contactDTO = new ContactDTO();

			contactDTO.setName(contact.getName());
			contactDTO.setPhone(String.valueOf(contact.getPhone()));
			contactDTO.setEmail(contact.getEmail());

			model.addAttribute("contactDTO", contactDTO);

		} catch (Exception exception) {
			return "Exception: " + exception.getMessage();
		}

		return "contacts/edit";

	}

	@PostMapping("/edit")
	public String editContact(Model model, @RequestParam long id, @Valid @ModelAttribute ContactDTO contactDTO,
			BindingResult result) {

		try {

			Contact contact = contactRepo.findById(id).get();
			model.addAttribute("contact", contact);

			if (result.hasErrors())
				return "contacts/edit";

			contact.setName(contactDTO.getName());
			contact.setPhone(Long.parseLong(contactDTO.getPhone()));
			contact.setEmail(contactDTO.getEmail());

			contactRepo.save(contact);

		} catch (Exception exception) {
			return "Exception: " + exception.getMessage();
		}

		return "redirect:/contacts";

	}

	@GetMapping("/delete")
	public String deleteContact(@RequestParam long id) {

		try {

			Contact contact = contactRepo.findById(id).get();
			contactRepo.delete(contact);

		} catch (Exception exception) {
			return "Exception: " + exception.getMessage();
		}

		return "redirect:/contacts";
	}

}
