package com.liblab.rest;

import java.util.logging.Logger;

import java.io.IOException;

import java.nio.charset.StandardCharsets;

import org.apache.http.HttpResponse;

import org.apache.http.client.HttpClient;

import org.apache.http.client.methods.HttpGet;

import org.apache.http.util.EntityUtils;

import org.apache.http.impl.client.HttpClientBuilder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;

import retrofit2.converter.gson.GsonConverterFactory;

import com.liblab.common.CommonConstants;

import com.liblab.api.Manager;

public final class RESTCommunication {
	
	public static final String AUTHORIZATION = "Authorization";
	public static final String BEARER = "Bearer";

	protected static Logger logger = Logger.getLogger(RESTCommunication.class.getName());
		
	public static HttpClient getHttpClient() {
		return HttpClientBuilder.create().build();
	}
	
    public static String getJsonPayload(String uri, String accessToken) throws IOException {

        logger.info("REST URI: " + uri);

        HttpGet httpGet = new HttpGet(uri);
        if (accessToken != null) {
        	httpGet.setHeader(AUTHORIZATION, BEARER + CommonConstants.SPACE + accessToken);
        }
        
        HttpResponse httpResponse = getHttpClient().execute(httpGet);
        
        String responseJson = EntityUtils.toString(httpResponse.getEntity(), StandardCharsets.UTF_8);
        logger.info("Response JSON:\n" + responseJson);
        
        return responseJson;
        
    }
    
    public static String getJsonPayload(String uri) throws IOException {
    	return getJsonPayload(uri, null);
    }
    
    public static Retrofit getRetrofitInstance() {
    	Gson gson = new GsonBuilder().setLenient().create();
    	return new Retrofit.Builder().baseUrl(Manager.getBaseUrl())
    			.addConverterFactory(GsonConverterFactory.create(gson)).build();
    }

    public static RESTServices getRESTServicesInstance() {
        return getRetrofitInstance().create(RESTServices.class);
    }
    
}