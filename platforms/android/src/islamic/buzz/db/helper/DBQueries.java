
package islamic.buzz.db.helper;

/**
 * Queries Map
 */
public enum DBQueries {
    CREATE_TABLE_SHOPPING_BAG("CREATE TABLE IF NOT EXISTS " + DBTablesDef.T_SHOPPING_BAG
            + "("
            + DBTablesDef.C_SHOPPING_BAG_SKU_CODE
            + " text primary key, "
            + DBTablesDef.C_SHOPPING_BAG_QUANTITY
            + " int,"
            + DBTablesDef.C_SHOPPING_BAG_ITEM_INDEX
            + " text,"
            + DBTablesDef.C_SHOPPING_BAG_WEB_ID
            + " text,"
            + DBTablesDef.C_SHOPPING_BAG_COLLECTION_WEB_ID
            + " text,"
            + DBTablesDef.C_SHOPPING_BAG_REGISTRY_ID
            + " text,"
            + DBTablesDef.C_SHOPPING_BAG_REGISTRY_QTY
            + " text,"
            + DBTablesDef.C_SHOPPING_BAG_REGISTRY_NAME
            + " text,"
            + DBTablesDef.C_SHOPPING_BAG_REGISTRY_SHIPID
            + " text,"
            + DBTablesDef.C_SHOPPING_BAG_REGISTRY_TYPE
            + " text,"
            + DBTablesDef.C_SHOPPING_BAG_REGISTRY_WANTEDQTY
            + " text,"
            + DBTablesDef.C_SHOPPING_BAG_IS_GIFT
            + " int DEFAULT (0))"),
    CREATE_TABLE_SEARCHED_PLACES("CREATE TABLE IF NOT EXISTS " + DBTablesDef.T_SEARCH_PLACES
            + " ("
            + DBTablesDef.C_SEARCH_PLACES_ID
            + " integer primary key autoincrement, "
            + DBTablesDef.C_SEARCH_PLACES_CITY_NAME
            + " text, "
            + DBTablesDef.C_SEARCH_PLACES_CITY_LATITUDE
            + " text, "
            + DBTablesDef.C_SEARCH_PLACES_CITY_LONGITUDE
            + " text, "
            + DBTablesDef.C_SEARCH_PLACES_CITY_FEATURED_NAME
            + " text) "),
    CREATE_TABLE_SEARCH_SUGGESTION("CREATE TABLE IF NOT EXISTS " + DBTablesDef.T_SEARCH_SUGGESTIONS
            + " ("
            + DBTablesDef.C_SEARCH_SUGGESTIONS_ID
            + " integer primary key autoincrement, "
            + DBTablesDef.C_SEARCH_SUGGESTIONS_SEARCH_KEYWORD
            + " text,"
            + DBTablesDef.C_SEARCH_SUGGESTIONS_SEARCH_DATE_TIME
            + " datetime default current_timestamp, "
            + " UNIQUE ("
            + DBTablesDef.C_SEARCH_SUGGESTIONS_SEARCH_KEYWORD
            + ") ON CONFLICT REPLACE)"),
    CREATE_TABLE_DICT_SEARCHWORDS("CREATE TABLE " + DBTablesDef.T_DICT_SEARCHWORDS
            + " ("
            + DBTablesDef.KEY_WORD
            + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + DBTablesDef.KEY_FIELD
            + " TEXT, "
            + DBTablesDef.VALUE_FIELD
            + " TEXT, "
            + DBTablesDef.ENCRYPTION_FIELD
            + " BOOLEAN);"),
    DROP_TABLE_SHOPPING_BAG("DROP TABLE IF EXISTS " + DBTablesDef.T_SHOPPING_BAG),
    DROP_TABLE_SEARCH_PLACES("DROP TABLE IF EXISTS " + DBTablesDef.T_SEARCH_PLACES),
    DROP_TABLE_SEARCH_SUGGESTIONS("DROP TABLE IF EXISTS " + DBTablesDef.T_SEARCH_SUGGESTIONS),
    DROP_TABLE_DICT_SEARCHWORDS("DROP TABLE IF EXISTS " + DBTablesDef.T_DICT_SEARCHWORDS),
    COUNT("SELECT COUNT(1) COUNT FROM ?"),
    SEARCH_SUGGESTIONS_FILTER_DATA("SELECT * FROM " + DBTablesDef.T_SEARCH_SUGGESTIONS
            + " WHERE "
            + DBTablesDef.C_SEARCH_SUGGESTIONS_SEARCH_KEYWORD
            + " LIKE '%?%'"
            + " ORDER BY "
            + DBTablesDef.C_SEARCH_SUGGESTIONS_SEARCH_DATE_TIME
            + " DESC"
            + " LIMIT 25");

    private String query;

    private DBQueries(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
