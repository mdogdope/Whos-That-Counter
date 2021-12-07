package com.github.mdogdope.whosthatcounter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;

public class SplashScreen extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_screen);
		Thread timer = new Thread(){
			public void run(){
				try {
					sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}finally {
					Intent intent = new Intent(SplashScreen.this,MainMenu.class);
					startActivity(intent);
				}
			}
		};
		timer.start();
	}

	@Override
	protected void onPause(){
		super.onPause();
		finish();
	}
}