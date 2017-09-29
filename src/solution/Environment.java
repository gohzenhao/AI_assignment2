package solution;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
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
            if(i.getOverallValue(goal) > j.getOverallValue(goal)){
                return 1;
            }

            else if (i.getOverallValue(goal) < j.getOverallValue(goal)){
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
	    LinkedList<ASVConfig> explored = new LinkedList<>();
		ASVConfig child;

			ASVConfig current;
		    initial.setCostFromStart(0,goal);

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
			    		boolean isClosed = explored.contains(child);
			    		double x,y;
			    		x = child.getY()-current.getY();
			    		y = child.getX()-current.getX();
			    		double cost = Math.pow((x*x+y*y),0.5);
			    		double costFromStart = current.getCostFromStart()+cost;			    		
			    		if((costFromStart < child.getCostFromStart()))
			    		{
			    			child.setCostFromStart(costFromStart,goal);
			    		}
			    		if((!isOpen && !isClosed) || ((costFromStart+child.getCostToGoal(goal)) < child.getOverallValue(goal))){
			    			child.setCostFromStart(costFromStart,goal);
			    			child.setParent(current);			    			
			    			openList.add(child);		
			    		}
			    	}
			    	explored.add(current);	
		    	}
		    		    	
		    }
		    if(openList.isEmpty() && !goalFound)
		    	return null;
		return null;
		

		
	}

}

