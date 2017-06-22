package com.frogermcs.gactions.sample;

import com.frogermcs.gactions.ResponseBuilder;
import com.frogermcs.gactions.api.RequestHandler;
import com.frogermcs.gactions.api.SupportedPermissions;
import com.frogermcs.gactions.api.request.AIRequest;
import com.frogermcs.gactions.api.response.AIResponse;

/**
 * Created by froger_mcs on 19/01/2017.
 */
public class MainRequestHandler extends RequestHandler {
    MainRequestHandler(AIRequest rootRequest) {
        super(rootRequest);
    }

    @Override
    public AIResponse getResponse() {
        return ResponseBuilder.askForPermissionResponse("See how permissions work",
                SupportedPermissions.NAME);
    }
}