package com.androidGames.lettersoup;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;


public class MainActivity extends Activity {
	public final static String EXTRA_MENSSAGE = "com.androidGames.lettersoup.MESSAGE";
	Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
		
        Thread splashThread = new Thread() {
            @Override
            public void run() {
               try {
                  int waited = 0;
                  while (waited < 5000) {
                     sleep(100);
                     waited += 100;
                  }
               } catch (InterruptedException e) {
                  // do nothing
               } finally {
                  
                  Intent i = new Intent();
                  //Intent i = new Intent(activity,MenuSoupLettersActivity.class);
                  i.setClassName("com.androidGames.lettersoup",
                                 "com.androidGames.lettersoup.MenuSoupLettersActivity");
                  startActivity(i);
                  finish();
               }
            }
         };
         splashThread.start();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
   
    
}
