
package islamic.buzz.activities;

import com.kohlsphone.R;
import com.kohlsphone.common.util.UtilityMethods;
import com.kohlsphone.common.value.ConstantValues;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewActivity extends BaseActivityWithoutSlider {
    private WebView mWebView;

    private String mUrl;

    @Override
    protected void initializeViews(Bundle bundle) {
        mWebView = (WebView) findViewById(R.id.id_webView_webView);
        setWebViewSettings();
        attachActionItemListener();
        loadBundledData(bundle);
        if (mUrl != null) {
            loadMyUrl(bundle.getString(ConstantValues.EXTRA_KEY_URL));
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.web_view_activity;
    }

    @Override
    protected void updateActionBarViews() {
        getActionBarHelper().showActionBarForWebViewActivity();

    }

    /**
     * Attach action item click listener
     */
    private void attachActionItemListener() {
        getActionBarHelper().getItemView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UtilityMethods.openShoppingBagActivity(WebViewActivity.this);
            }
        });
    }

    @Override
    public void updateViewsOnSuccess(Object object) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateViewsOnFailure(Object object) {
        // TODO Auto-generated method stub

    }

    private void setWebViewSettings() {
        mWebView.getSettings().setLoadsImagesAutomatically(true);
        mWebView.getSettings().setJavaScriptEnabled(true);
        // Foce URL to open in this webview only
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedError(WebView view,
                    int errorCode,
                    String description,
                    String failingUrl) {
                // Handle the error
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view,
                    String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }

    private void loadMyUrl(String url) {
        if (mWebView != null) {
            mWebView.loadUrl(url);
        }
    }

    private void loadBundledData(Bundle bundle) {
        if (bundle != null) {
            mUrl = bundle.getString(ConstantValues.EXTRA_KEY_URL);
        }
    }

}
