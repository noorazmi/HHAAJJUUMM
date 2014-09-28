
package islamic.buzz.activities;

import islamic.buzz.helpers.ActionBarHelper;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;

import com.eybsolution.islamic.buzz.R;

public class ErrorScreenActivity extends Activity implements OnClickListener {
    private ActionBarHelper mActionBarHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_frame);
        FrameLayout contentLayout = (FrameLayout) findViewById(R.id.content_detail);
        View contentView = getLayoutInflater().inflate(R.layout.error_screen, null);
        contentLayout.addView(contentView);
        initActionBar();
        ((Button) findViewById(R.id.id_errorSceen_ok)).setOnClickListener(this);
    }

    protected void initActionBar() {
        mActionBarHelper = new ActionBarHelper(this, getActionBar());
        mActionBarHelper.initActionBarWithCustomView();
        mActionBarHelper.showActionBarForErrorActivity();
    }

    @Override
    public void onBackPressed() {
        System.exit(0);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.id_errorSceen_ok) {
            System.exit(0);
        }
    }
}
