package com.imnotjames.tribbleengine.entity;

import com.imnotjames.tribbleengine.Family;

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

		if (this.hasComponent(component)) {
			throw new IllegalArgumentException("Entity already has component of type " + component.getClass());
		}

		this.components.add(component);

		this.family.addComponentType(component.getClass());
	}

	public Component[] getComponents() {
		return this.components.toArray(new Component[this.components.size()]);
	}

	public <T extends Component> T getComponent(Class<T> componentType) {
		for (Component component : this.components) {
			if (component.getClass().equals(componentType)) {
				return componentType.cast(component);
			}
		}

		return null;
	}

	public boolean hasComponent(Component component) {
		return this.hasComponent(component.getClass());
	}

	public boolean hasComponent(Class<? extends Component> componentType) {
		return this.family.hasComponentType(componentType);
	}

	public boolean removeComponent(Component component) {
		return this.removeComponent(component.getClass());
	}

	public boolean removeComponent(Class<? extends Component> componentType) {
		for (Component component : this.components) {
			if (component.getClass().equals(componentType)) {
				this.components.remove(component);
				this.family.removeComponentType(componentType);
				return true;
			}
		}

		return false;
	}

	public void removeComponents() {
		this.family.removeComponentTypes();
		this.components.clear();
	}
}
