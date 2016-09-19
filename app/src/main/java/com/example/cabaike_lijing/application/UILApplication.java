package com.example.cabaike_lijing.application;

import com.example.cabaike_lijing.R;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import android.app.Application;
import android.content.Context;

public class UILApplication extends Application{

	public static ImageLoader imageLoader = ImageLoader.getInstance();
	public static DisplayImageOptions options;
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		initImageLoader(getApplicationContext());
		
		options = new DisplayImageOptions.Builder()
		.showStubImage(R.drawable.default_imgs)// 加载等待时显示的图片
		.showImageForEmptyUri(R.drawable.blank)// 加载数据为空时显示的图片
		.showImageOnFail(R.drawable.losing)// 加载失败时显示的图片
		.cacheInMemory().cacheOnDisc() 
		/**
		 * .displayer(new RoundedBitmapDisplayer(20))
		 * 
		 * cacheInMemory()：缓存进内存
		 * cacheOnDisc() ：缓存到手机中
		 **/
		.build(); 
	}
	
	
	public static void initImageLoader(Context context) {
		// This configuration tuning is custom. You can tune every option, you may tune some of them, 
		// or you can create default configuration by
		// ImageLoaderConfiguration.createDefault(this);
		// method.
		ImageLoaderConfiguration config = 
				new ImageLoaderConfiguration.
					Builder(context)
					.threadPriority(Thread.NORM_PRIORITY - 2)//加载图片的线程数 ,默认一次性下载5张，NORM_PRIORITY - 2：一次性下载3张
					.denyCacheImageMultipleSizesInMemory()//解码图像的大尺寸将在内存中缓存先前解码图像的小尺寸
					.discCacheFileNameGenerator(new Md5FileNameGenerator())//设置磁盘缓存文件名称 
					.tasksProcessingOrder(QueueProcessingType.LIFO)//设置加载显示图片队列进程 
					.enableLogging() // Not necessary in common
					.build();
		// Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(config); //初始化
		//imageLoader.init(ImageLoaderConfiguration.createDefault(context));
			
	}

}
