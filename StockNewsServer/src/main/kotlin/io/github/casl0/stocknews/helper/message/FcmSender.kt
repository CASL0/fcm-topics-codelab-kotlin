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

package io.github.casl0.stocknews.helper.message

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingException
import com.google.firebase.messaging.Message
import com.google.firebase.messaging.Notification
import io.github.casl0.stocknews.entity.MessageEntity
import org.springframework.stereotype.Component
import java.io.IOException

/**
 * FCM メッセージ送信機能を提供します
 */
@Component
class FcmSender {
    companion object {
        /**
         * FCM credentials
         */
        private val _serviceAccount = FcmSender::class.java.classLoader.getResourceAsStream("service-account.json")

        /**
         * firebase-admin SDK を初期化します
         *
         * @throws [IOException]
         */
        @Throws(IOException::class)
        private fun initFirebaseSDK() {
            val options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(_serviceAccount))
                .build()
            FirebaseApp.initializeApp(options)
        }

        init {
            initFirebaseSDK()
        }
    }

    /**
     * FCM トピック条件にメッセージを送信します
     *
     * @param messageEntity メッセージのエンティティ
     */
    fun sendMessageToFcmTopicCondition(messageEntity: MessageEntity) {
        val topicCondition = "" // TODO: Provide the Topic Condition you want to send to. Eg., 'Technology' in topics || 'Automotive' in topics
        TODO("Implement FCM Topic Condition send call")
    }

    /**
     * FCM トピックにメッセージを送信します
     *
     * @param messageEntity メッセージのエンティティ
     */
    fun sendMessageToFcmTopic(messageEntity: MessageEntity) {
        val topicName = "" // TODO: Provide the Topic you want to send to -> /topics/<Topic Name>. Eg., /topics/Technology
        TODO("Implement FCM Topic send call")
    }

    /**
     * FCM 登録トークンにメッセージを送信します
     *
     * @param messageEntity メッセージのエンティティ
     * @throws [com.google.firebase.messaging.FirebaseMessagingException]
     */
    @Throws(FirebaseMessagingException::class)
    fun sendMessageToFcmRegistrationToken(
        registrationToken: String,
        messageEntity: MessageEntity
    ) {
        val message = Message.builder()
            .setNotification(
                Notification.builder()
                    .setTitle(messageEntity.title)
                    .setBody(messageEntity.body)
                    .build()
            )
            .setToken(registrationToken)
            .build()
        FirebaseMessaging.getInstance()
            .send(message)
    }
}
