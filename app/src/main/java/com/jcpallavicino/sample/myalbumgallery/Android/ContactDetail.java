package com.jcpallavicino.sample.myalbumgallery.Android;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jcpallavicino.sample.myalbumgallery.R;
import com.jcpallavicino.sample.myalbumgallery.Utils.DataHandler;
import com.squareup.picasso.Picasso;


/**
 * Created by juan.pallavicino on 16/1/2018.
 */


public class ContactDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_detail);

        setContactLayout();

    }

    private void setContactLayout() {
        ImageView image = (ImageView)findViewById(R.id.imagen_contacto);
        if (!DataHandler.largeImageURL.isEmpty()){
            Picasso.with(ContactDetail.this).load(DataHandler.largeImageURL).into(image);
        }else{
            image.setVisibility(View.GONE);
        }

        TextView nombreContacto = (TextView)findViewById(R.id.nombre_contacto);
        if (!DataHandler.name.isEmpty()){
            nombreContacto.setText(DataHandler.name);
        }else{
            nombreContacto.setVisibility(View.GONE);
        }

        TextView compania = (TextView)findViewById(R.id.company_contacto);
        if (!DataHandler.companyName.isEmpty() || DataHandler.companyName != "null" || DataHandler.companyName != null){
            compania.setText(DataHandler.companyName);
        }else{
            compania.setVisibility(View.GONE);
        }

        TextView phone_home_label = (TextView)findViewById(R.id.phone_home_label);
        TextView phone_home_data = (TextView)findViewById(R.id.phone_home_data);
        TextView phone_home_legend = (TextView)findViewById(R.id.phone_home_legend);
        if (!DataHandler.home.isEmpty()){
            phone_home_label.setText("PHONE");
            phone_home_data.setText(DataHandler.home);
            phone_home_legend.setText("Home");
        }else{
            phone_home_label.setVisibility(View.GONE);
            phone_home_data.setVisibility(View.GONE);
            phone_home_legend.setVisibility(View.GONE);
        }

        TextView phone_mobile_label = (TextView)findViewById(R.id.phone_mobile_label);
        TextView phone_mobile_data = (TextView)findViewById(R.id.phone_mobile_data);
        TextView phone_mobile_legend = (TextView)findViewById(R.id.phone_mobile_legend);
        if (!DataHandler.mobile.isEmpty()){
            phone_mobile_label.setText("PHONE");
            phone_mobile_data.setText(DataHandler.mobile);
            phone_mobile_legend.setText("Mobile");
        }else{
            phone_mobile_label.setVisibility(View.GONE);
            phone_mobile_data.setVisibility(View.GONE);
            phone_mobile_legend.setVisibility(View.GONE);
        }

        TextView phone_work_label = (TextView)findViewById(R.id.phone_work_label);
        TextView phone_work_data = (TextView)findViewById(R.id.phone_work_data);
        TextView phone_work_legend = (TextView)findViewById(R.id.phone_work_legend);
        if (!DataHandler.work.isEmpty()){
            phone_work_label.setText("PHONE");
            phone_work_data.setText(DataHandler.work);
            phone_work_legend.setText("Work");
        }else{
            phone_work_label.setVisibility(View.GONE);
            phone_work_data.setVisibility(View.GONE);
            phone_work_legend.setVisibility(View.GONE);
        }

        TextView address_label = (TextView)findViewById(R.id.address_label);
        TextView address = (TextView)findViewById(R.id.address);
        if(!DataHandler.street.isEmpty()){
            address_label.setText("ADDRESS");
            address.setText(DataHandler.street);
        }else{
            address_label.setVisibility(View.GONE);
            address.setVisibility(View.GONE);
        }

        TextView city = (TextView)findViewById(R.id.city);
        if (!DataHandler.city.isEmpty()||!DataHandler.state.isEmpty()||!DataHandler.city.isEmpty()||!DataHandler.zipCode.isEmpty()){
            String dataAddress = DataHandler.city +", "+ DataHandler.state +" "+ DataHandler.zipCode + ", " + DataHandler.country;
            city.setText(dataAddress);
        }else{
            city.setVisibility(View.GONE);
        }

        TextView birthdate_label = (TextView)findViewById(R.id.birthdate_label);
        TextView birthdate = (TextView)findViewById(R.id.birthdate);
        if (!DataHandler.birthdate.isEmpty()){
            birthdate_label.setText("BIRTHDAY");
            birthdate.setText(DataHandler.birthdate);
        }else{
            birthdate_label.setVisibility(View.GONE);
            birthdate.setVisibility(View.GONE);
        }

        TextView email_label = (TextView)findViewById(R.id.email_label);
        TextView email = (TextView)findViewById(R.id.email);
        if (!DataHandler.emailAddress.isEmpty()){
            email_label.setText("EMAIL");
            email.setText(DataHandler.emailAddress);
        }else{
            email_label.setVisibility(View.GONE);
            email.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.contact_menu,menu);

        if (DataHandler.isFavorite == "true") {
            menu.findItem(R.id.menu_star).setIcon(R.drawable.favoritetrue);
        }else{
            menu.findItem(R.id.menu_star).setIcon(R.drawable.favoritefalse);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if (item.getItemId() == R.id.menu_star){
            if (DataHandler.isFavorite == "true") {
                DataHandler.isFavorite = "false";
            }else{
                DataHandler.isFavorite = "true";
            }

            Intent intent = getIntent();
            setResult(RESULT_OK, intent);
            finish();

        }
        return true;
    }
}
