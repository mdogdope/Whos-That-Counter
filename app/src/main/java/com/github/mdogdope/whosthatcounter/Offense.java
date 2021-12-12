package com.github.mdogdope.whosthatcounter;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;
import java.util.Vector;

public class Offense extends AppCompatActivity {

	private final String[] types = {"bug","dark","dragon","electric","fairy","fighting","fire","flying","ghost","grass","ground","ice","normal","poison","psychic","rock","steel","water"};
	private final int[] typeImages = {R.drawable.type_bug, R.drawable.type_dark, R.drawable.type_dragon, R.drawable.type_electric, R.drawable.type_fairy, R.drawable.type_fighting, R.drawable.type_fire, R.drawable.type_flying, R.drawable.type_ghost, R.drawable.type_grass, R.drawable.type_ground, R.drawable.type_ice, R.drawable.type_normal, R.drawable.type_poison, R.drawable.type_psychic, R.drawable.type_rock, R.drawable.type_steel, R.drawable.type_water};

	private final Integer[] ids = {R.id.bug, R.id.dark, R.id.dragon, R.id.electric, R.id.fairy, R.id.fighting, R.id.fire, R.id.flying, R.id.ghost, R.id.grass, R.id.ground, R.id.ice, R.id.normal, R.id.poison, R.id.psychic, R.id.rock, R.id.steel, R.id.water};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_offense);
		
		setupGame();
	}
	
	private void setupGame(){
		Counters question = new Counters(setQuestion());

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

		Button check = findViewById(R.id.check);
		check.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				Vector<Boolean> ans = getAns(typeButtons);

				check(question, ans);
			}
		});
		
		ImageButton back = findViewById(R.id.offense_back);
		back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				finish();
			}
		});
	}
	
	private Vector<Boolean> getAns(Vector<ImageButton> btns){
		Vector<Boolean> ans = new Vector<Boolean>();
		for(ImageButton btn : btns){
			if(btn.getTag().equals("pressed")){
				ans.add(true);
			}else{
				ans.add(false);
			}
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
		TextView questionText = findViewById(R.id.q_name);
		ImageView questionIcon = findViewById(R.id.q_icon);

		Random randInt = new Random();
		int qNum = randInt.nextInt(types.length);
		
		String name = types[qNum];
		questionText.setText(capitalize(name));
		questionIcon.setImageResource(typeImages[qNum]);
		return qNum;
	}
	
	private String capitalize(String s){
		//Simply capitalizes the first letter in a given string.
		return s.substring(0,1).toUpperCase() + s.substring(1);
	}
	
	private void check(Counters q, Vector<Boolean> ans){
		Vector<Boolean> correct = q.getWeakList();
		boolean wrong = false;
		for(int i=0; i < correct.toArray().length; i++){
			if(correct.elementAt(i) != ans.elementAt(i)){
				wrong = true;
				break;
			}
		}
		showResults(q, wrong, correct);
	}
	
	private void showResults(Counters q, boolean wrong, Vector<Boolean> correct){
		final Dialog resultsDialog = new Dialog(Offense.this);
		
		resultsDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		resultsDialog.setCancelable(true);
		resultsDialog.setContentView(R.layout.fragment_game_results);
		
		TextView score = resultsDialog.findViewById(R.id.gr_score);
		TextView question = resultsDialog.findViewById(R.id.gr_type);
		LinearLayout ans = resultsDialog.findViewById(R.id.gr_counters);
		
		ImageButton menu = resultsDialog.findViewById(R.id.gr_menu);
		Button again = resultsDialog.findViewById(R.id.gr_again);

		if(wrong){
			score.setText("Wrong");
		}else {
			score.setText("Correct");
		}
		
		question.setText(capitalize(q.getTypeString()));
		
		for(int i = 0; i < correct.size(); i++){
			if(correct.elementAt(i)){
				TextView temp = new TextView(this);
				temp.setText(types[i]);
				temp.setTextColor(Color.parseColor("#000000"));
				temp.setTextSize(20);
				temp.setGravity(Gravity.CENTER);
				ans.addView(temp);
			}
		}
		
		menu.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
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