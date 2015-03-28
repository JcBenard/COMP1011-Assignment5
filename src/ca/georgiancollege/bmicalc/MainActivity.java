package ca.georgiancollege.bmicalc;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity implements OnClickListener{

	private static final String HEIGHT = "HEIGHT";
	private static final String WEIGHT = "WEIGHT";
	private static final String BMI = "BMI";
	
	private int _height;
	private int _weight;
	private double _bmi;
	private EditText _weightEditText;
	private EditText _heightEditText;
	private EditText _bmiEditText;
	private EditText _bmiTypeEditText;
	private RadioGroup _radioGroup;
	private RadioButton _selectedButton;
	private Button _calculate;	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        if(savedInstanceState == null)
        {
        	this._height = 0;
        	this._weight = 0;
        	this._bmi = 0;
        }
        else
        {
        	this._height = savedInstanceState.getInt(HEIGHT);
        	this._weight = savedInstanceState.getInt(WEIGHT);
        	this._bmi = savedInstanceState.getDouble(BMI);
        }
        
        this._weightEditText = (EditText) findViewById(R.id.weightEditText);
        this._heightEditText = (EditText) findViewById(R.id.heightEditText);
        this._bmiEditText = (EditText) findViewById(R.id.bmiEditText);
        this._bmiTypeEditText = (EditText) findViewById(R.id.bmiTypeEditText);
        this._radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        this._calculate = (Button) findViewById(R.id.calculateButton); 
        
        this._calculate.setOnClickListener(this); 
        
    }
    
    public void onClick(View v){
	    int id = this._radioGroup.getCheckedRadioButtonId();
	    this._selectedButton = (RadioButton) findViewById(id);
	    String selection = (String) this._selectedButton.getText();
	    
	    this._bmiTypeEditText.setText(selection);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
