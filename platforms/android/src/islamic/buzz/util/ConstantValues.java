
package islamic.buzz.util;

public interface ConstantValues {


    String APP_PLATFORM = "android";

    // Content type
    public static final String CONTENT_TYPE = "application/json";



    // Authentication grant type.
    String GRANT_TYPE_PASSWORD = "password";

    // Refresh token grant type
    String GRANT_TYPE_REFRESH_TOKEN = "refresh_token";

    String ACCEPT_JSON = "application/json";

    String ACCEPT_XML = "application/xml";

    String HEADER_API_KEY = "X-APP-API_KEY";

    String HEADER_ACCEPT = "Accept";


    String URL_PRODUCT = "/product";

    String HEADER_CHANNEL = "?channel=mobile";

    String VALUE_MOBILE = "mobile";

    // time when access token will be refreshed before its expires
    public static int APP_ACCESS_TOKEN_EXPIRY = 600; // 10 *60 seconds


    String GEOCODE_URL = "http://maps.googleapis.com/maps/api/geocode/json";


    double DEBUG_LATITUDE = 43.120094;

    double DEBUG_LONGITUDE = -88.123104;

    String CAMPAIGN_ID = "0ece169b898b0e4a";

    Double STORE_DEFAULT_US_LATITUDE = 40.0000;

    Double STORE_DEFAULT_US_LONGITUDE = -100.0000;

    Float STORE_DEFAULT_US_ZOOM = 4.0f;

    /**
     * Product matrix page constants.
     */
    int PMP_PRODUCTS_DEFAULT_LIMIT = 12;

    int PMP_PRODUCTS_DEFAULT_OFFSET = 1;

    int PMP_PRODUCTS_DEFAULT_SORTID = 3;

    /** Image Caching constants **/
    // Msg to notify activity that image has been loaded from disk cache.
    int MSG_IMG_LOADED_FROM_DISK = 1;

    // Msg to notify activity that image is not present in disk cache.
    int MSG_IMG_NOT_PPRESENT_IN_CACHE = 2;

    // Bundle constant for notifying UI thread with image key.
    String BUNDLE_IMAGE_KEY = "image_key";

    /*
     * Dialog Constants Reserved from 4000-4099
     */
    String DIALOG_ID = "dialogId";

    String SUB_DIALOG_ID = "subDialogId";

    int SUB_DIALOG_ID_DEFAULT = 4000;

    int ALERT_DIALOG_DEFAULT = SUB_DIALOG_ID_DEFAULT + 1;

    int PROGRESS_DIALOG_DEFAULT = ALERT_DIALOG_DEFAULT + 1;

    int LOCATION_SETTINGS_SUB_DIALOG_ID = PROGRESS_DIALOG_DEFAULT + 1;

    int FETCHING_LOCATION_SUB_DIALOG_ID = LOCATION_SETTINGS_SUB_DIALOG_ID + 1;

    int FETCHING_STORES_SUB_DIALOG_ID = FETCHING_LOCATION_SUB_DIALOG_ID + 1;

    int CANCEL_CLICKED = FETCHING_STORES_SUB_DIALOG_ID + 1;

    int POSITIVE_BUTTON_CLICKED = CANCEL_CLICKED + 1;

    int NEGATIVE_BUTTON_CLICKED = POSITIVE_BUTTON_CLICKED + 1;

    int NEUTRAL_BUTTON_CLICKED = NEGATIVE_BUTTON_CLICKED + 1;

    int PRODUCT_IMAGE_DIALOG = NEUTRAL_BUTTON_CLICKED + 1;

    int PRODUCT_IMAGE_LOAD = PRODUCT_IMAGE_DIALOG + 1;


    /*
     * Preferences Constants
     */

    String APP_PREFERENCE = "preference";

    String USER_LOC_PREF = "LastUserLoc";

    String USER_STORE_PREF = "MyStore";

    String DIALOG_TITLE = "title";

    String DIALOG_MESSAGE = "message";

    String DIALOG_POSITIVE_BUTTON_TEXT = "positiveBtnText";

    String DIALOG_NEGATIVE_BUTTON_TEXT = "negativeBtnText";

    String DIALOG_NEUTRAL_BUTTON_TEXT = "neutralBtnText";

