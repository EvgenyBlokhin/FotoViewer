package ru.uj.fotoviewer;

import android.content.Context;
import android.content.Intent;

/**
 * Created by Блохин Евгений on 23.10.2017.
 */

public class FotoPresenter extends BasePresenter<IFotoInteractor, IFotoView> implements IFotoPresenter, IFotoInteractor.OnGetFotosListener {
    private Foto[] loadedFotos;

    public FotoPresenter() {
        this.model = new FotoInteractor();
    }

    @Override
    public void openFotoView(Foto foto) {

    }

    @Override
    public void getFotoList() {
        if (loadedFotos != null){
            this.view.onGetFotos(loadedFotos);
            return;
        }
        this.model.getCities(this);
    }

    @Override
    public void bindView(IFotoView view) {
        super.bindView((IFotoView) view);
    }

    @Override
    public void onSuccess(Foto[] fotos) {
        loadedFotos = fotos;
        this.view.onGetFotos(fotos);
    }
}
