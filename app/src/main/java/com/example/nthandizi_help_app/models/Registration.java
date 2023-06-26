package com.example.nthandizi_help_app.models;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

public class Registration {
    public static void register(String fname, String lname, String pnumber, String email, String password) {
        new RegistrationTask(fname, lname, pnumber, email, password).execute();
    }

    private static class RegistrationTask extends AsyncTask<Void, Void, Void> {
        private String fname;
        private String lname;
        private String pnumber;
        private String email;
        private String password;

        public RegistrationTask(String fname, String lname, String pnumber, String email, String password) {
            this.fname = fname;
            this.lname = lname;
            this.pnumber = pnumber;
            this.email = email;
            this.password = password;
        }

        @Override
        protected Void doInBackground(Void... params) {
            String apiURL = "http://10.0.2.2:8000/api/v1/User/register/";

            try {
                // Generate OTP
                String otp = generateOTP();

                URL url = new URL(apiURL);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");

                JSONObject requestBody = new JSONObject();
                requestBody.put("fname", fname);
                requestBody.put("lname", lname);
                requestBody.put("pnumber", pnumber);
                requestBody.put("email", email);
                requestBody.put("password", password);
                requestBody.put("otp", otp);
                requestBody.put("is_active", 1);

                // Send the JSON request
                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
                wr.write(requestBody.toString());
                wr.flush();
                wr.close();

                int responseCode = conn.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_CREATED) {
                    // Registration successful
                    Log.d("Registration", "Registration successful!" + responseCode);

                } else {
                    // Registration failed
                    Log.d("Registration", "Registration failed. Response code: " + responseCode);
                    // Handle registration failure here
                }

                conn.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
                // Handle exception here
            }

            return null;
        }

        private String generateOTP() {
            Random random = new Random();
            int otpDigits = 4; // Number of OTP digits

            StringBuilder otp = new StringBuilder();
            for (int i = 0; i < otpDigits; i++) {
                int digit = random.nextInt(10); // Generate a random digit (0-9)
                otp.append(digit);
            }

            return otp.toString();
        }
    }
}

