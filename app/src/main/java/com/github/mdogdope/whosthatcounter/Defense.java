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

public class Defense extends AppCompatActivity {

	private final String[] types = {"bug","dark","dragon","electric","fairy","fighting","fire","flying","ghost","grass","ground","ice","normal","poison","psychic","rock","steel","water"};
	private final int[] typeImages = {R.drawable.type_bug, R.drawable.type_dark, R.drawable.type_dragon, R.drawable.type_electric, R.drawable.type_fairy, R.drawable.type_fighting, R.drawable.type_fire, R.drawable.type_flying, R.drawable.type_ghost, R.drawable.type_grass, R.drawable.type_ground, R.drawable.type_ice, R.drawable.type_normal, R.drawable.type_poison, R.drawable.type_psychic, R.drawable.type_rock, R.drawable.type_steel, R.drawable.type_water};

	private final Integer[] ids = {R.id.d_bug, R.id.d_dark, R.id.d_dragon, R.id.d_electric, R.id.d_fairy, R.id.d_fighting, R.id.d_fire, R.id.d_flying, R.id.d_ghost, R.id.d_grass, R.id.d_ground, R.id.d_ice, R.id.d_normal, R.id.d_poison, R.id.d_psychic, R.id.d_rock, R.id.d_steel, R.id.d_water};
	
	private Vector<Integer> mixedQs = new Vector<>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_defense);

		setupGame();
	}

	private void setupGame(){
		PokeTypes question = new PokeTypes(setQuestion());

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

		Button check = findViewById(R.id.d_check);
		check.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				Vector<Integer> correct = question.getStringList();
				Vector<Integer> ans = getAns(typeButtons);

				showResults(question, ans, correct);
			}
		});

		ImageButton back = findViewById(R.id.d_back);
		back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				finish();
			}
		});
	}

	private Vector<Integer> getAns(Vector<ImageButton> btns){
		Vector<Integer> ans = new Vector<>();
		int counter = 0;
		for(ImageButton btn : btns){
			if(btn.getTag().equals("pressed")){
				ans.add(counter);
			}
			counter++;
		}
		return ans;
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

	private int setQuestion(){
		TextView questionText = findViewById(R.id.d_name);
		ImageView questionIcon = findViewById(R.id.d_icon);

		if(this.mixedQs.isEmpty()){
			Vector<Integer> unmixed = new Vector<>();
			for(int i = 0; i < types.length; i++){
				unmixed.add(i);
			}
			Random randInt = new Random();
			while(!unmixed.isEmpty()) {
				int n = randInt.nextInt(unmixed.size());
				int b = unmixed.get(n);
				unmixed.removeElementAt(n);
				this.mixedQs.add(b);
			}
		}
		int qNum = this.mixedQs.get(0);
		this.mixedQs.remove(0);
		String name = types[qNum];
		questionText.setText(capitalize(name));
		questionIcon.setImageResource(typeImages[qNum]);
		return qNum;
	}

	private String capitalize(String s){
		//Simply capitalizes the first letter in a given string.
		return s.substring(0,1).toUpperCase() + s.substring(1);
	}



	private void showResults(PokeTypes q, Vector<Integer> answers, Vector<Integer> correct){
		final Dialog resultsDialog = new Dialog(Defense.this, android.R.style.Theme_Black_NoTitleBar);
		resultsDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.argb(119,0,0,0)));
		resultsDialog.setCancelable(true);
		resultsDialog.setContentView(R.layout.fragment_game_results);

		TextView score = resultsDialog.findViewById(R.id.gr_score);
		TextView question = resultsDialog.findViewById(R.id.gr_type);
		LinearLayout ans = resultsDialog.findViewById(R.id.gr_counters);

		ImageButton menu = resultsDialog.findViewById(R.id.gr_menu);
		Button again = resultsDialog.findViewById(R.id.gr_again);

		question.setText(capitalize(q.getTypeString()));

		boolean good = false;
		boolean bad = false;

		for(int i = 0; i < types.length; i++){
			TextView temp = new TextView(this);
			if(answers.contains(i) && correct.contains(i)){
				good = true;
				temp.setBackgroundColor(Color.parseColor("#5500FF00"));
			}else if(answers.contains(i)){
				bad = true;
				temp.setBackgroundColor(Color.parseColor("#55FF0000"));
			} else if(correct.contains(i)){
				bad = true;
				temp.setBackgroundColor(Color.parseColor("#55FFFF00"));
			}
			if(answers.contains(i) || correct.contains(i)) {
				temp.setText(types[i]);
				temp.setTextColor(Color.parseColor("#000000"));
				temp.setTextSize(20);
				temp.setGravity(Gravity.CENTER);
				ans.addView(temp);
			}
		}

		if(good && bad){
			score.setText("Almost");
		}else if(good){
			score.setText("Correct");
		}else{
			score.setText("Wrong");
		}

		menu.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				resultsDialog.hide();
				finish();
			}
		});

		again.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				resultsDialog.hide();
				setupGame();
			}
		});

		resultsDialog.show();
	}
}