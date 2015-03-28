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

	//private instanced variables
	private static final String HEIGHT = "HEIGHT";
	private static final String WEIGHT = "WEIGHT";
	private static final String BMI = "BMI";
	private static final String SELECTION = "SELECTION";
	
	private double _height;
	private int _weight;
	private double _bmi;
	private String _selection;
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
        
        //check if the applcation has previous data and set the field to that information if there is
        if(savedInstanceState == null)
        {
        	this._height = 0;
        	this._weight = 0;
        	this._bmi = 0;
        	this._selection = "";
        }
        else
        {
        	this._height = savedInstanceState.getDouble(HEIGHT);
        	this._weight = savedInstanceState.getInt(WEIGHT);
        	this._bmi = savedInstanceState.getDouble(BMI);
        	this._selection = savedInstanceState.getString(SELECTION);
        }
        
        //link the variables to the application's gui
        this._weightEditText = (EditText) findViewById(R.id.weightEditText);
        this._heightEditText = (EditText) findViewById(R.id.heightEditText);
        this._bmiEditText = (EditText) findViewById(R.id.bmiEditText);
        this._bmiTypeEditText = (EditText) findViewById(R.id.bmiTypeEditText);
        this._radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        this._calculate = (Button) findViewById(R.id.calculateButton); 
        
        //create an event listener for the button
        this._calculate.setOnClickListener(this); 
        
    }
    
    @Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		
		outState.putInt(WEIGHT, this._weight);
		outState.putDouble(HEIGHT, this._height);
		outState.putDouble(BMI, this._bmi);
		outState.putString(SELECTION, this._selection);
	}
    
    public void onClick(View v){
    	//method variables
    	int id;
    	
    	//get what radio button is selected and put it into a string
	    id = this._radioGroup.getCheckedRadioButtonId();
	    this._selectedButton = (RadioButton) findViewById(id);
	    this._selection = (String) this._selectedButton.getText();
	    
	    //get the weight and height
	    this._weight = Integer.valueOf(this._weightEditText.getText().toString());
	    this._height = Double.valueOf(this._weightEditText.getText().toString());
	    
	    _calculateBmi();
	    
	    this._bmiEditText.setText(String.format("%.02f", this._bmi));
    }
    
    private void _calculateBmi(){
    	if(this._selection.equals("Imperial")){
    		this._bmi = ((this._weight * 703) / (this._height * this._height));
    		this._bmiTypeEditText.setText(this._selection + this._bmi);
    	}
    	else if (this._selection.equals("Metric")){
    		this._bmi = (this._weight / (this._height * this._height));
    		this._bmiTypeEditText.setText(this._selection + this._bmi);
    	}
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
