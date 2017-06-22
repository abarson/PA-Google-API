# Unofficial Google Actions Java SDK

Official [Google Actions SDK](https://github.com/actions-on-google/actions-on-google-nodejs) is written in Node.js. But in many situations voice interfaces like Google Home or Google Assistant will extend or replace mobile apps. If you are old fashioned Android engineer and the most of your code is already written in Java, why not reuse it and build voice extension to app on your own? And this is the main reason to build Google Actions Java SDK - enabling as much developers as possible to build their brilliant ideas for Google Assistant and Home.

Currently this is just working proof of concept of Google Actions Java SDK. It means that there is no documentation, fixed interface, (not much) unit tests and many, many others.  

Google Actions Java SDK is build based on official Node.js library, but it's not a mirror copy of it. The goal is to make it fully compatible with [Conversational Protocol](https://developers.google.com/actions/reference/conversation) of Assistant Platform.

### Assistant Actions Java SDK

## com.frogermcs.gactions

This code is responsible for handling requests and responses compatible with [Conversational Protocol](https://docs.api.ai/docs/webhook#webhook-requirements). 

## Code

Here is example code showing how to use Google Actions Java SDK

```java
AssistantActions assistantActions =
        new AssistantActions.Builder(new AppEngineResponseHandler(response))
                .addRequestHandlerFactory(StandardIntents.MAIN, new MainRequestHandlerFactory())
                .addRequestHandlerFactory(StandardIntents.TEXT, new TextRequestHandlerFactory())
                .addRequestHandlerFactory(StandardIntents.PERMISSION, new MyPermissionRequestHandlerFactory())
                .build();

assistantActions.handleRequest(request);
```

To build `AssistantActions` object, we need to pass implementation of `ResponseHandler` interface. This class will be responsible for passing json response to Google Actions.
Then we need to build intents mapping to delegate request to proper `RequestHandlers`. RequestHandlers are responsible for preparing response for Google Actions.  
At the end we need to pass request coming from Google Actions to our `AssistantActions` object.

### Example implementation

Here are the most important classes from example AppEngine Java implementation 

`ActionsServlet` - entry class in our endpoint.
 
```java
public class ActionsServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AssistantActions assistantActions =
                new AssistantActions.Builder(new AppEngineResponseHandler(response))
                        .addRequestHandlerFactory(StandardIntents.MAIN, new MainRequestHandlerFactory())
                        .addRequestHandlerFactory(StandardIntents.TEXT, new TextRequestHandlerFactory())
                        .addRequestHandlerFactory(StandardIntents.PERMISSION, new MyPermissionRequestHandlerFactory())
                        .build();

        assistantActions.handleRequest(parseActionRequest(request));
    }

    //...
}
```

`AppEngineResponseHandler` - implementation or `ResponseHandler`. Method `onResponse(RootResponse rootResponse)` passes back prepared response to HttpServletResponse.

```java
public class AppEngineResponseHandler implements ResponseHandler {
    private final HttpServletResponse httpServletResponse;

    @Override
    public void onPrepareContentType(String contentType) {
        httpServletResponse.setContentType(contentType);
    }

    @Override
    public void onPrepareResponseHeaders(Map<String, String> headers) {
        for (String headerName : headers.keySet()) {
            httpServletResponse.addHeader(headerName, headers.get(headerName));
        }
    }

    @Override
    public void onResponse(RootResponse rootResponse) {
        gson.toJson(rootResponse, httpServletResponse.getWriter());
    }
}
```

## How to deploy this project to Google Cloud

[official documentation](https://developers.google.com/actions/develop/sdk/)

### Google Cloud

- Follow steps from here: https://cloud.google.com/sdk/docs/. At the end you should be able to use gcloud on your computer. 

### Configuration

Files to edit:
- gactions-sample/src/main/webapp/WEB-INF/appengine-web.xml -> Edit application_id (Project ID: connectiontest-170113 from https://console.cloud.google.com/).

Once you have Google Cloud SDK installed on you machine, and you are ready to deploy, use this gradle task:
 
 `$ gradle google-sample:appengineDeploy`
 or
 `$ ./gradlew google-sample:appengineDeploy`
 
You can now use the endpoint  `https://test-9e127.appspot.com/` as a the webhook for your fulfillment.
Note that once 15 versions have been uploaded to Google Cloud, you will have to start deleting them.
To do so, run this command to see what versions are running:

`$ gcloud app versions list`

and then this command to delete older versions:

`$ gcloud app versions delete [version_id]`

### Testing

You can use the [Web Simulator](https://developers.google.com/actions/tools/web-simulator) or [API.AI](https://console.api.ai/) 
