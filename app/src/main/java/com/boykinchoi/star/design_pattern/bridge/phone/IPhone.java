package com.boykinchoi.star.design_pattern.bridge.phone;

import com.boykinchoi.star.design_pattern.bridge.software.Software;

/**
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in 2021/10/26 17:53
 */
public class IPhone extends Phone {

    public IPhone(Software software) {
        super(software);
        this.setSys("IOS");
    }

    @Override
    public void openSoftware() {
       this.getSoftware().open(this);
    }
}
