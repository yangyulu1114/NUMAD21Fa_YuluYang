package edu.neu.madcourse.NUMAD21Fa_YuluYang;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpUtils {
    private static final ExecutorService sExecutor = Executors.newSingleThreadExecutor();


    public static void sendGetRequest(String url, HttpCallback callback) {
        Log.v("bush", url);
        sExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    callback.onSuccess(sendGetRequest(url));
                } catch (Exception e) {
                    callback.onFail(e);
                }
            }
        });
    }

    private static String sendGetRequest(String urlString) throws Exception {
        HttpURLConnection httpConn = null;
        BufferedReader bufferedReader = null;
        try {
            URL url = new URL(urlString);
            httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setRequestMethod("GET");
            bufferedReader = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (httpConn != null) {
                httpConn.disconnect();
            }
        }
    }

}
