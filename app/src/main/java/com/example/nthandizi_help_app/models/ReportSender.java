package com.example.nthandizi_help_app.models;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class ReportSender {

    public static void sendReport(String message, int color) {
        new SendReportTask().execute(message, String.valueOf(color));
    }

    private static class SendReportTask extends AsyncTask<String, Void, Integer> {
        @Override
        protected Integer doInBackground(String... params) {
            String message = params[0];
            int color = Integer.parseInt(params[1]);

            String apiURL = "http://10.0.2.2:8000/api/v1/Cases/";
            int responseCode = -1;

            try {
                URL url = new URL(apiURL);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");

                JSONObject requestBody = new JSONObject();
                requestBody.put("message", message);
                requestBody.put("code", color);

                // Send the JSON request
                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
                wr.write(requestBody.toString());
                wr.flush();
                wr.close();

                responseCode = conn.getResponseCode();

                conn.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return responseCode;
        }

        @Override
        protected void onPostExecute(Integer responseCode) {
            if (responseCode == HttpURLConnection.HTTP_CREATED) {
                // Report sent successfully
                Log.d("ReportSender Status", "Successful! Response code: " + responseCode);
            } else {
                // Report sending failed
                Log.d("ReportSender Status", "Failed!. Response code: " + responseCode);
            }
        }
    }
}
