package org.leapmotion.ui.util;

import javax.media.j3d.Appearance;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.TransformGroup;

public abstract class Node extends BranchGroup{

	protected Appearance appearance;
	protected TransformGroup view;
	
	public Node(){
		appearance	= new Appearance();
		view 		= new TransformGroup();
	}
	
	public abstract void generate();	
	public abstract void redisplay();
}
