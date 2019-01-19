package com.ovi.videocutter.utils;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public final class Utils {


	private static final String TAG = Utils.class.getSimpleName();
    private static final int IO_BUFFER_SIZE = 32256;


    private Utils() {
    }


	public static void installBinaryFromRaw(Context context, int resId,
			File file) {
		final InputStream rawStream = context.getResources().openRawResource(
				resId);
		final OutputStream binStream = getFileOutputStream(file);

		if (rawStream != null && binStream != null) {
			pipeStreams(rawStream, binStream);

			try {
				rawStream.close();
				binStream.close();
			} catch (IOException e) {
				Log.e(TAG, "Failed to close streams!", e);
			}

		}
	}




	public static OutputStream getFileOutputStream(File file) {
		try {
			return new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			Log.e(TAG, "File not found attempting to stream file.", e);
		}
		return null;
	}

	public static void pipeStreams(InputStream is, OutputStream os) {
		byte[] buffer = new byte[IO_BUFFER_SIZE];
		int count;
		try {
			while ((count = is.read(buffer)) > 0) {
				os.write(buffer, 0, count);
			}
		} catch (IOException e) {
			Log.e(TAG, "Error writing stream.", e);
		}
	}


	
}
