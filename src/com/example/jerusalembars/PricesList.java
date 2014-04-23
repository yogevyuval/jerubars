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

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQuery.CachePolicy;

public class PricesList extends Activity {

	ListView list;
	PriceParseAdapter adapter;
	ParseQuery<ParseObject> query;
	ParseQuery<ParseObject> whichPro;
	List<ParseObject> list_id;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.prices);
		Bundle bu = this.getIntent().getExtras();
		whichPro = new ParseQuery("Product");
		ArrayList<String> arr = new ArrayList<String>();
		for (int i = 0; i < bu.getInt("Length"); i++) {
			if (bu.getString(i + "") != null)
				arr.add((bu.getString(i + "")));
		}
		whichPro.whereContainedIn("objectId", arr);
		whichPro.setCachePolicy(CachePolicy.CACHE_THEN_NETWORK);
		// TODO: clean this with a method or something
		whichPro.findInBackground(new FindCallback<ParseObject>() {
			public void done(List<ParseObject> products, ParseException e) {
				if (products == null)
					Toast.makeText(getApplicationContext(),
							"Error, go back and try again", Toast.LENGTH_LONG)
							.show();
				else {
					list_id = products;
					for (int i = 0; i < products.size(); i++) {
						if (i == 0) {
							final ImageView v = (ImageView) findViewById(R.id.first_image);
							ParseFile file = products.get(i).getParseFile(
									"photo");
							file.getDataInBackground(new GetDataCallback() {

								@Override
								public void done(byte[] data, ParseException e) {

									Bitmap bmp = BitmapFactory.decodeByteArray(
											data, 0, data.length);
									v.setImageBitmap(bmp);
								}
							});

						} else if (i == 1) {
							final ImageView v = (ImageView) findViewById(R.id.second_image);
							ParseFile file = products.get(i).getParseFile(
									"photo");
							file.getDataInBackground(new GetDataCallback() {

								@Override
								public void done(byte[] data, ParseException e) {
									Bitmap bmp = BitmapFactory.decodeByteArray(
											data, 0, data.length);
									v.setImageBitmap(bmp);
								}
							});

						} else if (i == 2) {
							final ImageView v = (ImageView) findViewById(R.id.third_image);
							ParseFile file = products.get(i).getParseFile(
									"photo");
							file.getDataInBackground(new GetDataCallback() {

								@Override
								public void done(byte[] data, ParseException e) {
									Bitmap bmp = BitmapFactory.decodeByteArray(
											data, 0, data.length);
									v.setImageBitmap(bmp);
								}
							});

						} else if (i == 3) {
							final ImageView v = (ImageView) findViewById(R.id.fourth_image);
							ParseFile file = products.get(i).getParseFile(
									"photo");
							file.getDataInBackground(new GetDataCallback() {

								@Override
								public void done(byte[] data, ParseException e) {
									Bitmap bmp = BitmapFactory.decodeByteArray(
											data, 0, data.length);
									v.setImageBitmap(bmp);
								}
							});

						} else if (i == 4) {
							final ImageView v = (ImageView) findViewById(R.id.fifth_image);
							ParseFile file = products.get(i).getParseFile(
									"photo");
							file.getDataInBackground(new GetDataCallback() {

								@Override
								public void done(byte[] data, ParseException e) {
									Bitmap bmp = BitmapFactory.decodeByteArray(
											data, 0, data.length);
									v.setImageBitmap(bmp);
								}
							});

						}

					}
				}
				if (e == null) {

					Log.d("bar", "Retrieved " + products.size() + " bars menus");
				} else {
					Log.d("bar", "Error: " + e.getMessage());
				}
			}
		});

		list = (ListView) findViewById(R.id.listPrices);
		query = ParseQuery.getQuery("Bar");
		query.setCachePolicy(CachePolicy.CACHE_THEN_NETWORK);
		query.findInBackground(new FindCallback<ParseObject>() {
			public void done(List<ParseObject> bars, ParseException e) {

				if (e == null) {
					adapter = new PriceParseAdapter(PricesList.this, bars,
							list_id);
					list.setAdapter(adapter);
					Log.d("bar", "Retrieved " + bars.size() + " bars menus");
				} else {
					Log.d("bar", "Error: " + e.getMessage());
				}
			}
		});
		// Getting adapter by passing xml data ArrayList

		// Click event for single list row

	}

	// @Override
	// public void onBackPressed() {
	// Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
	// startActivity(intent);
	// return;
	// }

}
