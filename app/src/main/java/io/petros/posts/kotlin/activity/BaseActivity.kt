package io.petros.posts.kotlin.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import butterknife.ButterKnife
import com.github.salomonbrys.kodein.KodeinInjector
import com.github.salomonbrys.kodein.android.appKodein
import timber.log.Timber

abstract class BaseActivity<BINDING : ViewDataBinding, VIEW_MODEL : BaseViewModel> : AppCompatActivity() {

    protected val injector = KodeinInjector()

    protected lateinit var binding: BINDING
    protected lateinit var viewModel: VIEW_MODEL

    // LIFECYCLE // ************************************************************************************************************************

    @SuppressLint("MissingSuperCall")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBinding()
        setViewModel()
        setKodein()
        setButterKnife()
        Timber.d("%s created. [Bundle: %s]", javaClass.simpleName, savedInstanceState)
    }

    private fun setKodein() {
        injector.inject(appKodein())
        Timber.d("%s kodein set.", javaClass.simpleName)
    }

    private fun setButterKnife() {
        ButterKnife.bind(this)
    }

    private fun setBinding() {
        binding = constructBinding()
        Timber.d("%s binding constructed.", javaClass.simpleName)
    }

    protected abstract fun constructBinding(): BINDING

    private fun setViewModel() {
        viewModel = constructViewModel()
        viewModel.whenReady(appKodein(), this)
        Timber.d("%s view model constructed.", javaClass.simpleName)
    }

    protected abstract fun constructViewModel(): VIEW_MODEL

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        Timber.d("%s new intent. [Intent: %s]", javaClass.simpleName, intent)
    }

    public override fun onRestart() {
        super.onRestart()
        Timber.d("%s restarted.", javaClass.simpleName)
    }

    override fun onStart() {
        super.onStart()
        Timber.d("%s started.", javaClass.simpleName)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Timber.d("%s instance state restored. [Bundle: %s]", javaClass.simpleName, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        Timber.d("%s resumed.", javaClass.simpleName)
    }

    override fun onPause() {
        Timber.d("%s paused.", javaClass.simpleName)
        super.onPause()
    }

    public override fun onSaveInstanceState(outState: Bundle) {
        Timber.d("%s instance state saved. [Bundle: %s]", javaClass.simpleName, outState)
        super.onSaveInstanceState(outState)
    }

    override fun onStop() {
        Timber.d("%s stopped.", javaClass.simpleName)
        super.onStop()
    }

    @SuppressLint("MissingSuperCall")
    override fun onDestroy() {
        Timber.d("%s destroyed.", javaClass.simpleName)
        super.onDestroy()
    }

    // TOOLBAR // **************************************************************************************************************************

    protected fun setToolbar(toolbar: Toolbar) {
        setSupportActionBar(toolbar)
        Timber.d("%s toolbar set.", javaClass.simpleName)
    }

    protected fun setToolbarWithActionBar(toolbar: Toolbar) {
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(true)
            actionBar.setDisplayHomeAsUpEnabled(true)
            Timber.d("%s toolbar with action bar set.", javaClass.simpleName)
        } else {
            Timber.w("Cannot get an action bar for this activity; verify that this activity has actually defined a toolbar.")
        }
    }

    // FRAGMENT // *************************************************************************************************************************

    protected fun addFragment(fragmentId: Int) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(fragmentId, Fragment())
        fragmentTransaction.commit()
        Timber.d("%s fragment added.", javaClass.simpleName)
    }

    protected fun removeFragment(fragmentId: Int) {
        val fragment = supportFragmentManager.findFragmentById(fragmentId)
        if (fragment != null) {
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.remove(fragment)
            fragmentTransaction.commit()
            Timber.d("%s fragment removed.", javaClass.simpleName)
        } else {
            Timber.d("%s cannot removed fragment. [It Doesn't Exist]", javaClass.simpleName)
        }
    }

}
