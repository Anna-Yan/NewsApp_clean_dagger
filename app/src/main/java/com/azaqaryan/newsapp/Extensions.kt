package com.azaqaryan.newsapp

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

fun View.showSnackBar(
	@StringRes messageResId: Int,
	duration: Int = BaseTransientBottomBar.LENGTH_INDEFINITE,
	@StringRes actionMessage: Int = 0,
	action: () -> Unit = {},
) {
	Snackbar.make(
		this, messageResId, duration
	).setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE)
		.setAction(
			if (actionMessage == 0) R.string.action_hide_snackbar else actionMessage
		) { action() }
		.show()
}

fun Context.makeToast(@StringRes message: Int) =
	Toast.makeText(this, message, Toast.LENGTH_SHORT).show()



