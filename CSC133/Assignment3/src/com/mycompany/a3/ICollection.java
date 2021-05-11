package com.mycompany.a3;


interface ICollection {
	public void add(GameObject gO);
	public IIterator getIterator();
	public void remove(GameObject gO);
	public int getSize();
}
