package ru.uj.fotoviewer;

/**
 * Created by Блохин Евгений on 27.10.2017.
 */

public interface IFotoInteractor {
    interface OnGetFotosListener {
        void onSuccess(Foto[] fotos);
    }

    void getCities(IFotoInteractor.OnGetFotosListener listener);
}
