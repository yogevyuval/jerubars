package com.example.jerusalembars;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;

class PrePriceParseAdapter extends BaseAdapter {
	private Activity activity;
	private List<ParseObject> data;
	private static LayoutInflater inflater = null;

	// public ImageLoader imageLoader;

	public PrePriceParseAdapter(Activity a, List<ParseObject> bars) {
		this.activity = a;
		this.data = bars;
		PrePriceParseAdapter.inflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		// max = getLongestName();
		// fixNames();
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View vi = convertView;
		if (convertView == null)
			vi = inflater.inflate(R.layout.pre_price_row, null);
		final ImageView image = (ImageView) vi
				.findViewById(R.id.pre_price_image);
		TextView name = (TextView) vi.findViewById(R.id.pre_price_name);

		ParseObject product = data.get(position);
		name.setText(product.get("name") + "");

		ParseFile file = product.getParseFile("photo");
		file.getDataInBackground(new GetDataCallback() {

			@Override
			public void done(byte[] data, ParseException e) {
				Bitmap bmp = BitmapFactory
						.decodeByteArray(data, 0, data.length);
				image.setImageBitmap(bmp);
			}
		});

		return vi;
	}

	public List<ParseObject> getData() {
		return this.data;
	}

}
