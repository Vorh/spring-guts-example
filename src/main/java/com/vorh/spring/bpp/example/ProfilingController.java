package com.vorh.spring.bpp.example;

/**
 * Created by vorh on 6/30/18.
 */
public class ProfilingController implements ProfilingControllerMBean {
    private boolean enabled  =true;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
