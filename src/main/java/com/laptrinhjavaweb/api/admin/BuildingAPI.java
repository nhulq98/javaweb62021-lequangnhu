package com.laptrinhjavaweb.api.admin;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.request.BuildingRequest;
import com.laptrinhjavaweb.dto.response.BuildingResponse;
import com.laptrinhjavaweb.service.IBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/building")
public class BuildingAPI {

    @Autowired // find bean is created and inject into.
    private IBuildingService buildingService;

    //SCOPE FOR GET DATA

    @PostMapping("/{id}")
    public ResponseEntity<BuildingDTO> getOne(@PathVariable(name = "id") Long id) {

        return ResponseEntity.status(HttpStatus.OK).body(buildingService.getOne(id));
    }

    @GetMapping
    public ResponseEntity<List<BuildingResponse>> findByCondition(@RequestParam Map<String, Object> requestParam,
                                                           @RequestParam(value = "listType", required = false) List<String> listType) {
        requestParam.put("buildingTypes", listType);
        return ResponseEntity.status(HttpStatus.OK).body(buildingService.findByCondition(requestParam));
    }

    //SCOPE FOR CHANGE DATA

    @PostMapping
    public ResponseEntity<BuildingDTO> createBuilding(@RequestBody BuildingDTO newBuilding) {

        buildingService.updateOrSave(newBuilding);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteBuilding(@RequestBody BuildingRequest buildingRequest) {
        System.out.println(buildingRequest.getIds());
        buildingService.deleteByListId(buildingRequest.getIds());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateBuilding(@RequestBody BuildingDTO dto) {

        buildingService.updateOrSave(dto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}