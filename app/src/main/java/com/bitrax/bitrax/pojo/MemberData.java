package com.bitrax.bitrax.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MemberData {

    @SerializedName("member")
    @Expose
    private String member;
    @SerializedName("user_code")
    @Expose
    private String userCode;
    @SerializedName("transaction_password")
    @Expose
    private String transactionPassword;

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getTransactionPassword() {
        return transactionPassword;
    }

    public void setTransactionPassword(String transactionPassword) {
        this.transactionPassword = transactionPassword;
    }

}
