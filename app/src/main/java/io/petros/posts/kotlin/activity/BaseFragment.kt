package io.petros.posts.kotlin.activity

import android.annotation.SuppressLint
import android.content.Context
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.salomonbrys.kodein.KodeinInjector
import com.github.salomonbrys.kodein.android.appKodein
import timber.log.Timber

abstract class BaseFragment<BINDING : ViewDataBinding, VIEW_MODEL : BaseViewModel> : Fragment() {

    protected val injector = KodeinInjector()

    protected lateinit var binding: BINDING
    protected lateinit var viewModel: VIEW_MODEL

    // LIFECYCLE // ************************************************************************************************************************

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Timber.d("%s attached.", javaClass.simpleName)
    }

    @SuppressLint("MissingSuperCall")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setKodein()
        Timber.d("%s created. [Bundle: %s]", javaClass.simpleName, savedInstanceState)
    }

    private fun setKodein() {
        injector.inject(appKodein())
        Timber.d("%s kodein set.", javaClass.simpleName)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        setBinding(inflater, container)
        setViewModel()
        Timber.d("%s create view. [Bundle: %s]", javaClass.simpleName, savedInstanceState)
        return binding.root
    }

    private fun setBinding(inflater: LayoutInflater, container: ViewGroup?) {
        binding = constructBinding(inflater, container)
        Timber.d("%s binding constructed.", javaClass.simpleName)
    }

    protected abstract fun constructBinding(inflater: LayoutInflater, container: ViewGroup?): BINDING

    private fun setViewModel() {
        viewModel = constructViewModel()
        viewModel.whenReady(appKodein(), this)
        Timber.d("%s view model constructed.", javaClass.simpleName)
    }

    protected abstract fun constructViewModel(): VIEW_MODEL

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) { // After that the activity is created.
        super.onViewCreated(view, savedInstanceState)
        Timber.d("%s view created. [Bundle: %s]", javaClass.simpleName, savedInstanceState)
    }

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
