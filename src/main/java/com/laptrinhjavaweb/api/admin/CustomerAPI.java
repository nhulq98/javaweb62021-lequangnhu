package com.laptrinhjavaweb.api.admin;

import com.laptrinhjavaweb.dto.CustomerDTO;
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

//    @GetMapping
//    public ResponseEntity<List<CustomerDTO>> findByCondition(Map<String, Object> requestParam){
//        //List<CustomerDTO> result = service.findByCondition(requestParam);
//        //return ResponseEntity.status(HttpStatus.OK).body(result);
//        return ResponseEntity.ok().build();
//    }

    @PostMapping
    public ResponseEntity<Void> CreateOne(@RequestBody CustomerDTO newDto){
        service.save(newDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        System.out.println(id);
        service.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody CustomerDTO newDto){
        service.save(newDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
