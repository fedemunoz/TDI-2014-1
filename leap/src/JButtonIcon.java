import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;


public class JButtonIcon {
   public static void main(String[] a) {
      JFrame f = new JFrame("My Icon Button");
      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      JButton b = new JButton(new ImageIcon("java.gif"));
      f.getContentPane().add(b);
      f.pack();      
      f.setVisible(true);
   }
}
