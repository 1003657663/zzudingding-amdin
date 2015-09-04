package com.codeevery.myElement;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.codeevery.zzudingdingAd.R;

/**
 * Created by songchao on 15/9/4.
 */
public class LongPopWinodws {
    Context context;
    PopupWindow popupWindow;
    public LongPopWinodws(Context context){
        this.context = context;
    }
    public void showPopWindow(View v,String text, final int position){
        LinearLayout popView = new LinearLayout(context);
        popView.setBackgroundColor(context.getResources().getColor(R.color.black));
        popView.setPadding(30,15,30,15);
        TextView textView = new TextView(context);
        textView.setText(text);
        textView.setTextColor(context.getResources().getColor(R.color.wheat));
        popView.addView(textView);
        popupWindow = new PopupWindow(popView, ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT,true);
        //获取弹窗的长宽度
        popView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int popWidth = popView.getMeasuredWidth();
        int popHeight = popView.getMeasuredHeight();
        //获取父控件位置
        int[] location = new int[2];
        v.getLocationOnScreen(location);
        textView.setClickable(true);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popOnDo.Do(position);
            }
        });
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                    popupWindow.dismiss();
                    return true;
                }
                return false;
            }
        });
        popupWindow.showAtLocation(v, Gravity.NO_GRAVITY, location[0] + v.getWidth() / 2 - popWidth / 2, location[1] - popHeight - 20);
        popupWindow.update();
    }

    public void hidePopWindows(){
        if(popupWindow.isShowing()) {
            popupWindow.dismiss();
        }
    }

    PopOnDo popOnDo;
    public void setInterface(PopOnDo popOnDo){
        this.popOnDo = popOnDo;
    }
    public interface PopOnDo{
        public void Do(int position);
    }
}
