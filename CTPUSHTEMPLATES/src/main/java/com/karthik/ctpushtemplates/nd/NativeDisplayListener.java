package com.karthik.ctpushtemplates.nd;

public interface NativeDisplayListener {
    void onSuccess(String id);

    void onFailure(String id);

    void onClick(int resId, String id, String deepLink);
}
