package islamic.buzz.view.adapter;

import islamic.buzz.po.MenuCategory;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.eybsolution.islamic.buzz.R;

/**
 * Left menu adapter for displaying the menu contents
 */
public class LeftMenuListAdapter extends ArrayAdapter<MenuCategory> {
	LayoutInflater mLayoutInflator;

	ArrayList<MenuCategory> categoryList;
	private Context mContext;

	public LeftMenuListAdapter(Context context, int textViewResourceId, ArrayList<MenuCategory> categoryList) {
		super(context, textViewResourceId, categoryList);
		mContext = context;
		this.categoryList = categoryList;
		mLayoutInflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public void setListofCategories(ArrayList<MenuCategory> categoryList) {
		this.categoryList = categoryList;
	}

	public ArrayList<MenuCategory> getListofCategories() {
		return categoryList;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder holder = null;

		if (convertView == null) {
			holder = new ViewHolder();
			convertView = mLayoutInflator.inflate(R.layout.drawer_list_item, parent, false);
			holder.menuItemText = (TextView) convertView.findViewById(R.id.id_navigationDrawer_menuItemTxt);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.menuItemText.setText(categoryList.get(position).getName());
		return convertView;
	}

	@Override
	public int getCount() {
		return categoryList.size();
	}

	private final static class ViewHolder {
		private TextView menuItemText;
	}

}
