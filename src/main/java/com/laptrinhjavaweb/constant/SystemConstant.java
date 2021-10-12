package com.laptrinhjavaweb.constant;

public class SystemConstant {

    // scope for Spring MVC
    /*Spring security 4: ROLE_ADMIN, Spring security 3 not required*/
    public static final String ROLE_MANAGER = "ROLE_MANAGER";
    /*Spring security 4: ROLE_ADMIN, Spring security 3 not required*/
    public static final String ROLE_STAFF = "ROLE_STAFF";

    //Scope for model data
    public static final String BUILDING_SEARCH_FORM_MODEL = "buildingSearchForm";
    public static final String CUSTOMMER_SEARCH_FORM_MODEL = "customerSearchForm";
    public static final String CUSTOMMER_MODEL = "customer";
    public static final String SEARCH_RESULT_MODEL = "result";
    public static final String MODEL = "model";
    public static final String DISTRICT = "district";
    public static final String STAFF = "staff";
    public static final String STAFF_BUILDING = "staffbuilding";
    public static final String RENT_TYPE = "renttype";
    public static final String RENT_TYPE_EDIT = "renttypeedit";

    // Scope for Logic
    public static final String ISNUMBER = "[0-9]+";
    public static final String IDMODEL = "IDMODEL";


    //scope for config
    public static final String PASSWORD_DEFAULT = "123456";
}
