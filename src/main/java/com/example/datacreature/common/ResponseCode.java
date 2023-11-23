package com.example.datacreature.common;

public interface ResponseCode { // interface 변수는 "String n" 이라고 선언해도 "public static final"로 변한다.

    // HTTP Status 200
    public static final String SUCCESS = "SU";

    // HTTP Status 400
    public static final String VALIDATION_FAILED = "VF";
    public static final String DUPLICATE_EMAIL = "DE";
    public static final String DUPLICATE_NICKNAME = "DN";
    public static final String DUPLICATE_TEL_NUMBER = "DT";
    public static final String NOT_EXISTED_USER = "NU";
    public static final String NOT_EXISTED_EXAMPLE = "NE";

    // HTTP Status 401
    public static final String SIGN_IN_FAIL = "SF";
    public static final String AUTHORIZATION_FAIL = "AF";

    // HTTP Status 403
    public static final String NO_PERMISSION = "NP";

    // HTTP Status 500
    public static final String DATABASE_ERROR = "DBE";
}
