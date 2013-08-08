package org.leapmotion.leap.util;


import org.leapmotion.ui.main.LeapHandModeler;

import com.leapmotion.leap.*;

/**
 *
 */
public class LeapListener extends Listener {
	
	private static LeapHandModeler leapHandModeler;
	
	public LeapListener(LeapHandModeler lhm){
		leapHandModeler = lhm;
	}
	
    public void onInit(Controller controller){
        System.out.println("LeapListener Initialized");
    }

    public void onConnect(Controller controller) {
        System.out.println("LeapListener Connected");
        controller.enableGesture(Gesture.Type.TYPE_SWIPE);
        controller.enableGesture(Gesture.Type.TYPE_CIRCLE);
        controller.enableGesture(Gesture.Type.TYPE_SCREEN_TAP);
        controller.enableGesture(Gesture.Type.TYPE_KEY_TAP);
    }
    
    public void onExit(Controller controller){
    	System.out.println("LeapListener Exit");
    }
    
    public void onDisconnect(Controller controller){
    	System.out.println("LeapListener Disconnected");
    }

    public void onFrame(Controller controller){
        // Get the most recent frame and report some basic information
        Frame frame = controller.frame();
        GestureList gestures = frame.gestures();
        HandList hands = frame.hands();
        
        LeapData.setFrame(frame);
        LeapData.setHandList(hands);
        LeapData.setGestureList(gestures);
        
        leapHandModeler.redisplay();

        //Gesture Loop
        for (int i = 0; i < gestures.count(); i++) {
            Gesture gesture = gestures.get(i);
            
            switch (gesture.type()) {
                case TYPE_CIRCLE:
                    CircleGesture circle = new CircleGesture(gesture);
                    // Calculate clock direction using the angle between circle normal and pointable
                    
                    /*
                    String clockwiseness;
                    if (circle.pointable().direction().angleTo(circle.normal()) <= Math.PI/4) {
                        // Clockwise if angle is less than 90 degrees
                        clockwiseness = "clockwise";
                    } else {
                        clockwiseness = "counterclockwise";
                    }

                    // Calculate angle swept since last frame
                    double sweptAngle = 0;
                    if (circle.state() != Gesture.State.STATE_START) {
                        CircleGesture previousUpdate = new CircleGesture(controller.frame(1).gesture(circle.id()));
                        sweptAngle = (circle.progress() - previousUpdate.progress()) * 2 * Math.PI;
                    }*/
                    
                    /*System.out.println("Circle id: " + circle.id()
                            + ", " + circle.state()
                            + ", progress: " + circle.progress()
                            + ", radius: " + circle.radius()
                            + ", angle: " + Math.toDegrees(sweptAngle)
                            + ", " + clockwiseness);*/
                    break;
                case TYPE_SWIPE:
                    SwipeGesture swipe = new SwipeGesture(gesture);
                    /*System.out.println("Swipe id: " + swipe.id()
                            + ", " + swipe.state()
                            + ", position: " + swipe.position()
                            + ", direction: " + swipe.direction()
                            + ", speed: " + swipe.speed());*/
                    break;
                case TYPE_SCREEN_TAP:
                	ScreenTapGesture screenTap = new ScreenTapGesture(gesture);
                    /*System.out.println("Screen Tap id: " + screenTap.id()
                            + ", " + screenTap.state()
                            + ", position: " + screenTap.position()
                            + ", direction: " + screenTap.direction());*/
                    break;
                case TYPE_KEY_TAP:
                	KeyTapGesture keyTap = new KeyTapGesture(gesture);
                    /*System.out.println("Key Tap id: " + keyTap.id()
                            + ", " + keyTap.state()
                            + ", position: " + keyTap.position()
                            + ", direction: " + keyTap.direction());*/
                    break;
                default:
                    //System.out.println("Unknown gesture type.");
                    break;
            }
        }
    }

}
