package com.azaqaryan.buildsrc

object Versions {
	object Sdk {
		const val Min = 24
		const val Target = 33
		const val Compile = 33
	}

	object App {
		const val Code = 1
		const val Name = "1.0"
		const val Jvm = "11"
	}

	object Remote {
		const val BaseUrl = "https://newsapi.org"
	}
}

object Misc {
	const val applicationName = "newsapp"
	const val appNameSpace = "com.azaqaryan.newsapp"
	const val appId = "com.azaqaryan.newsapp.android"
	const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
	const val isMinifyEnabled = false
}

private object VersionsLocal {
	object Kotlin {
		const val GradlePlugin = "1.8.0"
		const val Coroutines = "1.6.1"
	}

	object Gradle {
		const val Plugin = "8.0.2"
	}

	object Google {
		const val Material = "1.8.0-alpha01"
		const val Core = "20.1.2"
	}

	object AndroidX {
		const val Fragment = "1.4.1"
		const val ConstraintLayout = "2.1.4"
		const val AppCompat = "1.6.1"
		const val Navigation = "2.5.0"
		const val Ktx = "1.9.0"
		const val Paging = "3.1.1"

		object Lifecycle {
			const val Runtime = "2.5.1"
			const val Extensions = "2.2.0"
		}
	}

	object Dagger {
		const val Core = "2.45"
	}

	object Retrofit {
		const val Core = "2.9.0"
		const val Logging = "4.9.0"
	}

	object Test {
		const val Junit = "4.13.2"
		const val JunitExt = "1.1.3"
		const val EspressoCore = "3.4.0"
	}

	const val Coil = "2.2.2"
}

object Dependencies {
	object Kotlin {
		const val GradlePlugin =
			"org.jetbrains.kotlin:kotlin-gradle-plugin:${VersionsLocal.Kotlin.GradlePlugin}"

		object Coroutines {
			const val Core =
				"org.jetbrains.kotlinx:kotlinx-coroutines-core:${VersionsLocal.Kotlin.Coroutines}"
			const val Android =
				"org.jetbrains.kotlinx:kotlinx-coroutines-android:${VersionsLocal.Kotlin.Coroutines}"
		}
	}

	object Gradle {
		const val Plugin = "com.android.tools.build:gradle:${VersionsLocal.Gradle.Plugin}"
	}

	object Google {
		const val Material = "com.google.android.material:material:${VersionsLocal.Google.Material}"
	}

	object Coil {
		const val Core = "io.coil-kt:coil:${VersionsLocal.Coil}"
	}

	object AndroidX {
		const val AppCompat = "androidx.appcompat:appcompat:${VersionsLocal.AndroidX.AppCompat}"
		const val CoreKtx = "androidx.core:core-ktx:${VersionsLocal.AndroidX.Ktx}"
		const val ConstraintLayout =
			"androidx.constraintlayout:constraintlayout:${VersionsLocal.AndroidX.ConstraintLayout}"
		const val Fragment = "androidx.fragment:fragment-ktx:${VersionsLocal.AndroidX.Fragment}"
		const val Paging =
			"androidx.paging:paging-runtime-ktx:${VersionsLocal.AndroidX.Paging}"

		object Lifecycle {
			const val Runtime =
				"androidx.lifecycle:lifecycle-runtime-ktx:${VersionsLocal.AndroidX.Lifecycle.Runtime}"
			const val ViewModel =
				"androidx.lifecycle:lifecycle-viewmodel-ktx:${VersionsLocal.AndroidX.Lifecycle.Runtime}"
			const val Extensions =
				"androidx.lifecycle:lifecycle-extensions:${VersionsLocal.AndroidX.Lifecycle.Extensions}"
		}

		object Navigation {
			const val Fragment =
				"androidx.navigation:navigation-fragment-ktx:${VersionsLocal.AndroidX.Navigation}"
			const val Ui =
				"androidx.navigation:navigation-ui-ktx:${VersionsLocal.AndroidX.Navigation}"
			const val Testing =
				"androidx.navigation:navigation-testing:${VersionsLocal.AndroidX.Navigation}"
			const val SafeArgsGradlePlugin =
				"androidx.navigation:navigation-safe-args-gradle-plugin:${VersionsLocal.AndroidX.Navigation}"
			const val SafeArgsKt = "androidx.navigation.safeargs.kotlin"
		}

		object Dagger {
			const val Support =
				"com.google.dagger:dagger-android-support:${VersionsLocal.Dagger.Core}"

			const val Compiler =
				"com.google.dagger:dagger-compiler:${VersionsLocal.Dagger.Core}"
		}
	}

	object Retrofit {
		const val Core = "com.squareup.retrofit2:retrofit:${VersionsLocal.Retrofit.Core}"
		const val ConverterGson =
			"com.squareup.retrofit2:converter-gson:${VersionsLocal.Retrofit.Core}"

		object Interceptor {
			const val Logging =
				"com.squareup.okhttp3:logging-interceptor:${VersionsLocal.Retrofit.Logging}"
		}
	}
}

object Test {
	const val Junit = "junit:junit:${VersionsLocal.Test.Junit}"
	const val JunitExt = "androidx.test.ext:junit:${VersionsLocal.Test.JunitExt}"
	const val EspressoCore =
		"androidx.test.espresso:espresso-core:${VersionsLocal.Test.EspressoCore}"
}
