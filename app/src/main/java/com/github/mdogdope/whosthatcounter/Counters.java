package com.github.mdogdope.whosthatcounter;

import java.util.Vector;

public class Counters {

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

	private String type = "";
	
	public Counters(String type){
		
		this.type = type.toLowerCase();
	}

	public int effectiveness(String type){
		int x = -1;
		int y = -1;

		int counter = 0;
		for(String t : types){
			if (this.type.equals(t)){
				x = counter;
			}
			if (type.equals(t)){
				y = counter;
			}
			counter++;
		}

		return counters[x][y];
	}

	public void setType(String type){
		
		this.type = type.toLowerCase();
	}

	public String getType(){
		
		return this.type;
	}
	
	public Vector<Boolean> getWeak(){
		Vector<Boolean> ret = new Vector<>();
		
		int id = 0;
		for(String t : types){
			if(t.equals(this.type)){
				break;
			}
			id++;
		}
		for(int ia = 0; ia < counters.length; ia++){
			if(counters[ia][id] > 0){
				ret.add(true);
			}else{
				ret.add(false);
			}
		}
		return ret;
	}
}
