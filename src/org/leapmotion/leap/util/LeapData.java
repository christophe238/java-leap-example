package org.leapmotion.leap.util;


import com.leapmotion.leap.*;

/**
 * User: Christophe Marchadour
 * Email : christophe.marchadour@gmail.com
 * Date: 07/08/13
 * Time: 21:34
 * Leap motion and Java3D example
 */
public class LeapData {
	private static HandList handList;
	private static GestureList gestureList;
	private static Frame frame;
	
	public LeapData(){}
	
	
	/**
	 * Setters
	 */
	public static void setHandList(HandList hl){
		handList = hl;
	}
	public static void setGestureList(GestureList g){
		gestureList = g;
	}
	public static void setFrame(Frame f){
		frame = f;
	}
	
	/**
	 * Getters
	 */
	public static HandList getHandList(){
		return handList;
	}
	public static GestureList getGestureList(){
		return gestureList;
	}
	public static Frame getFrame(){
		return frame;
	}
}
