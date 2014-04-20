package com.example.jerusalembars;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQuery.CachePolicy;


public abstract class BarsList extends Activity{

	ListView list;
	ParseAdapter adapter;
    ParseQuery<ParseObject> query;

	protected void onCreate(Bundle savedInstanceState) 
	{
	    super.onCreate(savedInstanceState);
	    setContentView (R.layout.all_bars);
	    
        list = (ListView)findViewById(R.id.list);
        query = ParseQuery.getQuery("Bar");
        query.setCachePolicy(CachePolicy.CACHE_THEN_NETWORK);
        filterQuery();
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> bars, ParseException e) {
                if (e == null) {
                	adapter = new ParseAdapter(BarsList.this, bars);
                    list.setAdapter(adapter);
                    Log.d("bar", "Retrieved " + bars.size() + " bars");
                } else {
                    Log.d("barerror", "Error: " + e.getMessage());
                }
            }
        });
        
        // Click event for single list row
        list.setOnItemClickListener(new OnItemClickListener() {
 
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				
				ParseObject parseObj = adapter.getData().get(position);
				Intent intent = new Intent(getApplicationContext(),BarDesc.class);
				Bundle bu = createBundle(parseObj);
				intent.putExtras(bu);
				startActivity(intent);
				finish();
                Log.d("bar", "Clicked"  + position); 
			}
        });
    }
	
	public static Bundle createBundle(ParseObject parseObj){
		Bundle bu = new Bundle();
		bu.putString("name", parseObj.getString("name"));
		bu.putString("address", parseObj.getString("address"));
		bu.putString("description", parseObj.getString("description"));
		return bu;
	}

	abstract protected void filterQuery();
}
