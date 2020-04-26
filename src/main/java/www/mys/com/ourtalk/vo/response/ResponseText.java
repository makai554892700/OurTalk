package www.mys.com.ourtalk.vo.response;

import java.util.Date;

public class ResponseText {

    private Integer id;
    private Integer textType;                   //字符串类型
    private String textValue;                   //字符串内容
    private String groupName;                   //组名
    private String groupMD5;                    //组名md5
    private String textDesc;                    //描述
    private String typeName;                    //类型名
    private Long groupCount;                    //资源组条数
    private Integer intDesc;                    //数字标记
    private Long dateDesc;                      //日期标记
    private Date createdAt;
    private Date updatedAt;

    public ResponseText() {
    }

    public ResponseText(Integer id, Integer textType, String textValue, String groupName, String groupMD5
            , String textDesc, String typeName, Long groupCount, Integer intDesc, Long dateDesc
            , Date createdAt, Date updatedAt) {
        this.id = id;
        this.textType = textType;
        this.textValue = textValue;
        this.groupName = groupName;
        this.groupMD5 = groupMD5;
        this.textDesc = textDesc;
        this.typeName = typeName;
        this.groupCount = groupCount;
        this.intDesc = intDesc;
        this.dateDesc = dateDesc;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public ResponseText(Integer id, Integer textType, String textValue, String groupName
            , String groupMD5, String textDesc, String typeName
            , Integer intDesc, Long dateDesc, Date createdAt, Date updatedAt) {
        this.id = id;
        this.textType = textType;
        this.textValue = textValue;
        this.groupName = groupName;
        this.groupMD5 = groupMD5;
        this.textDesc = textDesc;
        this.typeName = typeName;
        this.intDesc = intDesc;
        this.dateDesc = dateDesc;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTextType() {
        return textType;
    }

    public void setTextType(Integer textType) {
        this.textType = textType;
    }

    public String getTextValue() {
        return textValue;
    }

    public void setTextValue(String textValue) {
        this.textValue = textValue;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupMD5() {
        return groupMD5;
    }

    public void setGroupMD5(String groupMD5) {
        this.groupMD5 = groupMD5;
    }

    public String getTextDesc() {
        return textDesc;
    }

    public void setTextDesc(String textDesc) {
        this.textDesc = textDesc;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Long getGroupCount() {
        return groupCount;
    }

    public void setGroupCount(Long groupCount) {
        this.groupCount = groupCount;
    }

    public Integer getIntDesc() {
        return intDesc;
    }

    public void setIntDesc(Integer intDesc) {
        this.intDesc = intDesc;
    }

    public Long getDateDesc() {
        return dateDesc;
    }

    public void setDateDesc(Long dateDesc) {
        this.dateDesc = dateDesc;
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
        return "ResponseText{" +
                "id=" + id +
                ", textType=" + textType +
                ", textValue='" + textValue + '\'' +
                ", groupName='" + groupName + '\'' +
                ", groupMD5='" + groupMD5 + '\'' +
                ", textDesc='" + textDesc + '\'' +
                ", typeName='" + typeName + '\'' +
                ", groupCount=" + groupCount +
                ", intDesc=" + intDesc +
                ", dateDesc=" + dateDesc +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