    String DIALOG_DRAWABLE_ID = "drawableID";

    String DIALOG_EXTRA = "extra";

    String IS_CUSTOM_DIALOG_VIEW = "isCustomView";

    String IS_DIALOG_CANCELLABLE = "isCancellable";

    String IS_DIALOG_INDETERMINATE = "isIndeterminate";

    String IS_DIALOG_CANCEL_ON_OUTSIDE_TOUCH = "isOutsideCancellable";

    String RESULTANT_RECEIVER = "resultReceiver";

    String ADD_DUMMY_CATEGORIES = "AddDummyCategory";

    String CURRENT_CATEGORY_PAGE = "1";

    String USER_STORE_INFO = "userstore";

    String USER_LAST_LOCATION = "userlocation";


    /*
     * Adapter Procedures Constants
     */

    String ADAPTER_CATEGORY = "CategoryAdapter";

    String ADAPTER_STORE_LOCATOR = "StoreLocatorAdapter";

    /*
     * Layout Constants
     */

    int CATEGORY_IMAGE_HEIGHT = 240;

    int CATEGORY_IMAGE_WIDTH = 278;

    /** Fragment Constants **/

    String FRAGMENT_CATEGORY_NAME = "item_name";

    String FRAGMENT_SUBCATEGORY_NAME = "item_subcategory_name";

    String CATEGORY_ITEM_TYPE_CATEGORY = "category";

    String CATEGORY_ITEM_TYPE_CATALOG = "catalog";

    /** Toast constants **/
    String SHOW_TOAST = "show_toast";

    /** Google service error dialog constants **/
    // Unique tag for the google service error dialog fragment.
    String GOOGLE_SERVICE_DIALOG_ERROR = "dialog_error";

    // Google service connection failure resolve request error key.
    String RESOLUTION_REQUEST_ERROR_KEY = "resolution_request_error";


    /**
     * Share Constants
     */
    String SHARE_DIALOG_TAG = "share_dialog";

    String SHARE_VIA = "share_via";

    int SHARE_FACEBOOK = 7001;

    int SHARE_TWITTER = 7002;

    String TWITTER_API_KEY = "v1MbjtSP51voQOAu394Cw";

    String TWITTER_API_SECRET = "ciSPNHWogW6fhhy3KY5T3S1hgGdHcQ73YnoIxJsgo8";

    String TWITTER_CALLBACK_URL = "http://www.kohls.com";

    String PRODUCT_LINK = "product_link";

    String PRODUCT_IMAGE_LINK = "product_image_link";

    String TWITTER_OAUTH_KEY_SECRET = "oauth_token_secret";

    String TWITTER_OAUTH_KEY_TOKEN = "oauth_token";

    String TWITTER_IS_USER_AUTHORIZED = "twitter_is_user_authorized";

    String EMAIL_SUBJECT = "Checkout this Product";

    /*
     * Configuration changes
     */

    String APP_CONFIG = "appConfig";

    /*
     * Forsee debug changes
     */

    // Sign in extra keys.
    String EXTRA_KEY_USER_ID = "key_user_id";

    String EXTRA_KEY_PASSWORD = "key_password";

    /**
     * Preference keys.
     */
    String PREF_ACCESS_TOKEN_KEY = "access_token";

    String PREF_REFRESH_TOKEN_KEY = "refresh_token";

    String PREF_USERNAME_KEY = "user_name";

    String PREF_USER_LOYALTY_ID = "user_loyalty_id";

    String PREF_USERNAME_ID = "user_id";

    String PREF_EMAIL_KEY = "email_address";

    String PREF_SIGN_IN_TIME_KEY = "sign_in_time";

    String PREF_EXPIRE_TIME_KEY = "expire_time";

    String PREF_SECURE_TIME_KEY = "secure_time";

    String PREF_USERLASTNAME_KEY = "user_lastname";

    String PREF_USER_SIGNED_IN_STATUS = "user_signed_in_status";

    String PREF_IDLE_TIME_KEY = "idle_time";

    // TODO Remove variable PREF_OMNITURE_SERVER when app goes on production
    public static final String PREF_DIGESTED_MSG = "PREF_DIGESTED_MSG";

