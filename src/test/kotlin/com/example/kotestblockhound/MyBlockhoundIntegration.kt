package com.example.kotestblockhound

import reactor.blockhound.BlockHound
import reactor.blockhound.integration.BlockHoundIntegration

class MyBlockhoundIntegration : BlockHoundIntegration {
    override fun applyTo(builder: BlockHound.Builder?) {
        builder!!.nonBlockingThreadPredicate { other ->
            other.or { thread -> thread.name == "MustNotBeBlocked" }
        }
    }
}
