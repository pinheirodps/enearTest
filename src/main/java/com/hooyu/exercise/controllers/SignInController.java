package com.hooyu.exercise.controllers;

import com.hooyu.exercise.customers.dao.CustomerNotFoundException;
import com.hooyu.exercise.customers.domain.Customer;
import com.hooyu.exercise.customers.service.CustormerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

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
    public ModelAndView signinList(Customer customer) {
         ModelAndView view = new ModelAndView("signinList");
         view.addObject("customer", customer);
             return view;
    }

    @PostMapping("/signinFilterList")
    public RedirectView signinFilterList(@Valid @ModelAttribute("customer")  Customer customer, Errors result , RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("errorsMsg", result.getAllErrors());
            redirectAttributes.addFlashAttribute("customer", customer);
            return new RedirectView("/signinList", true);
        }
        List<Customer> customers = customerService.filterCustomerByFields(customer);
        redirectAttributes.addFlashAttribute("custormes", customers);
       return new RedirectView("/signinList", true);
    }

    @RequestMapping(value = "/signinFilterListJson", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<Customer> signinFilterListJson(
            @RequestBody  Customer customer) {
        List<Customer> customers = customerService.filterCustomerByFields(customer);
        return customers;
    }
}
