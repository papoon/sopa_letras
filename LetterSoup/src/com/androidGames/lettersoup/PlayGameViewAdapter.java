package com.androidGames.lettersoup;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import android.widget.TextView;



public class PlayGameViewAdapter extends BaseAdapter{
	//private PlayGameActivity playgameactivity = new PlayGameActivity();
	private Context mContext;
	private String[] board;
	private Typeface tf;
	private TextView textview;
	

    public PlayGameViewAdapter(Context c,String[] board) {
    	//this.playgameactivity = (PlayGameActivity) c;
        mContext = c;
        this.board = board;
        Log.d("mContext", "mContext");
        tf = Typeface.createFromAsset(c.getAssets(), "fonts/Marker SD 1.2.ttf");
       
        
    }
   
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return board.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		//PlayGameView playgameview;
		 textview = new TextView(mContext);
	   	 textview.setTypeface(tf);
		 if (convertView == null) {
			 
			 
			
			 //textview.setBackgroundColor(Color.GRAY);
			 textview.setTextColor(Color.WHITE);
			 textview.setGravity(Gravity.CENTER);
			 textview.setPadding(2, 2, 0, 0);
			 //textview.setLayoutParams(new GridView.LayoutParams(85, 85));
			 //playgameview = new PlayGameView(mContext);
			 //playgameview.setImageResource(R.drawable.firstscreen);
			 
		 }
		 else
		 {
			 
			// playgameview = (PlayGameView) convertView;
			 textview = (TextView) convertView;
			 
		
		 }
		 textview.setText(board[position]);
		 
		
		return textview;
	}

}
