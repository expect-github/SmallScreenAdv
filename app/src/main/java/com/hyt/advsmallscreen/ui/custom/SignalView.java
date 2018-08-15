package com.hyt.advsmallscreen.ui.custom;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.hyt.advsmallscreen.R;


/**
 * Created by Tao on 2018/3/13 0013.
 */

@SuppressLint("AppCompatCustomView")
public class SignalView extends ImageView {

      public enum SignalStatue {
         none,normal,great;
    }
    
    public SignalView(Context context) {
        super(context);
    }

    public SignalView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SignalView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

     public  void   setSignalStatue(SignalStatue statue){

         switch (statue){
             case   none:
                 setImageResource(R.mipmap.none);
                 break;
             case normal:
                 setImageResource(R.mipmap.normal);
                 break;
             case great:
                 setImageResource(R.mipmap.great);
                 break;

         }
    }
}
