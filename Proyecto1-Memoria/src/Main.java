import com.leapmotion.leap.Finger;

import com.onformative.leap.LeapMotionP5;

import de.voidplus.dollar.OneDollar;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Robot;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import static java.lang.System.out;
import java.net.URL;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.FontUIResource;
import processing.core.PApplet;
import processing.core.PVector;

public class Main extends JFrame{
	//private PApplet pApp;
	
	private int width = 1040;
	private int height = 680;
	private boolean capsOn = true;
	
	private TextArea output;
        private ActionListener guardarGesto;
        
        private boolean primeraVez=true;
        
	public Main() throws IOException
	{
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            setSize(width, height);
            setBounds(170, 25, width, height);
		// get rid of annoying layout managers
//		setLayout(null);    
            
            JPanel p2= new JPanel() {
                                        ImageIcon imageIcon = new ImageIcon(getClass().getResource("fondo.png"));
                                        public void paintComponent(Graphics g) {
                                          Graphics2D g2d = (Graphics2D) g.create();
                                          g2d.drawImage(imageIcon.getImage(), 0, 0,1040, 680, this);
                                        }
                                };
            
			 JPanel p= new JPanel() {
											ImageIcon imageIcon = new ImageIcon(getClass().getResource("fondo.gif"));
											public void paintComponent(Graphics g) {
											  Graphics2D g2d = (Graphics2D) g.create();
											  g2d.drawImage(imageIcon.getImage(), 0, 0,1040, 680, this);
											}
										};
			 setContentPane(p);  
             
		
		// make the text boxes 
//		output = new TextArea("");  
//		// make output read only and color it back to white 
//		output.setEditable(false); 
//                output.setBackground(Color.white); 
//
//		output.setBounds(160, height-100, 720, 60);
		// add text boxes to their containers 

//		add(output);
        
                         
                //final JButton b2 = new JButton("CHEQUEAR GESTO");
                JButton b2 = new JButton(new ImageIcon("reconocerconimagen.png"));
                b2.setOpaque(false);
                b2.setContentAreaFilled(false);
                b2.setBorder(null);
                b2.setBorderPainted(false);
                b2.setForeground(Color.white);
                b2.setCursor(new Cursor(Cursor.HAND_CURSOR));      
                add(b2);
                b2.setVisible(true);
                
                //final JButton b1 = new JButton("GUARDAR GESTO");
                JButton b1 = new JButton(new ImageIcon("guardar.png"));
                b1.setOpaque(false);
                b1.setContentAreaFilled(false);
                b1.setBorder(null);
                b1.setBorderPainted(false);
                b1.setForeground(Color.white);
                b1.setCursor(new Cursor(Cursor.HAND_CURSOR));      
                add(b1);
                b1.setVisible(true);
                
                final JButton b4 = new JButton(new ImageIcon("reconocer.png"));
                b4.setOpaque(false);
                b4.setVisible(true); 
                b4.setContentAreaFilled(false);
                b4.setBorder(null);
                b4.setBorderPainted(false);
                b4.setForeground(Color.white);
                b4.setCursor(new Cursor(Cursor.HAND_CURSOR));                                                
                b4.setBounds(500, 650, 287, 26);
                add(b4);
                
                //final JButton b3 = new JButton("ELEGIR IMAGEN");
                JButton b3 = new JButton(new ImageIcon("importar.png"));
                b3.setOpaque(false);
                b3.setContentAreaFilled(false);
                b3.setBorder(null);
                b3.setBorderPainted(false);
                b3.setForeground(Color.white);
                b3.setCursor(new Cursor(Cursor.HAND_CURSOR));                 
                b3.setVisible(true);                
                b3.setBounds(500, 650, 287, 26);
                add(b3);                                
                
                final HWRCanvas hwrc = new HWRCanvas(this);
                hwrc.setBounds(160, 75, 720, 500);
                
                b1.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e)
                    {
                        //Execute when button is pressed
                        hwrc.Grabo=true;
                         hwrc.templateXY= new ArrayList<Integer>();
                        if(primeraVez){
                            hwrc.init();       
                        }
                        hwrc.start();
                        if(primeraVez){
                            add(hwrc);
                            primeraVez=false;
                        }

                        System.out.println("You clicked the button");
                    }
                });
                
                b2.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e)
                    {
                        //Execute when button is pressed
                         hwrc.conImagen=true;    
                         hwrc.Grabo=false;  
                        
                         //hwrc.init();                          
                          hwrc.start();                        
                         //add(hwrc);
                         
                        System.out.println("You clicked the button chequear");
                        //b4.setVisible(true); 
                    }
                });  
                
                b3.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e)
                    {                        
                        JFileChooser filechooser= new JFileChooser();
                        FileFilter imageFilter = new FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes());
                        filechooser.setFileFilter(imageFilter);
                        filechooser.setDialogTitle("Seleccionar imagen");
                        filechooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                        //below codes for select  the file 
                        int returnval=filechooser.showOpenDialog(null);
                        
                        if(returnval==JFileChooser.APPROVE_OPTION)
                        {
                            File file = filechooser.getSelectedFile();
                            BufferedImage bi;
                            try
                            {   //display the image in jlabel
                                bi=ImageIO.read(file);
                                System.out.println(file.getName());
                                                                
                                File outputfile = new File("saved.png");
                                ImageIO.write(bi, "png", outputfile);
                            }
                            catch(IOException ev)
                            {

                            }
                            
                        }
                    }
                    
                    
                });  
                
                b4.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e)
                {
                    
                    //Execute when button is pressed
                         hwrc.Grabo=false;                         
                         //hwrc.init();                          
                          hwrc.start();                        
                         //add(hwrc);
                          
                          hwrc.conImagen=false;
                                           
                    
                    
                }
                    
                });
                
		//pack();
                 
                  
		setResizable(false);
		setVisible(true);
		setTitle("Gesture Password");
                
	}
	
	public void detectedGesto(String gesture, int x, int y, int c_x, int c_y) throws IOException {
                JOptionPane.showMessageDialog(this, "Has iniciado sesión.");
               
		  if(capsOn)
			output.append("gestoo");
		  else
			  output.append("gestooo");
		System.out.println("This is gestooo...");
		}
	
	private void toggleCaps()
	{
		if(capsOn)
			capsOn = false;
		else
			capsOn = true;
	}
	
	public static void main(String[] args) throws IOException {
		new Main();

	}


        
        
}
