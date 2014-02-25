package com.androidGames.lettersoup;

import java.io.IOException;
import java.io.InputStream;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Build;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.drawable.ColorDrawable;

import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View.OnTouchListener;

import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.NavUtils;

public class PlayGameActivity extends Activity {
	private char[][] boardGame;
	PlayGameView playgameview;
	private String puzzle = "";
	FrameLayout layout;
	Paint paint;
	private float width;
	private float height;
	private float x;
	private float y;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_play_game);
		//Get the JSON object from the data
		JSONObject parent = this.parseJSONData();
		paint = new Paint();
		paint.setAlpha(255);
		paint.setColor(Color.BLUE);
		paint.setStyle(Style.STROKE);
		paint.setStrokeWidth(5);
		/*//THis will store all the values inside "Hydrogen" in a element string  
		try {
			String element = parent.getString("Nivel1");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

		//THis will store "1" inside atomicNumber
		try {
			puzzle = parent.getJSONObject("Nivel1").getString("Puzzle");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		Log.d("onCreate", "oncreate");
		Log.w("onCreate", "oncreate");
		
		this.boardGame = board(4,5);
		this.boardGame = fillBoardGame(puzzle,this.boardGame);
		Log.d("onCreate", ""+this.boardGame );
		
		
		
		
		final GridView gridView = (GridView) findViewById(R.id.playgamegrid);
		gridView.setNumColumns(4);
		gridView.setAdapter(new PlayGameViewAdapter(this,this.getBoard()));
		
 
		gridView.setOnTouchListener(new OnTouchListener() {
			String text="";
			int pos = 0;
			
			@SuppressWarnings("unused")
			protected void onDraw(Canvas canvas) 
			{
				
				canvas.drawLine(x, y, width, height, paint);
			
			}
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				float currentXPosition = event.getX();
	            float currentYPosition = event.getY();
	            int position = gridView.pointToPosition((int) currentXPosition, (int) currentYPosition);
	            
	            TextView tv = (TextView) gridView.getChildAt(position);
	            width = tv.getWidth();
	            height = tv.getHeight();
				switch(event.getAction())
				{
				case MotionEvent.ACTION_DOWN:
					pos = position;
					text += tv.getText();
					
					//tv.setBackgroundColor(Color.RED);
					
					return true;
				case MotionEvent.ACTION_MOVE:
					if(pos != position)
					{	x=event.getX();
						y=event.getY();
						pos = position;
						text += tv.getText();	
					}
					gridView.invalidateViews();
					
					
					 //adapter.notifyDataChanged();
					 //grid.setAdapter(adapter);
					
					
					return true;
				
				case MotionEvent.ACTION_UP:
					
					getWordBoard(text);
					return true;
				}
				
				return false;
			}
			
			
			});
		
		/*PlayGameView playgameview = new PlayGameView(this);
		setContentView(playgameview);
		playgameview.requestFocus();*/
		
		
		
		//RelativeLayout game = (RelativeLayout)findViewById(R.id.layoutgame);
		
		//game.addView(playgameview);
		
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            // Show the Up button in the action bar.
			setupActionBar();
        }
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.play_game, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public char[][] board(int x, int y)
	{
		final char board[][] = new char [x][y];
		return board;
	}
	public char[][] fillBoardGame(String string,char[][] board)
	{
		int indexString=0;
		int nCols = this.getColsBoard();
		int nRows = this.getRowsBoard();
		
		for (int i = 0; i < nRows; i++) 
		{
			for(int k = 0; k<nCols;k++)
			{
				this.boardGame[i][k] = string.charAt(indexString);
				
				indexString++;
			}
			Log.d("fillBoard", ""+this.boardGame[i][0]);
		}
		return this.boardGame;
		
	}
	public String getcharBoard(int x, int y){
		StringBuilder charBoard = new StringBuilder();
		
		Log.d("getcharBoard", ""+this.boardGame[x][y]);
		charBoard.append(this.boardGame[x][y]);
		
		return charBoard.toString();
		
		
	}
	public int getColsBoard()
	{
		Log.d("numCols",""+this.boardGame[0].length);
		return this.boardGame[0].length;
		
	}
	public int getRowsBoard(){
		Log.d("numRows",""+this.boardGame.length);
		return this.boardGame.length;
	}
	public String[] getBoard()
	{
		final String[] texts = new String[puzzle.length()];
		for(int i=0;i<puzzle.length();i++)
		{
			texts[i]=""+puzzle.charAt(i);
		}
		return texts;
		
	}
	public JSONObject parseJSONData() {
		String JSONString = null;
        JSONObject JSONObject = null;
        try {

            //open the inputStream to the file 
            InputStream inputStream = getAssets().open("PuzzleStore.json");

            int sizeOfJSONFile = inputStream.available();

            //array that will store all the data 
            byte[] bytes = new byte[sizeOfJSONFile];

            //reading data into the array from the file
            inputStream.read(bytes);

            //close the input stream
            inputStream.close();

            JSONString = new String(bytes, "UTF-8");
            JSONObject = new JSONObject(JSONString);

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        catch (JSONException x) {
            x.printStackTrace();
            return null;
        }
        return JSONObject;
		
	}
	public void getWordBoard(String caracter)
	{
		String word = caracter;
		Log.d("Word",word);
		
		
	}
	
	

}
