package com.imnotjames.tribbleengine.engine.events;

import com.imnotjames.tribbleengine.engine.Engine;
import com.imnotjames.tribbleengine.engine.EngineSystem;
import com.imnotjames.tribbleengine.entity.Entity;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class EngineSystemEventTest {

	private class TestEngineSystem implements EngineSystem {
		@Override
		public void setUp(Engine engine) {}

		@Override
		public void tearDown(Engine engine) {}

		@Override
		public int getPriority() { return 0; }

		@Override
		public void update(long delta) {}
	}

	@Test
	public void testGetTargetEngineSystem() throws Exception {
		final Engine engine = new Engine();
		final EngineSystem engineSystem = new TestEngineSystem();

		final EngineSystemEvent engineSystemEvent = new EngineSystemEvent(engine, engineSystem);

		Assert.assertSame(engine, engineSystemEvent.getSource());
		Assert.assertSame(engineSystem, engineSystemEvent.getTargetEngineSystem());
	}
}