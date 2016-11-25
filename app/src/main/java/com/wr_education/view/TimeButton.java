package com.wr_education.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.Button;

import com.wr_education.R;

import java.util.Timer;
import java.util.TimerTask;

public class TimeButton extends Button {
	private long lenght = 60 * 1000;//记是时间
	private String textafter = "";
	private String textbefore = "";
	private Timer t;
	private TimerTask tt;
	private long time;
	public TimeButton(Context context) {
		super(context);
	}

	public TimeButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		
	}

	@SuppressLint("HandlerLeak")
	Handler han = new Handler() {
		public void handleMessage(android.os.Message msg) {
			TimeButton.this.setText(time / 1000 + textafter);
			time -= 1000;
			if (time < 0) {
				TimeButton.this.setEnabled(true);
				TimeButton.this.setText(textbefore);
				clearTimer();
			}
		};
	};

	private void initTimer() {
		time = lenght;
		if(t!=null){
			t.cancel();
			t = new Timer();
		}else{
			t = new Timer();
		}
		tt = new TimerTask() {

			@Override
			public void run() {
				han.sendEmptyMessage(0x01);
			}
		};
	}

	private void clearTimer() {
		if (tt != null) {
			tt.cancel();
			tt = null;
		}
		if (t != null)
			t.cancel();
		t = null;
	}



	public void TimeGo() {
		initTimer();
		this.setText(time / 1000 + textafter);
		this.setEnabled(false);
		this.setBackgroundResource(R.drawable.shape_findpassworld_button_background);
		t.schedule(tt, 0, 1000);
	}

	public TimeButton setTextAfter(String text1) {
		this.textafter = text1;
		return this;
	}


	public TimeButton setTextBefore(String text0) {
		this.textbefore = text0;
		this.setText(textbefore);
		return this;
	}

	public TimeButton setLenght(long lenght) {
		this.lenght = lenght;
		return this;
	}

}