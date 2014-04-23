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

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQuery.CachePolicy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Prices extends Activity {
	ListView list;
	PrePriceParseAdapter adapter;
	ParseQuery<ParseObject> query;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pre_price_list);

		list = (ListView) findViewById(R.id.pre_price_list_list);
		query = ParseQuery.getQuery("Product");
		query.setCachePolicy(CachePolicy.CACHE_THEN_NETWORK);
		query.findInBackground(new FindCallback<ParseObject>() {
			public void done(List<ParseObject> products, ParseException e) {

				if (e == null) {
					adapter = new PrePriceParseAdapter(Prices.this, products);
					list.setAdapter(adapter);
					Log.d("bar", "Retrieved " + products.size() + " bars menus");
				} else {
					Log.d("bar", "Error: " + e.getMessage());
				}
			}
		});

	}

	public void onButtonClicked(View view) {
		// TODO:Check that he doesnt choose more than 5 products
		// Is the view now checked?
		Bundle bu = new Bundle();
		ParseObject par;
		int counter = 0;
		for (int i = 0; i < list.getChildCount(); i++) {
			view = list.getChildAt(i).findViewById(R.id.pre_price_check);
			boolean checked = ((CheckBox) view).isChecked();
			if (checked) {
				par = adapter.getData().get(i);
				bu.putString(i + "", par.getObjectId());
				counter++;
			}
		}
		if (counter > 5) {
			Toast.makeText(getApplicationContext(),
					"You have chosen more than 5 products", Toast.LENGTH_LONG)
					.show();
		} else {
			bu.putInt("Length", list.getChildCount());

			Intent intent = new Intent(getApplicationContext(),
					PricesList.class);
			intent.putExtras(bu);
			startActivity(intent);
		}
	}

}