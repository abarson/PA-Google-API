package com.frogermcs.gactions.api;

import com.frogermcs.gactions.api.request.AIRequest;
import com.frogermcs.gactions.api.response.AIResponse;

/**
 * The {@code RequestHandler} is the class which handles Google Actions API requests. It should be used in
 * {@link com.frogermcs.gactions.AssistantActions} mappings to handle action intents.
 */
public abstract class RequestHandler {

    private final AIRequest aiRequest;

    protected RequestHandler(AIRequest aiRequest) {
        this.aiRequest = aiRequest;
    }

    /**
     * @return the {@link RootRequest} object - Google Actions API request
     */
    public AIRequest getAIRequest() {
        return aiRequest;
    }

    /**
     * @return UserId from Google Actions API request
     */
    public String getSessionId() {
        return aiRequest.sessionID;
    }

    /**
     * @return the {@link RootResponse} object, response for Google Actions API request
     */
    public abstract AIResponse getResponse();

    public static abstract class Factory {
        /**
         * @param rootRequest - the {@link RootResponse} object that contains request from Google Actions
         * @return the {@link RequestHandler} object, handler for Google Actions API requests
         */
        public abstract RequestHandler create(AIRequest aiRequest);
        
    }
}
