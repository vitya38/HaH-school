package model

import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import com.example.lesson_5_lukin.FifthActivity

class FifthActivityContract : ActivityResultContract<Unit, String>() {
    override fun createIntent(context: Context, input: Unit?): Intent {
        return FifthActivity.createStartIntent(context)
    }

    override fun parseResult(resultCode: Int, intent: Intent?): String {
        return intent?.getStringExtra(FifthActivity.TEXT).orEmpty()
    }
}