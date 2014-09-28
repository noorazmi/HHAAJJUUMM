package islamic.buzz.util;

import islamic.buzz.app.BuzzApplication;
import islamic.buzz.vo.ConfigurationVO;
import android.text.TextUtils;

public class ConfigurationUtils {

	private ConfigurationVO.PayLoad.Config mConfig = null;

	public void initAppConfig() {
		String configString = BuzzApplication.getInstance().getAppPref()
				.getAppConfiguration();

		if (configString == null)
			configString = UtilityMethods.loadJSONFromAsset(
					BuzzApplication.getContext(),
					ConstantValues.APP_CONFIG_FILE);
		ConfigurationVO configurationVO = (ConfigurationVO) UtilityMethods
				.getModelFromJsonString(configString, ConfigurationVO.class);

		if (configurationVO.getPayload() != null
				&& configurationVO.getPayload().getConfig() != null) {
			mConfig = configurationVO.getPayload().getConfig();
			setConficurationProp();
		}
	}

	public void setConficurationProp() {
		setAppProperties();
	}

	private void setAppProperties() {
		CommonValues commonValues = CommonValues.getInstance();
		if (!TextUtils.isEmpty(mConfig.getIdleTimeout())) {
			commonValues.setIdleTimeOut(Integer.parseInt(mConfig
					.getIdleTimeout()));
		}
	}

}
