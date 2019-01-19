package com.ovi.videocutter.inter;


import com.ovi.videocutter.view.ObservableScrollView;

public interface ScrollViewListener {

    void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy, boolean isByUser);

}  