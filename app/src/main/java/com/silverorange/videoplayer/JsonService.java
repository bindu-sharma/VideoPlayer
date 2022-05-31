package com.silverorange.videoplayer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
//Class that handles JSON data parsing

public class JsonService {

        //Function to parse JSON for Vedio List
        public ArrayList<VedioItem> parseVideoList(String jsonVedioList) {
            ArrayList<VedioItem> allVediosFromAPI = new ArrayList<>(0);

            try {
                for (int i = 0; i < jsonVedioList.length(); i++) {
                    JSONArray jsonArray = new JSONArray(jsonVedioList);
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    //Getting the video link and description and storing ot in an array of type 'VideoItem'
                    String description = jsonObject.getString("description");
                    String vedioLink= jsonObject.getString("vedio_link");
                    allVediosFromAPI.add(new VedioItem(description,vedioLink));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            System.out.println(allVediosFromAPI);
            return allVediosFromAPI;
        }

    }



