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

package io.github.casl0.stocknews.messages.controller

import io.github.casl0.stocknews.messages.model.MessageEntity
import io.github.casl0.stocknews.messages.model.MessageResponse
import io.github.casl0.stocknews.messages.service.MessageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ProblemDetail
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * プッシュ通知のトリガーとなるエンドポイント
 */
@RestController
@RequestMapping("api/v1")
class MessageController {
    @Autowired
    lateinit var messageService: MessageService

    /**
     * POST /api/v1/messages: トピック条件を指定し、条件に合致するトピックを購読しているデバイスにプッシュ通知を送信します
     *
     * @param topicCondition プッシュ通知先のトピック条件（例：'Technology' in topics || 'Automotive' in topics）
     * @param message プッシュ通知のメッセージ内容
     * @return 送信成功したメッセージ内容
     */
    @PostMapping("messages")
    fun pushMessageToTopicCondition(
        @RequestHeader(
            value = "X-Topic-Condition",
            required = true
        ) topicCondition: String,
        @RequestBody message: MessageEntity,
    ): ResponseEntity<MessageResponse> {
        val sentMessage = messageService.sendMessageToTopicCondition(
            topicCondition,
            message
        )
            .getOrElse {
                return ResponseEntity.of(
                    ProblemDetail.forStatusAndDetail(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        it.localizedMessage
                    )
                )
                    .build()
            }
        return ResponseEntity.ok(MessageResponse(sentMessage))
    }

    /**
     * POST /api/v1/topics/{topic}/messages: トピックを指定し、同トピックを購読しているデバイスにプッシュ通知を送信します
     *
     * @param topicName プッシュ通知先のトピック
     * @param message プッシュ通知のメッセージ内容
     * @return 送信成功したメッセージ内容
     */
    @PostMapping("topics/{topic}/messages")
    fun pushMessageToTopic(
        @PathVariable(value = "topic") topicName: String,
        @RequestBody message: MessageEntity,
    ): ResponseEntity<MessageResponse> {
        val sentMessage = messageService.sendMessageToTopic(
            topicName,
            message
        )
            .getOrElse {
                return ResponseEntity.of(
                    ProblemDetail.forStatusAndDetail(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        it.localizedMessage
                    )
                )
                    .build()
            }
        return ResponseEntity.ok(MessageResponse(sentMessage))
    }

    /**
     * POST /api/v1/tokens/{token}/messages: 登録トークンを指定し、プッシュ通知を送信します
     *
     * @param registrationToken プッシュ通知先の登録トークン
     * @param message プッシュ通知のメッセージ内容
     * @return 送信成功したメッセージ内容
     */
    @PostMapping("tokens/{token}/messages")
    fun pushMessageToRegistrationToken(
        @PathVariable(value = "token") registrationToken: String,
        @RequestBody message: MessageEntity
    ): ResponseEntity<MessageResponse> {
        val sentMessage = messageService.sendMessageToRegistrationToken(
            registrationToken,
            message
        )
            .getOrElse {
                return ResponseEntity.of(
                    ProblemDetail.forStatusAndDetail(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        it.localizedMessage
                    )
                )
                    .build()
            }
        return ResponseEntity.ok(MessageResponse(sentMessage))
    }
}
