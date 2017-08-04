package io.petros.posts.kotlin.util.rx

import io.reactivex.Scheduler

/**
 * @property ioScheduler A [Scheduler] intended for IO-bound work. The implementation is backed by an [java.util.concurrent.Executor]
 * thread-pool that will grow as needed. This can be used for asynchronously performing blocking IO.
 * @property computationScheduler A [Scheduler] intended for computational work. This can be used for event-loops, processing callbacks and
 * other computational work. Unhandled errors will be delivered to the scheduler Thread's [Thread.UncaughtExceptionHandler].
 * @property trampolineScheduler A [Scheduler] that queues work on the current thread to be executed after the current work completes.
 * @property androidMainThreadScheduler A {@link Scheduler} which executes actions on the Android UI thread.
 */
class RxSchedulers(val ioScheduler: Scheduler, val computationScheduler: Scheduler, val trampolineScheduler: Scheduler,
                   val androidMainThreadScheduler: Scheduler)
