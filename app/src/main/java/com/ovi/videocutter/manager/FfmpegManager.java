package com.ovi.videocutter.manager;

import android.annotation.SuppressLint;
import android.content.Context;

import com.ovi.videocutter.R;
import com.ovi.videocutter.inter.CompletionListener;
import com.ovi.videocutter.task.StitchingTask;
import com.ovi.videocutter.task.TrimTask;
import com.ovi.videocutter.utils.Utils;

import java.io.File;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class FfmpegManager {

    private static FfmpegManager manager;

    private String mFfmpegInstallPath;

    private static int NUMBER_OF_CORES =
            Runtime.getRuntime().availableProcessors();
    private final BlockingQueue<Runnable> mDecodeWorkQueue = new LinkedBlockingQueue<Runnable>();
    private static final int KEEP_ALIVE_TIME = 1;
    private static final TimeUnit KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS;


    ThreadPoolExecutor mDecodeThreadPool = new ThreadPoolExecutor(
            NUMBER_OF_CORES,
            NUMBER_OF_CORES,
            KEEP_ALIVE_TIME,
            KEEP_ALIVE_TIME_UNIT,
            mDecodeWorkQueue);

    private FfmpegManager() {

    }
    public synchronized static FfmpegManager getInstance() {

        if (manager == null) {
            manager = new FfmpegManager();

        }
        return manager;
    }




    /**
     * Merger functionality
     * @param context
     * @param videoStitchingRequest
     * @param completionListener
     */
    public void stitchVideos(Context context, VideoStitchingRequest videoStitchingRequest, CompletionListener completionListener) {
        installFfmpeg(context);
        StitchingTask stitchingTask = new StitchingTask(context, mFfmpegInstallPath, videoStitchingRequest, completionListener);
        mDecodeThreadPool.execute(stitchingTask);
    }
    
    //切割操作
    public void trimVideo(Context context,File srcFile,File destFile,List<long[]> mNewSeeks,CompletionListener completionListener){
    	 installFfmpeg(context);
    	 TrimTask trimTask=new TrimTask(context, mFfmpegInstallPath, srcFile, destFile,mNewSeeks , completionListener);
    	 mDecodeThreadPool.execute(trimTask);
    }

    /**
     * Insert the path to FFmpeg (here I save it in the raw folder under the resource file)
     * @param context
     */
    @SuppressLint("NewApi") private void installFfmpeg(Context context) {

        String arch = System.getProperty("os.arch");//获取CPU的架构类型
        String arc = arch.substring(0, 3).toUpperCase();
        String rarc = "";
        int rawFileId;
        if (arc.equals("ARM")) {
            rawFileId = R.raw.ffmpeg;
        } else if (arc.equals("MIP")) {
            rawFileId = R.raw.ffmpeg;
        } else if (arc.equals("X86")) {
            rawFileId = R.raw.ffmpeg_x86;
        } else {
            rawFileId = R.raw.ffmpeg;
        }

        File ffmpegFile = new File(context.getCacheDir(), "ffmpeg");
        mFfmpegInstallPath = ffmpegFile.toString();
        Utils.installBinaryFromRaw(context, rawFileId, ffmpegFile);
        ffmpegFile.setExecutable(true);
    }

}
