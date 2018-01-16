package com.emily.scrollview;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private LinearLayout mLayout;
    private ScrollView sView;
    private final Handler mHandler=new Handler();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //创建一个线性布局
        mLayout=this.findViewById(R.id.LinearLayout);
        //创建一个ScrollView对象
        sView=this.findViewById(R.id.ScrollView);
        Button mbtn=this.findViewById(R.id.Button);
        mbtn.setOnClickListener(myListner);//添加单机事件监听
    }
    private View.OnClickListener myListner=new View.OnClickListener() {
        private  int index=1;
        @Override
        public void onClick(View v) {
            TextView textView=new TextView(MainActivity.this);//定义一个textview
            textView.setText("文本框控件"+index);//设置textview的文本信息

            //设置线性布局的属性
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            mLayout.addView(textView,params);//添加一个TextView控件
            Button button=new Button(MainActivity.this);
            button.setText("控件控件"+index);
            button.setId(index++);
            mLayout.addView(button,params);
            mHandler.post(mScorllToButton);


        }

    };
    private Runnable mScorllToButton=new Runnable() {
        @Override
        public void run() {
            int off=mLayout.getMeasuredHeight()-sView.getHeight();
            if(off>0){
                sView.scrollTo(0,off);
            }

        }
    };
    public boolean onKeyDown(int keyCode, KeyEvent keyEvent){
        Button b=(Button)this.getCurrentFocus();
        int count=mLayout.getChildCount();
        Button bm=(Button)mLayout.getChildAt(count-1);
        if(keyCode==KeyEvent.KEYCODE_DPAD_DOWN&&b.getId()==R.id.Button){
            bm.requestFocus();
            return  true;
        }else if(keyCode==KeyEvent.KEYCODE_DPAD_DOWN&&b.getId()==bm.getId()){
            this.findViewById(R.id.Button).requestFocus();
            return true;
        }
        return false;
    }

}
