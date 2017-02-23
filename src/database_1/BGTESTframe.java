package database_1;

import java.awt.Graphics; 
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class BGTESTframe extends JFrame {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

BGTESTframe() {
    add(new ContentPanel());
    setSize(500, 300);
  }

  public static void main(String[] args) {
    BGTESTframe jrframe = new BGTESTframe();
    jrframe.setVisible(true);
  }

}

class ContentPanel extends JPanel {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
Image bgimage = null;

  ContentPanel() {
    MediaTracker mt = new MediaTracker(this);
    bgimage = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Akhil Mathew\\workspace\\Data Base\\Images\\bg1.png");
    mt.addImage(bgimage, 0);
    try {
      mt.waitForAll();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    bgimage.getWidth(null);
    bgimage.getHeight(null);
    g.drawImage(bgimage, 1, 1, null);
  }
}











