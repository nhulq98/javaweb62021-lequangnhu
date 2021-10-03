package com.laptrinhjavaweb.controller.admin;

import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.converter.CustomerConverter;
import com.laptrinhjavaweb.dto.request.CustomerRequest;
import com.laptrinhjavaweb.dto.response.CustomerResponse;
import com.laptrinhjavaweb.service.IAssignmentBuildingService;
import com.laptrinhjavaweb.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CustomerController {

    @Autowired
    ICustomerService service;

    @Autowired
    IAssignmentBuildingService assignmentBuildingService;

    @Autowired
    CustomerConverter converter;

    @GetMapping(value = "/admin/customer-list")
    public ModelAndView getAll(@ModelAttribute(SystemConstant.CUSTOMMER_SEARCH_FORM_MODEL)CustomerRequest customerSearchModel) {
        ModelAndView mav = new ModelAndView("admin/customer/list");

        List<CustomerResponse> result = service.findByCondition(converter.convertRequestToMap(customerSearchModel));


        //add model to view
        mav.addObject(SystemConstant.CUSTOMMER_SEARCH_FORM_MODEL, customerSearchModel);
        mav.addObject(SystemConstant.STAFF, assignmentBuildingService.findAllStaff());

        //result for search function
        mav.addObject(SystemConstant.SEARCH_RESULT_MODEL, result);
        return mav;
    }
}