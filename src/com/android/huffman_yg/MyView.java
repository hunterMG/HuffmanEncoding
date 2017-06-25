package com.android.huffman_yg;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MyView extends SurfaceView implements SurfaceHolder.Callback{

	Paint paint = null;
	Paint paintText = null;
	public MyView(Context context, AttributeSet attrs) {
		super(context,attrs);
		// TODO Auto-generated constructor stub
		getHolder().addCallback(this);
		paint = new Paint();
		paint.setColor(Color.CYAN);
		paintText = new Paint();
		paintText.setColor(Color.BLUE);
	}

	public void draw(String str1, String str2, String str3) {
		Canvas canvas = getHolder().lockCanvas();
		canvas.drawColor(Color.WHITE);//将View的背景置为白色
		paintText.setTextSize(40);
		int x=200, y=300, radius = 20, dis=15;
		canvas.drawCircle(x, y, radius, paint);
		canvas.drawCircle(x+x, y, radius, paint);
		canvas.drawCircle(x+x/2, y-x, radius, paint);
		canvas.drawText(str1, x-dis, y+dis, paintText);
		canvas.drawText(str2, x+x-dis, y+dis, paintText);
		canvas.drawText(str3, x+x/2-dis, y-x+dis, paintText);
		canvas.drawLine(x, y, x+x/2, y-x, paint);
		canvas.drawLine(x+x, y, x+x/2, y-x, paint);
		getHolder().unlockCanvasAndPost(canvas);
	}
	public void clear(){
		Canvas canvas = getHolder().lockCanvas();
		canvas.drawColor(Color.WHITE);//将View的背景置为白色
		getHolder().unlockCanvasAndPost(canvas);
	}
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		Canvas canvas = getHolder().lockCanvas();//锁定canvas
		canvas.drawColor(Color.WHITE);
		
		getHolder().unlockCanvasAndPost(canvas);//解锁canvas
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
	}

}
