package com.example.jerusalembars;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
	private int max ;
	//public ImageLoader imageLoader; 
	
	public PriceParseAdapter(Activity a, List<ParseObject> bars) {
		this.activity = a;
		this.data = bars;
		PriceParseAdapter.inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		max = getLongestName();
		fixNames();
	}
	
	@Override
	public int getCount() {
		if(! data.equals(null))
			return data.size();
		return 0;
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
		View vi=convertView;
		if(convertView==null)
			vi = inflater.inflate(R.layout.price_row, null);
		
		TextView goldstar = (TextView)vi.findViewById(R.id.Goldstar); 
		TextView guiness = (TextView)vi.findViewById(R.id.Guiness);
		TextView vodka = (TextView)vi.findViewById(R.id.Vodka);
		TextView chips = (TextView)vi.findViewById(R.id.Chips);
		TextView name = (TextView)vi.findViewById(R.id.prices_name);

		
		ParseObject bar = data.get(position);
		// Setting all values in listview
		goldstar.setText(bar.get("Goldstar")+"");
		guiness.setText(bar.get("Guiness") +"");
		vodka.setText(bar.get("Vodka") +"");
		chips.setText(bar.get("Chips") +"");
		name.setText(bar.get("Name")+"");

		
		
		//imageLoader.DisplayImage(song.get(CustomizedListView.KEY_THUMB_URL), thumb_image);
		return vi;
	}
	
	public List<ParseObject> getData(){
		return this.data;
	}
	
	public int getLongestName(){
		int max = 0;
		int current = 0;
		for (int i = 0; i < data.size(); i++) {
			current = (data.get(i).get("Name")+"").length();
			if(current > max)
				max = current;
		}
		return max;
	}
	
	public void fixNames(){
		int curr_len = 0;
		for (int i = 0; i<data.size(); i++){
			curr_len = (data.get(i).get("Name")+"").length();
			data.get(i).put("Name", (data.get(i).get("Name")+"")+space_gen(max-curr_len));
		}
	}
	public String space_gen(int amount){
		String ret = "";
		for( int i = 0;i<amount;i++){
			ret+=" ";
		}
		return ret;
	}
}