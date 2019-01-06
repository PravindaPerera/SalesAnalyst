package com.salesAnalyst.version1.SalesAnalyst.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import java.util.Calendar;

@Component
public class OrganizationService {
    @Value("${app.timezone}")
    private String timezone;
    public ModelMap getOrganizationDetails(ModelMap model){
        java.util.TimeZone tz = java.util.TimeZone.getTimeZone(timezone);
        java.util.Calendar c = java.util.Calendar.getInstance(tz);
        model.addAttribute("currentYear", c.get(Calendar.YEAR));
        model.addAttribute("customerName", "TGIF Colombo");
        model.addAttribute("customerBusiness", "Restaurant Management");
        model.addAttribute("customerAddress", "555A, Canal Row, Colombo 01");
        return model;
    }
}
