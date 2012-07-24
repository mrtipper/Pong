package com.fluxnine.mrtipper.android.pong.controller;

public interface GameLoopObserver {
	public void update(float delta_time_in_ms);

	public void render();
}
