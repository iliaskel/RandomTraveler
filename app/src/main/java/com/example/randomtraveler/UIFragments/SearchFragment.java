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

public class SearchFragment extends Fragment {

    private final String TAG = getClass().getSimpleName();
    private SearchFragmentListener listener;

    private EditText mStartingPointEt;
    private EditText mBudgetEt;
    private EditText mDaysEt;
    private Button mSearchBtn;

    public interface SearchFragmentListener{
        void searchButtonClicked(String startingPoint, String days, String budget);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.search_fragment,container,false);

        findViewsById(v);
        setUpListeners();

        return v;
    }

    private void setUpListeners() {

        mSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onSearchBtn clicked :: Entered ");
                String startingPoint = mStartingPointEt.getText().toString();
                String budget = mBudgetEt.getText().toString();
                String days = mDaysEt.getText().toString();
                if(startingPoint.equals("") || budget.equals("") || days.equals("") )
                {
                    showToast("Please fill all the required fields");
                }
                else{
                    listener.searchButtonClicked(startingPoint,days,budget);
                }
            }
        });
    }

    /**
     * Lifecycle methods
     */

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof SearchFragmentListener){
            listener = (SearchFragmentListener)context;
        }
        else {
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
    private void showToast(String text){
        Toast.makeText(getContext(),text,Toast.LENGTH_LONG).show();
    }

    private void findViewsById(View v) {

        mStartingPointEt = v.findViewById(R.id.search_fragment_starting_point_et);
        mBudgetEt = v.findViewById(R.id.search_fragment_budget_et);
        mDaysEt = v.findViewById(R.id.search_fragment_days_et);
        mSearchBtn = v.findViewById(R.id.search_fragment_sarch_btn);

    }

}
