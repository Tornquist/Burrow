package com.petronicarts.burrow;

public class Touch {
	public float x;
	public float y;
	public boolean Active;
	public boolean onButton;
	public boolean justHit;
	
	Touch(float X, float Y, boolean ACTIVE, boolean JustHit)
	{
		x = X;
		y = Y;
		Active = ACTIVE;
		onButton = false;
		justHit = JustHit;
	}
	
	public void setState(boolean buttonState)
	{
		onButton = buttonState;
	}
	public boolean getState()
	{
		return onButton;
	}
	
}
