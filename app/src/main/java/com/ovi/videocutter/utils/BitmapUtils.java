package com.ovi.videocutter.utils;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;

import java.util.List;

public class BitmapUtils {

    /**
     * Multiple pictures horizontally stitched
     *
     * @param bits
     * @return
     */
    public static Bitmap addHBitmap(List<Bitmap> bits) {
        Bitmap firstBit = null;
        if (bits != null && bits.size() > 0) {
            firstBit = bits.get(0);
            for (int i = 1; i < bits.size(); i++) {
                firstBit = addHBitmap(firstBit, bits.get(i));
            }
        }
        return firstBit;

    }


    /**
     * @param first
     * @param second
     * @return
     */
    private static Bitmap addHBitmap(Bitmap first, Bitmap second) {
        int width = first.getWidth() + second.getWidth();
        int height = Math.max(first.getHeight(), second.getHeight());
        Bitmap result = Bitmap.createBitmap(width, height, Config.RGB_565);
        Canvas canvas = new Canvas(result);
        canvas.drawBitmap(first, 0, 0, null);
        canvas.drawBitmap(second, first.getWidth(), 0, null);
        return result;
    }


}
