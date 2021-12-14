package com.github.mdogdope.whosthatcounter;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class MainMenu extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);

		Button offense = findViewById(R.id.mm_offense);
		offense.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(MainMenu.this, Offense.class);
				startActivity(intent);
			}
		});
		
		Button defense = findViewById(R.id.mm_defense);
		defense.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent i = new Intent(MainMenu.this, Defense.class);
				startActivity(i);
			}
		});
	}

	@Override
	public void onBackPressed() {
		confirmExit();
	}

	private void confirmExit(){
		final Dialog confirmExit = new Dialog(MainMenu.this);

		confirmExit.requestWindowFeature(Window.FEATURE_NO_TITLE);

		confirmExit.setCancelable(true);

		confirmExit.setContentView(R.layout.fragment_confirm_exit);
		
		Button back = confirmExit.findViewById(R.id.ce_back);
		Button exit = confirmExit.findViewById(R.id.ce_exit);
		
		back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				confirmExit.dismiss();
			}
		});
		
		exit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				confirmExit.dismiss();
				MainMenu.super.onBackPressed();
			}
		});
		
		confirmExit.show();
	}
}