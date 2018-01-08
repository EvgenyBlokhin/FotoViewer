package ru.uj.fotoviewer;

import android.os.Bundle;
import android.support.test.espresso.core.deps.guava.cache.Cache;
import android.support.test.espresso.core.deps.guava.cache.CacheBuilder;
import android.util.Log;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Блохин Евгений on 27.10.2017.
 */

public class PresenterHolder {
    private static final String SIS_KEY_PRESENTER_ID = "presenter_id";
    private static PresenterHolder instance;
    final String TAG = "myLogs";
    private final AtomicLong currentId;
    private final Cache<Long, BasePresenter<?>> presenters;

    PresenterHolder(long maxSize, long expirationValue, TimeUnit expirationUnit) {
        currentId = new AtomicLong();
        presenters = CacheBuilder.newBuilder()
                .maximumSize(maxSize)
                .expireAfterWrite(expirationValue, expirationUnit)
                .build();
    }

    public static PresenterHolder getInstance() {
        if (instance == null) {
            instance = new PresenterHolder(10, 30, TimeUnit.SECONDS);
        }
        return instance;
    }

    public <P extends BasePresenter<?>> P restorePresenter(Bundle savedInstanceState) {
        Long presenterId = savedInstanceState.getLong(SIS_KEY_PRESENTER_ID);
        Log.d(TAG, "mPresenter restorePresenter Long presenterId " + presenterId);
        P presenter = (P) presenters.getIfPresent(presenterId);
        Log.d(TAG, "mPresenter restorePresenter presenter " + presenter);
        presenters.invalidate(presenterId);
        Log.d(TAG, "mPresenter restorePresenter presenters FotoPresenter" + presenters.toString().getClass());
        if (presenter == null)
            Log.d(TAG, "mPresenter restorePresenter presenter is null");
        return presenter;
    }

    public void savePresenter(BasePresenter<?> presenter, Bundle outState) {
        long presenterId = currentId.incrementAndGet();
        Log.d(TAG, "mPresenter savePresenter Long presenterId " + presenterId);
        presenters.put(presenterId, presenter);
        outState.putLong(SIS_KEY_PRESENTER_ID, presenterId);
    }
}