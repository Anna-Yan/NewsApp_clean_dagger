import kotlinx.coroutines.*
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import kotlin.coroutines.CoroutineContext

@ExperimentalCoroutinesApi
class MainCoroutineRule(
	private val dispatcher: CoroutineDispatcher = TestCoroutineDispatcher()
) : TestRule, CoroutineScope {

	private val job = Job()

	override val coroutineContext: CoroutineContext
		get() = dispatcher + job

	override fun apply(base: Statement, description: Description?): Statement {
		return object : Statement() {
			override fun evaluate() {
				Dispatchers.resetMain()
				Dispatchers.setMain(dispatcher)
				base.evaluate()
				Dispatchers.resetMain()
				job.cancel()
			}
		}
	}

	fun runBlockingTest(block: suspend () -> Unit) {
		this.launch {
			block()
		}
	}
}
