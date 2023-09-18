package com.example.kotestblockhound

import io.kotest.common.runBlocking
import io.kotest.core.spec.style.StringSpec
import io.kotest.extensions.blockhound.BlockHound
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.junit.jupiter.api.assertThrows
import reactor.blockhound.BlockingOperationError

class Test : StringSpec({
    extension(BlockHound())

    "BlockingOperationError is thrown as expected" {
        assertThrows<BlockingOperationError> {
            runBlocking {
                withContext(Dispatchers.Default) {
                    Thread.sleep(100)
                }
            }
        }
    }
})
