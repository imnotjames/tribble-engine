package com.imnotjames.tribbleengine.engine.events;

import com.imnotjames.tribbleengine.engine.Engine;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class EngineEventTest {

	private class TestEngineEvent extends EngineEvent {

		public TestEngineEvent(Engine source) {
			super(source);
		}
	}

	@Test
	public void testGetSource() throws Exception {
		final Engine engine = new Engine();
		final EngineEvent engineEvent = new TestEngineEvent(engine);

		Assert.assertSame(engine, engineEvent.getSource());
	}
}