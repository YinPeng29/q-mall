package com.bays.model.resp;

/**
 * Created by yinpeng on 2018/5/11.
 */
public class MusicMessage extends BaseMessage {
    /**
     * 音乐
     */
    private Music Music;

    public Music getMusic() {
        return Music;
    }

    public void setMusic(Music music) {
        Music = music;
    }
}