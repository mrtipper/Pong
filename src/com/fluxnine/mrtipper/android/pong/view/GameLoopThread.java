package com.fluxnine.mrtipper.android.pong.view;

import com.fluxnine.mrtipper.android.pong.controller.GameLoopObserver;

public class GameLoopThread extends Thread {
	// private static final String TAG = GameLoopThread.class.getSimpleName();
	private static final int MAX_FPS = 30;// 1;
	private static final long MILLISECS_PER_FRAME = 1000 / MAX_FPS;
	private static final int MAX_SKIPPED_RENDERS = 10;

	private final GameLoopObserver observer;
	private boolean running = false;

	public GameLoopThread(GameLoopObserver observer) {
		super();
		this.observer = observer;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	@Override
	public void run() {
		long next_game_tick = System.currentTimeMillis();
		while (running) {
			int renders_skipped = 0;
			long current_game_tick = System.currentTimeMillis();
			while (current_game_tick > next_game_tick
					&& renders_skipped < MAX_SKIPPED_RENDERS) {
				this.observer.update(MILLISECS_PER_FRAME);
				next_game_tick += MILLISECS_PER_FRAME;
				renders_skipped++;
			}

			this.observer.render();
		}
	}
}
