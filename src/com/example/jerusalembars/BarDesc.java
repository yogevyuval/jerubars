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

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class BarDesc extends Activity {

	private Button get_directions;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bar_page);

		TextView barName = (TextView) findViewById(R.id.bar_name);
		barName.setText(this.getIntent().getExtras().getString("name"));

		TextView barAdress = (TextView) findViewById(R.id.bar_location);
		barAdress.setText(this.getIntent().getExtras().getString("address"));

		TextView desc = (TextView) findViewById(R.id.bar_desc);
		desc.setText(this.getIntent().getExtras().getString("description"));

		ImageView img = (ImageView) findViewById(R.id.bar_image);
		
		get_directions = (Button) findViewById(R.id.get_directions);
		get_directions.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				GPSTracker tracker = new GPSTracker(BarDesc.this);
			    if (tracker.canGetLocation() == false) {
			        tracker.showSettingsAlert();
			    } else {
			        double target_lat = getIntent().getExtras().getDouble("lat");
			        double target_long = getIntent().getExtras().getDouble("long");
			        //String url = "http://maps.google.com/maps?f=d&daddr=hataklit";
			        String url = "http://maps.google.com/maps?f=d&daddr=" + target_lat + "," + target_long + "(Abc)";
					Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(url));
					intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
					startActivity(intent);
			    }
			}
		});
	}

} // end class
