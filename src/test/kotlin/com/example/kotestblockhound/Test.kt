package com.example.kotestblockhound

import io.kotest.core.spec.style.StringSpec
import io.kotest.extensions.blockhound.BlockHound
import org.junit.jupiter.api.assertThrows
import reactor.blockhound.BlockingOperationError
import java.util.concurrent.ExecutionException
import java.util.concurrent.Executors
import java.util.concurrent.Future

private fun <A> Future<A>.getOrThrow(): A = try {
    get()
} catch (e: ExecutionException) {
    throw e.cause!!
}

class Test : StringSpec({
    extension(BlockHound())

    "BlockingOperationError is thrown as expected" {
        val myDispatcher = Executors.newFixedThreadPool(1) { r ->
            Executors.defaultThreadFactory().newThread(r).also { it.name = "MustNotBeBlocked" }
        }

        assertThrows<BlockingOperationError> {
            myDispatcher.submit {
                Thread.sleep(100)
            }.getOrThrow()
        }
    }
})
