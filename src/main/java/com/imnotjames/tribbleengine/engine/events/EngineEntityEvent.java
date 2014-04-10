package com.imnotjames.tribbleengine.engine.events;

import com.imnotjames.tribbleengine.engine.Engine;
import com.imnotjames.tribbleengine.entity.Entity;

import java.util.EventObject;

public class EngineEntityEvent extends EngineEvent {
	private Entity target;

	public EngineEntityEvent(Engine source, Entity target) {
		super(source);

		this.target = target;
	}

	public Entity getTargetEntity() {
		return this.target;
	}
}
