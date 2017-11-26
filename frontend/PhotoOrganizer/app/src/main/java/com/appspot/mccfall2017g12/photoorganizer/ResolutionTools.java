package com.appspot.mccfall2017g12.photoorganizer;

import android.graphics.BitmapFactory;

public class ResolutionTools {

    public final static int RESOLUTION_LOW = 480;
    public final static int RESOLUTION_HIGH = 960;

    public static final String LEVEL_LOW = "low";
    public static final String LEVEL_HIGH = "high";
    public static final String LEVEL_FULL = "full";

    /**
     * Calculates the resolution of an image file stored locally.
     * See doc/resolution.txt for further information.
     *
     * @param filePath the image file full path
     * @return Resolution
     */
    public static int calculateResolution(String filePath) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);
        int a = Math.max(options.outWidth, options.outHeight);
        int b = Math.min(options.outWidth, options.outHeight);
        if (3 * a > 4 * b)
            return ceiledDivision(3 * a, 4);
        else
            return b;
    }

    /**
     * Returns correctly ceiled division of two integers
     *
     * @param p Dividend
     * @param q Divisor
     * @return ceil(p divided by q)
     */
    private static int ceiledDivision(int p, int q) {
        return (p + q - 1) / q;
    }

    public static int getResolution(String resolutionLevel) {
        switch (resolutionLevel) {
            case LEVEL_LOW:
                return RESOLUTION_LOW;
            case LEVEL_HIGH:
                return RESOLUTION_HIGH;
            case LEVEL_FULL:
                return Integer.MAX_VALUE;
            default:
                throw new IllegalArgumentException();
        }
    }

    public static int getResolution(String resolutionLevel, int fullResolution) {
        return Math.min(getResolution(resolutionLevel), fullResolution);
    }
}
