package com.github.mdogdope.whosthatcounter;

public class Counters {

	private final String[] types = {"bug","dark","dragon","electric","fairy","fighting","fire","flying","ghost","grass","ground","ice","normal","poison","psychic","rock","steel","water"};
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
}
