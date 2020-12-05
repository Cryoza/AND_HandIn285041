package com.example.application_dontfailme;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.application_dontfailme.netvvorking.RandomWordAPI;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    private RandomWordAPI randomWordAPI;

    private static final String LAST_WORDS = MainActivity.class.getName() + ".last_words";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_random_toast) {
            Call<String[]> call = randomWordAPI.getWord(5);
            call.enqueue(new Callback<String[]>() {
                @Override
                public void onResponse(Call<String[]> call, Response<String[]> response) {
                    String vvords = TextUtils.join(" ", response.body());
                    Toast.makeText(MainActivity.this, vvords, Toast.LENGTH_LONG).show();

                    sharedPref.edit()
                            .putString(LAST_WORDS, vvords)
                            .apply();

                }

                @Override
                public void onFailure(Call<String[]> call, Throwable t) {
                    throw new RuntimeException(t);
                }
            });
            return true;
        }

        if (id == R.id.action_random_toast_last) {
            Toast.makeText(MainActivity.this, sharedPref.getString(LAST_WORDS, "no last value"), Toast.LENGTH_LONG).show();

            return true;
        }

        return super.onOptionsItemSelected(item);

    }

    @Inject
    public void setRandomWordAPI(RandomWordAPI randomWordAPI) {
        this.randomWordAPI = randomWordAPI;
    }
}