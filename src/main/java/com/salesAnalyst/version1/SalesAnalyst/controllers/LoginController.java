package com.salesAnalyst.version1.SalesAnalyst.controllers;

import com.salesAnalyst.version1.SalesAnalyst.entities.Users;
import com.salesAnalyst.version1.SalesAnalyst.repositories.UsersRepository;
import com.salesAnalyst.version1.SalesAnalyst.serviceFacades.SaleserviceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Calendar;

@Controller
@SessionAttributes("username")
public class LoginController {
    @Autowired // This means to inject the bean called userRepository
    private UsersRepository userRepository;
@Autowired
private SaleserviceFacade saleserviceFacade;
    @Value("${app.timezone}")
    private String timezone;
    @Value("${app.months}")
    private String months;

    @PostMapping(path="/login") // Map ONLY POST Requests with the path /login
    public String handleUserLogin(RedirectAttributes redirectAttributes, @RequestParam String username,
                                  @RequestParam String password) {

        Users user  = userRepository.findByUsernameAndPassword(username, password);

        if (user == null) { // If user entered incorrect credentials
            return "redirect:/login/error";
        } else { // successful login
            redirectAttributes.addFlashAttribute("user", user);
            redirectAttributes.addFlashAttribute("username", username);
            return "redirect:/user/dashboard";
        }
    }

    @PostMapping(path="/signUp") // Map ONLY POST Requests with the path /signUp
    public String handleUserSignUp(ModelMap model, @RequestParam String username,
                                  @RequestParam String password) {

        Users user = new Users();
        user.setUsername(username);
        user.setPassword(password);
        userRepository.save(user);

        return "redirect:/signUp/success";
    }

    @GetMapping(path="/user/dashboard") // Map ONLY GET Requests with the path /user/dashboard
    public String handleUponSuccessUserLogin(ModelMap model, @ModelAttribute("user") Users currentUser) {
        String[] monthValues = months.split(",");

        if (!model.containsAttribute("username")) {
            return "redirect:/";
        }

        // current year and current month for users timezone needs to be obtained
        java.util.TimeZone tz = java.util.TimeZone.getTimeZone(timezone);
        java.util.Calendar c = java.util.Calendar.getInstance(tz);

        model.addAttribute("currentYear", c.get(Calendar.YEAR));
        model.addAttribute("currentMonth", monthValues[c.get(Calendar.MONTH)]);
        model.addAttribute("monthValues", monthValues);
        model=saleserviceFacade.getDashBoardModel(model,c.get(Calendar.YEAR),c.get(Calendar.MONTH));
        return "dashboard";
    }

    @GetMapping(path="/login/error") // Map ONLY GET Requests with the path /login/error
    public String handleUponFailedUserLogin(ModelMap model) {
        model.put("errorMessage", "Incorrect username or password. Please try again.");
        return "errorLogin";
    }

    @GetMapping(path="/signUp/success") // Map ONLY GET Requests with the path /user/dashboard
    public String handleUponSuccessUserSignUp(ModelMap model) {
        model.put("successMessage", "Successfully signed up. Login with your new credentials.");
        return "successSignUp";
    }
}
