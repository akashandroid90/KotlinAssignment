package app.kotlinassignment.constants

import androidx.annotation.StringDef

@Retention(AnnotationRetention.SOURCE)
@StringDef(
    ClickActionEvents.HOME
)
annotation class ClickActionEvents {
    companion object {
        const val HOME = "home"
    }
}