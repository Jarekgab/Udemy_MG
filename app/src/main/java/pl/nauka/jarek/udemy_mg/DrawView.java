package pl.nauka.jarek.udemy_mg;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

class DrawView extends View implements View.OnTouchListener {          //implementacja do obsługi dotyku

    private Paint paint = new Paint();      //Rysowanie
    private List<Point> points = new ArrayList<Point>();     //lista zawiera położenia narysowanych punktów

    public DrawView(Context context) {
        super(context);

        setFocusable(true);                 //metody: setFocusable i setFocusableInTouchMode dają focus na naszym widoku
        setFocusableInTouchMode(true);
        this.setOnTouchListener(this);      //ustawienie Listenera na bieżącą metode
        paint.setColor(Color.BLUE);         //kolor rysowania blue
        paint.setAntiAlias(true);           //wygładzanie krawędzi
    }

    public DrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        setFocusable(true);
        setFocusableInTouchMode(true);
        this.setOnTouchListener(this);
        paint.setColor(Color.BLUE);
        paint.setAntiAlias(true);
    }

    /*
          Klasa do rysowania
    */

    @Override
    protected void onDraw(Canvas canvas) {

        for (Point point : points) {
            canvas.drawCircle(point.x, point.y, 20, paint);      //canvas czyli płótno, które wypełnia cały widok po którym się poruszamy
                                                                        //drawCircle rysuje koło
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        //przekazanie inf. gdzie znajduje się nasz palec

        Point point = new Point();
        point.x = (int) event.getX();
        point.y = (int) event.getY();

        points.add(point);              //zapisywanie listy narysowanych punktów
        invalidate();                   //wywołanie metody onDraw

        return true;       //metoda zwraca true w przypadku przerwania dotyku
    }

    public void clearCanvas() {
        points.clear();
        invalidate();
    }
}

//TODO 1. Zapisywanie widoku po zamknięciu itp.
//TODO 2. Inne opcje wyboru rysowania (kolor, gubość lini itp)
//TODO 3. Dokładniejszy sposób rysowania (mniejszy punkt rysowania)