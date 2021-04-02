package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements onitem,NavigationView.OnNavigationItemSelectedListener, TabLayout.OnTabSelectedListener {
ImageView iv;
Button tv;
  onitem acticity ;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    TabLayout tabLayout;
    ViewPager viewPager;
    ArrayList<News> News;
    Myadapter myadapter;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ProgressBar progressBar;
    public final String API_KEY="db4073ccdfbd4e21ab9ad5232473c600";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        acticity=this;


         toolbar = findViewById(R.id.my_toolbar);
          navigationView=(NavigationView) findViewById(R.id.navigationdrawer);

        setSupportActionBar(toolbar);
         getSupportActionBar().setDisplayShowTitleEnabled(true);
         drawerLayout= findViewById(R.id.drawer);
         toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
      drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
   navigationView.setNavigationItemSelectedListener(this);
   tabLayout= findViewById(R.id.tab_layout);
        News= new ArrayList<News>();
         recyclerView= findViewById(R.id.recycler);
        progressBar= findViewById(R.id.progressbar);
        layoutManager= new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        news(null);
tabLayout.addOnTabSelectedListener(this);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
     int position=tab.getPosition();
        switch (position){
            case 0:
                news(null);
                toolbar.setTitle("Home");
                break;
            case 1:
                news("technology");
                toolbar.setTitle("Technology");
                break;
                case 2:
                news("business");
                    toolbar.setTitle("Business");
                break;

                case 3:
                news("entertainment");
                    toolbar.setTitle("Entertainment");
                break;
                case 4:
                news("health");
                    toolbar.setTitle("Health");
                break;
                case 5:
                news("science");
                    toolbar.setTitle("Science");
                break;
            case 6:
                news("sports");
                toolbar.setTitle("Sports");
                break;

        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
//        viewPager.setCurrentItem(tab.getPosition());
        Toast.makeText(getApplicationContext(),String.valueOf(tab.getPosition()),Toast.LENGTH_LONG).show();
    }

    public  void news(String category){
        progressBar.setVisibility(View.VISIBLE);
        String cat="";
        if(category!=null){
            cat = "&category="+category;
        }else{
            cat="";
        }
        String url= "https://newsapi.org/v2/top-headlines?country=in"+cat+"&"+"apiKey="+API_KEY;
        if(News!=null)
            News.clear();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray news = response.getJSONArray("articles");

                            for(int i=0; i<news.length(); i++){
                                JSONObject object = news.getJSONObject(i);
                                String title=object.getString("title");
                                String link=object.getString("url");
                                String image=object.getString("urlToImage");
                                News mynews= new News(title,link,image);
                                News.add(mynews);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();

                        }

                        progressBar.setVisibility(View.INVISIBLE);
                        myadapter =new Myadapter(News,getApplicationContext(), acticity);
                        recyclerView.setAdapter(myadapter);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        progressBar.setVisibility(View.INVISIBLE);
                        Toast.makeText(getApplicationContext(),String.valueOf(error.networkResponse.statusCode),Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override

            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("User-Agent", "Mozilla/5.0");
                return headers;}
        };

        MySingleton.getInstance(getApplicationContext()).
                addToRequestQueue(jsonObjectRequest);

    }

    @Override
    public void oniclick(String url,String title) {
      //  Toast.makeText(getApplicationContext(),"ho",Toast.LENGTH_SHORT).show();
        Intent i= new Intent(MainActivity.this, Detail.class);
        i.putExtra("url",url);
        i.putExtra("title",title);
        startActivity(i);
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if(toggle.onOptionsItemSelected(item))
            return true;
        int id= item.getItemId();
      //  toolbar.setTitle(item.getTitle());
        switch (id){
            case R.id.tech:
//                viewPager.setCurrentItem(2);
                tabLayout.selectTab(tabLayout.getTabAt(1));
      //tabLayout.setVerticalScrollbarPosition();
                return true;

            case R.id.enter:
//                viewPager.setCurrentItem(4);
                tabLayout.selectTab(tabLayout.getTabAt(3));
                return true;
            case R.id.sports:
//                viewPager.setCurrentItem(7);
                tabLayout.selectTab(tabLayout.getTabAt(6));
                return true;
            case R.id.science:
//                viewPager.setCurrentItem(6);
                tabLayout.selectTab(tabLayout.getTabAt(5));
                return true;
            case R.id.health:
//                viewPager.setCurrentItem(5);
                tabLayout.selectTab(tabLayout.getTabAt(4));
                return true;
            case R.id.buss:
//                viewPager.setCurrentItem(3);
                tabLayout.selectTab(tabLayout.getTabAt(2));
                return true; case R.id.home:
//                viewPager.setCurrentItem(1);
                tabLayout.selectTab(tabLayout.getTabAt(0));
                return true;
        }
             DrawerLayout drawerLayout= findViewById(R.id.drawer);
            drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


}