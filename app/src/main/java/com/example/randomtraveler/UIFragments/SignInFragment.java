package com.example.randomtraveler.UIFragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.randomtraveler.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SignInFragment extends Fragment {

    private final String TAG = getClass().getSimpleName();

    private SignInFragmentListener listener;
    private EditText mUsernameEt;
    private EditText mPasswordEt;
    private Button mSignInBtn;
    private TextView mCreateAccountTv;
    private TextView mForgotPasswordTv;



    public interface SignInFragmentListener{
        void onSignInClicked(String username, String password);
        void onCreateAccountClicked();
        void onForgotPasswordClicked();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.sign_in_fragment,container,false);

        findViewsById(v);
        setUpListeners();

        return v;

    }

    private void setUpListeners() {

        mSignInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Sign in button pressed");
                String email = mUsernameEt.getText().toString();
                String password = mPasswordEt.getText().toString();
                listener.onSignInClicked(email,password);
            }
        });

        mForgotPasswordTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Forgot password",Toast.LENGTH_LONG).show();
            }
        });

        mCreateAccountTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Create account",Toast.LENGTH_LONG).show();
                listener.onCreateAccountClicked();
            }
        });
    }

    private void findViewsById(View v) {
        mUsernameEt = v.findViewById(R.id.sign_in_email_et);
        mPasswordEt = v.findViewById(R.id.sing_in_password_et);
        mCreateAccountTv = v.findViewById(R.id.sign_in_create_account_tv);
        mForgotPasswordTv = v.findViewById(R.id.sign_in_password_forgot_tv);
        mSignInBtn  = v.findViewById(R.id.sign_in_sign_in_btn);
    }


    /**
     * Lifecycle methods
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof SignInFragmentListener) {
            listener = (SignInFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement FragmentAListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }


    /**
     * Other methods
     **/
    private void ShowText(String text){
        Toast.makeText(getContext(),text,Toast.LENGTH_LONG).show();
    }

}
