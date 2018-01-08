package ru.uj.fotoviewer;

/**
 * Created by Блохин Евгений on 02.11.2017.
 */

public class Foto2Presenter extends BasePresenter<IFoto2View> implements IFoto2Presenter {

    @Override
    public void bindView(IFoto2View view) {
        super.bindView((IFoto2View) view);
    }

//    @Override
//    public void saveFoto(Foto foto) {
//        Intent intent2 = new Intent((Context)view, MainActivity.class);
//        intent2.putExtra("data", foto);
//        ((Context)view).startActivity(intent2);
//    }
}
