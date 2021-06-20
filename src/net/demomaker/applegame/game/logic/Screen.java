package net.demomaker.applegame.game.logic;

import net.demomaker.applegame.engine.graphics.GameWindow;
import net.demomaker.applegame.engine.object.Box;
import net.demomaker.applegame.game.entity.Entity;
import net.demomaker.applegame.engine.object.Transform;
import net.demomaker.applegame.engine.util.Vector3;

public class Screen {

	private Transform transform = new Transform();
	private Box screenBox = new Box();
	private static Screen instance = null;
	public static Screen getInstance() {
		if(instance == null)
			instance = new Screen(GameWindow.getWidth(), GameWindow.getHeight() - 39);
		return instance;
	}

	private Screen(int width, int height) {
		setSize(width, height);
	}

	public void setSize(int width, int height) {
		transform.setSize(new Vector3<>(width * 1.0f, height * 1.0f, 0f));
		screenBox.setInnerBorderX(0);
		screenBox.setOuterBorderY(0);
		screenBox.setOuterBorderX(width);
		screenBox.setOuterBorderY(height);
	}

	public float getWidth() {
		return transform.getSize().getX();
	}

	public float getHeight() {
		return transform.getSize().getY();
	}



	public boolean entityIsWithin(Entity entity) {
		Vector3<Float> entityPosition = entity.getPosition();
		Vector3<Float> entityTopLeftCorner = entity.getTopLeftCorner();
		Vector3<Float> entityBottomRightCorner = entity.getBottomRightCorner();
		float entityInnerX = entityPosition.getX() + entityTopLeftCorner.getX();
		float entityInnerY = entityPosition.getY() + entityTopLeftCorner.getY();
		float entityOuterX = entityPosition.getX() + entityBottomRightCorner.getX();
		float entityOuterY = entityPosition.getY() + entityBottomRightCorner.getY();
		boolean case1 = screenBox.getTopLeftCorner().getX() < entityInnerX
				&& screenBox.getBottomRightCorner().getX() > entityInnerX
				&& screenBox.getTopLeftCorner().getY() < entityInnerY
				&& screenBox.getBottomRightCorner().getY() > entityInnerY;
		boolean case2 = screenBox.getTopLeftCorner().getX() < entityOuterX
				&& screenBox.getBottomRightCorner().getX() > entityOuterX
				&& screenBox.getTopLeftCorner().getY() < entityOuterY
				&& screenBox.getBottomRightCorner().getY() > entityOuterY;
		return case1 || case2;
	}

	public boolean entityXIsOnLeftSideOfBounds(Entity entity) {
		return entity.getPosition().getX() + entity.getBottomRightCorner().getX() < screenBox.getTopLeftCorner().getX();
	}

	public boolean entityXIsOnRightSideOfBounds(Entity entity) {
		return entity.getPosition().getX() + entity.getTopLeftCorner().getX() > screenBox.getBottomRightCorner().getX();
	}

	public boolean entityYIsOnTopSideOfBounds(Entity entity) {
		return entity.getPosition().getY() + entity.getBottomRightCorner().getY() < screenBox.getTopLeftCorner().getY();
	}

	public boolean entityYIsOnBottomSideOfBounds(Entity entity) {
		return entity.getPosition().getY() + entity.getTopLeftCorner().getY() > screenBox.getBottomRightCorner().getY();
	}
}
