package com.zbam.tajer.utils;

/**
 * Created by z.bazoubandi on 7/16/2018.
 */

public class AppConstants {

    private AppConstants()
    {

    }


    // Names
    public static final String NAME_ACCEPT = "NAME_ACCEPT";
    public static final String NAME_CONTENT_TYPE = "NAME_CONTENT_TYPE";
    public static final String NAME_SECURITY_KEY = "NAME_SECURITY_KEY";
    public static final String NAME_PASSENGER_ID = "NAME_PASSENGER_ID";
    public static final String NAME_AUTHORIZATION = "NAME_AUTHORIZATION_ID";

    // Headers
    public static final String HEADER_ACCEPT = "Accept" ;
    public static final String HEADER_CONTENT_TYPE = "Content-Type" ;
    public static final String HEADER_PASSENGER_ID = "obj_id" ;
    public static final String HEADER_SECURITY_KEY = "security_key" ;
    public static final String HEADER_AUTHORIZATION_KEY = "Authorization" ;
    public static final String TAG = "===>>>";

    //url
   // public static final String BASE_URL = "http://tajer.mobi/";
    public static final String BASE_URL = "http://95.216.51.88:80/";

    public static final String PREF_NAME = "com_tajet_pref";

    public static final int PICK_IMAGE =1 ;
    public static final int PICK_IMAGE1 =2 ;
    public static final int PICK_IMAGE3 =3 ;
    public static final int PICK_IMAGE4 =4 ;

    // Permission Codes
    public static final int PERMISSION_CODE_LOCATION = 2000 ;
    public static final int PERMISSION_CODE_STORAGE = 2001;

    // API CALL CODES
    public static final int API_CODE_LOGIN = 1001;
    public static final int API_CODE_ADVERTISING = 1002;
    public static final int API_CODE_GET_LAST_VERSION = 1003;
    public static final int API_CODE_ADVERTISING_DETAILS = 1004;
    public static final int API_CODE_SEARCH_ADVERTISING = 1005;
    public static final int API_CODE_CATEGORY = 1006;
    public static final int API_CODE_PARENT_CATEGORY = 1007;
    public static final int API_CODE_ADVERTISING_CREATE = 1008;
    public static final int API_CODE_UPLOAD_IMAGE = 1011;
    public static final int API_CODE_City = 1009;
    public static final int API_CODE_PARENT_City = 1010;
    public static final int API_CODE_PARENT_PARAMETER_TYPE = 1011;
    public static final int API_CODE_PARAMETER_TYPE = 1012;
    public static final int API_CODE_CAR_FETCH = 1013;
    public static final int API_CODE_HCAR_FETCH = 1014;
    public static final int API_CODE_BICYCLE_FETCH = 1015;
    public static final int API_CODE_MOTOR_FETCH = 1016;
    public static final int API_CODE_ACCESSORIS_FETCH = 1017;
    public static final int API_CODE_SUPPORT_TOPICS = 1018;
    public static final int API_CODE_PRODUCT_TYPE_FETCH = 1019;
    public static final int API_CODE_MOBILE_FETCH = 1020;
    public static final int API_CODE_FURNITURE_FETCH = 1021;
    public static final int API_CODE_APPLIANCE_FETCH = 1022;
    public static final int API_CODE_TRIP_FETCH = 1023;
    public static final int API_CODE_CRASH_INFO = 1024;
    public static final int API_CODE_ESTATE_FETCH = 1025;
    public static final int API_CODE_CHANNEL = 1026;
    public static final int API_CODE_SEARCH_CHANNEL = 1027;
    public static final int API_CODE_CHANNEL_DETAILS = 1028;
    public static final int API_CODE_UPLOAD_CHANNEL_IMAGE = 1029;
    public static final int API_CODE_CHANNEL_CREATE = 1030;
    public static final int API_CODE_CHANNEL_USERNAME_CREATE = 1031;

    //intent
    public static final String REQ_KEY_INTENT_CATEGORY_OPEN_BY = "categoryOpenBy";
    public static final String REQ_KEY_INTENT_City_OPEN_BY = "cityOpenBy";
    public static final String REQ_KEY_INTENT_PARAMETER_TYPE_OPEN_BY = "parameterTypeOpenBy";
    public static final String REQ_KEY_INTENT_PARAMETER_TYPE_ACTIVITY = "parameterTypeActivity";


