package io.petros.posts.kotlin.activity

import android.annotation.SuppressLint
import android.content.Context
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import com.github.salomonbrys.kodein.android.KodeinFragment
import timber.log.Timber

abstract class BaseFragment<BINDING : ViewDataBinding> : KodeinFragment() {

    protected lateinit var binding: BINDING

    // LIFECYCLE // ************************************************************************************************************************

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Timber.d("%s attached.", javaClass.simpleName)
    }

    @SuppressLint("MissingSuperCall")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("%s created. [Bundle: %s]", javaClass.simpleName, savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(getLayoutId(), container, false)
        setBinding()
        setButterKnife(view)
        Timber.d("%s create view. [Bundle: %s]", javaClass.simpleName, savedInstanceState)
        return view
    }

    protected abstract fun getLayoutId(): Int

    private fun setBinding() {
        binding = constructBinding()
    }

    protected abstract fun constructBinding(): BINDING

    private fun setButterKnife(view: View) {
        ButterKnife.bind(this, view)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) { // After that the activity is created.
        super.onViewCreated(view, savedInstanceState)
        bindViewModel()
        Timber.d("%s view created. [Bundle: %s]", javaClass.simpleName, savedInstanceState)
    }

    protected abstract fun bindViewModel()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Timber.d("%s activity created. [Bundle: %s]", javaClass.simpleName, savedInstanceState)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Timber.d("%s view state restored. [Bundle: %s]", javaClass.simpleName, savedInstanceState)
    }

    override fun onStart() { // After that the activity is started.
        super.onStart()
        Timber.d("%s started.", javaClass.simpleName)
    }

    override fun onResume() { // Prior that the activity is resumed.
        super.onResume()
        Timber.d("%s resumed.", javaClass.simpleName)
    }

    override fun onPause() { // Prior that the activity is paused.
        Timber.d("%s paused.", javaClass.simpleName)
        super.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle?) { // Prior that the activity instance state is saved.
        Timber.d("%s instance state saved. [Bundle: %s]", javaClass.simpleName, outState)
        super.onSaveInstanceState(outState)
    }

    override fun onStop() { // Prior that the activity is stopped.
        Timber.d("%s stopped.", javaClass.simpleName)
        super.onStop()
    }

    override fun onDestroyView() {
        Timber.d("%s view destroyed.", javaClass.simpleName)
        super.onDestroyView()
    }

    @SuppressLint("MissingSuperCall")
    override fun onDestroy() {
        Timber.d("%s destroyed.", javaClass.simpleName)
        super.onDestroy()
    }

    override fun onDetach() {
        Timber.d("%s detached.", javaClass.simpleName)
        super.onDetach()
    }

}
