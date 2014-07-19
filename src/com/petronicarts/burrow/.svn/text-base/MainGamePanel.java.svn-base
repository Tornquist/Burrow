package com.petronicarts.burrow;

import java.io.IOException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;
//import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
//import android.graphics.PorterDuff.Mode;

import android.media.MediaPlayer;
import android.os.Vibrator;

import com.petronicarts.burrow.Touch;
import com.petronicarts.burrow.Rectangle;

public class MainGamePanel extends SurfaceView implements SurfaceHolder.Callback 
{
	float CANVAS_HEIGHT = 1280.0f/2;
	float CANVAS_WIDTH = 720.0f/2;
	//Variable Declarations.
	private MainThread thread;
		
	public int actScreenWidth;
	public int actScreenHeight;
	
	Paint paint;
	
	Map<Integer,Touch> touchDict = new HashMap<Integer, Touch>();
	private int LEFT_BUTTON = 0;
	private int CENTER_BUTTON = 2;
	private int RIGHT_BUTTON = 1;
	private int PLAY_BUTTON = 3;
	private int AGAIN_BUTTON = 4;
	private int SKIP_BUTTON = 5;
	Map<Integer, Button> buttons = new HashMap<Integer, Button>();
	List<Hawk> hawks = new ArrayList<Hawk>();
	List<Clouds> clouds = new ArrayList<Clouds>();

	public int gameState;
	
	public int timeElapsed;
	public long oldDate;
	public long newDate;
	
	public int highScore;
	
	public boolean pause = false;
		
	//Intro
	public Bitmap intro;
	public int introImage;	
	public Bitmap canvas;
	public Bitmap wallpaper;
		
	//Bitmap carrot;
	public Sprite hawk;
	public Sprite bunny;
	//public Sprite healthSprite;
	public int bunnyFrame;
	public int bunnyDirection;
	public int bunnyTimeElapsed;
	public int carrotFrame;
	public int carrotDirection;
	public int carrotTimeElapsed;
	public int carrotRandomTimeThreshold;
	public int carrotRandomTimer;
	public int hawkTimer;
	public long bunnyOldDate;
	public long bunnyNewDate;
	public long scoreStartDate;
	public int score;
	public Bitmap hawkImage;
	public int health;
	public int cloudTimer;
	public Bitmap cloudImage; 
	Vibrator vibrate;
	AssetManager assetMgr;
	private boolean buttonHit;
	public int justHit;
	public int justCarrot;
	public Bitmap rButtonImage;
	public Bitmap lButtonImage;
	public int bunnyButton;
	Random rand;
	@SuppressWarnings("deprecation")
	public boolean loading;
	
	public Bitmap logo;
	public Bitmap failure;
	public Bitmap wallpaperBase;
	public boolean bunnyBurrow;
	public Sprite carrot;
	public Bitmap helpSplash;
	public int helpSplashTimer;
	public boolean lastRightState;
	public boolean lastLeftState;
	public int failTimer;
	public Bitmap replayGrey;
	public boolean pastSkipButtonState;
	public int timeLeftAfterSkip;
	public boolean justPaused;
	public String version;
	private SoundPoolPlayer sounds;
	public int justStarted;
	
