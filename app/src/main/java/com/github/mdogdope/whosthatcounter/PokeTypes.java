package com.github.mdogdope.whosthatcounter;

import java.util.Random;
import java.util.Vector;

public class PokeTypes {
	
	public final int BUG = 0;
	public final int DARK = 1;
	public final int DRAGON = 2;
	public final int ELECTRIC = 3;
	public final int FAIRY = 4;
	public final int FIGHTING = 5;
	public final int FIRE = 6;
	public final int FLYING = 7;
	public final int GHOST = 8;
	public final int GRASS = 9;
	public final int GROUND = 10;
	public final int ICE = 11;
	public final int NORMAL = 12;
	public final int POISON = 13;
	public final int PSYCHIC = 14;
	public final int ROCK = 15;
	public final int STEEL = 16;
	public final int WATER = 17;


	private final String[] types = {"bug","dark","dragon","electric","fairy","fighting","fire","flying","ghost","grass","ground","ice","normal","poison","psychic","rock","steel","water"};

	// counters[attacker][defender]
	private final Integer[][] counters = {
			{0,1,0,0,-1,-1,-1,-1,-1,1,0,0,0,-1,1,0,-1,0},
			{0,-1,0,0,-1,-1,0,0,1,0,0,0,0,0,1,0,0,0},
			{0,0,1,0,-2,0,0,0,0,0,0,0,0,0,0,0,-1,0},
			{0,0,-1,-1,0,0,0,1,0,-1,-2,0,0,0,0,0,0,1},
			{0,1,1,0,0,1,-1,0,0,0,0,0,0,-1,0,0,-1,0},
			{-1,1,0,0,-1,0,0,-1,-2,0,0,1,1,-1,-1,1,1,0},
			{1,0,-1,0,0,0,-1,0,0,1,0,1,0,0,0,-1,1,-1},
			{1,0,0,-1,0,1,0,0,0,1,0,0,0,0,0,-1,-1,0},
			{0,-1,0,0,0,0,0,0,1,0,0,0,-2,0,1,0,0,0},
			{-1,0,-1,0,0,0,-1,-1,0,-1,1,0,0,-1,0,1,-1,1},
			{-1,0,0,1,0,0,1,-2,0,-1,0,0,0,1,0,1,1,0},
			{0,0,1,0,0,0,-1,1,0,1,1,-1,0,0,0,0,-1,-1},
			{0,0,0,0,0,0,0,0,-2,0,0,0,0,0,0,-1,-1,0},
			{0,0,0,0,1,0,0,0,-1,1,-1,0,0,-1,0,-1,-2,0},
			{0,-2,0,0,0,1,0,0,0,0,0,0,0,1,-1,0,-1,0},
			{1,0,0,0,0,-1,1,1,0,0,-1,1,0,0,0,0,-1,0},
			{0,0,0,-1,1,0,-1,0,0,0,0,1,0,0,0,1,-1,-1},
			{0,0,-1,0,0,0,1,0,0,-1,1,0,0,0,0,1,0,-1}
	};
	
	private final int TYPE_COUNT = types.length;
	
	private int subject = 0;

	private Vector<Integer> mixedSubjectList = new Vector<>();

	public PokeTypes(){
		generateMixedList();
	}
	
	public Integer nextSubject(){
		if(!this.mixedSubjectList.isEmpty()){
			generateMixedList();
		}
		this.subject = this.mixedSubjectList.elementAt(0);
		this.mixedSubjectList.removeElementAt(0);
		return this.subject;
	}
	
	public boolean setSubject(int subject){
		if(subject <= this.TYPE_COUNT && subject >= 0){
			this.subject = subject;
			return true;
		}else{
			return false;
		}
	}
	
	public Integer getSubject(){
		return this.subject;
	}
	
	public String getSubjectString(){
		return types[this.subject];
	}
	
	public boolean checkWeak(Vector<Integer> ans){
		boolean ret = false;
		Vector<Integer> correct = generateWeak();
		if(ans.containsAll(correct) && ans.size() == correct.size()){
			ret = true;
		}
		return ret;
	}

	public boolean checkStrong(Vector<Integer> ans){
		boolean ret = false;
		Vector<Integer> correct = generateStrong();
		if(ans.containsAll(correct) && ans.size() == correct.size()){
			ret = true;
		}
		return ret;
	}
	
	public Vector<Integer> getCorrect(Vector<Integer> ans, Vector<Integer> correct){
		Vector<Integer> ret = new Vector<>();
		for(int i = 0; i < types.length; i++){
			if(ans.contains(i) && correct.contains(i)){
				ret.add(i);
			}
		}
		return ret;
	}

	public Vector<Integer> getWrong(Vector<Integer> ans, Vector<Integer> correct){
		Vector<Integer> ret = new Vector<>();
		for(int i = 0; i < types.length; i++){
			if(ans.contains(i) && !correct.contains(i)){
				ret.add(i);
			}
		}
		return ret;
	}

	public Vector<Integer> getMissing(Vector<Integer> ans, Vector<Integer> correct){
		Vector<Integer> ret = new Vector<>();
		for(int i = 0; i < types.length; i++){
			if(!ans.contains(i) && correct.contains(i)){
				ret.add(i);
			}
		}
		return ret;
	}
	
	public Vector<Integer> generateWeak(){
		Vector<Integer> ret = new Vector<>();
		for(int i = 0; i < this.TYPE_COUNT; i++){
			int test = this.counters[i][this.subject];
			if(test > 0){
				ret.add(test);
			}
		}
		return ret;
	}

	public Vector<Integer> generateStrong(){
		Vector<Integer> ret = new Vector<>();
		for(int i = 0; i < this.TYPE_COUNT; i++){
			int test = this.counters[i][this.subject];
			if(test < 0){
				ret.add(test);
			}
		}
		return ret;
	}
	
	private void generateMixedList(){
		Vector<Integer> baseSubjectList = new Vector<>();
		for(int i = 0; i < this.TYPE_COUNT; i++){
			baseSubjectList.add(i);
		}
		Random rng = new Random();
		while(!baseSubjectList.isEmpty()){
			int loc = rng.nextInt(baseSubjectList.size());
			this.mixedSubjectList.add(baseSubjectList.elementAt(loc));
			baseSubjectList.remove(loc);
		}
	}
}

