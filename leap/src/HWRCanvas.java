import com.leapmotion.leap.Finger;
import com.leapmotion.leap.Tool;
import com.onformative.leap.LeapMotionP5;
import de.voidplus.dollar.OneDollar;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Robot;
import java.awt.TextArea;
import java.sql.Time;
import java.util.ArrayList;

import javax.management.timer.Timer;


import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;


public class HWRCanvas extends PApplet{
	/**
	 * 
	 */
    
         public boolean conImagen=false;
    
	private static final long serialVersionUID = 1L;

	private OneDollar one;

	private boolean isStarted = false;
	
	private boolean noFingers = true;
	
	
	// generate key events
	Robot robot;
	
	// the leap
	private LeapMotionP5 leap;
	
	private Main  main;
	
	private int time = 0;
        
        ArrayList<Integer> templateXY;
        Boolean Grabo;


	PVector avgPos = new PVector();
	
	public HWRCanvas()
	{
	
	}

	
	public HWRCanvas(Main main)
	{
		
		this.main    = main;

	}
	
	@Override
	public void setup()
	{
	  size(720,500);
                    
          if (Grabo){
            PImage pimg= loadImage("saved.png","png");
            pimg.resize(720, 500);
            background(pimg);
          }else{
               PImage pimg= loadImage("fondo_canvas.png","png");
                    pimg.resize(720, 500);
                    background(pimg);
                    stroke(230,230,230);
                    
            //background(255);             
          }
          
          templateXY = new ArrayList<Integer>();

		  
          // initialize the Leap
          leap = new LeapMotionP5(this);
          
           try {
              robot = new Robot();
           } catch (AWTException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
           }
		  
                  
            one = new OneDollar(this);
            
		  one.setMinLength(50);
		  one.setMaxLength(2500);
		  one.setMaxTime(5000);

		  one.setMinScore(75); // 85%
		  one.setRotationAngle(10);
		  one.setFragmentationRate(64);

		  println(one);
		  one.setVerbose(true);  
		  
		  //one.start(200);
		  //one.setMaxTime(5000);
	}
	
	@Override
	public void draw()
	{
//            if(Grabo){
//		if(noFingers)
//		{
//			//System.out.println("Elapsed time 1: " + (millis() - time));
//		}
//		if(noFingers && ((millis() - time) > 1000))
//		{
//			one.check();
//			System.out.println(templateXY.toString());
////                        Integer[] stockArr = new Integer[templateXY.size()];
////                        stockArr = templateXY.toArray(stockArr);
////                        one.add("gesto", stockArr);
////                        one.bind("gesto",main, "detectedA");
////			one.end(200);
//			isStarted = false;
//
//			//noFingers = false;
//			//time = 0;
//		}
		
		clear();
                
                if (Grabo || conImagen){
                    //Cuando esta grabando sobre la imagen
                    PImage pimg= loadImage("saved.png","png");
                    pimg.resize(720, 500);
                    background(pimg);
                    stroke(230,230,230);
                } else{
                    
                    PImage pimg= loadImage("fondo_canvas.png","png");
                    pimg.resize(720, 500);
                    background(pimg);
                    stroke(230,230,230);
                    
                    /*
                    //Fondo blanco cuando esta reconociendo
                    background(255);
                    stroke(0);*/
                }
                
		requestFocus();
                smooth();  
                strokeWeight(5);
                noFill();
		one.draw();
                
		// anyway to detect only one finger?
		// detect a fingeR
		// start the unistroke
		// finger moves away - stop unistroke
		if(leap.getFingerList().size() == 0)
		{         
			if(noFingers == false)
			{
                            noFingers = true;
                            time = millis();

                            //System.out.println(noFingers + ", time: " + time);
                        }

		}
		else
		{
//			if(leap.getFingerList().size() >= 2)
//			{
//				one.end(200);
//				isStarted = false;
//			}
				 // get a new finger
                    
                    for (Finger finger : leap.getFingerList()) {
                           noFingers = false;

                           if(!isStarted)
                           {
                               one.start(200);
                               isStarted = true;
                           }

                           PVector fingerPos = leap.getTip(finger);
                           ellipse(fingerPos.x, fingerPos.y, 15, 15);

                           //avgPos.add(fingerPos);	
                           System.out.println(fingerPos.x + " , " +  fingerPos.y);
                           templateXY.add(Math.round(fingerPos.x));
                           templateXY.add(Math.round(fingerPos.y));
                           //templateXY.add(fingerPos.y);
                           one.update(200, fingerPos.x, fingerPos.y);
                                break;	    
                    }
			 
				 
			 //avgPos.div(leap.getFingerList().size());
			 //ellipse(avgPos.x, avgPos.y, 10, 10);
			 //one.update(200, avgPos.x, avgPos.y);
		}
            }
		
//	}
	
	
	@Override
	public void keyPressed()  
	{ 
            
          clear();
          stop();
          System.out.println(templateXY.toString());
          if(Grabo){ 
              //one.removeGesture("gesto");
              Integer[] stockArr = new Integer[templateXY.size()];
              stockArr = templateXY.toArray(stockArr);
              one.add("gesto", stockArr);
              one.bind("gesto",main, "detectedGesto");
              one.end(200);
          } else { 
              
              one.check();            
              one.end(200);
            
          }
              
         //destroy();     
	}
    
	
	@Override
	public void mousePressed()  
	{ 
	  //one.start(100);
	} // 100 = ID
	
	@Override
	public void mouseDragged()  { //one.update(100, mouseX, mouseY); 
	}
	
	@Override
	public void mouseReleased() { //one.end(100); 

	}
        
}
