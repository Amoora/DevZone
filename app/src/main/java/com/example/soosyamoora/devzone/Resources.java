package com.example.soosyamoora.devzone;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Resources extends AppCompatActivity {

    ResourcesClass[] resourceArray;
    int[] imagesArray;
    String response = null;
    String line;
    JSONObject jsonObject;
    ResourcesClass rresourcesClass;
    StringBuilder jsonBuilder;
    JSONObject rJsonObject;
    int i;
    JSONArray rJsonArray;
    ListView rListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resources);





        rListView = (ListView) findViewById(R.id.rListView);

        try {
            BufferedReader jsonReader = new BufferedReader(new InputStreamReader(this.getResources().openRawResource(R.raw.resources)));
            jsonBuilder = new StringBuilder();
            for (line = null;
                 (line = jsonReader.readLine()) != null;) {
                jsonBuilder.append(line).append("\n");
            }
            response = jsonBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try{
            rJsonObject = new JSONObject(response);
            rJsonArray = rJsonObject.getJSONArray("Resources");
            resourceArray = new ResourcesClass[rJsonArray.length()];

            imagesArray = new int[]{
                    R.drawable.imgm,
                    R.drawable.imgd,
                    R.drawable.imgp,
                    R.drawable.imgs,
                    R.drawable.imga,
                    R.drawable.imgm,
                    R.drawable.imgd,
                    R.drawable.imgp,
                    R.drawable.imgs,
                    R.drawable.imga,



            };

            for (i = 0; i < rJsonArray.length(); i++) {
                jsonObject = rJsonArray.getJSONObject(i);

                rresourcesClass = new ResourcesClass();
                rresourcesClass.setId(Integer.valueOf(jsonObject.getString("id")));
                rresourcesClass.setTitle(jsonObject.getString("title"));
                rresourcesClass.setDetail(jsonObject.getString("detail"));
                rresourcesClass.setType(jsonObject.getString("type"));
                rresourcesClass.setPoints(jsonObject.getString("points"));
                rresourcesClass.setWords(jsonObject.getString("author"));

                resourceArray[i] = rresourcesClass;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }






    CustomAdapter mCustomAdapter = new CustomAdapter() {
        @Override
        public int getCount() {
            return resourceArray.length;
        }

        @Override
        public Object getItem(int position) {
            return resourceArray[position];
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }
    };


        rListView.setAdapter(mCustomAdapter);

        rListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent mIntent = new Intent(Resources.this, DevDetails.class);

                mIntent.putExtra("hotoo", imagesArray[Integer.valueOf(resourceArray[position].id)]);
                mIntent.putExtra("title", resourceArray[position].Title);
                mIntent.putExtra("detail", resourceArray[position].Detail);
                mIntent.putExtra("type", resourceArray[position].Type);
                mIntent.putExtra("point", resourceArray[position].Points);
                mIntent.putExtra("author", resourceArray[position].Author);


                startActivity(mIntent);
            }
        });
    }

    public abstract class CustomAdapter extends BaseAdapter {
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.resources_layout, parent,
                        false);


                ResourcesClass resourcesClass = (ResourcesClass) getItem(position);

                String hoto;
                hoto = resourcesClass.getDetail();

                ((TextView) convertView.findViewById(R.id.txttitle)).
                        setText(resourcesClass.getTitle());

                Picasso.with(Resources.this)
                        .load(imagesArray[Integer.valueOf(resourcesClass.getId())])
                        .resize(120, 120)
                        .into(((ImageView) convertView.findViewById(R.id.rimg)));

                ((TextView) convertView.findViewById(R.id.txtdetail)).
                        setText(resourcesClass.getDetail());

                ((TextView) convertView.findViewById(R.id.txttype)).
                        setText(resourcesClass.getType());

                ((TextView) convertView.findViewById(R.id.txtpoint)).
                        setText(resourcesClass.getPoints());

                ((TextView) convertView.findViewById(R.id.txtauthor)).
                        setText(resourcesClass.getPoints());

            }
            return convertView;
        }
    }
}
