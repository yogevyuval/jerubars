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
class ParseAdapter extends BaseAdapter {
	private Activity activity;
	private List<ParseObject> data;
	private static LayoutInflater inflater = null;
	//public ImageLoader imageLoader; 
	
	public ParseAdapter(Activity a, List<ParseObject> bars) {
		this.activity = a;
		this.data = bars;
		ParseAdapter.inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
			vi = inflater.inflate(R.layout.list_row, null);
		
		TextView title = (TextView)vi.findViewById(R.id.title); // title
		TextView artist = (TextView)vi.findViewById(R.id.artist); // artist name
		final ImageView thumb_image=(ImageView)vi.findViewById(R.id.list_image); // thumb image
		
		ParseObject bar = data.get(position);
		// Setting all values in listview
		title.setText(bar.get("name").toString());
		artist.setText(bar.get("address").toString());
		ParseFile file = bar.getParseFile("photo");
		file.getDataInBackground(new GetDataCallback() {
			
			@Override
			public void done(byte[] data, ParseException e) {
				Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
				thumb_image.setImageBitmap(bmp);
			}
		});
		//imageLoader.DisplayImage(song.get(CustomizedListView.KEY_THUMB_URL), thumb_image);
		return vi;
	}
	
	public List<ParseObject> getData(){
		return this.data;
	}
}