
package com.jeremyfeinstein.slidingmenu.lib.view;

import android.content.Context;
import android.widget.ListView;
import android.widget.PopupWindow;

public class ListPopupMenu extends PopupWindow {

    private Context mContext;

    private ListView mListView;

    private ListViewAdapter mAdapter;

    public ListPopupMenu(Context ctx,
            int itemLayoutRes,
            int textViewId,
            String... texts) {
        super(ctx);
        init(ctx, new ListViewAdapter(ctx, itemLayoutRes, textViewId, texts));
    }

    private void init(Context ctx,
            ListViewAdapter adapter) {
        // mContext = ctx;
        // setWidth(WindowUtils.getDisplayMetrics(ctx).widthPixels / 2);
        // setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        // View popView = LayoutInflater.from(ctx).inflate(R.layout.popup_menu,
        // null);
        // setContentView(popView);
        // mListView = (ListView) popView.findViewById(R.id.menu_list);
        // setBackgroundDrawable(new BitmapDrawable(ctx.getResources()));
        // // mListView.setDividerHeight(0);
        // mAdapter = adapter;
        // mListView.setAdapter(mAdapter);
        // setFocusable(true);
        // popView.setOnFocusChangeListener(new OnFocusChangeListener() {
        // public void onFocusChange(View v, boolean hasFocus) {
        // if (!hasFocus && isShowing()) {
        // dismiss();
        // }
        // }
        // });
    }

    public ListView getListView() {
        return mListView;
    }

    public ListViewAdapter getAdapter() {
        return mAdapter;
    }

    public Context getContext() {
        return mContext;
    }

}
