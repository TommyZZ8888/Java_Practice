package com.www.designpattern.adapter;

/**
 * @Describtion: VlcPlayer
 * @Author: 张卫刚
 * @Date: 2025/6/27 9:14
 */
public class VlcPlayer implements AdvancedMediaPlayer{
	@Override
	public void playVlc(String fileName) {
		System.out.println("Playing vlc file. Name: "+ fileName);
	}

	@Override
	public void playMp4(String fileName) {
		//什么也不做
	}
}
