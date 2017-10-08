package com.example.admin.othello;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public final static int NO_PLAYER = 0;
    public final static int PLAYER_1 = 1;
    public final static int PLAYER_2 = 2;
    public final static int INCOMPLETE = 0;
    public final static int  DRAW = 3;
    public int  PlayerTurn;
    public String currentcolor;
    public final static int PLAYER_1_WINS = 1;
    public final static int PLAYER_2_WINS = 2;
    public int black_palyer1=0;
    public int white_player2=0;
    MyButton[][] buttons;
    LinearLayout mainLayout;
    public static int n = 8;
    LinearLayout rowLayouts[];
    boolean player1Turn = true;
    boolean gameOver = false;
    String userName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpBoard();

    }
    public void setUpBoard(){
        player1Turn = true;
        gameOver = false;
        buttons = new MyButton[n][n];
        rowLayouts = new LinearLayout[n];
        mainLayout.removeAllViews();
        PlayerTurn=PLAYER_1;
       black_palyer1=0;
        white_player2=0;
        currentcolor="B";
        for(int i = 0; i < n; i++){
            rowLayouts[i] = new LinearLayout(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,0, 1f);
            params.setMargins(1,1,1,1);
            rowLayouts[i].setLayoutParams(params);
            rowLayouts[i].setOrientation(LinearLayout.HORIZONTAL);
            mainLayout.addView(rowLayouts[i]);
        }

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                buttons[i][j] = new MyButton(this);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1f);
                 params.setMargins(1,1,1,1);
                buttons[i][j].setLayoutParams(params);
                buttons[i][j].setOnClickListener(this);
                buttons[i][j].setTextSize(50);
                rowLayouts[i].addView(buttons[i][j]);

            }


        }
        buttons[3][3].setText("W");
        buttons[3][4].setText("B");
        buttons[4][3].setText("B");
        buttons[4][4].setText("W");
    }
    public int CheckWinner(){
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(buttons[i][j].getText()=="W"){
                    white_player2++;
                }
                if(buttons[i][j].getText()=="B"){
                    black_palyer1++;
                }
            }
        }
        if(white_player2>black_palyer1){
            return PLAYER_2_WINS;
        }
        else if(white_player2<black_palyer1){
            return PLAYER_1_WINS;
        }
        else {
            return DRAW;
        }
    }

    public void MakeAmove(MyButton p){
        int steprow[]={-1,0,1,1,1,0,-1,-1};
        int stepcol[]={-1,-1,-1,0,1,1,1,0};
        int i=p.x;
        int j=p.y;

        if(PlayerTurn==PLAYER_1){
        buttons[i][j].setText("B");}
        if(PlayerTurn==PLAYER_2){
            buttons[i][j].setText("W");
        }
        int q=0;
        for( q=0;q<8;q++){
            int x2=i+steprow[q];
            int y2=j+stepcol[q];
            if(inrange(x2,y2,n)){
                flip(x2,y2,q);
            }
        }
      if( PlayerTurn == PLAYER_1){
          PlayerTurn=PLAYER_2;
          currentcolor="W";
        //  hint();
      }
      else{
          PlayerTurn=PLAYER_1;
          currentcolor="B";
          //hint();

    }}
    public void flip(int x2,int y2,int q){
        int end=-1;
        int end1=-1;
        int end2=-1;
        if( buttons[x2][y2].getText()=="" || buttons[x2][y2].getText().toString()== currentcolor){
           return;
       }

       if(q==5){
           for(int i=y2;i<n;i++){
               if(buttons[x2][i].getText().toString()==currentcolor){
                   end=i;
                   break;
               }}
               if(end==-1){
                  return;
               }
               else{
                   for(int j= end;j>=y2;j--){
                       buttons[x2][j].setText(currentcolor);

                   }
                   end=-1;
               }

       }
       if(q==3){
         for(int i=x2;i<n;i++){
             if(buttons[i][y2].getText().toString()==currentcolor){
                 end=i;
                 break;
             }
         }
         if(end==-1){
             return;
         }
         else{
             for(int m=end;m>=x2;m--){
                 buttons[m][y2].setText(currentcolor);
             }
             end=-1;
         }
       }
       if(q==1){
           for(int i=y2;i>=0;i--){
               if(buttons[x2][i].getText().toString()==currentcolor){
                   end=i;
                   break;
               }
           }
           if(end==-1){
               return;
           }
           else{
               for(int m=end;m<=y2;m++){
                   buttons[x2][m].setText(currentcolor);
               }
               end=-1;
           }
       }
       
if(q==7){
    for(int i=x2;i>=0;i--){
        if(buttons[i][y2].getText().toString()==currentcolor){
            end=i;
            break;
        }
    }
    if(end==-1){
        return;
    }
    else{
        for(int m=end;m<x2;m++){
            buttons[m][y2].setText(currentcolor);
        }
        end=-1;
    }
}
if(q==0){
    for(int i=x2;i>=0;i--){
        if(buttons[i][i].getText().toString()==currentcolor){
            end1=i;
            end2=i;
            break;
        }
    }
    if(end1 == -1 || end2==-1){
        return;
    }
    else{
        for(int m=end1;m<=x2;m++){
            buttons[m][m].setText(currentcolor);
        }
        end1=-1;
        end2=-1;
    }
}
if(q==4){
    for(int i=x2;i<n;i++){
        if(buttons[i][i].getText().toString()==currentcolor){
            end1=i;
            end2=i;
            break;
        }
    }
    if(end1==-1 || end2==-1){
        return;
    }
    else{
        for(int m=end1;m>=x2;m--){
            buttons[m][m].setText(currentcolor);
        }
        end1=-1;
        end2=-1;
    }

}
if(q==2){
    for(int i=x2,j=y2;i<n&&j>=0;i++,j--){
       if(buttons[i][j].getText().toString()==currentcolor){
           end1=i;
           end2=j;
           break;
       }
    }
    if(end1==-1 || end2==-1){
        return;
    }
    else{
        for(int i=end1,j=end2;i>=x2 && j<=y2;i--,j++){
            buttons[i][j].setText(currentcolor);
        }
         end1=-1;
        end2=-1;
        }
    }
    if(q==6){
        for(int i=x2,j=y2;i>=0&&j<n;i--,j++){
            if(buttons[i][j].getText().toString()==currentcolor){
                end1=i;
                end2=j;
                break;
            }
        }
        if(end1==-1 || end2==-1){
            return;
        }
        else{
            for(int i=end1,j=end2;i<=x2 && j>=y2;i++,j--){
                buttons[i][j].setText(currentcolor);
            }
            end1=-1;
            end2=-1;
        }
    }
}


    public boolean inrange(int x2,int y2,int n){
        if(x2>=0 && x2<n && y2>=0 && y2<n){
            return true;
        }
        return false;
    }
public boolean isComplete(){
    for(int i=0;i<n;i++){
        for(int j=0;j<n;j++){
            if(buttons[i][j].getText()==""){
                return false;
            }
        }
    }
    return true;
}
    @Override
    public void onClick(View v) {
        MyButton b=(MyButton) v;
        if(isComplete()){
            int i=CheckWinner();
            if(i==PLAYER_1_WINS)
            Toast.makeText(this,"Player 1 Wins",Toast.LENGTH_SHORT).show();
            if(i==PLAYER_2_WINS)
                Toast.makeText(this,"Player 2 Wins",Toast.LENGTH_SHORT).show();
             if(i==DRAW)
                 Toast.makeText(this,"DRAW",Toast.LENGTH_SHORT).show();
            gameOver=true;
        }
        MakeAmove(b);

    }
}
