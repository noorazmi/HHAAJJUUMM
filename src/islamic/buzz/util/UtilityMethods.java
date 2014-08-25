package islamic.buzz.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class UtilityMethods
{
    /** This opens a given url in external browser **/
    public static final void openURLInExternalBrowser(String url, final Context context){
	context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse((!url.startsWith("http://") && !url.startsWith("https://"))?("http://" + url):url)));
    }
}
