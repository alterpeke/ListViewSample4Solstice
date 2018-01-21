package com.jcpallavicino.sample.myalbumgallery.Android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.jcpallavicino.sample.myalbumgallery.R;
import com.jcpallavicino.sample.myalbumgallery.Utils.DataHandler;
import com.jcpallavicino.sample.myalbumgallery.Utils.DataObject;

import java.util.ArrayList;
import java.util.List;

import com.jcpallavicino.sample.myalbumgallery.Utils.AsynkConnector;
import com.jcpallavicino.sample.myalbumgallery.Utils.Callback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

    private ListView listView, listView2;
    private ListViewAdapter adapter, adapter2;
    private List<Album> albumList, albumList2;
    private List<DataObject> contactListFav, contactListOthers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*setContentView(R.layout.activity_main);*/
        setContentView(R.layout.activity_main_contacts);

        //Create album data list
        albumList=new ArrayList<>();
        albumList2=new ArrayList<>();
        contactListFav=new ArrayList<>();
        contactListOthers=new ArrayList<>();

        //Bind listview
        listView=(ListView)findViewById(R.id.listView1); //Favorite Contacts
        listView2=(ListView)findViewById(R.id.listView2); //Other Contacts

        getData();

        //Set an item click listener to show toast
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                callContactDetail(position, contactListFav);
            }
        });

        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                callContactDetail(position, contactListOthers);
            }
        });

    }

    private void callContactDetail(int position, List<DataObject> List) {

//        //Show a toast depends on clicked item of listview
//        Toast.makeText(MainActivity.this, getResources().getString(R.string.clicked_item, albumList.get(position).getAlbumName()), Toast.LENGTH_SHORT).show();

        DataHandler.name = List.get(position).name;
        DataHandler.isFavorite = List.get(position).isFavorite;
        DataHandler.id = List.get(position).id;
        DataHandler.companyName = List.get(position).companyName;
        DataHandler.smallImageURL = List.get(position).smallImageURL;
        DataHandler.largeImageURL = List.get(position).largeImageURL;
        DataHandler.emailAddress = List.get(position).emailAddress;
        DataHandler.birthdate = List.get(position).birthdate;
        DataHandler.work = List.get(position).work;
        DataHandler.home = List.get(position).home;
        DataHandler.mobile = List.get(position).mobile;
        DataHandler.street = List.get(position).street;
        DataHandler.city = List.get(position).city;
        DataHandler.state = List.get(position).state;
        DataHandler.country = List.get(position).country;
        DataHandler.zipCode = List.get(position).zipCode;

        Intent intent = new Intent(getBaseContext(), ContactDetail.class);
//        startActivity(intent);
        startActivityForResult(intent, 1);

    }

    private void getData() {
        String content = "{}";

        String param = "";

        AsynkConnector c = new AsynkConnector(0, param ,content, new Callback() {

            @Override
            public void starting() {

            }

            @Override
            public void completed(String res, int responseCode) {

                Log.i("INFO TASK","Tarea ejecutada");
                if(responseCode==200){
                    // Parsing de la respuesta
                    parseJsonResponse(res);

                    // Seteo de Datos para Mostrar en Pantalla
                    setAdapters();

                }else{
                    Toast.makeText(MainActivity.this, "Error on Contact Service, try it later", Toast.LENGTH_LONG);
                }

            }

            @Override
            public void completedWithErrors(Exception e) {
            }

            @Override
            public void update() {
            }
        });
        c.execute();

    }

    private void setAdapters() {
        //Create adapter and set it to listview.
        for(int i=0; i<contactListFav.size(); i++){
            Album al = new Album(contactListFav.get(i).name, contactListFav.get(i).companyName, contactListFav.get(i).smallImageURL);
            albumList.add(al);

        }
        adapter=new ListViewAdapter(MainActivity.this,albumList);
        listView.setAdapter(adapter);

        for(int i=0; i<contactListOthers.size(); i++){
            Album al = new Album(contactListOthers.get(i).name, contactListOthers.get(i).companyName, contactListOthers.get(i).smallImageURL);
            albumList2.add(al);

        }
        adapter2=new ListViewAdapter(MainActivity.this,albumList2);
        listView2.setAdapter(adapter2);

        ListUtils.setDynamicHeight(listView);
        ListUtils.setDynamicHeight(listView2);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Check which request we're responding to
        if (requestCode == 1) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                // The user picked a contact.
                    if (DataHandler.isFavorite == "true"){
                        for(int i=0; i<contactListOthers.size(); i++){
                            if (contactListOthers.get(i).name == DataHandler.name){
                                contactListOthers.get(i).isFavorite = "true";
                                DataObject al = contactListOthers.get(i);
                                contactListOthers.remove(al);
                                contactListFav.add(al);
                            }

                        }

                    }else{
                        for(int i=0; i<contactListFav.size(); i++){
                            if (contactListFav.get(i).name == DataHandler.name){
                                contactListFav.get(i).isFavorite = "false";
                                DataObject al = contactListFav.get(i);
                                contactListFav.remove(al);
                                contactListOthers.add(al);
                            }

                        }
                    }
                albumList.clear();
                albumList2.clear();
                setAdapters();
            }
        }
    }

    private void parseJsonResponse(String res) {
        try{
            JSONArray arr = new JSONArray(res);

            for(int i=0; i<arr.length();i++)
            {
                JSONObject jo = arr.getJSONObject(i);
                DataObject con = new DataObject();

                con.name = jo.optString("name");
                con.id = jo.optString("id");
                con.companyName = jo.optString("companyName");
                con.isFavorite = jo.optString("isFavorite");
                con.smallImageURL = jo.optString("smallImageURL");
                con.largeImageURL = jo.optString("largeImageURL");
                con.emailAddress = jo.optString( "emailAddress");
                con.birthdate = jo.optString( "birthday");
                JSONObject phone = jo.getJSONObject("phone");
                con.work = phone.optString("work");
                con.home = phone.optString( "home");
                con.mobile = phone.optString( "mobile");
                JSONObject address = jo.getJSONObject("address");
                con.street = address.optString("street");
                con.city = address.optString( "city");
                con.state = address.optString( "state");
                con.country = address.optString("country");
                con.zipCode = address.optString("zipCode");

                Log.i("Contacto, nombre:", con.name);
                if (con.isFavorite == "false"){
                    contactListOthers.add(con);
                }else{
                    contactListFav.add(con);
                }

            }

        }
        catch (JSONException e){
            System.out.println(e.getMessage());
            Log.i("ERROR PARSING",e.getMessage());
        }
    }

    public static class ListUtils {
        public static void setDynamicHeight(ListView mListView) {
            ListAdapter mListAdapter = mListView.getAdapter();
            if (mListAdapter == null) {
                // when adapter is null
                return;
            }
            int height = 0;
            int desiredWidth = View.MeasureSpec.makeMeasureSpec(mListView.getWidth(), View.MeasureSpec.UNSPECIFIED);
            for (int i = 0; i < mListAdapter.getCount(); i++) {
                View listItem = mListAdapter.getView(i, null, mListView);
                listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
                height += listItem.getMeasuredHeight();
            }
            ViewGroup.LayoutParams params = mListView.getLayoutParams();
            params.height = height + (mListView.getDividerHeight() * (mListAdapter.getCount() - 1));
            mListView.setLayoutParams(params);
            mListView.requestLayout();
        }
    }

}
