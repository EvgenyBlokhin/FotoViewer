package ru.uj.fotoviewer;

/**
 * Created by Блохин Евгений on 23.10.2017.
 */

public interface IFotoView {
    void onGetFotos(Foto[] fotos);
    void showEmptyView();
    void hideEmptyView();
}
