package com.example.zeon.notificationexamples;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnShow = (Button) findViewById(R.id.btn_show);
        btnShow.setOnClickListener(clickListener);
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v == btnShow){
                startService(new Intent(MainActivity.this, NotificationService.class));
                Toast.makeText(MainActivity.this, "Notification will show in 5 second",
                        Toast.LENGTH_LONG).show();
                finish();
            }
        }
    };
}
