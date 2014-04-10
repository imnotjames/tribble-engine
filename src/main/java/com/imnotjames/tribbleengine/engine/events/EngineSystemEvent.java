package com.imnotjames.tribbleengine.engine.events;

import com.imnotjames.tribbleengine.engine.Engine;
import com.imnotjames.tribbleengine.engine.EngineSystem;

import java.util.EventObject;

public class EngineSystemEvent extends EngineEvent {
	private EngineSystem target;

	public EngineSystemEvent(Engine source, EngineSystem target) {
		super(source);

		this.target = target;
	}

	public EngineSystem getTargetEngineSystem() {
		return this.target;
	}
}
