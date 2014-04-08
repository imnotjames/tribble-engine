package com.notjames.tribbleengine.engine;

import com.notjames.tribbleengine.engine.EngineEntityEvent;

import java.util.EventListener;

/**
 * Created by james on 4/8/14.
 */
public interface EngineEventListener extends EventListener {
	public void entityAdded(EngineEntityEvent event);

	public void entityRemoved(EngineEntityEvent event);
}
