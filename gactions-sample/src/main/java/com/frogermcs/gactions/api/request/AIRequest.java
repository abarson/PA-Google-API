package com.frogermcs.gactions.api.request;

import java.util.*;
import com.google.gson.internal.LinkedTreeMap;
/**
 * Created by froger_mcs on 17/01/2017.
 */
public class AIRequest{
	public String lang;
	public Object status;
	public String timestamp;
	public String sessionID;
	public Object result;
	public String id;
	public Object originalRequest;
	
	public LinkedTreeMap<Object, Object> getParameters(){
		LinkedTreeMap<Object, Object> resultMap = (LinkedTreeMap<Object, Object>)(result);
		LinkedTreeMap<Object, Object> parametersMap = (LinkedTreeMap<Object, Object>)(resultMap.get("parameters")); 
		return parametersMap;
	}
	
	private LinkedTreeMap<Object, Object> getMetaData(){
		LinkedTreeMap<Object, Object> map = (LinkedTreeMap<Object, Object>)(result);
    	LinkedTreeMap<Object, Object> metadata = (LinkedTreeMap<Object, Object>)map.get("metadata");
		return metadata;
	}
	
	public String getIntent(){
		return (String)(getMetaData().get("intentName"));
	}
	
	public String toString(){
		return String.format("lang: %s, status: %s, timestamp: %s, sessionID: %s, result: %s, id: %s, originalRequest: %s",
		lang, status, timestamp, sessionID, result, id, originalRequest);
	}
}