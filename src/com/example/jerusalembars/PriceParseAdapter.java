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

class PriceParseAdapter extends BaseAdapter {
	private Activity activity;
	private List<ParseObject> data;
	private static LayoutInflater inflater = null;
	private List<ParseObject> id;
	private int max;

	// public ImageLoader imageLoader;

	public PriceParseAdapter(Activity a, List<ParseObject> bars,
			List<ParseObject> id) {
		this.activity = a;
		this.data = bars;
		this.id = id;
		PriceParseAdapter.inflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		int curr = 0;
		for (int i = 0; i < bars.size(); i++) {
			curr = bars.get(i).getString("name").length();
			if (curr > max)
				max = curr;
		}

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
			vi = inflater.inflate(R.layout.price_row, null);

		TextView first = (TextView) vi.findViewById(R.id.Goldstar);
		TextView second = (TextView) vi.findViewById(R.id.Guiness);
		TextView third = (TextView) vi.findViewById(R.id.Vodka);
		TextView fourth = (TextView) vi.findViewById(R.id.Chips);
		TextView fifth = (TextView) vi.findViewById(R.id.fifth);

		TextView name = (TextView) vi.findViewById(R.id.prices_name);

		ParseObject bar = data.get(position);
		JSONObject json = bar.getJSONObject("m");

		try {
			// Setting all values in listview
			second.setText(id.size() + "");
			for (int i = 0; i < id.size(); i++) {
				switch (i) {
				case 0:
					first.setText(json.getInt(id.get(0).getObjectId()) + "");
					break;
				case 1:
					second.setText(json.getInt(id.get(1).getObjectId()) + "");
					break;
				case 2:
					third.setText(json.getInt(id.get(2).getObjectId()) + "");
					break;
				case 3:
					fourth.setText(json.getInt(id.get(3).getObjectId()) + "");
					break;
				case 4:
					fifth.setText(json.getInt(id.get(4).getObjectId()) + "");
					break;

				default:
					break;
				}
			}

		} catch (Exception e) {
			first.setText("loading");

		}
		name.setText(bar.get("name") + space_gen(max-bar.getString("name").length()+1));

		// imageLoader.DisplayImage(song.get(CustomizedListView.KEY_THUMB_URL),
		// thumb_image);
		return vi;
	}

	public List<ParseObject> getData() {
		return this.data;
	}
	
	private static String space_gen(int curr){
		String ret = "";
		for (int i = 0; i < curr; i++) {
			ret+=" ";
		}
		return ret;
	}

}
