package com.fluxnine.mrtipper.android.pong.controller;

import android.app.Activity;
import android.os.Bundle;

import com.fluxnine.mrtipper.android.pong.R;
import com.fluxnine.mrtipper.android.pong.view.GameLoopThread;
import com.fluxnine.mrtipper.android.pong.view.PongSurfaceView;

public class MainActivity extends Activity implements GameLoopObserver {
	private GameLoopThread game_loop_thread;
	private PongSurfaceView pong_view;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.activity_main);
		setContentView(R.layout.pong_layout);

		pong_view = (PongSurfaceView) findViewById(R.id.pong_view);
	}

	@Override
	protected void onResume() {
		super.onResume();
		game_loop_thread = createGameLoop(this);
	}

	@Override
	protected void onPause() {
		super.onPause();
		destroyGameLoop(game_loop_thread);
	}

	@Override
	public void update(float delta_time_in_ms) {
	}

	@Override
	public void render() {
		pong_view.renderScene();
	}

	private GameLoopThread createGameLoop(GameLoopObserver observer) {
		GameLoopThread game_loop_thread = new GameLoopThread(observer);
		game_loop_thread.setRunning(true);
		game_loop_thread.start();
		return game_loop_thread;
	}

	private void destroyGameLoop(GameLoopThread game_loop_thread) {
		boolean retry = true;
		game_loop_thread.setRunning(false);
		while (retry) {
			try {
				game_loop_thread.join();
				retry = false;
			} catch (InterruptedException e) {
			}
		}
	}
}
