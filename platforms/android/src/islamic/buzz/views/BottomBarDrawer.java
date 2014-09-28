
package islamic.buzz.views;

import islamic.buzz.interfaces.listeners.BottomBarDrawerListener;
import islamic.buzz.util.UtilityMethods;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.Transformation;

import com.eybsolution.islamic.buzz.R;

public class BottomBarDrawer extends ViewGroup {

    private int mDrawerHeight;

    private View mDrawerView;

    private boolean mIsDrawerCollapsed = true;

    private boolean mIsDrawerAnimating = false;

    private int mDrawerCollapsedHeight;

    private float mFirstTouchDownY;

    private boolean mIsDrawerMoving;

    private boolean mIsDrawerMovingUp;

    private boolean mIsTouchStarted;

    private int mChangeHeightBy;

    private int mOffsetY;

    private static final int ANIM_DURATION = 500;

    private int mDrawerAnimUpOffset;

    private int mDrawerAnimDownOffset;

    private boolean mHideDrawer;

    private int mLastY;

    private BottomBarDrawerListener mListener;

    private int mDrawerExpandedHeight;

    public BottomBarDrawer(Context context,
            AttributeSet attrs,
            int defStyle) {
        super(context, attrs, defStyle);
        TypedArray typedArray =
                context.obtainStyledAttributes(attrs, R.styleable.BottomBarDrawer, defStyle, 0);
        mDrawerHeight =
                typedArray.getDimensionPixelSize(R.styleable.BottomBarDrawer_drawerHeight, -1);
        mDrawerCollapsedHeight = mDrawerHeight;
        mOffsetY = (int) UtilityMethods.convertDpToPixel(10f, getContext());
    }

    public BottomBarDrawer(Context context,
            AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BottomBarDrawer(Context context) {
        super(context, null, 0);
    }

    public void setDrawerListener(BottomBarDrawerListener listener) {
        mListener = listener;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec,
            int heightMeasureSpec) {
        final int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        final int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int childCount = getChildCount();

        if (childCount == 2) {
            for (int i = 0; i < childCount; i++) {

                View childView = getChildAt(i);
                if (childView.getVisibility() == View.GONE) {
                    continue;
                }
                LayoutParams layoutParams = (LayoutParams) childView.getLayoutParams();
                int childWidthSpec;
                if (layoutParams.width == LayoutParams.WRAP_CONTENT) {
                    childWidthSpec = MeasureSpec.makeMeasureSpec(widthSize, MeasureSpec.AT_MOST);
                } else if (layoutParams.width == LayoutParams.MATCH_PARENT) {
                    childWidthSpec = MeasureSpec.makeMeasureSpec(widthSize, MeasureSpec.EXACTLY);
                } else {
                    childWidthSpec =
                            MeasureSpec.makeMeasureSpec(layoutParams.width, MeasureSpec.EXACTLY);
                }
                int height = layoutParams.height;
                if (i == 1) {
                    height = mDrawerHeight;
                } else {
                    height = heightSize;
                }
                int childHeightSpec;
                if (layoutParams.height == LayoutParams.WRAP_CONTENT) {
                    childHeightSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.AT_MOST);
                } else if (layoutParams.height == LayoutParams.MATCH_PARENT) {
                    childHeightSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
                } else {
                    childHeightSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
                }
                childView.measure(childWidthSpec, childHeightSpec);
            }
            setMeasuredDimension(widthSize, heightSize);
        }
    }

    @Override
    protected void onLayout(boolean changed,
            int left,
            int top,
            int right,
            int bottom) {
        int childCount = getChildCount();
        if (childCount == 2) {
            for (int i = 0; i < childCount; i++) {
                View childView = getChildAt(i);
                if (i == 1) {
                    mDrawerView = childView;
                }
                if (childView.getVisibility() == View.GONE) {
                    continue;
                }
                int childTop = getPaddingTop();
                // Bottom bar drawer view....
                if (i == 1) {
                    childTop =
                            (getMeasuredHeight() - getPaddingBottom()) - childView.getMeasuredHeight();
                    if (mDrawerAnimUpOffset == 0) {
                        mDrawerAnimUpOffset = mDrawerCollapsedHeight;
                    }
                    if (mDrawerAnimDownOffset == 0) {
                        mDrawerAnimDownOffset = getExpandedHeightofDrawer();
                    }
                    setDrawerTouchListener();
                    setDrawerClickListener();

                }

                int childLeft = getPaddingLeft();
                int childBottom = childTop + childView.getMeasuredHeight();
                int childRight = childLeft + childView.getMeasuredWidth();
                childView.layout(childLeft, childTop, childRight, childBottom);
            }
        }
    }

