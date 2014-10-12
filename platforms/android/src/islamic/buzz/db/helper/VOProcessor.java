
package islamic.buzz.db.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.content.ContentValues;
import android.database.Cursor;

/**
 * Creates Value Objects based on the table names
 */
public final class VOProcessor {
    private static final VOProcessor mVoProcessor = new VOProcessor();

    private VOProcessor() {
        // Singleton class
    }

    public static final VOProcessor getInstance() {
        return mVoProcessor;
    }

//    private ShoppingBagVO getSBVO(final Cursor cursor) {
//        ShoppingBagVO bagVO = new ShoppingBagVO();
//        bagVO.setSkuCode(cursor.getString(cursor.getColumnIndex(DBTablesDef.C_SHOPPING_BAG_SKU_CODE)));
//        bagVO.setQuantity(cursor.getInt(cursor.getColumnIndex(DBTablesDef.C_SHOPPING_BAG_QUANTITY)));
//        bagVO.setWebId(cursor.getString(cursor.getColumnIndex(DBTablesDef.C_SHOPPING_BAG_WEB_ID)));
//        bagVO.setItemIndex(cursor.getString(cursor.getColumnIndex(DBTablesDef.C_SHOPPING_BAG_ITEM_INDEX)));
//        bagVO.setCollectionWebId(cursor.getString(cursor.getColumnIndex(DBTablesDef.C_SHOPPING_BAG_COLLECTION_WEB_ID)));
//        bagVO.setRegistryID(cursor.getString(cursor.getColumnIndex(DBTablesDef.C_SHOPPING_BAG_REGISTRY_ID)));
//        bagVO.setRegistryQty(cursor.getInt(cursor.getColumnIndex(DBTablesDef.C_SHOPPING_BAG_REGISTRY_QTY)));
//        bagVO.setRegistryName(cursor.getString(cursor.getColumnIndex(DBTablesDef.C_SHOPPING_BAG_REGISTRY_NAME)));
//        bagVO.setRegistryWantedQty(cursor.getString(cursor.getColumnIndex(DBTablesDef.C_SHOPPING_BAG_REGISTRY_WANTEDQTY)));
//        bagVO.setRegistryType(cursor.getString(cursor.getColumnIndex(DBTablesDef.C_SHOPPING_BAG_REGISTRY_TYPE)));
//        bagVO.setRegistryShipID(cursor.getString(cursor.getColumnIndex(DBTablesDef.C_SHOPPING_BAG_REGISTRY_SHIPID)));
//
//        if (cursor.getInt(cursor.getColumnIndex(DBTablesDef.C_SHOPPING_BAG_IS_GIFT)) == 1) {
//            bagVO.setIsGift(true);
//        } else {
//            bagVO.setIsGift(false);
//        }
//        return bagVO;
//    }

//    private SearchPlacesVO getSPVO(final Cursor cursor) {
//        SearchPlacesVO searchPlacesVO = new SearchPlacesVO(Locale.US);
//        searchPlacesVO.setLocality(cursor.getString(cursor.getColumnIndex(DBTablesDef.C_SEARCH_PLACES_CITY_NAME)));
//        searchPlacesVO.setLongitude(cursor.getDouble(cursor.getColumnIndex(DBTablesDef.C_SEARCH_PLACES_CITY_LONGITUDE)));
//        searchPlacesVO.setLatitude(cursor.getDouble(cursor.getColumnIndex(DBTablesDef.C_SEARCH_PLACES_CITY_LATITUDE)));
//        searchPlacesVO.setFeatureName(cursor.getString(cursor.getColumnIndex(DBTablesDef.C_SEARCH_PLACES_CITY_FEATURED_NAME)));
//        return searchPlacesVO;
//    }

//    private SearchSuggestionVO getSSVO(final Cursor cursor) {
//        SearchSuggestionVO suggessionVO = new SearchSuggestionVO();
//        suggessionVO.setSearchKeyword(cursor.getString(cursor.getColumnIndex(DBTablesDef.C_SEARCH_SUGGESTIONS_SEARCH_KEYWORD)));
//        suggessionVO.setSearchDateTime(cursor.getString(cursor.getColumnIndex(DBTablesDef.C_SEARCH_SUGGESTIONS_SEARCH_DATE_TIME)));
//        return suggessionVO;
//    }

