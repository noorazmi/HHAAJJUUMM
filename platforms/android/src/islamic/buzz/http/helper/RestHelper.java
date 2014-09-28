
package islamic.buzz.http.helper;


import android.os.AsyncTask;
import android.util.Log;
import islamic.buzz.error.AppException;
import islamic.buzz.interfaces.listeners.IRestListener;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.WeakHashMap;

/**
 * This class is responsible for executing Rest Calls
 */
public class RestHelper {
    private static final String TAG = RestHelper.class.getSimpleName();

    public static enum Method {
        GET, POST, PUT
    };

    private IRestListener listener = null;

    private Method method;

    private String urlValue;

    private String requestParameterOrBody;

    private WeakHashMap<String, String> headers;

    public RestHelper(IRestListener listener, Method method, final String urlValue,
            String requestParameterOrBody, WeakHashMap<String, String> headers) {
        this.listener = listener;
        this.method = method;
        this.urlValue = urlValue;
        this.requestParameterOrBody = requestParameterOrBody;
        this.headers = headers;
    }

    public void performTask() {
        task.execute();
    }

    private AsyncTask<Void, Void, String> task = new AsyncTask<Void, Void, String>() {

        @Override
        protected void onPostExecute(String result) {

            if (result != null) {
                listener.onSuccess(result);
            } else {
                listener.onFailure(new Error(new AppException(
                        "Exception while executing request")));
            }
            super.onPostExecute(result);
        }

        @Override
        protected String doInBackground(Void... params) {
            String result = performRequestOperation();
            return result;
        }

    };

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
            Log.e(TAG, "Error while processing input stream: " + e.getMessage());
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

    private String performRequestOperation() {
        String response = null;
        String newUrlValue = null;

        if (Method.GET == method && requestParameterOrBody != null) {
            newUrlValue = urlValue + "?" + requestParameterOrBody;
        } else {
            newUrlValue = urlValue;
        }

        URL url = null;
        InputStream urlInputStream = null;
        HttpURLConnection urlConnection = null;
        try {
            url = new URL(newUrlValue);

            urlConnection = (HttpURLConnection)url.openConnection();

            // Setting Method
            urlConnection.setRequestMethod(method.name());
            if (headers != null) {
                Iterator<String> itHeaders = headers.keySet().iterator();
                String headerName = null;
                String headerValue = null;

                // Setting Headers
                while (itHeaders.hasNext()) {
                    headerName = itHeaders.next();
                    headerValue = headers.get(headerName);
                    urlConnection.setRequestProperty(headerName, headerValue);
                }
            }
            // Adding object in Post Body
            if (Method.POST == method) {
                DataOutputStream outputStream = new DataOutputStream(
                        urlConnection.getOutputStream());
                if (requestParameterOrBody != null) {
                    outputStream.writeBytes(requestParameterOrBody);
                }
                outputStream.flush();
                outputStream.close();
            }

            urlInputStream = urlConnection.getInputStream();
            if (urlInputStream != null) {
                response = readStream(urlInputStream);
            }
        } catch (MalformedURLException e) {
            Log.e(TAG, "MalformedURLException: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            Log.e(TAG, "IOException: " + e.getMessage());
            // e.printStackTrace();
            urlInputStream = urlConnection.getErrorStream();
            if (urlInputStream != null) {
                response = readStream(urlInputStream);
            }
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e.getMessage());
        } finally {

            if (urlConnection != null) {
                urlConnection.disconnect();
                urlInputStream = null;
            }
        }

        return response;
    }
}
