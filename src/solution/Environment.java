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
import problem.ProblemSpec;

public class Environment {
	
	ASVConfig initial;
	ASVConfig goal;
	List<Obstacle> obstacles;
	List<ASVConfig> samples;
	ProblemSpec problemSpec;
	Sampler sampler;
	
	
	public Environment(ProblemSpec problemSpec,List<ASVConfig> samples,Sampler sampler){
		this.problemSpec = problemSpec;
		this.initial = this.problemSpec.getInitialState();
		this.goal = this.problemSpec.getGoalState();
		this.samples = samples;
		this.obstacles = this.problemSpec.getObstacles();
		this.sampler = sampler;
		
	}
	
	Comparator<ASVConfig> comparator = new Comparator<ASVConfig>() {
	    @Override
	    public int compare(ASVConfig i, ASVConfig j){
            if(i.getCostToGoal(goal)+i.getCostFromStart(initial) > j.getCostToGoal(goal)+j.getCostFromStart(initial)){
                return 1;
            }

            else if (i.getCostToGoal(goal)+i.getCostFromStart(initial)< j.getCostToGoal(goal)+j.getCostFromStart(initial)){
                return -1;
            }

            else{
                return 0;
            }
        }
	};
		
	  protected List<ASVConfig> constructPath(ASVConfig node) {
	
		 List<ASVConfig> path = new ArrayList<ASVConfig>();

		    while (node.getParent() != null) {
		    	
		 
		      path.add(node);
		     	node = node.getParent();


		    }
		    return path;

		  
		  }
	
	public List<ASVConfig> compute(){
		
		PriorityQueue<ASVConfig> openList = new PriorityQueue<ASVConfig>(samples.size(),comparator);
//	    LinkedList<AS> explored = new LinkedList<>();
		ASVConfig child;

			ASVConfig current;
		    initial.SetCostFromStart(0);

		    openList.add(initial);

		    
		    boolean goalFound = false;
		    goal.getASVPositions();
		    initial.getASVPositions();
		    while(!openList.isEmpty()){
		    	
		    	current = openList.poll();
		    	
//		    	System.out.println("current is : "+current);
		    	
	
		    	if(this.sampler.validPath(current, goal)){
		    		goalFound = true;
		    		goal.setParent(current);
		    		return constructPath(goal);
		    	}
		    	if(current.getEdges().size()!=0){
			    	for(int i=0;i<current.getEdges().size();i++)
			    	{
			    		Edge edge = current.getEdges().get(i);
			    		child = edge.target;	
			    		
			    		boolean isOpen = openList.contains(child);
	//		    		double cost = edge.distanceToGoal;
			    		double costToGoal = current.getCostToGoal(goal) + current.getCostFromStart(initial);
			    		
			    		
			    		if((!isOpen) || costToGoal < (child.getCostToGoal(goal)+child.getCostFromStart(initial))){
			    			child.setParent(current);
			    			
			    			openList.add(child);
		
			    		}
			    	}
		    	}
		    	child = current;
		    	
		    }
		    if(openList.isEmpty() && !goalFound)
		    	return null;
		return null;
		

		
	}

}

