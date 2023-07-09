package com.dragon_vs_samurai.game_1;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

public class GameView extends View {

    private Bitmap main_char[] = new Bitmap[2];
    private int main_charX=10;
    private int main_charY;
    private int main_charSpeed;

    private boolean touch =false;

    private int yellowX,yellowY,yellowSpeed =8;

    private int greenX,greenY,greenSpeed=12;

    private int redX,redY,redSpeed=10;

    private int red2X,red2Y,red2Speed=12;

    private int green2X,green2Y,green2Speed=12;

    protected int red3X,red3Y,red3Speed=14;

    private int points,lifeCounter;

    private int canvasWidth,canvasHeight;


    private Bitmap backgroundImages[] = new Bitmap[3];
    private Paint score = new Paint();
    private Bitmap life[] = new Bitmap[2]; // Array of bitmaps for life+

    private Bitmap objects[]  = new Bitmap[6];

    private DisplayMetrics displayMetrics;
    private WindowManager wm;

    private Paint levelCount = new Paint();

    private int level2_points = 300;
    private int level3_points = 1500;


    public GameView(Context context) {
        super(context);


        main_char[0] = BitmapFactory.decodeResource(getResources(),R.drawable.mother_dragon); //Convert png into bitmap
        main_char[1] = BitmapFactory.decodeResource(getResources(),R.drawable.mother_dragon);



        displayMetrics = new DisplayMetrics();
         wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(displayMetrics);
        int screenWidth = displayMetrics.widthPixels;
        int screenHeight = displayMetrics.heightPixels;

        backgroundImages[0] = BitmapFactory.decodeResource(getResources(),R.drawable.level_1);  // Background image
        backgroundImages[0] = Bitmap.createScaledBitmap(backgroundImages[0],screenWidth,screenHeight,true);

        backgroundImages[1] = BitmapFactory.decodeResource(getResources(),R.drawable.level_2);  // Background image
        backgroundImages[1] = Bitmap.createScaledBitmap(backgroundImages[1],screenWidth,screenHeight,true);

        backgroundImages[2] = BitmapFactory.decodeResource(getResources(),R.drawable.level_3);  // Background image
        backgroundImages[2] = Bitmap.createScaledBitmap(backgroundImages[2],screenWidth,screenHeight,true);


        score.setColor(Color.RED);
        score.setTextSize(70);
        score.setTypeface(Typeface.DEFAULT_BOLD);
        score.setAntiAlias(true);

        levelCount.setColor(Color.RED);
        levelCount.setTextSize(70);
        levelCount.setTypeface(Typeface.DEFAULT_BOLD);
        levelCount.setAntiAlias(true);

        life[0] = BitmapFactory.decodeResource(getResources(),R.drawable.hearts);
        life[1] = BitmapFactory.decodeResource(getResources(),R.drawable.heart_grey);

        objects[0] = BitmapFactory.decodeResource(getResources(),R.drawable.baby_dragon1);
        objects[1] = BitmapFactory.decodeResource(getResources(),R.drawable.samurai);
        objects[2] = BitmapFactory.decodeResource(getResources(),R.drawable.baby_dragon2);
        objects[3] = BitmapFactory.decodeResource(getResources(),R.drawable.ninja);
        objects[4] = BitmapFactory.decodeResource(getResources(),R.drawable.baby_dragon3);
        objects[5] = BitmapFactory.decodeResource(getResources(),R.drawable.bomb);




        main_charY =600;
        points =0;
        lifeCounter=3;


    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvasWidth = canvas.getWidth();
        canvasHeight = canvas.getHeight();

        if((points>=level3_points)&&(points>level2_points))
        {


            canvas.drawBitmap(backgroundImages[2],0,0,null);
            canvas.drawText("Level  : "+3,20,140,levelCount);

        }

        if((points>=level2_points)&&(points<level3_points))
        {
            canvas.drawBitmap(backgroundImages[1],0,0,null);
            canvas.drawText("Level  : "+2,20,140,levelCount);

        }

         if(points<=level2_points)
            {
                canvas.drawBitmap(backgroundImages[0],0,0,null);
                canvas.drawText("Level  : "+1,20,140,levelCount);
            }



        int min_main_charY = main_char[0].getHeight();
        int max_main_charY = canvasHeight - main_char[0].getHeight()*2;

        boolean isGameOver =false;

        main_charY=main_charY+main_charSpeed;

        if(main_charY<min_main_charY)
        {
            main_charY = min_main_charY;
        }

        if(main_charY>max_main_charY)
        {
            main_charY = max_main_charY;
        }

        main_charSpeed = main_charSpeed+2;

        if(touch)
        {
            canvas.drawBitmap(main_char[1],main_charX,main_charY,null);
            touch =false;

        }else
            {
                canvas.drawBitmap(main_char[0],main_charX,main_charY,null);
            }

        //----------------------------------Yellow Ball------------------------------------------------
        yellowX = yellowX-yellowSpeed;

        if(hitBallChecker(yellowX,yellowY))
        {
            points = points +10;
            yellowX = -100;
        }

        if(yellowX<0)
        {
            yellowX = canvasWidth+21;
            yellowY = (int) Math.floor(Math.random()*(max_main_charY-min_main_charY))+min_main_charY;

        }

        canvas.drawBitmap(objects[0],yellowX,yellowY,null);


        //------------------------------------Green Ball----------------------------------------------
        greenX = greenX-greenSpeed;

        if(hitBallChecker(greenX,greenY))
        {
            points = points +20;
            greenX = -100;
        }

        if(greenX<0)
        {
            greenX = canvasWidth+21;
            greenY = (int) Math.floor(Math.random()*(max_main_charY-min_main_charY))+min_main_charY;

        }

        canvas.drawBitmap(objects[2],greenX,greenY,null);


        //------------------------------------Green2 Ball----------------------------------------------
        if(points>level3_points)
        {
            green2X = green2X-green2Speed;

            if(hitBallChecker(green2X,green2Y))
            {
                points = points +20;
                green2X = -100;
            }

            if(green2X<0)
            {
                green2X = canvasWidth+21;
                green2Y = (int) Math.floor(Math.random()*(max_main_charY-min_main_charY))+min_main_charY;

            }

            canvas.drawBitmap(objects[4],green2X,green2Y,null);
            // canvas.drawCircle(greenX,greenY,20,greenPaint);
        }

        //-------------------------------------------------------------------------------------------


        //------------------------------------Samurai----------------------------------------------

        redX = redX-redSpeed;

        if(hitBallChecker(redX,redY))
        {
            redX = -100;
            lifeCounter--;
            points=points-5;
            if(lifeCounter==0)
            {

                isGameOver =true;
            }
        }

        if(redX<0)
        {
            redX = canvasWidth+21;
            redY = (int) Math.floor(Math.random()*(max_main_charY-min_main_charY))+min_main_charY;

        }

        canvas.drawBitmap(objects[1],redX,redY,null);
        //canvas.drawCircle(redX,redY,25,redPaint);

        //-----------------------------------Ninja----------------------------------------------

        if(points>level2_points)
        {
            red2X = red2X-red2Speed;

            if(hitBallChecker(red2X,red2Y))
            {
                red2Speed+=10;
                red2X = -100;
                lifeCounter--;
                points=points-10;
                if(lifeCounter==0)
                {

                    isGameOver =true;
                }
            }

            if(red2X<0)
            {
                red2X = canvasWidth+21;
                red2Y = (int) Math.floor(Math.random()*(max_main_charY-min_main_charY))+min_main_charY;

            }

            canvas.drawBitmap(objects[3],red2X,red2Y,null);
        }


    //-----------------------------------------------------------------------------------------------

        //-----------------------------------Bomb----------------------------------------------

        if(points>=500)
        {
            red3X = red3X-red3Speed;

            if(hitBallChecker(red3X,red3Y))
            {
                red3Speed+=7;
                red3X = -100;
                lifeCounter--;
                points=points-10;
                if(lifeCounter==0)
                {
                    isGameOver =true;
                }
            }

            if(red3X<0)
            {
                red3X = canvasWidth+21;
                red3Y = (int) Math.floor(Math.random()*(max_main_charY-min_main_charY))+min_main_charY;

            }

            canvas.drawBitmap(objects[5],red3X,red3Y,null);
        }


        //-----------------------------------------------------------------------------------------------

        canvas.drawText("Score : "+points,20,70,score);


        for(int i=0;i<3;i++)
        {
            int x = (int) (canvas.getWidth()-410+life[0].getWidth()*1.5*i);
            int y =30;

            if(i<lifeCounter)
            {
                canvas.drawBitmap(life[0],x,y,null);
            }else
                {
                    canvas.drawBitmap(life[1],x,y,null);
                }

        }

        if(isGameOver)
        {


            Intent gameOverIntent = new Intent(getContext(),GameOverActivity.class);
            gameOverIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            getContext().startActivity(gameOverIntent);
        }



    }



    public boolean hitBallChecker(int x,int y)
    {

        if(main_charX<x && x<=(main_charX+main_char[0].getWidth()) && main_charY<y && y<=(main_charY+main_char[0].getHeight()))
        {
            return true;
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if(event.getAction() == MotionEvent.ACTION_DOWN)
        {

            touch =true;
            main_charSpeed = -22;

        }


        return true;
    }


}
