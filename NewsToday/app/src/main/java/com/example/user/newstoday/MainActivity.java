package com.example.user.newstoday;

import android.annotation.SuppressLint;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.user.newstoday.model.dataholder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;

    RecyclerView.LayoutManager layoutManager;


    SwipeRefreshLayout swipeRefreshLayout;
    String news_url[]={"https://newsapi.org/v2/top-headlines?country=in&category=business&apiKey=f5d2d9d2cb9e4829bc8596aa8f0202cf"
            ,"https://newsapi.org/v2/top-headlines?sources=techcrunch&apiKey=f5d2d9d2cb9e4829bc8596aa8f0202cf"
    ,"https://newsapi.org/v2/everything?domains=wsj.com&apiKey=f5d2d9d2cb9e4829bc8596aa8f0202cf"};

//    String news_url2="https://newsapi.org/v2/top-headlines?sources=techcrunch&apiKey=f5d2d9d2cb9e4829bc8596aa8f0202cf";
//
//    String new_url3="https://newsapi.org/v2/everything?domains=wsj.com&apiKey=f5d2d9d2cb9e4829bc8596aa8f0202cf";

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        swipeRefreshLayout=(SwipeRefreshLayout)findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setColorSchemeColors(R.color.colorAccent);
        recyclerView=(RecyclerView)findViewById(R.id.recycle);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);




        recyclerView.setLayoutManager(layoutManager);


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(networkHelper.isnewtworkAvailable(MainActivity.this)) {
                    dataLoading();
                    swipeRefreshLayout.setRefreshing(false);
                }
                else
                {

                    Toast.makeText(MainActivity.this, "Internet not available", Toast.LENGTH_SHORT).show();
                    swipeRefreshLayout.setRefreshing(false);
                }
            }
        });
        Log.d("akash","a");



    }

    @Override
    protected void onStart() {
        super.onStart();

        if(networkHelper.isnewtworkAvailable(MainActivity.this)) {
            dataLoading();
        }
        else
        {
            Toast.makeText(MainActivity.this, "Internet not available", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_layout,menu);

        MenuItem menuItem=menu.findItem(R.id.ic_search);

        SearchView searchView=(SearchView)menuItem.getActionView();
        searchView.setQueryHint("Type for search");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return true;

    }


    private  void dataLoading()
    {
        Log.d("akash","bb");
        for(int i=0;i<3;i++) {
            Log.d("akash",i+"hijkl");
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, news_url[i], null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    try {
                        JSONArray jsonArray = response.getJSONArray("articles");
                        Log.d("akash", "b");
                        int count = 0;
                        ArrayList<dataholder> sprt = new ArrayList<>();
                        Log.d("akash", jsonArray.length() + "kk");
                        while (count < jsonArray.length()) {
                            Log.d("akash", "b ");

                            JSONObject jsonObject = jsonArray.getJSONObject(count);
                            Log.d("akash", "1");
//                        Log.d("akash",jsonObject.getString("place"));
//                        Log.d("akash", "2");
                            dataholder dataholder = new dataholder(jsonObject.getString("author"), jsonObject.getString("publishedAt")
                                    , jsonObject.getString("urlToImage"), jsonObject.getString("description"),jsonObject.getString("url"));
//                                jsonObject.getString("place"),jsonObject.getDouble("mag")
//                                ,jsonObject.getString("time"),jsonObject.getString("title"));

                            sprt.add(dataholder);
                            count++;

                        }
                        adapter = new MyAdapter(sprt, MainActivity.this);

                        recyclerView.setAdapter(adapter);

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.d("akash", "Not able to go inside while loop");
                    }


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Log.d("akash", "Something went wrong ");

                }
            });

            mysingelton.getInstance(MainActivity.this).addToRequestQueue(jsonObjectRequest);
            Log.d("akash", "At Last");
        }
    }


}
