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
	    this._height = Double.valueOf(this._heightEditText.getText().toString());
	    
	    //call this method to calculate the bmi
	    _calculateBmi();
	    
	    //display the bmi in the proper field
	    this._bmiEditText.setText(String.format("%.01f", this._bmi));
	    
	    _bmiCatagory();
    }
    
    //calculate the bmi based on the selected units, the height and the weight
    private void _calculateBmi(){
    	if(this._selection.equals("Imperial")){
    		this._bmi = ((this._weight * 703) / (this._height * this._height));
    	}
    	else if (this._selection.equals("Metric")){
    		this._bmi = (this._weight / (this._height * this._height));
    	}
    }
    
    //set the text in the bmi type field acording to what catagory the bmi falls in
    private void _bmiCatagory(){

    	if(this._bmi < 18.5){
    		this._bmiTypeEditText.setText("You are in the underweight catagory");
    	}else if(this._bmi < 25){
    		this._bmiTypeEditText.setText("You are in the normal catagory");
    	}else if(this._bmi < 30){
    		this._bmiTypeEditText.setText("You are in the overweight catagory");
    	}else if(this._bmi >= 30){
    		this._bmiTypeEditText.setText("You are in the obese catagory");
    	}
    	
    	//attempted to do a swtich but android dosen't seem to like it
    	/*switch(true){
    		case(this._bmi < 18.5):
    			this._bmiTypeEditText.setText("You are in the underweight catagory");
    			break;
    		case(this._bmi < 25):
    			this._bmiTypeEditText.setText("You are in the normal catagory");
    			break;
    		case(this._bmi < 30):
    			this._bmiTypeEditText.setText("You are in the overweight catagory");
    			break;
    		case(this._bmi >= 30):
    			this._bmiTypeEditText.setText("You are in the obese catagory");
    			break;
    	}*/
    }
}