    //fields
    public static final String REQ_KEY_USERNAME = "username";
    public static final String REQ_KEY_PASSWORD = "password";
    public static final String REQ_KEY_TOKEN = "token";
    public static final String REQ_KEY_CHANNEL_TOKEN = "channelToken";
    public static final String REQ_KEY_CHANNEL_TOKEN_AD_LIST = "searchChannelToken";
    public static final String REQ_KEY_PAGE_INDEX = "page";
    public static final String REQ_KEY_TITLE_SEARCH = "title";
    public static final String REQ_KEY_CATEGORY_SEARCH = "categoryId";
    public static final String REQ_KEY_CITY_GLOBAL_SEARCH = "searchCityId";
    public static final String REQ_KEY_PARAMETER_TYPE_SEARCH = "parametersId";
    public static final String REQ_KEY_CITY_SEARCH = "locationId";
    public static final String REQ_KEY_PARENT_ID = "parentId";
    public static final String REQ_KEY_PARAMS_NAME = "paramName";
    public static final String REQ_KEY_PARAMETR_SEARCH = "parametrSearch";
    public static final String INTENT_KEY_CHANEEL_OBJ = "channelResponse";
    public static final String REQ_KEY_PARAMETR_ITEMS_SEARCH = "parameterItemSearch";
    public static final String REQ_KEY_ACT_TYPE = "actType";
    public static final String REQ_KEY_USE_TYPE = "usedStatus";
    public static final String REQ_KEY_CONTENT = "content";
    public static final String REQ_KEY_PRICE_TYPE = "priceType";
    public static final String REQ_KEY_PRICE = "price";
    public static final String REQ_KEY_NAME = "name";
    public static final String REQ_KEY_MOBILE = "mobile";
    public static final String REQ_KEY_EMAIL = "email";
    public static final String REQ_KEY_TEL = "tel";
    public static final String REQ_KEY_LOCATION = "locationId";
    public static final String REQ_KEY_USER_TYPE = "userType";
    public static final String REQ_KEY_TYPE = "type";
    public static final String REQ_KEY_SEND_ALERT = "sendAlert";
    public static final String REQ_KEY_IMAGE1 = "image1";
    public static final String REQ_KEY_IMAGE2 = "image2";
    public static final String REQ_KEY_IMAGE3 = "image3";
    public static final String REQ_KEY_IMAGE4 = "image4";

    //car
    public static final String REQ_KEY_CAR_MODEL = "carModel";
    public static final String REQ_KEY_CAR_KMS_DRIVEN = "carKmsDriven";
    public static final String REQ_KEY_CAR_PRODUCT_YEAR = "carProductYear";
    public static final String REQ_KEY_CAR_INSURANCE_VALID_TELL = "carInsuranceValidTill";
    public static final String REQ_KEY_CAR_INSURANCE_DISCOUNT = "carInsuranceDiscount";
    public static final String REQ_KEY_CAR_COLOR = "carColor";
    public static final String REQ_KEY_CAR_FUAL_TYPE = "carFualType";
    public static final String REQ_KEY_CAR_BODY_STATUS = "carBodyStatus";
    public static final String REQ_KEY_CAR_INSIDE_COLOR = "carInsideColor";
    public static final String REQ_KEY_CAR_TRANSMISSION = "carTransmission";
    public static final String REQ_KEY_CAR_PLAQUE = "plaque";

    //hcar
    public static final String REQ_KEY_HCAR_PRODUCT_YEAR = "hcarProductYear";
    public static final String REQ_KEY_HCAR_KMS_DRIVEN = "hcarKmsDriven";
    public static final String REQ_KEY_HCAR_BRAND_NAME = "hcarBrandName";
    public static final String REQ_KEY_HCAR_TYPE_NAME = "hcarTypeName";

