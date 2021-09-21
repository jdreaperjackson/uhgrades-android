package com.us.gradesearch;


import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.us.gradesearch.databinding.ActivityMainBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.us.gradesearch.Tools.isConnectedToInternet;
import static com.us.gradesearch.Tools.showBottomDialog;
import static com.us.gradesearch.Tools.showProgressDialog;


public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    APIInterface apiInterface = null;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initInterface();
        setUpRC();
        binding.btnSearch.setOnClickListener(view -> {
            startSearch();
        });

        binding.etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    startSearch();
                    return true;
                }
                return false;
            }
        });
    }

    private void initInterface() {
        apiInterface = APIClient.getInstance().create(APIInterface.class);
    }

    void startSearch() {
        String text = binding.etSearch.getText().toString().trim();
        text = text.replace(" ", "?");
        if (text == null || text.isEmpty()) {
            binding.etSearch.setError("Required*");
            return;
        } else if (!isConnectedToInternet(this)) {
            showBottomDialog(this, R.string.network_error, R.string.network_error_details);
            return;
        }
        loadSearch(text);
    }

    KProgressHUD progress;

    void loadSearch(String searchString) {
        progress = showProgressDialog(this,R.string.search_for_results);

        Call call = apiInterface.getSearchResultFromCourse(searchString);
        call.enqueue(callback);

    }

    Callback callback =  new Callback<List<ApiResponse>>()
    {

        @Override
        public void onResponse(Call<List<ApiResponse>> call, Response<List<ApiResponse>> response) {
            placeResult(response.body());
            progress.dismiss();
        }

        @Override
        public void onFailure(Call<List<ApiResponse>> call, Throwable t) {
            showBottomDialog(MainActivity.this,R.string.network_error, R.string.network_error_details);
            progress.dismiss();
        }
    };



    private void  placeResult(List<ApiResponse> result) {
        adapter.submitList(result);
        isEmptyResult(result);
    }

    private void isEmptyResult(List<ApiResponse> result) {
        if (result==null||result.isEmpty())
            binding.tvEmptyResult.setVisibility(  View.VISIBLE);
        else
            binding.tvEmptyResult.setVisibility(View.GONE);
    }

    ResponseAdapter adapter;

    private void  setUpRC() {
        adapter = new ResponseAdapter();
        binding.rcView.setLayoutManager(new LinearLayoutManager(this));
        binding.rcView.setHasFixedSize(true);
        binding.rcView.requestDisallowInterceptTouchEvent(true);
        binding.rcView.setAdapter(adapter);
    }
}
