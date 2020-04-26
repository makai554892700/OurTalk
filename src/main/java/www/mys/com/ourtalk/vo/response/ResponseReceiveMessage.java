package www.mys.com.ourtalk.vo.response;

import java.util.List;

public class ResponseReceiveMessage {

    private int contentType;
    private int sessionAccountType;
    private String strTargetId;
    private User fromUser;
    private User toUser;
    private int sessionPriority;
    private int userRelationship;
    private long timestampMs;
    private KwaiRemindBody kwaiRemindBody;
    private Content content;

    public int getContentType() {
        return contentType;
    }

    public void setContentType(int contentType) {
        this.contentType = contentType;
    }

    public int getSessionAccountType() {
        return sessionAccountType;
    }

    public void setSessionAccountType(int sessionAccountType) {
        this.sessionAccountType = sessionAccountType;
    }

    public String getStrTargetId() {
        return strTargetId;
    }

    public void setStrTargetId(String strTargetId) {
        this.strTargetId = strTargetId;
    }

    public User getFromUser() {
        return fromUser;
    }

    public void setFromUser(User fromUser) {
        this.fromUser = fromUser;
    }

    public User getToUser() {
        return toUser;
    }

    public void setToUser(User toUser) {
        this.toUser = toUser;
    }

    public int getSessionPriority() {
        return sessionPriority;
    }

    public void setSessionPriority(int sessionPriority) {
        this.sessionPriority = sessionPriority;
    }

    public int getUserRelationship() {
        return userRelationship;
    }

    public void setUserRelationship(int userRelationship) {
        this.userRelationship = userRelationship;
    }

    public long getTimestampMs() {
        return timestampMs;
    }

    public void setTimestampMs(long timestampMs) {
        this.timestampMs = timestampMs;
    }

    public KwaiRemindBody getKwaiRemindBody() {
        return kwaiRemindBody;
    }

    public void setKwaiRemindBody(KwaiRemindBody kwaiRemindBody) {
        this.kwaiRemindBody = kwaiRemindBody;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ResponseReceiveMessage{" +
                "contentType=" + contentType +
                ", sessionAccountType=" + sessionAccountType +
                ", strTargetId='" + strTargetId + '\'' +
                ", fromUser=" + fromUser +
                ", toUser=" + toUser +
                ", sessionPriority=" + sessionPriority +
                ", userRelationship=" + userRelationship +
                ", timestampMs=" + timestampMs +
                ", kwaiRemindBody=" + kwaiRemindBody +
                ", content=" + content +
                '}';
    }

    public static class KwaiRemindBody {
        private String originText;
        private List<String> remindBodyArray;

        public String getOriginText() {
            return originText;
        }

        public void setOriginText(String originText) {
            this.originText = originText;
        }

        public List<String> getRemindBodyArray() {
            return remindBodyArray;
        }

        public void setRemindBodyArray(List<String> remindBodyArray) {
            this.remindBodyArray = remindBodyArray;
        }

        @Override
        public String toString() {
            return "KwaiRemindBody{" +
                    "originText='" + originText + '\'' +
                    ", remindBodyArray=" + remindBodyArray +
                    '}';
        }
    }

    public static class Content {
        private String text;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return "Content{" +
                    "text='" + text + '\'' +
                    '}';
        }
    }

    public static class User {
        private int appid;
        private int uid;

        public int getAppid() {
            return appid;
        }

        public void setAppid(int appid) {
            this.appid = appid;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        @Override
        public String toString() {
            return "User{" +
                    "appid=" + appid +
                    ", uid=" + uid +
                    '}';
        }
    }

}
