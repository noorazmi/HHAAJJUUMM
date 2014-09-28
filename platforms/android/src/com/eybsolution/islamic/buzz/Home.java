package com.eybsolution.islamic.buzz;

import islamic.buzz.sidemenu.ContentWindow;
import islamic.buzz.sidemenu.LeftSideMenu;
import islamic.buzz.views.AboutView;
import islamic.buzz.views.HajjView;
import islamic.buzz.views.UmrahView;
import islamic.buzz.views.UpdatesView;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Home extends FragmentActivity
{
    private FrameLayout container;
    private ContentWindow contentWindow;
    private LinearLayout contentHolder;
    private LinearLayout contentView;
    private UmrahView umrahView;
    private UpdatesView updatesView;
    private HajjView hajjView;
    private AboutView aboutView;
    private LeftSideMenu leftSideMenu;
    private View currentView;

    private static final String UPDATES = "Updates";
    private static final String UMRAH = "Umrah";
    private static final String HAJJ = "Hajj";
    private static final String ABOUT = "About";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_home);
	container = (FrameLayout) findViewById(R.id.container);
	contentWindow = (ContentWindow) findViewById(R.id.contnet_window);
	contentHolder = (LinearLayout) contentWindow.findViewById(R.id.content_holder);
	leftSideMenu = (LeftSideMenu) findViewById(R.id.left_side_menu);
	leftSideMenu.setOnLeftMenuItemSeletedListener(new CustomLeftMenuItemSlelectedListener());
	// FragmentManager fragmentManager = getSupportFragmentManager();
	// FragmentTransaction fragmentTransaction =
	// fragmentManager.beginTransaction();
	// fragmentTransaction.add(R.id.content_holder, new
	// UmrahViewFragment());
	// fragmentTransaction.commit();

	// umrahView = new UmrahView(this);
	updatesView = new UpdatesView(this);
	contentHolder.addView(updatesView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
	// Inflate the menu this adds items to the action bar if it is present.
	getMenuInflater().inflate(R.menu.home, menu);
	return true;
    }

    @Override
    public void onBackPressed()
    {
	if (!contentWindow.canAnimateToRight())
	{
	    contentWindow.animateLeft();
	    return;
	}
	else
	{
	    AlertDialog.Builder builder = new AlertDialog.Builder(this);
	    builder.setTitle("Exit").setMessage(getResources().getString(R.string.exit_message)).setIcon(R.drawable.app_icon_small).setPositiveButton("Yes", new DialogInterface.OnClickListener()
	    {
		public void onClick(DialogInterface dialog, int which)
		{
		    // Yes button clicked, do something
		    Toast.makeText(getApplicationContext(), "Yes button pressed", Toast.LENGTH_SHORT).show();
		   finish();
		}
	    }).setNegativeButton("No", null) // Do nothing on no
	    .show();
	}
	
    }

    public boolean onKeyUp(int keyCode, KeyEvent event)
    {
	if (keyCode == KeyEvent.KEYCODE_MENU)
	{
	    if (contentWindow.canAnimateToRight())
	    {
		contentWindow.animateRight();

	    }
	    else
	    {
		contentWindow.animateLeft();
	    }
	    return true;
	}
	return super.onKeyUp(keyCode, event);
    }

    // Returns the content holder
    public LinearLayout getContentHolder()
    {
	return contentHolder;
    }

    // Returns the main content currently showing.
    public LinearLayout getContentView()
    {
	return contentView;
    }

    /**
     * This class will listen the click events of LeftSideMenu items and will
     * change the views on the content holder accordingly.
     **/
    private class CustomLeftMenuItemSlelectedListener implements LeftSideMenu.OnLeftMenuItemSeletedListener
    {

	@Override
	public void onLeftMenuItemSelected(String menuTitle)
	{
	    Toast.makeText(Home.this, menuTitle, Toast.LENGTH_SHORT).show();
	    contentHolder.removeAllViews();
	    if (menuTitle.equalsIgnoreCase(UPDATES))
	    {
		contentHolder.addView(updatesView);
		currentView = updatesView;
	    }
	    else if (menuTitle.equalsIgnoreCase(UMRAH))
	    {
		if (umrahView == null)
		{
		    umrahView = new UmrahView(Home.this);
		}
		contentHolder.addView(umrahView);
		currentView = umrahView;

	    }
	    else if (menuTitle.equalsIgnoreCase(HAJJ))
	    {
		if (hajjView == null)
		{
		    hajjView = new HajjView(Home.this);
		}
		contentHolder.addView(hajjView);
		currentView = hajjView;

	    }
	    else if (menuTitle.equalsIgnoreCase(ABOUT))
	    {
		if(aboutView == null){
		    aboutView = new AboutView(Home.this);
		}
		
		contentHolder.addView(aboutView);
		currentView = aboutView;
		
	    }

	    // Now close the menu
	    contentWindow.animateLeft();
	}

    }

}
