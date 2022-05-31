package com.silverorange.videoplayer;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//Class responsible for fetching data from an API

public class NetworkingService {

        String url = "http://localhost:4000/videos";

        public static final ExecutorService networkingExecutor = Executors.newFixedThreadPool(4);
        static Handler networkHandler = new Handler(Looper.getMainLooper());

        interface NetworkingListener{
            void APINetworkListener(String jsonString);
            void APINetworkingListerForImage(Bitmap image);
        }

        NetworkingListener listener;

        //Function to fetch video links from API

        public void fetchVideos(){
            connect(url);
        }

        public void getVideoData(String videoLink){
            String videoURL = videoLink;
            networkingExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        URL urlObj = new URL(videoURL);
                        InputStream in = ((InputStream)urlObj.getContent());
                        Bitmap videoData = BitmapFactory.decodeStream(in);
                        networkHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                listener.APINetworkingListerForImage(videoData);
                            }
                        });
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        //Function to connect to the API and get JSON data and store in a string

        private void connect(String url){
            networkingExecutor.execute(new Runnable() {
                String jsonString = "";
                @Override
                public void run() {
                    HttpURLConnection httpURLConnection = null;

                    try {
                        URL urlObject = new URL(url);
                        httpURLConnection = (HttpURLConnection) urlObject.openConnection();
                        httpURLConnection.setRequestMethod("GET");
                        httpURLConnection.setRequestProperty("Content-Type","application/json");
                        int status = httpURLConnection.getResponseCode();

                        System.out.println(status);
                        if ((status >= 200) && (status <= 299)) {
                            InputStream in = httpURLConnection.getInputStream();
                            InputStreamReader inputStreamReader = new InputStreamReader(in);
                            int read = 0;
                            while ((read = inputStreamReader.read()) != -1) {
                                char c = (char) read;
                                jsonString += c;
                            }
                            System.out.println(jsonString);
                            final String finalJson = jsonString;
                            System.out.println(finalJson);
                            networkHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    //send data to main thread
                                    listener.APINetworkListener(finalJson);
                                }
                            });
                        }
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }  finally {
                        httpURLConnection.disconnect();
                    }
                }
            });
        }
    }


