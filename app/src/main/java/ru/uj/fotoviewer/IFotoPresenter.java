package ru.uj.fotoviewer;

/**
 * Created by Блохин Евгений on 23.10.2017.
 */

public interface IFotoPresenter {
    void openFotoView(Foto foto);
    void getFotoList();
    void bindView(IFotoView view);
    void unbindView();
    void addFoto(Foto fotos);
}