    public static final String PREF_STRING_MSG = "PREF_STRING_MSG";

    // TODO Remove variable PREF_OMNITURE_SERVER when app goes on production
    String PREF_OMNITURE_SERVER = "omniture_server";

    // TODO Remove variable PREF_OMNITURE_FIRST_LAUNCH when app goes on
    // production
    String PREF_OMNITURE_FIRST_LAUNCH = "omniture_is_first_launch";

    // Request to code when sign in activity is opened from any other activity.
    int REQUEST_CODE_SIGN_IN_ACTIVITY = 1;

    int REQUEST_CODE_SCAN_ACTIVITY = REQUEST_CODE_SIGN_IN_ACTIVITY + 1;

    int REQUEST_CODE_CREATE_ACCOUNT = 1006;

    int REQUEST_CODE_SIGN_IN_ACTIVITY_FROM_MY_INFO = 1003;

    int REQUEST_CODE_SIGN_IN_ACTIVITY_FROM_HYBRID = REQUEST_CODE_SIGN_IN_ACTIVITY_FROM_MY_INFO + 1;

    String OMNITURE_CONFIGURATION_RSSID = "kohlsnativeappprod";

    String OMNITURE_CONFIGURATION_TRACKING_SERVER = "172.16.4.168:50000/pac";

    // Collection
    String MIME = "text/html";

    String ENCODING_UTF8 = "utf-8";

    String HTML_END_TAG = "</font>";

    String HTML_COLOR_START_TAG = "<font color='gray'>";



    // Create profile extra keys.
    String EXTRA_KEY_FIRST_NAME = "key_first_name";

    String EXTRA_KEY_LAST_NAME = "key_last_name";

    String EXTRA_KEY_EMAIL = "key_email";

    String EXTRA_KEY_RETYPE_EMAIL = "key_retype_email";

    String EXTRA_KEY_RETYPE_PASSWORD = "key_retype_password";

    String EXTRA_KEY_SEARCH_KEYWORD = "key_search_keyword";






    // Scan Result Constants

    public static final int RESULT_CODE_CLOSE_ACTIVITY_ONLY = 7002;

    public static final int CACHE_DIR = 1001;

    public static final int EXTERNAL_CACHE_DIR = CACHE_DIR + 1;

    public static final long ONE_MINUTE_IN_MILLIS = 60000;// millisecs

    // Name of the file that will contain the LinkedHashMap of the data
    public static final String CATALOG_FILENAME = "catalog.tmp";

    // Name of the file that will contain the LinkedHashMap of the data
    public static final String CATEGORY_FILENAME = "category.tmp";

    // Day Constants
    public static final String CMS_FILENAME = "cms.tmp";

    // Day Constants
    String DAY_MONDAY = "monday";

    String DAY_TUESDAY = "tuesday";

    String DAY_WEDNESDAY = "wednesday";

    String DAY_THURSDAY = "thursday";

    String DAY_FRIDAY = "friday";

    String DAY_SATURDAY = "saturday";

    String DAY_SUNDAY = "sunday";

    // GeoCode Specific Strings.
    String GEOCODE_US_SHORT_NAME = "US";

    String GEOCODE_CITYNAME = "locality";

    String GEOCODE_STATENAME = "administrative_area_level_1";

    String GEOCODE_COUNTYNAME = "administrative_area_level_2";

    String GEOCODE_PINCODE = "postal_code";

    String GEOCODE_COUNTRY = "country";

    String GEOCODE_POI = "point_of_interest";

    String GEOCODE_ESTABLISHMENT = "establishment";

    String GEOCODE_ROUTE = "route";

    String STREET_NUMBER = "street_number";

    String GEOCODE_ADDRESS_PARAMS = "address";

    String GEOCODE_CLIENTID = "client";

    String GEOCODE_SENSOR_PARAMS = "sensor";

    String SIGNATURE_PARAMETER = "signature";

    public static final String US_CODE = "United States";

    // Request code to use when launching the resolution activity
    public static final int REQUEST_RESOLVE_ERROR = 1001;

    public static final String BUNDLE_STORE_DETAIL = "store_detail";

    public static final String BUNDLE_SEARCH_LATITUDE = "search_latitude";