	public MainGamePanel(Context context)
	{
		super(context);
		
		getHolder().addCallback(this);
		vibrate = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
		thread = new MainThread(getHolder(),this);
		
		paint = new Paint();
		paint.setAntiAlias(true);
		assetMgr = context.getAssets();
		Typeface tf = Typeface.createFromAsset(assetMgr, "BitBold.ttf");
		
		paint.setTypeface(tf);
		
		gameState = -1;	    
		setFocusable(true);
	    thread.setRunning(true);
	    thread.start();
	    
	    SharedPreferences fileStore = context.getSharedPreferences("userData", 0);
	    
    	highScore = fileStore.getInt("highscore", 0); //0 is the default value
    	
    	try
    	{
    		intro = BitmapFactory.decodeStream(assetMgr.open("intro.png"));
    		replayGrey = BitmapFactory.decodeStream(assetMgr.open("refreshButtonG.png"));
    		helpSplash = BitmapFactory.decodeStream(assetMgr.open("help2.png"));
    		hawkImage = BitmapFactory.decodeStream(assetMgr.open("hawk.png"));
    		cloudImage = BitmapFactory.decodeStream(assetMgr.open("clouds.png"));
    		wallpaper = BitmapFactory.decodeStream(assetMgr.open("wallpaper_hole.png"));
    		wallpaperBase = BitmapFactory.decodeStream(assetMgr.open("wallpaper_base.png"));
    		bunny = new Sprite(BitmapFactory.decodeStream(assetMgr.open("Aindra.png")),32, 32, 2.0f);
    		hawk = new Sprite(BitmapFactory.decodeStream(assetMgr.open("hawk.png")),32,32,2.0f);
    		carrot = new Sprite(BitmapFactory.decodeStream(assetMgr.open("carrot.png")),14,32,2.0f);
    		carrot.setCoors(-100,424);
    		//healthSprite = new Sprite(BitmapFactory.decodeStream(assetMgr.open("health.png")),80,80,1);
    		bunny.setCoors(100, 424);
    		rButtonImage = BitmapFactory.decodeStream(assetMgr.open("rButton.png"));
    		lButtonImage = BitmapFactory.decodeStream(assetMgr.open("lButton.png"));
    		logo = BitmapFactory.decodeStream(assetMgr.open("mainLogo.png"));
    		failure = BitmapFactory.decodeStream(assetMgr.open("failure.png"));
    		 	      
    		sounds = new SoundPoolPlayer(context);
    	} catch (IOException e)
    	{
    		
    	}
		loading = false;
    	bunnyDirection=1;
    	bunnyFrame = 7;
    	rand = new Random();
    	justPaused = false;
    	//initGameVariables();
    	PackageInfo pinfo;
		try {
			pinfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
			version = pinfo.versionName;
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			version = "1.0";
		}
		justStarted = 0;
    	
	}
	
	private void initGameVariables(){
		timeLeftAfterSkip = 0;
		helpSplashTimer = 4000;
		carrot.setX(-100);
		loading = true;
    	health = 360*2;
    	hawkTimer = -3875;
    	cloudTimer = -3875;
    	timeElapsed = 0;
    	//bunnyFrame = 0;
    	//bunnyDirection = 0;
    	bunnyTimeElapsed = 0;
    	carrotRandomTimer = 0;
    	carrotRandomTimeThreshold = 8000;
    	oldDate = System.currentTimeMillis();
    	newDate = System.currentTimeMillis();
    	for (int i = 0; i < clouds.size(); i++)
    	{
    		clouds.get(i).setGoalAlpha(0);
    	}
    	hawks.clear();
    	bunny.setY(424);
	    //bunnyDirection = 1;//Right
	    //bunnyFrame = 7;
	    bunnyTimeElapsed = 0;
    	bunnyOldDate = System.currentTimeMillis();
    	bunnyNewDate = System.currentTimeMillis();
    	scoreStartDate = System.currentTimeMillis();
    	justHit = 0;
    	justCarrot=0;
    	score = 0;
    	buttons.clear();
    	bunnyBurrow = false;
    	lastRightState = false;
    	lastLeftState = false;
    	try
    	{
    		buttons.put(RIGHT_BUTTON,new Button(rButtonImage, BitmapFactory.decodeStream(assetMgr.open("rButtonD.png")), (int) CANVAS_WIDTH-120, (int) CANVAS_HEIGHT-120, 100, 100));
			buttons.put(LEFT_BUTTON,new Button(lButtonImage, BitmapFactory.decodeStream(assetMgr.open("lButtonD.png")), (int) 20, (int) CANVAS_HEIGHT-120, 100, 100));
			buttons.put(SKIP_BUTTON,new Button(BitmapFactory.decodeStream(assetMgr.open("skipButton.png")), BitmapFactory.decodeStream(assetMgr.open("skipButtonD.png")), 75+11, 75+275, 188, 49));
    	}
    	catch (IOException e)
    	{
    		//No action
    	}
    	loading = false;
    	pastSkipButtonState = false;
	}
	
