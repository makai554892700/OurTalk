package www.mys.com.ourtalk.vo.response;

import java.util.Date;

public class ResponseMessageHistory {

    private Integer id;
    private String userKey;
    private String messageValue;
    private Date createdAt;
    private Date updatedAt;

    public ResponseMessageHistory() {
    }

    public ResponseMessageHistory(Integer id, String userKey, String messageValue, Date createdAt, Date updatedAt) {
        this.id = id;
        this.userKey = userKey;
        this.messageValue = messageValue;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public String getMessageValue() {
        return messageValue;
    }

    public void setMessageValue(String messageValue) {
        this.messageValue = messageValue;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "ResponseMessageHistory{" +
                "id=" + id +
                ", userKey='" + userKey + '\'' +
                ", messageValue='" + messageValue + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
