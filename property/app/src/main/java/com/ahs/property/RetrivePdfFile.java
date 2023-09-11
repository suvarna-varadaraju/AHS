package com.ahs.property;

import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class RetrivePdfFile extends AsyncTask<String, Void, InputStream> {
    public static InputStream inputStream1;
    @Override
    protected InputStream doInBackground(String... strings) {
        // we are using inputstream
        // for getting out PDF.
        InputStream inputStream = null;
        try {
            URL url = new URL(strings[0]);
            // below is the step where we are
            // creating our connection.
            HttpURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            if (urlConnection.getResponseCode() == 200) {
                // response is success.
                // we are getting input stream from url
                // and storing it in our variable.
                inputStream = new BufferedInputStream(urlConnection.getInputStream());
            }

        } catch (IOException e) {
            // this is the method
            // to handle errors.
            e.printStackTrace();
            return null;
        }
        return inputStream;
    }

    @Override
    protected void onPostExecute(InputStream inputStream) {
        inputStream1 = inputStream;

    }
}
