package com.petronicarts.burrow;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Clouds extends Sprite {

	int direction;
	int timer;
	int sprite = 0;
	public boolean remove;
	int maxX, maxY;
	int dropLoc;
	int alpha;
	int goalAlpha;
	
	public Clouds(Bitmap Image, int XWidth, int XHeight, float Scale, int Direction, int MaxX, int Sprite) {
		super(Image, XWidth, XHeight, Scale);
		direction = Direction;
		timer = 0;
		sprite = Sprite;
		remove = false;
		maxX = MaxX;
		alpha = 255;
		goalAlpha = 255;
	}
	
	public void setGoalAlpha(int Alpha)
	{
		if (Alpha > 255)
			Alpha = 255;
		if (Alpha < 0)
			Alpha = 0;
		goalAlpha = Alpha;
	}
	
	public void update(int timeElapsed)
	{
		timer += timeElapsed;
		
		if (alpha != goalAlpha)
		{
			if (alpha > goalAlpha)
			{
				alpha -= timeElapsed;
				if (alpha < goalAlpha)
					alpha = goalAlpha;
			}
			else if (alpha < goalAlpha)
			{
				alpha += timeElapsed;
				if (alpha > goalAlpha)
					alpha = goalAlpha;
			}
		}
		
		if (timer > 125)
		{
			if (direction == 0)
			{
				this.incX(-2);
				if (this.getX() < -this.image.getWidth())
					remove = true;
				
			}
			else if (direction == 1)
			{
				this.incX(2);
				if (this.getX()>maxX)
					remove = true;
			}
					
			timer-=125;
		}
	}
	
	public void draw(Canvas canvas, Paint paint)
	{
		paint.setAlpha(alpha);
		this.draw(canvas, paint, sprite);
	}

}
