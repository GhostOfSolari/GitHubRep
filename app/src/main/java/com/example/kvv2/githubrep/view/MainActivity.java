package com.example.kvv2.githubrep.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.kvv2.githubrep.presenter.PresenterFactory;
import com.example.kvv2.githubrep.R;
import com.example.kvv2.githubrep.model.tables.GitRepositoryTBL;
import com.example.kvv2.githubrep.RouterInterface;

import java.util.List;

public class MainActivity extends AppCompatActivity implements RouterInterface.MainViewInterface {

    private RouterInterface.MainViewPresenterInterface mPresenter;
    private ListView lvMain;
    private EditText etSearchText;
    private ProgressBar pbProcess;
    private Button btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPresenter = PresenterFactory.GetPresenter(this);

        lvMain = (ListView) findViewById(R.id.lvMain);
        pbProcess = (ProgressBar) findViewById(R.id.pbProcess);
        etSearchText = (EditText) findViewById(R.id.etSearchText);
        btnSearch = (Button) findViewById(R.id.btnSearch);

        pbProcess.setVisibility(View.INVISIBLE);
    }

    public void btnSearchClick(View v) {
        if (pbProcess.getVisibility() == View.INVISIBLE) {
            pbProcess.setVisibility(View.VISIBLE);
            btnSearch.setText(R.string.cancel);
            lvMain.setAdapter(null);
            mPresenter.getData("" + etSearchText.getText(), onGetData);
        } else {
            btnSearch.setText(R.string.search);
            pbProcess.setVisibility(View.INVISIBLE);
            mPresenter.cancel();
        }
    }

    private RouterInterface.OnGetData onGetData = new RouterInterface.OnGetData() {
        @Override
        public void callBack(List<GitRepositoryTBL> listData, boolean isSuccessful) {
            viewData(listData, isSuccessful);
        }
    };

    public void viewData(List<GitRepositoryTBL> listData, boolean isSuccessful) {
        btnSearch.setText(R.string.search);
        pbProcess.setVisibility(View.INVISIBLE);

        if (!isSuccessful || (listData == null)) return;

        ArrayAdapter<GitRepositoryTBL> adapter = new ArrayAdapter<GitRepositoryTBL>(this,
                android.R.layout.simple_list_item_1, listData);

        lvMain.setAdapter(adapter);
    }
}
