package com.boykinchoi.star.reflect;

/**
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in 2021/12/25 10:26
 */
public class Bank {
    private String MSG = "USA Big Bank";
    private final String FINAL_MSG = "aaaaaaaaa";
    private final String FINAL_MSG2;

    public Bank() {
        this.FINAL_MSG2 = "bbbbbbbb";
    }

    private void privateMethod(String head, int tail) {
        System.out.print(head + tail);
    }

    public String getMsg() {
        return MSG;
    }

    public String getFinalMsg() {
        return FINAL_MSG;
    }

    public String getFinalMsg2() {
        return FINAL_MSG2;
    }

}
