/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.example.grpc.provider;

import com.example.grpc.provider.interceptor.ProviderInterceptor;
import com.example.grpc.provider.service.GreeterBlockingErrorServiceImpl;
import com.example.grpc.provider.service.GreeterBlockingServiceImpl;
import com.example.grpc.provider.service.GreeterServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.ServerInterceptors;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Configurable
@Component
public class ProviderConfiguration {

    @Bean(initMethod = "start", destroyMethod = "shutdown")
    public Server server() {
        return ServerBuilder.forPort(18080)
                            .addService(ServerInterceptors.intercept(new GreeterServiceImpl(), new ProviderInterceptor()))
                            .addService(ServerInterceptors.intercept(new GreeterBlockingServiceImpl(), new ProviderInterceptor()))
                            .addService(ServerInterceptors.intercept(new GreeterBlockingErrorServiceImpl(), new ProviderInterceptor()))
                            .build();
    }
}
