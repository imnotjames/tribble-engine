package com.imnotjames.tribbleengine;

import com.imnotjames.tribbleengine.entity.Component;
import com.imnotjames.tribbleengine.entity.Entity;
import org.jmock.Mockery;
import org.junit.Assert;
import org.junit.Test;

public class FamilyTest {
	private Mockery context = new Mockery();

	@Test
	public void testAddComponentType() throws Exception {

	}

	@Test
	public void testMatches() throws Exception {
		final Component componentA = new Component() {};
		final Component componentB = new Component() {};

		Family family = new Family(componentA.getClass());

		Family familyTrue = new Family(componentA.getClass());
		Family familyFalse = new Family(componentA.getClass(), componentB.getClass());

		Assert.assertTrue(familyTrue.matches(family));

		Assert.assertFalse(familyFalse.matches(family));
	}

	@Test
	public void testMatchesEmpty() {
		final Entity entity = new Entity();

		Family familyA = new Family();
		Family familyB = new Family(Component.class);

		Assert.assertTrue(familyA.matches(familyB));
	}

	@Test
	public void testNoMatchesNullEntity() {
		Family family = new Family(Component.class);

		Assert.assertFalse(family.matches(null));
	}

	@Test
	public void testEquals() {
		final Family familyA = new Family(Component.class);
		final Family familyB = new Family(Component.class);
		final Family familyC = new Family();

		Assert.assertTrue(familyA.equals(familyB));
		Assert.assertFalse(familyA.equals(familyC));
		Assert.assertFalse(familyA.equals(context.mock(Component.class)));
	}

	@Test
	public void testRemoveComponentTypes() {
		final Family familyA = new Family(Component.class);
		final Family familyB = new Family(Component.class);

		familyA.removeComponentTypes();

		Assert.assertFalse(familyA.equals(familyB));

		familyB.removeComponentTypes();

		Assert.assertTrue(familyA.equals(familyB));
	}

	@Test
	public void testRemoveComponentType() {
		final Component componentA = new Component() {};
		final Component componentB = new Component() {};

		final Family familyA = new Family(componentA.getClass(), componentB.getClass());
		final Family familyB = new Family(componentA.getClass(), componentB.getClass());

		familyA.removeComponentType(componentA.getClass());

		Assert.assertFalse(familyA.equals(familyB));

		familyB.removeComponentType(componentA.getClass());

		Assert.assertTrue(familyA.equals(familyB));
	}
}
