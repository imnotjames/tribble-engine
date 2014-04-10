package com.imnotjames.tribbleengine.engine;

import com.imnotjames.tribbleengine.Family;
import com.imnotjames.tribbleengine.entity.Entity;

import java.util.ArrayList;
import java.util.List;

public class Engine {
	private List<Entity> entities;

	private List<EngineSystem> engineSystems;

	private List<EngineEventListener> listeners;

	public Engine() {
		this.entities = new ArrayList<Entity>();
		this.engineSystems = new ArrayList<EngineSystem>();
		this.listeners = new ArrayList<EngineEventListener>();
	}

	public void update(long delta) {
		for (EngineSystem s : engineSystems) {
			s.update(delta);
		}
	}

	public void addEntity(Entity entity) {
		this.entities.add(entity);

		EngineEntityEvent event = new EngineEntityEvent(this, entity);

		for (EngineEventListener listener : this.listeners) {
			listener.entityAdded(event);
		}
	}

	public void removeEntity(Entity entity) {
		// If we don't remove the entity don't fire events
		if (! this.entities.remove(entity)) {
			return;
		}

		EngineEntityEvent event = new EngineEntityEvent(this, entity);

		for (EngineEventListener listener : this.listeners) {
			listener.entityRemoved(event);
		}
	}

	public void removeAllEntities() {
		while (this.entities.size() > 0) {
			this.removeEntity(this.entities.get(0));
		}
	}


	public List<Entity> getEntities() {
		return this.entities;
	}

	public List<Entity> getEntities(Family family) {
		List<Entity> filteredEntities = new ArrayList<Entity>();

		for (Entity entity : this.entities) {
			if (family.matches(entity)) {
				filteredEntities.add(entity);
			}
		}

		return filteredEntities;
	}

	public void addSystem(EngineSystem engineSystem) {
		this.engineSystems.add(engineSystem);

		engineSystem.setUp(this);

		// this.engineSystems.sort();
	}

	public void removeSystem(EngineSystem engineSystem) {
		if (this.engineSystems.remove(engineSystem)) {
			engineSystem.tearDown(this);
		}
	}

	public void addEventListener(EngineEventListener listener) {
		this.listeners.add(listener);
	}

	public void removeEventListener(EngineEventListener listener) {
		this.listeners.remove(listener);
	}
}
