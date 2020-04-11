package com.henridebel;

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

    public static void main(String... args) throws IOException {
        System.out.println("testing request maker");
        List<String> keys = Arrays.asList("query");
        List<String> values = Arrays.asList("fart");
        RequestMaker mymaker = new RequestMaker("https://api.chucknorris.io/jokes/search?", keys, values);
        mymaker.sendRequest();
    }

    /*
    params: specify the base url 
    for example here https://api.chucknorris.io/search?
    give in possible params

     */
    public RequestMaker(String baseUrl, List<String> inputparametersNames, List<String> inputparametersValues) throws IOException {
        this.url = new URL(baseUrl); // just create the basic url
        generateParameterMap(inputparametersNames, inputparametersValues);
        generateParamsString();
        /*
        con.setRequestMethod("GET");
        con.setDoOutput(true);
        DataOutputStream out = new DataOutputStream(con.getOutputStream());
        out.writeBytes(stringParams);
        out.flush();
        out.close();
         */
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
        String resultString = result.toString();
        this.stringParams = resultString.length() > 0 ?  resultString.substring(0, resultString.length() - 1) : resultString;
    }


    public void sendRequest() throws IOException {
        this.con = (HttpURLConnection) this.url.openConnection();
        this.con.setDoOutput(true);
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

        System.out.println(response.toString());
    }
}
