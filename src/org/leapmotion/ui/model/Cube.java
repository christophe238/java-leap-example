package org.leapmotion.ui.model;

import javax.media.j3d.BoundingBox;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.Material;
import javax.media.j3d.PolygonAttributes;
import javax.media.j3d.QuadArray;
import javax.media.j3d.RenderingAttributes;
import javax.media.j3d.Shape3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3f;

import org.leapmotion.leap.util.LeapData;
import org.leapmotion.ui.main.LeapView;
import org.leapmotion.ui.util.Node;

import com.leapmotion.leap.Hand;
import com.leapmotion.leap.HandList;
import com.sun.j3d.utils.geometry.Sphere;
/**
 * User: Christophe Marchadour
 * Email : christophe.marchadour@gmail.com
 * Date: 07/08/13
 * Time: 21:34
 * Leap motion and Java3D example
 */
public class Cube extends Node{

	private float cube_size = 0.3f;
	private QuadArray lsa 			= new QuadArray(48,QuadArray.COORDINATES|QuadArray.NORMALS);
	private Shape3D shape			= new Shape3D();
    private PolygonAttributes pa	= new PolygonAttributes();
	private Material material		= new Material();
    private Vector3f [] normals		= new Vector3f[24];
    private Point3f [] points		= new Point3f[24];
    private Color3f [] colors		= new Color3f[24];
    private RenderingAttributes ra	= new RenderingAttributes();
    private ColoringAttributes ca	= new ColoringAttributes();
    private DirectionalLight viewer_light = new DirectionalLight();
    
	@Override
	public void generate() {
		//Initializing
        for(int i=0;i<24;i++)normals[i]=new Vector3f();
        for(int i=0;i<24;i++)points[i]=new Point3f();
        for(int i=0;i<24;i++)colors[i]=new Color3f(0.5f,0.5f,0.5f);
        
        //cube=6 quads 
        //first quad
        points[0].x=-cube_size;points[0].y=-cube_size;points[0].z=-cube_size;
        points[1].x=-cube_size;points[1].y=-cube_size;points[1].z=cube_size;
        points[2].x=-cube_size;points[2].y=cube_size;points[2].z=cube_size;
        points[3].x=-cube_size;points[3].y=cube_size;points[3].z=-cube_size;
        normals[0].x=-1;normals[1].x=-1;normals[2].x=-1;normals[3].x=-1;
        //second quad
        points[4].x=cube_size;points[4].y=-cube_size;points[4].z=-cube_size;
        points[5].x=cube_size;points[5].y=-cube_size;points[5].z=cube_size;
        points[6].x=cube_size;points[6].y=cube_size;points[6].z=cube_size;
        points[7].x=cube_size;points[7].y=cube_size;points[7].z=-cube_size;
        normals[4].x=1;normals[5].x=1;normals[6].x=1;normals[7].x=1;
        //third quad
        points[8].x=-cube_size;points[8].y=-cube_size;points[8].z=-cube_size;
        points[9].x=cube_size;points[9].y=-cube_size;points[9].z=-cube_size;
        points[10].x=cube_size;points[10].y=cube_size;points[10].z=-cube_size;
        points[11].x=-cube_size;points[11].y=cube_size;points[11].z=-cube_size;
        normals[8].z=-1;normals[9].z=-1;normals[10].z=-1;normals[11].z=-1;
        //fourth quad
        points[12].x=-cube_size;points[12].y=-cube_size;points[12].z=cube_size;
        points[13].x=cube_size;points[13].y=-cube_size;points[13].z=cube_size;
        points[14].x=cube_size;points[14].y=cube_size;points[14].z=cube_size;
        points[15].x=-cube_size;points[15].y=cube_size;points[15].z=cube_size;
        normals[12].z=1;normals[13].z=1;normals[14].z=1;normals[15].z=1;
        //fifth quad
        points[16].x=-cube_size;points[16].y=-cube_size;points[16].z=-cube_size;
        points[17].x=-cube_size;points[17].y=-cube_size;points[17].z=cube_size;
        points[18].x=cube_size;points[18].y=-cube_size;points[18].z=cube_size;
        points[19].x=cube_size;points[19].y=-cube_size;points[19].z=-cube_size;
        normals[16].y=-1;normals[17].y=-1;normals[18].y=-1;normals[19].y=-1;
        //sixth quad
        points[20].x=-cube_size;points[20].y=cube_size;points[20].z=-cube_size;
        points[21].x=-cube_size;points[21].y=cube_size;points[21].z=cube_size;
        points[22].x=cube_size;points[22].y=cube_size;points[22].z=cube_size;
        points[23].x=cube_size;points[23].y=cube_size;points[23].z=-cube_size;
        normals[20].y=1;normals[21].y=1;normals[22].y=1;normals[23].y=1;
        
        lsa.setNormals(0, normals);
        lsa.setCoordinates(0, points);
                
        pa.setPolygonMode(PolygonAttributes.POLYGON_FILL);
        pa.setCullFace(PolygonAttributes.CULL_NONE);
        
        //material.setEmissiveColor(new Color3f(0.9f,0.2f,0.2f));
        //material.setAmbientColor(new Color3f(0.6f,0.6f,0.6f));
        material.setDiffuseColor(new Color3f(1f,1f,1f));
        //material.setSpecularColor(new Color3f(1f,0.3f,0.2f));
        material.setLightingEnable(true);
        
        ra.setIgnoreVertexColors(true);
        
        ca.setShadeModel(ColoringAttributes.SHADE_GOURAUD);
        ca.setColor(new Color3f(1f,1f,1f));
        
        appearance.setColoringAttributes(ca);
        appearance.setRenderingAttributes(ra);   
        appearance.setMaterial(material);
        appearance.setPolygonAttributes(pa);
        
        shape.setGeometry(lsa);
        shape.setAppearance(appearance);
        shape.setPickable(true); 
        
        view.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        view.addChild(shape);
        
        view.addChild(new Sphere(0.35f,1,128));
        
        DirectionalLight light1=new DirectionalLight();
        DirectionalLight light2=new DirectionalLight();
        
        light1.setInfluencingBounds(new BoundingBox());
        light1.setColor(new Color3f(1f,0f,0f));
        light1.setDirection(new Vector3f(0,7,0));
        
        light2.setInfluencingBounds(new BoundingBox());
        light2.setColor(new Color3f(0f,1f,0f));
        light2.setDirection(new Vector3f(0,-7,0));
                
        viewer_light.setCapability(DirectionalLight.ALLOW_STATE_WRITE);
        viewer_light.setCapability(DirectionalLight.ALLOW_STATE_READ);
        viewer_light.setCapability(DirectionalLight.ALLOW_DIRECTION_WRITE);
        viewer_light.setCapability(DirectionalLight.ALLOW_DIRECTION_READ);
        viewer_light.setInfluencingBounds(new BoundingSphere());
        viewer_light.setColor(new Color3f(1f,1f,1f));
        viewer_light.setEnable(false);
        
        view.addChild(viewer_light);
        view.addChild(light1);
        view.addChild(light2);
        
        addChild(view);
	}

	@Override
	public void redisplay() {
		//System.out.println("Cube update");
		HandList handList = LeapData.getHandList();
		if(!handList.empty()){
			if(!viewer_light.getEnable()) viewer_light.setEnable(true);
			
			Hand hand = handList.get(0);
			hand.direction();
			Vector3f direction = new Vector3f(hand.direction().getX(),hand.direction().getY(),hand.direction().getZ());
			viewer_light.setDirection(direction);
			
		}
		else if(viewer_light.getEnable()){
			viewer_light.setEnable(false);
		}
		
	}

}
