package com.petronicarts.burrow;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity{
    /** Called when the activity is first created. */
	MainGamePanel viewPanel;
	
	float CAMERA_WIDTH = 720.0f;
	float CAMERA_HEIGHT = 1280.0f;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        //Window state functions.
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        viewPanel = new MainGamePanel(this);
        setContentView(viewPanel);
        
        Display display = getWindowManager().getDefaultDisplay(); 
        Point screenSize = getDisplaySize(display);
        
        viewPanel.setSize(screenSize);
        
    }
    
    //Restarts the accelerometer after onPause
    protected void onResume() {
    	super.onResume();
        viewPanel.resume(this);
        
    }		float CANVAS_HEIGHT = 720.0f;
	float CANVAS_WIDTH = 1280.0f;  @SuppressLint("NewApi")
	private static Point getDisplaySize(final Display display) {
        final Point point = new Point();
        try {
            display.getSize(point);
        } catch (java.lang.NoSuchMethodError ignore) { // Older device
            point.x = display.getWidth();
            point.y = display.getHeight();
        }
        return point;
    }
    
    //Standard Method run when the Application loses focus.
    //This runs the pause() function in the viewPanel so that
    //the accelerometer can be paused.
    protected void onPause() {
    	super.onPause();   
        viewPanel.pause();
         
    }
    
    protected void onDestroy() {
    	super.onDestroy();
    	viewPanel.destroy();
    }
    
    @Override
	public boolean onKeyDown(int keyCode, KeyEvent event)  {
	    if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
	        // do something on back.
	    	viewPanel.backHit();
	    	return true;
	    }

	    return super.onKeyDown(keyCode, event);
	}
    
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
            super.onWindowFocusChanged(hasFocus);
        //if (hasFocus) {
        //    viewPanel.setSystemUiVisibility(
        //            View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        //            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
        //            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        //            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        //            | View.SYSTEM_UI_FLAG_FULLSCREEN
        //            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);}
    }
}