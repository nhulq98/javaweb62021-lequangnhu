package com.laptrinhjavaweb.controller.admin;

import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.converter.CustomerConverter;
import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.dto.request.CustomerRequest;
import com.laptrinhjavaweb.dto.response.CustomerResponse;
import com.laptrinhjavaweb.repository.CustomerRepository;
import com.laptrinhjavaweb.service.IAssignmentBuildingService;
import com.laptrinhjavaweb.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CustomerController {

    @Autowired
    ICustomerService service;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    IAssignmentBuildingService assignmentBuildingService;

    @Autowired
    CustomerConverter converter;

    @GetMapping(value = "/admin/customer-list")
    public ModelAndView getAll(@ModelAttribute(SystemConstant.CUSTOMMER_SEARCH_FORM_MODEL)CustomerRequest customerSearchModel) {
        ModelAndView mav = new ModelAndView("admin/customer/list");

        List<CustomerResponse> result = service.findByCondition(customerSearchModel);

        //add model to view
        mav.addObject(SystemConstant.CUSTOMMER_SEARCH_FORM_MODEL, customerSearchModel);
        mav.addObject(SystemConstant.STAFF, assignmentBuildingService.findAllStaff());

        //result for search function
        mav.addObject(SystemConstant.SEARCH_RESULT_MODEL, result);
        return mav;
    }

    @GetMapping(value = "/admin/customer-edit")
    public ModelAndView create(@ModelAttribute(SystemConstant.CUSTOMMER_MODEL) CustomerDTO customerDTO) {
        ModelAndView mav = new ModelAndView("admin/customer/edit");

        // add model to view
        mav.addObject(SystemConstant.CUSTOMMER_MODEL, customerDTO);

        return mav;
    }
    @GetMapping(value = "/admin/customer-edit-{id}")
    public ModelAndView edit(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("admin/customer/edit");

        CustomerDTO customerDTO = converter.convertEntityToDTO(customerRepository.findOne(id));

        // add model to view
        mav.addObject(SystemConstant.CUSTOMMER_MODEL, customerDTO);

        return mav;
    }
}