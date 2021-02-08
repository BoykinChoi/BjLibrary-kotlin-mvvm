package com.boykinchoi.read.util

import android.widget.ImageView
import com.boykinchoi.read.R
import com.boykinchoi.read.api.HostUrl
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

/**
 * 图片加载工具类
 * Created by BoykinChoi
 * on 2021/2/1
 */
class ImageUtil {
    companion object {
        fun getImageUrl(url: String?): String = HostUrl.HOST_URL + url
        fun checkUrl(url: String?): String? {
            if (url != null && (url.startsWith("http://")
                            || url.startsWith("https://")
                            || url.startsWith("file:///android_asset/"))) {
                return url
            } else return getImageUrl(url)
        }

        fun load(imageView: ImageView, url: String?) {
            Glide.with(imageView.context)
                    .load(checkUrl(url))
                    .apply(
                            RequestOptions.placeholderOf(R.mipmap.placeholder_img)
                                    .error(R.mipmap.placeholder_img)
                                    .dontAnimate()
                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    )
                    .into(imageView)
        }
    }
}
