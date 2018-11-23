package com.easyrentalcar.controller;

import com.easyrentalcar.interfaces.CarRentalManager;
import com.easyrentalcar.model.CreateOfferCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WebController {

    private CarRentalManager carRentalManager;

    @Autowired
    public WebController(CarRentalManager carRentalManager) {
        this.carRentalManager = carRentalManager;
    }

    @GetMapping(value = "/offers")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("offers", carRentalManager.findAllOffers());
        modelAndView.setViewName("caraddform");
        return modelAndView;
    }

    @PostMapping(value = "/offers")
    public String addOffer(CreateOfferCommand command) {
        carRentalManager.postOffer(command);
        return "redirect:/offers";
    }
}