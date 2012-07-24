package com.fluxnine.mrtipper.android.pong.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class PongSurfaceView extends SurfaceView implements
		SurfaceHolder.Callback {

	private boolean isValid;
	private final Paint background = new Paint();
	private final Paint p1_paddle = new Paint();

	public PongSurfaceView(Context context, AttributeSet attrs) {
		super(context, attrs);

		SurfaceHolder holder = getHolder();
		holder.addCallback(this);
		setFocusable(true);
		isValid = false;
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		isValid = true;
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		isValid = false;
	}

	@Override
	public void onDraw(Canvas canvas) {

		// draw background
		// background.setColor(getResources().getColor(R.color.background));
		background.setColor(Color.BLACK);
		canvas.drawRect(0, 0, getWidth(), getHeight(), background);

		// draw p1 paddle
		p1_paddle.setColor(Color.GREEN);
		canvas.drawRect(5, 5, 80, 20, p1_paddle);
	}

	public void renderScene() {
		if (isValid == false) {
			return;
		}

		Canvas canvas = null;
		SurfaceHolder holder = getHolder();
		try {
			canvas = holder.lockCanvas();
			synchronized (holder) {
				onDraw(canvas);
			}
		} finally {
			if (canvas != null) {
				holder.unlockCanvasAndPost(canvas);
			}
		}
	}
}
