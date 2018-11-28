package my.edu.taruc.lab41sharedpref;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SettingActivity extends AppCompatActivity {
    private static final String PREF_NAME =" my.edu.taruc.lab41sharedpref";
    private EditText editTextName;
    private RadioGroup radioGroupGender;
    private RadioButton radioButtonMale, radioButtonFemale;
    private SharedPreferences sharedPreferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);


        editTextName = findViewById(R.id.editTextName);
        radioButtonMale = findViewById(R.id.radioButtonMale);
        radioButtonFemale = findViewById(R.id.radioButtonFemale);
        radioGroupGender = findViewById(R.id.radioGroupGender);
        //Context level shared preference file
        sharedPreferences = getSharedPreferences(PREF_NAME,MODE_PRIVATE);
        //Or activity level shared preference file

    }

    @Override
    protected void onResume() {
        super.onResume();
        String name;
        int gender; //default = -1, male = 1, female = 0

        name = sharedPreferences.getString(getString(R.string.user_name),
                getString(R.string.no_name));

        gender = sharedPreferences.getInt(getString(R.string.gender),
                -1);
        editTextName.setText(name);
        if(gender == 1){
            radioButtonMale.setChecked(true);
        }else if(gender == 0){
            radioButtonFemale.setChecked(true);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        //Create an editor
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if(!TextUtils.isEmpty(editTextName.getText().toString())){
            String name = editTextName.getText().toString();
            editor.putString(getString(R.string.user_name), name);
        }
        int gender = radioGroupGender.getCheckedRadioButtonId();
        if(gender == R.id.radioButtonMale){
            editor.putInt(getString(R.string.gender),1);
        }else if(gender == R.id.radioButtonFemale){
            editor.putInt(getString(R.string.gender),0);
        }
        //Apply changes to the Shared Preference
        editor.apply();
    }
}
