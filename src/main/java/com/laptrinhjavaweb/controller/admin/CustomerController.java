package com.laptrinhjavaweb.controller.admin;

import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.converter.CustomerConverter;
import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.dto.request.CustomerRequest;
import com.laptrinhjavaweb.dto.response.CustomerResponse;
import com.laptrinhjavaweb.dto.response.TransactionTypeResponse;
import com.laptrinhjavaweb.entity.CustomerEntity;
import com.laptrinhjavaweb.exception.NotFoundException;
import com.laptrinhjavaweb.repository.CustomerRepository;
import com.laptrinhjavaweb.service.IAssignmentBuildingService;
import com.laptrinhjavaweb.service.ICustomerService;
import com.laptrinhjavaweb.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
public class CustomerController {

    @Autowired
    private ICustomerService service;

    @Autowired
    private IAssignmentBuildingService assignmentBuildingService;

    @Autowired
    private ITransactionService transactionService;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerConverter converter;

    @GetMapping(value = "/admin/customer-list")
    public ModelAndView getAll(@ModelAttribute(SystemConstant.CUSTOMMER_SEARCH_FORM_MODEL)CustomerRequest customerSearchModel) {
        ModelAndView mav = new ModelAndView("admin/customer/list");

        List<CustomerResponse> result = service.findByCondition(customerSearchModel);

        mav.addObject(SystemConstant.CUSTOMMER_SEARCH_FORM_MODEL, customerSearchModel);
        mav.addObject(SystemConstant.STAFF, assignmentBuildingService.findAllStaffs());
        mav.addObject(SystemConstant.SEARCH_RESULT_MODEL, result);

        return mav;
    }

    @GetMapping(value = "/admin/customer-new")
    public ModelAndView create(@ModelAttribute(SystemConstant.CUSTOMMER_MODEL) CustomerDTO customerDTO) {

        ModelAndView mav = new ModelAndView("admin/customer/edit");

        //GET transaction type
        //mav.addObject(SystemConstant.TRANSACTION_TYPE, transactionService.getAllTranSactions());
        mav.addObject(SystemConstant.CUSTOMMER_MODEL, customerDTO);

        return mav;
    }
    @GetMapping(value = "/admin/customer-edit-{id}")
    public ModelAndView edit(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("admin/customer/edit");

        CustomerEntity entity = Optional.ofNullable(customerRepository.findOne(id))
                .orElseThrow(()-> new NotFoundException("customer not found!"));

        CustomerDTO customerDTO = converter.convertEntityToDTO(entity);
        List<TransactionTypeResponse> transactionType = transactionService.getAllTranSactions();

        mav.addObject(SystemConstant.CUSTOMMER_TRANSACTION, customerDTO.getTransactionlist());
        mav.addObject(SystemConstant.CUSTOMMER_MODEL, customerDTO);
        mav.addObject(SystemConstant.TRANSACTION_TYPE, transactionType);

        return mav;
    }
}