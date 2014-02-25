package com.androidGames.lettersoup;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;




public class PlayGameView extends View{
	
	//private final PlayGameActivity playgameactivity;
	private static final String TAG = "LetterSoup";
	private float width;
	private float height;
	private float x;
	private float y;
	private int selX;
	private int selY;
	private final Rect selRect = new Rect();
	Paint paint;
	Path path;

	public PlayGameView(Context context) {
		super(context);
		//this.playgameactivity = (PlayGameActivity) context;
		setFocusable(true);
		setFocusableInTouchMode(true);
		path= new Path();
		paint = new Paint();
		paint.setAlpha(255);
		paint.setColor(Color.BLUE);
		paint.setStyle(Style.STROKE);
		paint.setStrokeWidth(5);
	}
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh)
	{
		width = w / 9f;
		height = h / 9f;
		 
		getRect(selX, selY, selRect);
		Log.d(TAG, "onSizeChanged: width " + width + " height " + height);
		super.onSizeChanged(w, h, oldw, oldh);
	}

	private void getRect(int x, int y, Rect rect) {
		rect.set((int) (x * width), (int) (y * height), (int) (x * width + width), (int) (y * height + height));
	}
	
	
	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas) 
	{
		// Draw the background
		Paint background = new Paint();
		background.setColor(Color.CYAN);
		canvas.drawLine(x, y, width, height, paint);
		
		
//		// Draw the board
//		// Define colors for the grid lines
//		Paint linedark = new Paint();
//		linedark.setColor(Color.BLACK);
//		
//		// Draw the minor grid lines
//		for (int i = 0; i <= 4; i++) {
//		canvas.drawLine(0, i * height, width*4, i*height, linedark);
//		canvas.drawLine(0, i * height + 1, width*4, i * height + 1, linedark);
//		canvas.drawLine(i * width, 0, i * width, height*4, linedark);
//		canvas.drawLine(i * width + 1, 0, i * width + 1, height*4, linedark);
//		}
		
		// Draw the numbers
		// Define color and style for numbers
		Paint foreground = new Paint(Paint.ANTI_ALIAS_FLAG);
		foreground.setColor(Color.BLACK);
		foreground.setStyle(Style.FILL);
		foreground.setTextSize(height * 0.75f);
		foreground.setTextScaleX(width / height);
		foreground.setTextAlign(Paint.Align.CENTER);
		
		
		// Draw the number in the center of the tile
		FontMetrics fm = foreground.getFontMetrics();
		// Centering on X: use alignment (and X at midpoint)
		x = width / 2;
		// Centering on Y: measure ascent/descent first
		y = height / 2 - (fm.ascent + fm.descent) / 2;
		/*for (int i = 0; i < this.playgameactivity.getRowsBoard(); i++) 
		{
			for (int j = 0; j < this.playgameactivity.getColsBoard(); j++) 
			{
				canvas.drawText(this.playgameactivity.getcharBoard(i, j), j * width + x, i * height + y, foreground);
			}
		}*/
		
		
		
		
	}
	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		 int action = event.getAction();
		 switch(action){
		 
		 case MotionEvent.ACTION_DOWN:
			 path.moveTo(event.getX(), event.getY());
			 path.lineTo(event.getX(), event.getY());
			 break;
		 case MotionEvent.ACTION_MOVE:
			 x = event.getX();
			 y = event.getY();
			 path.lineTo(x, y);
			 invalidate();
			 break;
		 case MotionEvent.ACTION_UP:
			 path.lineTo(event.getX(), event.getY());
			 break;
		 case MotionEvent.ACTION_CANCEL:
			 break;
		 default:
			 break;
		 }
		return super.onTouchEvent(event);
	}
	

}

