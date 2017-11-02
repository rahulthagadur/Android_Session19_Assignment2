package com.example.thagadur.android_session19_assignment2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.thagadur.android_session19_assignment2.utils.CommonUtilities;
import com.squareup.okhttp.FormEncodingBuilder;

import org.json.JSONArray;
import org.json.JSONObject;
import com.example.thagadur.android_session19_assignment2.network.*;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnWebServiceResult{

    // URL to get upcoming tv shows
    private static String url = "http://api.themoviedb.org/3/tv/top_rated?api_key=8496be0b2149805afa458ab8ec27560c";
    List<DataHandler> model= new ArrayList<>();
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list= (ListView)findViewById(R.id.list);
        getMovies();
    }

    private void getMovies(){
        FormEncodingBuilder params= new FormEncodingBuilder();
        params.add("id", "123456");
        params.add( "action", "get_contacts");
        if(NetworkStatus.getInstance(this).isConnectedToInternet()){
            CallAddr call = new CallAddr(this, url, params, CommonUtilities.SERVICE_TYPE.GET_DATA, this);
            CommonUtilities.showLoading(this, "Please wait...", false);
            call.execute();
        }else{
            Toast.makeText(this, "Please check your internet connection!!",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getWebResponse(String result, CommonUtilities.SERVICE_TYPE type) {
        CommonUtilities.hideLoading();
        switch (type){
            case GET_DATA:
                try {
                    JSONObject obj= new JSONObject(result);
                    JSONArray arr= obj.getJSONArray("results");
                    for(int i=0; i<arr.length(); i++){
                        JSONObject jobj= arr.getJSONObject(i);
                        DataHandler handler= new DataHandler();
                        handler.setId(jobj.getInt("id"));
                        handler.setName(jobj.getString("name"));
                        handler.setVote_count(jobj.getInt("vote_count"));
                        model.add(handler);
                    }
                    CustomListAdapter adapter= new CustomListAdapter(this, model);
                    list.setAdapter(adapter);
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
        }

    }
}
