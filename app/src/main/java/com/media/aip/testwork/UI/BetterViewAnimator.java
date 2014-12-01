package com.media.aip.testwork.UI;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ViewAnimator;
/**
 * Created by user_sca on 01.12.2014.
 */
public class BetterViewAnimator extends ViewAnimator {
  public BetterViewAnimator(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public void setDisplayedChildId(int id) {
    if (getDisplayedChildId() == id) {
      return;
    }
    for (int i = 0, count = getChildCount(); i < count; i++) {
      if (getChildAt(i).getId() == id) {
        setDisplayedChild(i);
        return;
      }
    }
    throw new IllegalArgumentException("No view with ID " + id);
  }

  public int getDisplayedChildId() {
    return getChildAt(getDisplayedChild()).getId();
  }
}
