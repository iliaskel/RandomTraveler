package com.example.randomtraveler.UIFragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.randomtraveler.R;
import com.example.randomtraveler.Utils.StringHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SignUpFragment extends Fragment {

    private final String TAG = getClass().getSimpleName();
    private SignUpFragmentListener signUpFragmentListener;

    private EditText mEmail;
    private EditText mPassword;
    private EditText mPasswordConfirm;
    private EditText mAge;
    private Button mSignUpBtn;

    public interface SignUpFragmentListener{
        void OnSignUpClicked(String email,String password,int age);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.sign_up_fragment,container,false);

        findViewsById(v);
        setUpListeners();

        return v;
    }

    private void setUpListeners() {
        mSignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "OnSignUpClicked :: Entered ");
                if(areCredentialsOk()){
                    signUpFragmentListener.OnSignUpClicked(
                            mEmail.getText().toString(),
                            mPassword.getText().toString(),
                            Integer.parseInt(mAge.getText().toString()));
                }

            }
        });
    }

    private void findViewsById(View v) {
        mEmail = v.findViewById(R.id.sign_up_email_et);
        mPassword = v.findViewById(R.id.sign_up_password_et);
        mPasswordConfirm = v.findViewById(R.id.sign_up_confirm_password_et);
        mAge = v.findViewById(R.id.sign_up_age_et);
        mSignUpBtn = v.findViewById(R.id.sign_up_sign_up_btn);
    }

    private boolean areCredentialsOk() {

        String email = mEmail.getText().toString();
        String pswd = mPassword.getText().toString();
        String pswdConf = mPasswordConfirm.getText().toString();
        int age = Integer.parseInt(mAge.getText().toString());


        if(!StringHelper.emailCheck(email)){
            Log.d(TAG, "areCredentialsOk :: Email is invalid");
            ShowToast("Email is valid...");
            return false;
        }
        if(!pswd.equals(pswdConf)){
            Log.d(TAG, "areCredentialsOk :: Passwords don't match");
            ShowToast("Passwords don't match...");
            return false;
        }
        if(age<18){
            Log.d(TAG, "areCredentialsOk :: Age invalid");
            ShowToast("Age must be over 18");
            return false;
        }
        return true;
    }


    /**
     * Lifecycle methods
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof SignInFragment.SignInFragmentListener) {
            signUpFragmentListener = (SignUpFragment.SignUpFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement FragmentAListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        signUpFragmentListener = null;
    }

    /**
     * Other methods
     **/

    private void ShowToast(String text){
        Toast.makeText(getContext(),text,Toast.LENGTH_LONG).show();
    }
}
