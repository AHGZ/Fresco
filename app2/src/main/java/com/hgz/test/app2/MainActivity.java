package com.hgz.test.app2;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SimpleDraweeView draweeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        draweeView = (SimpleDraweeView) findViewById(R.id.simpleDraweeView);
        //图片路径
//        String imageUrl = "http://www.pp3.cn/uploads/201511/2015111212.jpg";
//        draweeView.setImageURI(imageUrl);

        Uri uri = Uri.parse("http://www.pp3.cn/uploads/201511/2015111212.jpg");
        draweeView.setImageURI(uri);
        //初始化多张背景图的集合
        List<Drawable> backgrounds = new ArrayList<Drawable>();
        backgrounds.add(ContextCompat.getDrawable(this,R.mipmap.bg_zero));
        backgrounds.add(ContextCompat.getDrawable(this,R.mipmap.bg_one));
        backgrounds.add(ContextCompat.getDrawable(this,R.mipmap.bg_two));

        //初始化多张叠加图
        List<Drawable> overlays = new ArrayList<Drawable>();
        overlays.add(ContextCompat.getDrawable(this,R.mipmap.overlay_one));
        overlays.add(ContextCompat.getDrawable(this,R.mipmap.overlay_two));
        overlays.add(ContextCompat.getDrawable(this,R.mipmap.overlay_three));

         /*RoundingParams params = new RoundingParams();
        //设置圆形图
        //params.setRoundAsCircle(true);
        //设置圆角图
        params.setCornersRadius(20);
        //分别设置四个角的度数
//        params.setCornersRadii(20,40,60,80);
        //设置八个角
        params.setCornersRadii(new float[]{12,20,30,40,45,50,80,85});
        params.setBorder(Color.BLACK,10);
        params.setOverlayColor(Color.YELLOW);*/

        GenericDraweeHierarchy hierarchy = new GenericDraweeHierarchyBuilder(getResources())
                //设置淡入淡出时间
                .setFadeDuration(5000)
                /*//设置背景图
                .setBackground(ContextCompat.getDrawable(this,R.mipmap.bg_zero))*/
                /*//设置多张背景图
                .setBackgrounds(backgrounds)
                //设置单张叠加图
                .setOverlay(ContextCompat.getDrawable(this,R.mipmap.overlay_one))
                .setOverlays(overlays)*/
                //设置占位图及其缩放类型
                .setPlaceholderImage(ContextCompat.getDrawable(this,R.mipmap.icon_placeholder), ScalingUtils.ScaleType.FOCUS_CROP)
                //设置加载图及其缩放类型
                .setProgressBarImage(ContextCompat.getDrawable(this,R.mipmap.icon_progress_bar), ScalingUtils.ScaleType.FOCUS_CROP)
                //设置失败图
                .setFailureImage(ContextCompat.getDrawable(this,R.mipmap.icon_failure), ScalingUtils.ScaleType.FOCUS_CROP)
                //设置重试图
                .setRetryImage(ContextCompat.getDrawable(this,R.mipmap.icon_retry), ScalingUtils.ScaleType.FOCUS_CROP)
                //设置属性
//                 .setRoundingParams(params)
                //设置圆角(设置圆角与设置圆形同时使用只能显示为圆形图片)
//                .setRoundingParams(RoundingParams.fromCornersRadius(20))
//                .setRoundingParams(RoundingParams.fromCornersRadii(20,40,60,80))
//                .setRoundingParams(RoundingParams.fromCornersRadii(new float[]{12,20,30,40,45,50,80,85}))
                //设置圆形
                .setRoundingParams(RoundingParams.asCircle())
                //构建
                .build();
        draweeView.setHierarchy(hierarchy);
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(uri)
                .setTapToRetryEnabled(true)
                .setOldController(draweeView.getController())
                .build();

        draweeView.setController(controller);
    }

}
