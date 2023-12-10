# fcm-topics-codelab-kotlin

[FCM トピックを使用した最初のマルチキャスト プッシュ メッセージ](https://firebase.google.com/codelabs/firebase-fcm-topics?hl=ja) の Kotlin 版です。

使用する技術スタックを次のように変更しております。

- アプリ側
  - Kotlin + Jetpack Compose で作成しています。
- サーバー側
  - Kotlin + SpringBoot で作成しています。

次の内容を学べます。

- Android アプリから受信したいトピックを購読/購読解除する方法。
- 登録トークンを指定し、特定のデバイスにプッシュ通知を送信する方法。
- トピックを指定し、同トピックを購読しているデバイスにプッシュ通知を送信する方法。
- トピック条件を指定し、条件を満たすデバイスにプッシュ通知を送信する方法。

## 作るもの

- アプリ側
  - FCM で送信されたメッセージを受信する Android アプリ。
  - 受信したいトピックを購読/購読解除することができます。
    ![アプリスクリーンショット](https://github.com/CASL0/fcm-topics-codelab-kotlin/assets/28913760/64af8c3a-e8fb-4d85-b713-0d4fd0e78136)
- サーバー側
  - プッシュ通知のトリガーとなるエンドポイントを持つ API サーバー。
  - デバイス指定、トピック指定、トピック条件指定でプッシュ通知を送信できます。
  - エンドポイント
    - /api/v1/tokens/{token}/messages
      - 登録トークンを指定し、プッシュ通知を送信します。
    - /api/v1/topics/{topic}/messages
      - トピックを指定し、同トピックを購読しているデバイスにプッシュ通知を送信します。
    - /api/v1/messages
      - トピック条件を指定し、条件を満たすデバイスにプッシュ通知を送信します。

## Getting Started

- 次のツールをインストールしてください。
  - [IntelliJ IDEA](https://www.jetbrains.com/idea/)
  - [Android Studio](https://developer.android.com/studio?hl=ja)
- Firebase プロジェクトを作成および管理するための Google アカウントを作成してください。
- starter コードをセットアップしてください。

```sh
git clone https://github.com/CASL0/fcm-topics-codelab-kotlin.git
cd fcm-topics-codelab-kotlin
git checkout starter
```

- IDE でプロジェクトを開いてください。
  - [StockNewsApp](./StockNewsApp/)を Android Studio で開いてください。
  - [StockNewsServer](./StockNewsServer/)を IntelliJ IDEA で開いてください。

次の本でコードラボを開始してください。

- https://zenn.dev/casl0/books/fcm-topics-codelab-kotlin

## ライセンス

```
MIT License

Copyright (c) 2023 CASL0

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
