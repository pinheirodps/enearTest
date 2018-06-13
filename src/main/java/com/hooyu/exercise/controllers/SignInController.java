package com.hooyu.exercise.controllers;

import com.hooyu.exercise.customers.dao.CustomerNotFoundException;
import com.hooyu.exercise.customers.dao.HardcodedListOfCustomersImpl;
import com.hooyu.exercise.customers.domain.Customer;
import com.hooyu.exercise.customers.domain.CustomerType;
import com.hooyu.exercise.customers.service.CustomerService;
import com.hooyu.exercise.customers.service.CustormerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class SignInController {

    @Autowired
    private CustormerServiceImpl customerService;


    @GetMapping
    public ModelAndView signin() {
        return new ModelAndView("signin");
    }


    @PostMapping("/signin")
    public RedirectView signin(@ModelAttribute("customer") Customer customerParam, HttpServletRequest request, RedirectAttributes redirectAttributes) {

        Customer customer;

        try {
            customer = customerService.findCustomerByEmailAddress(customerParam);
            customerService.checkEmailInSession(customer.getEmailAddress(), request.getSession());
            request.getSession().setAttribute("email", customer.getEmailAddress());
        } catch (CustomerNotFoundException | EmailAlreadyExistsInSessionException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return new RedirectView("/", true);
        }

        redirectAttributes.addFlashAttribute("customer", customer);

        return new RedirectView("/signinList", true);
    }

    @GetMapping("/signinList")
    public ModelAndView signinList() {
        ModelAndView view = new ModelAndView("signinList");
             return view;
    }

    @PostMapping("/signinFilterList")
    public ModelAndView signinFilterList(@ModelAttribute("customer") Customer customerParam) {
        ModelAndView view = new ModelAndView("signinList");
        List<Customer> customers = customerService.findAll(customerParam);
        view.addObject("custormes", customers);
        return view;
    }
}
