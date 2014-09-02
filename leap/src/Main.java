import processing.core.PApplet;
import processing.core.PVector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.onformative.leap.LeapMotionP5;

import de.voidplus.dollar.OneDollar;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.Color;
import java.awt.Robot;
import java.util.ArrayList;
import com.leapmotion.leap.Finger;

public class Main extends JFrame{
	//private PApplet pApp;
	
	private int width = 640;
	private int height = 480;
	private boolean capsOn = true;
	
	private TextArea output;
	public Main()
	{
		setSize(width, height);
		setBounds(0, 0, width, height);
		// get rid of annoying layout managers
		setLayout(null);
		
		// make the text boxes 
		output = new TextArea("");  
		// make output read only and color it back to white 
		output.setEditable(false); 
		output.setBackground(Color.white); 

		output.setBounds(50, 380, width-100, height - 420);
		// add text boxes to their containers 

		add(output);
		
		HWRCanvas hwrc = new HWRCanvas(this);
		hwrc.init();
		hwrc.setBounds(170, 50, 300, 300);
		add(hwrc);
		
		
		  
		

		//pack();
		setResizable(false);
		setVisible(true);
		setTitle("TDI Grupo 1!");
	}
	
	public void detectedA(String gesture, int x, int y, int c_x, int c_y) {
		System.out.println("This is gestooo...");
		  if(capsOn)
			output.append("gestoo");
		  else
			  output.append("gestooo");
		}
	
	public void detectedB(String gesture, int x, int y, int c_x, int c_y) {
		  System.out.println("This is B...");

		  if(capsOn)
			  output.append("B");
		  else
			  output.append("b");
		  System.out.println("output text: " + output.getText());
		}

	public void detectedC(String gesture, int x, int y, int c_x, int c_y) {
		  System.out.println("This is C...");

		  if(capsOn)
			  output.append("C");
		  else
			  output.append("c");
		  System.out.println("output text: " + output.getText());
		}
	
	public void detectedD(String gesture, int x, int y, int c_x, int c_y) {
		  System.out.println("This is D...");

		  if(capsOn)
			  output.append("D");
		  else
			  output.append("d");
		  System.out.println("output text: " + output.getText());
		}
	
	public void detectedE(String gesture, int x, int y, int c_x, int c_y) {
		  System.out.println("This is E...");

		  if(capsOn)
			  output.append("E");
		  else
			  output.append("e");
		  System.out.println("output text: " + output.getText());
		}
	
	public void detectedF(String gesture, int x, int y, int c_x, int c_y) {
		  System.out.println("This is F...");

		  if(capsOn)
			  output.append("F");
		  else
			  output.append("f");
		  System.out.println("output text: " + output.getText());
		}
	
	public void detectedG(String gesture, int x, int y, int c_x, int c_y) {
		  System.out.println("This is G...");

		  if(capsOn)
			  output.append("G");
		  else
			  output.append("g");
		  System.out.println("output text: " + output.getText());
		}
	
	public void detectedH(String gesture, int x, int y, int c_x, int c_y) {
		  System.out.println("This is H...");

		  if(capsOn)
			  output.append("H");
		  else
			  output.append("h");
		  System.out.println("output text: " + output.getText());
		}
	
	public void detectedI(String gesture, int x, int y, int c_x, int c_y) {
		  System.out.println("This is I...");

		  if(capsOn)
			  output.append("I");
		  else
			  output.append("i");
		  System.out.println("output text: " + output.getText());
		}
	
	public void detectedJ(String gesture, int x, int y, int c_x, int c_y) {
		  System.out.println("This is J...");

		  if(capsOn)
			  output.append("J");
		  else
			  output.append("j");
		  System.out.println("output text: " + output.getText());
		}
	
	public void detectedK(String gesture, int x, int y, int c_x, int c_y) {
		  System.out.println("This is K...");

		  if(capsOn)
			  output.append("K");
		  else
			  output.append("k");
		  System.out.println("output text: " + output.getText());
		}
	
	public void detectedL(String gesture, int x, int y, int c_x, int c_y) {
		  System.out.println("This is L...");

		  if(capsOn)
			  output.append("L");
		  else
			  output.append("l");
		  System.out.println("output text: " + output.getText());
		}
	
	public void detectedM(String gesture, int x, int y, int c_x, int c_y) {
		  System.out.println("This is M...");

		  if(capsOn)
			  output.append("M");
		  else
			  output.append("m");
		  System.out.println("output text: " + output.getText());
		}
	
