package com.imnotjames.tribbleengine.engine;

import com.imnotjames.tribbleengine.Family;
import com.imnotjames.tribbleengine.engine.events.EngineEntityEvent;
import com.imnotjames.tribbleengine.engine.events.EngineEntityEventListener;
import com.imnotjames.tribbleengine.engine.events.EngineSystemEvent;
import com.imnotjames.tribbleengine.engine.events.EngineSystemEventListener;
import com.imnotjames.tribbleengine.entity.Entity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Engine {
	private static Comparator<EngineSystem> engineSystemPriorityComparator = new Comparator<EngineSystem>() {
		@Override
		public int compare(EngineSystem a, EngineSystem b) {
			return b.getPriority() - a.getPriority();
		}
	};

	private List<Entity> entities;

	private List<EngineSystem> engineSystems;

	private List<EngineEntityEventListener> engineEntityEventListeners = new ArrayList<EngineEntityEventListener>();
	private List<EngineSystemEventListener> engineSystemEventListeners = new ArrayList<EngineSystemEventListener>();

	public Engine() {
		this.entities = new ArrayList<Entity>();
		this.engineSystems = new ArrayList<EngineSystem>();
	}

	public void update(long delta) {
		for (EngineSystem s : engineSystems) {
			s.update(delta);
		}
	}

	public void addEntity(Entity entity) {
		this.entities.add(entity);

		EngineEntityEvent event = new EngineEntityEvent(this, entity);

		for (EngineEntityEventListener listener : this.engineEntityEventListeners) {
			listener.entityAdded(event);
		}
	}

	public void removeEntity(Entity entity) {
		// If we don't remove the entity don't fire events
		if (! this.entities.remove(entity)) {
			return;
		}

		EngineEntityEvent event = new EngineEntityEvent(this, entity);

		for (EngineEntityEventListener listener : this.engineEntityEventListeners) {
			listener.entityRemoved(event);
		}
	}

	public void removeEntities() {
		while (this.entities.size() > 0) {
			this.removeEntity(this.entities.get(0));
		}
	}


	public List<Entity> getEntities() {
		return this.entities;
	}

	public List<Entity> getEntities(Family family) {
		if (family == null) {
			return this.entities;
		}

		List<Entity> filteredEntities = new ArrayList<Entity>();

		for (Entity entity : this.entities) {
			if (family.matches(entity)) {
				filteredEntities.add(entity);
			}
		}

		return filteredEntities;
	}

	public void addEngineSystem(EngineSystem engineSystem) {
		this.engineSystems.add(engineSystem);

		engineSystem.setUp(this);

		this.engineSystems.sort(Engine.engineSystemPriorityComparator);

		EngineSystemEvent event = new EngineSystemEvent(this, engineSystem);

		for (EngineSystemEventListener listener : this.engineSystemEventListeners) {
			listener.systemAdded(event);
		}
	}

	public void removeEngineSystem(EngineSystem engineSystem) {
		if (this.engineSystems.remove(engineSystem)) {
			engineSystem.tearDown(this);

			EngineSystemEvent event = new EngineSystemEvent(this, engineSystem);

			for (EngineSystemEventListener listener : this.engineSystemEventListeners) {
				listener.systemRemoved(event);
			}
		}
	}

	public void addEngineEntityEventListener(EngineEntityEventListener listener) {
		this.engineEntityEventListeners.add(listener);
	}

	public void removeEngineEntityEventListener(EngineEntityEventListener listener) {
		this.engineEntityEventListeners.remove(listener);
	}

	public void addEngineSystemEventListener(EngineSystemEventListener listener) {
		this.engineSystemEventListeners.add(listener);
	}

	public void removeEngineSystemEventListener(EngineSystemEventListener listener) {
		this.engineSystemEventListeners.remove(listener);
	}
}
