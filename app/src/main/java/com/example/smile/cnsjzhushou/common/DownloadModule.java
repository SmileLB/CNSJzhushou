package com.example.smile.cnsjzhushou.common;

import android.app.Application;
import android.os.Environment;

import com.example.smile.cnsjzhushou.common.util.ACache;

import java.io.File;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import zlc.season.rxdownload2.RxDownload;

import static android.os.Environment.DIRECTORY_DOWNLOADS;



@Module
public class DownloadModule {

    @Provides
    @Singleton
    public RxDownload provideRxDownload(Application application, Retrofit retrofit,File downDir){

        ACache.get(application).put(Constant.APK_DOWNLOAD_DIR,downDir.getPath());

       return RxDownload.getInstance(application)
            .defaultSavePath(downDir.getPath())
            .retrofit(retrofit)
                .maxDownloadNumber(10)
                .maxThread(10);
}
    @Singleton
    @Provides
//    @FileType("download")
    File provideDownloadDir(){
        return Environment.getExternalStoragePublicDirectory(DIRECTORY_DOWNLOADS);

    }
}
