package com.example.abhij.tictaktoe;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class Game extends AppCompatActivity implements View.OnLongClickListener, View.OnClickListener {


    public static final int Player_O = 1;
    public static final int Player_X = 2;
    public static final int Player_NO = -1;

    int currentPlayer;
    Boolean gameOver;

    TextView t1;
    TextView t2;
    int side = 5;
    TTTButton board[][];
    LinearLayout rootLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        rootLayout = findViewById(R.id.rootLayout);
        t1 = findViewById(R.id.textPlayer1);
        t2 = findViewById(R.id.textPlayer2);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        side = bundle.getInt(First.Size);
        t1.setText(bundle.getString(First.Player1, ""));
        t2.setText(bundle.getString(First.Player2, ""));


        initGame();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater =getMenuInflater();
        inflater.inflate(R.menu.game_menu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {

            case R.id.playAgain:
                finish();
                startActivity(new Intent(this,First.class));
                return true;
            default :
                return super.onOptionsItemSelected(item);

        }


    }



    private void initGame() {
        //rootLayout.removeAllViews();
        gameOver=false;
        currentPlayer=Player_O;
        board=new TTTButton[side][side];
        setUpBoard();
    }

    private void setUpBoard() {

        int k=0;
        for(int i=0;i<side;i++)
        {
            LinearLayout row=new LinearLayout(this);
            LinearLayout.LayoutParams rowParams=
                    new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,0,1);

            row.setLayoutParams(rowParams);
            row.setOrientation(LinearLayout.HORIZONTAL);
            for(int j=0;j<side;j++)
            {
                TTTButton but=new TTTButton(this);
                LinearLayout.LayoutParams butParams =
                        new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT,1);
                but.setLayoutParams(butParams);

                but.setBackgroundResource(R.drawable.button);
                //but.setTextSize(20);
                but.setTextColor(Color.parseColor("#000000"));
                but.setId(k++);
                but.setTextSize(50);

                but.setOnClickListener(this);
                board[i][j]=but;
                row.addView(but);
            }
            rootLayout.addView(row);
        }

    }


    @Override
    public boolean onLongClick(View view) {
        return false;
    }

    @Override
    public void onClick(View view) {

        TTTButton button =findViewById(view.getId());
        if(!gameOver) {
            button.setPlayer(currentPlayer);

            togglePlayer();

            checkGameOver();
        }
    }

    private void checkGameOver() {

        //for Rows
        for(int row=0;row<side;row++){
            boolean rowSame=true;
            TTTButton rowfirstButton =board[row][0];

            for(int col=0;col<side;col++)
            {
                TTTButton currentButton=board[row][col];
                if(gameOver==true||currentButton.getPlayer()==Player_NO||rowfirstButton.getPlayer()!=currentButton.getPlayer())
                {
                    rowSame=false;
                    break;
                }
            }
            if(rowSame)
            {
                int player = board[row][0].getPlayer();
                onGameOver(player);
                gameOver=true;
                for(int a=0;a<side;a++)
                {
                    board[row][a].setBackgroundResource(R.drawable.won_bg);
                }

                return;
            }
        }

        //for Columns

        for(int col=0;col<side;col++)
        {
            boolean colSame=true;
            TTTButton firstColButton=board[0][col];
            for(int row=0;row<side;row++)
            {
                TTTButton currentButton=board[row][col];
                if(gameOver==true||currentButton.getPlayer()==Player_NO||firstColButton.getPlayer()!=currentButton.getPlayer())
                {
                    colSame=false;
                    break;
                }
            }
            if(colSame)
            {
                int player=board[0][col].getPlayer();
                onGameOver(player);
                gameOver=true;
                for(int i=0;i<side;i++)
                {
                    board[i][col].setBackgroundResource(R.drawable.won_bg);

                }
                return;
            }
        }

        //for first diagnol
        boolean firstdiagSame=true;

        for(int i=0;i<side;i++)
        {
            TTTButton firstButton=board[0][0];
            TTTButton currentButton=board[i][i];

            if(gameOver==true||currentButton.getPlayer()==Player_NO||currentButton.getPlayer()!=firstButton.getPlayer())
            {
                firstdiagSame=false;
                break;
            }
        }
        if(firstdiagSame)
        {
            gameOver=true;
            int player=board[0][0].getPlayer();
            onGameOver(player);
            for(int i=0;i<side;i++)
            {
                board[i][i].setBackgroundResource(R.drawable.won_bg);
            }
        }


    //for other diagnol
      boolean nextDiagSame=true;
        for(int i=0;i<side;i++)
        {
            TTTButton firstButton=board[0][side-1];
            TTTButton currentButton =board[i][side-1-i];

            if(gameOver==true||currentButton.getPlayer()==Player_NO||firstButton.getPlayer()!=currentButton.getPlayer())
            {
                nextDiagSame=false;
                break;
            }
        }
        if(nextDiagSame)
        {
            gameOver=true;
            int player=board[0][side-1].getPlayer();
            onGameOver(player);
            for(int i=0;i<side;i++)
            {
                board[i][side-1-i].setBackgroundResource(R.drawable.won_bg);
            }
        }


        //for Draw
        boolean draw=true;
        for(int row=0;row<side;row++)
        {
            for(int col=0;col<side;col++)
            {
                if(board[row][col].getPlayer()==Player_NO||gameOver==true)
                {
                    draw=false;
                    break;

                }
                if (!draw)
                    break;
            }
        }
        if(draw)
        {
            gameOver=true;
            onGameOver(Player_NO);
        }

    }

    private void onGameOver(int player) {

        if(player==Player_O)
        Toast.makeText(this,t1.getText()+" Won",Toast.LENGTH_LONG).show();
        if(player==Player_X)
            Toast.makeText(this,t2.getText()+" Won",Toast.LENGTH_LONG).show();
        if(player==Player_NO)
            Toast.makeText(this,"Draw",Toast.LENGTH_LONG).show();

        for(int i=0;i<side;i++)
        {
            for(int j=0;j<side;j++)
            {
                TTTButton b=board[i][j];
                b.setEnabled(false);
            }
        }

    }

    private void togglePlayer() {

        currentPlayer=currentPlayer==Player_O?Player_X:Player_O;

    }
}
