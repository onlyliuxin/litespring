package org.litespring.util;

import java.util.ArrayList;
import java.util.List;

public class MessageTracker {
	
	private static List<String> MESSAGES = new ArrayList<String>();
	
	public static void addMsg(String msg){
		MESSAGES.add(msg);
	}
	public static void clearMsgs(){
		MESSAGES.clear();
	}
	public static List<String> getMsgs(){
		return MESSAGES;
	}
}
