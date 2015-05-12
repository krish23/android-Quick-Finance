package com.example.account;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


public class UserSelector extends Activity 
{
	
	private RadioGroup radioUserGroup;
	private RadioButton radioUserButton;
	private Button userSubmit;
	private int usertype_key;
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		
		// remove title
				this.requestWindowFeature(Window.FEATURE_NO_TITLE);
				
				// set content
				setContentView(R.layout.cusomdialog);
				
				addListenerOnButton();
	}

	public void addListenerOnButton() {
		radioUserGroup = (RadioGroup) findViewById(R.id.radioUser);
		userSubmit = (Button) findViewById(R.id.btn_userOk);
		
		
		userSubmit.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				
				int selectedId = radioUserGroup.getCheckedRadioButtonId();
				
				radioUserButton = (RadioButton) findViewById(selectedId);
				
				if(radioUserButton.getText()=="Owner")
				{
					usertype_key = 3;
				}else if(radioUserButton.getText()=="Viewer")
				{
					usertype_key = 1;
				}else if(radioUserButton.getText()=="Administrator")
				{
					usertype_key = 4;
				}
				
				
				
				//Now pass the radio button value to next activity
				
				Intent intent = new Intent(UserSelector.this,userRegister.class);
				intent.putExtra("type", radioUserButton.getText());
				startActivity(intent);
			}
			
		});
		
	}	
}
