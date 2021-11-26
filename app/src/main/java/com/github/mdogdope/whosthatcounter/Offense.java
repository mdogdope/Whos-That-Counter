package com.github.mdogdope.whosthatcounter;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Vector;

public class Offense extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_offense);

		Button check = findViewById(R.id.check);
		check.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				view.getContext();
			}
		});

		Integer[] ids = {R.id.bug, R.id.dark, R.id.dragon, R.id.electric, R.id.fairy, R.id.fighting, R.id.fire, R.id.flying, R.id.ghost, R.id.grass, R.id.ground, R.id.ice, R.id.normal, R.id.poison, R.id.psychic, R.id.rock, R.id.steel, R.id.water};
		String[] types = {"bug","dark","dragon","electric","fairy","fighting","fire","flying","ghost","grass","ground","ice","normal","poison","psychic","rock","steel","water"};

		HashMap<String, ImageView> buttons = new HashMap<>();

		for(int i = 0; i < types.length; i++) {
			buttons.put(types[i],findViewById(ids[i]));
		}



	}
}