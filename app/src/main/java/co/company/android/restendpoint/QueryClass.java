package co.company.android.restendpoint;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

class QueryTask extends AsyncTask<String, Integer, String> {
    private View rootView;

    public QueryTask(Context context, View rootView) {
        this.rootView = rootView;
    }

    @Override
    protected String doInBackground(String... params) {

        URL endpoint = null;
        try {
            endpoint = new URL("https://jsonplaceholder.typicode.com/comments");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            HttpsURLConnection endpointConnection =
                    (HttpsURLConnection) endpoint.openConnection();
            endpointConnection.setRequestProperty("Accept", "application/json");

            if (endpointConnection.getResponseCode() == 200) {
                return parseResponse(endpointConnection);
            } else {
                //error connecting
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "All Done!";
    }

    private String parseResponse(HttpsURLConnection endpointConnection) throws IOException{
        InputStream responseBody = endpointConnection.getInputStream();

        BufferedReader reader = new BufferedReader(new InputStreamReader(responseBody));
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            //error reading response
        } finally {
            try {
                responseBody.close();
            } catch (IOException e) {
                //error closing connection
            }
        }
        return sb.toString();
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        TextView txt = (TextView) rootView.findViewById(R.id.my_textarea);
        txt.setText(result);
    }
}