package com.reactnativekrumodbanner;

import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;
import android.view.ViewTreeObserver;

import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.views.scroll.ReactScrollView;
import com.facebook.react.views.view.ReactViewGroup;

@SuppressLint("ViewConstructor")
public class KrumodBannerView extends ReactViewGroup {
    private ReactScrollView scrollView;
    private Boolean bannerExists = false;

    public KrumodBannerView(ThemedReactContext themedReactContext) {
        super(themedReactContext);
        setLayerType(LAYER_TYPE_HARDWARE, null);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    protected void onVisibilityChanged( View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        Log.d("BANNER", "onVisibilityChanged " + visibility);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        if (scrollView == null) {
            scrollView = KrumodBannerView.fintSpecifyParent(ReactScrollView.class, this.getParent());

            if (scrollView != null) {
                setHandleScroll();
            }
        } else {
            setHandleScroll();
        }
    }

    private void setHandleScroll() {
        bannerExists = true;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            scrollView.setOnScrollChangeListener(new OnScrollChangeListener() {
                @Override
                public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                    KrumodBannerView.this.handleScroll(scrollView);
                }
            });
        } else {
            scrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
                @Override
                public void onScrollChanged() {
                    KrumodBannerView.this.handleScroll(scrollView);
                }
            });
        }
    }

    private void handleScroll(ReactScrollView scrollView) {
        if (bannerExists) {
            Rect scrollBounds = new Rect();

            scrollView.getHitRect(scrollBounds);

            if (KrumodBannerView.this.getLocalVisibleRect(scrollBounds)) {
                Log.d("BANNER", "handleScroll VISIBLE" + ", " + getVisibilityPercents(this));
            } else {
                Log.d("BANNER", "handleScroll NOT VISIBLE");
            }
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        bannerExists = false;
    }

    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);

        if (visibility == View.VISIBLE) {
            bannerExists = true;
        } else {
            bannerExists = false;
        }
        Log.d("BANNER", "onWindowVisibilityChanged " + visibility);
    }

    public static <T> T fintSpecifyParent(Class<T> tClass, ViewParent parent) {
        if (parent == null) {
            return null;
        } else {
            if (tClass.isInstance(parent)) {
                return (T) parent;
            } else {
                return fintSpecifyParent(tClass, parent.getParent());
            }
        }
    }

    public static int getVisibilityPercents(View view) {
        final Rect currentViewRect = new Rect();

        int percents = 100;

        int height = (view == null || view.getVisibility() != View.VISIBLE) ? 0 : view.getHeight();

        if (height == 0) {
            return 0;
        }

        Boolean v = view.getLocalVisibleRect(currentViewRect);

        if(viewIsPartiallyHiddenTop(currentViewRect)){
            percents = (height - currentViewRect.top) * 100 / height;
        } else if(viewIsPartiallyHiddenBottom(currentViewRect, height)){
            percents = currentViewRect.bottom * 100 / height;
        }

        return percents;
    }

    private static boolean viewIsPartiallyHiddenBottom(Rect currentViewRect, int height) {
        return currentViewRect.bottom > 0 && currentViewRect.bottom < height;
    }

    private static boolean viewIsPartiallyHiddenTop(Rect currentViewRect) {
        return currentViewRect.top > 0;
    }
}
