package pojo;

import java.util.List;

public class MailVO {
	private static final long serialVersionUID = 4280650483975256784L;
    // 邮件发送者的标示
    private String key;
    // 登陆邮件发送服务器的用户名和密码
    private String mailAccount;
    private String mailPassword;
    //收件人名字
    private String senderAlias;
    // 邮件接收者的地址数组
    private List<String> receiveAddressArray;

    // 邮件主题
    private String subject;
    // 邮件的文本内容
    private String content;
    // 邮件附件的文件名
    private String[] attachFileNames;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getMailAccount() {
		return mailAccount;
	}
	public void setMailAccount(String mailAccount) {
		this.mailAccount = mailAccount;
	}
	public String getMailPassword() {
		return mailPassword;
	}
	public void setMailPassword(String mailPassword) {
		this.mailPassword = mailPassword;
	}
	public String getSenderAlias() {
		return senderAlias;
	}
	public void setSenderAlias(String senderAlias) {
		this.senderAlias = senderAlias;
	}
	public List<String> getReceiveAddressArray() {
		return receiveAddressArray;
	}
	public void setReceiveAddressArray(List<String> receiveAddressArray) {
		this.receiveAddressArray = receiveAddressArray;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String[] getAttachFileNames() {
		return attachFileNames;
	}
	public void setAttachFileNames(String[] attachFileNames) {
		this.attachFileNames = attachFileNames;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
    


}
