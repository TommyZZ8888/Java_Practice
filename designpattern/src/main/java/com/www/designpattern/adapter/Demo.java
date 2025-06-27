package com.www.designpattern.adapter;

/**
 * @Describtion: Demo
 * @Author: 张卫刚
 * @Date: 2025/6/27 9:15
 */
public class Demo {
	public static void main(String[] args) {
		AudioPlayer audioPlayer = new AudioPlayer();

		audioPlayer.play("mp3", "beyond the horizon.mp3");
		audioPlayer.play("mp4", "alone.mp4");
		audioPlayer.play("vlc", "far far away.vlc");
		audioPlayer.play("avi", "mind me.avi");
	}
}
