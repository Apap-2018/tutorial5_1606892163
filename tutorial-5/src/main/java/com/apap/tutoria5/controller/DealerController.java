package com.apap.tutorial4.controller;

import java.util.Collections;
import java.util.List;
import com.apap.tutorial4.model.*;
import com.apap.tutorial4.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * DealerController
 * @author rico.putra
 * @version 2/10/18
 */
@Controller
public class DealerController {
	@Autowired
	private DealerService dealerService;
	
	@Autowired
	private CarService carService;
	
	@RequestMapping("/")
	private String home() {
		return "home";
	}
	
	@RequestMapping(value = "/dealer/add", method = RequestMethod.GET)
	private String add(Model model) {
		model.addAttribute("dealer", new DealerModel());
		return "addDealer";
	}
	
	@RequestMapping(value = "/dealer/add", method = RequestMethod.POST)
	private String addDealerSubmit(@ModelAttribute DealerModel dealer) {
		dealerService.addDealer(dealer);
		return "add";
	}
	
	@RequestMapping(value = "/dealer/view", method = RequestMethod.GET)
	private String viewDealerById(@RequestParam(value = "dealerId", required = true) Long id, Model model) {
		DealerModel dealer = null;
		List<CarModel> listCar = null;
		
		if (dealerService.getDealerDetailById(id).isPresent()) {
			dealer = dealerService.getDealerDetailById(id).get();
			listCar = dealer.getListCar();
			Collections.sort(listCar);
		}
		model.addAttribute("dealer", dealer);
		model.addAttribute("listCar", listCar);
		return "view-dealer";
	}
	
	@RequestMapping(value = "/dealer/view-all")
	private String viewAllDealer(Model model) {
		List<DealerModel> listDealer = null;
		
		if (!dealerService.getListDealer().isEmpty()) {
			listDealer = dealerService.getListDealer();
			model.addAttribute("listDealer", listDealer);
		}
		return "view-allDealer";
	}
	
	@RequestMapping(value = "/dealer/delete/{dealerId}", method = RequestMethod.GET)
	private String deleteDealerById(@PathVariable(value = "dealerId") Long id) {
		dealerService.deleteById(id);
		
		return "delete";
	}
	
	@RequestMapping(value = "/dealer/update/{dealerId}", method = RequestMethod.GET)
	private String updateDealer(@PathVariable(value = "dealerId") Long id, Model model) {
		DealerModel dealerOld = dealerService.getDealerDetailById(id).get();
		model.addAttribute("dealerOld", dealerOld);
		model.addAttribute("dealerNew", new DealerModel());
		return "updateDealer";
	}
	
	@RequestMapping(value = "/dealer/update/{dealerId}", method = RequestMethod.POST)
	private String updateDealer(@ModelAttribute DealerModel dealerNew, @PathVariable(value = "dealerId") Long id) {
		dealerService.updateById(id, dealerNew);
		return "update";
	}
}
