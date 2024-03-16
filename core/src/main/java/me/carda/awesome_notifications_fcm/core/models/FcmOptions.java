package me.carda.awesome_notifications_fcm.core.models;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

import me.carda.awesome_notifications.core.exceptions.AwesomeNotificationsException;
import me.carda.awesome_notifications.core.models.AbstractModel;
import me.carda.awesome_notifications_fcm.core.FcmDefinitions;

public class FcmOptions extends AbstractModel {
    public Map<String, Object> apns;
    public Map<String, Object> android;
    public Map<String, Object> webPush;
    public int priority;

    @Override
    public FcmOptions fromMap(Map<String, Object> arguments) {

        apns = getValueOrDefaultMap(arguments,
                FcmDefinitions.NOTIFICATION_OPTION_APNS, null);
        android = getValueOrDefaultMap(arguments,
                FcmDefinitions.NOTIFICATION_OPTION_ANDROID, null);
        webPush = getValueOrDefaultMap(arguments,
                FcmDefinitions.NOTIFICATION_OPTION_WEB, null);
        priority = getValueOrDefault(arguments,
                FcmDefinitions.NOTIFICATION_OPTION_PRIORITY, int.class, 3);

        return this;
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> mapData = new HashMap<>();

        putDataOnSerializedMap(FcmDefinitions.NOTIFICATION_OPTION_APNS, mapData, this.apns);
        putDataOnSerializedMap(FcmDefinitions.NOTIFICATION_OPTION_ANDROID, mapData, this.android);
        putDataOnSerializedMap(FcmDefinitions.NOTIFICATION_OPTION_WEB, mapData, this.webPush);
        putDataOnSerializedMap(FcmDefinitions.NOTIFICATION_OPTION_PRIORITY, mapData, this.priority);

        return mapData;
    }

    @Override
    public String toJson() {
        return templateToJson();
    }

    @Override
    public SilentDataModel fromJson(String json){
        return (SilentDataModel) super.templateFromJson(json);
    }

    @Override
    public void validate(Context context) throws AwesomeNotificationsException {

    }
}
