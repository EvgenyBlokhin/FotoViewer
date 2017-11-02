package ru.uj.fotoviewer;

import java.util.ArrayList;

/**
 * Created by Блохин Евгений on 23.10.2017.
 */
public class FotoPresenter extends BasePresenter<IFotoView> implements IFotoPresenter {
    private ArrayList<Foto> loadedFotos = new ArrayList<>();
    @Override
    public void openFotoView(Foto foto) {

    }

    @Override
    public void getFotoList() {
        if (loadedFotos != null){
            this.view.onGetFotos(loadedFotos);
            return;
        }
    }

    @Override
    public void bindView(IFotoView view) {
        super.bindView((IFotoView) view);
    }

    @Override
    public void addFoto(Foto fotos) {
        loadedFotos.add(fotos);
        this.view.onGetFotos(loadedFotos);
    }
}
