
package islamic.buzz.fragments;


import islamic.buzz.util.ConstantValues;
import islamic.buzz.util.Logger;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.text.TextUtils;
import android.view.KeyEvent;

/**
 * Display Dialog
 */
public class BaseDialogFragment extends DialogFragment {

    private static ProgressDialog mProgressDialog = null;

    private static String TAG = BaseDialogFragment.class.getName();

    private static AlertDialog mAlertDialog = null;

    private ResultReceiver receiver;

    public static ProgressDialog getProgressDialog() {
        return mProgressDialog;
    }

    public static void setProgressDialog(ProgressDialog mProgressDialog) {
        BaseDialogFragment.mProgressDialog = mProgressDialog;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        int dialogId = getArguments().getInt(ConstantValues.DIALOG_ID);

        switch (dialogId) {
            case ConstantValues.PROGRESS_DIALOG_DEFAULT: {

                final Bundle data = new Bundle();
                data.putInt(ConstantValues.DIALOG_ID, ConstantValues.PROGRESS_DIALOG_DEFAULT);

                receiver = getArguments().getParcelable(ConstantValues.RESULTANT_RECEIVER);

                String message = getArguments().getString(ConstantValues.DIALOG_MESSAGE);

                String title = getArguments().getString(ConstantValues.DIALOG_TITLE);

                final boolean isCancellable =
                        getArguments().getBoolean(ConstantValues.IS_DIALOG_CANCELLABLE, true);
                boolean isIndeterminate =
                        getArguments().getBoolean(ConstantValues.IS_DIALOG_INDETERMINATE, true);
                boolean isCanceledOnTouchOutside =
                        getArguments().getBoolean(ConstantValues.IS_DIALOG_CANCEL_ON_OUTSIDE_TOUCH,
                                false);

                int subDialogID = getArguments().getInt(ConstantValues.SUB_DIALOG_ID, 0);
                data.putInt(ConstantValues.SUB_DIALOG_ID, subDialogID);

                if (null != mProgressDialog) {
                    if (mProgressDialog.isShowing()) {
                        mProgressDialog.dismiss();
                    }
                    mProgressDialog = null;
                }
                mProgressDialog = new ProgressDialog(getActivity());

                if (!TextUtils.isEmpty(title)) {
                    mProgressDialog.setTitle(title);
                }

                if (!TextUtils.isEmpty(message)) {
                    mProgressDialog.setMessage(message);
                }
                mProgressDialog.setCancelable(isCancellable);
                mProgressDialog.setIndeterminate(isIndeterminate);

                mProgressDialog.setCanceledOnTouchOutside(isCanceledOnTouchOutside);

                mProgressDialog.setOnKeyListener(new OnKeyListener() {

                    @Override
                    public boolean onKey(DialogInterface dialog,
                            int keyCode,
                            KeyEvent event) {
                        if (keyCode == event.KEYCODE_SEARCH) {
                            return true;
                        } else if (keyCode == event.KEYCODE_BACK) {

                            if (null != receiver) {
                                receiver.send(ConstantValues.CANCEL_CLICKED, data);
                            }
                            return !isCancellable;
                        }
                        return false;
                    }

                });

                return mProgressDialog;
            }

            case ConstantValues.ALERT_DIALOG_DEFAULT: {

                try {
                    String titleText = getArguments().getString(ConstantValues.DIALOG_TITLE);
                    String messageText = getArguments().getString(ConstantValues.DIALOG_MESSAGE);

                    int drawableId = getArguments().getInt(ConstantValues.DIALOG_DRAWABLE_ID, 0);

                    String positiveBtnText =
                            getArguments().getString(ConstantValues.DIALOG_POSITIVE_BUTTON_TEXT);
                    String negativeBtnText =
                            getArguments().getString(ConstantValues.DIALOG_NEGATIVE_BUTTON_TEXT);
                    String neutralBtnText =
                            getArguments().getString(ConstantValues.DIALOG_NEUTRAL_BUTTON_TEXT);

                    boolean isCustomView =
                            getArguments().getBoolean(ConstantValues.IS_CUSTOM_DIALOG_VIEW, false);

                    boolean isCancellable =
                            getArguments().getBoolean(ConstantValues.IS_DIALOG_CANCELLABLE, true);

                    final ResultReceiver receiver =
                            getArguments().getParcelable(ConstantValues.RESULTANT_RECEIVER);

                    final Bundle data = new Bundle();
                    data.putInt(ConstantValues.DIALOG_ID, ConstantValues.ALERT_DIALOG_DEFAULT);

                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                    if (!TextUtils.isEmpty(titleText)) {
                        builder.setTitle(titleText);
                    }

                    if (!TextUtils.isEmpty(messageText) && !isCustomView) {
                        builder.setMessage(messageText);
                    }

                    if (drawableId > 0) {
                        builder.setIcon(drawableId);
                    }

                    if (!TextUtils.isEmpty(positiveBtnText)) {
                        builder.setPositiveButton(positiveBtnText,
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog,
                                            int id) {
                                        // Send the positive button event back
                                        // to the host activity
                                        if (null != receiver) {
                                            receiver.send(ConstantValues.POSITIVE_BUTTON_CLICKED,
                                                    data);

                                        }
                                        dialog.dismiss();
                                    }
                                });
                    }

                    if (!TextUtils.isEmpty(negativeBtnText)) {
                        builder.setNegativeButton(negativeBtnText,
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog,
                                            int id) {
                                        // Send the negative button event back
                                        // to the host activity
                                        if (null != receiver) {
                                            receiver.send(ConstantValues.NEGATIVE_BUTTON_CLICKED,
                                                    data);
                                        }
                                        dialog.dismiss();
                                    }
                                });
                    }

