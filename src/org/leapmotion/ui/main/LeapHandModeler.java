package org.leapmotion.ui.main;

import javax.swing.JFrame;

import org.leapmotion.leap.util.LeapListener;

import com.leapmotion.leap.Controller;

public class LeapHandModeler extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4483671659107112219L;
	private static Controller controller;
	private static LeapListener leapListener; 
	private static LeapView leapView;	
	private static int FRAME_WIDTH = 800, FRAME_HEIGHT = 800;
	
	public LeapHandModeler(){
		super("Leap hand modeler");
        setSize(FRAME_WIDTH,FRAME_HEIGHT);
        setVisible(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
	}
	
	private void init(){
		controller		= new Controller();
		leapListener 	= new LeapListener(this);
		leapView		= new LeapView(FRAME_WIDTH,FRAME_HEIGHT);
		
		controller.addListener(leapListener);
		leapView.init();
		
		setLayout(null);
		add(leapView);
		setVisible(true);
	}
	
	public void redisplay(){
		//System.out.println("Leap Hand modeler update");
		leapView.redisplay();
		repaint();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LeapHandModeler lhm = new LeapHandModeler();
		lhm.init();

	}

}
