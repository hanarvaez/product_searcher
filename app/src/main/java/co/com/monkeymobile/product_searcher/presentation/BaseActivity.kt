package co.com.monkeymobile.product_searcher.presentation

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import co.com.monkeymobile.product_searcher.util.LOG_MESSAGE_STATE
import co.com.monkeymobile.product_searcher.util.TAG_VIEW_UPDATE

abstract class BaseActivity<ViewModel : BaseViewModel<State, Event>, State : ViewState, Event : ViewEvent> :
    AppCompatActivity() {

    abstract val viewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.state.observe(this) { state ->
            try {
                Log.i(TAG_VIEW_UPDATE, "$LOG_MESSAGE_STATE${state.getName()}")
                buildState(state)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        viewModel.toastMessage.observe(this) { message ->
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        }
    }

    protected abstract fun buildState(state: State)

    protected fun dispatchEvent(event: Event) {
        viewModel.dispatchEvent(event)
    }
}