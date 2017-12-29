package com.zongke.hapiloimservice.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ActionFlowNodeData implements Serializable {
	
	public static String behavior = "behavior";// "关键行为",
	public static String sendMessage = "sendMessage";// "发送通知消息",
	public static String video = "video";// : "视频",
	public static String audio = "audio";// : "音频",
	public static String sceneLight = "sceneLight";// "情景灯",
	public static String camera = "camera";// "摄像头",
	public static String microphone = "microphone";// "麦克风",
	public static String lamp = "lamp";// "台灯",
	public static String infrared = "infrared";// "红外检测",
	public static String temperature = "temperature";// "温度",
	public static String humidity = "humidity";// "湿度",
	public static String lightIntensity = "lightIntensity";// "光照强度",
	public static String tools = "tools";// "活动道具",
	public static String invitation = "invitation";// "活动邀请",
	public static String actionChat = "actionChat";// "活动聊天",
	public static String answer = "answer";// "活动抢答",
	public static String uploadResult = "uploadResult";// "上传活动成果",
	public static String evaluate = "evaluate";// "活动评价",
	public static String award = "award";// "活动奖励",

	private Integer id = 0;

	private Integer flowId;

	private String fNodeType = "";

	private String fNodeName = "";

	private Integer actionId = 0;

	private String fNodeId = "";

	private Integer runTime = 0;

	private Integer doObject = 0;

	private String msgContent = "";

	private Integer toolsId = 0;

	private String toolsName;

	private Integer behaviorId;

	private String behaviorName;

	private Integer awardId;

	private String awardName;

	private Integer resId;

	private String resName;

	private String resUrl;

	private Integer judgeType;



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFlowId() {
		return flowId;
	}

	public void setFlowId(Integer flowId) {
		this.flowId = flowId;
	}

	public String getfNodeType() {
		return fNodeType;
	}

	public void setfNodeType(String fNodeType) {
		this.fNodeType = fNodeType;
	}

	public String getfNodeName() {
		return fNodeName;
	}

	public void setfNodeName(String fNodeName) {
		this.fNodeName = fNodeName;
	}

	public Integer getActionId() {
		return actionId;
	}

	public void setActionId(Integer actionId) {
		this.actionId = actionId;
	}

	public String getfNodeId() {
		return fNodeId;
	}

	public void setfNodeId(String fNodeId) {
		this.fNodeId = fNodeId;
	}

	public Integer getRunTime() {
		return runTime;
	}

	public void setRunTime(Integer runTime) {
		this.runTime = runTime;
	}

	public Integer getDoObject() {
		return doObject;
	}

	public void setDoObject(Integer doObject) {
		this.doObject = doObject;
	}

	public String getMsgContent() {
		return msgContent;
	}

	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}

	public Integer getToolsId() {
		return toolsId;
	}

	public void setToolsId(Integer toolsId) {
		this.toolsId = toolsId;
	}

	public String getToolsName() {
		return toolsName;
	}

	public void setToolsName(String toolsName) {
		this.toolsName = toolsName;
	}

	public Integer getBehaviorId() {
		return behaviorId;
	}

	public void setBehaviorId(Integer behaviorId) {
		this.behaviorId = behaviorId;
	}

	public String getBehaviorName() {
		return behaviorName;
	}

	public void setBehaviorName(String behaviorName) {
		this.behaviorName = behaviorName;
	}

	public Integer getAwardId() {
		return awardId;
	}

	public void setAwardId(Integer awardId) {
		this.awardId = awardId;
	}

	public String getAwardName() {
		return awardName;
	}

	public void setAwardName(String awardName) {
		this.awardName = awardName;
	}

	public Integer getResId() {
		return resId;
	}

	public void setResId(Integer resId) {
		this.resId = resId;
	}

	public String getResName() {
		return resName;
	}

	public void setResName(String resName) {
		this.resName = resName;
	}

	public String getResUrl() {
		return resUrl;
	}

	public void setResUrl(String resUrl) {
		this.resUrl = resUrl;
	}

	public Integer getJudgeType() {
		return judgeType;
	}

	public void setJudgeType(Integer judgeType) {
		this.judgeType = judgeType;
	}



}
