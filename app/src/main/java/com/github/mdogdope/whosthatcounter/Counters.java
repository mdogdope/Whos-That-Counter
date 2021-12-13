package com.github.mdogdope.whosthatcounter;

import java.util.Vector;

public class Counters {
	
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
	
	private int type = 0;
	
	public Counters(int typeId){
		
		this.type = typeId;
	}

	public int effectiveness(int typeId){
		
		return counters[this.type][typeId];
	}

	public void setType(int typeId){
		this.type = typeId;
	}

	public String getTypeString(){
		return types[this.type];
	}
	
	public Vector<Integer> getWeakList(){
		Vector<Integer> ret = new Vector<>();
		
		int id = this.type;
		int counter = 0;
		for(int ia = 0; ia < counters.length; ia++){
			if(counters[ia][id] > 0){
				ret.add(counter);
			}
			counter++;
		}
		return ret;
	}
}
