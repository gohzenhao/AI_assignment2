package solution;

import java.awt.geom.Point2D;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import problem.ASVConfig;
import problem.Obstacle;
import problem.ProblemSpec;
import tester.Tester;

public class Sampler {
	
	
	private int ASVCount;	
	private List<ASVConfig> ASVConfigs = new ArrayList<ASVConfig>();	
	private Tester tester = new Tester();	
	private ProblemSpec problemSpec;	
	
	public Sampler(ProblemSpec inProblemSpec){		
		this.problemSpec = inProblemSpec;
//		this.ASVCount = 3;
		this.ASVCount = inProblemSpec.getASVCount();		
	}
	
	public void Sample(int times){
		ASVConfig currentASV;
		this.ASVConfigs.add(problemSpec.getInitialState());
		for(int u=0;u<times;u++){
			currentASV = new ASVConfig();
			double baseX = Math.random();
			double baseY = Math.random();			
			currentASV.addPoints(baseX, baseY);
			currentASV.addAngle(Math.random()*360);
			if(ASVCount>=3)
			{
				double count;
				if(this.ASVCount>=7)
					count = 6.5;
				else
					count = this.ASVCount-1;
				double eachAngle = (180.00*(this.ASVCount*count-2))/(this.ASVCount*count);
				for(int i = 1 ; i < this.ASVCount-1;i++)
				{
					double prevAngle = currentASV.getAngles().get(i-1);
					double angle = eachAngle;
					if(problemSpec.getInitialState().checkCurvingDirection().equals("RightOfTheBase"))
					{
						if(prevAngle == 180)
							angle = eachAngle;
						else if(prevAngle == 0 || prevAngle==360)
							angle = 180 + eachAngle;
						else if(prevAngle>0 && prevAngle<180)
						{
							double conseqInAngle = 180 - prevAngle;
							double deltaAngle = Math.abs(conseqInAngle-eachAngle);
							if(eachAngle>conseqInAngle)
								angle = deltaAngle;
							else
								angle = 360 - deltaAngle; 
						}
						else if(prevAngle>180 && prevAngle<360)
						{
							double suppAngle = 360 - prevAngle;
							double conseqInAngle = 180 - suppAngle;
							angle = eachAngle + conseqInAngle;
						}
					}
					else
					{
						if(prevAngle == 180)
							angle = 360 - eachAngle;
						else if(prevAngle == 0 || prevAngle==360)
							angle = 180 - eachAngle;
						else if(prevAngle>0 && prevAngle<180)
							angle = 180+prevAngle-eachAngle;
						else if(prevAngle>180 && prevAngle<360)
						{
							double suppAngle = 360 - prevAngle;
							double conseqInAngle = 180 - suppAngle;
							double deltaAngle = Math.abs(conseqInAngle-eachAngle);
							if(eachAngle>conseqInAngle)
								angle = 360 - deltaAngle;
							else
								angle = deltaAngle;
						}
					}
					currentASV.addAngle(angle);
				}
			}			
			currentASV.getASVPositions();
			boolean result = false;

				
			if(!tester.hasCollision(currentASV, this.problemSpec.getObstacles()) && tester.fitsBounds(currentASV)){
			
				if(tester.isConvex(currentASV) && tester.hasEnoughArea(currentASV)){
					//adding edge to each existing ASVConfig in the list if valid
					for(int i=0;i<this.ASVConfigs.size();i++){
						ASVConfig lastASV = this.ASVConfigs.get(i);
						lastASV.getASVPositions();
						if(this.validPath(lastASV, currentASV)){
							result = lastASV.addEdge(currentASV);
						}					
					}
					this.ASVConfigs.add(currentASV);
				}
			}
				
		}
		
		
	}
	
	public boolean validPath (ASVConfig asv1, ASVConfig asv2) {
		 	
		for(int u=0;u<asv1.getASVCount();u++){
		 			
			double x1 = asv1.getPosition(u).getX();
			double y1 = asv1.getPosition(u).getY();
			double x2 = asv2.getPosition(u).getX();
			double y2 = asv2.getPosition(u).getY();
 	
			List<Obstacle> obstacles = this.problemSpec.getObstacles();
			
			for(int i=0;i<obstacles.size();i++){
				if (obstacles.get(i).getRect().intersectsLine(x1, y1, x2, y2)) {
					return false;
				}
			}
 		}
		 		
		return true;
	}
	
	public List<ASVConfig> ASVConfigs(){
		return this.ASVConfigs;
	}
	
	
	

}
