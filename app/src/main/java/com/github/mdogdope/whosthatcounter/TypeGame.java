package com.github.mdogdope.whosthatcounter;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;
import java.util.Vector;

public class TypeGame extends AppCompatActivity {

	private final String[] types = {"bug","dark","dragon","electric","fairy","fighting","fire","flying","ghost","grass","ground","ice","normal","poison","psychic","rock","steel","water"};
	
	private final int[] typeImages = {R.drawable.type_bug, R.drawable.type_dark, R.drawable.type_dragon, R.drawable.type_electric, R.drawable.type_fairy, R.drawable.type_fighting, R.drawable.type_fire, R.drawable.type_flying, R.drawable.type_ghost, R.drawable.type_grass, R.drawable.type_ground, R.drawable.type_ice, R.drawable.type_normal, R.drawable.type_poison, R.drawable.type_psychic, R.drawable.type_rock, R.drawable.type_steel, R.drawable.type_water};

	private final Integer[] ids = {R.id.tg_bug, R.id.tg_dark, R.id.tg_dragon, R.id.tg_electric, R.id.tg_fairy, R.id.tg_fighting, R.id.tg_fire, R.id.tg_flying, R.id.tg_ghost, R.id.tg_grass, R.id.tg_ground, R.id.tg_ice, R.id.tg_normal, R.id.tg_poison, R.id.tg_psychic, R.id.tg_rock, R.id.tg_steel, R.id.tg_water};

	private String mode = "";
	
	private PokeTypes pt = new PokeTypes();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_type_game);
		
		Bundle extras = getIntent().getExtras();
		if(extras != null){
			this.mode = extras.getString("mode");
		}else{
			finish();
		}
		setupStaticUI();
		setupGame();
	}
	
	private void setupStaticUI(){
		String relationText = "";
		switch(this.mode){
			case "offense":
				relationText = "is weak to";
				break;
			case "defense":
				relationText = "is resistant to";
				break;
		}
		TextView relation = findViewById(R.id.gr_type_relation);
		relation.setText(relationText);
	}
	
	private void setupGame(){
		int subject = pt.nextSubject();
		Vector<ImageButton> typeButtons = new Vector<>();

		for(int id : this.ids) {
			ImageButton temp = findViewById(id);
			temp.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {

					buttonClick(view);
				}
			});

			if(temp.getTag().equals("pressed")){
				temp.setTag("unpressed");
				temp.setBackgroundResource(R.drawable.ui_button);
			}

			typeButtons.add(temp);
		}
		
		Button check = findViewById(R.id.tg_check);
		check.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				//TODO:Add code for check button.
			}
		});

		ImageButton back = findViewById(R.id.tg_back);
		back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				finish();
			}
		});
	}

	private void buttonClick(View view){
		if(view.getTag().equals("unpressed")){
			view.setTag("pressed");
			view.setBackgroundResource(R.drawable.ui_button_selected);
		}else{
			view.setTag("unpressed");
			view.setBackgroundResource(R.drawable.ui_button);
		}
	}
	
}
