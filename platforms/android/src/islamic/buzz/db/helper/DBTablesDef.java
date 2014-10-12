
package islamic.buzz.db.helper;

/**
 * Table definitions and name constants
 */

// Convention:
// 1. Table Name constant will be as T_<Table Name>
// 2. Column Name constant should be as: C_<Table Name>_<Column_Name>
// 3. Other constants relevant to table will be as <Table Name>_<Constant Name>

public interface DBTablesDef {
    // Shopping Bag
    String T_SHOPPING_BAG = "ShoppingBag";

    String C_SHOPPING_BAG_SKU_CODE = "skuCode";

    String C_SHOPPING_BAG_QUANTITY = "quantity";

    String C_SHOPPING_BAG_ITEM_INDEX = "itemIndex";

    String C_SHOPPING_BAG_IS_GIFT = "isGift";

    String C_SHOPPING_BAG_WEB_ID = "webId";

    String C_SHOPPING_BAG_COLLECTION_WEB_ID = "collectionWebId";

    String C_SHOPPING_BAG_REGISTRY_ID = "registryId";

    String C_SHOPPING_BAG_REGISTRY_QTY = "registryQty";

    String C_SHOPPING_BAG_REGISTRY_NAME = "registryName";

    String C_SHOPPING_BAG_REGISTRY_TYPE = "registryType";

    String C_SHOPPING_BAG_REGISTRY_SHIPID = "shipToId";

    String C_SHOPPING_BAG_REGISTRY_WANTEDQTY = "wantedQty";

    // Search Suggestions
    String T_SEARCH_SUGGESTIONS = "SearchSuggestions";

    String C_SEARCH_SUGGESTIONS_ID = "id";

    String C_SEARCH_SUGGESTIONS_SEARCH_KEYWORD = "searchKeyword";

    String C_SEARCH_SUGGESTIONS_SEARCH_DATE_TIME = "searchDateTime";

    int SEARCH_SUGGESTIONS_MAX_VALUE = 25;

    // Searched Places
    String T_SEARCH_PLACES = "SearchPlaces";

    String C_SEARCH_PLACES_ID = "id";

    String C_SEARCH_PLACES_CITY_NAME = "city_name";

    String C_SEARCH_PLACES_CITY_LATITUDE = "city_latitude";

    String C_SEARCH_PLACES_CITY_LONGITUDE = "city_longitude";

    String C_SEARCH_PLACES_CITY_FEATURED_NAME = "city_featured_name";

    // List and Registry Recent Searches

    String T_DICT_SEARCHWORDS = "dictionary";

    String KEY_WORD = "_id";

    String KEY_FIELD = "key";

    String VALUE_FIELD = "value";

    String ENCRYPTION_FIELD = "encryption";

    String PRIMARY_KEY_NAME = "securekey";

    // Anonymous count column
    String COUNT = "COUNT";
}
