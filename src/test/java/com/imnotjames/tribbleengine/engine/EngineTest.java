package com.imnotjames.tribbleengine.engine;

import com.imnotjames.tribbleengine.Family;
import com.imnotjames.tribbleengine.engine.events.EngineEntityEvent;
import com.imnotjames.tribbleengine.engine.events.EngineEntityEventListener;
import com.imnotjames.tribbleengine.engine.events.EngineSystemEvent;
import com.imnotjames.tribbleengine.engine.events.EngineSystemEventListener;
import com.imnotjames.tribbleengine.entity.Component;
import com.imnotjames.tribbleengine.entity.Entity;
import com.sun.javaws.exceptions.InvalidArgumentException;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Assert;
import org.junit.Test;

public class EngineTest {
	private Mockery context = new Mockery();

	@Test
	public void testUpdateEngineSystems() throws Exception {
		final long expectedDelta = 1234;
		final EngineSystem engineSystem = context.mock(EngineSystem.class);

		final Engine engine = new Engine();

		context.checking(new Expectations() {{
			oneOf (engineSystem).update(expectedDelta);
			oneOf (engineSystem).setUp(engine);
		}});

		engine.addEngineSystem(engineSystem);

		engine.update(expectedDelta);

		context.assertIsSatisfied();
	}

