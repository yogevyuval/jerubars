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
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

public class Prices extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pre_prices);
		

	}

	public void onButtonClicked(View view) {
		// Is the view now checked?
		view = findViewById(R.id.gold_check);
		boolean checked = ((CheckBox) view).isChecked();
		Bundle bu = new Bundle();
		
		if (checked)
			bu.putBoolean("Goldstar", true);
		else
			bu.putBoolean("Goldstar", false);

		view = findViewById(R.id.guin_check);
		checked = ((CheckBox) view).isChecked();

		if (checked)
			bu.putBoolean("Guiness", true);
		else
			bu.putBoolean("Guiness", false);

		view = findViewById(R.id.vodkcheck);
		checked = ((CheckBox) view).isChecked();

		if (checked)
			bu.putBoolean("Vodka", true);
		else
			bu.putBoolean("Vodka", false);

		view = findViewById(R.id.chipsCheck);
		checked = ((CheckBox) view).isChecked();

		if (checked)
			bu.putBoolean("Chips", true);
		else
			bu.putBoolean("Chips", false);
		
		Intent intent = new Intent(getApplicationContext(),PricesList.class);
		intent.putExtras(bu);
		startActivity(intent);
	}

}