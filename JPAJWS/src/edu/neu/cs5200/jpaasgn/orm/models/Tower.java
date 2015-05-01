package edu.neu.cs5200.jpaasgn.orm.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Tower {
	@Id
	private int id;
	private String name;
	private double height;
	private int sides;
	private int siteid;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public int getSides() {
		return sides;
	}
	public void setSides(int sides) {
		this.sides = sides;
	}
	public int getSiteid() {
		return siteid;
	}
	public void setSiteid(int siteid) {
		this.siteid = siteid;
	}
	public Tower(int id, String name, double height, int sides, int siteid) {
		super();
		this.id = id;
		this.name = name;
		this.height = height;
		this.sides = sides;
		this.siteid = siteid;
	}
	public Tower() {
		super();
	}
	
}

