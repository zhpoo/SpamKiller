package top.zhpoo.spamkiller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MmsReceiver extends BroadcastReceiver {
    private static final String TAG = "MmsReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "new mms message: \n" + intent);
    }
}
