package com.petronicarts.burrow;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Hawk extends Sprite {

	int direction, sDirection;
	int timer;
	int sprite = 0;
	public boolean remove;
	int maxX, maxY;
	int dropLoc;
	boolean dropped;
	boolean collided;
	
	public Hawk(Bitmap Image, int XWidth, int XHeight, float Scale, int Direction, int maxY, int MaxX, int dropPoint) {
		super(Image, XWidth, XHeight, Scale);
		direction = sDirection = Direction;
		timer = 0;
		if (direction == 0)
		{
			sprite = 0;
		}
		else if (direction == 1)
		{
			sprite = 2;
		}
		remove = false;
		maxX = MaxX;
		dropLoc = dropPoint;
		this.maxY = maxY;
		dropped = false;
		collided = false;
	}
	
	public void hit()
	{
		collided = true;
	}
	
	public boolean getCollided()
	{
		return collided;
	}
	
	public void update(int timeElapsed)
	{
		timer += timeElapsed;
		if (timer > 125)
		{
			sprite += 1;
			if (direction == 0)
			{
				if (this.getX()<this.dropLoc && !dropped)
				{
					this.direction = 2;
					this.sprite = 4;
					dropped = true;
				}
				else
				{
					if (sprite > 1)
						sprite = 0;
					this.incX(-20);
				
					if (this.getX() < -this.image.getWidth())
					remove = true;
				}
			}
			else if (direction == 1)
			{
				if (this.getX()>this.dropLoc && !dropped)
				{
					this.direction = 2;
					this.sprite = 4;
					dropped = true;
				}
				else
				{
					if (sprite > 3)
						sprite = 2;
					this.incX(20);
				
					if (this.getX()>maxX)
						remove = true;
				}
			}
			
			if (direction == 2)
			{
				if (sprite > 5)
					sprite = 4;
				this.incY(40);
			
				if (this.getY()>maxY)
				{
					this.direction = 3;
					this.sprite = 6;
				}			
			}
			
			if (direction == 3)
			{
				collided = true;//Can only be hit on the way down
				if (sprite > 7)
					sprite = 6;
				this.incY(-40);
				if (this.getY()<this.getStartY())
				{
					this.direction = sDirection;
					if (direction == 0)
					{
						sprite = 0;
					}
					else if (direction == 1)
					{
						sprite = 2;
					}
				}
			}
			
			
			timer-=125;
		}
	}
	
	public void draw(Canvas canvas, Paint paint)
	{
		this.draw(canvas, paint, sprite);
	}

}
