
package islamic.buzz.vo;

public class ConfigurationVO implements IValueObject {

    private static ConfigurationVO sInstance;

    public static ConfigurationVO getInstance() {
        if (sInstance == null)
            sInstance = new ConfigurationVO();
        return sInstance;
    }

    public String getResponseID() {
        return responseID;
    }

    public void setResponseID(String responseID) {
        this.responseID = responseID;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public void setSuccessful(boolean isSuccessful) {
        this.isSuccessful = isSuccessful;
    }

    public PayLoad getPayload() {
        return payload;
    }

    public void setPayload(PayLoad payload) {
        this.payload = payload;
    }

    private String responseID;

    private boolean isSuccessful;

    private PayLoad payload;

    public class PayLoad {

        private Config config;

        public class Config {

            public String getAppVersion() {
                return appVersion;
            }

            public void setAppVersion(String appVersion) {
                this.appVersion = appVersion;
            }

            public String getConfigFileVersion() {
                return configFileVersion;
            }

            public void setConfigFileVersion(String configFileVersion) {
                this.configFileVersion = configFileVersion;
            }

            public String getHeaderApikey() {
                return headerApikey;
            }

            public void setHeaderApikey(String headerApikey) {
                this.headerApikey = headerApikey;
            }

            public String getLoggingMode() {
                return loggingMode;
            }

            public void setLoggingMode(String loggingMode) {
                this.loggingMode = loggingMode;
            }

            public String getStoreRadius() {
                return storeRadius;
            }

            public void setStoreRadius(String storeRadius) {
                this.storeRadius = storeRadius;
            }

            public String getZipCode() {
                return zipCode;
            }

            public void setZipCode(String zipCode) {
                this.zipCode = zipCode;
            }

            public String getWeeklyAdURL() {
                return weeklyAdURL;
            }

            public void setWeeklyAdURL(String weeklyAdURL) {
                this.weeklyAdURL = weeklyAdURL;
            }

            public String getEulaUrl() {
                return eulaUrl;
            }

            public void setEulaUrl(String eulaUrl) {
                this.eulaUrl = eulaUrl;
            }

            public String getClient() {
                return client;
            }

            public void setClient(String client) {
                this.client = client;
            }

            public String getSecret() {
                return secret;
            }

            public void setSecret(String secret) {
                this.secret = secret;
            }

            public String getPassphrase() {
                return passphrase;
            }

            public void setPassphrase(String passphrase) {
                this.passphrase = passphrase;
            }

            public String getPercentAvailableSpaceUsed() {
                return PercentAvailableSpaceUsed;
            }

            public void setPercentAvailableSpaceUsed(String percentAvailableSpaceUsed) {
                PercentAvailableSpaceUsed = percentAvailableSpaceUsed;
            }

            public String getPDPTTLOverrideTime() {
                return PDPTTLOverrideTime;
            }

            public void setPDPTTLOverrideTime(String pDPTTLOverrideTime) {
                PDPTTLOverrideTime = pDPTTLOverrideTime;
            }

            public String getTypeaheadUrl() {
                return TypeaheadUrl;
            }

            public void setTypeaheadUrl(String typeaheadUrl) {
                TypeaheadUrl = typeaheadUrl;
            }

            public String getTodaysDealsUrl() {
                return TodaysDealsUrl;
            }

            public void setTodaysDealsUrl(String todaysDealsUrl) {
                TodaysDealsUrl = todaysDealsUrl;
            }

            public String getGIftCardCNNumber() {
                return GIftCardCNNumber;
            }

            public void setGIftCardCNNumber(String gIftCardCNNumber) {
                GIftCardCNNumber = gIftCardCNNumber;
            }

            public String getCreateLoyaltyProfile() {
                return CreateLoyaltyProfile;
            }

            public void setCreateLoyaltyProfile(String createLoyaltyProfile) {
                CreateLoyaltyProfile = createLoyaltyProfile;
            }

            public String getWalletandLoyaltyBaseURL() {
                return WalletandLoyaltyBaseURL;
            }

            public void setWalletandLoyaltyBaseURL(String walletandLoyaltyBaseURL) {
                WalletandLoyaltyBaseURL = walletandLoyaltyBaseURL;
            }

            public String getWalletVideoURL() {
                return WalletVideoURL;
            }

            public void setWalletVideoURL(String walletVideoURL) {
                WalletVideoURL = walletVideoURL;
            }

            public String getLoyaltyEnrollmentScanningUrl() {
                return LoyaltyEnrollmentScanningUrl;
            }

            public void setLoyaltyEnrollmentScanningUrl(String loyaltyEnrollmentScanningUrl) {
                LoyaltyEnrollmentScanningUrl = loyaltyEnrollmentScanningUrl;
            }

            public String getLoyaltySignupPoints() {
                return loyaltySignupPoints;
            }

            public void setLoyaltySignupPoints(String loyaltySignupPoints) {
                this.loyaltySignupPoints = loyaltySignupPoints;
            }

            public String getNetworktimeout() {
                return networktimeout;
            }

            public void setNetworktimeout(String networktimeout) {
                this.networktimeout = networktimeout;
            }

            public String getIdleTimeout() {
                return idleTimeout;
            }

            public void setIdleTimeout(String idleTimeout) {
                this.idleTimeout = idleTimeout;
            }

            public String getSigninTimeout() {
                return signinTimeout;
            }

            public void setSigninTimeout(String signinTimeout) {
                this.signinTimeout = signinTimeout;
            }

            public String getWalletTimeout() {
                return walletTimeout;
            }

            public void setWalletTimeout(String walletTimeout) {
                this.walletTimeout = walletTimeout;
            }

            public String getAddToListUrl() {
                return AddToListUrl;
            }

            public void setAddToListUrl(String addToListUrl) {
                AddToListUrl = addToListUrl;
            }

            public String getShowListItemsUrl() {
                return ShowListItemsUrl;
            }

            public void setShowListItemsUrl(String showListItemsUrl) {
                ShowListItemsUrl = showListItemsUrl;
            }

            public String getAddToRegistryUrl() {
                return AddToRegistryUrl;
            }

            public void setAddToRegistryUrl(String addToRegistryUrl) {
                AddToRegistryUrl = addToRegistryUrl;
            }

            public String getShowRegistryItemsUrl() {
                return ShowRegistryItemsUrl;
            }

            public void setShowRegistryItemsUrl(String showRegistryItemsUrl) {
                ShowRegistryItemsUrl = showRegistryItemsUrl;
            }

            public String getNewPaymentPreference() {
                return NewPaymentPreference;
            }

            public void setNewPaymentPreference(String newPaymentPreference) {
                NewPaymentPreference = newPaymentPreference;
            }

            public String getKohlsChargeSignInUrl() {
                return kohlsChargeSignInUrl;
            }

            public void setKohlsChargeSignInUrl(String kohlsChargeSignInUrl) {
                this.kohlsChargeSignInUrl = kohlsChargeSignInUrl;
            }

            public String getKohlsChargeApplyNowUrl() {
                return kohlsChargeApplyNowUrl;
            }

            public void setKohlsChargeApplyNowUrl(String kohlsChargeApplyNowUrl) {
                this.kohlsChargeApplyNowUrl = kohlsChargeApplyNowUrl;
            }

            public String getDefaultShippingMethod() {
                return defaultShippingMethod;
            }

            public void setDefaultShippingMethod(String defaultShippingMethod) {
                this.defaultShippingMethod = defaultShippingMethod;
            }

            public String isForceUpgrade() {
                return forceUpgrade;
            }

            public void setForceUpgrade(String forceUpgrade) {
                this.forceUpgrade = forceUpgrade;
            }

            public String getForceUpgradeDisplayMessage() {
                return forceUpgradeDisplayMessage;
            }

            public void setForceUpgradeDisplayMessage(String forceUpgradeDisplayMessage) {
                this.forceUpgradeDisplayMessage = forceUpgradeDisplayMessage;
            }

            public String getForceUpgradeTitle() {
                return forceUpgradeTitle;
            }

            public void setForceUpgradeTitle(String forceUpgradeTitle) {
                this.forceUpgradeTitle = forceUpgradeTitle;
            }

            public String getForceupgradereferenceurl() {
                return forceupgradereferenceurl;
            }

            public void setForceupgradereferenceurl(String forceupgradereferenceurl) {
                this.forceupgradereferenceurl = forceupgradereferenceurl;
            }

            public String getBazaarVoiceApiKey() {
                return bazaarVoiceApiKey;
            }

            public void setBazaarVoiceApiKey(String bazaarVoiceApiKey) {
                this.bazaarVoiceApiKey = bazaarVoiceApiKey;
            }

            public String getBazaarVoiceApiVersion() {
                return bazaarVoiceApiVersion;
            }

            public void setBazaarVoiceApiVersion(String bazaarVoiceApiVersion) {
                this.bazaarVoiceApiVersion = bazaarVoiceApiVersion;
            }

            public String getBazaarVoiceURL() {
                return bazaarVoiceURL;
            }

            public void setBazaarVoiceURL(String bazaarVoiceURL) {
                this.bazaarVoiceURL = bazaarVoiceURL;
            }

            public String getForseeFeedbackURL() {
                return forseeFeedbackURL;
            }

            public void setForseeFeedbackURL(String forseeFeedbackURL) {
                this.forseeFeedbackURL = forseeFeedbackURL;
            }

            public String getForsee_DefaultDaysSinceFirstLaunch() {
                return forsee_DefaultDaysSinceFirstLaunch;
            }

            public void
                    setForsee_DefaultDaysSinceFirstLaunch(String forsee_DefaultDaysSinceFirstLaunch) {
                this.forsee_DefaultDaysSinceFirstLaunch = forsee_DefaultDaysSinceFirstLaunch;
            }

            public String getForsee_DefaultLaunchCount() {
                return forsee_DefaultLaunchCount;
            }

            public void setForsee_DefaultLaunchCount(String forsee_DefaultLaunchCount) {
                this.forsee_DefaultLaunchCount = forsee_DefaultLaunchCount;
            }

            public String getForsee_DefaultRepeatDaysAfterComplete() {
                return forsee_DefaultRepeatDaysAfterComplete;
            }

            public void
                    setForsee_DefaultRepeatDaysAfterComplete(String forsee_DefaultRepeatDaysAfterComplete) {
                this.forsee_DefaultRepeatDaysAfterComplete = forsee_DefaultRepeatDaysAfterComplete;
            }

            public String getForsee_DefaultRepeatDaysAfterDecline() {
                return forsee_DefaultRepeatDaysAfterDecline;
            }

            public void
                    setForsee_DefaultRepeatDaysAfterDecline(String forsee_DefaultRepeatDaysAfterDecline) {
                this.forsee_DefaultRepeatDaysAfterDecline = forsee_DefaultRepeatDaysAfterDecline;
            }

            public String isForseeEnabled() {
                return isForseeEnabled;
            }

            public void setForseeEnabled(String isForseeEnabled) {
                this.isForseeEnabled = isForseeEnabled;
            }

            public String isDigbyURL() {
                return digbyURL;
            }

            public void setDigbyURL(String digbyURL) {
                this.digbyURL = digbyURL;
            }

            public String isOmnitureEnabled() {
                return isOmnitureEnabled;
            }

            public void setOmnitureEnabled(String isOmnitureEnabled) {
                this.isOmnitureEnabled = isOmnitureEnabled;
            }

            public String getOmnitureURL() {
                return omnitureURL;
            }

            public void setOmnitureURL(String omnitureURL) {
                this.omnitureURL = omnitureURL;
            }

            public String getOmnitureAPIKey() {
                return omnitureAPIKey;
            }

            public void setOmnitureAPIKey(String omnitureAPIKey) {
                this.omnitureAPIKey = omnitureAPIKey;
            }

            public String getTIntSocialFeedUrl() {
                return TIntSocialFeedUrl;
            }

            public void setTIntSocialFeedUrl(String tIntSocialFeedUrl) {
                TIntSocialFeedUrl = tIntSocialFeedUrl;
            }

            public String getRedLaserURL() {
                return redLaserURL;
            }

            public void setRedLaserURL(String redLaserURL) {
                this.redLaserURL = redLaserURL;
            }

            public String getCrashlyticsURL() {
                return crashlyticsURL;
            }

            public void setCrashlyticsURL(String crashlyticsURL) {
                this.crashlyticsURL = crashlyticsURL;
            }

            public String getAppDisplayName() {
                return appDisplayName;
            }

            public void setAppDisplayName(String appDisplayName) {
                this.appDisplayName = appDisplayName;
            }

            public String getAppMode() {
                return appMode;
            }

            public void setAppMode(String appMode) {
                this.appMode = appMode;
            }

            public String getFacebookURL() {
                return facebookURL;
            }

            public void setFacebookURL(String facebookURL) {
                this.facebookURL = facebookURL;
            }

            public String getFacebook_page() {
                return facebook_page;
            }

            public void setFacebook_page(String facebook_page) {
                this.facebook_page = facebook_page;
            }

            public String getTwitterURL() {
                return twitterURL;
            }

            public void setTwitterURL(String twitterURL) {
                this.twitterURL = twitterURL;
            }

            public String getTwitter_page() {
                return twitter_page;
            }

            public void setTwitter_page(String twitter_page) {
                this.twitter_page = twitter_page;
            }

            public String getGoogleMapsURL() {
                return googleMapsURL;
            }

            public void setGoogleMapsURL(String googleMapsURL) {
                this.googleMapsURL = googleMapsURL;
            }

            public String isRegistryEnabled() {
                return isRegistryEnabled;
            }

            public void setRegistryEnabled(String isRegistryEnabled) {
                this.isRegistryEnabled = isRegistryEnabled;
            }

            public String getIsScanningEnabled() {
                return isScanningEnabled;
            }

            public void setIsScanningEnabled(String isScanningEnabled) {
                this.isScanningEnabled = isScanningEnabled;
            }

            public String getPMPCacheSessions() {
                return PMPCacheSessions;
            }

            public void setPMPCacheSessions(String pMPCacheSessions) {
                PMPCacheSessions = pMPCacheSessions;
            }

            public String getCacheSize() {
                return cacheSize;
            }

            public void setCacheSize(String cacheSize) {
                this.cacheSize = cacheSize;
            }

            public String getSignInRefreshInterval() {
                return signInRefreshInterval;
            }

            public void setSignInRefreshInterval(String signInRefreshInterval) {
                this.signInRefreshInterval = signInRefreshInterval;
            }

            public String getCmsURL() {
                return cmsURL;
            }

            public void setCmsURL(String cmsURL) {
                this.cmsURL = cmsURL;
            }

            public String getIsBugsense() {
                return isBugsense;
            }

            public void setIsBugsense(String isBugsense) {
                this.isBugsense = isBugsense;
            }

            public String getBugsenseAPIKeyIos() {
                return BugsenseAPIKeyIos;
            }

            public void setBugsenseAPIKeyIos(String bugsenseAPIKeyIos) {
                BugsenseAPIKeyIos = bugsenseAPIKeyIos;
            }

            public String getBugsenseAPIKeyHybrid() {
                return BugsenseAPIKeyHybrid;
            }

            public void setBugsenseAPIKeyHybrid(String bugsenseAPIKeyHybrid) {
                BugsenseAPIKeyHybrid = bugsenseAPIKeyHybrid;
            }

            public String getBugsenseAPIKeyAndroid() {
                return BugsenseAPIKeyAndroid;
            }

            public void setBugsenseAPIKeyAndroid(String bugsenseAPIKeyAndroid) {
                BugsenseAPIKeyAndroid = bugsenseAPIKeyAndroid;
            }

            private String appVersion;

            private String configFileVersion;

            private String headerApikey;

            private String loggingMode;

            private String storeRadius;

            private String zipCode;

            private String weeklyAdURL;

            private String eulaUrl;

            private String client;

            private String secret;

            private String passphrase;

            private String PercentAvailableSpaceUsed;

            private String PDPTTLOverrideTime;

            private String isBugsense;

            private String TypeaheadUrl;

            private String TodaysDealsUrl;

            private String GIftCardCNNumber;

            private String CreateLoyaltyProfile;

            private String WalletandLoyaltyBaseURL;

            private String WalletVideoURL;

            private String LoyaltyEnrollmentScanningUrl;

            private String loyaltySignupPoints;

            private String networktimeout;

            private String idleTimeout;

            private String signinTimeout;

            private String walletTimeout;

            private String AddToListUrl;

            private String ShowListItemsUrl;

            private String AddToRegistryUrl;

            private String ShowRegistryItemsUrl;

            private String NewPaymentPreference;

            private String kohlsChargeSignInUrl;

            private String kohlsChargeApplyNowUrl;

            private String defaultShippingMethod;

            private String forceUpgrade;

            private String forceUpgradeDisplayMessage;

            private String forceUpgradeTitle;

            private String forceupgradereferenceurl;

            private String bazaarVoiceApiKey;

            private String bazaarVoiceApiVersion;

            private String bazaarVoiceURL;

            private String forseeFeedbackURL;

            private String forsee_DefaultDaysSinceFirstLaunch;

            private String forsee_DefaultLaunchCount;

            private String forsee_DefaultRepeatDaysAfterComplete;

            private String forsee_DefaultRepeatDaysAfterDecline;

            private String isForseeEnabled;

            private String digbyURL;

            private String isOmnitureEnabled;

            private String omnitureURL;

            private String omnitureAPIKey;

            private String TIntSocialFeedUrl;

            private String redLaserURL;

            private String crashlyticsURL;

            private String appDisplayName;

            private String appMode;

            private String facebookURL;

            private String facebook_page;

            private String twitterURL;

            private String twitter_page;

            private String googleMapsURL;

            private String isRegistryEnabled;

            private String isScanningEnabled;

            private String PMPCacheSessions;

            private String cacheSize;

            private String signInRefreshInterval;

            private String cmsURL;

            private String BugsenseAPIKeyIos;

            private String BugsenseAPIKeyHybrid;

            private String BugsenseAPIKeyAndroid;
        }

        public Config getConfig() {
            return config;
        }

        public void setConfig(Config config) {
            this.config = config;
        }
    }
}
