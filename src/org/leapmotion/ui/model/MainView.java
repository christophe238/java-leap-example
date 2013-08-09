package org.leapmotion.ui.model;

import java.util.ArrayList;
import java.util.List;

import org.leapmotion.ui.util.Node;

/**
 * User: Christophe Marchadour
 * Email : christophe.marchadour@gmail.com
 * Date: 07/08/13
 * Time: 21:34
 * Leap motion and Java3D example
 */
public class MainView extends Node{

	
	public MainView(){
		childs.add(new Cube());
		childs.add(new Axis());
	}
	@Override
	public void generate() {
		generateChilds();
	}

	@Override
	public void redisplay() {
		//System.out.println("Main view update");
		redisplayChilds();	
	}

}
