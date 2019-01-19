package com.ovi.videocutter.inter;

import java.util.List;

/**
 * Created by Karthik on 22/01/16.
 */
public interface CompletionListener {

    void onProcessCompleted(String message, List<String> merger);

}
