package com.boykinchoi.star.myEvent;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.boykinchoi.star.R;
import com.boykinchoi.star.coroutine.CoroutineTest;

import me.jessyan.autosize.utils.LogUtils;

/**
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in 2021/9/26 14:10
 */
public class TestMainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_main);
        MyLayout myLayout = findViewById(R.id.ll_root);
        Button first = findViewById(R.id.tv_first);
        Button second = findViewById(R.id.tv_second);
        myLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                LogUtils.d("MyLayout on touch");
                return false;
            }
        });
        first.setOnClickListener(v ->
                LogUtils.d("first on touch"));
        second.setOnClickListener(v ->
                LogUtils.d("second on touch"));

        CoroutineTest coroutineTest = new CoroutineTest();
        coroutineTest.test2();
    }
}
