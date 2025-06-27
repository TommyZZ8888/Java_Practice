package com.www.designpattern.adapter;

/**
 * @Describtion: VlcPlayer
 * @Author: 张卫刚
 * @Date: 2025/6/27 9:14
 */
public class Mp4Player implements AdvancedMediaPlayer{

	@Override
	public void playVlc(String fileName) {
		//什么也不做
	}

	@Override
	public void playMp4(String fileName) {
		System.out.println("Playing mp4 file. Name: "+ fileName);
	}
}