    //bicycle
    public static final String REQ_KEY_BICYCLE_SEX_NAME = "bicycleBySexName";
    public static final String REQ_KEY_BICYCLE_PRODUCT_YEAR = "bicycleProductYear";
    public static final String REQ_KEY_BICYCLE_BRAND_NAME = "bicycleBrandName";
    public static final String REQ_KEY_BICYCLE_TYPE_NAME = "bicycleTypeName";
    public static final String REQ_KEY_BICYCLE_COLOR = "bicycleColor";
    public static final String REQ_KEY_BICYCLE_SIZE_NAME = "bicycleBySizeName";
    public static final String REQ_KEY_BICYCLE_MATERIAL_NAME = "bicycleMaterialName";
    public static final String REQ_KEY_BICYCLE_STOP_TYPE_NAME = "bicycleStopTypeName";

    //motor
    public static final String REQ_KEY_MOTOR_PRODUCT_YEAR = "motorProductYear";
    public static final String REQ_KEY_MOTOR_BRAND_NAME = "motorBrandName";
    public static final String REQ_KEY_MOTOR_KMS_DRIVEN = "motorKmsDriven";
    public static final String REQ_KEY_MOTOR_INSURANCE_VALID_TILL = "motorInsuranceValidTill";
    public static final String REQ_KEY_MOTOR_INSURANCE_DISCOUNT = "motorInsuranceDiscount";

    //accessories
    public static final String REQ_KEY_ACCESSORIES_TYPE_NAME = "accessoriesTypeName";


    public static final String REQ_KEY_PRODUCT_TYPE_NAME = "productTypeName";



    //job
    public static final String REQ_KEY_JOB_TYPE_NAME = "jobTypeName";
    public static final String REQ_KEY_JOB_CITY_NAME = "jobCityName";
    public static final String REQ_KEY_JOB_MIN_SALARY = "jobMinSalary";
    public static final String REQ_KEY_JOB_MAX_SALARY = "jobMaxSalary";

    //mobile
    public static final String REQ_KEY_MOBILE_MODEL_NAME = "mobileModelName";
    public static final String REQ_KEY_TABLET_BRAND_NAME = "tabletBrandName";
    public static final String REQ_KEY_MOBILE_OS_NAME = "mobileOsName";
    public static final String REQ_KEY_MOBILE_SIMS_NUM_NAME = "mobileSimsNumber";
    public static final String REQ_KEY_MOBILE_ACCESSORIES = "mobileAccessories";

    //Furniture
    public static final String REQ_KEY_FURNITURE_CONDITION_ID = "FurnitureConditionName";
    public static final String REQ_KEY_FURNITURE_AGE_ID = "FurnitureAgeId";

    //Appliance
    public static final String REQ_KEY_APPLIANCE_BRAND_ID = "ApplianceBrandName";
    public static final String REQ_KEY_APPLIANCE_COLOR_ID = "ApplianceColorName";
    public static final String REQ_KEY_APPLIANCE_TYPE_ID = "ApplianceTypeName";

    //trip
    public static final String REQ_KEY_TRIP_TYPE = "tripTypeName";
    public static final String REQ_KEY_TRIP_TRANSPORT_TYPE = "tripTransportTypeName";
    public static final String REQ_KEY_TRIP_SUBJECT_TYPE = "tripSubjectTypeName";
    public static final String REQ_KEY_TRIP_CITY = "tripCityName";
    public static final String REQ_KEY_TRIP_REQUIRED_DOCUMENTS = "tripRequiredDocuments";
    public static final String REQ_KEY_TRIP_STAY_TIME = "tripStayTime";
    public static final String REQ_KEY_TRIP_DESTINATION= "tripDestination";
    public static final String REQ_KEY_TRIP_END_DATE= "tripEndDate";
    public static final String REQ_KEY_TRIP_START_DATE= "tripStartDate";

