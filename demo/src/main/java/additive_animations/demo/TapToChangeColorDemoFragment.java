package additive_animations.demo;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import at.wirecube.additiveanimations.additive_animator.AdditiveAnimatorSubclassDemo;
import at.wirecube.additiveanimations.additiveanimationsdemo.R;
import at.wirecube.additiveanimations.helper.EaseInOutPathInterpolator;

public class TapToChangeColorDemoFragment extends Fragment {
    ViewGroup rootView;
    View animatedView;

    int index = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(R.layout.fragment_tap_to_change_color_demo, container, false);
        animatedView = rootView.findViewById(R.id.animated_view);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final int colors[] = new int[] {
                getResources().getColor(R.color.niceOrange),
                getResources().getColor(R.color.niceBlue),
                getResources().getColor(R.color.niceGreen),
                getResources().getColor(R.color.nicePink)
        };
        animatedView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(AdditiveAnimationsShowcaseActivity.ADDITIVE_ANIMATIONS_ENABLED) {
                    AdditiveAnimatorSubclassDemo.animate(v).backgroundColor(colors[++index % 4]).setDuration(1000).start();
                } else {
                    final ObjectAnimator backgroundColorAnimator = ObjectAnimator.ofObject(animatedView,
                            "backgroundColor",
                            new ArgbEvaluator(),
                            ((ColorDrawable)v.getBackground()).getColor(),
                            colors[++index % 4]);
                    backgroundColorAnimator.setDuration(1000);
                    backgroundColorAnimator.setInterpolator(EaseInOutPathInterpolator.create());
                    backgroundColorAnimator.start();
                }
            }
        });
    }
}
