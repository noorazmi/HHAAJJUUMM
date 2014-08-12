package islamic.buzz.views;

import com.bitstacksolutions.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DuaView extends LinearLayout
{

    public DuaView(Context context, AttributeSet attrs)
    {
	super(context, attrs);
	
	
	//LinearLayout.LayoutParams lp = new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
	
	//setLayoutParams(lp);
	LayoutInflater.from(context).inflate(R.layout.dua, this);
	TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.dua_styleable, 0, 0);
	String dua_heading = typedArray.getString(R.styleable.dua_styleable_dua_heading);
	Drawable dua_image = typedArray.getDrawable(R.styleable.dua_styleable_dua_image);
	String dua_pronumciation = typedArray.getString(R.styleable.dua_styleable_dua_pronunciation);
	String dua_meaning = typedArray.getString(R.styleable.dua_styleable_dua_meaning);
	
	TextView tv = (TextView) findViewById(R.id.dua_heading);
	tv.setText(dua_heading);
	tv = (TextView) findViewById(R.id.dua_pronumciation);
	tv.setText(dua_pronumciation);
	tv = (TextView) findViewById(R.id.dua_meaning);
	tv.setText(dua_meaning);
	
	ImageView iv = (ImageView) findViewById(R.id.dua_image);
	iv.setImageDrawable(dua_image);
	
    }
   

}