	@Test
	public void testUpdate() {
		Engine engine = new Engine();

		engine.update(1);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testExceptionOnZeroDeltaUpdate() {
		Engine engine = new Engine();

		engine.update(0);
	}

	@Test
	public void testAddEntity() throws Exception {
		final Entity expected = new Entity();

		final Engine engine = new Engine();

		engine.addEntity(expected);
		Assert.assertEquals(1, engine.getEntities().size());
		Assert.assertEquals(expected, engine.getEntities().get(0));
	}

	@Test
	public void testRemoveEntity() throws Exception {
		final Entity entityA =  new Entity();
		final Entity entityB =  new Entity();
		final Entity entityC =  new Entity();
		final Entity entityD =  new Entity();

		final Engine engine = new Engine();

		engine.addEntity(entityA);
		engine.addEntity(entityB);
		engine.addEntity(entityC);
		engine.addEntity(entityD);

		int length = engine.getEntities().size();

		engine.removeEntity(entityC);

		Assert.assertEquals(length - 1, engine.getEntities().size());
		Assert.assertFalse(engine.getEntities().contains(entityC));
	}

	@Test
	public void testRemoveEntities() throws Exception {
		final Entity entityA = new Entity();
		final Entity entityC = new Entity();
		final Entity entityD = new Entity();
		final Entity entityB = new Entity();

		final Component componentA = context.mock(Component.class);

		entityA.addComponent(componentA);
		entityB.addComponent(componentA);

		final Engine engine = new Engine();

		engine.addEntity(entityA);
		engine.addEntity(entityB);
		engine.addEntity(entityC);
		engine.addEntity(entityD);

		engine.removeEntities(new Family(componentA.getClass()));

		Assert.assertEquals(2, engine.getEntities().size());

		Assert.assertSame(entityC, engine.getEntities().get(0));
		Assert.assertSame(entityD, engine.getEntities().get(1));

		engine.removeEntities();

		Assert.assertEquals(0, engine.getEntities().size());
	}

	@Test
	public void testGetEntities() throws Exception {
		final Entity entityA = new Entity();
		final Entity entityB = new Entity();
		final Entity entityC = new Entity();
		final Entity entityD = new Entity();

		final Component componentA = context.mock(Component.class);

		entityA.addComponent(componentA);

		Engine engine = new Engine();

		Assert.assertNotNull(engine.getEngineSystems());

		engine.addEntity(entityA);
		engine.addEntity(entityB);
		engine.addEntity(entityC);
		engine.addEntity(entityD);

		Assert.assertNotNull(engine.getEntities());
		Assert.assertEquals(4, engine.getEntities().size());

		Assert.assertEquals(4, engine.getEntities(null).size());
		Assert.assertEquals(1, engine.getEntities(new Family(componentA.getClass())).size());
	}

	@Test
	public void testGetEngineSystems() throws Exception {
		final EngineSystem mockSystemA = context.mock(EngineSystem.class, "Engine System A");
		final EngineSystem mockSystemB = context.mock(EngineSystem.class, "Engine System B");

		context.checking(new Expectations() {{
			ignoring (mockSystemA);
			ignoring (mockSystemB);
		}});

		Engine engine = new Engine();

		Assert.assertNotNull(engine.getEngineSystems());
		Assert.assertEquals(0, engine.getEngineSystems().size());

		engine.addEngineSystem(mockSystemA);
		engine.addEngineSystem(mockSystemB);

		Assert.assertEquals(2, engine.getEngineSystems().size());
	}

	@Test
	public void testAddEngineSystem() throws Exception {
		final EngineSystem mockSystemA = context.mock(EngineSystem.class, "Engine System A");
		final EngineSystem mockSystemB = context.mock(EngineSystem.class, "Engine System B");
		final EngineSystem mockSystemC = context.mock(EngineSystem.class, "Engine System C");
		final EngineSystem mockSystemD = context.mock(EngineSystem.class, "Engine System D");

		context.checking(new Expectations() {{
			ignoring (mockSystemA);
			ignoring (mockSystemB);
			ignoring (mockSystemC);
			ignoring (mockSystemD);
		}});

		Engine engine = new Engine();

		engine.addEngineSystem(mockSystemA);
		engine.addEngineSystem(mockSystemB);
		engine.addEngineSystem(mockSystemC);
		engine.addEngineSystem(mockSystemD);

		Assert.assertNotNull(engine.getEngineSystems());
		Assert.assertEquals(4, engine.getEngineSystems().size());
		Assert.assertTrue(engine.getEngineSystems().contains(mockSystemC));
	}

	@Test
	public void testRemoveEngineSystem() throws Exception {
		final EngineSystem mockSystemA = context.mock(EngineSystem.class, "Engine System A");
		final EngineSystem mockSystemB = context.mock(EngineSystem.class, "Engine System B");
		final EngineSystem mockSystemC = context.mock(EngineSystem.class, "Engine System C");
		final EngineSystem mockSystemD = context.mock(EngineSystem.class, "Engine System D");

		context.checking(new Expectations() {{
			ignoring (mockSystemA);
			ignoring (mockSystemB);
			ignoring (mockSystemC);
			ignoring (mockSystemD);
		}});

		Engine engine = new Engine();

		engine.addEngineSystem(mockSystemA);
		engine.addEngineSystem(mockSystemB);
		engine.addEngineSystem(mockSystemC);
		engine.addEngineSystem(mockSystemD);

		engine.removeEngineSystem(mockSystemC);

		Assert.assertNotNull(engine.getEngineSystems());
		Assert.assertEquals(3, engine.getEngineSystems().size());
		Assert.assertFalse(engine.getEngineSystems().contains(mockSystemC));
	}

	@Test
	public void testRemoveEngineSystems() throws Exception {
		final EngineSystem mockSystemA = context.mock(EngineSystem.class, "Engine System A");
		final EngineSystem mockSystemB = context.mock(EngineSystem.class, "Engine System B");
		final EngineSystem mockSystemC = context.mock(EngineSystem.class, "Engine System C");
		final EngineSystem mockSystemD = context.mock(EngineSystem.class, "Engine System D");

		context.checking(new Expectations() {{
			ignoring (mockSystemA);
			ignoring (mockSystemB);
			ignoring (mockSystemC);
			ignoring (mockSystemD);
		}});

		Engine engine = new Engine();

		engine.addEngineSystem(mockSystemA);
		engine.addEngineSystem(mockSystemB);
		engine.addEngineSystem(mockSystemC);
		engine.addEngineSystem(mockSystemD);

		Assert.assertNotNull(engine.getEngineSystems());

		int oldSize = engine.getEngineSystems().size();

		engine.removeEngineSystems();

		Assert.assertNotNull(engine.getEngineSystems());
		Assert.assertNotEquals(oldSize, engine.getEngineSystems().size());
		Assert.assertEquals(0, engine.getEngineSystems().size());
	}

	@Test
	public void testEngineSystemEvents() {
		final EngineSystemEventListener engineSystemEventListener = context.mock(EngineSystemEventListener.class);
		final Engine engine = new Engine();

		final EngineSystem engineSystem = context.mock(EngineSystem.class);

		context.checking(new Expectations() {{
			allowing (engineSystem).setUp(engine);
			allowing (engineSystem).tearDown(engine);
			oneOf (engineSystemEventListener).systemAdded(with(any(EngineSystemEvent.class)));
			oneOf (engineSystemEventListener).systemRemoved(with(any(EngineSystemEvent.class)));
		}});

		engine.addEngineSystemEventListener(engineSystemEventListener);

		engine.addEngineSystem(engineSystem);
		engine.removeEngineSystem(engineSystem);

		engine.removeEngineSystemEventListener(engineSystemEventListener);

		// Since we have removed the listener we won't get these invoked,
		// and the oneOf will assert true

		engine.addEngineSystem(engineSystem);
		engine.removeEngineSystem(engineSystem);

		context.assertIsSatisfied();
	}

	@Test
	public void testEngineEntityEvents() {
		final EngineEntityEventListener engineEntityEventListener = context.mock(EngineEntityEventListener.class);
		final Engine engine = new Engine();

		final Entity entityA = new Entity();

		context.checking(new Expectations() {{
			oneOf (engineEntityEventListener).entityAdded(with(any(EngineEntityEvent.class)));
			oneOf (engineEntityEventListener).entityRemoved(with(any(EngineEntityEvent.class)));
		}});

		engine.addEngineEntityEventListener(engineEntityEventListener);

		engine.addEntity(entityA);
		engine.removeEntity(entityA);

		// Since we've already removed entityA the event won't fire again
		// Thus oneof will be satisfied
		engine.removeEntity(entityA);

		engine.removeEngineEntityEventListener(engineEntityEventListener);

		// We've removed the listener, so the oneof should still be satisfied

		engine.addEntity(entityA);
		engine.removeEntity(entityA);

		context.assertIsSatisfied();
	}
}
