package com.apap.tutoria5.controller;

import com.apap.tutoria5.model.*;
import com.apap.tutoria5.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * CarController
 * @author rico.putra
 * @version 2/10/18
 */
@Controller
public class CarController {
	@Autowired
	private CarService carService;
	
	@Autowired
	private DealerService dealerService;
	
	@RequestMapping(value = "/car/add/{dealerId}", method = RequestMethod.GET)
	private String add(@PathVariable(value = "dealerId") Long dealerId, Model model) {
		CarModel car = new CarModel();
		DealerModel dealer = dealerService.getDealerDetailById(dealerId).get();
		car.setDealer(dealer);
		model.addAttribute("pageTitle", "Add Car");
		model.addAttribute("car", car);
		return "addCar";
	}
	
	@RequestMapping(value = "/car/add", method = RequestMethod.POST)
	private String addCar(@ModelAttribute CarModel car, Model model) {
		carService.addCar(car);
		model.addAttribute("pageTitle", "Add Car Succeed");
		return "add";
	}
	
	@RequestMapping(value = "/car/delete/{idCar}", method = RequestMethod.GET)
	private String deleteCar(@PathVariable(value = "idCar") Long carId, Model model) {
		carService.deleteCarById(carId);
		model.addAttribute("pageTitle", "Delete Car Succeed");
		return "delete";
	}
	
	@RequestMapping(value = "/car/update/{idCar}", method = RequestMethod.GET)
	private String updateCar(@PathVariable(value = "idCar") Long carId, Model model) {
		CarModel carOld = carService.getDetailCarById(carId).get();
		model.addAttribute("carOld", carOld);
		model.addAttribute("carNew", new CarModel());
		model.addAttribute("pageTitle", "Update Car");
		return "updateCar";
	}
	
	@RequestMapping(value = "/car/update/{idCar}", method = RequestMethod.POST)
	private String updateCar(@ModelAttribute CarModel carNew, @PathVariable(value = "idCar") Long id, Model model) {
		carService.updateCar(id, carNew);
		model.addAttribute("pageTitle", "Update Car Succeed");
		return "update";
	}
}
