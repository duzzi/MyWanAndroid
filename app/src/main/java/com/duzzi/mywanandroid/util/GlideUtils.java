package com.duzzi.mywanandroid.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.Base64;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.duzzi.mywanandroid.R;

import java.io.File;

import jp.wasabeef.glide.transformations.BlurTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

/**
 * 文件名: GlideUtils
 * 描    述: [该类的简要描述]
 * 创建人: duzzi
 * 创建时间: 2018/6/6
 */
public class GlideUtils {


    public static Bitmap stringToBitmap(String string) {
        Bitmap bitmap = null;
        try {
            byte[] bitmapArray = Base64.decode(string.split(",")[1], Base64.DEFAULT);
            bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    public static void loadImageBase64(Context context, String base64, ImageView imageView) {
        Glide.with(context)
                .load(stringToBitmap(base64))
                .apply(new RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.ALL))
                .into(imageView);
    }
    public static void loadRefreshCaptchaView(Context context, Drawable drawable, ImageView imageView) {
        Glide.with(context)
                .load(drawable)
                .apply(new RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.ALL))
                .into(imageView);
    }

    public static void loadCollegeBadge(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .apply(new RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .centerCrop())
                .apply(bitmapTransform(new CircleCrop()))
                .into(imageView);
    }

    public static void loadBigCover(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .apply(new RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.ALL))
                .into(imageView);
    }

    public static void loadBanner(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .apply(new RequestOptions()
                        .dontAnimate())
                .into(imageView);
    }

    public static void loadImage(Context context, String url, ImageView imageView, BitmapTransformation bitmapTransformation) {
        Glide.with(context)
                .load(url)
                .apply(new RequestOptions()
//                        .placeholder(R.drawable.ic_thumbnail_1)
//                        .error(R.drawable.ic_thumbnail_1)
                        .diskCacheStrategy(DiskCacheStrategy.ALL).centerCrop())
                .apply(bitmapTransform(bitmapTransformation))
                .into(imageView);
    }

    public static void loadImageSet(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .apply(new RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.ALL).centerCrop())
                .apply(bitmapTransform(new CenterCrop()))
                .into(imageView);
    }

    public static void loadImageSet2(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .apply(new RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.ALL))
                .into(imageView);
    }

    public static void loadImage(Context context, Drawable drawable, ImageView imageView) {
        Glide.with(context)
                .load(drawable)
                .apply(new RequestOptions()
//                        .placeholder(R.drawable.ic_thumbnail_1)
//                        .error(R.drawable.ic_thumbnail_1)
                        .diskCacheStrategy(DiskCacheStrategy.ALL).centerCrop())
                .apply(bitmapTransform(new CenterCrop()))
                .into(imageView);
    }


    public static void loadCover(Context context, Drawable drawable, ImageView imageView) {
        Glide.with(context)
                .load(drawable)
                .apply(new RequestOptions()
//                        .placeholder(R.drawable.ic_thumbnail_1)
//                        .error(R.drawable.ic_thumbnail_1)
                        .diskCacheStrategy(DiskCacheStrategy.ALL).centerCrop())
//                .apply(bitmapTransform(new CenterCrop()))
                .into(imageView);
    }

    public static void loadCover(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .apply(new RequestOptions()
//                        .placeholder(R.drawable.ic_thumbnail_1)
//                        .error(R.drawable.ic_thumbnail_1)
                        .diskCacheStrategy(DiskCacheStrategy.ALL).centerCrop())
//                .apply(bitmapTransform(new CenterCrop()))
                .into(imageView);
    }
    public static void loadImage(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .apply(new RequestOptions()
//                        .placeholder(R.drawable.ic_thumbnail_1)
//                        .error(R.drawable.ic_thumbnail_1)
                        .diskCacheStrategy(DiskCacheStrategy.ALL))
//                .apply(bitmapTransform(new CenterCrop()))
                .into(imageView);
    }

    public static void loadInformationCover(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .apply(new RequestOptions()
//                        .placeholder(R.drawable.ic_thumbnail_1)
//                        .error(R.drawable.ic_thumbnail_1)
                        .diskCacheStrategy(DiskCacheStrategy.ALL).centerCrop())
                .into(imageView);
    }


    public static void loadImage(Context context, Drawable drawable, ImageView imageView, BitmapTransformation bitmapTransformation) {
        Glide.with(context)
                .load(drawable)
                .apply(new RequestOptions()
//                        .placeholder(R.drawable.ic_thumbnail_1)
//                        .error(R.drawable.ic_thumbnail_1)
                        .diskCacheStrategy(DiskCacheStrategy.ALL).centerCrop())
                .apply(bitmapTransform(new CenterCrop()))
                .into(imageView);
    }

    public static void loadRoundImage(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .apply(new RequestOptions()
//                        .placeholder(R.drawable.ic_avatar_default)
//                        .error(R.drawable.ic_avatar_default)
                        .diskCacheStrategy(DiskCacheStrategy.ALL).centerCrop())
                .apply(bitmapTransform(new CircleCrop()))
                .into(imageView);
    }

    public static void loadRoundAvatarImage(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .apply(new RequestOptions()
//                        .placeholder(R.drawable.ic_avatar_default)
//                        .error(R.drawable.ic_avatar_default)
                        .diskCacheStrategy(DiskCacheStrategy.ALL).centerCrop())
                .apply(bitmapTransform(new CircleCrop()))
                .into(imageView);
    }

    public static void loadRoundImage(Context context, Drawable drawable, ImageView imageView) {
        Glide.with(context)
                .load(drawable)
                .apply(new RequestOptions()
//                       .placeholder(R.drawable.ic_avatar_default)
//                        .error(R.drawable.ic_avatar_default)
                        .diskCacheStrategy(DiskCacheStrategy.ALL).centerCrop())
                .apply(bitmapTransform(new CircleCrop()))
                .into(imageView);
    }

    public static void loadBlurImage(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .apply(new RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .optionalCenterCrop()
//                        .placeholder(R.mipmap.ic_launcher)
//                        .error(R.drawable.ic_avatar_default)
                        .transform(new BlurTransformation(24, 3)))
                .into(imageView);
    }

    public static void loadImageFile(Context context, File file, ImageView imageView) {
        Glide.with(context)
                .load(file)
                .apply(new RequestOptions()
//                        .placeholder(R.drawable.ic_thumbnail_1)
//                        .error(R.drawable.ic_thumbnail_1)
                        .diskCacheStrategy(DiskCacheStrategy.ALL).centerCrop())
                .apply(bitmapTransform(new CenterCrop()))
                .into(imageView);
    }
}
