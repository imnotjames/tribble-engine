package com.imnotjames.tribbleengine;

import com.imnotjames.tribbleengine.entity.Component;
import com.imnotjames.tribbleengine.entity.Entity;

import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class Family {
	private Set<Class<? extends Component>> componentTypes;

	public Family(Class<? extends Component>... componentTypes) {
		this.componentTypes = new CopyOnWriteArraySet<Class<? extends Component>>();

		if (componentTypes != null) {
			this.componentTypes.addAll(Arrays.asList(componentTypes));
		}
	}

	public void addComponentType(Class<? extends Component> componentType) {
		this.componentTypes.add(componentType);
	}

	public boolean matches(Entity entity) {
		if (this.componentTypes.size() == 0) {
			return true;
		}

		Family other = entity.getFamily();

		if (other == null) {
			return false;
		}

		// So even though this is private we can access it since we're the same class.
		// Crazy, right?
		return (other.componentTypes).containsAll(this.componentTypes);
	}

	public boolean hasComponentType(Class<? extends Component> componentType) {
		return this.componentTypes.contains(componentType);
	}

	public boolean equals(Object object) {
		if (!(object instanceof Family)) {
			return false;
		}

		return ((Family) object).componentTypes.equals(this.componentTypes);
	}
}
