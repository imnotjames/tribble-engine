package com.notjames.tribbleengine;

import com.notjames.tribbleengine.engine.Engine;
import com.notjames.tribbleengine.entity.Component;
import com.notjames.tribbleengine.entity.Entity;

public class Test {
	private static class Position implements Component {
		private int x;
		private int y;

		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) {
		Engine engine = new Engine();

		Entity t = new Entity();
		t.addComponent(new Position(0, 0));

		engine.addEntity(t);

		Entity b = new Entity();
		engine.addEntity(b);

		engine.update(100);

		System.out.println(engine.getEntities());

		System.out.println(engine.getEntities(new Family(Position.class)));
	}
}
