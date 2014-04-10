package com.imnotjames.tribbleengine.engine;

import com.imnotjames.tribbleengine.entity.Entity;

import java.util.EventObject;

public class EngineEntityEvent extends EventObject {
	private Entity target;

	public EngineEntityEvent(Engine source, Entity target) {
		super(source);

		this.target = target;
	}

	public Engine getSource() {
		return (Engine) this.source;
	}

	public Entity getTarget() {
		return this.target;
	}
}