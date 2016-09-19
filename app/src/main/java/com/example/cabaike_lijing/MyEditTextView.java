package com.example.cabaike_lijing;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.EditText;

public class MyEditTextView extends EditText {

	public MyEditTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		Paint paint = new Paint();
		//只画边缘
		paint.setStyle(Style.STROKE);
		paint.setStrokeWidth(1);
		paint.setColor(Color.parseColor("#2E8B57"));
		RectF rf = new RectF(this.getScaleX()+2, this.getScaleY()+2, this.getWidth()-3+this.getScaleX(), this.getHeight()-3+this.getScaleY());
		canvas.drawRoundRect(rf, 10, 10, paint);
	}
}
