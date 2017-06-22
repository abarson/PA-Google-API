package com.frogermcs.gactions.sample;

import com.frogermcs.gactions.api.RequestHandler;
import com.frogermcs.gactions.ResponseBuilder;
import com.frogermcs.gactions.api.request.AIRequest;
import com.frogermcs.gactions.api.response.AIResponse;

/**
 * Created by froger_mcs on 19/01/2017.
 */
public class TextRequestHandler extends RequestHandler {

    TextRequestHandler(AIRequest aiRequest) {
        super(aiRequest);
    }

    @Override
    public AIResponse getResponse() {
        return ResponseBuilder.tellResponse("You've just said: " + getAIRequest().id);
    }
}