    //estate
    public static final String REQ_KEY_ESTATE_AGE = "estateAgeId";
    public static final String REQ_KEY_ESTATE_CABINET = "estateCabinetId";
    public static final String REQ_KEY_ESTATE_COOLING = "estateCoolingId";
    public static final String REQ_KEY_ESTATE_FACING = "estateFacingId";
    public static final String REQ_KEY_ESTATE_FLOOR_COVERING = "estateFloorCoveringId";
    public static final String REQ_KEY_ESTATE_HEATING = "estateHeatingId";
    public static final String REQ_KEY_ESTATE_ROOM_NUMBER = "estateRoomNumberId";
    public static final String REQ_KEY_ESTATE_VIEW = "estateViewId";
    public static final String REQ_KEY_ESTATE_WC = "estateWcId";
    public static final String REQ_KEY_ESTATE_ALLEY_WIDTH = "estateAlleyWidthName";
    public static final String REQ_KEY_ESTATE_AREA = "estateAreaName";
    public static final String REQ_KEY_ESTATE_BUILDING_FLOOR = "estateBuildingFloorName";
    public static final String REQ_KEY_ESTATE_FLOOR = "estateFloorName";
    public static final String REQ_KEY_ESTATE_LAND_AREA = "estateLandAreaName";
    public static final String REQ_KEY_ESTATE_LAND_LENGTH = "estateLandLengthName";
    public static final String REQ_KEY_ESTATE_SHOP_HEIGHT = "estateShopHeightName";
    public static final String REQ_KEY_ESTATE_UNIT_FLOOR = "estateUnitFloorName";
    public static final String REQ_KEY_ESTATE_BALCONY = "estateBalconyName";
    public static final String REQ_KEY_ESTATE_CENTRAL_ANTENNA = "estateCentralAntennaName";
    public static final String REQ_KEY_ESTATE_DEPOT = "estateDepotName";
    public static final String REQ_KEY_ESTATE_DOOR_REMOTE = "estateDoorRemoteName";
    public static final String REQ_KEY_ESTATE_IPHONE = "estateIPhoneName";
    public static final String REQ_KEY_ESTATE_LIFT = "estateLiftName";
    public static final String REQ_KEY_ESTATE_PARKING = "estateParkingName";
    public static final String REQ_KEY_ESTATE_TEL = "estateTelName";

    //channel
    public static final String REQ_KEY_CHANNEL_NAME = "channelName";
    public static final String REQ_KEY_CHANNEL_ADDRESS = "channelAddress";
    public static final String REQ_KEY_CHANNEL_TEL = "channelTel";
    public static final String REQ_KEY_CHANNEL_WEBSITE = "channelWebsite";
    public static final String REQ_KEY_CHANNEL_EMAIL = "channelEmail";
    public static final String REQ_KEY_CHANNEL_DESCRIPTION = "channelDesc";
    public static final String REQ_KEY_CHANNEL_MOBILE = "channelMobile";
    public static final String REQ_KEY_CHANNEL_IMG = "channelLogo";
    public static final String REQ_KEY_CHANNEL_CATEGORY = "categoryId";
    public static final String REQ_KEY_CHANNEL_CITY = "locationId";
    public static final String REQ_KEY_CHANNEL_USER_NAME = "channelUserName";


    //search
    public static final String REQ_KEY_SEARCH_PRICE_TYPE = "searchPriceType";
    public static final String REQ_KEY_SEARCH_USE_TYPE = "searchUsedStatus";
    public static final String REQ_KEY_SEARCH_TYPE = "searchType";
    public static final String REQ_KEY_SEARCH_HAVE_IMAGE = "searchHaveImage";
    public static final String REQ_KEY_SEARCH_MIN_PRICE = "searchMinPrice";
    public static final String REQ_KEY_SEARCH_MAX_PRICE = "searchMaxPrice";
    public static final String REQ_KEY_CATEGORY_NAME_SEARCH = "searchCategoryName";

    // Crash reporter
    public static final String REQUEST_ANDROID_UUID = "vUUID";
    public static final String REQUEST_ANDROID_VERSION = "vAndroidVersion";
    public static final String REQUEST_APP_VERSION = "vAppVersion";
    public static final String REQUEST_DEVICE_COMPANY = "vDeviceCompany";
    public static final String REQUEST_DEVICE_MODEL = "vDeviceModel";
    public static final String REQUEST_SCREEN_SIZE = "vScreenSize";
    public static final String REQUEST_SCREEN_REZOLUTION = "vScreenRezolution";
    public static final String REQUEST_RAM_TOTAL = "vRamTotal";
    public static final String REQUEST_RAM_FREE = "vRamFree";
    public static final String REQUEST_CRASH_MESSAGE = "vMessage";

}
