package com.example.soosyamoora.devzone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class DevelopersActivity extends AppCompatActivity {

    ContactClass[] contactArray;
    int[] imagesArray;
    String response = null;
    String line;
    JSONObject jsonObject;
    ContactClass mcontactClass;
    StringBuilder jsonBuilder;
    JSONObject mJsonObject;
    int i;
    JSONArray mJsonArray;
    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developers);

        mListView = (ListView) findViewById(R.id.mListView);

        try {
            BufferedReader jsonReader = new BufferedReader(new InputStreamReader(this.getResources().openRawResource(R.raw.datajson)));
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
            mJsonObject = new JSONObject(response);
            mJsonArray = mJsonObject.getJSONArray("Contacts");
            contactArray = new ContactClass[mJsonArray.length()];

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

            for (i = 0; i < mJsonArray.length(); i++) {
                jsonObject = mJsonArray.getJSONObject(i);

                mcontactClass = new ContactClass();
                mcontactClass.setId(Integer.valueOf(jsonObject.getString("id")));
                mcontactClass.setName(jsonObject.getString("name"));
                mcontactClass.setPhoneNumber(jsonObject.getString("phone"));
                mcontactClass.setEmail(jsonObject.getString("email"));
                mcontactClass.setGithub(jsonObject.getString("github"));
                mcontactClass.setWords(jsonObject.getString("hub"));
                mcontactClass.setArea(jsonObject.getString("area"));
                mcontactClass.setPics(jsonObject.getString("image"));
                //mcontactClass.setPSp(jsonObject.getString("specialzation"));

                contactArray[i] = mcontactClass;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        CustomAdapter mCustomAdapter = new CustomAdapter() {
            @Override
            public int getCount() {
                return contactArray.length;
            }

            @Override
            public Object getItem(int position) {
                return contactArray[position];
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }
        };

        mListView.setAdapter(mCustomAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent mIntent = new Intent(DevelopersActivity.this, DevDetails.class);

                mIntent.putExtra("hotoo", imagesArray[Integer.valueOf(contactArray[position].id)]);
                mIntent.putExtra("name", contactArray[position].Name);
                mIntent.putExtra("phone", contactArray[position].PhoneNumber);
                mIntent.putExtra("email", contactArray[position].Email);
                mIntent.putExtra("github", contactArray[position].Github);
                mIntent.putExtra("hub", contactArray[position].Hub);
                mIntent.putExtra("area", contactArray[position].Area);


                startActivity(mIntent);
            }
        });
    }

    public abstract class CustomAdapter extends BaseAdapter {
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.listview_layout, parent,
                        false);


                ContactClass contactClass = (ContactClass) getItem(position);

                String hoto;
                hoto = contactClass.getPics();

                ((TextView) convertView.findViewById(R.id.name)).
                        setText(contactClass.getName());

                Picasso.with(DevelopersActivity.this)
                        .load(imagesArray[Integer.valueOf(contactClass.getId())])
                        .resize(120, 120)
                        .into(((ImageView) convertView.findViewById(R.id.DevImg)));

                ((TextView) convertView.findViewById(R.id.phone)).
                        setText(contactClass.getPhoneNumber());

                ((TextView) convertView.findViewById(R.id.email)).
                        setText(contactClass.getEmail());

                ((TextView) convertView.findViewById(R.id.github)).
                        setText(contactClass.getGithub());

                ((TextView) convertView.findViewById(R.id.area)).
                        setText(contactClass.getArea());

            }
            return convertView;
      }
}
}
