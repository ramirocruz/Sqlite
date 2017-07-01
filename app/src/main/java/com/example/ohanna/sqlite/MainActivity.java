package com.example.ohanna.sqlite;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edid,edfname,edlname,edsal;
    Button btnins,btnupdate,btndelete,btnload;
    TextView text;
    DBhelper dBhelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dBhelper=new DBhelper(this);
        init();
    }

    private void init() {
    edid=(EditText)findViewById(R.id.editText);
    edfname=(EditText)findViewById(R.id.editText2);
    edlname=(EditText)findViewById(R.id.editText3);
    edsal=(EditText)findViewById(R.id.editText4);
    btnins=(Button) findViewById(R.id.button);
    btnupdate=(Button)findViewById(R.id.button2);
    btndelete=(Button)findViewById(R.id.button3);
    btnload=(Button)findViewById(R.id.button4);
    text=(TextView)findViewById(R.id.textView);
    btnins.setOnClickListener(clickobj);
    btnupdate.setOnClickListener(clickobj);
    btndelete.setOnClickListener(clickobj);
    btnload.setOnClickListener(clickobj);

    }

    View.OnClickListener clickobj=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int i=view.getId();
            long result;

            switch(i){
            case R.id.button:try {


                 result=dBhelper.insert(Integer.parseInt(getdata(edid)),getdata(edfname),getdata(edlname),Double.parseDouble(getdata(edsal)));


                if (result<0){
                    Toast.makeText(MainActivity.this,"Insertion error. ",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Inserted : "+result,Toast.LENGTH_SHORT).show();
                }
            }catch (NumberFormatException e){
                Toast.makeText(MainActivity.this,e.getLocalizedMessage(),Toast.LENGTH_SHORT).show();}
                break;

            case R.id.button2:try{
                result=dBhelper.update(Integer.parseInt(getdata(edid)),getdata(edfname),getdata(edlname),Double.valueOf(getdata(edsal)));
                if(result==0)
                {
                    Toast.makeText(MainActivity.this,"Updation error.",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this,"Updated : "+result,Toast.LENGTH_SHORT).show();
                }
            }catch (Exception e){
                Toast.makeText(MainActivity.this,e.getLocalizedMessage(),Toast.LENGTH_SHORT).show();}
                break;
            case R.id.button3:try{
                result=dBhelper.delete(Integer.parseInt(getdata(edid)));
                if(result==-1){
                    Toast.makeText(MainActivity.this,"Deletion error..",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this,"Deleted : "+result,Toast.LENGTH_SHORT).show();
                }
            }catch (NumberFormatException e){
                Toast.makeText(MainActivity.this,e.getLocalizedMessage(),Toast.LENGTH_SHORT).show();}
                break;
            case R.id.button4:try{
                Cursor cursor=dBhelper.loadall();
                StringBuffer slong=new StringBuffer();
                for(cursor.moveToFirst();!cursor.isLast();cursor.moveToNext()){
                    slong.append(cursor.getInt(0)+" ");
                    slong.append(cursor.getString(1)+" ");
                    slong.append(cursor.getString(2)+" ");
                    slong.append(cursor.getDouble(3)+"\n");
                }
                text.setText(slong);
            }catch (Exception e){
                Toast.makeText(MainActivity.this,e.getLocalizedMessage(),Toast.LENGTH_SHORT).show();}
                break;
            default:Toast.makeText(MainActivity.this,"Unknown error..",Toast.LENGTH_SHORT).show();
        }
        }
     };

    @Override
    protected void onStop() {
        dBhelper.close();
        super.onStop();
    }

    private String getdata(EditText edit) {
        return edit.getText().toString().trim();
    }
}
