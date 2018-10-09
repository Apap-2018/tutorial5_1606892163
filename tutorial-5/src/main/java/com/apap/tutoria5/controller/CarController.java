package com.apap.tutorial4.controller;

import com.apap.tutorial4.model.*;
import com.apap.tutorial4.service.*;
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
		
		model.addAttribute("car", car);
		return "addCar";
	}
	
	@RequestMapping(value = "/car/add", method = RequestMethod.POST)
	private String addCar(@ModelAttribute CarModel car) {
		carService.addCar(car);
		return "add";
	}
	
	@RequestMapping(value = "/car/delete/{idCar}", method = RequestMethod.GET)
	private String deleteCar(@PathVariable(value = "idCar") Long carId) {
		carService.deleteCarById(carId);
		return "delete";
	}
	
	@RequestMapping(value = "/car/update/{idCar}", method = RequestMethod.GET)
	private String updateCar(@PathVariable(value = "idCar") Long carId, Model model) {
		CarModel carOld = carService.getDetailCarById(carId).get();
		model.addAttribute("carOld", carOld);
		model.addAttribute("carNew", new CarModel());
		return "updateCar";
	}
	
	@RequestMapping(value = "/car/update/{idCar}", method = RequestMethod.POST)
	private String updateCar(@ModelAttribute CarModel carNew, @PathVariable(value = "idCar") Long id) {
		carService.updateCar(id, carNew);
		return "update";
	}
}
