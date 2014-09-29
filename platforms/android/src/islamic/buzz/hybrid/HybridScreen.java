
package islamic.buzz.hybrid;

public enum HybridScreen {
    NAMES_OF_ALLAH("99 Names Of Allah", "", "/namesOfAllah");
    

    private String mscreenTitle;

    private String mscreenUrlPrefix;

    private String mscreenUrl;


    private HybridScreen(String screenName,
            String screenUrlPrefix,
            String screenUrl) {
        this.mscreenTitle = screenName;
        this.mscreenUrlPrefix = screenUrlPrefix;
        this.mscreenUrl = screenUrl;
    }

    public String getScreenTitle() {
        return this.mscreenTitle;
    }

    public String getScreenUrlPrefix() {
        return this.mscreenUrlPrefix;
    }

    public String getScreenUrl() {
        return this.mscreenUrl;
    }

    public static HybridScreen getScreenFromUrl(String url) {
        HybridScreen screen = null;
        if (url != null) {
            int screenIdx = url.indexOf("#");
            if (screenIdx >= 0) {
                String postfix = url.substring(screenIdx + 1);
                screen = HybridScreen.getScreenFromStringUrl(postfix, false);
            }
        }
        return screen;
    }

    public static HybridScreen getScreenFromUrlPostfix(String urlPostfix) {
        return getScreenFromStringUrl(urlPostfix, true);
    }

    private static HybridScreen getScreenFromStringUrl(String value,
            boolean isPostFix) {
        HybridScreen screen = null;
        if (value != null) {
            HybridScreen[] allValues = HybridScreen.values();
            String valueToCompare = null;
            for (HybridScreen temp : allValues) {
                if (!isPostFix) {
                    valueToCompare = temp.getScreenUrlPrefix() + temp.getScreenUrl();
                } else {
                    valueToCompare = temp.getScreenUrl();
                }
                if (valueToCompare.equalsIgnoreCase(value)) {
                    allValues = null;
                    return temp;
                }
            }
        }
        return screen;
    }

    @Override
    public String toString() {
        return "screenTitle: " + mscreenTitle + ", screenUrl: " + mscreenUrl;
    }
}
