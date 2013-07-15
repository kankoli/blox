package com.blox.framework.v0;

public interface ICollisionDetectorFactory {
	void setSuccessor(ICollisionDetectorFactory successor);
	ICollisionDetector getDetector(int boundType1, int boundType2);
}
