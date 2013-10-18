package com.turpgames.framework.v0.effects;

public interface IEffectEndListener {
	/**
	 * Executed when associated effect ends. For looping effects this method is called repeatedly
	 * @param obj Object instance which is being updated by effect
	 * @return true: Effect ends whether or not it is looping. <br /> false: if effect is looping, it continues running, otherwise it ends
	 */
	boolean onEffectEnd(Object obj);
}
