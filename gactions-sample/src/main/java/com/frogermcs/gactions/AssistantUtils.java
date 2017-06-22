package com.frogermcs.gactions;

import com.frogermcs.gactions.api.request.Inputs;
import com.frogermcs.gactions.api.request.AIRequest;
import com.frogermcs.gactions.api.response.RootResponse;

import javax.annotation.Nullable;

/**
 * Helper methods for Google Actions API requests/responses
 */
public class AssistantUtils {
    /**
     * @param rootRequest - the {@link RootResponse} object that contains request from Google Actions
     * @return Action intent string from Google Action request.
     */
    @Nullable
    public static String getActionIntent(AIRequest request) {
        return request.getIntent();
    }
}
