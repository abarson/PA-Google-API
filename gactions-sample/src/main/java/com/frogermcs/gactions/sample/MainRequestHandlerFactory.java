package com.frogermcs.gactions.sample;

import com.frogermcs.gactions.api.RequestHandler;
import com.frogermcs.gactions.api.request.AIRequest;

/**
 * Created by froger_mcs on 19/01/2017.
 */
public class MainRequestHandlerFactory extends RequestHandler.Factory {
    @Override
    public RequestHandler create(AIRequest rootRequest) {
        return new MainRequestHandler(rootRequest);
    }
}
