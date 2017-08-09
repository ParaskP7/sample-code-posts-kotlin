package io.petros.posts.kotlin

import android.app.Application
import android.os.StrictMode
import android.support.v7.app.AppCompatDelegate
import com.github.salomonbrys.kodein.*
import com.squareup.leakcanary.LeakCanary
import io.petros.posts.kotlin.activity.main.fragment.adapter.PostsAdapter
import io.petros.posts.kotlin.app.*
import io.petros.posts.kotlin.datastore.Datastore
import io.petros.posts.kotlin.datastore.db.CommentDao
import io.petros.posts.kotlin.datastore.db.PostDao
import io.petros.posts.kotlin.datastore.db.PostsDatabase
import io.petros.posts.kotlin.datastore.db.UserDao
import io.petros.posts.kotlin.repository.PostsRepository
import io.petros.posts.kotlin.service.retrofit.WebService
import io.petros.posts.kotlin.util.rx.RxSchedulers
import net.danlew.android.joda.JodaTimeAndroid
import timber.log.Timber

class App : Application(), KodeinAware {

    companion object {
        init {
            AppCompatDelegate.setCompatVectorFromResourcesEnabled(true) // NOTE: This enables the "proxy" trick on the vector images.
        }
    }

    // DEPENDENCY INJECTION // *************************************************************************************************************

    override val kodein by Kodein.lazy {
        bind<PostsAdapter>() with singleton { constructPostsAdapter(kodein) }
        bind<RxSchedulers>() with singleton { constructRxSchedulers() }
        bind<WebService>() with singleton { constructWebService(applicationContext) }
        val postsDatabase = constructPostsDatabase(applicationContext)
        bind<PostsDatabase>() with singleton { postsDatabase }
        bind<UserDao>() with singleton { constructUserDao(postsDatabase) }
        bind<PostDao>() with singleton { constructPostDao(postsDatabase) }
        bind<CommentDao>() with singleton { constructCommentDao(postsDatabase) }
        bind<Datastore>() with singleton { Datastore(kodein) }
        bind<PostsRepository>() with singleton { PostsRepository(kodein) }
    }

    // LIFECYCLE // ************************************************************************************************************************

    override fun onCreate() {
        super.onCreate()
        if (!LeakCanary.isInAnalyzerProcess(this)) initLeakCanary() else return
        initStrictMode()
        initJodaTime()
        initTimber()
        Timber.i("%s application created!", getString(R.string.app_name))
    }

    private fun initLeakCanary() {
        LeakCanary.install(this)
    }

    private fun initJodaTime() {
        JodaTimeAndroid.init(this)
    }

    private fun initTimber() {
        Timber.plant(Timber.DebugTree())
    }

    private fun initStrictMode() {
        setThreadPolicyToStrictMode()
        setVmPolicyToStrictMode()
    }

    private fun setThreadPolicyToStrictMode() {
        StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .penaltyFlashScreen()
                .build())
    }

    private fun setVmPolicyToStrictMode() {
        StrictMode.setVmPolicy(StrictMode.VmPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build())
    }

}
