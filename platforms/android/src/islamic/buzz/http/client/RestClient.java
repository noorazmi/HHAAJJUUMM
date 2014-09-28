
package islamic.buzz.http.client;

import islamic.buzz.util.ConstantValues;
import islamic.buzz.util.Logger;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public final class RestClient {
    private static final String TAG = RestClient.class.getSimpleName();

    /**
     * @param apiName - End URL / API Name from AppConstants.java. If end Url is
     *            set - newUrl boolean should be set to true
     * @param params - Request Parameter mapping
     * @param isAppend - Whether header needs channel paramter
     * @param newUrl - Please refer to doc for apiName
     * @param extraParam - Non String paramter added directly.
     * @param isSignatureReqd - Does the API call need 'signature' paramter.
     * @return
     */
    public static final String getHttpGetApiResponseAsJSON(String apiName,
            Map<String, String> params,
            boolean isAppend,
            boolean newUrl,
            String extraParam,
            boolean isSignatureReqd) {
        String response = "";
        String urlValue = null;

        if (isAppend) {
            if (newUrl) {
                urlValue = apiName + ConstantValues.HEADER_CHANNEL;
            } else {
                urlValue = ConstantValues.URL + apiName + ConstantValues.HEADER_CHANNEL;
            }
        } else {
            if (newUrl) {
                urlValue = apiName + "?" + prepareParamString(params, extraParam);
            } else {
                urlValue = ConstantValues.URL + apiName
                        + "?"
                        + prepareParamString(params, extraParam);
            }

        }

//        if (isSignatureReqd) {
//            if (urlValue != null) {
//                urlValue = urlValue + "&"
//                        + ConstantValues.SIGNATURE_PARAMETER
//                        + "="
//                        + UrlSigner.generateKey(urlValue);
//            }
//        }
        Logger.debug(TAG, "URL is : " + urlValue);

        URL url = null;
        InputStream urlInputStream = null;
        HttpURLConnection urlConnection = null;
        try {
            url = new URL(urlValue);

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestProperty(ConstantValues.HEADER_ACCEPT,
                    ConstantValues.ACCEPT_JSON);
            //urlConnection.setRequestProperty(ConstantValues.HEADER_API_KEY, ConstantValues.API_KEY);
            // urlConnection.setRequestProperty(AppConstants.HEADER_CHANNEL,
            // AppConstants.VALUE_MOBILE);

            urlInputStream = urlConnection.getInputStream();
            if (urlInputStream != null) {
                response = readStream(urlInputStream);
            }
        } catch (MalformedURLException e) {
            Logger.error(TAG, "MalformedURLException: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            Logger.error(TAG, "IOException: " + e.getMessage());
            e.printStackTrace();
            urlInputStream = urlConnection.getErrorStream();
            if (urlInputStream != null) {
                response = readStream(urlInputStream);
            }
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
                urlInputStream = null;
            }

        }

        Logger.debug(TAG, "Response is: " + response);

        return response;
    }

    public static final String getHttpPostApiResponseAsJSON(String apiName,
            Map<String, String> params,
            String urlParameters,
            boolean isAppend) {
        String response = "";
        String urlValue = null;
        if (isAppend)
            urlValue = ConstantValues.URL + apiName + ConstantValues.HEADER_CHANNEL;
        else
            urlValue = ConstantValues.URL + apiName + "?" + prepareParamString(params, null);

        Logger.debug(TAG, "URL is : " + urlValue);

        URL url = null;
        InputStream urlInputStream = null;
        HttpURLConnection urlConnection = null;
        try {
            url = new URL(urlValue);

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestProperty(ConstantValues.HEADER_ACCEPT,
                    ConstantValues.ACCEPT_JSON);
            //urlConnection.setRequestProperty(ConstantValues.HEADER_API_KEY, ConstantValues.API_KEY);

            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            urlConnection.setRequestProperty("Content-Length",
                    "" + Integer.toString(urlParameters.getBytes().length));
            urlConnection.setRequestProperty("Content-Language", "en-US");

            urlConnection.setUseCaches(false);
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);

            // Send request
            DataOutputStream wr = new DataOutputStream(urlConnection.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();

            urlInputStream = urlConnection.getInputStream();
            if (urlInputStream != null) {
                response = readStream(urlInputStream);
            }
        } catch (MalformedURLException e) {
            Logger.error(TAG, "MalformedURLException: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            Logger.error(TAG, "IOException: " + e.getMessage());
            e.printStackTrace();
            urlInputStream = urlConnection.getErrorStream();
            if (urlInputStream != null) {
                response = readStream(urlInputStream);
            }
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
                urlInputStream = null;
            }

        }

        Logger.debug(TAG, "Response is: " + response);

        return response;
    }

    private static String readStream(InputStream in) {
        BufferedReader reader = null;
        StringBuilder builder = new StringBuilder();
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while (reader != null && (line = reader.readLine()) != null) {
                builder.append(line);
            }
        } catch (IOException e) {
            Logger.error(TAG, "Error while processing input stream: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return builder.toString();
    }

    public static String prepareParamString(Map<String, String> params,
            String extraParam) {
        String paramString = "";
        if (params != null && !params.isEmpty()) {
            Set<String> keys = params.keySet();
            Iterator<String> it = keys.iterator();
            String keyValue = "";
            while (it.hasNext()) {
                keyValue = it.next();
                String value = params.get(keyValue).replaceAll(" ", "%20");
                paramString += keyValue + "=" + value + "&";
            }

            if (!keys.isEmpty()) {
                paramString = paramString.substring(0, paramString.length() - 1);
            }
        }

        if (extraParam != null) {
            paramString = paramString + "&" + extraParam;
        }

        return paramString;
    }
}
