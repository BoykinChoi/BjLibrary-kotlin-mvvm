package com.boykinchoi.star.design_pattern.bridge.phone;

import com.boykinchoi.star.design_pattern.bridge.software.Software;

/**
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in 2021/10/26 17:50
 */
public abstract class Phone {
    private String sys;
    private Software software;

    public Phone(Software software) {
        this.software = software;
    }

    public String getSys() {
        return sys;
    }

    public void setSys(String sys) {
        this.sys = sys;
    }

    public void setSoftware(Software software) {
        this.software = software;
    }

    public Software getSoftware() {
        return software;
    }

    public abstract void openSoftware();
}
