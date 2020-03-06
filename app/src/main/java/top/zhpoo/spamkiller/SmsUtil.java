package top.zhpoo.spamkiller;

import android.content.Context;
import android.content.Intent;
import android.provider.Telephony;
import android.provider.Telephony.Sms;

public class SmsUtil {
    public static boolean isDefaultSmsApp(Context context) {
        return context.getPackageName().equals(defaultSmsPackage(context));
    }

    public static String defaultSmsPackage(Context context) {
        return Sms.getDefaultSmsPackage(context);
    }

    public static void goToSettingDefaultMsg(Context context, String packageName) {
        try {
            Intent intent = new Intent(Telephony.Sms.Intents.ACTION_CHANGE_DEFAULT);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra(Telephony.Sms.Intents.EXTRA_PACKAGE_NAME, packageName);
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setDefaultSmsApp(Context context) {

    }
}
