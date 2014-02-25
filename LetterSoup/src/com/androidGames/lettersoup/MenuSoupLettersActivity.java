package com.androidGames.lettersoup;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;


public class MenuSoupLettersActivity extends Activity {
	
	public final static String EXTRA_MENSSAGE = "com.androidGames.lettersoup.MESSAGE";
	private Typeface tf;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_menu_soup_letters);
		tf = Typeface.createFromAsset(this.getAssets(), "fonts/Marker SD 1.2.ttf");
		final TextView play = (TextView) findViewById(R.id.play);
		final TextView about = (TextView) findViewById(R.id.about);
		final TextView help = (TextView) findViewById(R.id.help);
		play.setTypeface(tf);
		about.setTypeface(tf);
		help.setTypeface(tf);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_soup_letters, menu);
		return true;
	}
	
	public void sendMenssage(View view){
    	Intent intent = new Intent(this,DisplayMenssageActivity.class);
    	/*EditText editText = (EditText) findViewById(R.id.edit_message);
    	String menssage = editText.getText().toString();
    	intent.putExtra(EXTRA_MENSSAGE,menssage);*/
    	startActivity(intent);
    }
	public void playGame(View view)
	{
		Intent intent = new Intent(this,PlayGameActivity.class);
		startActivity(intent);
	}
	public void about(){
		
	}
	public void help(){
		
	}

}
