package com.github.mdogdope.whosthatcounter;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Random;
import java.util.Vector;

public class Offense extends AppCompatActivity {

	private final String[] types = {"bug","dark","dragon","electric","fairy","fighting","fire","flying","ghost","grass","ground","ice","normal","poison","psychic","rock","steel","water"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_offense);

		setQuestion();

		Button check = findViewById(R.id.check);
		check.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				checkAns();
			}
		});

		Integer[] ids = {R.id.bug, R.id.dark, R.id.dragon, R.id.electric, R.id.fairy, R.id.fighting, R.id.fire, R.id.flying, R.id.ghost, R.id.grass, R.id.ground, R.id.ice, R.id.normal, R.id.poison, R.id.psychic, R.id.rock, R.id.steel, R.id.water};

		Vector<ImageView> buttons = new Vector<>();

		for(int id : ids) {
			ImageView temp = findViewById(id);
			temp.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					buttonClick(view);
				}
			});
			buttons.add(temp);
		}
	}

	private void buttonClick(View view){


	}

	private void setQuestion(){
		TextView questionText = findViewById(R.id.q_name);
		ImageView questionIcon = findViewById(R.id.q_icon);

		Random randInt = new Random();
		int qNum = randInt.nextInt(types.length);

		questionText.setText(types[qNum]);
		questionIcon.setImageDrawable(Drawable.createFromPath("@drawable/type_".concat(types[qNum])));
	}

	private boolean checkAns(){

		return true;
	}

}