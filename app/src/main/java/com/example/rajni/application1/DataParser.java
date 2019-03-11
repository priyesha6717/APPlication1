package com.example.rajni.application1;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataParser {
    private HashMap<String,String>getplace(JSONObject googleplaceJason){
        HashMap<String,String >googleplacemap=new HashMap<>();
        String placename="-NA-";
        String vicinity="-NA-";
        String latitude="";
        String longitude="";
        String reference = "";
        try {
            if (!googleplaceJason.isNull("name")) {

                placename = googleplaceJason.getString("name");
            }
            if(!googleplaceJason.isNull("vicinity"))
            {
                vicinity =googleplaceJason.getString("vicinity");
            }
            latitude=googleplaceJason.getJSONObject("geometry").getJSONObject("location").getString("lat");
            longitude=googleplaceJason.getJSONObject("geometry").getJSONObject("location").getString("lng");
            reference = googleplaceJason.getString("reference");
            googleplacemap.put("place_name",placename);
            googleplacemap.put("vicinoty",vicinity);
            googleplacemap.put("lat",latitude);
            googleplacemap.put("lng",longitude);
            googleplacemap.put("reference",reference);

        }
            catch (JSONException e) {
                e.printStackTrace();
            }

        return googleplacemap;
    }
    private List<HashMap<String,String>> getplaces(JSONArray jsonArray)
    {
        int count = jsonArray.length();
        List<HashMap<String,String>> placeslist=new ArrayList<>();
        HashMap<String,String> placemap=null;
        for (int i=0;i<count;i++)
        {
            try {
                placemap=getplace((JSONObject)jsonArray.get(i));
                placeslist.add(placemap);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return placeslist;
    }
    public List<HashMap<String,String>>parse(String jsondata)
    {
        JSONArray jsonArray=null;
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(jsondata);
            jsonArray=jsonObject.getJSONArray("results");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return getplaces(jsonArray);
    }
}
