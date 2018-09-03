package com.example.raju.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.support.v7.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // X = 0 , O = 1
    int activePlayer = 0;
    boolean gameActive = true;
    // 2 means it is unplayed
    int[] gameState = {2,2,2,2,2,2,2,2,2};

    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    public void dropIn(View view){
        ImageView counter = (ImageView) view;
        System.out.println(counter.getTag().toString());
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if(gameState[tappedCounter] == 2 && gameActive) {
            gameState[tappedCounter] = activePlayer;
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.images);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.download);
                activePlayer = 0;
            }
            for(int[] winningPosition : winningPositions){
                if(gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                        gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                        gameState[winningPosition[0]] != 2){
                    //Someone won the game
                    gameActive = false;
                    String winner = "Player 2";
                    if(gameState[winningPosition[0]] == 0){
                       winner = "Player 1";
                    }
                    TextView textView3 = (TextView) findViewById(R.id.textView3);
                    textView3.setText(String.format("%s %s", winner, getString(R.string.won)));
                    LinearLayout layout = (LinearLayout) findViewById(R.id.linearLayout2);
                    layout.setVisibility(View.VISIBLE);
                }
                else{
                    boolean gameOver = true;
                    for(int count : gameState){
                        if (count == 2)gameOver = false;
                    }
                    if(gameOver){
                        TextView textView3 = (TextView) findViewById(R.id.textView3);
                        textView3.setText(R.string.draw);
                        LinearLayout layout = (LinearLayout) findViewById(R.id.linearLayout2);
                        layout.setVisibility(View.VISIBLE);
                    }
                }
            }
        }
    }

    public void playAgain(View view){
        gameActive = true;
        LinearLayout layout = (LinearLayout)findViewById(R.id.linearLayout2);
        layout.setVisibility(View.INVISIBLE);
        activePlayer = 0;
        for(int i = 0; i < gameState.length; i++){
            gameState[i] = 2;
        }
        Log.i("Info", "Enter");
        GridLayout gridLayout3 = (GridLayout)findViewById(R.id.gridLayout3);
        Log.i("Info", "Enter2");
        for (int i = 0; i < gridLayout3.getChildCount(); i++){
            ((ImageView) gridLayout3.getChildAt(i)).setImageResource(0);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
