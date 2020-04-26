package www.mys.com.ourtalk.utils.chat;

import java.util.List;

public class BaseChatPOJO {

    public static final class Type {
        public static final int HANDSHAKE = 1;
        public static final int TALK_USER = 2;
        public static final int TALK_GROUP = 3;
        public static final int SEND_BACK = 4;
        public static final int RECEIVE_BACK = 5;
        public static final int ADD_FRIENDS = 6;
    }

    private int type;
    private String chatKey;
    private String fromKey;
    private String toKey;
    private List<String> atUsers;
    private String data;

    public BaseChatPOJO() {
    }

    public BaseChatPOJO(int type, String chatKey, String fromKey, String toKey, List<String> atUsers, String data) {
        this.type = type;
        this.chatKey = chatKey;
        this.fromKey = fromKey;
        this.toKey = toKey;
        this.atUsers = atUsers;
        this.data = data;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getChatKey() {
        return chatKey;
    }

    public void setChatKey(String chatKey) {
        this.chatKey = chatKey;
    }

    public String getFromKey() {
        return fromKey;
    }

    public void setFromKey(String fromKey) {
        this.fromKey = fromKey;
    }

    public String getToKey() {
        return toKey;
    }

    public void setToKey(String toKey) {
        this.toKey = toKey;
    }

    public List<String> getAtUsers() {
        return atUsers;
    }

    public void setAtUsers(List<String> atUsers) {
        this.atUsers = atUsers;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BaseChatPOJO{" +
                "type=" + type +
                ", chatKey='" + chatKey + '\'' +
                ", fromKey='" + fromKey + '\'' +
                ", toKey='" + toKey + '\'' +
                ", atUsers=" + atUsers +
                ", data='" + data + '\'' +
                '}';
    }
}
