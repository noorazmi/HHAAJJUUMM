
package islamic.buzz.view.adapter;

import islamic.buzz.po.MenuCategory;
import islamic.buzz.util.FontUtils;
import islamic.buzz.util.UtilityMethods;

import java.util.ArrayList;

import com.eybsolution.islamic.buzz.R;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


/**
 * Flyout menu adapter for displaying the menu contents
 */
public class LeftMenuListAdapter extends ArrayAdapter<MenuCategory> {
    LayoutInflater mLayoutInflator;

    ArrayList<MenuCategory> categoryList;
    private Context mContext;

    public LeftMenuListAdapter(Context context,
            int textViewResourceId,
            ArrayList<MenuCategory> categoryList) {
        super(context, textViewResourceId, categoryList);
        mContext = context;
        this.categoryList = categoryList;
        mLayoutInflator =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setListofCategories(ArrayList<MenuCategory> categoryList) {
        this.categoryList = categoryList;
    }

    public ArrayList<MenuCategory> getListofCategories() {
        return categoryList;
    }

    @Override
    public View getView(int position,
            View convertView,
            ViewGroup parent) {

        ViewHolder holder = null;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mLayoutInflator.inflate(R.layout.drawer_list_item, null);
            
            holder.textMenuItem =
                    (TextView) convertView.findViewById(R.id.id_navigationDrawer_menuItemTxt);

            holder.imageViewItem =
                    (ImageView) convertView.findViewById(R.id.id_navigationDrawer_imageView);

            holder.progressBar =
                    (ProgressBar) convertView.findViewById(R.id.id_navigationDrawer_progress);

            holder.listDivider =
                    (ImageView) convertView.findViewById(R.id.id_navigationDrawer_divider);

            // load Fonts required for the menu
            holder.mGothamBold = FontUtils.loadTypeFace(getContext(), FontUtils.Gotham_Bold);
            holder.mGothamBook = FontUtils.loadTypeFace(getContext(), FontUtils.Gotham_Book);

            convertView.setTag(holder);
        }// end If
        else {
            holder = (ViewHolder) convertView.getTag();
        } // end else

        // If the menu is being drawn, it means the items have been downloaded
        // hence set the progressbar to GONE
        holder.progressBar.setVisibility(View.GONE);

        // Yes2You Rewards/ Category like that may have image instead of text
        if (categoryList.get(position).getImage() != null && categoryList.get(position)
                .getImage()
                .length() > 0) {

            Drawable imageDrawable =
                    getContext().getResources().getDrawable(getContext().getResources()
                            .getIdentifier(categoryList.get(position).getImage(),
                                    "drawable",
                                    getContext().getPackageName()));

            // Set the ImageView to visible and Textview to GONE
            holder.imageViewItem.setVisibility(View.VISIBLE);
            holder.textMenuItem.setVisibility(View.GONE);

            holder.imageViewItem.setImageDrawable(imageDrawable);

            // set proper padding based on Requirment document
            int paddingLeft =
                    (int) (getContext().getResources()
                            .getDimension(R.dimen.navigation_drawer_text_paddingLeft) / getContext().getResources()
                            .getDisplayMetrics().density);

            holder.imageViewItem.setPadding(paddingLeft,
                    holder.imageViewItem.getPaddingTop(),
                    holder.imageViewItem.getPaddingRight(),
                    holder.imageViewItem.getPaddingBottom());

        } else {

            // The SpannableBuilder which will be used to make the text
            // formatting
            // before adding into textview
            SpannableStringBuilder spanString;

            // the Category isnt a Image , hence the textview is VISIBLE and
            // imageView is GONE
            holder.imageViewItem.setVisibility(View.GONE);
            holder.textMenuItem.setVisibility(View.VISIBLE);

            spanString = new SpannableStringBuilder(categoryList.get(position).getName());

            // set Fonts for the text
            if (categoryList.get(position)
                    .getName()
                    .equalsIgnoreCase(getContext().getString(R.string.home)) || categoryList.get(position)
                    .getType() == MenuCategory.TYPE_BOLD
                    || categoryList.get(position)
                            .getName()
                            .equalsIgnoreCase(getContext().getString(R.string.home))) {
                holder.textMenuItem.setTypeface(holder.mGothamBold);
            }// END IF
            else {
                holder.textMenuItem.setTypeface(holder.mGothamBook);
            } // END ELSE

            // If image present (ie HOME, MENU), then the padding on the left
            // will be considered according to the image width
            int paddingLeft = 0;

            // Set image in the left hand side
            if (categoryList.get(position)
                    .getName()
                    .equalsIgnoreCase(getContext().getString(R.string.home))) {
            	
            	///Set the top row height of the menu equal to the height of the action bar.
            	int actionBarHeight = UtilityMethods.getActionBarHeight(getContext());
            	if(actionBarHeight <= 0){
            		
            		actionBarHeight = (int) getContext().getResources().getDimension(R.dimen.actionbar_height);
            	}
            	LayoutParams layoutParams = holder.imageViewItem.getLayoutParams();
            	layoutParams.height = actionBarHeight;
            	
            	layoutParams = holder.textMenuItem.getLayoutParams();
            	layoutParams.height = actionBarHeight;
            	
            	
                // Show Home Image
                holder.textMenuItem.setCompoundDrawablesWithIntrinsicBounds(R.drawable.home_white,
                        0,
                        0,
                        0);

                // Calcuate the padding on the left, since there is an image,
                // the padding is different from the non image textview ones.
                paddingLeft =
                        (int) (getContext().getResources()
                                .getDimension(R.dimen.navigation_drawer_texthomeimage_paddingLeft) / getContext().getResources()
                                .getDisplayMetrics().density);

                // Home has different textsize
                holder.textMenuItem.setTextSize(getContext().getResources()
                        .getDimension(R.dimen.navigation_drawer_home_text_size) / getContext().getResources()
                        .getDisplayMetrics().density);
            } // end if
            else if (categoryList.get(position)
                    .getName()
                    .equalsIgnoreCase(getContext().getString(R.string.home))) {
                // Show Home Image
                holder.textMenuItem.setCompoundDrawablesWithIntrinsicBounds(R.drawable.back_icon,
                        0,
                        0,
                        0);

                // Calcuate the padding on the left, since there is an image,
                // the padding is different from the non image textview ones.
                paddingLeft =
                        (int) (getContext().getResources()
                                .getDimension(R.dimen.navigation_drawer_textbackimage_paddingLeft) / getContext().getResources()
                                .getDisplayMetrics().density);
            } // end Else if
            else {
                holder.textMenuItem.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                holder.textMenuItem.setTextSize(getContext().getResources()
                        .getDimension(R.dimen.navigation_drawer_text_size) / getContext().getResources()
                        .getDisplayMetrics().density);

                paddingLeft =
                        (int) (getContext().getResources()
                                .getDimension(R.dimen.navigation_drawer_text_paddingLeft) / getContext().getResources()
                                .getDisplayMetrics().density);
            }

            holder.textMenuItem.setPadding(paddingLeft,
                    holder.textMenuItem.getPaddingTop(),
                    holder.textMenuItem.getPaddingRight(),
                    holder.textMenuItem.getPaddingBottom());

            // If highlighted then set set background resource and textcolor
            if (categoryList.get(position).getType() == MenuCategory.TYPE_HIGHLIGHT) {
                holder.textMenuItem.setBackgroundResource(R.drawable.selected_background);
                holder.listDivider.setVisibility(View.GONE);
                holder.textMenuItem.setTextColor(Color.WHITE);
            } // end if
            else if (categoryList.get(position).getType() == MenuCategory.TYPE_GRAY_BACKGROUND) {
                holder.textMenuItem.setBackgroundResource(R.drawable.gray_background);
                holder.listDivider.setVisibility(View.GONE);
                holder.textMenuItem.setTextColor(Color.BLACK);
            }// end else if
            else {
                holder.listDivider.setVisibility(View.VISIBLE);
                holder.textMenuItem.setBackgroundResource(R.drawable.nonselected_background);
                holder.textMenuItem.setTextColor(Color.BLACK);

            }

            // Divider will not be shown for the item whose next item is a
            // highkighted item too.
            if (position < categoryList.size() - 1 && categoryList.get(position + 1).getType() == MenuCategory.TYPE_HIGHLIGHT) {
                holder.listDivider.setVisibility(View.GONE);

            }

            holder.textMenuItem.setText(spanString);

        }

        Animation anim = AnimationUtils.loadAnimation(getContext(), android.R.anim.slide_in_left);
        anim.setDuration(500);

        convertView.startAnimation(anim);
        anim = null;

        return convertView;
    }

    @Override
    public int getCount() {
        return categoryList.size();
    }

    static class ViewHolder {

        // Imageview for Yes2Rewards type of list item where we would show an
        // Image
        private ImageView imageViewItem;

        // Textview for the text
        private TextView textMenuItem;

        // List Divider
        private ImageView listDivider;

        private ProgressBar progressBar;

        private Typeface mGothamBook;

        private Typeface mGothamBold;

    }

}
