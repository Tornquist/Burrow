package com.petronicarts.burrow;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class Sprite {
	public Bitmap image;
	private int xWidth, xHeight;
	private int iWidth, iHeight;
	private int fWidth, fHeight;
	private float scale;
	private int x, y;
	private int startX, startY;
	private Rect destRect;
	public Sprite(Bitmap Image, int XWidth, int XHeight, float Scale)
	{
		xWidth = XWidth;
		xHeight = XHeight;
		image = Image;
		iWidth = image.getWidth();
		iHeight = image.getHeight();
		fWidth = (int) (iWidth/xWidth);
		fHeight = (int) (iHeight/xHeight);
		scale = Scale;
		x = 0;
		y = 0;
		destRect = new Rect(0,0,1,1);
	}
	
	public void setCoors(int X, int Y)
	{
		x = X;
		y = Y;
		startX = X;
		startY = Y;
	}
	
	public void setX(int X)
	{
		x = X;
		startX = X;
		
	}
	
	public void setY(int Y)
	{
		y = Y;
		
		startY = Y;
	}
	
	public void incX(int X)
	{
		x += X;
	}
	
	public void incY(int Y)
	{
		y += Y;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}

	public int getStartX()
	{
		return startX;
	}
	
	public int getStartY()
	{
		return startY;
	}
	
	void draw(Canvas canvas, Paint paint, int frame, int x, int y)
	{
		int row = 0;
		int col = 0;
		while (fWidth <= frame)
		{
			row += 1;
			frame = frame - fWidth;
		}
		col = frame;
		
		Rect sourceRect = new Rect(col*xWidth, row*xHeight, (col+1)*xWidth, (row+1)*xHeight);
		destRect = new Rect(x, y, x+((int) (xWidth*scale)), y+((int) (xHeight*scale)));
		
		canvas.drawBitmap(image, sourceRect, destRect, paint);
		
		//paint.setColor(Color.RED);
		//canvas.drawRect(destRect, paint);
		
	}
	
	void draw(Canvas canvas, Paint paint, int frame)
	{
		draw(canvas, paint, frame, x, y);		
	}
	
	public Rect getRect()
	{
		return destRect;
	}
}
