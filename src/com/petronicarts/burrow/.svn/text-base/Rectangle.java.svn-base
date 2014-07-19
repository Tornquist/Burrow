package com.petronicarts.burrow;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;

public class Rectangle {

	PointF[] points = new PointF[4];
	
	PointF center;
	Matrix clockwise;
	Matrix counterClockwise;
	Paint paint;
	float radius;
	float angle;
	float arc;
	
	public Rectangle(float X, float Y, float Width, float Height, float centX, float centY) {
		points[0] = new PointF(X,Y);
		points[1] = new PointF(X+Width,Y);
		points[2] = new PointF(X+Width,Y+Height);
		points[3] = new PointF(X,Y+Height);
		center = new PointF(centX,centY);
		clockwise = new Matrix();
		clockwise.setRotate(1, center.x, center.y);
		counterClockwise = new Matrix();
		counterClockwise.setRotate(-1, center.x, center.y);
		paint = new Paint();

		paint.setColor(android.graphics.Color.WHITE);
		paint.setStyle(Paint.Style.FILL_AND_STROKE);
		paint.setAntiAlias(true);
		
		//radius = (float) Math.sqrt(Math.pow(X+Width/2.0-centX, 2) + Math.pow(Y+Height/2.0-centY, 2));
		
	}
	
	public void draw(Canvas canvas)
	{
		Path path = new Path();
		path.moveTo(points[0].x, points[0].y);
		path.lineTo(points[1].x, points[1].y);
		path.lineTo(points[2].x, points[2].y);
		path.lineTo(points[3].x, points[3].y);
		path.lineTo(points[0].x, points[0].y);
		path.close();

		canvas.drawPath(path, paint);
		
		
		//canvas.drawLine(points[0].x, points[0].y, points[1].x, points[1].y, paint);
		//canvas.drawLine(points[1].x, points[1].y, points[2].x, points[2].y, paint);
		//canvas.drawLine(points[2].x, points[2].y, points[3].x, points[3].y, paint);
		//canvas.drawLine(points[3].x, points[3].y, points[0].x, points[0].y, paint);
	}
	
	public void rotClock()
	{
		float[] pointArray = new float[points.length*2];
		for (int i = 0; i < points.length; i++)
		{
			pointArray[2*i] = points[i].x;
			pointArray[2*i+1] = points[i].y;	
		}
		clockwise.mapPoints(pointArray);
		for (int i = 0; i < points.length; i++)
		{
			points[i].x = pointArray[2*i];
			points[i].y = pointArray[2*i+1];	
		}	
	}
	public void rotCount()
	{
		float[] pointArray = new float[points.length*2];
		for (int i = 0; i < points.length; i++)
		{
			pointArray[2*i] = points[i].x;
			pointArray[2*i+1] = points[i].y;	
		}
		counterClockwise.mapPoints(pointArray);
		for (int i = 0; i < points.length; i++)
		{
			points[i].x = pointArray[2*i];
			points[i].y = pointArray[2*i+1];	
		}	
	}

}
