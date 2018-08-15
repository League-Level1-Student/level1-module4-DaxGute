import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.sun.glass.ui.Timer;


public class WhackDatMole implements ActionListener { 
	int y = 13;
	JButton but [] = new JButton [y];
	int moleHits;
	int moleMisses;
	int mole1;
	Random random = new Random();
	Date date;
	public static void main(String[] args) {
		WhackDatMole mole = new WhackDatMole();
		mole.setup();
	}
	public void setup() {
		date = new Date();
		JFrame frame = new JFrame("Whack mole");
		frame.setSize(300, 300);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		for (int i = 0; i < but.length; i++) {
			but[i] = new JButton();
			frame.add(but[i]);
			but[i].addActionListener(this);
		}
		Random random = new Random();
		int x = random.nextInt(13);
		mole1 = x;
		but[mole1].setText("mole!");
		frame.setLayout(new FlowLayout());
	
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == but[mole1]) {
			speak("Dead on!");
			mole1 = random.nextInt(y+1);
			for (int i = 0; i < but.length; i++) {
				but[i].setText("");
			}
			but[mole1].setText("mole!") ;
			moleHits++;
			if (moleHits == 10) {
				endGame(date, 10);
				speak("win");
			}
			
		}else {
			speak("Fatality");
			moleMisses++;
			if (moleMisses == 5) {
				endGame(date, moleHits);
				speak("lose");
			}
		}
	}
	void speak(String words) {
	     try {
	          Runtime.getRuntime().exec("say " + words).waitFor();
	     } catch (Exception e) {
	          e.printStackTrace();
	     }
	}
	private void endGame(Date timeAtStart, int molesWhacked) {
	     Date timeAtEnd = new Date();
	     JOptionPane.showMessageDialog(null, "Your whack rate is "
	          + ((timeAtEnd.getTime() - timeAtStart.getTime()) / 1000.00 / molesWhacked)
	          + " moles per second.");
	}

}
