/*
 * Copyright (C) 2011 Wglxy.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.jerusalembars;

import java.util.List;

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

/**
 * This is the activity for feature 1 in the dashboard application. It displays
 * some text and provides a way to get back to the home activity.
 * 
 */

public class AllBars extends DashboardActivity {

	ListView list;
	ParseAdapter adapter;

	protected void onCreate(Bundle savedInstanceState) 
	{
	    super.onCreate(savedInstanceState);
	    setContentView (R.layout.activity_f1);
	    
        list=(ListView)findViewById(R.id.list);
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Bar");
        query.setCachePolicy(CachePolicy.CACHE_THEN_NETWORK);
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> bars, ParseException e) {
            	adapter=new ParseAdapter(AllBars.this, bars);
                list.setAdapter(adapter);
         
                if (e == null) {
                    Log.d("bar", "Retrieved " + bars.size() + " bars");
                } else {
                    Log.d("bar", "Error: " + e.getMessage());
                }
            }
        });
        // Getting adapter by passing xml data ArrayList
        
        
        // Click event for single list row
        list.setOnItemClickListener(new OnItemClickListener() {
 
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("bar", "Clicked"  + position);
			}
        });
    }
} // end class
