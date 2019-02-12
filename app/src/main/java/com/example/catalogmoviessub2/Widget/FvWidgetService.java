package com.example.catalogmoviessub2.Widget;

import android.content.Intent;
import android.widget.RemoteViewsService;

/**
 * Implementation of App Widget functionality.
 */
public class FvWidgetService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new MyFvRemoteFact(this.getApplicationContext(), intent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}

