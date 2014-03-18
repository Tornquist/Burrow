package com.petronicarts.burrow;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Button {
	Bitmap upImage, downImage;
	int x, y, width, height;
	Rect buttonRegion;
	int collisionID;
	
	boolean state;
	
	Button(Bitmap UPImage, Bitmap DOWNImage, int X, int Y, int Width, int Height)
	{
		upImage = UPImage;
		downImage = DOWNImage;
		x = X;
		y = Y;
		width = Width;
		height = Height;
		collisionID = -1;
		buttonRegion = new Rect(x, y, x+width, y+height);
	}
	
	public boolean checkCollision(Touch touchEvent)
	{
		if (buttonRegion.contains((int)touchEvent.x, (int)touchEvent.y) && touchEvent.Active)
		{
			return true;
		}
		return false;
	}
	
	public void attachPointer(int pointerID)
	{
		collisionID = pointerID;
	}
	
	public int getPointerID()
	{
		return collisionID;
	}
	
	public void updateState(boolean pointerState)
	{
		state = pointerState;
	}
	
	public boolean touched()
	{
		return state;
	}
	
	public void draw(Canvas canvas, Paint paint)
	{
		if (state)
		{
			canvas.drawBitmap(downImage, x, y, paint);
		}
		else
		{
			canvas.drawBitmap(upImage, x, y, paint);
		}
	}
}
