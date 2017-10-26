package ru.uj.fotoviewer;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import ru.uj.fotoviewer.Adapters.FotoViewerListAdapter;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, IFotoView {

    FloatingActionButton fab;
    private RecyclerView mRecyclerView;
    private TextView tvEmpty;
    private FotoViewerListAdapter mAdapter;
    IFotoPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                Intent intent = new Intent(this, Main2Activity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.bindView(this);
        mPresenter.getFotoList();
    }

    @Override
    protected void onPause() {
        mPresenter.unbindView();
        super.onPause();
    }

    @Override
    public void onGetFotos(Foto[] fotos) {
        mAdapter.setFoto(fotos);
    }

    @Override
    public void showEmptyView() {
        tvEmpty.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.GONE);
    }

    @Override
    public void hideEmptyView() {
        tvEmpty.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }
}
