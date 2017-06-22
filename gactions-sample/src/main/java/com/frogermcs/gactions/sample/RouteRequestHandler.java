package com.frogermcs.gactions.sample;

import com.google.gson.internal.LinkedTreeMap;
import com.frogermcs.gactions.api.RequestHandler;
import com.frogermcs.gactions.ResponseBuilder;
import com.frogermcs.gactions.api.request.AIRequest;
import com.frogermcs.gactions.api.response.AIResponse;
import com.frogermcs.gactions.api.request.Inputs;

/**
 * Created by froger_mcs on 19/01/2017.
 */
public class RouteRequestHandler extends RequestHandler {

    RouteRequestHandler(AIRequest aiRequest) {
        super(aiRequest);
    }

    @Override
    public AIResponse getResponse() {
    	String response = "";
    	//for (Inputs input : getRootRequest().inputs){
    	//	response += input + "and";
    	//}
    	
    	//Type type = new TypeToken<Map<Object, Object>>(){}.getType();
        //Gson gson = new Gson();
        //Map<Object, Object> map = gson.fromJson(getAIRequest().result, type);
    	//String s = getAIRequest().result);
    	LinkedTreeMap<Object, Object> map = (LinkedTreeMap<Object, Object>)getAIRequest().result;
    	LinkedTreeMap<Object, Object> metadata = (LinkedTreeMap<Object, Object>)map.get("metadata");
        //return ResponseBuilder.tellResponse("Look at this: " + getAIRequest().getIntent());
        return ResponseBuilder.tellResponse("Route Response: " + getAIRequest());
        
    }
}
