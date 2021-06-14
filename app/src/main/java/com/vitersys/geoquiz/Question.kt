package com.vitersys.geoquiz

import androidx.annotation.StringRes

data class Question (@StringRes val questionTextResId : Int, val userAnswer: Boolean) {
}