package com.easyrentalcar.controller;

import com.easyrentalcar.interfaces.CarRentalManager;
import com.easyrentalcar.model.CreateOfferCommand;
import com.easyrentalcar.services.InMemoryCarRentalManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WebController {

    private InMemoryCarRentalManager carRentalManager;

    @Autowired
    public WebController(InMemoryCarRentalManager carRentalManager) {
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
    public ModelAndView addOffer(CreateOfferCommand command) {
        ModelAndView modelAndView = new ModelAndView();
        carRentalManager.postOffer(command);
        modelAndView.addObject("offer", new CreateOfferCommand());
        modelAndView.setViewName("caraddform");
        return modelAndView;
    }
}