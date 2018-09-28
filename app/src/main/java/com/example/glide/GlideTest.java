package com.example.glide;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.example.carter.BaseActivity;
import com.example.carter.R;

import butterknife.BindView;

/**
 * Created by zwj on 5/30/18.
 */

public class GlideTest extends BaseActivity{

    @BindView(R.id.iv)
    ImageView iv;

    /**
     * glide用法
     *
     * .with() 图片加载的环境：1,Context对象。2,Activity对象。3,FragmentActivity对象。4,Fragment对象
     .load() ---->加载资源：1,drawable资源。2,本地File文件。3,uri。4,网络图片url。5,byte数组（可以直接加载GIF图片）
     .placeholder() 图片占位符
     .error() 图片加载失败时显示
     .crossFade() 显示图片时执行淡入淡出的动画默认300ms
     .dontAnimate() 不执行显示图片时的动画
     .override(200,200) 设置图片的大小
     .centerCrop() 和 fitCenter() 图片的显示方式
     .animate() view动画 2个重构方法
     .transform() bitmap转换
     .bitmapTransform() bitmap转换。比如旋转，放大缩小，高斯模糊等（当用了转换后你就不能使用.centerCrop()或.fitCenter()了。）
     .priority(Priority.HIGH) 当前线程的优先级
     .signature(new StringSignature(“ssss”))
     .thumbnail(0.1f) 缩略图，3个重构方法：优先显示原始图片的百分比(10%)
     .listener() 异常监听
     .asBitmap() // 将图片固定成静态图片
     .into() 图片加载完成后进行的处理：1,ImageView对象。2,宽高值。3,Target对象
     *
     * @param activity
     */



    private  String IMAGE_STATIC_URL = "http://image.baidu.com/search/down?tn=download&word=download&ie=utf8&fr=detail&url=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F01058a556895750000012716d39e4e.jpg%403000w_1l_2o_100sh.jpg&thumburl=http%3A%2F%2Fimg0.imgtn.bdimg.com%2Fit%2Fu%3D2801862717%2C279628383%26fm%3D27%26gp%3D0.jpg"; // 静态图片
    private  String IMAGE_GIF_URL = "https://image.baidu.com/search/down?tn=download&word=download&ie=utf8&fr=detail&url=https%3A%2F%2Ftimgsa.baidu.com%2Ftimg%3Fimage%26quality%3D80%26size%3Db9999_10000%26sec%3D1527684756268%26di%3D080e4951e53889bcc97787c4df8e56e0%26imgtype%3D0%26src%3Dhttp%253A%252F%252Fww1.sinaimg.cn%252Flarge%252F85cccab3gw1ete5mkvd3kg20dw07en7y.jpg&thumburl=https%3A%2F%2Fss1.bdstatic.com%2F70cFuXSh_Q1YnxGkpoWK1HF6hhy%2Fit%2Fu%3D1269095469%2C956578460%26fm%3D27%26gp%3D0.jpg"; // GIF图片



    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, GlideTest.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide_test);

        LruBitmapPool pool = new LruBitmapPool((int) (Runtime.getRuntime().maxMemory()) / 8);

        Glide.with(GlideTest.this) // 在MainActivity中调用Glide的API
                .load(IMAGE_STATIC_URL) // 加载网络中的静态图片
                //.placeholder(R.mipmap.ic_launcher) // 在图片没有加载出来或加载失败时显示ic_launcher图片
                //.bitmapTransform(new CropCircleTransformation(pool)) //  圆形
                //.bitmapTransform(new CropSquareTransformation(pool)) //方形
                //.bitmapTransform(new RoundedCornersTransformation(this, 50, 0, RoundedCornersTransformation.CornerType.LEFT)) //指定那个圆角
                //.bitmapTransform(new CropTransformation(this, 600, 200, CropTransformation.CropType.CENTER)) //指定裁剪某一个局域
                //.bitmapTransform(new BlurTransformation(this, 50, 2))
                //.animate(R.anim.push_down_in) //进场动画
                .into(iv); // 将图片加载到一个ImageView对象中
    }
}
