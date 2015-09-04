package com.codeevery.myElement;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

/**
 * Created by songchao on 15/9/4.
 */
public class LongPopWinodws {
    Context context;
    public LongPopWinodws(Context context){
        this.context = context;
    }
    public void showPopWindow(View v,String text){
        View popView = new LinearLayout(context);
        TextView textView = new TextView(context);
        textView.setText(text);
        final PopupWindow popupWindow = new PopupWindow(popView, ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT,true);
        //获取弹窗的长宽度
        popView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int popWidth = popView.getMeasuredWidth();
        int popHeight = popView.getMeasuredHeight();
        //获取父控件位置
        int[] location = new int[2];
        v.getLocationOnScreen(location);
        popupWindow.showAtLocation(v, Gravity.NO_GRAVITY, location[0] + v.getWidth() / 2 - popWidth / 2, location[1] - popHeight);
        popupWindow.update();
        textView.setClickable(true);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popOnDo.Do();
            }
        });
    }

    PopOnDo popOnDo;
    public void setInterface(PopOnDo popOnDo){
        this.popOnDo = popOnDo;
    }
    public interface PopOnDo{
        public void Do();
    }
}
