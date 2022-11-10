package com.example.notificationlistenerexample

import android.app.Notification
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.text.TextUtils
import com.orhanobut.logger.Logger

class NotificationListenerTestService : NotificationListenerService() {
    override fun onNotificationPosted(sbn: StatusBarNotification) {
        val packageName = sbn.packageName

        //로그 찍고 싶은 package 셋팅
        if (!TextUtils.isEmpty(packageName) && packageName == "com.kakao.talk") {
            val extras = sbn.notification.extras

            val title = extras.getString(Notification.EXTRA_TITLE)
            val text = extras.getCharSequence(Notification.EXTRA_TEXT)
            val subText = extras.getCharSequence(Notification.EXTRA_SUB_TEXT)

            Logger.d("title: $title, text: $text, sub: $subText")

            val sb = StringBuffer()
            extras.keySet().forEach { key ->
                sb.append("key: $key, value: ${extras.get(key)}\n")
            }

            Logger.d(sb)
        }
    }
}