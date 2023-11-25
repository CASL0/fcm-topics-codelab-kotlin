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

package io.github.casl0.stocknews.messages.service

import com.google.firebase.messaging.FirebaseMessagingException
import io.github.casl0.stocknews.helper.message.FcmSender
import io.github.casl0.stocknews.messages.model.MessageEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MessageService {
    @Autowired
    lateinit var messageSender: FcmSender

    /**
     * トピック条件に合致するトピックを購読しているデバイスにメッセージを送信します
     *
     * @param topicCondition 送信先のトピック条件（例：'Technology' in topics || 'Automotive' in topics）
     * @param messageEntity メッセージのエンティティ
     * @return 送信に成功した場合 Result.success、それ以外はResult.failure
     */
    fun sendMessageToTopicCondition(
        topicCondition: String,
        messageEntity: MessageEntity
    ): Result<String> {
        return try {
            val res = messageSender.sendMessageToFcmTopicCondition(
                topicCondition,
                messageEntity
            )
            Result.success(res)
        } catch (e: FirebaseMessagingException) {
            Result.failure(e)
        }
    }

    /**
     * トピックを購読しているデバイスにメッセージを送信します
     *
     * @param topicName 送信先のトピック名
     * @param messageEntity メッセージのエンティティ
     * @return 送信に成功した場合 Result.success、それ以外はResult.failure
     */
    fun sendMessageToTopic(
        topicName: String,
        messageEntity: MessageEntity
    ): Result<String> {
        return try {
            val res = messageSender.sendMessageToFcmTopic(
                topicName,
                messageEntity
            )
            Result.success(res)
        } catch (e: FirebaseMessagingException) {
            Result.failure(e)
        }
    }

    /**
     * 登録トークンを指定し通知を送信します
     *
     * @param registrationToken 登録トークン
     * @param message 送信するメッセージ
     * @return 送信に成功した場合 Result.success、それ以外はResult.failure
     */
    fun sendMessageToRegistrationToken(
        registrationToken: String,
        message: MessageEntity
    ): Result<String> {
        return try {
            val res = messageSender.sendMessageToFcmRegistrationToken(
                registrationToken,
                message
            )
            Result.success(res)
        } catch (e: FirebaseMessagingException) {
            Result.failure(e)
        }
    }
}
