package top.zhpoo.spamkiller;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity implements OnClickListener {
    private TextView textView;

    // com.android.mms
    private static final String SMARTISAN_MMS = "com.android.mms";
    private static final String SETTINGS_PACKAGE = "com.android.settings";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.tv_set_default_sms_app);
        setTextView();
    }

    private void setTextView() {
        textView.setOnClickListener(this);
        StringBuilder sb = new StringBuilder();
        sb.append(SmsUtil.defaultSmsPackage(this));
        sb.append('\n');
        sb.append(getString(R.string.set_default_sms_app));
        textView.setText(sb.toString());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_set_default_sms_app:
                SmsUtil.goToSettingDefaultMsg(this, targetSmsPackage());
                setTextView();
//                startSettings();
                break;
        }
    }

    private void startSettings() {
        try {
            Context settingsContext = createPackageContext(SETTINGS_PACKAGE, Context.CONTEXT_INCLUDE_CODE | Context.CONTEXT_IGNORE_SECURITY);

            Class<?> smsApp = Class.forName("com.android.internal.telephony.SmsApplication");
            Method setDefaultApplication = smsApp.getMethod("setDefaultApplication", String.class, Context.class);
            setDefaultApplication.setAccessible(true);

            setDefaultApplication.invoke(null, targetSmsPackage(), settingsContext);
        } catch (Exception e) {
            e.printStackTrace();
        }
        setTextView();
    }

    private boolean shouldReset() {
        return !SMARTISAN_MMS.equals(SmsUtil.defaultSmsPackage(this));
    }

    private String targetSmsPackage() {
        return shouldReset() ? SMARTISAN_MMS : getPackageName();
    }
}
