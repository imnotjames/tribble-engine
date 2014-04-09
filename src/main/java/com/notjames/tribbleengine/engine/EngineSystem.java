package com.notjames.tribbleengine.engine;

public interface EngineSystem {
	public void setUp(Engine engine);

	public void tearDown(Engine engine);

	public int getPriority();

	public void update(long delta);
}
