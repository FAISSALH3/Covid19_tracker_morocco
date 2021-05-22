package com.example.covid19trackerv2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.covid19trackerv2.api.ApiUtilities;
import com.example.covid19trackerv2.api.ApiUtilities2;
import com.example.covid19trackerv2.api.CountryData;
import com.google.gson.annotations.SerializedName;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView TotalConfirm , TotalActive , TotalRecovered , TotalDeaths , serious;
    private TextView TodayConfirm , TodayRecovered , TodayDeaths ;
    private List<CountryData> list;
    //private List<CountryData> list2;
    private PieChart pieChart ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);
        list = new ArrayList<>();
        //list2 = new ArrayList<>();

        init();

        findViewById(R.id.global).setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, CountryActivity.class)));


        ApiUtilities.getApiInterface().getCountryData()
                .enqueue(new Callback<List<CountryData>>() {
                    @Override
                    public void onResponse(Call<List<CountryData>> call, Response<List<CountryData>> response) {

                        list.addAll(response.body());
                        for(int i =0 ; i<list.size() ; i++){
                            if(list.get(i).getCountry().equals("Morocco")){
                                int confirm = Integer.parseInt(list.get(i).getCases());
                                int Active = Integer.parseInt( list.get(i).getActive());
                                int deaths = Integer.parseInt( list.get(i).getDeaths());
                                int recovered = Integer.parseInt( list.get(i).getRecovered());
                                //int critical =  Integer.parseInt( list.get(i).getCritical());


                                TotalActive.setText(NumberFormat.getInstance().format(Active));
                                TotalConfirm.setText(NumberFormat.getInstance().format(confirm));
                                TotalDeaths.setText(NumberFormat.getInstance().format(deaths));
                                TotalRecovered.setText(NumberFormat.getInstance().format(recovered));

                                //serious.setText(NumberFormat.getInstance().format(critical));
                                TodayDeaths.setText(NumberFormat.getInstance().format(Integer.parseInt(list.get(i).getTodayDeaths())));
                                TodayConfirm.setText(NumberFormat.getInstance().format(Integer.parseInt(list.get(i).getTodayCases())));
                                TodayRecovered.setText(NumberFormat.getInstance().format(Integer.parseInt(list.get(i).getTodayRecovered())));
                               // TotalTests.setText(NumberFormat.getInstance().format(Integer.parseInt(list.get(i).getTests())));

                                //setText(list.get(i).getUpdated());

                                pieChart.addPieSlice(new PieModel("confirm" , confirm , getResources().getColor(R.color.affected_rectangle_color)));
                                pieChart.addPieSlice(new PieModel("active" , Active , getResources().getColor(R.color.recovered_rectangle_color)));
                                pieChart.addPieSlice(new PieModel("deaths" , deaths , getResources().getColor(R.color.death_rectangle_color)));
                                pieChart.addPieSlice(new PieModel("recovered" , recovered , getResources().getColor(R.color.recovered_rectangle_color)));
                                pieChart.startAnimation();


                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<List<CountryData>> call, Throwable t) {
                        Toast.makeText(MainActivity.this , "Error : "+t.getMessage() , Toast.LENGTH_SHORT).show();

                    }
                });


       /* ApiUtilities2.getApiInterface2().getCountryData()
                .enqueue(new Callback<List<CountryData>>() {
                    @Override
                    public void onResponse(Call<List<CountryData>> call, Response<List<CountryData>> response) {
                        list2.addAll(response.body());
                        for (int i = 0; i < list2.size(); i++) {

                            if (list2.get(i).getCountry().equals("Morocco")) {


                                int vaccination = Integer.parseInt(list2.get(i).getDate());
                                System.out.println(vaccination);
                                TotalVaccinated.setText(NumberFormat.getInstance().format(vaccination));

                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<List<CountryData>> call, Throwable t) {
                        Toast.makeText(MainActivity.this , "Error : "+t.getMessage() , Toast.LENGTH_SHORT).show();
                    }
                });*/
    }






   /* private void setText(String updated){
        DateFormat  format = new SimpleDateFormat("MM dd , yyyy ");
        long milliseconds  = Long.parseLong(updated);
        Calendar calendar =Calendar.getInstance();
        calendar.setTimeInMillis(milliseconds);
        date.setText("Updated at "+format.format(calendar.getTime()));
    }*/

    private void init(){
        TotalConfirm = findViewById(R.id.TotalConfirm);
        TotalRecovered = findViewById(R.id.TotalRecovered);
        TotalDeaths = findViewById(R.id.TotalDeaths);
        TotalActive = findViewById(R.id.TotalActive);
        //TotalTests = findViewById(R.id.TotalTests);
        TodayRecovered = findViewById(R.id.TodayRecovered);
        TodayDeaths = findViewById(R.id.TodayDeaths);
       // TotalVaccinated = findViewById(R.id.TotalVaccinated);
        TodayConfirm = findViewById(R.id.TodayConfirm);
        pieChart = findViewById(R.id.pieChart);
        // date = findViewById(R.id.date);
        //serious.findViewById(R.id.critical);

    }
}