	private void initMenu()
	{
		loading = true;
		buttonHit = false;
    	buttons.clear();
    	timeElapsed = 0;
    	oldDate = System.currentTimeMillis();
    	newDate = System.currentTimeMillis();
    	//bunnyDirection = 1;//Right
	    //bunnyFrame = 7;
	    bunnyTimeElapsed = 0;
    	bunnyOldDate = System.currentTimeMillis();
    	bunnyNewDate = System.currentTimeMillis();
    	bunnyButton = 10;
    	hawkTimer = 0;
    	try
    	{
    		buttons.put(PLAY_BUTTON,new Button(BitmapFactory.decodeStream(assetMgr.open("pButton.png")), BitmapFactory.decodeStream(assetMgr.open("pButtonD.png")), (int) CANVAS_WIDTH/2-150, (int) CANVAS_HEIGHT-120, 300, 100));
    	}
    	catch (IOException e)
    	{
    		//No action
    	}
    	for (int i = 0; i < clouds.size(); i++)
    	{
    		clouds.get(i).setGoalAlpha(255);
    	}
    	loading = false;
	}
	
	private void failInit()
	{
		loading = true;
		buttons.clear();
		
		if (score > highScore)
			highScore = score;
		SharedPreferences fileStore = this.getContext().getSharedPreferences("userData", 0);
		SharedPreferences.Editor editor = fileStore.edit();
		editor.putInt("highscore", highScore);
		editor.commit();
		//try
    	//{
    	//	buttons.put(AGAIN_BUTTON,new Button(BitmapFactory.decodeStream(assetMgr.open("refreshButton.png")), BitmapFactory.decodeStream(assetMgr.open("refreshButtonD.png")), (int) CANVAS_WIDTH-120, (int) CANVAS_HEIGHT-120, 100, 100));
    	//}
    	//catch (IOException e)
    	//{
    	//	//No action
    	//}
		failTimer = 0;
		loading = false;
	}
	
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		//continue the thread
	    synchronized (thread) {
	        thread.pleaseWait = false;
	        thread.notifyAll();
	    }

	}
	
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		//pause the thread

	    
	    synchronized (thread) {
	        thread.pleaseWait = true;
	    }
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int action = event.getAction() & MotionEvent.ACTION_MASK;
        int pointerIndex = (event.getAction() & MotionEvent.ACTION_POINTER_ID_MASK) >> MotionEvent.ACTION_POINTER_ID_SHIFT;
        int pointerId = event.getPointerId(pointerIndex);        
        switch (action) {
        case MotionEvent.ACTION_DOWN:
        case MotionEvent.ACTION_POINTER_DOWN:
        	touchDict.put(pointerId, new Touch(event.getX(pointerIndex)/actScreenWidth*CANVAS_WIDTH, event.getY(pointerIndex)/actScreenHeight*CANVAS_HEIGHT,true, true));  
            break;
        case MotionEvent.ACTION_UP:          
        case MotionEvent.ACTION_POINTER_UP:
        case MotionEvent.ACTION_CANCEL:
        	touchDict.put(pointerId, new Touch(event.getX(pointerIndex)/actScreenWidth*CANVAS_WIDTH, event.getY(pointerIndex)/actScreenHeight*CANVAS_HEIGHT,false, false));
            break;

        case MotionEvent.ACTION_MOVE:
        	
        	int pointerCount = event.getPointerCount();
        	for(int i = 0; i < pointerCount; ++i)
        	{
        		pointerIndex = i;
        		pointerId = event.getPointerId(pointerIndex);
        		boolean state = touchDict.get(pointerId).justHit;
        		touchDict.put(pointerId, new Touch(event.getX(pointerIndex)/actScreenWidth*CANVAS_WIDTH, event.getY(pointerIndex)/actScreenHeight*CANVAS_HEIGHT,true, state));
        	}
            break;
        }
        
		return true;
	}

	public void backHit()
	{
		gameState = 0;
		initMenu();
	}
	
	@Override
	public void onDraw(Canvas canvas)
	{
		if (!pause && canvas != null)
		{
			if (gameState != -1)
			{
				if (justStarted>=0)
				{
					//canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
					canvas.drawColor(Color.BLACK);
					justStarted-=1;
				}
				else
				{
				    final float scaleFactor = Math.min( getWidth() / CANVAS_WIDTH, getHeight() / CANVAS_HEIGHT );
				    final float finalWidth = CANVAS_WIDTH * scaleFactor;
				    final float finalHeight = CANVAS_HEIGHT * scaleFactor;
				    final float leftPadding = ( getWidth() - finalWidth ) / 2;
				    final float topPadding =  ( getHeight() - finalHeight ) / 2;
			
				    final int savedState = canvas.save();
				    try {
				        canvas.clipRect(leftPadding, topPadding, leftPadding + finalWidth, topPadding + finalHeight);
			
				        canvas.translate(leftPadding, topPadding);
				        canvas.scale(scaleFactor, scaleFactor);
				        if (!loading)
				        	drawBase(canvas);
				    } finally {
				        canvas.restoreToCount(savedState);
				    }
				}
			}
			else
			{
				int width = intro.getWidth();
				int height = intro.getHeight();
				paint.setColor(Color.BLACK);
				canvas.drawRect(0, 0, getWidth(), getHeight(), paint);
				canvas.drawBitmap(intro, getWidth()/2-width/2, getHeight()/2 -height/2, paint);
				paint.setColor(Color.WHITE);
				
				paint.setTextSize(20);
				canvas.drawText("Version: "+version, 10, getHeight()-80, paint);
				canvas.drawText("Audio by Michael Betz", 10, getHeight()-60, paint);
				canvas.drawText("Game by Nathan Tornquist", 10, getHeight()-40, paint);
			}
		
		}
	}
	
	
	protected void drawBase(Canvas canvas)
	{		 		
		if (gameState == -1)
		{
			int width = 350;
			int height = 350;
			canvas.drawBitmap(intro, CANVAS_WIDTH/2-width/2, CANVAS_HEIGHT/2 -height/2, paint);
		}
		if (gameState == 0)
		{
			canvas.drawBitmap(wallpaper, 0, 0, paint);
			if (buttons.containsKey(PLAY_BUTTON))
				buttons.get(PLAY_BUTTON).draw(canvas, paint);
		    for(int i = 0; i < clouds.size(); i++)
			{
				clouds.get(i).draw(canvas, paint);
			}
		    paint.setAlpha(255);
		    bunny.draw(canvas, paint, bunnyFrame);
		    canvas.drawBitmap(logo, CANVAS_WIDTH/2-logo.getWidth()/2, 150, paint);
		}
		if (gameState == 1 || gameState == 2)
		{
			
			//canvas.drawRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT, paint);
			if (gameState == 1)
				drawHealth(canvas, paint);
				//healthSprite.draw(canvas, paint, 5-health, (int)CANVAS_WIDTH/2-40, (int)CANVAS_HEIGHT-110);
			
			canvas.drawBitmap(wallpaper, 0, 0, paint);
			if (gameState == 2)
				canvas.drawBitmap(wallpaperBase, 0,	484, paint);
			
			
			
			//TODO: STUFF
			/*paint.setColor(Color.RED);
			Rect bunnyRec = bunny.getRect();
			bunnyRec = new Rect(bunnyRec.left+5, bunnyRec.top+20, bunnyRec.right-5, bunnyRec.bottom-5);
			canvas.drawRect(bunnyRec, paint);
		    for (int i = 0; i <hawks.size();i++)
		    {
		    	Rect hawkRec = hawks.get(i).getRect();			    	
		    	hawkRec = new Rect(hawkRec.left+5, hawkRec.top+10, hawkRec.right-5, hawkRec.bottom-10);
		    	canvas.drawRect(hawkRec,paint);
		    	
		    }*/
			
			bunny.draw(canvas, paint, bunnyFrame);
			
			
			
			
			for(int i = 0; i < hawks.size(); i++)
			{
				hawks.get(i).draw(canvas, paint);
			}
			for(int i = 0; i < clouds.size(); i++)
			{
				clouds.get(i).draw(canvas, paint);
			}
			paint.setAlpha(255);
			if (gameState == 1)
				carrot.draw(canvas, paint, carrotFrame);
			//canvas.drawBitmap(carrot, 30, 454, paint);
			if (helpSplashTimer > 0)
				canvas.drawBitmap(helpSplash, 75, 75, paint);
			
			try{
				for ( Integer j : buttons.keySet() ) 
				{
					
					buttons.get(j).draw(canvas, paint);
					
				}
			}
			catch (ConcurrentModificationException e)
			{
				
			}
			
			paint.setTextSize(30);
			paint.setColor(Color.BLACK);
			canvas.drawText(Integer.toString(score), 16, 36, paint);
			paint.setColor(Color.WHITE);
			canvas.drawText(Integer.toString(score), 20, 40, paint);
			
			if (justHit>0)
			{
				paint.setColor(Color.RED);
				canvas.drawRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT, paint);
			}	
			//if (justCarrot>0)
			//{
				
			//	paint.setColor(Color.GREEN);
			//	canvas.drawRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT, paint);
			//}			
		}
		
		if (gameState == 2)
		{
			
			canvas.drawBitmap(failure, CANVAS_WIDTH/2-failure.getWidth()/2, 150, paint);
			if (buttons.containsKey(AGAIN_BUTTON))
				buttons.get(AGAIN_BUTTON).draw(canvas, paint);
			else
				canvas.drawBitmap(replayGrey, (int) CANVAS_WIDTH-120, (int) CANVAS_HEIGHT-120, paint);
			paint.setColor(Color.BLACK);
			paint.setTextSize(25);
			canvas.drawText("Score: "+Integer.toString(score), 30, CANVAS_HEIGHT-80, paint);
			canvas.drawText("High: "+Integer.toString(highScore), 30, CANVAS_HEIGHT-45, paint);
			
		}
		
		//for (Integer i : touchDict.keySet())
		//{
		//	paint.setColor(Color.RED);
		//	if (touchDict.get(i).Active)
		//		canvas.drawCircle(touchDict.get(i).x, touchDict.get(i).y, 20, paint);
		//}
	}
	
	public void drawHealth(Canvas canvas, Paint paint)
	{
		paint.setColor(Color.BLACK);
		canvas.drawRect(140, 530, 220, 610, paint);
		paint.setColor(Color.RED);
		Path testPath = new Path();
		testPath.moveTo(180, 570);
		testPath.lineTo((int)(60*Math.cos(Math.toRadians(-health/2-90))+180), (int)(60*Math.sin(Math.toRadians(-health/2-90))+570));
		if (health/2 > 270)
			testPath.lineTo(220, 530);
		if (health/2 > 180)
			testPath.lineTo(220, 610);
		if (health/2 > 90)
			testPath.lineTo(140, 610);
		testPath.lineTo(140, 530);
		testPath.lineTo(180, 530);
		testPath.lineTo(180, 570);
		canvas.drawPath(testPath,paint);
	}
	
	public void update() {
		if (justPaused)
		{
			gameState = 0;
			//pause = true;
			
			initMenu();
	    	clouds.clear();
	    	hawks.clear();
	    	justPaused = false;
		}
		
		if (!loading)
		{
			if (gameState == -1)
			{
				//timeElapsed = 0;	
			    newDate = System.currentTimeMillis();
			    timeElapsed += newDate - oldDate;
			    oldDate = newDate;
				if (timeElapsed > 2500)
				{
					gameState = 0;	
					justStarted = 2;
					initMenu();
				}
			}
			if (gameState == 0)
			{
				if (buttons.get(PLAY_BUTTON).touched())
				{
					buttonHit = true;
				}
				else
				{
					if (buttonHit)
					{
						gameState = 1;
						initGameVariables();
						buttonHit = false;
						if (!pause)
							sounds.playShortResource(R.raw.start);
					}
				}
				
				newDate = System.currentTimeMillis();
			    timeElapsed = (int) (newDate - oldDate);
			    oldDate = newDate;
				cloudManagement(timeElapsed);
				
				bunnyNewDate = System.currentTimeMillis();
			    bunnyTimeElapsed += bunnyNewDate - bunnyOldDate;
			    bunnyOldDate = bunnyNewDate;
			    
			    if (bunnyTimeElapsed > 1500)
			    {
			    	int q = rand.nextInt(10);
			    	bunnyButton = q;
			    	bunnyTimeElapsed-=1500;
			    }
			    hawkTimer += timeElapsed;
			    if (hawkTimer > 125)
			    {
			    	if (bunnyButton == 0)
			    	{
			    		bunnyLeft();
			    	}
			    	else if (bunnyButton == 1)
			    	{
			    		bunnyRight();
			    	}
			    	else
			    	{
			    		bunnyStop();
			    	}
			    	hawkTimer-=125;
			    }
			    
			    
			}
			if (gameState == 1)
			{
				
				if (justCarrot>0)
					justCarrot-=1;
				
				score = (int) ((System.currentTimeMillis() - scoreStartDate-4000 + timeLeftAfterSkip)/1000);
				if (score < 0)
					score = 0;
				
				
				bunnyNewDate = System.currentTimeMillis();
			    bunnyTimeElapsed += bunnyNewDate - bunnyOldDate;
			    bunnyOldDate = bunnyNewDate;
			    
			    newDate = System.currentTimeMillis();
			    timeElapsed = (int) (newDate - oldDate);
			    oldDate = newDate;
			    
			    if (justHit>0)
					justHit-=timeElapsed;
			    
			    carrotRandomTimer += timeElapsed;
			    if (carrotRandomTimer > carrotRandomTimeThreshold)
			    {
			    	int i = 0;
			    	if (bunny.getX() < CANVAS_WIDTH/2)
			    	{
			    		i = (int)(CANVAS_WIDTH/2)-20;
			    	}
			    	carrot.setX(rand.nextInt((int)((CANVAS_WIDTH/2)+i)));
			    	carrotRandomTimer = 0;
			    	carrotRandomTimeThreshold = rand.nextInt(score*100)+8000;
			    }
			    
			    carrotTimeElapsed += timeElapsed;
			    if (carrotTimeElapsed > 200)
			    {
			    	if (carrotDirection == 0)
			    	{
			    		carrotFrame+=1;
			    		if (carrotFrame > 2)
			    			carrotDirection = 1;
			    	}
			    	else
			    	{
			    		carrotFrame-=1;
			    		if (carrotFrame < 0)
			    		{
			    			carrotDirection = 0;
			    			carrotFrame = 0; //Extra pause on bottom
			    		}
			    	}
			    	carrotTimeElapsed-=200;
			    }
			    
			    hawkTimer += timeElapsed;
			    if (hawkTimer > 1000)
			    {
			    	int j = rand.nextInt(2);
			    	int l = (int) (rand.nextInt((int) (CANVAS_WIDTH)));
			    	Hawk hawk = new Hawk(hawkImage, 32, 32, 2.0f, j, 430, ((int) (CANVAS_WIDTH)), l);
			    	if (j == 0)
			    	{
			    		hawk.setX((int) CANVAS_WIDTH);
			    	}
			    	else
			    	{
			    		hawk.setX(-32);
			    	}
			    	int q = rand.nextInt(120);
			    	hawk.setY(100+q);
			    	hawks.add(hawk);
			    	hawkTimer -= 1000;
			    }
			    
			    
			    for (int i = hawks.size()-1; i >= 0; i--)
			    {
			    	hawks.get(i).update(timeElapsed);
			    	if (hawks.get(i).remove)
			    		hawks.remove(i);
			    }
			    
			    cloudManagement(timeElapsed);
			    
			    //Log.d("BUNNYTIMER", Integer.toString(bunnyTimeElapsed));
			    if (buttons.containsKey(LEFT_BUTTON))
			    	if (lastLeftState != buttons.get(LEFT_BUTTON).touched())
			    		bunnyTimeElapsed = 100;
			    if (buttons.containsKey(RIGHT_BUTTON))
			    	if (lastRightState != buttons.get(RIGHT_BUTTON).touched())
			    		bunnyTimeElapsed = 100;
			    if (bunnyTimeElapsed > 100)
			    {
			    	
			    	
			    	health-=1;
			    	if (buttons.containsKey(LEFT_BUTTON) && buttons.containsKey(RIGHT_BUTTON))
			    	{
				    	if (buttons.get(LEFT_BUTTON).touched() && !buttons.get(RIGHT_BUTTON).touched())
				    	{
				    		bunnyLeft();
		
				    	}
				    	else if (!buttons.get(LEFT_BUTTON).touched() && buttons.get(RIGHT_BUTTON).touched())
				    	{
				    		bunnyRight();
				    	}
				    	else if (buttons.get(LEFT_BUTTON).touched() && buttons.get(RIGHT_BUTTON).touched())
				    	{
				    		health-=5;
				    		bunnyBurrow();
				    	}
				    	else 
				    	{
				    		bunnyStop();
				    	}
			    	}
			    	if (health < 1)
	    			{
	    				gameState = 2;
	    				failInit();
	    				//dieSound.start();
	    				if (!pause)
	    					sounds.playShortResource(R.raw.die);
    				}
			    	bunnyTimeElapsed-=100;
			    }
			    
			    if (buttons.containsKey(RIGHT_BUTTON))
			    	lastRightState = buttons.get(RIGHT_BUTTON).touched();
			    if (buttons.containsKey(LEFT_BUTTON))
			    	lastLeftState = buttons.get(LEFT_BUTTON).touched();
			    
			    Rect bunnyRec = bunny.getRect();
			    bunnyRec = new Rect(bunnyRec.left+5, bunnyRec.top+20, bunnyRec.right-5, bunnyRec.bottom-5);
			    for (int i = 0; i <hawks.size();i++)
			    {
			    	Rect hawkRec = hawks.get(i).getRect();			    	
			    	hawkRec = new Rect(hawkRec.left+5, hawkRec.top+10, hawkRec.right-5, hawkRec.bottom-10);		    	
			    	if (hawkRec.intersect(bunnyRec) && !bunnyBurrow)
			    	{
			    		if (!hawks.get(i).getCollided())
			    		{
			    			health-=60*2;
			    			justHit = 50;
			    			vibrate.vibrate(50);
			    			hawks.get(i).hit();
			    			if (health < 1)
			    			{
			    				gameState = 2;
			    				failInit();
			    				if (!pause)
			    					sounds.playShortResource(R.raw.die);
		    				}
			    			//hawkSound.start();
			    			if (!pause)
			    				sounds.playShortResource(R.raw.bird);
			    			
			    		}
		    		}
			    }
			    if (carrot.getRect().intersect(bunnyRec))
			    {
			    	carrot.setX(-100);
			    	health+=120*2;
			    	justCarrot=5;
			    	if (health > 360*2)
			    		health = 360*2;
			    	//vibrate.vibrate(100);
			    	//carrotSound.start();
			    	if (!pause)
			    		sounds.playShortResource(R.raw.carrot);
			    }
			    if (helpSplashTimer > 0)
				{
					helpSplashTimer -= timeElapsed;
					health=360*2;
				}
				
			    if (buttons.containsKey(SKIP_BUTTON))
				{			    	
			    	if (buttons.get(SKIP_BUTTON).touched())
					{
						pastSkipButtonState = true;
					}
					else
					{
						if (pastSkipButtonState)
						{
							timeLeftAfterSkip = helpSplashTimer;
							helpSplashTimer = 0;
							hawkTimer = -0;
					    	cloudTimer = 0;
					    	buttons.remove(SKIP_BUTTON);
						}
					}
			    	if (helpSplashTimer <= 0)
			    	{
			    		if (buttons.containsKey(SKIP_BUTTON))
			    		{
			    			buttons.remove(SKIP_BUTTON);
			    		}
			    	}
				}
				
			}
			else if (gameState == 2)
			{
				newDate = System.currentTimeMillis();
			    timeElapsed = (int) (newDate - oldDate);
			    oldDate = newDate;
				
				if (justHit>0)
					justHit=0;
				if (justCarrot>0)
					justCarrot=0;
				if (buttons.containsKey(AGAIN_BUTTON))
				{
					if (buttons.get(AGAIN_BUTTON).touched())
					{
						buttonHit = true;
					}
					else
					{
						if (buttonHit)
						{
							gameState = 1;
							initGameVariables();
							buttonHit = false;
							if (!pause)
								sounds.playShortResource(R.raw.start);
						}
					}
				}
				else
				{
					failTimer += timeElapsed;
					if (failTimer > 2000)
					{
						try
				    	{
				    		buttons.put(AGAIN_BUTTON,new Button(BitmapFactory.decodeStream(assetMgr.open("refreshButton.png")), BitmapFactory.decodeStream(assetMgr.open("refreshButtonD.png")), (int) CANVAS_WIDTH-120, (int) CANVAS_HEIGHT-120, 100, 100));
				    	}
				    	catch (IOException e)
				    	{
				    		//No action
				    	}
					}
				}
			}
			
			//TODO: THIS IS WHERE THE BUTTON LOGIC IS
			for (Integer i : touchDict.keySet())
			{
				try
				{
					for ( Integer j : buttons.keySet() ) 
					{
						if (buttons.get(j).touched())
						{
							if (buttons.get(j).getPointerID() == i)
							{
								if (touchDict.get(i).Active == false || !buttons.get(j).checkCollision(touchDict.get(i)))
								{
									buttons.get(j).updateState(false);
									touchDict.get(i).onButton = false;
								}
							}
						}
						else
						{
							if (touchDict.get(i).onButton == false && touchDict.get(i).justHit)
							{
								if (buttons.get(j).checkCollision(touchDict.get(i)))
								{
									buttons.get(j).attachPointer(i);
									buttons.get(j).updateState(true);
									touchDict.get(i).onButton = true;
									vibrate.vibrate(15);
								}
							}
						}
					}
				}
				catch (ConcurrentModificationException e)
				{
					
				}
				
				touchDict.get(i).justHit = false;
			}
		}
	}
	
	public void bunnyBurrow()
	{
		bunnyFrame = 1;
		if (!bunnyBurrow)
		{
			bunnyBurrow = true;
			bunny.incY(14);
			if (!pause)
				sounds.playShortResource(R.raw.hop);
		}
	}
	
	public void bunnyRight()
	{
		if (bunnyBurrow)
		{
			bunnyBurrow = false;
			bunny.incY(-14);
			
			bunnyFrame = 7;
		}
		
		if (bunnyDirection != 1)
		{
			bunnyFrame = 7;
			bunnyDirection = 1;
		}
		else
		{
			bunnyFrame+=1;
			if (bunnyFrame > 8)
			{
				bunnyFrame = 7;
			}
			else
			{
				if (!pause)
					sounds.playShortResource(R.raw.hop);
			}
			bunny.incX(20);
			if (bunny.getX()>CANVAS_WIDTH-60)
				bunny.incX(-20);
			
		}
	}
	
	public void bunnyStop()
	{
		if (bunnyBurrow)
		{
			bunnyBurrow = false;
			bunny.incY(-14);
		}
		if (bunnyDirection == 1)
		{
			bunnyFrame = 7;
		}
		else if (bunnyDirection == 0)
		{
			bunnyFrame = 4;
		}
	}
	
	public void bunnyLeft()
	{
		if (bunnyBurrow)
		{
			bunnyBurrow = false;
			bunny.incY(-14);
			bunnyFrame = 4;
		}
		if (bunnyDirection != 0)
		{
			bunnyFrame = 4;
			bunnyDirection = 0;
		}
		else
		{
			bunnyFrame-=1;
			if (bunnyFrame < 3)
			{
				bunnyFrame = 4;
			}	
			else
			{
				if (!pause)
					sounds.playShortResource(R.raw.hop);
			}
			bunny.incX(-20);
			if (bunny.getX()<0)
				bunny.incX(20);
			
		}
	}
	
	public void cloudManagement(int timeElapsed)
	{
		cloudTimer += timeElapsed;
	    if (cloudTimer > 5000)
	    {
	    	if (score > 4 || gameState != 1)
	    	{
		    	int j = rand.nextInt(2);
		    	
		    	int l = (int) rand.nextInt(3);
		    	Clouds cloud = new Clouds(cloudImage, 182, 116, 1.0f, j, ((int) (CANVAS_WIDTH)), l);
		    	if (j == 0)
		    	{
		    		cloud.setX((int) CANVAS_WIDTH);
		    	}
		    	else
		    	{
		    		cloud.setX(-cloud.image.getWidth());
		    	}
		    	int q = rand.nextInt(160);
		    	cloud.setY(40+q);
		    	int cloudAlpha = score*5;
		    	if (gameState !=1 )
		    		cloudAlpha = 255;
		    	cloud.setGoalAlpha(cloudAlpha);
		    	clouds.add(cloud);
	    	}
	    	cloudTimer -= 5000;
	    }
	    
	    
	    for (int i = clouds.size()-1; i >= 0; i--)
	    {
	    	clouds.get(i).update(timeElapsed);
	    	if (clouds.get(i).remove)
	    		clouds.remove(i);
	    }
	}

	public void pause() {
		////mSensorManager.unregisterListener(this);
		//gameState = 0;
		pause = true;
		justPaused = true;
		//initMenu();
    	//clouds.clear();
    	//hawks.clear();
		
	}

	public void resume(Context context) {
		//mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_FASTEST);
		pause = false;
		initMenu();
		if (sounds == null)
			sounds = new SoundPoolPlayer(context);
	}

	public void destroy() {
		thread.setRunning(false);
		sounds.release();
		if (thread != null)
		{
			Thread killThread = thread;
		    thread = null;
		    killThread.interrupt();
		}	
	}

	public void setSize(Point screenSize) {
		actScreenWidth = screenSize.x;
		actScreenHeight = screenSize.y;		
	}		
}

