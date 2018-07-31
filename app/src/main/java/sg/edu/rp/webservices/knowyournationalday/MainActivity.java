package sg.edu.rp.webservices.knowyournationalday;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.sql.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> al =new ArrayList<String>();
    ArrayAdapter<String> adapter;
    ListView lv;
    String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView)findViewById(R.id.lvItem);
        al.add("Singapore National Day is on 9 Aug ");
        al.add("Singapore is 53 years old ");
        al.add("Theme is We are Singapore ");

        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, al);
        lv.setAdapter(adapter);

        LayoutInflater inflater = (LayoutInflater)
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final LinearLayout passPhrase =
                (LinearLayout) inflater.inflate(R.layout.access, null);
        final EditText etPassphrase = (EditText) passPhrase
                .findViewById(R.id.etAccess);


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Access Code")
                .setView(passPhrase)
                .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        String code = etPassphrase.getText().toString();
                        //final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                        //etPassphrase.setText(prefs.getString("password",""));

                        if (code.equals("738964")) {

                            SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                            SharedPreferences.Editor editor = pref.edit();
                            editor.putBoolean("isLogined", true);
                            editor.commit();
                            Toast.makeText(MainActivity.this, "You had entered " +
                                    etPassphrase.getText().toString(), Toast.LENGTH_LONG).show();
                            //String code = etPassphrase.getText().toString();




                        } else {
                            Toast.makeText(MainActivity.this,
                                   "Wrong. Try Again.", Toast.LENGTH_LONG).show();
                            finish();



                        }
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Tally against the respective action item clicked
        //  and implement the appropriate action
        if (item.getItemId() == R.id.itemSend) {


            String [] list = new String[] { "Email","SMS"};

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Select the way to enrich your frienc")
                    // Set the list of items easily by just supplying an
                    //  array of the items
                    .setItems(list, new DialogInterface.OnClickListener() {
                        // The parameter "which" is the item index
                        // clicked, starting from 0
                        public void onClick(DialogInterface dialog, int which) {
                            if (which == 0) {
                                Intent email = new Intent(Intent.ACTION_SEND);
                                //email.putExtra(Intent.EXTRA_EMAIL,new String[]);

                                for (int i = 0; i < al.size(); i++){
                                    message += al.get(i) + "\n";

                                }

                                Log.d("Messge given to me by: ", message);
                                // The action you want this intent to do;
                                // ACTION_SEND is used to indicate sending text
                                //Intent email = new Intent(Intent.ACTION_SEND);
                                // Put essentials like email address, subject & body text
                                email.putExtra(Intent.EXTRA_EMAIL,
                                        new String[]{"jason_lim@rp.edu.sg"});
                                email.putExtra(Intent.EXTRA_SUBJECT,
                                        "Things about Singapore");
                                email.putExtra(Intent.EXTRA_TEXT,
                                        message);
                                // This MIME type indicates email
                                email.setType("message/rfc822");
                                // createChooser shows user a list of app that can handle
                                // this MIME type, which is, email
                                startActivity(Intent.createChooser(email,
                                        "Choose an Email client :"));

                                startActivity(Intent.createChooser(email,"Choose an Email client :"));

                            } else if (which == 1) {
                                Intent intent = new Intent(Intent.ACTION_MAIN);
                                intent.addCategory(Intent.CATEGORY_APP_MESSAGING);
                                startActivity(intent);
                            }
                        }
                    });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();

            /*AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Are you sure?")
                    // Set text for the positive button and the corresponding
                    //  OnClickListener when it is clicked
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {			  Toast.makeText(MainActivity.this, "You clicked yes",
                                Toast.LENGTH_LONG).show();
                        }
                    })
                    // Set text for the negative button and the corresponding
                    //  OnClickListener when it is clicked
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {			  Toast.makeText(MainActivity.this, "You clicked no",
                                Toast.LENGTH_LONG).show();
                        }
                    });
            // Create the AlertDialog object and return it
            AlertDialog alertDialog = builder.create();
            alertDialog.show();*/
        }
        else if (item.getItemId() == R.id.itemQuiz) {
            LayoutInflater inflater = (LayoutInflater)
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            LinearLayout passPhrase =
                    (LinearLayout) inflater.inflate(R.layout.quiz, null);
            //final EditText etPassphrase = (EditText) passPhrase
                    //.findViewById(R.id.etAccess);
            final RadioButton rb1 = (RadioButton)passPhrase.findViewById(R.id.radioButton);
            final RadioButton rb2 = (RadioButton)passPhrase.findViewById(R.id.radioButton2);
            final RadioButton rb3 = (RadioButton)passPhrase.findViewById(R.id.radioButton3);
            final RadioButton rb4 = (RadioButton)passPhrase.findViewById(R.id.radioButton4);
            final RadioButton rb5 = (RadioButton)passPhrase.findViewById(R.id.radioButton5);
            final RadioButton rb6 = (RadioButton)passPhrase.findViewById(R.id.radioButton6);


            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Access Code")
                    .setView(passPhrase)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int id) {

                            int score = 0;
                            //RadioButton rb1 = (RadioButton)findViewById(R.id.radioButton);
                            //RadioButton rb2 = (RadioButton)findViewById(R.id.radioButton2);
                            //RadioButton rb3 = (RadioButton)findViewById(R.id.radioButton3);
                            //RadioButton rb4 = (RadioButton)findViewById(R.id.radioButton4);
                            //RadioButton rb5 = (RadioButton)findViewById(R.id.radioButton5);
                            //RadioButton rb6 = (RadioButton)findViewById(R.id.radioButton6);
                            if(rb2.isChecked()){
                                score += 1;
                            }if (rb3.isChecked()){
                                score += 1;
                            }if(rb5.isChecked()){
                                score += 1;
                            }

                            Toast.makeText(MainActivity.this, "You scored " + score,
                                    Toast.LENGTH_LONG).show();

                        }
                    })
                    .setNegativeButton("Don't Know Lah", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    Toast.makeText(MainActivity.this, "You clicked no",
                            Toast.LENGTH_LONG).show();
                }
                });


            //AlertDialog alertDialog = builder.create();
            //alertDialog.show();
            AlertDialog alertDialog = builder.create();
            alertDialog.show();

        }else{
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Quit?")
                    // Set text for the positive button and the corresponding
                    //  OnClickListener when it is clicked
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {			  Toast.makeText(MainActivity.this, "You clicked yes",
                                Toast.LENGTH_LONG).show();
                        finish();
                        }
                    })
                    // Set text for the negative button and the corresponding
                    //  OnClickListener when it is clicked
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {			  Toast.makeText(MainActivity.this, "You clicked no",
                                Toast.LENGTH_LONG).show();
                        }
                    });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
            //finish();
        }



        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.other, menu);
        return true;
    }
}
