package com.silverorange.videoplayer;

import android.app.Application;

//Class for creating class objects at App level

public class MyApp extends Application {
    private NetworkingService networkingService = new NetworkingService();
    private JsonService jsonService = new JsonService();

    public JsonService getJsonService() {
        return jsonService;
    }

    public NetworkingService getNetworkingService() {
        return networkingService;
    }


}
