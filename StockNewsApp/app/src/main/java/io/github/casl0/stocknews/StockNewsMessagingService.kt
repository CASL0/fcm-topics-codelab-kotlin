/*
 * MIT License
 *
 * Copyright (c) 2023 CASL0
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package io.github.casl0.stocknews

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

/** FCMのメッセージハンドリングするサービス */
class StockNewsMessagingService : FirebaseMessagingService() {
    /**
     * メッセージが受信され、アプリがフォアグラウンドにあるときに呼び出されます
     *
     * [詳細](https://firebase.google.com/docs/cloud-messaging/android/receive#handling_messages)
     */
    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        Log.i(
                TAG,
                "Notification received with data-payload ${message.data}, title ${message.notification?.title}, body ${message.notification?.body}"
        )
    }

    /**
     * FCM 登録トークンが作成または更新されるときに呼び出されます
     *
     * [詳細](https://firebase.google.com/docs/cloud-messaging/android/client#monitor-token-generation)
     */
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.i(
                TAG,
                "FCM Registration Token created or refreshed: $token"
        )
    }
}

private const val TAG = "StockNewsMessagingService"
