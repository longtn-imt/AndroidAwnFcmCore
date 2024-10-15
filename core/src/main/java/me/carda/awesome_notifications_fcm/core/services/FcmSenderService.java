package me.carda.awesome_notifications_fcm.core.services;

//import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
//import com.google.api.client.http.HttpRequestInitializer;
//import com.google.api.client.http.HttpResponse;
//import com.google.api.client.http.HttpTransport;
//import com.google.api.client.http.javanet.NetHttpTransport;
//import com.google.api.client.json.JsonFactory;
//import com.google.api.client.json.gson.GsonFactory;
//import com.google.api.client.http.HttpRequestFactory;
//import com.google.api.client.http.GenericUrl;
//import com.google.api.client.http.ByteArrayContent;
//import com.google.api.client.util.Base64;


import com.google.firebase.messaging.FirebaseMessaging;

import java.security.KeyFactory;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import kotlin.NotImplementedError;
import me.carda.awesome_notifications.core.models.NotificationModel;
import me.carda.awesome_notifications.core.utils.JsonUtils;
import me.carda.awesome_notifications.core.utils.StringUtils;

public class FcmSenderService {

    public static String TAG = "AwesomeFcmEventsReceiver";

    // ************** SINGLETON PATTERN ***********************

    private static FcmSenderService instance;

    private FcmSenderService(){}
    public static FcmSenderService getInstance() {
        if (instance == null)
            instance = new FcmSenderService();
        return instance;
    }

    // ********************************************************

    /**
     * @deprecated This method is deprecated as FCM upstream messaging
     * will be decommissioned in June 2024.
     *
     * Use a REST API endpoint on your server to handle device-to-server
     * communication, and send push notifications using the
     * Firebase Admin SDK from the server side.
     *
     * This method will be removed in next releases.
     */
    public boolean sendPushNotification(
            String projectSenderId,
            String clientEmail,
            String privateKeyPem,
            Map<String, Object> data
    ) throws Exception {
        throw new NotImplementedError();
//        String BASE_URL = "https://fcm.googleapis.com/v1/projects/" + projectSenderId + "/messages:send";
//        String FCM_JSON = JsonUtils.toJson(data);
//
//        // Convert PEM format private key to PKCS8 format
//        String realPrivateKey = privateKeyPem
//                .replaceAll("-----END PRIVATE KEY-----", "")
//                .replaceAll("-----BEGIN PRIVATE KEY-----", "")
//                .replaceAll("\\s", "");

//        byte[] encoded = Base64.decodeBase64(realPrivateKey);
//        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encoded);
//        KeyFactory kf = KeyFactory.getInstance("RSA");

//        // Create Google credentials
//        GoogleCredential googleCredential = new GoogleCredential.Builder()
//                .setTransport(new NetHttpTransport())
//                .setJsonFactory(new GsonFactory())
//                .setServiceAccountId(clientEmail)
//                .setServiceAccountPrivateKey(kf.generatePrivate(keySpec))
//                .setServiceAccountScopes(Arrays.asList("https://www.googleapis.com/auth/firebase.messaging"))
//                .build();
//
//        // Obtain access token
//        googleCredential.refreshToken();
//        String accessToken = googleCredential.getAccessToken();
//
//        // Create and customize HTTP request
//        HttpRequestFactory requestFactory = googleCredential.getTransport().createRequestFactory();
//        GenericUrl url = new GenericUrl(BASE_URL);
//        ByteArrayContent content = ByteArrayContent.fromString("application/json", FCM_JSON);
//
//        com.google.api.client.http.HttpRequest request = requestFactory.buildPostRequest(url, content);
//        request.getHeaders().setAuthorization("Bearer " + accessToken);
//
//        // Send the request
//        HttpResponse response = request.execute();
//        return response.getStatusCode() == 200;
//        return true;
    }

    public Map<String, Object> buildPushContent(
        NotificationModel notificationModel,
        int priorityLevel
    ) {
        if (notificationModel.content == null) return null;

        String title = notificationModel.content.title;
        String body = notificationModel.content.body;
        String image = notificationModel.content.bigPicture;
        String sound = notificationModel.content.customSound;
        boolean isSoundEnabled = notificationModel.content.playSound;

        boolean isSilentPush =
                StringUtils.getInstance().isNullOrEmpty(title) &&
                StringUtils.getInstance().isNullOrEmpty(body);

        Map<String,Object> data = new HashMap<String,Object>(){{
            put("android", new HashMap<String, Object>(){{
                put("priority", priorityLevel > 3 ? "high" : "normal");
            }});
            put("webpush", new HashMap<String, Object>(){{
                switch (priorityLevel){
                    case 1: put("Urgency", "very-low"); break;
                    case 2: put("Urgency", "low"); break;
                    case 3: put("Urgency", "normal"); break;
                    case 4:
                    case 5: put("Urgency", "high"); break;
                }
            }});
            put("apns", new HashMap<String, Object>(){{
                put("payload", new HashMap<String, Object>(){{
                    put("aps", new HashMap<String, Object>(){{
                        if (isSilentPush) {
                            put("content-available", 1);
                        } else {
                            put("mutable-content", 1);
                        }
                        if (notificationModel.content.badge != null) {
                            put("badge", notificationModel.content.badge);
                        }
                    }});
                    put("headers", new HashMap<String, Object>(){{
                        if (!isSilentPush) {
                            put("apns-priority", priorityLevel);
                        }
                    }});
                }});
            }});
        }};

        if (isSilentPush) {
            data.put("data", notificationModel.content.payload);
            return data;
        }

        data.put("notification", new HashMap<String, String>() {{
            if (title != null) put("title", title);
            if (body != null) put("body", body);
            if (image != null) put("image", image);
            if (isSoundEnabled && sound != null) put("sound", sound);
        }});

        return data;
    }
}
