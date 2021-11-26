package com.boykinchoi.star.service.remote;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.boykinchoi.star.R;

/**
 * 模拟夏杰 语控命令
 *
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in 2021/9/26 14:10
 */
public class TestMainActivity2 extends AppCompatActivity {

    private static final String TAG = "TestMainActivity2";
    private EditText etContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_main2);
        Button btSendCommonSearch = findViewById(R.id.bt_send_common_search);
        Button btSendControlAction = findViewById(R.id.bt_send_control_action);
        Button btSendControlIndex = findViewById(R.id.bt_send_control_index);
        etContent = findViewById(R.id.et_content);
        btSendCommonSearch.setOnClickListener(v ->
                startService(1));
        btSendControlAction.setOnClickListener(v ->
                startService(2));
        btSendControlIndex.setOnClickListener(v ->
                startService(3));
    }

    private void startService(int type) {
        Intent intent = new Intent();
        intent.setPackage("com.wyt.iexuetang.tv.jtb");
        intent.setAction("com.peasun.aispeech.action.education");
        String content = etContent.getText().toString().trim();
        switch (type) {
            case 1:
                intent.putExtra("common", "search");
                intent.putExtra("keyword", content);
                break;
            case 2:
                intent.putExtra("common", "control");
                intent.putExtra("action", content);
                break;
            case 3:
                intent.putExtra("common", "control");
                intent.putExtra("PlayIndex", content);
                break;
            default:
                break;
        }
        // 注意：每次调用 startService() 都会导致系统完成大量工作来管理围绕意图处理的服务生命周期，
        // 这可能需要多毫秒的 CPU 时间。由于这个成本， startService() 不应该用于频繁地向服务传递意图，
        // 而只能用于安排重要的工作。使用 {@link bindService 绑定服务}进行高频调用。
        startService(intent);
        Log.d(TAG, "startService");
    }
}
