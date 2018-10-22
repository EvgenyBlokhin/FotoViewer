package ru.uj.fotoviewer;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

import ru.uj.fotoviewer.Adapters.FotoViewerListAdapter;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, IFotoView {

    public static final int FOTO_CAMERA = 2;
    final String TAG = "myLogs";
    private FloatingActionButton mFab;
    private RecyclerView mRecyclerView;
    private FotoViewerListAdapter mAdapter;
    private IFotoPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            mPresenter = new FotoPresenter();
        } else {
            mPresenter = PresenterHolder.getInstance().restorePresenter(savedInstanceState);
        }
        setContentView(R.layout.main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mFab = findViewById(R.id.fab);
        mFab.setOnClickListener(this);

        mRecyclerView = findViewById(R.id.rvFotos);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));
        mAdapter = new FotoViewerListAdapter(new ArrayList<Foto>(), this, mPresenter);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                Intent intent = new Intent(this, Main2Activity.class);
                startActivityForResult(intent, FOTO_CAMERA);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mPresenter.bindView(this);
        if (requestCode == FOTO_CAMERA) {
            if (resultCode == RESULT_OK) {
                if (data == null) {
                    Log.d(TAG, "Intent1 is null");
                } else {
                    Foto foto = (Foto) data.getParcelableExtra("data");
                    mPresenter.addFoto(foto);
                }
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mPresenter == null)
        mPresenter.bindView(this); //Unable to resume activity {ru.uj.fotoviewer/ru.uj.fotoviewer.MainActivity}: java.lang.NullPointerException:
        // Attempt to invoke interface method 'void ru.uj.fotoviewer.IFotoPresenter.bindView(ru.uj.fotoviewer.IFotoView)' on a null object reference
        if (this == null)
        mPresenter.getFotoList();
    }

    @Override
    protected void onPause() {
        mPresenter.unbindView();
        super.onPause();
    }

    @Override
    public void onGetFotos(ArrayList<Foto> fotos) {
        mAdapter.setFoto(fotos);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        PresenterHolder.getInstance().savePresenter((BasePresenter<?>) mPresenter, outState);
    }
}
