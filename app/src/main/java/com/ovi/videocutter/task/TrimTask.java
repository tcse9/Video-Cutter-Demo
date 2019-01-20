package com.ovi.videocutter.task;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.util.Log;

import com.ovi.videocutter.EditVedioActivity;
import com.ovi.videocutter.R;
import com.ovi.videocutter.inter.CompletionListener;
import com.ovi.videocutter.utils.BitmapUtils;
import com.ovi.videocutter.utils.TimeFormatUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import nl.bravobit.ffmpeg.ExecuteBinaryResponseHandler;
import nl.bravobit.ffmpeg.FFmpeg;


public class TrimTask implements Runnable{

    private Context context;
    private CompletionListener completionListener;
    private String mFfmpegInstallPath;
    private File srcFile;
    private File destFile;
    private List<long[]> mNewSeeks;
    private List<String> mMergeList=new ArrayList<String>();
    public TrimTask(Context context, String mFfmpegInstallPath, File srcFile,File destFile,List<long[]> mNewSeeks, CompletionListener completionListener) {
        this.context = context;
        this.mFfmpegInstallPath = mFfmpegInstallPath;
        this.completionListener = completionListener;
        this.srcFile=srcFile;
        this.destFile=destFile;
        this.mNewSeeks=mNewSeeks;


    }


    @Override
    public void run() {
    	trimVideo(context, mFfmpegInstallPath, completionListener, mNewSeeks, srcFile, destFile);
    }

    /**
     *
     * @param context
     * @param mFfmpegInstallPath
     * @param completionListener
     * @param mNewSeeks
     * @param srcFile
     * @param destFile
     */
    private void trimVideo(Context context, String mFfmpegInstallPath,  final CompletionListener completionListener,final List<long[]> mNewSeeks,File srcFile,File destFile) {

    	mMergeList.clear();
    	for(int i=0;i<mNewSeeks.size();i++){

    		File myMovie = new File(destFile, String.format("0cut_output-%s.mp4", System.currentTimeMillis()+""));
			mMergeList.add(myMovie.getAbsolutePath());
			String startTime= TimeFormatUtils.formatLongToTimeStr((int)mNewSeeks.get(i)[0]);
			String durationTime=TimeFormatUtils.formatLongToTimeStr((int)(mNewSeeks.get(i)[1]-mNewSeeks.get(i)[0]));

            String [] command1 = new String[]{"-i" ,srcFile.getAbsolutePath(),"-vcodec", "copy", "-acodec", "copy","-ss",startTime ,"-t" ,  durationTime,myMovie.getAbsolutePath(),"-y"};;


            if (FFmpeg.getInstance(context).isSupported()) {
				// ffmpeg is supported
				FFmpeg.getInstance(context).execute(command1, new ExecuteBinaryResponseHandler() {

					@Override
					public void onStart() {
                        Log.e("***START", "start");
                    }

					@Override
					public void onProgress(String message) {}

					@Override
					public void onFailure(String message) {
					    Log.e("***FAILED", ""+message);
                    }

					@Override
					public void onSuccess(String message) {
                        Log.e("***SUCCESS", ""+message);

                    }

					@Override
					public void onFinish() {
                        Log.e("***FINISH", "finish");

                    }

				});



			} else {
				// ffmpeg is not supported
			}
    	}



        completionListener.onProcessCompleted("Video trim Comleted",mMergeList);


    }


}
