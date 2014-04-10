package com.imnotjames.tribbleengine.engine.events;

import com.imnotjames.tribbleengine.engine.Engine;
import com.imnotjames.tribbleengine.entity.Entity;

import java.util.EventObject;

public abstract class EngineEvent extends EventObject {
	private int id;

	public EngineEvent(Engine source) {
		super(source);
	}

	public Engine getSource() {
		return (Engine) this.source;
	}
}
