package com.tian.videomergedemo;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
        findViewById(R.id.btn_click).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(MainActivity.this,RecordActivity.class);
				startActivity(intent);
				
			}
		});
        
        
        
        findViewById(R.id.btn_click_selcet_loaction).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainActivity.this,VideoGridActivity.class));
			}
		});
        
        
        findViewById(R.id.btn_record_audio).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainActivity.this,AudioActivity.class));
				
			}
		});
        
        
        
    }

   
}
