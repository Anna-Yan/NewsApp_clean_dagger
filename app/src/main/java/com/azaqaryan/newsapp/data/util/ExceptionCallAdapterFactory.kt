package com.azaqaryan.newsapp.data.util

import com.azaqaryan.newsapp.common.ActionResult
import okhttp3.Request
import okio.Timeout
import retrofit2.*
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

internal class NetworkResponseAdapterFactory : CallAdapter.Factory() {
	override fun get(
		returnType: Type,
		annotations: Array<out Annotation>,
		retrofit: Retrofit
	): CallAdapter<*, *>? {
		if (getRawType(returnType) != Call::class.java) return null
		check(returnType is ParameterizedType) {
			"return type must be parameterized as Call<ActionResult<<Foo>> or Call<ActionResult<out Foo>>"
		}
		val responseType = getParameterUpperBound(0, returnType)
		if (getRawType(responseType) != ActionResult::class.java) return null

		check(responseType is ParameterizedType) {
			"Response must be parameterized as ActionResult<Foo> or ActionResult<out Foo>"
		}

		val bodyType = getParameterUpperBound(0, responseType)

		return object : CallAdapter<Any, Call<ActionResult<Any>>> {
			override fun responseType(): Type = bodyType

			override fun adapt(call: Call<Any>): Call<ActionResult<Any>> = NetworkResponseCall(call)
		}
	}
}

private class NetworkResponseCall<T>(
	private val delegate: Call<T>
) : Call<ActionResult<T>> {

	override fun enqueue(callback: Callback<ActionResult<T>>) {
		delegate.enqueue(object : Callback<T> {
			override fun onResponse(call: Call<T>, response: Response<T>) {
				callback.onResponse(
					this@NetworkResponseCall,
					Response.success(mapResponse(response))
				)
			}

			override fun onFailure(call: Call<T>, t: Throwable) =
				callback.onResponse(this@NetworkResponseCall, Response.success(mapException(t)))
		})
	}

	override fun clone(): Call<ActionResult<T>> = NetworkResponseCall(delegate.clone())

	override fun execute(): Response<ActionResult<T>> =
		throw UnsupportedOperationException("You're gonna run a synchronize network request? why?!")

	override fun isExecuted(): Boolean = delegate.isExecuted

	override fun cancel() = delegate.cancel()

	override fun isCanceled(): Boolean = delegate.isCanceled

	override fun request(): Request = delegate.request()

	override fun timeout(): Timeout = delegate.timeout()

}