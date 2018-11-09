package pl.nauka.jarek.udemy_mg;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class TriangleView extends View {

    Paint paint = new Paint();
    private float width;
    private float height;
    private float h;

    public TriangleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setTextSize(100);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int k = 0;

        String txt  = "TEST";

        paint.setColor(Color.BLACK);
        canvas.drawText(txt, 5, height, paint);

        paint.setColor(Color.BLACK);

        for (int i = txt.length(); i > 0; i--) {
           canvas.drawText("" + txt.charAt(i-1), width*2 - (65) - (k*60), height, paint);
           k++;
        }

        canvas.drawPath(drawTriangle(), paint);
        canvas.drawPath(line(), paint);
    }

    private Path drawTriangle() {
        Path path = new Path();
        paint.setColor(Color.CYAN);
        path.moveTo(width + 5,height + 5);
        path.lineTo(width + 5, height + 150);
        path.lineTo(width + 150, height + 50);

        path.close();       //dociagniecie lini z punktu w którym jestesmy do punktu poczatkowego
        return path;
    }

    private Path line() {
        Path line = new Path();

        paint.setColor(Color.RED);
        //Linia pionowa
        line.moveTo(width-1, 0);
        line.lineTo(width-1, height*2);
        line.lineTo(width+1, height*2);
        line.lineTo(width+1, 0);


        //Linia pozioma
        line.moveTo(0, height+1);
        line.lineTo(width*2, height+1);
        line.lineTo(width*2, height-1);
        line.lineTo(0, height-1);
        return line;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {           //obsługa zmiany rozmiaru widoku
        super.onSizeChanged(w, h, oldw, oldh);

        width = w / 2;
        height = h / 2;

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {     //odpowiada za rozmiat elementu
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}

//TODO onSizeChanged(), onMeasure()  zrobić tak, żeby rysunek automatycznie dopasował się do miejsca rysowania