    private List<Object> getSBVOList(final Cursor cursor) {
        ArrayList<Object> list = new ArrayList<Object>();
        do {
            //list.add(getSBVO(cursor));
        } while (cursor.moveToNext());

        return list;
    }

//    private List<Object> getSPVOList(final Cursor cursor) {
//        ArrayList<Object> list = new ArrayList<Object>();
//        do {
//            list.add(getSPVO(cursor));
//        } while (cursor.moveToNext());
//
//        return list;
//    }

//    private List<Object> getSSVOList(final Cursor cursor) {
//        ArrayList<Object> list = new ArrayList<Object>();
//        if (cursor.moveToFirst()) {
//            do {
//                list.add(getSSVO(cursor));
//            } while (cursor.moveToNext());
//        }
//        return list;
//    }

//    private ContentValues getSBValues(final Object ivo) {
//        ContentValues values = new ContentValues();
//        ShoppingBagVO vo = (ShoppingBagVO) ivo;
//        if (vo.getSkuCode() != null) {
//            values.put(DBTablesDef.C_SHOPPING_BAG_SKU_CODE, vo.getSkuCode());
//        }
//        values.put(DBTablesDef.C_SHOPPING_BAG_QUANTITY, vo.getQuantity());
//
//        if (vo.getItemIndex() != null) {
//
//            values.put(DBTablesDef.C_SHOPPING_BAG_ITEM_INDEX, vo.getItemIndex());
//        }
//
//        if (vo.getWebId() != null) {
//            values.put(DBTablesDef.C_SHOPPING_BAG_WEB_ID, vo.getWebId());
//        }
//        if (vo.getCollectionWebId() != null) {
//            values.put(DBTablesDef.C_SHOPPING_BAG_COLLECTION_WEB_ID, vo.getCollectionWebId());
//        }
//
//        if (vo.getRegistryID() != null) {
//            values.put(DBTablesDef.C_SHOPPING_BAG_REGISTRY_ID, vo.getRegistryID());
//        }
//
//        values.put(DBTablesDef.C_SHOPPING_BAG_REGISTRY_QTY, vo.getRegistryQty());
//        values.put(DBTablesDef.C_SHOPPING_BAG_IS_GIFT, vo.getIsGift());
//
//        return values;
//    }

    private ContentValues getSPValues(final Object ivo) {
        ContentValues values = new ContentValues();
        //SearchPlacesVO vo = (SearchPlacesVO) ivo;
        //String cityName = vo.getLocality();
//        if (cityName == null || cityName.length() == 0) {
//            cityName = vo.getAdminArea();
//        }
//        values.put(DBTablesDef.C_SEARCH_PLACES_CITY_NAME, cityName);
//        values.put(DBTablesDef.C_SEARCH_PLACES_CITY_LONGITUDE, vo.getLongitude());
//        values.put(DBTablesDef.C_SEARCH_PLACES_CITY_LATITUDE, vo.getLatitude());
//        values.put(DBTablesDef.C_SEARCH_PLACES_CITY_FEATURED_NAME, vo.getFeatureName());

        return values;
    }

    private ContentValues getSSValues(final Object ivo) {
        ContentValues values = new ContentValues();
        //SearchSuggestionVO vo = (SearchSuggestionVO) ivo;
       // values.put(DBTablesDef.C_SEARCH_SUGGESTIONS_SEARCH_KEYWORD, vo.getSearchKeyword());
        //values.put(DBTablesDef.C_SEARCH_SUGGESTIONS_SEARCH_DATE_TIME, vo.getSearchDateTime());

        return values;
    }

    public Object getVO(String tableName,
            final Cursor cursor) {
        Object vo = null;
        if (DBTablesDef.T_SHOPPING_BAG.equalsIgnoreCase(tableName)) {
            //vo = getSBVO(cursor);
        } else if (DBTablesDef.T_SEARCH_PLACES.equalsIgnoreCase(tableName)) {
           // vo = getSPVO(cursor);
        } else if (DBTablesDef.T_SEARCH_SUGGESTIONS.equalsIgnoreCase(tableName)) {
            if (cursor.moveToFirst()) {
              //  vo = getSSVO(cursor);
            }
        }

        return vo;
    }

    public List<Object> getVOList(String tableName,
            final Cursor cursor) {
        List<Object> voList = null;
        if (DBTablesDef.T_SHOPPING_BAG.equalsIgnoreCase(tableName)) {
            voList = getSBVOList(cursor);
        } else if (DBTablesDef.T_SEARCH_PLACES.equalsIgnoreCase(tableName)) {
           // voList = getSPVOList(cursor);
        } else if (DBTablesDef.T_SEARCH_SUGGESTIONS.equalsIgnoreCase(tableName)) {
           // voList = getSSVOList(cursor);
        }

        return voList;
    }

    public ContentValues getContentValues(String tableName,
            Object vo) {
        ContentValues values = null;
        if (DBTablesDef.T_SHOPPING_BAG.equalsIgnoreCase(tableName)) {
           // values = getSBValues(vo);
        } else if (DBTablesDef.T_SEARCH_PLACES.equalsIgnoreCase(tableName)) {
            values = getSPValues(vo);
        } else if (DBTablesDef.T_SEARCH_SUGGESTIONS.equalsIgnoreCase(tableName)) {
            values = getSSValues(vo);
        }
        return values;
    }
}
