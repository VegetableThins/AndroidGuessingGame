/**
 *  {A guessing game}
 *  @author {mcgi0145@algonquinlive.com}
 */

package com.example.vegetablethins.guessinggame;

import android.app.ActionBar;
import android.app.Activity;
import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Objects;
import java.util.Random;

public class MainActivity extends Activity {

    Random randomGenerator = new Random();
    Integer TheNumber = randomGenerator.nextInt(1000);
    Integer Counter = 0;

    private static final String ABOUT_DIALOG_TAG;

    static {
        ABOUT_DIALOG_TAG = "About Dialog";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        ActionBar actionBar = getActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.rgb(198,65,58)));


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button guessButton = (Button) findViewById(R.id.guessbutton);
        final Button resetButton = (Button) findViewById(R.id.button2);

        final TextView tooLow = (TextView) findViewById(R.id.tooLow);
        final TextView tooHigh = (TextView) findViewById(R.id.tooHigh);
        final TextView userInput = (TextView) findViewById(R.id.editText);
        final TextView topText = (TextView) findViewById(R.id.textView);
        final TextView winner = (TextView) findViewById(R.id.winnerBanner);


        guessButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Counter++;

                System.out.println(Counter);
                System.out.println(TheNumber);
                if (Objects.equals(userInput.getText().toString(), "")) {
                    userInput.setError("Please Enter a Number");
                } else {
                    int userText = Integer.valueOf(userInput.getText().toString());

                    if (userText < 1 || userText > 1000) {
                        userInput.setError("Please Enter a Value Between 1 and 1000");
                        tooHigh.setText("");
                        tooLow.setText("");
                        Counter = 0;
                    }
                    //System.out.println(userText);
                    if (userText < TheNumber) {
                        tooLow.setText("Too Low!");
                        tooHigh.setText("");
                    }
                    if (userText > TheNumber) {
                        tooHigh.setText("Too High!");
                        tooLow.setText("");
                    }
                    //superior win
                    if (userText == TheNumber && Counter < 6) {
                        winner.setText("WINNER");
                        winner.setTextColor(Color.GREEN);
                        topText.setText("!^*({[{(SUPERIOR WIN)}]})*^!");
                        topText.setTextColor(Color.GREEN);
                        tooHigh.setText("");
                        tooLow.setText("");
                        guessButton.setEnabled(false);
                        userInput.setEnabled(false);
                    }

                //excellent win
                if (userText == TheNumber && Counter < 10  && Counter > 6) {
                    topText.setText("Excellent Win!");
                    topText.setTextColor(Color.GREEN);
                    winner.setText("WINNER");
                    winner.setTextColor(Color.GREEN);
                    tooHigh.setText("");
                    tooLow.setText("");
                    guessButton.setEnabled(false);
                    userInput.setEnabled(false);
                }
                //lost state
                if (Counter == 11) {
                    winner.setText("Loser..");
                    winner.setTextColor(Color.RED);
                    topText.setText("Please Try Again..");
                    topText.setTextColor(Color.RED);
                    guessButton.setEnabled(false);
                    userInput.setEnabled(false);
                    tooHigh.setText("");
                    tooLow.setText("");
                }

            }
        }


    });

        resetButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Game Reset", Toast.LENGTH_SHORT).show();
                tooHigh.setText("");
                tooLow.setText("");
                winner.setText("");
                topText.setText("Guess a Number Between 1 and 1000");
                topText.setTextColor(Color.BLACK);
                tooHigh.setTextColor(Color.RED);
                tooLow.setTextColor(Color.BLUE);
                userInput.setText("");

                guessButton.setEnabled(true);
                userInput.setEnabled(true);

                TheNumber = randomGenerator.nextInt(1000);

                Counter = 0;
            }
        });
        resetButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(getApplicationContext(), TheNumber.toString(), Toast.LENGTH_LONG).show();

                return true;
            }

        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //TODO notice how I re-named "action_settings" to "action_about"
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            DialogFragment newFragment = new AboutDialogFragment();
            newFragment.show( getFragmentManager(), ABOUT_DIALOG_TAG );
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
