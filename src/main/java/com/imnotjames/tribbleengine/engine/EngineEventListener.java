package com.imnotjames.tribbleengine.engine;

import java.util.EventListener;

public interface EngineEventListener extends EventListener {
	public void entityAdded(EngineEntityEvent event);

	public void entityRemoved(EngineEntityEvent event);
}
