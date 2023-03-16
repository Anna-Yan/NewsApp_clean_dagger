package com.azaqaryan.newsapp.data

object Constants {

	object ResponseCodes {
		const val USER_EMAIL_EXISTS_CODE = 409
		const val AUTHENTICATION_ERROR_CODE = 401
		const val AUTHENTICATION_ERROR_MINIMUM_VALUE = 400
		const val USER_BLOCKED_CODE = 502
	}

	object Ranges {
		val INFO_RESPONSE_RANGE = 100..199
		val SUCCESS_RESPONSE_RANGE = 200..299
		val REDIRECT_RESPONSE_RANGE = 300..399
		val CLIENT_ERROR_RESPONSE_RANGE = 400..499
		val SERVER_ERROR_RESPONSE_RANGE = 500..599
	}
}