    private void setDrawerClickListener() {
        mDrawerView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                toggleDrawerAnimation();
            }
        });
    }

    private void toggleDrawerAnimation() {
        if (!mIsDrawerAnimating) {
            if (mIsDrawerCollapsed) {
                startExpandingAnimation();
            } else {
                startCollapseAnimation();
            }
        }
    }

    private void setDrawerTouchListener() {
        mDrawerView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view,
                    MotionEvent motionEvent) {
                switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN:
                        if (!mIsTouchStarted) {
                            mIsTouchStarted = true;
                            mFirstTouchDownY = motionEvent.getRawY();
                            mIsDrawerMoving = false;
                            mLastY = 0;
                        }
                        break;
                    case MotionEvent.ACTION_MOVE:
                        float deltaY = mFirstTouchDownY - motionEvent.getRawY();
                        if (Math.abs(deltaY) >= mOffsetY) {
                            mIsDrawerMoving = true;
                            if (deltaY > 0) {
                                mIsDrawerMovingUp = true;
                            } else {
                                mIsDrawerMovingUp = false;
                            }
                            moveDrawer((int) motionEvent.getRawY());
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        mIsTouchStarted = false;
                        mFirstTouchDownY = 0;
                        mLastY = 0;
                        if (mIsDrawerMoving) {
                            mIsDrawerMoving = false;
                            if (mIsDrawerMovingUp) {
                                if (mDrawerHeight >= mDrawerAnimUpOffset) {
                                    startExpandingAnimation();
                                } else {
                                    startCollapseAnimation();
                                }
                            } else {
                                if (mDrawerHeight <= mDrawerAnimDownOffset) {
                                    startCollapseAnimation();
                                } else {
                                    startExpandingAnimation();
                                }
                            }
                            return true;
                        }
                }
                return false;
            }
        });
    }

    private void moveDrawer(int yMove) {
        int height = 0;
        if (mLastY == 0) {
            mLastY = yMove;
        }
        if (mIsDrawerMovingUp) {
            height = (int) (mDrawerHeight + (mLastY - yMove));
        } else {
            height = ((int) (mDrawerHeight - (yMove - mLastY)));
        }
        mLastY = yMove;
        if (height < mDrawerCollapsedHeight) {
            mDrawerHeight = mDrawerCollapsedHeight;
        } else if (height > getExpandedHeightofDrawer()) {
            mDrawerHeight = getExpandedHeightofDrawer();
        } else {
            mDrawerHeight = height;
        }
        mDrawerView.requestLayout();
        if (mListener != null) {
            mListener.onMoving(mDrawerHeight);
        }
    }

    private void startExpandingAnimation() {
        Animation upAnimation = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime,
                    Transformation t) {
                mChangeHeightBy = getChangedHeight();
                int height = (int) (mDrawerHeight + mChangeHeightBy);
                if (height > mDrawerCollapsedHeight && height <= getExpandedHeightofDrawer()) {
                    mDrawerHeight = height;
                } else if (height < mDrawerCollapsedHeight) {
                    mDrawerHeight = mDrawerCollapsedHeight;
                } else {
                    mDrawerHeight = getExpandedHeightofDrawer();
                }
                mDrawerView.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };
        upAnimation.setDuration(ANIM_DURATION);
        mDrawerView.startAnimation(upAnimation);
        upAnimation.setAnimationListener(new AnimationListener() {

            @Override
            public void onAnimationStart(Animation arg0) {
                if (mListener != null) {
                    mListener.onExpanding();
                }
                mIsDrawerAnimating = true;
            }

            @Override
            public void onAnimationRepeat(Animation arg0) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onAnimationEnd(Animation arg0) {
                mIsDrawerCollapsed = false;
                mIsDrawerAnimating = false;
            }
        });
    }

    private void startCollapseAnimation() {
        Animation downAnimation = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime,
                    Transformation t) {
                mChangeHeightBy = getChangedHeight();
                int height = (int) (mDrawerHeight - mChangeHeightBy);
                if (height > mDrawerCollapsedHeight) {
                    mDrawerHeight = height;
                } else {
                    mDrawerHeight = mDrawerCollapsedHeight;
                }
                mDrawerView.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };
        downAnimation.setDuration(ANIM_DURATION);
        mDrawerView.startAnimation(downAnimation);
        downAnimation.setAnimationListener(new AnimationListener() {

            @Override
            public void onAnimationStart(Animation arg0) {
                if (mListener != null) {
                    mListener.onCollapsing();
                }
                mIsDrawerAnimating = true;
            }

            @Override
            public void onAnimationRepeat(Animation arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationEnd(Animation arg0) {
                mIsDrawerCollapsed = true;
                mIsDrawerAnimating = false;

            }
        });
    }

    public int getExpandedHeightofDrawer() {
        if (mDrawerExpandedHeight == 0) {
            return getMeasuredHeight();
        } else {
            return mDrawerExpandedHeight;
        }
    }

    public void setExpandedHeightOfDrawer(int drawerExpandedHeight) {
        mDrawerExpandedHeight = drawerExpandedHeight;
    }

    public void collapse() {
        if (!mIsDrawerCollapsed) {
            startCollapseAnimation();
        }
    }

    public void expand() {
        if (mIsDrawerCollapsed) {
            startExpandingAnimation();
        }
    }

    public void hide() {
        mDrawerView.setVisibility(View.GONE);
        requestLayout();
        if (mListener != null) {
            mListener.onHidden();
        }
    }

    public void show() {
        mDrawerView.setVisibility(View.VISIBLE);
        requestLayout();
        if (!mIsDrawerCollapsed) {
            startCollapseAnimation();
        }
    }

    public int getCollapsedHeightofDrawer() {
        return mDrawerCollapsedHeight;
    }

    private int getChangedHeight() {
        return getExpandedHeightofDrawer() / 12;
    }
}
