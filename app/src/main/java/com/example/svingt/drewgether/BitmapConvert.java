package com.example.svingt.drewgether;

import android.content.Context;
import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by svingt on 10/20/14.
 */
public class BitmapConvert {
    Bitmap bitmap;
    public BitmapConvert(Bitmap bm, Context context) throws IOException {
        File file = new File(context.getCacheDir(), "temp.png");
        file.createNewFile();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, bos);
        byte[] bitData = bos.toByteArray();
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(bitData);
    }


}
