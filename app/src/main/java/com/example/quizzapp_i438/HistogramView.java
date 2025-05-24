package com.example.quizzapp_i438;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class HistogramView extends View {

    private Paint barPaint;
    private Paint textPaint;
    private Paint axisPaint;
    private int[] scores = new int[0];
    private int maxScore = 1;

    public HistogramView(Context context) {
        super(context);
        init();
    }

    public HistogramView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        barPaint = new Paint();
        barPaint.setColor(Color.parseColor("#3F51B5")); 
        barPaint.setStyle(Paint.Style.FILL);

        textPaint = new Paint();
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(40);
        textPaint.setTextAlign(Paint.Align.CENTER);

        axisPaint = new Paint();
        axisPaint.setColor(Color.BLACK);
        axisPaint.setStrokeWidth(5);
    }

 
    public void setScores(int[] scores) {
        this.scores = scores;

        
        maxScore = 1;
        for (int score : scores) {
            if (score > maxScore) {
                maxScore = score;
            }
        }
        invalidate(); 
    }

    public void clearQuizScore(){
        this.scores = new int[0];
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();
        int padding = 80;
        final int label_padding = 40;
        final int min_bar_width = 150;

        if (scores.length == 0) {
            
            canvas.drawText("No quiz attempts yet", width / 2f, height / 2f, textPaint);
            return;
        }

        int barWidth = Math.min((width - 2 * padding) / scores.length, min_bar_width);

        for (int i = 0; i < scores.length; i++) {
            int score = scores[i];
            float barHeight = ((float) score / maxScore) * (height - 3 * padding);

            float left = padding + i * barWidth + 20;
            float right = padding + (i + 1) * barWidth - 20;
            float top = height - padding - barHeight;
            float bottom = height - padding;

            canvas.drawLine(padding, padding, padding, height - padding, axisPaint);
            canvas.drawLine(padding, height - padding, width - padding, height - padding, axisPaint);
            canvas.drawRect(left, top, right, bottom, barPaint);
            canvas.drawText(String.valueOf(score), (left + right) / 2, top - 10, textPaint);
            canvas.drawText("Quiz " + (i + 1), (left + right) / 2, bottom + (label_padding), textPaint);
            canvas.drawText("Number of correct answers by quiz", width / 2, padding - 20, textPaint);


        }
    }
}