	public void detectedN(String gesture, int x, int y, int c_x, int c_y) {
		  System.out.println("This is N...");

		  if(capsOn)
			  output.append("N");
		  else
			  output.append("n");
		  System.out.println("output text: " + output.getText());
		}
	
	public void detectedO(String gesture, int x, int y, int c_x, int c_y) {
		  System.out.println("This is O...");

		  if(capsOn)
			  output.append("O");
		  else
			  output.append("o");
		  System.out.println("output text: " + output.getText());
		}
	
	public void detectedP(String gesture, int x, int y, int c_x, int c_y) {
		  System.out.println("This is P...");

		  if(capsOn)
			  output.append("P");
		  else
			  output.append("p");
		  System.out.println("output text: " + output.getText());
		}
	
	public void detectedQ(String gesture, int x, int y, int c_x, int c_y) {
		  System.out.println("This is Q...");

		  if(capsOn)
			  output.append("Q");
		  else
			  output.append("q");
		  System.out.println("output text: " + output.getText());
		}
	
	public void detectedR(String gesture, int x, int y, int c_x, int c_y) {
		  System.out.println("This is R...");

		  if(capsOn)
			  output.append("R");
		  else
			  output.append("r");
		  System.out.println("output text: " + output.getText());
		}

	public void detectedS(String gesture, int x, int y, int c_x, int c_y) {
		  System.out.println("This is S...");

		  if(capsOn)
			  output.append("S");
		  else
			  output.append("s");
		  System.out.println("output text: " + output.getText());
		}	
	
	public void detectedT(String gesture, int x, int y, int c_x, int c_y) {
		  System.out.println("This is T...");

		  if(capsOn)
			  output.append("T");
		  else
			  output.append("t");
		  System.out.println("output text: " + output.getText());
		}
	
	public void detectedU(String gesture, int x, int y, int c_x, int c_y) {
		  System.out.println("This is U...");

		  if(capsOn)
			  output.append("U");
		  else
			  output.append("u");
		  System.out.println("output text: " + output.getText());
		}
	
	public void detectedV(String gesture, int x, int y, int c_x, int c_y) {
		  System.out.println("This is V...");

		  if(capsOn)
			  output.append("V");
		  else
			  output.append("v");
		  System.out.println("output text: " + output.getText());
		}
	
	public void detectedW(String gesture, int x, int y, int c_x, int c_y) {
		  System.out.println("This is W...");

		  if(capsOn)
			  output.append("W");
		  else
			  output.append("w");
		  System.out.println("output text: " + output.getText());
		}
	
	public void detectedX(String gesture, int x, int y, int c_x, int c_y) {
		  System.out.println("This is X...");

		  if(capsOn)
			  output.append("X");
		  else
			  output.append("x");
		  System.out.println("output text: " + output.getText());
		}
	
	public void detectedY(String gesture, int x, int y, int c_x, int c_y) {
		  System.out.println("This is Y...");

		  if(capsOn)
			  output.append("Y");
		  else
			  output.append("y");
		  System.out.println("output text: " + output.getText());
		}
	
	public void detectedZ(String gesture, int x, int y, int c_x, int c_y) {
		  System.out.println("This is Z...");

		  if(capsOn)
			  output.append("Z");
		  else
			  output.append("z");
		  System.out.println("output text: " + output.getText());
		}
	
	
	

	public void detectedCaps(String gesture, int x, int y, int c_x, int c_y) {
		  System.out.println("This is CAPS...");

		  toggleCaps();
		  
		  System.out.println("output text: " + output.getText());
		}
	
	public void detectedSpace(String gesture, int x, int y, int c_x, int c_y) {
		  System.out.println("This is space...");

		  output.append(" ");
		  
		  System.out.println("output text: " + output.getText());
	}
	
	public void detectedBackSpace(String gesture, int x, int y, int c_x, int c_y) {
		  System.out.println("This is a backspace...");

		  String txt = output.getText();
		  output.setText(txt.substring(0, txt.length()-1));
		  
		  System.out.println("output text: " + output.getText());
	}
	
	private void toggleCaps()
	{
		if(capsOn)
			capsOn = false;
		else
			capsOn = true;
	}
	
	public static void main(String[] args) {
		new Main();

	}
}
