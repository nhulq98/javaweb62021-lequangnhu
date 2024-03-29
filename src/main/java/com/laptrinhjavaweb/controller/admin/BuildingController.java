package com.laptrinhjavaweb.controller.admin;

import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.converter.BuildingConverter;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.request.BuildingRequest;
import com.laptrinhjavaweb.dto.response.BuildingResponse;
import com.laptrinhjavaweb.dto.response.TypesResponse;
import com.laptrinhjavaweb.service.IAssignmentBuildingService;
import com.laptrinhjavaweb.service.IBuildingService;
import com.laptrinhjavaweb.utils.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller(value = "buildingsControllerOfAdmin")
public class BuildingController {

    @Autowired
    private IBuildingService buildingService;

    @Autowired
    private IAssignmentBuildingService assignmentBuildingService;

    @Autowired
    private BuildingConverter buildingConverter;

    @Autowired
    private MessageUtils messageUtil;

    @RequestMapping(value = "/admin/building-list", method = RequestMethod.GET)
    public ModelAndView getAll(@ModelAttribute(SystemConstant.BUILDING_SEARCH_FORM_MODEL) BuildingRequest buildingSearchModel) {
        ModelAndView mav = new ModelAndView("admin/building/list");
        List<BuildingResponse> result = buildingService.findByCondition(buildingConverter.convertRequestToMap(buildingSearchModel));

        mav.addObject(SystemConstant.BUILDING_SEARCH_FORM_MODEL, buildingSearchModel);
        mav.addObject(SystemConstant.DISTRICT, buildingService.getDistricts());
        mav.addObject(SystemConstant.RENT_TYPE, buildingService.getBuildingTypes());
        mav.addObject(SystemConstant.STAFF, assignmentBuildingService.findAllStaffs());
        mav.addObject(SystemConstant.SEARCH_RESULT_MODEL, result);

        return mav;
    }

    @RequestMapping(value = "/admin/building-edit", method = RequestMethod.GET)
    public ModelAndView create(@ModelAttribute(SystemConstant.MODEL) BuildingDTO model, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/building/edit");

        mav.addObject(SystemConstant.DISTRICT, buildingService.getDistricts());
        mav.addObject(SystemConstant.RENT_TYPE, buildingService.getBuildingTypes());

        return mav;
    }

    //@PreAuthorize("")
    @RequestMapping(value = "/admin/building-edit-{id}", method = RequestMethod.GET)
    public ModelAndView update(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("admin/building/edit");

        BuildingDTO dto = buildingService.getOne(id);
        List<TypesResponse> types = buildingService.getBuildingTypes(dto.getRentTypes());

        mav.addObject(SystemConstant.RENT_TYPE_EDIT, types);
        mav.addObject(SystemConstant.DISTRICT, buildingService.getDistricts());
        mav.addObject(SystemConstant.MODEL, dto);

        return mav;
    }
}
