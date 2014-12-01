package com.media.aip.testwork.UI.gallery;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.media.aip.testwork.R;
import com.media.aip.testwork.models.Image;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
/**
 * Created by user_sca on 01.12.2014.
 */
public class GalleryItemView extends FrameLayout {
  @InjectView(R.id.gallery_image_image) ImageView imageView;
  @InjectView(R.id.gallery_image_title) TextView title;

  private float aspectRatio = 1;
  private RequestCreator request;

  public GalleryItemView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  @Override protected void onFinishInflate() {
    super.onFinishInflate();
    ButterKnife.inject(this);
  }

  public void bindTo(Image item, Picasso picasso) {
    request = picasso.load(item.getUrl());
    aspectRatio = 1f * item.width / item.height;
    requestLayout();

    title.setText(item.width + "x" + item.height);
  }

  @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    int mode = MeasureSpec.getMode(widthMeasureSpec);
    if (mode != MeasureSpec.EXACTLY) {
      throw new IllegalStateException("layout_width must be match_parent");
    }

    int width = MeasureSpec.getSize(widthMeasureSpec);

    int height = Math.min((int) (width / aspectRatio), width * 2);
    heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    if (request != null) {
      request.resize(width, height).centerCrop().into(imageView);
      request = null;
    }
  }
}
