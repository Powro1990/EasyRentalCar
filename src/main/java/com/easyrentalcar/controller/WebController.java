package com.easyrentalcar.controller;

import com.easyrentalcar.interfaces.CarRentalManager;
import com.easyrentalcar.model.CarRentalOffer;
import com.easyrentalcar.model.CreateOfferCommand;
import com.easyrentalcar.model.User;
import com.easyrentalcar.services.AccountingService;
import com.easyrentalcar.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Collection;
import java.util.List;

@Controller
public class WebController {

    private CarRentalManager carRentalManager;
    private AccountingService accountingService;
    private UserService userService;

    @Autowired
    public WebController(CarRentalManager carRentalManager, AccountingService accountingService, UserService userService) {
        this.carRentalManager = carRentalManager;
        this.accountingService = accountingService;
        this.userService = userService;
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

    @GetMapping(value = "/offerstorent")
    public ModelAndView viewOffersToRent(@RequestParam(value = "findByLocation", required = false) String location) {
        ModelAndView modelAndView = new ModelAndView();
        Collection<CarRentalOffer> offers;
        if (location != null) {
            offers = carRentalManager.findOfferByLocation(location);
        } else {
            offers = carRentalManager.findAllOffers();
        }
        modelAndView.addObject("offerstorent", offers);
        modelAndView.setViewName("offerstorent");
        modelAndView.addObject("earnings", accountingService.totalEarnings());
        return modelAndView;
    }


    @PostMapping(value = "/offerstorent")
    public String rentCarByButton(Principal principal,
                                  @RequestParam(value = "id") Long id) {
        carRentalManager.rentCar(id, principal.getName());
        return "redirect:/offerstorent";
    }

    @GetMapping(value = "/register")
    public ModelAndView displayRegisterForm(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("register");
        return modelAndView;
    }

    @PostMapping(value = "/register")
    public String addUserToBase(User user){
        userService.registerUser(user);
        return "redirect:/login";
    }

}
