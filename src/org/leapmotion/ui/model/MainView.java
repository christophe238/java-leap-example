package org.leapmotion.ui.model;

import org.leapmotion.ui.util.Node;

public class MainView extends Node{

	private Cube cube = new Cube();
	private Axis axis = new Axis();
	
	@Override
	public void generate() {		
		cube.generate();
		axis.generate();
		cube.compile();
		axis.compile();
		this.addChild(cube);
		this.addChild(axis);
	}

	@Override
	public void redisplay() {
		//System.out.println("Main view update");
		cube.redisplay();
		axis.redisplay();
		
	}

}
