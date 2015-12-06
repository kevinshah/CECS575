package com.example.kevin.unisalestorm;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kevin.unisalestoem.R;

import java.util.ArrayList;
import java.util.List;

public class ListViewAdapter extends BaseAdapter {

	// Declare Variables
	Context context;
	LayoutInflater inflater;
	ImageLoader imageLoader;
	private List<ListHelper> listHelper = null;
	private ArrayList<ListHelper> arraylist;
	String username;

	public ListViewAdapter(Context context,
			List<ListHelper> worldpopulationlist, String username) {
		this.context = context;
		this.listHelper = worldpopulationlist;
		inflater = LayoutInflater.from(context);
		this.arraylist = new ArrayList<ListHelper>();
		this.arraylist.addAll(worldpopulationlist);
		imageLoader = new ImageLoader(context);
		this.username = username;
	}

	public class ViewHolder {
		TextView itemName;
		TextView category;
		TextView description;
		ImageView flag;
	}

	@Override
	public int getCount() {
		return listHelper.size();
	}

	@Override
	public Object getItem(int position) {
		return listHelper.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public View getView(final int position, View view, ViewGroup parent) {
		final ViewHolder holder;
		if (view == null) {
			holder = new ViewHolder();
			view = inflater.inflate(R.layout.listview_item, null);
			// Locate the TextViews in listview_item.xml
			holder.itemName = (TextView) view.findViewById(R.id.rank);
			//holder.country = (TextView) view.findViewById(R.id.country);
			//holder.population = (TextView) view.findViewById(R.id.population);
			// Locate the ImageView in listview_item.xml
			holder.flag = (ImageView) view.findViewById(R.id.flag);
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}
		// Set the results into TextViews
		holder.itemName.setText(listHelper.get(position).getItemName());
		//holder.country.setText(worldpopulationlist.get(position).getCountry());
		//holder.population.setText(worldpopulationlist.get(position).getPopulation());
		// Set the results into ImageView
		imageLoader.DisplayImage(listHelper.get(position).getPhoto(), holder.flag);
		// Listen for ListView Item Click
		view.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// Send single item click data to SingleItemView Class
				Intent intent = new Intent(context, SingleItemView.class);
				// Pass all data rank
				intent.putExtra("itemDatabase", (listHelper.get(position).getItemName()));
				// Pass all data country
				intent.putExtra("category", (listHelper.get(position).getCategory()));
				// Pass all data population
				intent.putExtra("description", (listHelper.get(position).getDescription()));
				// Pass all data flag
				intent.putExtra("flag", (listHelper.get(position).getPhoto()));
				intent.putExtra("username", username);
				// Start SingleItemView Class
				context.startActivity(intent);
			}
		});
		return view;
	}

}
