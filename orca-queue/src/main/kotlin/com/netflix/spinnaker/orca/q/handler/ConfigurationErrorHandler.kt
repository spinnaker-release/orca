/*
 * Copyright 2017 Netflix, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.netflix.spinnaker.orca.q.handler

import com.netflix.spinnaker.orca.ExecutionStatus.TERMINAL
import com.netflix.spinnaker.orca.q.Message.ConfigurationError
import com.netflix.spinnaker.orca.q.Message.ExecutionComplete
import com.netflix.spinnaker.orca.q.MessageHandler
import com.netflix.spinnaker.orca.q.Queue
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
open class ConfigurationErrorHandler
@Autowired constructor(
  override val queue: Queue
) : MessageHandler<ConfigurationError> {

  override val messageType = ConfigurationError::class.java

  override fun handle(message: ConfigurationError) {
    queue.push(ExecutionComplete(message, TERMINAL))
  }
}