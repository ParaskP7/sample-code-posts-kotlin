package io.petros.posts.kotlin.activity

import android.content.Intent
import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

abstract class BaseActivity : AppCompatActivity() {

    // LIFECYCLE // ************************************************************************************************************************

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        setToolbar()
        Timber.d("%s created. [Bundle: %s]", javaClass.simpleName, savedInstanceState)
    }

    protected abstract fun getLayoutId(): Int

    private fun setToolbar() {
        setSupportActionBar(toolbar)
    }

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

    @CallSuper
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

    override fun onDestroy() {
        Timber.d("%s destroyed.", javaClass.simpleName)
        super.onDestroy()
    }

}
