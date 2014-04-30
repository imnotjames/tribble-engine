package com.imnotjames.tribbleengine.entity;

import com.imnotjames.tribbleengine.Family;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

public class EntityTest {
	private Mockery context = new Mockery();

	private class Foo implements Component {

	}

	private class Bar implements Component {

	}

	@Test
	public void testGetIndex() {
		Entity a = new Entity();
		Entity b = new Entity();

		Assert.assertFalse("index changes on each entity", a.getIndex() == b.getIndex());
	}

	@Test
	public void testGetFamily() {
		final Family expectFamily = new Family(Foo.class, Bar.class);

		Entity e = new Entity();

		e.addComponent(new Foo());
		e.addComponent(new Bar());

		Family family = e.getFamily();

		Assert.assertTrue(family.equals(expectFamily));
	}

	@Test
	public void testAddComponent() {
		Entity entity = new Entity();

		entity.addComponent(new Foo());
		entity.addComponent(new Bar());
	}

	@Test
	public void testAddNullComponent() {
		Entity entity = new Entity();

		entity.addComponent(null);

		Assert.assertEquals(0, entity.getComponents().length);
	}

	@Test(expected = java.lang.IllegalArgumentException.class)
	public void testAddDuplicateComponents() {
		Entity entity = new Entity();

		entity.addComponent(new Foo());
		entity.addComponent(new Foo());
	}

	@Test
	public void testGetComponents() {
		Entity entity = new Entity();

		entity.addComponent(new Component() { });
		entity.addComponent(new Component() { });

		Assert.assertTrue(entity.getComponents().length == 2);
	}

	@Test
	public void testGetComponent() {
		Entity entity = new Entity();

		Component expected = new Component() {};

		entity.addComponent(expected);

		Assert.assertEquals(expected, entity.getComponent(expected.getClass()));
	}

	@Test
	public void testGetNonexistentComponent() {
		Entity entity = new Entity();

		entity.addComponent(context.mock(Component.class));

		Assert.assertNull(entity.getComponent(Foo.class));
	}

	@Test
	public void testHasComponent() {
		Entity e = new Entity();

		e.addComponent(new Foo());
		e.addComponent(new Bar());

		Assert.assertTrue(e.hasComponent(Foo.class));
	}

	@Test
	public void testRemoveComponent() {
		Component componentA = new Component() {};
		Component componentB = new Component() {};

		Entity e = new Entity();

		e.addComponent(componentA);
		e.addComponent(componentB);

		Assert.assertTrue(e.removeComponent(componentB));

		Assert.assertTrue(e.hasComponent(componentA.getClass()));
		Assert.assertFalse(e.hasComponent(componentB.getClass()));

		Assert.assertTrue(e.removeComponent(componentA.getClass()));

		Assert.assertFalse(e.hasComponent(componentA.getClass()));

		// ComponentB doesn't exist, so we expect this to not remove
		Assert.assertFalse(e.removeComponent(componentB.getClass()));
	}

	@Test
	public void testRemoveComponents() {
		Entity e = new Entity();

		e.addComponent(new Component() {});
		e.addComponent(new Component() {});

		e.removeComponents();

		Assert.assertEquals(0, e.getComponents().length);
	}
}
