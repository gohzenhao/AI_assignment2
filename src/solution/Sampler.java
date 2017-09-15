package solution;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import problem.ASVConfigAngle;
import problem.ProblemSpec;
import tester.Tester;

public class Sampler {
	
	
	private int ASVCount;
	
	private ASVConfigAngle currentASV;
	
	private List<ASVConfigAngle> ASVConfigAngles = new ArrayList<ASVConfigAngle>();
	
	private Random random = new Random();
	
	private double pointX;
	
	private double pointY;
	
	private double angle;
	
	private DecimalFormat format = new DecimalFormat("#.0");
	
	private DecimalFormat format2 = new DecimalFormat("#.000");
	
	
	public Sampler(int inASVcount){
		
		this.ASVCount = inASVcount;
		
	}
	
	public void Sample(){
		
		this.pointX = Double.parseDouble(format2.format(random.nextDouble()));
		this.pointY = Double.parseDouble(format2.format(random.nextDouble()));
		
		currentASV = new ASVConfigAngle();
		
		currentASV.addPoints(this.pointX, this.pointY);
		
		for(int i=0;i<this.ASVCount-1;i++){
			
			angle = Math.random()*360;
			angle = Double.parseDouble(format.format(angle));
			currentASV.addAngle(angle);
			
			
		}
		
		this.ASVConfigAngles.add(currentASV);
		
		
		
	}
	
	public List<ASVConfigAngle> ASVConfigs(){
		return this.ASVConfigAngles;
	}
	
	
	
	

}
