
package islamic.buzz.vo;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.List;

public class GeoCoderVO implements IValueObject, Serializable {
    private static final long serialVersionUID = 1L;

    @Expose
    private List<GeoCodeResult> results;

    public List<GeoCodeResult> getResults() {
        return results;
    }

    public void setResults(List<GeoCodeResult> results) {
        this.results = results;
    }

    public class GeoCodeResult implements Serializable {

        /**
         * 
         */
        private static final long serialVersionUID = 2L;

        @Expose
        private List<AddressComponent> address_components;

        public List<AddressComponent> getAddress_components() {
            return address_components;
        }

        public void setAddress_components(List<AddressComponent> address_components) {
            this.address_components = address_components;
        }

        @Expose
        private String formatted_address;

        public String getFormatted_address() {
            return formatted_address;
        }

        public void setFormatted_address(String formatted_address) {
            this.formatted_address = formatted_address;
        }

        @Expose
        private Geometry geometry;

        public Geometry getGeometry() {
            return geometry;
        }

        public void setGeometry(Geometry geometry) {
            this.geometry = geometry;
        }

        public class Geometry implements Serializable {

            /**
             * 
             */
            private static final long serialVersionUID = 4L;

            @Expose
            private GeoLocation location;

            public GeoLocation getLocation() {
                return location;
            }

            public void setLocation(GeoLocation location) {
                this.location = location;
            }

            public class GeoLocation implements Serializable {

                /**
                 * 
                 */
                private static final long serialVersionUID = 5L;

                @Expose
                private Double lat;

                public Double getLat() {
                    return lat;
                }

                public void setLat(Double lat) {
                    this.lat = lat;
                }

                @Expose
                private Double lng;

                public Double getLon() {
                    return lng;
                }

                public void setLon(Double lon) {
                    this.lng = lon;
                }

            }

        }

        public class AddressComponent implements Serializable {

            /**
             * 
             */
            private static final long serialVersionUID = 3L;

            @Expose
            private String long_name;

            public String getLong_name() {
                return long_name;
            }

            public void setLong_name(String long_name) {
                this.long_name = long_name;
            }

            @Expose
            private String short_name;

            public String getShort_name() {
                return short_name;
            }

            public void setShort_name(String short_name) {
                this.short_name = short_name;
            }

            @Expose
            private List<String> types;

            public List<String> getTypes() {
                return types;
            }

            public void setTypes(List<String> types) {
                this.types = types;
            }

        }
    }

}
