package com.example.drag;

import android.app.Activity;
import android.widget.Button;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.view.View;

public class MainActivity extends Activity implements View.OnTouchListener {
private Button mButton;
private ViewGroup mViewGroup;
private int xDelta;
private int yDelta;

  @Override
  public void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    mViewGroup = (ViewGroup) findViewById(R.id.root);
    mButton = (Button) findViewById(R.id.id_text);
    RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    layoutParams.leftMargin = 50;
    layoutParams.topMargin = 50;

    mButton.setLayoutParams(layoutParams);
    mButton.setOnTouchListener(this);
  }

  @Override
  public boolean onTouch(View view, MotionEvent event) {
    final int x = (int) event.getRawX();
    final int y = (int) event.getRawY();
    switch (event.getAction() & MotionEvent.ACTION_MASK) {
      case MotionEvent.ACTION_DOWN:
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) view.getLayoutParams();
        xDelta = x - params.leftMargin;
        yDelta = y - params.topMargin;
        break;
      case MotionEvent.ACTION_MOVE:
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
        int xDistance = x - xDelta;
        int yDistance = y - yDelta;
        layoutParams.leftMargin = xDistance;
        layoutParams.topMargin = yDistance;
        view.setLayoutParams(layoutParams);
        break;
    }
    mViewGroup.invalidate();
    return true;
  }
}