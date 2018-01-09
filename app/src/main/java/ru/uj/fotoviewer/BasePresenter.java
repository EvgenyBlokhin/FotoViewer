package ru.uj.fotoviewer;

/**
 * Created by Blokhin Evgeny on 27.10.2017.
 */

public abstract class BasePresenter<V> {
    protected V view;

    public void bindView(V view) {
        this.view = view;
    }

    public void unbindView() {
        this.view = null;
    }
}
