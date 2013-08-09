package org.leapmotion.ui.util;

import java.util.ArrayList;
import java.util.List;

import javax.media.j3d.Appearance;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.TransformGroup;

/**
 * User: Christophe Marchadour
 * Email : christophe.marchadour@gmail.com
 * Date: 07/08/13
 * Time: 21:34
 * Leap motion and Java3D example
 */
public abstract class Node extends BranchGroup{

	protected Appearance appearance;
	protected TransformGroup view;
	protected List<Node> childs;
	
	public Node(){
		appearance	= new Appearance();
		view 		= new TransformGroup();
		childs 		= new ArrayList<Node>();
	}
	
	public abstract void generate();	
	public abstract void redisplay();
	
	protected void generateChilds(){
		for(Node node : childs){
			node.generate();
			node.compile();
			addChild(node);
		}
	}
	protected void redisplayChilds(){
		for(Node node : childs) node.redisplay();	
	}
}
