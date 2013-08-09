package org.leapmotion.ui.main;

import java.awt.BorderLayout;

import javax.media.j3d.Canvas3D;
import javax.media.j3d.TransformGroup;
import javax.swing.JPanel;

import org.leapmotion.ui.model.MainView;
import org.leapmotion.ui.util.Node;
import org.leapmotion.ui.util.ViewerCamera;

import com.sun.j3d.utils.universe.SimpleUniverse;

/**
 * User: Christophe Marchadour
 * Email : christophe.marchadour@gmail.com
 * Date: 07/08/13
 * Time: 21:34
 * Leap motion and Java3D example
 */
public class LeapView extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1975634239509087431L;
	
	private static Canvas3D canvas;
	private static Node scene;
	
	private static SimpleUniverse universe; 
	private static TransformGroup view;
	private static ViewerCamera camera;
	
	public LeapView(){
		this(800,800);
	}
	public LeapView(int width, int height){
        camera 		= new ViewerCamera();
        canvas 		= new Canvas3D(SimpleUniverse.getPreferredConfiguration());
        scene 		= new MainView();
        universe 	= new SimpleUniverse(canvas); 
        view 		= universe.getViewer().getViewingPlatform().getViewPlatformTransform();
        
        setLayout(new BorderLayout());
        setSize(width,height);
        add("Center", canvas);
	}
	
	public void init(){     
        scene.generate();
        scene.compile();
        universe.getViewingPlatform().getViewPlatformTransform().setTransform(camera.getCamera());
        universe.addBranchGraph(scene);
        System.out.println("Leap view initialized");
	}
	
	public void redisplay(){
		//System.out.println("Leap view update");
		//Updating scene
		scene.redisplay();
		camera.redisplay();
		
		//Apply camera changes
		view.setTransform(camera.getCamera());
		repaint();
	}
	
	public Node getScene(){
		return scene;
	}
	
	public static ViewerCamera getCamera(){
		return camera;
	}
}
