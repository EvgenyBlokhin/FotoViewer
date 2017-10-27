package ru.uj.fotoviewer;

/**
 * Created by Блохин Евгений on 27.10.2017.
 */

public abstract class BasePresenter<M, V> {
    protected M model;
    protected V view;

    public void bindView(V view) {
        this.view = view;
    }

    public void unbindView() {
        this.view = null;
    }
}
