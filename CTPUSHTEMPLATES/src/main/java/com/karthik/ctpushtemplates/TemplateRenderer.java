package com.karthik.ctpushtemplates;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.ViewGroup;
import androidx.annotation.IdRes;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.karthik.ctpushtemplates.nd.CustomButton;
import com.karthik.ctpushtemplates.nd.NativeDisplayListener;
import com.karthik.ctpushtemplates.nd.VideoFragment;
import com.karthik.ctpushtemplates.nd.story.StoryAdapter;
import com.karthik.ctpushtemplates.pn.PushNotificationListener;
import com.karthik.ctpushtemplates.pn.PushTemplateRenderer;

import org.json.JSONException;
import org.json.JSONObject;

public class TemplateRenderer {

    private static TemplateRenderer instance;

    public static TemplateRenderer getInstance() {
        if (instance == null) {
            return new TemplateRenderer();
        } else {
            return instance;
        }
    }

    public void showNativeDisplay(@IdRes int buttonPushProfile, FragmentManager supportFragmentManager,
                                  JSONObject jsonObject, NativeDisplayListener listener) {
        FragmentTransaction transaction = supportFragmentManager.beginTransaction();
        transaction.add(buttonPushProfile, VideoFragment.newInstance(jsonObject, listener));
        transaction.commit();
    }

    public void animateButton(Context applicationContext, ViewGroup viewGroup, JSONObject jsonObject,
                              NativeDisplayListener listener) {
        new CustomButton(applicationContext, jsonObject, viewGroup, listener);
    }

    public void showPushNotification(Context applicationContext, Bundle extras, PushNotificationListener listener) {
        PushTemplateRenderer.getInstance().render(applicationContext, extras, listener);
    }

    public StoryAdapter displayStories(Activity context, JSONObject jsonObject,
                                       Boolean shouldAppend) {
        try {
            return new StoryAdapter(context, jsonObject.getJSONArray("content"), (NativeDisplayListener) context, shouldAppend);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
