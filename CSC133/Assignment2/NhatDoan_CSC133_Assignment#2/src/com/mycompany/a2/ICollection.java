package com.mycompany.a2;


interface ICollection {
	public void add(GameObject gO);
	public IIterator getIterator();
	public void remove(GameObject gO);
}
