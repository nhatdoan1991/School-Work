package com.mycompany.a3;

public interface ICollider {

	boolean collidewith(GameObject otherobject);
	void handleCollision(GameObject otherobject);
}
