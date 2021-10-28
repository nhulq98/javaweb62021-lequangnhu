package com.laptrinhjavaweb.utils;

import com.laptrinhjavaweb.config.ApplicationContextHolder;
import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.dto.response.StaffBuildingResponse;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.repository.UserRepository;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Utils{

    /**
     * make the object eligible for garbage collection by de-referencing the object
     *
     * @param param
     * @param <T>
     */
    public static <T> void destroyReference(T... param) {
        for (T item : param) {
            item = null;
        }
        //System.gc();// call Garbage collection
    }

    /**
     * Generic method to create condition clause with values have typed is String become like this.
     * Example: " AND name LIKE '%value%' "
     *
     * @param fieldName
     * @param value
     * @return
     */
    public static StringBuilder createConditionForStringByLike(String fieldName, String value) {
        if (StringUtils.isNotEmpty(value)) {
            return new StringBuilder(" AND ")
                    .append(fieldName)
                    .append(" LIKE '%")
                    .append(value)
                    .append("%' ");
        }
        return new StringBuilder();
    }

    /**
     * Generic method to create a condition with values have typed is Integer become like this.
     * Example: " AND age = 23 "
     *
     * @param fieldName
     * @param value
     * @return
     */
    public static StringBuilder createConditionForNumber(String fieldName, Number value) {
        if (value != null) {
            return new StringBuilder(" AND ")
                    .append(fieldName)
                    .append("=")
                    .append(value).append(" ");
        }
        return new StringBuilder();
    }

    public static <T> List<StaffBuildingResponse> setCheckedForStaffs(List<T> list){
        List<StaffBuildingResponse> result = new ArrayList<>();
        UserRepository userRepository = ApplicationContextHolder.getContext().getBean(UserRepository.class);

        List<UserEntity> allStaffs = userRepository.findByStatusAndRoles_Code(SystemConstant.ACTIVE_STATUS, SystemConstant.ROLE_STAFF.split("_")[1]);

        allStaffs.forEach(
                item -> {
                    String checked = SystemConstant.EMPTY_STRING;
                    if (list.contains(item)) {
                        checked = "checked";
                    }
                    try {

                        Field field = item.getClass().getSuperclass().getDeclaredField("id");
                        Field field2 = item.getClass().getDeclaredField("fullName");
                        field.setAccessible(true);
                        field2.setAccessible(true);

                        Object id = field.get(item);
                        Object fullName = field2.get(item);

                        result.add(new StaffBuildingResponse(Long.parseLong(String.valueOf(id)), String.valueOf(fullName), checked));
                    } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }

                }
        );
        return result;
    }
}
