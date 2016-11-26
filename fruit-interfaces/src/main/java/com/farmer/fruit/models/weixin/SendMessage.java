package com.farmer.fruit.models.weixin;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.List;

@XStreamAlias("xml")
public class SendMessage {
	
	 
	@XStreamAlias("ToUserName")
	private String toUserName; 
	@XStreamAlias("FromUserName")
	private String fromUserName;
	@XStreamAlias("CreateTime")
	private long createTime;
	@XStreamAlias("MsgType")
	private String msgType;
	@XStreamAlias("Content")
	private String content;
	
	@XStreamAlias("Image")
	private Image image;
	@XStreamAlias("Voice")
	private Voice voice;
	@XStreamAlias("video")
	private Video video;
	@XStreamAlias("Music")
	private Music music;
	@XStreamAlias("Articles")
	private List<Articles> news;
	
	@XStreamAlias("MsgId")
	private String msgId;
	
	
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}
	public Voice getVoice() {
		return voice;
	}
	public void setVoice(Voice voice) {
		this.voice = voice;
	}
	public Video getVideo() {
		return video;
	}
	public void setVideo(Video video) {
		this.video = video;
	}
	public Music getMusic() {
		return music;
	}
	public void setMusic(Music music) {
		this.music = music;
	}
	public List<Articles> getNews() {
		return news;
	}
	public void setNews(List<Articles> news) {
		this.news = news;
	}
	public String getToUserName() {
		return toUserName;
	}
	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}
	public String getFromUserName() {
		return fromUserName;
	}
	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}
	public long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getMsgId() {
		return msgId;
	}
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
	
	
}
