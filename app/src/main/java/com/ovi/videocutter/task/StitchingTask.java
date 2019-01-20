package com.ovi.videocutter.task;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.ovi.videocutter.inter.CompletionListener;
import com.ovi.videocutter.manager.VideoStitchingRequest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import nl.bravobit.ffmpeg.ExecuteBinaryResponseHandler;
import nl.bravobit.ffmpeg.FFmpeg;


public class StitchingTask implements Runnable {

    private Context context;
    private VideoStitchingRequest videoStitchingRequest;
    private CompletionListener completionListener;
    private String mFfmpegInstallPath;

    public StitchingTask(Context context, String mFfmpegInstallPath, VideoStitchingRequest stitchingRequest, CompletionListener completionListener) {
        this.context = context;
        this.mFfmpegInstallPath = mFfmpegInstallPath;
        this.videoStitchingRequest = stitchingRequest;
        this.completionListener = completionListener;
    }


    @Override
    public void run() {
        stitchVideo(context, mFfmpegInstallPath, videoStitchingRequest, completionListener);
    }


    private void stitchVideo(Context context, String mFfmpegInstallPath, VideoStitchingRequest videoStitchingRequest, final CompletionListener completionListener) {


        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "ffmpeg_videos";
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        final File inputfile = new File(path, "input.txt");

        try {
            inputfile.createNewFile();
            FileOutputStream out = new FileOutputStream(inputfile);
            for (String string : videoStitchingRequest.getInputVideoFilePaths()) {
                out.write(("file " + "'" + string + "'").getBytes());
                out.write("\n".getBytes());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] command1 = new String[] {
                "-f",
                "concat",
                "-safe",
                "0",
                "-i",
                inputfile.getAbsolutePath(),
                "-c",
                "copy",
                videoStitchingRequest.getOutputPath()
        };


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
                    inputfile.delete();
                }

            });



        } else {
            // ffmpeg is not supported
        }

        //inputfile.delete();

        completionListener.onProcessCompleted("Video Stitiching Comleted",null);

    }
}
