package com.laptrinhjavaweb.utils;

import org.apache.commons.lang.StringUtils;

public class Utils{

    /**
     * make for object đủ đk để Garbage collection bằng cách hủy tham chiếu đến đối tượng
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
}
