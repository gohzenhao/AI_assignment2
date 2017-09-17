package solution;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeSet;

import problem.ASVConfig;
import problem.Obstacle;

public class Environment {
	
	ASVConfig initial;
	ASVConfig goal;
	List<Obstacle> obstacles;
	List<ASVConfig> samples;
	
	
	public Environment(ASVConfig initial,ASVConfig goal,List<Obstacle> obstacles,List<ASVConfig> samples){
		
		this.initial = initial;
		this.goal = goal;
		this.obstacles = obstacles;
		this.samples = samples;
		
		
	}	  

}