    public static final String BUNDLE_SEARCH_LONGITUDE = "search_longitude";

    public static final String BUNDLE_PRODUCT_IMAGEURL = "product_imageUrl";

    public static final String BUNDLE_PRODUCT_NAME = "product_nameTitle";

    public static final String BUNDLE_PRODUCT_SALE = "product_salePrice";

    public static final String BUNDLE_PRODUCT_ORIGINAL = "product_oriPrice";

    public static final String BUNDLE_PRODUCT_SKU = "product_sku";

    public static final String BUNDLE_PRODUCT_RATINGS = "product_ratings";

    public static final String BUNDLE_PRODUCT_RATINGS_COUNT = "product_ratings_count";

    public static final int START_GEOCODE_REQUEST = 1001;

    public static final int GET_STORES = START_GEOCODE_REQUEST + 1;

    public static final int GET_STORE_INVENTORY = GET_STORES + 1;

    public static final int MERGE_STORE_INVENTORY = GET_STORE_INVENTORY + 1;

    public static final String GOOGLE_APIS_CLIENT_ID = "gme-kohlsdepartmentstores";

    String SEARCH_FRAGMENT = "search_add";

    // Request and result code for account activity
    public static final int REQUEST_CODE_MANAGE_ACCOUNT = 7001;

    public static final int RESULT_CODE_ATTACH_MANAGE_ACCOUNT_FRAGMENT = 7002;

    public static final String ATTACH_MANAGE_ACCOUNT_FRAGMENT = "ATTACH_MANAGE_ACCOUNT_FRAGMENT";

    public static final String CLOSE_ACTIVITY_ONLY = "CLOSE_ACTIVITY_ONLY";

    String SEARCH_KEYWORD = "search_key";

    String SKU = "sku";


    public static final String WEB_COM = ".com";

    public static final String ASSETS_FOLDER_PATH = "file:///android_asset/";

    // Activity results constants
    public static final int ACTIVITY_RESULT_BACK_PRESS = 10;

    public static final int ACTIVITY_RESULT_FAILURE = 11;


    public static final String DIALOG_EULA_ACCEPT = "ACCEPT";

    public static final String DIALOG_EULA_CANCEL = "CANCEL";

    public static final String DIALOG_EULA_TITLE = "EULA";

    public static final String DIALOG_EULA_DESC = "Accept the licsence agreement or else the applicattion will be closed.";


    // From constants shows from where the user reached to PDP screen.

    public static final String FROM = "from";

    public static final String FROM_RECOMMENDATIONS = "Recommendations";

    public static final String FROM_SCAN = "ScanHelper";

    public static final String FROM_IN_STORE_TOOLS = "InStoreTools";

    public static final String FROM_SHOPPING_BAG = "ShoppingBag";

    public static final String FROM_HOME = "Home";

    public static final String FROM_COLLECTION_PRODUCTS = "CollectionProducts";

    public static final String FROM_SIX_UP_ADAPTER = "SixUpAdapter";

    public static final String FROM_BROWS_FRAGMENT = "BrowsFragment";

    public static final String FROM_RATING_REVIEW = "RatingReview";

    public static final String FROM_FEATURE_BRAND = "FeatureBrand";

    public static final String FROM_BIG_DATA = "BigData";

    String PDP_OFFER_MESSAGE_ADD_TO_BAG_SINGULAR = "AddToBagMessageSingular";

    String PDP_OFFER_MESSAGE_ADD_TO_BAG = "AddToBagMessage";

    String PDP_OFFER_MESSAGE_DISCARD = "DiscardMessage";

    String PDP_OFFER_MESSAGE_GIFT_NOT_ADDED = "OfferGiftNotAdded";

    String EXTRA_KEY_PDP_OFFER_MESSAGES = "offer_msg_hash_map";

    String EXTRA_KEY_PDP_BUY_PRODUCT_NAME = "pdp_offer_buy_product_web_id";


    public static final String APP_CONFIG_FILE = "default_config";

    public static final int REQUEST_CODE_EULA = 111;

    public static final String LIC_ACCEPTANCE = "lic-acceptance";


    
    public static final String APP_TAG = "HAJ_UMRAH";
    public static final int LEFT_SIDE_MENU_WIDTH = 270;

}
