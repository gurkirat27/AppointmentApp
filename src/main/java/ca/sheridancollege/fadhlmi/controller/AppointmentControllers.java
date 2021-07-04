package ca.sheridancollege.fadhlmi.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ca.sheridancollege.fadhlmi.bean.Appointment;
import ca.sheridancollege.fadhlmi.database.DatabaseAccess;

@Controller
public class AppointmentControllers {

	@Autowired
	private DatabaseAccess da;

	@GetMapping("/")
	public String getIndex(Model model,Appointment ap) {
		da.getApps1();
		ArrayList<Appointment> list=da.getApps1();
		model.addAttribute("ap", ap);
		model.addAttribute("list", list);
		return "index.html";
	}
	
	@PostMapping("/")
	public String postIndex(Model model,@ModelAttribute Appointment ap) {
		da.insertApp(ap);
		ArrayList<Appointment> list=da.getApps1();
		model.addAttribute("ap", ap);
		model.addAttribute("list", list);
		return "index.html";
	}
	
	@GetMapping("/edit/{id}")
	public String editAp(Model model,@PathVariable int id){
		Appointment a= da.getApp(id);
		model.addAttribute("ap", a);
		return "edit_form.html";
	}
	
	@PostMapping("/save")
	public String saveStudent(Model model,@ModelAttribute Appointment ap) {
		da.saveAppointment(ap);
		ArrayList<Appointment> list=da.getApps1();
		model.addAttribute("ap", ap);
		model.addAttribute("list", list);
		return "index.html";
		}
	@GetMapping("/delete/{id}")
	public String remove(Model model, @PathVariable int id, Appointment ap){
		da.deleteAp(id);
		model.addAttribute("ap", ap);
		model.addAttribute("list", da.getApps());
	return "index.html";
	}
}
