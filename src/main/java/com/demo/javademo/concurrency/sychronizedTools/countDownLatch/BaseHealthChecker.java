package com.demo.javademo.concurrency.sychronizedTools.countDownLatch;

public abstract class BaseHealthChecker implements Runnable {
    private String serviceName;
    private boolean serviceUp;

    public BaseHealthChecker(String serviceName) {
        this.serviceName = serviceName;
    }

    @Override
    public void run() {
        try {
            verifyService();
            serviceUp = true;
        } catch (Exception e) {
            serviceUp = false;
            e.printStackTrace();
        } finally {

        }
    }

    public abstract void verifyService() throws Exception;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public boolean isServiceUp() {
        return serviceUp;
    }

    public void setServiceUp(boolean serviceUp) {
        this.serviceUp = serviceUp;
    }
}
