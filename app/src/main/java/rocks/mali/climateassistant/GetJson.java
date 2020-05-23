package rocks.mali.climateassistant;

import org.json.JSONObject;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.HttpURLConnection;
import java.net.URL;


public class GetJson {
    private static HttpURLConnection connection;


    public static JSONObject jsonReturn() throws JSONException {
        System.out.println("Sending request...");
        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();
//        String jsonResponse;
        try {
            System.out.println("Sending request...Step 2 ========================");
            String link = "http://192.168.10.98";
            System.out.println("Sending request...Step 3 ========================");
//            String link = "https://community-open-weather-map.p.rapidapi.com/weather";
            URL url = new URL(link);
            System.out.println("Sending request...Step 4 ========================");
            connection = (HttpURLConnection) url.openConnection();
            System.out.println("Sending request...Step 5 ========================");
            connection.setRequestMethod("GET");
            System.out.println("Sending request...Step 6 ========================");
            // connection.setConnectTimeout(5000);
            System.out.println("Sending request...Step 7 ========================");
            // connection.setReadTimeout(5000);
            System.out.println("Sending request...Step 8 ========================");
            int status = connection.getResponseCode();
            System.out.println(status);
            System.out.println("Sending request...Step 9 ========================");
            System.out.println("Response is " + responseContent.toString());
            if (status > 299) {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
            }
            System.out.println(responseContent.toString());
            String jsonString = responseContent.toString();


            JSONObject obj = new JSONObject(jsonString);
            System.out.println("+++++++++++++++++");
            System.out.println(obj);
            System.out.println("+++++++++++++++++");
            JSONObject meters_data = obj.getJSONObject("readings");
            JSONObject hum = meters_data.getJSONObject("humidity");
            JSONObject temp = meters_data.getJSONObject("temperature");
            JSONObject alt = meters_data.getJSONObject("altitude");
            JSONObject press = meters_data.getJSONObject("pressure");
            System.out.println(meters_data);
            return meters_data;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        JSONObject no_answer = new JSONObject();
//        no_answer.put("result", "Sorry, no connection!");
//        return no_answer;
        return null;
    }
}
