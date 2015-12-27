package com.fidroid.irregularimageview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {


    private static final float RADIUS_FACTOR = 8.0f;
    private static final int TRIANGLE_WIDTH = 60;
    private static final int TRIANGLE_HEIGHT = 80;
    private static final int TRIANGLE_OFFSET = 100;
    ImageView roundEdgeImage, ovalImage, heartImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ovalImage = (ImageView) findViewById(R.id.ovalImage);
        roundEdgeImage = (ImageView) findViewById(R.id.roundEdgeImage);
        heartImage = (ImageView) findViewById(R.id.heartImage);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.a);
        ovalImage.setImageBitmap(ovalImage(bitmap));
        roundEdgeImage.setImageBitmap(roundEdgeImage(bitmap));
        heartImage.setImageBitmap(heartImage(bitmap));
        bitmap.recycle();
    }


    private Bitmap ovalImage(Bitmap bitmap) {
        Bitmap bmp;

        bmp = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        BitmapShader shader = new BitmapShader(bitmap, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);

        Canvas canvas = new Canvas(bmp);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(shader);

        RectF rect = new RectF(TRIANGLE_WIDTH, 0, bitmap.getWidth(), bitmap.getHeight());
        canvas.drawOval(rect, paint);

        return bmp;
    }


    private Bitmap roundEdgeImage(Bitmap bitmap) {
        Bitmap bmp;

        bmp = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        BitmapShader shader = new BitmapShader(bitmap, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);

        float radius = Math.min(bitmap.getWidth(), bitmap.getHeight()) / RADIUS_FACTOR;
        Canvas canvas = new Canvas(bmp);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(shader);

        RectF rect = new RectF(TRIANGLE_WIDTH, 0, bitmap.getWidth(), bitmap.getHeight());
        canvas.drawRoundRect(rect, radius, radius, paint);

        // draw a triangle
        Path triangle = new Path();
        triangle.moveTo(0, TRIANGLE_OFFSET);
        triangle.lineTo(TRIANGLE_WIDTH,
                TRIANGLE_OFFSET - (TRIANGLE_HEIGHT / 2));
        triangle.lineTo(TRIANGLE_WIDTH,
                TRIANGLE_OFFSET + (TRIANGLE_HEIGHT / 2));
        triangle.close();
        canvas.drawPath(triangle, paint);

        return bmp;
    }


    private Bitmap heartImage(Bitmap bitmap) {
        Bitmap bmp;

        bmp = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        BitmapShader shader = new BitmapShader(bitmap, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);

        Canvas canvas = new Canvas(bmp);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(shader);


        float width = bitmap.getWidth();
        float height = bitmap.getHeight();

        Path oval = new Path();
        Matrix matrix = new Matrix();
        Region region = new Region();


        RectF ovalRect = new RectF(width / 4.0f, 0, width - (width / 4), height);
        oval.addOval(ovalRect, Path.Direction.CW);

        matrix.postRotate(40, width / 2, height / 2);
        oval.transform(matrix, oval);

        region.setPath(oval, new Region((int) (width) / 2, 0, (int) width, (int) height));

        canvas.drawPath(region.getBoundaryPath(), paint);


        matrix.reset();
        oval.reset();

        oval.addOval(ovalRect, Path.Direction.CW);
        matrix.postRotate(-40, width / 2, height / 2);
        oval.transform(matrix, oval);
        region.setPath(oval, new Region(0, 0, (int) width / 2, (int) height));

        canvas.drawPath(region.getBoundaryPath(), paint);

        return bmp;
    }
}
