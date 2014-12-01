package com.media.aip.testwork.UI.gallery;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsListView;

import com.etsy.android.grid.StaggeredGridView;
import com.media.aip.testwork.R;
import com.media.aip.testwork.TestApplication;
import com.media.aip.testwork.UI.BetterViewAnimator;
import com.media.aip.testwork.data.IImageGenerator;
import com.media.aip.testwork.models.Image;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import rx.Subscription;

/**
 * Created by user_sca on 01.12.2014.
 */
public class GalleryView extends BetterViewAnimator {
    @InjectView(R.id.gallery_grid)
    StaggeredGridView galleryView;

    @Inject
    IImageGenerator imageGenerator;

    @Inject
    Picasso picasso;

    private Subscription request;

    private final GalleryAdapter adapter;

    private int mLastFirstVisibleItem;

    public GalleryView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TestApplication.get(context).inject(this);

        adapter = new GalleryAdapter(context, picasso);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.inject(this);

        final Dialog dialog = getDialog();

        galleryView.setOnScrollListener(new AbsListView.OnScrollListener() {
                                            @Override
                                            public void onScrollStateChanged(AbsListView view, int scrollState) {

                                            }

                                            @Override
                                            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                                                if (view.getId() == galleryView.getId()) {

                                                    boolean scrollIsUp = scrollIsUp(firstVisibleItem);

                                                    if (scrollIsUp) {

                                                        if (!dialog.isShowing()) {

                                                            dialog.show();

                                                        }
                                                    }

                                                    mLastFirstVisibleItem = firstVisibleItem;

                                                }
                                            }
                                        }

        );
    }

    private Dialog getDialog() {
        final Dialog dialog = new Dialog(getContext(), R.style.PauseDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();

        wlp.gravity = Gravity.BOTTOM;

        wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(wlp);

        galleryView.setAdapter(adapter);

        dialog.setContentView(R.layout.button_reset);
        dialog.findViewById(R.id.btn_reset).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                galleryView.setSelection(0);
                refreshImages();
                dialog.dismiss();
            }
        });
        return dialog;
    }

    private boolean scrollIsUp(int currentFirstVisibleItem) {
        boolean mIsScrollingUp = false;
        if (currentFirstVisibleItem > mLastFirstVisibleItem) {
            mIsScrollingUp = false;
        } else if (currentFirstVisibleItem < mLastFirstVisibleItem) {
            mIsScrollingUp = true;
        }

        return mIsScrollingUp;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        refreshImages();
    }

    private void refreshImages() {
        List<Image> images = imageGenerator.generateImages(50);

        adapter.replaceWith(images);

        setDisplayedChildId(R.id.gallery_grid);
    }

    @Override
    protected void onDetachedFromWindow() {
        request.unsubscribe();
        super.onDetachedFromWindow();
    }


}
