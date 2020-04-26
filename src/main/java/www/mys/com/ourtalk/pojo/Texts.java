package www.mys.com.ourtalk.pojo;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(indexes = {@Index(columnList = "textType,groupMD5,intDesc,dateDesc", name = "tgid")})
public class Texts {

    public static class Type {
        public static final int NICK_NAME = 1;       //昵称
        public static final int HEAD_IMG = 2;       //头像
        public static final int DIY_SIGN = 3;       //个性签名
        public static final int COMMENT = 4;       //评论
        public static final int SAY_HELLO = 5;       //招呼
        public static final int AUTO_RETURN = 6;       //自动回复
        public static final int LATTER = 7;       //私信
        public static final int USER = 8;       //用户
        public static final int ZONE = 9;       //地区
        public static final int CONTACT = 10;       //通讯录
    }

    public static String getTypeName(int textType) {
        return getTypeName(textType, String.valueOf(textType));
    }

    public static String getTypeName(int textType, String defaultName) {
        switch (textType) {
            case Texts.Type.NICK_NAME:
                return "昵称";
            case Texts.Type.HEAD_IMG:
                return "头像";
            case Texts.Type.DIY_SIGN:
                return "个性签名";
            case Texts.Type.COMMENT:
                return "评论";
            case Texts.Type.SAY_HELLO:
                return "招呼";
            case Texts.Type.AUTO_RETURN:
                return "自动回复";
            case Texts.Type.LATTER:
                return "私信";
            case Texts.Type.USER:
                return "用户";
            case Type.ZONE:
                return "地区";
            case Type.CONTACT:
                return "通讯录";
            default:
                return defaultName;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private Integer textType;                   //字符串类型
    @Column(nullable = false)
    @org.hibernate.annotations.Type(type = "text")
    private String textValue;                   //字符串内容
    @Column(nullable = false)
    private String groupName;                   //组名
    @Column(nullable = false, length = 32)
    private String groupMD5;                    //组名md5
    private String textDesc;                    //描述
    private Integer intDesc;                    //数字标记
    private Long dateDesc;                      //日期标记
    private String typeName;                    //类型名
    @Column(columnDefinition = "TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private Date createdAt;
    @Column(columnDefinition = "TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private Date updatedAt;

    public Texts() {
    }

    public Texts(Integer textType, String textValue, String groupName, String groupMD5, String textDesc
            , String typeName) {
        this.textType = textType;
        this.textValue = textValue;
        this.groupName = groupName;
        this.groupMD5 = groupMD5;
        this.textDesc = textDesc;
        this.typeName = typeName;
    }

    public Texts(Integer id, Integer textType, String textValue, String groupName, String groupMD5
            , String textDesc, String typeName, Integer intDesc, Long dateDesc
            , Date createdAt, Date updatedAt) {
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

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
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
        return "Texts{" +
                "id=" + id +
                ", textType=" + textType +
                ", textValue='" + textValue + '\'' +
                ", groupName='" + groupName + '\'' +
                ", groupMD5='" + groupMD5 + '\'' +
                ", textDesc='" + textDesc + '\'' +
                ", intDesc=" + intDesc +
                ", dateDesc=" + dateDesc +
                ", typeName='" + typeName + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
