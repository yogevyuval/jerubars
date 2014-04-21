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

import java.util.Date;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;

/**
 * This is the activity for feature 1 in the dashboard application. It displays
 * some text and provides a way to get back to the home activity.
 * 
 */

public class HappyHourBars extends BarsList {

	protected void onCreate(Bundle savedInstanceState) 
	{
	    super.onCreate(savedInstanceState);
	}

	@Override
	protected void filterQuery() {
		String curTimeString = DateFormat.format("HH:mm", new Date().getTime()).toString();
		query.whereLessThanOrEqualTo("happy_hour_begin", curTimeString);
		query.whereGreaterThanOrEqualTo("happy_hour_end", curTimeString);
	}
}