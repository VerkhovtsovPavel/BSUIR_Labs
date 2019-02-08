package by.bsuir.mastering.pavelverk.android.hardwrite;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

import static android.R.attr.x;
import static java.lang.Float.isNaN;

public class SketchView extends View {
    private Bitmap mBitmap;
    private Canvas mCanvas;
    private Paint mPaint;
    private Paint mBitmapPaint;
    private Path mPath;
    private int mStartColor;
    private int mBackgroundColor;

    private float mX, mY;
    private static final float TOUCH_TOLERANCE = 4;

    private int lines;
    private ArrayList<Float> clickX = new ArrayList<Float>();
    private ArrayList<Float> clickY = new ArrayList<Float>();
    private ArrayList<Long> times = new ArrayList<Long>();

    private TimeIntervals timeIntervaler;

    public SketchView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.SketchView, 0, 0);

        try {
            mStartColor = a.getColor(R.styleable.SketchView_startColor, Color.BLACK);
            mBackgroundColor = a.getColor(R.styleable.SketchView_backgroundColor, Color.WHITE);

        } finally {
            a.recycle();
        }

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(mStartColor);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(8);

        mBitmapPaint = new Paint(Paint.DITHER_FLAG);
        mPath = new Path();

        this.setBackgroundColor(mBackgroundColor);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(mBitmap, 0, 0, mBitmapPaint);
        canvas.drawPath(mPath, mPaint);
    }

    private void touchDown(float x, float y) {
        mPath.reset();
        mPath.moveTo(x, y);
        mX = x;
        mY = y;
        lines++;
    }

    private void touchMove(float x, float y) {
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);
        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
            mX = x;
            mY = y;
        }
    }

    private void touchUp(float x, float y) {
        mPath.lineTo(mX, mY);
        mCanvas.drawPath(mPath, mPaint); // commit the path to our offscreen
        mPath.reset(); // kill this so we don't double draw
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touchDown(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                touchMove(x, y);
                invalidate();
                clickX.add(x);
                clickY.add(y);
                if (timeIntervaler == null) {
                    timeIntervaler = new TimeIntervals();
                }
                times.add(timeIntervaler.currentInterval());
                break;
            case MotionEvent.ACTION_UP:
                touchUp(x, y);
                invalidate();
                break;
        }
        return true;
    }

    public void clear() {
        timeIntervaler = null;
        clickX.clear();
        clickY.clear();
        times.clear();
        lines = 0;

        mPaint.setColor(mBackgroundColor);
        mCanvas.drawPaint(mPaint);
        invalidate();
        mPaint.setColor(mStartColor);
    }

    public String getFeatures() {
        long timer = sum(times, 0);
        float min_x = (float) min(clickX);
        float min_y = (float) min(clickY);
        float max_x = (float) max(clickX);
        float max_y = (float) max(clickY);
        float horizontalLength = max_y - min_y;
        float verticalLength = max_x - min_x;
        float square = horizontalLength * verticalLength;
        ArrayList<Float> dists = distances(clickX, clickY);
        float totalLength = sum(dists);
        ArrayList<Float> velocities = getVelocities(dists, times);
        float maxVelocity = (float) max(velocities);
        float minVelocity = (float) min(velocities);
        long durationX = getDuration(clickX, times);
        long durationY = getDuration(clickY, times);
        String message = String.format("%s %s %s %s %s %s %s %s %s %s", timer, lines, square, horizontalLength, verticalLength, totalLength, maxVelocity, minVelocity, durationX, durationY);
        Log.d("FEAT", message);
        return message;
    }

    private long getDuration(ArrayList<Float> coordinates, ArrayList<Long> times) {
        long total = 0;
        for (int i = 1; i < coordinates.size() - 1; i++) {
            if (coordinates.get(i - 1) - coordinates.get(i) != 0) {
                total += times.get(i);
            }
        }
        return total;
    }

    private ArrayList<Float> distances(ArrayList<Float> clickX, ArrayList<Float> clickY) {
        ArrayList<Float> dist = new ArrayList<Float>();
        for (int i = 1; i < clickX.size(); i++) {
            float deltaX = clickX.get(i - 1) - clickX.get(i);
            float deltaY = clickY.get(i - 1) - clickY.get(i);
            float distance = (float) Math.sqrt((deltaX * deltaX) + (deltaY * deltaY));
            dist.add(distance);
        }
        return dist;
    }

    private ArrayList<Float> getVelocities(ArrayList<Float> dists, ArrayList<Long> times) {
        ArrayList<Float> result = new ArrayList<>();
        for (int i = 0; i < dists.size(); i++) {
            float distance = dists.get(i);
            float time = times.get(i);
            if (distance != 0 && time != 0)
                result.add(distance / time);
        }
        return result;
    }

    private float max(ArrayList<Float> list) {
        float max = 0;
        for (int i = 0; i < list.size(); i++) {
            float number = list.get(i);
            if (number > max) max = number;
        }
        return max;
    }

    private float min(ArrayList<Float> list) {
        float min = Float.MAX_VALUE;
        for (int i = 0; i < list.size(); i++) {
            float number = list.get(i);
            if (number < min) min = number;
        }
        return min;
    }

    private Long sum(ArrayList<Long> list, int marker) {
        Long acc = 0L;
        for (int i = 0; i < list.size(); i++) {
            Long number = list.get(i);
            acc += number;
        }
        return acc;
    }

    private Float sum(ArrayList<Float> list) {
        Float acc = 0.0f;
        for (int i = 0; i < list.size(); i++) {
            Float number = list.get(i);
            acc += number;
        }
        return acc;
    }
}