                    if (!TextUtils.isEmpty(neutralBtnText)) {
                        builder.setNeutralButton(neutralBtnText,
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog,
                                            int id) {
                                        // Send the neutral button event back to
                                        // the host activity
                                        if (null != receiver) {
                                            receiver.send(ConstantValues.NEUTRAL_BUTTON_CLICKED,
                                                    data);

                                        }
                                        dialog.dismiss();
                                    }
                                });
                    }

                    mAlertDialog = builder.create();
                    mAlertDialog.setCanceledOnTouchOutside(false);
                    mAlertDialog.setCancelable(isCancellable);

                    mAlertDialog.setOnKeyListener(new OnKeyListener() {

                        @Override
                        public boolean onKey(DialogInterface dialog,
                                int keyCode,
                                KeyEvent event) {

                            if (keyCode == event.KEYCODE_BACK) {
                                if (null != receiver) {
                                    receiver.send(ConstantValues.CANCEL_CLICKED, data);
                                }
                            }
                            return false;
                        }

                    });

                    return mAlertDialog;
                } catch (Exception exception) {
                    Logger.error(TAG, "Error is: ");
                    exception.printStackTrace();
                }

            }

            default:
                break;
        }
        return null;
    }

    /**
     * Dismiss Dialog Based on Dialog ID
     * 
     * @param dialogId
     */
    public static void dismissDialog(int dialogId) {

        try {
            switch (dialogId) {
                case ConstantValues.PROGRESS_DIALOG_DEFAULT: {
                    if (mProgressDialog != null && mProgressDialog.isShowing()) {
                        mProgressDialog.dismiss();
                        mProgressDialog = null;
                    }
                }
                    break;
                case ConstantValues.ALERT_DIALOG_DEFAULT: {
                    if (mAlertDialog != null && mAlertDialog.isShowing()) {
                        mAlertDialog.dismiss();
                        mAlertDialog = null;
                    }
                }
                default:
                    break;
            }
        } catch (Exception e) {
            Logger.error(TAG, "Error is:");
            e.printStackTrace();
        }
    }

    /**
     * Check if the dialog is visible
     * 
     * @param dialogId
     * @return
     */
    public static boolean isDialogVisible(int dialogId) {

        try {
            switch (dialogId) {
                case ConstantValues.PROGRESS_DIALOG_DEFAULT:
                    if (mProgressDialog != null) {
                        return mProgressDialog.isShowing();
                    }
                    break;
                case ConstantValues.ALERT_DIALOG_DEFAULT:
                    if (mAlertDialog != null) {
                        return mAlertDialog.isShowing();
                    }
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            Logger.error(TAG, "Stack Trace is:--");
            e.printStackTrace();
        }
        return false;
    }

}
