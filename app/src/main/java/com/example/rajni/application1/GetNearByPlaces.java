package com.example.rajni.application1;

import android.os.AsyncTask;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class GetNearByPlaces extends AsyncTask<Object,String,String> {
    String googleplacesdata;
    GoogleMap map;
    String url;

    @Override
    protected String doInBackground(Object... objects) {

        try{
            map =(GoogleMap)objects[0];
            url=(String)objects[1];
            DownloadUrl downloadUrl=new DownloadUrl();
            googleplacesdata=downloadUrl.readurl(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return googleplacesdata;
    }
    protected void onPostExecute(String s){
        List<HashMap<String,String>>nearbyplaceslist=null;
        DataParser parser=new DataParser();
        nearbyplaceslist=parser.parse(s);
        shownearbyplaces(nearbyplaceslist);
    }
    private void shownearbyplaces(List<HashMap<String,String>>nearbyplaceslist)
    {
        for(int i =0;i<nearbyplaceslist.size();i++){
        MarkerOptions markerOptions=new MarkerOptions();
        HashMap<String,String>googleplace=nearbyplaceslist.get(i);
        String placename=googleplace.get("place_name");
        String vicinity=googleplace.get("vicinity");
        double lat = Double.parseDouble(googleplace.get("lat"));
        double lng = Double.parseDouble(googleplace.get("lng"));
        LatLng latLng=new LatLng(lat,lng);
        markerOptions.position(latLng);
        markerOptions.title(placename+" : "+vicinity);
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        map.addMarker(markerOptions);
        map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        map.animateCamera(CameraUpdateFactory.zoomTo(10));
    }
    }
}
