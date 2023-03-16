package com.azaqaryan.newsapp.data

import com.azaqaryan.newsapp.CommonStates
import com.azaqaryan.newsapp.R
import com.azaqaryan.newsapp.data.entity.error.NewsAppException
import retrofit2.HttpException
import retrofit2.Response
import java.net.SocketTimeoutException
import java.net.UnknownHostException

fun <T> mapResponse(response: Response<T>): ActionResult<T> {
	return when (response.code()) {
		in Constants.Ranges.SERVER_ERROR_RESPONSE_RANGE -> {
			ActionResult.Error(NewsAppException(
				messageResId = R.string.toast_server_error,
				state = CommonStates.NOT_FOUND
			))
		}
		in Constants.Ranges.CLIENT_ERROR_RESPONSE_RANGE -> {
			ActionResult.Error(NewsAppException(messageResId = R.string.toast_client_error))
		}
		in Constants.Ranges.INFO_RESPONSE_RANGE -> {
			ActionResult.Error(NewsAppException(messageResId = R.string.toast_informational_error))
		}
		in Constants.Ranges.REDIRECT_RESPONSE_RANGE -> {
			ActionResult.Error(NewsAppException(messageResId = R.string.toast_redirection_error))
		}
		in Constants.Ranges.SUCCESS_RESPONSE_RANGE -> {
			ActionResult.Success(response)
		}
		else -> ActionResult.Error(
			NewsAppException(
				messageResId = R.string.toast_unknown_error,
				state = CommonStates.NOT_FOUND
			)
		)
	}
}

fun mapException(t: Throwable): ActionResult<Nothing> = when (t) {
	is SocketTimeoutException -> ActionResult.Failure(t, CommonStates.NO_CONNECTION)
	is java.io.IOException -> ActionResult.Failure(t, CommonStates.NO_CONNECTION)
	is HttpException -> ActionResult.Failure(t, CommonStates.NO_CONNECTION)
	is UnknownHostException -> ActionResult.Failure(t, CommonStates.NO_CONNECTION)
	else -> ActionResult.Failure(t, CommonStates.NOT_FOUND)
}

