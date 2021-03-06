package com.ihunda.android.binauralbeat.viz;

/*
 * @author Giorgio Regni
 * @contact @GiorgioRegni on Twitter
 * http://twitter.com/GiorgioRegni
 * 
 * This file is part of Binaural Beats Therapy or BBT.
 *
 *   BBT is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   BBT is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with BBT.  If not, see <http://www.gnu.org/licenses/>.
 *   
 *   BBT project home is at https://github.com/GiorgioRegni/Binaural-Beats
 */

import com.ihunda.android.binauralbeat.BBeat;
import com.ihunda.android.binauralbeat.R;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.ihunda.android.binauralbeat.Visualization;

public class Aurora implements Visualization {	
	
	/**
	 * Beat frequency in Hz
	 */
	private double period;
	private Bitmap background;
	private Paint pTag;
	
	public Aurora() {
		background = BitmapFactory.decodeResource(BBeat.getInstance().getResources(), R.drawable.aurora);
		pTag = new Paint();
		pTag.setStyle(Paint.Style.FILL);
	}
	
	public void redraw(Canvas c, int width, int height, float now,
			float totalTime) {
		// window [-2.0, 1.0, -1.5, 1.5]
		
		double dperiod = period * 2 * 10;
	
		double ratio = (now % dperiod) / dperiod;
		double trans;
		
		c.drawBitmap(background, 0, 0, null);
		
		if (ratio > 0.5)
			trans = (1-ratio) * 50;
		else
			trans = ratio * 50;
		float radius = width/2;
		

		pTag.setColor(Color.argb((int) (trans*0.7), 255, 255, 255));
		c.drawCircle(width/4, height/2, radius, pTag);
		
		pTag.setColor(Color.argb((int) (trans*0.4), 255, 255, 255));
		c.drawCircle(width/4-20, height/2+20, radius*0.7f, pTag);
		
		pTag.setColor(Color.argb((int) trans, 255, 255, 255));
		c.drawCircle(width/4, height/2, radius*0.4f, pTag);
	}

	public void setFrequency(float beat_frequency) {
		period = 1f / beat_frequency;
	}

}
