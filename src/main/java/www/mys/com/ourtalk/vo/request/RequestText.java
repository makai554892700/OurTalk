package www.mys.com.ourtalk.vo.request;

public class RequestText {

    private String groupName;                   //组名
    private String textValue;                   //字符串内容
    private String textDesc;                    //描述
    private String typeName;                    //类型名
    private Integer textType;                   //字符串类型
    private Integer intDesc;                    //数字标记
    private Long dateDesc;                      //日期标记

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getTextValue() {
        return textValue;
    }

    public void setTextValue(String textValue) {
        this.textValue = textValue;
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

    public Integer getTextType() {
        return textType;
    }

    public void setTextType(Integer textType) {
        this.textType = textType;
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

    @Override
    public String toString() {
        return "RequestText{" +
                "groupName='" + groupName + '\'' +
                ", textValue='" + textValue + '\'' +
                ", textDesc='" + textDesc + '\'' +
                ", typeName='" + typeName + '\'' +
                ", textType=" + textType +
                ", intDesc=" + intDesc +
                ", dateDesc=" + dateDesc +
                '}';
    }
}
