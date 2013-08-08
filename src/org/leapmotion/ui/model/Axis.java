package org.leapmotion.ui.model;

import java.awt.Font;

import javax.media.j3d.Font3D;
import javax.media.j3d.FontExtrusion;
import javax.media.j3d.GeometryArray;
import javax.media.j3d.LineArray;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Text3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Point3f;

import org.leapmotion.ui.util.Node;

/**
 * User: Christophe Marchadour
 * Email : christophe.marchadour@gmail.com
 * Date: 07/08/13
 * Time: 21:34
 * Leap motion and Java3D example
 */
public class Axis extends Node{

	@Override
	public void generate() {
		view.addChild(createAxis());

        float number = 20;//Number of spheres on each axis

        for(int e =0; e<=3; e++){
             float start = -1.0f;
             for(int i =0; i<=number; i++){
            	 switch(e){
                  case 0:
			        if(i == number-1)
			        	view.addChild(createName("X",8*start,0.05f,0.0f,e));
			        break;
                  case 1:
			        if(i == number-1)
			        	view.addChild(createName("Y",8*start,0.05f,0.0f,e));
			        break;
                  case 2:
			        if(i == number-1)
			        	view.addChild(createName("Z",8*start,0.05f,0.0f,e));
			        break;
            	 }
             start +=(float) (2.0f/number);
             }
        }
        
		addChild(view);
	}

	@Override
	public void redisplay() {
		
		
	}

	private Shape3D createAxis()
    {
    	
	    Shape3D axis = new Shape3D();
	    Point3f[] point = {
		    new Point3f( -1.0f,0.0f,0.0f),//X
		    new Point3f( 1.0f,0.0f,0.0f),
		
		    new Point3f( 0.0f,-1.0f,0.0f),
		    new Point3f( 0.0f, 1.0f,0.0f),//Y
		
		    new Point3f( 0.0f, 0.0f,-1.0f),//Z
		    new Point3f( 0.0f, 0.0f,1.0f),
	    	};
	
	    LineArray geom = new LineArray(point.length, GeometryArray.COORDINATES);
	    geom.setCoordinates(0, point);
	    axis.setGeometry(geom);
	    axis.setAppearance(appearance);
	
	    return axis;
    }

    private TransformGroup createName(String name,float xPos,float yPos,float zPos, int rotCase)
    {
         TransformGroup trans = new TransformGroup();
         TransformGroup holdRot = new TransformGroup();
         Transform3D settingScale = new Transform3D();
         Transform3D rotate = new Transform3D();

         switch(rotCase){
	         case 0:
	              rotate.rotX(0);
	         break;
	         case 1:
	              rotate.rotZ(Math.PI/2.0d);
	         break;
	         case 2:
	              rotate.rotY(-Math.PI/2.0d);
	         break;
         }
         settingScale.setScale(.1f);
         trans.setTransform(settingScale);
         holdRot.setTransform(rotate);
         holdRot.addChild(trans);

	    Font3D font = new Font3D(new Font("TestFont", Font.PLAIN,1),
	                   new FontExtrusion());
	    Text3D txt = new Text3D(font, name, new Point3f(xPos,yPos, zPos));
	    Shape3D someText = new Shape3D();
	    someText.setGeometry(txt);
	    someText.setAppearance(appearance);
	    trans.addChild(someText);
	    return holdRot;
    }
}
