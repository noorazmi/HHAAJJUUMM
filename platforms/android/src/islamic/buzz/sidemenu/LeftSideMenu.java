package islamic.buzz.sidemenu;

import islamic.buzz.leftmenu.LeftMenuItem;
import islamic.buzz.util.Const;
import islamic.buzz.util.Util;
import islamic.buzz.views.Divider;

import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.eybsolution.islamic.buzz.R;

public class LeftSideMenu extends LinearLayout
{
    private final LinearLayout scrollViewContainer;
    public final LeftMenuItemClickListener leftMenuItemClickListener;
    private OnLeftMenuItemSeletedListener onLeftMenuItemSeletedListener;

    public LeftSideMenu(Context context, AttributeSet attrs)
    {
	super(context, attrs);
	LayoutInflater.from(context).inflate(R.layout.left_side_menu, this);
	scrollViewContainer = (LinearLayout) findViewById(R.id.scroll_view_container);
	leftMenuItemClickListener = new LeftMenuItemClickListener();
	setMenu();
    }

    //Create left side menu list 
    private void setMenu()
    {
	String[] menuItemTitles = getResources().getStringArray(R.array.left_menu_titles);
	int[] menuItemIcons = {R.drawable.info_update, R.drawable.kaba,R.drawable.hajj,R.drawable.about};
	View menuItem;
	View divider;
	LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	for (int i = 0; i < menuItemTitles.length; i++)
	{
	   // menuItem = new LeftMenuItem(getContext(), menuItemIcons[i], menuItemTitles[i]);
	    //(Context context, int iconResId, String title,ImageView.ScaleType scaleType, int cornerRadius,int borderWidth, int borderColorStateList,boolean roundBackgroud, boolean isOval)

	    menuItem = new LeftMenuItem(getContext(), menuItemIcons[i], menuItemTitles[i]);
	    menuItem.setOnClickListener(leftMenuItemClickListener);
	    scrollViewContainer.addView(menuItem);
	    divider = new Divider(getContext(), R.color.menu_divider, (int)Util.getPixels(Const.LEFT_SIDE_MENU_WIDTH, getResources()),2,0/*padding left*/,0/* padding right*/);
	    scrollViewContainer.addView(divider);
	}
    }
    
    
    /** Listener to listen to clicks on left side menu item.These events will be sent to Home.java Home screen to change the views according to selections. **/
    private class LeftMenuItemClickListener implements OnClickListener{

	@Override
	public void onClick(View v)
	{
	    if(onLeftMenuItemSeletedListener != null){
		onLeftMenuItemSeletedListener.onLeftMenuItemSelected(v.getTag().toString());
	    }
	}
	
    }
    
    
    /** This interface will be implemented by the Home.java Home screen to get informed about the  left menu item selection. Method of this listener will called from {@link LeftMenuItemClickListener}**/
    public static interface OnLeftMenuItemSeletedListener{
	abstract public void onLeftMenuItemSelected(String menuTitle);
    }
    
    
    /** Set the  OnLeftMenuItemSeletedListener.Will be called from Home.js Home screen.**/
    public void setOnLeftMenuItemSeletedListener(OnLeftMenuItemSeletedListener onLeftMenuItemSeletedListener){
	this.onLeftMenuItemSeletedListener = onLeftMenuItemSeletedListener;
    }
    
    private String getXml(String path)
    {
	String xmlString = null;
	AssetManager am = getContext().getAssets();
	try
	{
	    InputStream is = am.open(path);
	    int length = is.available();
	    byte[] data = new byte[length];
	    is.read(data);
	    xmlString = new String(data);
	}
	catch (IOException e1)
	{
	    e1.printStackTrace();
	}

	return xmlString;
    }

}
