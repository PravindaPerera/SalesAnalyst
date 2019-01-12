package com.salesAnalyst.version1.SalesAnalyst.controllers;

import com.salesAnalyst.version1.SalesAnalyst.entities.Users;
import com.salesAnalyst.version1.SalesAnalyst.repositories.UsersRepository;
import com.salesAnalyst.version1.SalesAnalyst.serviceFacades.CoustomerAnerlysisFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Calendar;

@Controller
@SessionAttributes("username")
public class LoginController {
    @Autowired // This means to inject the bean called userRepository
    private UsersRepository userRepository;
    @Autowired
    private CoustomerAnerlysisFacade coustomerAnerlysisFacade;
    @Value("${app.timezone}")
    private String timezone;
    @Value("${app.months}")
    private String months;

    @PostMapping(path="/login") // Map ONLY POST Requests with the path /login
    public String handleUserLogin(RedirectAttributes redirectAttributes,
                                  @RequestParam String username,
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

        model= coustomerAnerlysisFacade.getDashBoardModel(model,c.get(Calendar.YEAR),c.get(Calendar.MONTH));

        model.addAttribute("currentYear", c.get(Calendar.YEAR));
        model.addAttribute("currentMonth", monthValues[c.get(Calendar.MONTH)]);
        model.addAttribute("monthValues", monthValues);

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

    @GetMapping(path="/user/history") // Map ONLY GET Requests with the path /user/history
    public String handleHistoryDisplay(ModelMap model, @RequestParam(value = "year", required = false) String year) {
        String[] monthValues = months.split(",");

        if (!model.containsAttribute("username")) {
            return "redirect:/";
        }

        // current year and current month for users timezone needs to be obtained
        java.util.TimeZone tz = java.util.TimeZone.getTimeZone(timezone);
        java.util.Calendar c = java.util.Calendar.getInstance(tz);

        model.addAttribute("currentYear", c.get(Calendar.YEAR));

        String selectedYear = Integer.toString(c.get(Calendar.YEAR));

        if (year != null) {
            selectedYear = year;
        }

        // @todo get the unique set of years that the sales records are present in the db
        ArrayList<String> years = new ArrayList<String>();
        years.add("2018");
        years.add("2017");
        years.add("2016");
        years.add("2015");

        model=coustomerAnerlysisFacade.getDashBoardModel(model,Integer.parseInt(selectedYear),c.get(Calendar.MONTH));

        model.addAttribute("monthValues", monthValues);
        model.addAttribute("years", years);
        model.addAttribute("selectedYear", selectedYear);
        return "history";
    }
}
