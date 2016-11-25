package hebiao.hantu;

import android.graphics.Bitmap;

/**
 * Created by apple on 2016/11/15.
 */

public class WechatMessageBean {

    /*
    消息类型，文本，音频，图片
     */
    public  enum  MessageType{
        Text,
        Voice,
        Image

    }
    /*
    聊天的人， 韩图客服 & 用户
     */

    public  enum  MessageSenderType{
        Hantu,
        User

    }

    /*
        消息发送状态 送达 发送中 发送失败
     */

    public  enum  MessageSentStatus{
        Sended,
        Sending,
        UnSended
    }

    /*
      韩图读取消息状态，已读 未读
     */

    public  enum  MessageReadStatus{
        Read,
        UnRead
    }


    private MessageType messageType;
    private MessageSenderType senderType;
    private MessageSentStatus sentStatus;
    private MessageReadStatus readStatus;

// 是否显示时间
    private boolean showMessageTime;

//    消息时间
    private String messageTime;

//    用户图像url
    private String logoUrl;

//    文本消息内容
    private String messageText;

// 音频持续时间
    private Integer duringTime;

//   音频Url
    private String voiceUrl;

//   图片文件
    private Bitmap imageBitmap;

//    图片文件url
    private String imageUrl;



//    保留字段1
    private String value1;

    //    保留字段2
    private String value2;

    //    保留字段3
    private String value3;

    //    保留字段4
    private String value4;

    //    保留字段5
    private String value5;


    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public MessageSenderType getSenderType() {
        return senderType;
    }

    public void setSenderType(MessageSenderType senderType) {
        this.senderType = senderType;
    }

    public MessageSentStatus getSentStatus() {
        return sentStatus;
    }

    public void setSentStatus(MessageSentStatus sentStatus) {
        this.sentStatus = sentStatus;
    }

    public MessageReadStatus getReadStatus() {
        return readStatus;
    }

    public void setReadStatus(MessageReadStatus readStatus) {
        this.readStatus = readStatus;
    }

    public boolean isShowMessageTime() {
        return showMessageTime;
    }

    public void setShowMessageTime(boolean showMessageTime) {
        this.showMessageTime = showMessageTime;
    }

    public String getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(String messageTime) {
        this.messageTime = messageTime;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public Integer getDuringTime() {
        return duringTime;
    }

    public void setDuringTime(Integer duringTime) {
        this.duringTime = duringTime;
    }

    public String getVoiceUrl() {
        return voiceUrl;
    }

    public void setVoiceUrl(String voiceUrl) {
        this.voiceUrl = voiceUrl;
    }

    public Bitmap getImageBitmap() {
        return imageBitmap;
    }

    public void setImageBitmap(Bitmap imageBitmap) {
        this.imageBitmap = imageBitmap;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getValue1() {
        return value1;
    }

    public void setValue1(String value1) {
        this.value1 = value1;
    }

    public String getValue2() {
        return value2;
    }

    public void setValue2(String value2) {
        this.value2 = value2;
    }

    public String getValue3() {
        return value3;
    }

    public void setValue3(String value3) {
        this.value3 = value3;
    }

    public String getValue4() {
        return value4;
    }

    public void setValue4(String value4) {
        this.value4 = value4;
    }

    public String getValue5() {
        return value5;
    }

    public void setValue5(String value5) {
        this.value5 = value5;
    }

    public WechatMessageBean() {
    }



}
