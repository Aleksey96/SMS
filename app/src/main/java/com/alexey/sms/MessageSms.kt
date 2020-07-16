package com.alexey.sms

class MessageSms(
    numberPhone: String,
    bodyMessage: String,
    status: String,
    timeSend: String
) {
    private var numberPhone: String = ""
    private var bodyMessage: String = ""
    private var status: String = ""
    private var timeSend: String = ""

    init {
        this.numberPhone = numberPhone
        this.bodyMessage = bodyMessage
        this.status = status
        this.timeSend = timeSend
    }

}