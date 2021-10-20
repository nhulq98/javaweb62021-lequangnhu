package com.laptrinhjavaweb.api.admin;

import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.dto.request.CustomerRequest;
import com.laptrinhjavaweb.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/customer")
@RestController
public class CustomerAPI {

    @Autowired
    ICustomerService service;

    @PostMapping
    public ResponseEntity<Void> CreateOne(@RequestBody CustomerDTO newDto){

        service.save(newDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestBody CustomerRequest customerRequest){

        service.deleteByListId(customerRequest.getCustomerIds());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody CustomerDTO newDto){

        service.save(newDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}