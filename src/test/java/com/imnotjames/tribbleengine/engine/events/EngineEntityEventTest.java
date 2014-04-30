package com.imnotjames.tribbleengine.engine.events;

import com.imnotjames.tribbleengine.engine.Engine;
import com.imnotjames.tribbleengine.entity.Entity;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class EngineEntityEventTest {

	@Test
	public void testGetTargetEntity() {
		final Engine engine = new Engine();
		final Entity entity = new Entity();

		final EngineEntityEvent engineEntityEvent = new EngineEntityEvent(engine, entity);

		Assert.assertSame(engine, engineEntityEvent.getSource());
		Assert.assertSame(entity, engineEntityEvent.getTargetEntity());
	}
}