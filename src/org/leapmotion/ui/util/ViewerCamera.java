package org.leapmotion.ui.util;

import javax.media.j3d.Transform3D;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

import org.leapmotion.leap.util.LeapData;

import com.leapmotion.leap.Finger;
import com.leapmotion.leap.FingerList;
import com.leapmotion.leap.Hand;
import com.leapmotion.leap.HandList;
import com.leapmotion.leap.Vector;

/**
 * User: Christophe Marchadour
 * Email : christophe.marchadour@gmail.com
 * Date: 07/08/13
 * Time: 21:34
 * Leap motion and Java3D example
 */
public class ViewerCamera {
	
	private static Point3d eye;
	private static Point3d lookAt;
	private static Vector3d up;
	private static Point3d speed; 
	private static Transform3D camera;
	
	public ViewerCamera(){
		camera  = new Transform3D();
		eye 	= new Point3d(2,4,3);
		lookAt  = new Point3d(0,0,0);
		up		= new Vector3d(0,1,0);
		speed 	= new Point3d(0.02,0.02,0.02);
		System.out.println("Viewer camera initialized");
	}
	
	public void redisplay(){
		//System.out.println("Viewer camera update");
		//Compute new camera location
		HandList handlist = LeapData.getHandList();
		if (!handlist.empty()) {
            // Get the first hand
            Hand hand = handlist.get(0);
            
            FingerList fingers = hand.fingers();
            if (!fingers.empty()) {
                // Calculate the hand's average finger tip position
                Vector avgPos = Vector.zero();
                for (Finger finger : fingers) {
                    avgPos = avgPos.plus(finger.tipPosition());
                }
                avgPos = avgPos.divide(fingers.count());
                /*System.out.println("Hand has " + fingers.count()
                        + " fingers, average finger tip position: " + avgPos);*/
            }
                        
            moveEyeWithHandPalm(hand.palmPosition());
            
            // Get the hand's normal vector and direction
            //Normal 	: Vector normal = hand.palmNormal();
            //Direction : Vector direction = hand.direction();
            //Pitch		: Math.toDegrees(direction.pitch())
            //Roll 		: Math.toDegrees(normal.roll())
            //Yaw  		: Math.toDegrees(direction.yaw())            
        }
	}
	
	private void moveEyeWithHandPalm(Vector position){
		
        double stepX = (double) Math.round(Math.abs(position.getX())+100)/100 * speed.x;
        double stepY = speed.y;
        //double stepY = (translate.getY() > 150) ? (float) Math.round(translate.getY()+100)/100 * stepY : (float) Math.round((translate.getY()-50))/100 * stepY;
        double stepZ = (double) Math.round(Math.abs(position.getZ())+100)/100 * speed.z;
        
        eye.x += (position.getX() > 0   && ((eye.x+stepX) < 4   ) ) ? stepX:( -4    < (eye.x-stepX)) ? -stepX:0;
        eye.y += (position.getY() > 150 && ((eye.y+stepY) < 5   ) ) ? stepY:( -5    < (eye.y-stepY)) ? -stepY:0;
        eye.z += (position.getZ() > 0   && ((eye.z+stepZ) < 10  ) ) ? stepZ:(  2    < (eye.z-stepZ)) ? -stepZ:0;
        
        //eye.x = (float) Math.round(eye.x*100)/100;
        //eye.y = (float) Math.round(eye.y*100)/100;
        //eye.z = (float) Math.round(eye.z*100)/100;
	}
	
	public Point3d getEye(){
		return eye;
	}
	public Point3d getLookAt(){
		return lookAt;
	}
	public Vector3d getUp(){
		return up;
	}
	public Transform3D getCamera(){
        camera.lookAt(eye,lookAt,up);
        camera.invert();
        return camera;
	}
}
