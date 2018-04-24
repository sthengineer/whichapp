package sth.com.whichapp.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import sth.com.whichapp.R;
import sth.com.whichapp.service.Country;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private List<Country> countries = new ArrayList<>();
    private MyAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            run();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ButterKnife.bind(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAdapter = new MyAdapter(countries);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);


    }

    void run() throws IOException {

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.whichapp.com/v1/countries")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final String myResponse = response.body().string();

                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {

                            JSONArray json = new JSONArray(myResponse);
                            JSONObject j_cunt;
                            String[] names = new String[json.length()-1];
                            String[] iso = new String[json.length()-1];
                            String[] number = new String[json.length()-1];
                            for(int i = 0; i<json.length()-1; i++){
                                j_cunt = (JSONObject) json.get(i);
                                names[i]= (String) j_cunt.get("name");
                                iso[i]= (String) j_cunt.get("iso");
                                number[i] = (String) j_cunt.get("phone");
                                Country country = new Country(names[i], iso[i], number[i]);
                                countries.add(country);

                            }
                            mAdapter.notifyDataSetChanged();


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

            }
        });
    }
}
