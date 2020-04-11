package com.henridebel;

import org.json.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;

public class RequestMaker {
    private URL url;
    private HttpURLConnection con;
    private Map<String, String> parameters = new HashMap<>();
    private String stringParams;

    public String response;

    public static void main(String... args) throws IOException {
        System.out.println("testing request maker");
        List<String> keys = Arrays.asList("query");
        List<String> values = Arrays.asList("fart");
        RequestMaker requestmaker = new RequestMaker("https://api.chucknorris.io/jokes", keys, values);
        requestmaker.sendRequest();
        System.out.println(requestmaker.getResponse());
    }

    /*
    params: specify the base url 
    for example here https://api.chucknorris.io/search?
    give in possible params
     */
    public RequestMaker(String baseUrl, List<String> inputparametersNames, List<String> inputparametersValues) throws IOException {
        generateParameterMap(inputparametersNames, inputparametersValues);
        generateParamsString();
        this.url = new URL(baseUrl + this.stringParams);
    }

    public void generateParameterMap(List<String> inputparametersNames, List<String> inputparametersValues) {
        for (int i = 0; i < inputparametersNames.size() ; i++) {
            String inputName = inputparametersNames.get(i);
            String inputValue = inputparametersValues.get(i);
            this.parameters.put(inputName, inputValue);
        }

    }

    public void generateParamsString() throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            result.append("&");
        }
        String resultString = "/search?" + result.toString();
        System.out.println("\nresult string : " + resultString.substring(0, resultString.length() - 1));
        this.stringParams = resultString.length() > 0 ?  resultString.substring(0, resultString.length() - 1) : resultString;
    }



    public void sendRequest() throws IOException {
        this.con = (HttpURLConnection) this.url.openConnection();

        this.con.setRequestProperty("User-Agent", "Mozilla/5.0");

        int responseCode = this.con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(this.con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }

        in.close();
        this.response = response.toString();
        this.con.disconnect();
    }

    public String getResponse() {
        try {
            JSONObject myjson = new JSONObject(this.response);
            return myjson.toString(2);
        } catch (JSONException ex) {
            return this.response;
        }
    }
}
