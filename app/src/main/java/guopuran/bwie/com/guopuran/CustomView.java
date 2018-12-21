package guopuran.bwie.com.guopuran;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.TextView;

@SuppressLint("AppCompatCustomView")
public class CustomView extends EditText {
    private Context context;
    public CustomView(Context context) {
        super(context);
        init(context);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        setBackgroundResource(R.drawable.bd_bg);
    }
}
