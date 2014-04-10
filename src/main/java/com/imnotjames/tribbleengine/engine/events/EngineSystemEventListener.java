package com.imnotjames.tribbleengine.engine.events;

import com.imnotjames.tribbleengine.engine.events.EngineSystemEvent;

import java.util.EventListener;

public interface EngineSystemEventListener extends EventListener {
	public void systemAdded(EngineSystemEvent event);

	public void systemRemoved(EngineSystemEvent event);
}
