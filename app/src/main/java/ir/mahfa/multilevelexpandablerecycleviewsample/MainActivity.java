package ir.mahfa.multilevelexpandablerecycleviewsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String json = "[{'name':'CategoryA','categories':[{'name':'CategoryB','categories':[{'name':'CategoryC'},{'name':'CategoryD'},{'name':'CategoryE'},{'name':'CategoryF'}]},{'name':'CategoryG','categories':[]},{'name':'CategoryH','categories':[]}]},{'name':'CategoryI','categories':[{'name':'CategoryB','categories':[]},{'name':'CategoryJ','categories':[]}]}]";
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        ArrayList<Categories> categories = new ArrayList<>(Arrays.asList(gson.fromJson(json, Categories[].class)));
        categoriesAdapter adapter = new categoriesAdapter(this, categories, 0);

        RecyclerView recycler_content = findViewById(R.id.recycler_content);
        recycler_content.setLayoutManager(new LinearLayoutManager(this));
        recycler_content.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recycler_content.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        adapter.expandGroup(0,0);
    }
}
