package com.bays.model;

/**
 * Created by yinpeng on 2018/5/11.
 */
public class TextMessage extends BaseMessage {
    /**
     * 回复的消息内容
     */
    private String Content;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}