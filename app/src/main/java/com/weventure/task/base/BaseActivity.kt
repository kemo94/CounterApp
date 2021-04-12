package com.weventure.task.base


import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

/**
 * A simple [Fragment] subclass.
 */
abstract class BaseActivity<P : BaseViewModel, V : ViewDataBinding> : AppCompatActivity() {


    lateinit var mViewModel: P


    lateinit var mViewDataBinding: V

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        initialize()
    }

    abstract fun initialize()

    @LayoutRes
    abstract fun getLayoutId(): Int


    fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }


}