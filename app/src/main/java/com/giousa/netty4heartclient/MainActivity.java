package com.giousa.netty4heartclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.giousa.netty4heartclient.client.Client;

public class MainActivity extends AppCompatActivity {

    private EditText mMsg;
    private Client mClient;
    private String hostIp = "192.168.0.108";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

    }

    private void initView() {
        mMsg = (EditText) findViewById(R.id.et_msg);
        Button send = (Button) findViewById(R.id.btn_send);

        mClient = new Client(hostIp);
        mClient.start();

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String trim = mMsg.getText().toString().trim();

                if(TextUtils.isEmpty(trim)){
                    Toast.makeText(getApplication(),"数据不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    mClient.sendData(trim);
                } catch (Exception e) {
                    System.out.println("异常");
                    e.printStackTrace();
                }
            }
        });
    }
}
