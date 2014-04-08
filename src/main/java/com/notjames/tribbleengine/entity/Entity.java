package com.notjames.tribbleengine.entity;

import com.notjames.tribbleengine.Family;

import java.util.ArrayList;
import java.util.List;

public class Entity {
	private static int singletonIndex = 0;

	private int index;

	private List<Component> components;

	private Family family;

	public Entity() {
		this.index = Entity.singletonIndex++;

		this.family = new Family();
		this.components = new ArrayList<Component>();
	}

	public int getIndex() {
		return this.index;
	}

	public Family getFamily() {
		return this.family;
	}

	public void addComponent(Component component) {
		if (component == null) {
			return;
		}

		this.components.add(component);

		this.family.addComponentType(component.getClass());
	}

	public Component[] getComponents() {
		return this.components.toArray(new Component[this.components.size()]);
	}

	public boolean hasComponent(Component component) {
		return false;
	}
}
