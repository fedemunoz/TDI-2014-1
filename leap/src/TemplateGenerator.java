import java.awt.AWTException;
import java.awt.Robot;
import java.awt.TextArea;
import java.util.ArrayList;

import com.leapmotion.leap.Finger;
import com.onformative.leap.LeapMotionP5;

import de.voidplus.dollar.OneDollar;
import processing.core.PApplet;
import processing.core.PVector;


public class TemplateGenerator extends PApplet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private boolean isStarted = false;
	
	// generate key events
	Robot robot;
	
	// the leap
	private LeapMotionP5 leap;

	// for obtaining the points
	boolean move = false;
	boolean over = false;
	ArrayList<Integer> templateX;
	ArrayList<Integer> templateY;


	
	private OneDollar one;
	
	public TemplateGenerator()
	{
		
	}

	
	@Override
	public void setup()
	{
		 size(300, 300);
		 background(250);
		 
		 one = new OneDollar(this);

		  // initialize the Leap
		  leap = new LeapMotionP5(this);
		  
		 println(one);
		 one.setVerbose(true);  
		  
		 templateX = new ArrayList<Integer>();
		 templateY = new ArrayList<Integer>();
	}
	
	@Override
	public void draw()
	{
            			    
		clear();
		background(125);
		one.check();
		  
		noFill();
		one.draw();
	
		if (move)
		{
		    //println ("Mouse X: " + mouseX +", Mouse Y: " + mouseY);
		    templateX.add(mouseX);
		    templateY.add(mouseY);
		}
		
		if(leap.getFingerList().size() == 0)
		{
			//one.end(200);
			//isStarted = false;
		}
		else
		{
			if(leap.getFingerList().size() >= 2)
			{
				//one.end(200);
				isStarted = false;
//				
//				System.out.println("The points are...");
//				for(int i = 0; i < templateX.size(); i++)
//				{
//				  System.out.print(templateX.get(i) + ", " + templateY.get(i) + ", ");
//				}
			}
			for (Finger finger : leap.getFingerList()) {
				
				if(!isStarted)
			    {
			    	//one.start(200);
			    	isStarted = true;
			    }
				
			    PVector fingerPos = leap.getTip(finger);
			    ellipse(fingerPos.x, fingerPos.y, 10, 10);
			     

			    
			    //one.update(200, fingerPos.x, fingerPos.y);
			    
//			    templateX.add((int)fingerPos.x);
//			    templateY.add((int)fingerPos.y);
			    
			    //break;
			  }
		}


	}
	
	@Override
	public void mousePressed()  
	{ 
	  one.start(100);
	 
	  if(over)
	     move = true; 
	} // 100 = ID
	
	@Override
	public void mouseDragged()  { one.update(100, mouseX, mouseY); 
	}
	
	@Override
	public void mouseReleased() { one.end(100); 
	System.out.println("Mouse released...");
	System.out.println("The points are...");
	for(int i = 0; i < templateX.size(); i++)
	{
	  System.out.print(templateX.get(i) + ", " + templateY.get(i) + ", ");
	}
    templateX.clear();
    templateY.clear();
	move = false;
	if(over == false)
	  over = true;
	else
	  over = true;
	}
}
