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

import androidx.lifecycle.ViewModel
import io.github.casl0.stocknews.model.STOCK_CATEGORIES
import io.github.casl0.stocknews.model.StockCategory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

/**
 * UI状態のdata class
 *
 * @property stockCategories [StockCategory]のリスト
 */
data class UiState(
        val stockCategories: List<StockCategory> = STOCK_CATEGORIES,
)

/** ビジネスロジックを扱うViewModel */
class MainViewModel : ViewModel() {
    /** UI状態 */
    val uiState: StateFlow<UiState> get() = _uiState
    private val _uiState = MutableStateFlow(UiState())

    /**
     * 購読の切り替えをします
     *
     * @param topicName 切り替え対象のカテゴリのトピック
     * @param isSubscribed 切り替え後の購読状態
     */
    fun toggleSubscribed(
            topicName: CharSequence,
            isSubscribed: Boolean,
    ) {
        _uiState.update { uiState ->
            val stockCategories = uiState.stockCategories.map {
                if (it.topicName == topicName) {
                    it.copy(isSubscribed = isSubscribed)
                } else {
                    it
                }
            }
            uiState.copy(stockCategories = stockCategories)
        }
        if (isSubscribed) {
            subscribeToStockCategory(topicName)
        } else {
            unsubscribeFromStockCategory(topicName)
        }
    }

    /**
     * トピックを購読します
     *
     * @param topicName 購読対象のトピック
     */
    private fun subscribeToStockCategory(topicName: CharSequence) {
        TODO("Topic subscribing call")
    }

    /**
     * トピックを購読解除します
     *
     * @param topicName 購読解除対象のトピック
     */
    private fun unsubscribeFromStockCategory(topicName: CharSequence) {
        TODO("Topic unsubscribing call")
    }
}
