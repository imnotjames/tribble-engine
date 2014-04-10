package com.imnotjames.tribbleengine.engine.events;

import java.util.EventListener;

public interface EngineEntityEventListener extends EventListener {
	public void entityAdded(EngineEntityEvent event);

	public void entityRemoved(EngineEntityEvent event);
}
