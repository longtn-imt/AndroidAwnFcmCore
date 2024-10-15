package me.carda.awesome_notifications_fcm.core.services;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.carda.awesome_notifications.core.utils.JsonUtils;

public class FcmSenderServiceTest {
    @Test
    public void simpleRealPush() {
        String tokens = "cSor7sDnS4Gs-txpgSNSP-:APA91bH0aZ-CJD4qq2w32mQWBZBDCp9OOd2w_GDvI56kpXeehsDfR98XE8lg1Qqdpsi0g0Zn5SeUb4xhtdSmcgCdYuHdqBYdO2uCgjw6xAWYSJVi0dHvXHyypcvRCN5pt4NU0t1BW7yX";
        Map<String, Object> data = JsonUtils.fromJson(
        "{\n" +
                "    \"message\": {\n" +
                "        \"token\": \""+tokens+"\",\n" +
                "        \"android\": {\n" +
                "            \"priority\": \"high\"\n" +
                "        },\n" +
                "        \"apns\": {\n" +
                "            \"payload\": {\n" +
                "                \"aps\": {\n" +
                "                    \"mutable-content\": 1,\n" +
                "                    \"badge\": 42\n" +
                "                },\n" +
                "                \"headers\": {\n" +
                "                    \"apns-priority\": \"5\"\n" +
                "                }\n" +
                "            }\n" +
                "        },\n" +
                "        \"notification\": {\n" +
                "            \"title\": \"Huston! The eagle has landed!\",\n" +
                "            \"body\": \"A small step for a man, but a giant leap to Flutter's community!\"\n" +
                "        },\n" +
                "        \"data\": {\n" +
                "            \"content.id\": \"1\",\n" +
                "            \"content.badge\": \"42\",\n" +
                "            \"content.channelKey\": \"alerts\",\n" +
                "            \"content.displayOnForeground\": \"true\",\n" +
                "            \"content.notificationLayout\": \"BigPicture\",\n" +
                "            \"content.largeIcon\": \"https://br.web.img3.acsta.net/pictures/19/06/18/17/09/0834720.jpg\",\n" +
                "            \"content.bigPicture\": \"https://www.dw.com/image/49519617_303.jpg\",\n" +
                "            \"content.showWhen\": \"true\",\n" +
                "            \"content.autoDismissible\": \"true\",\n" +
                "            \"content.privacy\": \"Private\",\n" +
                "            \"content.payload.secret\": \"Awesome Notifications Rocks!\",\n" +
                "            \"actionButtons.0.key\": \"REDIRECT\",\n" +
                "            \"actionButtons.0.label\": \"Redirect\",\n" +
                "            \"actionButtons.0.autoDismissible\": \"true\",\n" +
                "            \"actionButtons.1.key\": \"DISMISS\",\n" +
                "            \"actionButtons.1.label\": \"Dismiss\",\n" +
                "            \"actionButtons.1.actionType\": \"DismissAction\",\n" +
                "            \"actionButtons.1.isDangerousOption\": \"true\",\n" +
                "            \"actionButtons.1.autoDismissible\": \"true\"\n" +
                "        }\n" +
                "    }\n" +
                "}");

        try {
            FcmSenderService
                    .getInstance()
                    .sendPushNotification(
                        "teste-firebase-67c93",
                        "firebase-adminsdk-pkmbs@teste-firebase-67c93.iam.gserviceaccount.com",
                        "-----BEGIN PRIVATE KEY-----\nMIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDNXmC+aNxGsKxq\nA82nJH4oQ04smrywAuFDusCkZVPU+Qh5q4Lk45v5SOqfg3oe8JXsvMlALZrZzUAk\n1+RhhW4S+unh1MnEjk7AgbAQih3C5fooEx4vVSdMgl4A3S+d5gy7lzwAuV4WjvWB\ni+8Iz1xnbvjGuOUF9MJB/mdHsIgx2v5trJJCYeyeLU52DL9veG97vXQDtCogvkKQ\n1Z3/IVisgZBHJsypuY11VhltUyVPtUFPBdbSfYoXicrxlwQHk1fO0q9uPnmm+DsX\nQIAp7IOeYVDy6gML7kVURfKK7kc1fKCY1Io2Kl0+aAivuzMpYQwYzVyJJUPsjWVW\nYSWDC+7xAgMBAAECggEADexqZDZxSsSjJ9vip0SY3gt0Ix+Jj+heE+y3L14kUSHi\nMnePnAuXcU8BgzeTgcH/dCm3fkepQj+BbA4MEJWOiTBkR3iZBl+8lJ4mTPTEQEi9\nzVZAe2aLT8td6rKUTiEA933xJj5rE9305KfTWLZQVj3mlXiHJIB75CQGpgxgq3qF\nnOVFmLQTxJ73S6Yovr43lgePXim1AVuJYWAYdG3IlshjcXfdFBFWNLsrtDHxT6v3\nYDJwbYSyX47LJAYYgWq7wfYTgjK3nnS+vEHgFYOL3HqNI2Ufp8r/Aiq3xPzCGU4b\nvHrP2Vs3xMipLYTHc6i4Ar1+So60tLNekg8u8kDKDQKBgQDuIpFq5MRg9JjVL/fJ\nPDRVEMW9j7KGyzvybpTojHVZ226Yp+ItC/0UFHxlIhoUqiKEn3GjCegGe86IyXLt\nIFpyOh2FSIbul2U4pnpUK3DDoh0OZyMjgveZmzbXXqnbVDOoZB3zxnFZR2MO8i5V\ni5DPT08gqXx9+flhYVIjusRmJQKBgQDcxoZre6bhgzHP4Kq14NIIzVZwQA/NPt0d\nn97NEaI20KwNkUdUKIIM+Itv+DgoP4Uqdna9Okug5CFQFNrfdkSKGudZdnAJ3M72\nOdTUaRflJV2tENMgwrMviahAc+Vok/BPtFB5XBYdap1T1zhukPDhmTm6LaRZuOaX\ndP5bE1pt3QKBgDeOxRKFUPO13TpPuKWwcB0wjpHNRr+ABvpmpkMjEfXm5gSjlncg\nBul6ZhNKQ39yuh7q/B4nn4Rzuhnbtf5wv+lKebyw0Ro1JB51aLDeea6K0fCcS2xr\nj8y8k6znnwVWvb6ZrQtEzwkfSJMiGYFH0H8tZ66dHDhf67i2de10E4IpAoGAX4v/\nosa5+4E27ptx5hLtc2fCkAP5kr8xHOASobeO96ApxhlowFBjsE9ksUnArBKJhE4O\np2hyUKhKjojoDKv3j7mDBGHQrUQFweRXarIs6DvapcrRRK1etSUjTvmZgD/+UdBc\nv5kN7R82BZHwV1GdHlWc+SlFppryGPxGFI50pXUCgYEA0+7k5js/zweD+gDneOak\nN63iAeIzrfxv1dg1RFfSt/Yw9I+ABbk2ShNrMaPVmDFJAWK6kkdV1guDdLxbJOI2\nI4xzojg/PumUquhAr4h1I2LgumlKBngkM6jraFov3G6Jv51QQ9AF2vsZfUIVQQgq\nFE784bLGiNpEpOfhSrX3rv0=\n-----END PRIVATE KEY-----\n",
                         data
                    );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
