

package com.roger.dropus;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.util.LruCache;
import android.util.Log;

import com.roger.dropus.BuildConfig;


public class ImageCache {
    private static final String TAG = "ImageCache";
    private LruCache<String, Bitmap> mMemoryCache;

    private ImageCache(float memCacheSizePercent) {
        init(memCacheSizePercent);
    }

    public static ImageCache getInstance(
            FragmentManager fragmentManager, float memCacheSizePercent) {
        final RetainFragment mRetainFragment = findOrCreateRetainFragment(fragmentManager);
        ImageCache imageCache = (ImageCache) mRetainFragment.getObject();
        if (imageCache == null) {
            imageCache = new ImageCache(memCacheSizePercent);
            mRetainFragment.setObject(imageCache);
        }

        return imageCache;
    }
    private void init(float memCacheSizePercent) {
        int memCacheSize = calculateMemCacheSize(memCacheSizePercent);
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "Memory cache created (size = " + memCacheSize + ")");
        }
        mMemoryCache = new LruCache<String, Bitmap>(memCacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                final int bitmapSize = getBitmapSize(bitmap) / 1024;
                return bitmapSize == 0 ? 1 : bitmapSize;
            }
        };
    }
    public void addBitmapToCache(String data, Bitmap bitmap) {
        if (data == null || bitmap == null) {
            return;
        }
        if (mMemoryCache != null && mMemoryCache.get(data) == null) {
            mMemoryCache.put(data, bitmap);
        }
    }
    public Bitmap getBitmapFromMemCache(String data) {
        if (mMemoryCache != null) {
            final Bitmap memBitmap = mMemoryCache.get(data);
            if (memBitmap != null) {
                if (BuildConfig.DEBUG) {
                    Log.d(TAG, "Memory cache hit");
                }
                return memBitmap;
            }
        }
        return null;
    }
    @TargetApi(12)
    public static int getBitmapSize(Bitmap bitmap) {
        if (Utils.hasHoneycombMR1()) {
            return bitmap.getByteCount();
        }
        // Pre HC-MR1
        return bitmap.getRowBytes() * bitmap.getHeight();
    }


    public static int calculateMemCacheSize(float percent) {
        if (percent < 0.05f || percent > 0.8f) {
            throw new IllegalArgumentException("setMemCacheSizePercent - percent must be "
                    + "between 0.05 and 0.8 (inclusive)");
        }
        return Math.round(percent * Runtime.getRuntime().maxMemory() / 1024);
    }


    public static RetainFragment findOrCreateRetainFragment(FragmentManager fm) {

        RetainFragment mRetainFragment = (RetainFragment) fm.findFragmentByTag(TAG);


        if (mRetainFragment == null) {
            mRetainFragment = new RetainFragment();
            fm.beginTransaction().add(mRetainFragment, TAG).commitAllowingStateLoss();
        }

        return mRetainFragment;
    }
    public static class RetainFragment extends Fragment {
        private Object mObject;
        public RetainFragment() {}

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setRetainInstance(true);
        }
        public void setObject(Object object) {
            mObject = object;
        }
        public Object getObject() {
            return mObject;
        }
    }

}
