package kale.adapter.adapter;

import android.view.View;
import android.view.animation.DecelerateInterpolator;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.ValueAnimator;
import com.nineoldandroids.view.ViewHelper;

import java.util.Hashtable;
import java.util.Map;

import kale.adapter.util.ViewHolder;

/**
 * Created by zzz40500 on 15/8/30.
 */
public class AdapterAnimationHelper {

    private Map<Integer,ViewHolder> mViewHolders =new Hashtable<>();
    private Map<Integer,AnimatorSet> mAnimations =new Hashtable<>();


    private boolean mIsAnimation =false;
    private int mAnimationIndex =-1;
    private  int mMaxIndex =-1;

    public void  add(int index,ViewHolder vh){


        if(mMaxIndex >= index){
            ViewHelper.setAlpha(vh.getConvertView(),1);
            return;
        }
        mMaxIndex =index;
        AnimatorSet animatorSet=mAnimations.get(vh.getConvertView().hashCode());
        if(animatorSet != null) {
            animatorSet.end();
        }
        ViewHelper.setAlpha(vh.getConvertView(),0);
        mViewHolders.put(Integer.valueOf(index), vh);

        post();
    }

    public void post(){

        if(!mIsAnimation){
            mIsAnimation =true;
            boolean isStart=false;
            for (int i = mAnimationIndex +1; i <= mMaxIndex; i++) {
                ViewHolder vh= mViewHolders.get(i);
                if(vh !=  null && vh.position == i){
                    mAnimationIndex =i;
                    startView(mAnimationIndex, vh.getConvertView());
                    mViewHolders.remove(mAnimationIndex);
                    isStart=true;
                    break;
                }
            }
            mIsAnimation =isStart;

        }


    }
    private void postNext(int index){


        if(index>= mAnimationIndex) {

            ViewHolder vh = mViewHolders.get(index );
            if (vh != null && vh.position == index) {

                mAnimationIndex = index ;
                startView(mAnimationIndex, vh.getConvertView() );
                mViewHolders.remove(mAnimationIndex);
            }else if(index == mAnimationIndex +1){
                mIsAnimation =false;
                post();
            }

        }

    }

    private void startView(final int i, final View view) {


        ObjectAnimator objectAnimator= ObjectAnimator.ofFloat(view,"translationY",500f,0);
        objectAnimator.setDuration(300);
        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            private boolean isResponse = true;

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float fraction = animation.getAnimatedFraction();

                if (isResponse && fraction > 0.8f) {
                    isResponse = false;
                    postNext(i + 1);
                }

            }
        });
        objectAnimator.setInterpolator(new DecelerateInterpolator());

        ObjectAnimator alpha=ObjectAnimator.ofFloat(view,"alpha",0.2f,1);
        alpha.setDuration(300);

        AnimatorSet animatorSet=new AnimatorSet();
        animatorSet.playTogether(objectAnimator, alpha);
        animatorSet.setDuration(300);
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mAnimations.remove(view.hashCode());

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        if(i==0)
        animatorSet.setStartDelay(200);
        animatorSet.start();
        mAnimations.put(view.hashCode(),animatorSet);


    }


}
