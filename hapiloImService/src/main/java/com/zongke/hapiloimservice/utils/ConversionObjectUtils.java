package com.zongke.hapiloimservice.utils;

import com.zhongke.local.message.model.ZkLocalMessage;
import com.zk.NetFace;
import com.zk.net.ZkMessagePacket;
import com.zk.net.message.ChatRequestMessage;
import com.zk.net.message.ExtCmdRequestMessage;
import com.zk.net.message.ExtCmdResponseMessage;

/**
 * Created by ${xingen} on 2017/11/24.
 */

public class ConversionObjectUtils {
    /**
     * 获取额外信息
     * @param zkLocalMessage
     * @param netFaceClient
     * @return
     */
    public static  ZkMessagePacket conversionExtraObject(ZkLocalMessage zkLocalMessage, NetFace netFaceClient){
        ZkMessagePacket zkMessagePacket = null;
        try {
             zkMessagePacket = netFaceClient.getSendExtMsg(zkLocalMessage.getChatContent(),zkLocalMessage.getToUserList(),zkLocalMessage.getGroupId());
              ExtCmdRequestMessage extCmdRequestMessage = ((ExtCmdRequestMessage) zkMessagePacket.getMessage());
              extCmdRequestMessage.setMsgId(zkLocalMessage.getMsgId());
              extCmdRequestMessage.setTime(zkLocalMessage.getTime());
        } catch (Exception x) {
            x.printStackTrace();
        }
        return zkMessagePacket;
    }

    /**
     * 获取聊天信息
     * @param zkLocalMessage
     * @param netFaceClient
     * @return
     */
    public static ZkMessagePacket conversionObject(ZkLocalMessage zkLocalMessage, NetFace netFaceClient) {
        ZkMessagePacket zkMessagePacket = null;
        try {
            zkMessagePacket = netFaceClient.getSendChatMsg(zkLocalMessage.getChatContent(), zkLocalMessage.getToUserList(), zkLocalMessage.getGroupId());
            ChatRequestMessage chatRequestMessage = ((ChatRequestMessage) zkMessagePacket.getMessage());
            chatRequestMessage.setMsgId(zkLocalMessage.getMsgId());
            chatRequestMessage.setTime(zkLocalMessage.getTime());
        } catch (Exception x) {
            x.printStackTrace();
        }
        return zkMessagePacket;
    }

    public static ZkLocalMessage conversionObject(com.zk.android.sqlite.ZkLocalMessage zkLocalMessage) {
        ZkLocalMessage localMessage = new ZkLocalMessage();
        localMessage.setTime(zkLocalMessage.getTime());
        localMessage.setChatContent(zkLocalMessage.getChatContent());
        localMessage.setDeviceType(zkLocalMessage.getDeviceType());
        localMessage.setGroupId(zkLocalMessage.getGroupId());
        localMessage.setMessageType(zkLocalMessage.getMessageType());
        localMessage.setMsgId(zkLocalMessage.getMsgId());
        localMessage.setSendUserId(zkLocalMessage.getSendUserId());
        localMessage.setToUserList(zkLocalMessage.getToUserList());
        localMessage.setRetCode(zkLocalMessage.getRetCode());
        return localMessage;
    }
    public static ZkLocalMessage conversionExtraObject(ExtCmdResponseMessage zkLocalMessage) {
        ZkLocalMessage localMessage = new ZkLocalMessage();
        localMessage.setTime(zkLocalMessage.getTime());
        localMessage.setChatContent(zkLocalMessage.getChatContent());
        localMessage.setDeviceType(zkLocalMessage.getDeviceType());
        localMessage.setGroupId(zkLocalMessage.getGroupId());
        localMessage.setMsgId(zkLocalMessage.getMsgId());
        localMessage.setSendUserId(zkLocalMessage.getSendUserId());
        localMessage.setMessageType(ZkLocalMessage.COMMAND_EXT_REQ);
        return localMessage;
    }
}
