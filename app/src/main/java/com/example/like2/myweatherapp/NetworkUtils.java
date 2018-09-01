package com.example.like2.myweatherapp;

import android.net.Uri;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public final class NetworkUtils {
    private static final String Baseurl="https://andfun-weather.udacity.com/weather";
    public static void Fetchdata()
    {
        URL url=CreateURL();
        String jsonresponse;
        jsonresponse=MakeHttpsRequest(url);
        ExtractfromJson(jsonresponse);
    }
    private static URL CreateURL()
    {
        Uri uri=Uri.parse(Baseurl).buildUpon()
                .build();
        URL url=null;
        try {
            url= new URL(uri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }
    private static String MakeHttpsRequest(URL url)
    {
        String jsonResponse=null;
        InputStream stream=null;
        HttpsURLConnection connection=null;
        try {
            connection= (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(10000);
            connection.setConnectTimeout(15000);
            connection.connect();
            if(connection.getResponseCode()==200)
            {
                stream=connection.getInputStream();
                jsonResponse=ReadfromStream(stream);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (connection != null) {
                connection.disconnect();
            }
            try {
                if (stream != null) {
                    stream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return jsonResponse;

    }

    private static String ReadfromStream(InputStream stream) {
        StringBuilder stringBuilder=new StringBuilder();
        String line;
        InputStreamReader reader=new InputStreamReader(stream);
        BufferedReader bufferedReader=new BufferedReader(reader);
        try {
            line=bufferedReader.readLine();
            while (line!=null)
            {
                stringBuilder.append(line);
                line=bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    private static void ExtractfromJson(String response)
    {
        try {
            JSONObject root=new JSONObject(response);
            JSONObject city=root.optJSONObject("city");
            String cityname=city.optString("name");
            String country=city.optString("country");
            JSONArray weather=root.optJSONArray("list");
            for(int i=0;i<weather.length();i++)
            {
                Long date=weather.optJSONObject(i).optLong("dt");
                JSONObject temp=weather.optJSONObject(i).optJSONObject("temp");
                Double tday=temp.optDouble("day");
                Double tmax=temp.optDouble("max");
                Double tmin=temp.optDouble("min");
                Double tmor=temp.optDouble("morn");
                Double tnight=temp.optDouble("night");
                Double presure=weather.optJSONObject(i).optDouble("pressure");
                Double humidity=weather.optJSONObject(i).optDouble("humidity");
                Double speed=weather.optJSONObject(i).optDouble("speed");
                Double cloud=weather.optJSONObject(i).optDouble("clouds");
                JSONArray weth=weather.optJSONObject(i).optJSONArray("weather");
                String description=weth.optJSONObject(0).optString("main");
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
