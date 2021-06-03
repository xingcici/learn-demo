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

package com.example.grpc.controller;

import com.example.grpc.consumr.ConsumerInterceptor;
import io.grpc.ClientInterceptors;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.ClientCallStreamObserver;
import io.grpc.stub.ClientResponseObserver;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/case")
public class CaseController {

    private static final Logger LOGGER = LogManager.getLogger(CaseController.class);

    private static final String SUCCESS = "Success";

    private final String gprcProviderHost = "127.0.0.1";
    private final int grpcProviderPort = 18080;
    private ManagedChannel channel;
    private com.example.grpc.proto.GreeterGrpc.GreeterStub greeterStub;
    private com.example.grpc.proto.GreeterBlockingGrpc.GreeterBlockingBlockingStub greeterBlockingStub;
    private com.example.grpc.proto.GreeterBlockingErrorGrpc.GreeterBlockingErrorBlockingStub greeterBlockingErrorStub;

    @PostConstruct
    public void up() {
        channel = ManagedChannelBuilder.forAddress(gprcProviderHost, grpcProviderPort).usePlaintext(true).build();
        greeterStub = com.example.grpc.proto.GreeterGrpc.newStub(ClientInterceptors.intercept(channel, new ConsumerInterceptor()));
        greeterBlockingStub = com.example.grpc.proto.GreeterBlockingGrpc.newBlockingStub(ClientInterceptors.intercept(channel, new ConsumerInterceptor()));
        greeterBlockingErrorStub = com.example.grpc.proto.GreeterBlockingErrorGrpc.newBlockingStub(ClientInterceptors.intercept(channel, new ConsumerInterceptor()));
    }

    @RequestMapping("/grpc-scenario")
    @ResponseBody
    public String testcase() {
        //greetService();
        greetBlockingService();
        //greetBlockingErrorService();
        return SUCCESS;
    }

    @RequestMapping("/healthCheck")
    @ResponseBody
    public String healthCheck() {
        // your codes
        return SUCCESS;
    }

    private static List<String> names() {
        return Arrays.asList("Sophia", "Jackson");
    }

    private void greetService() {
        ClientResponseObserver<com.example.grpc.proto.HelloRequest, com.example.grpc.proto.HelloReply> helloReplyStreamObserver = new ClientResponseObserver<com.example.grpc.proto.HelloRequest, com.example.grpc.proto.HelloReply>() {
            private ClientCallStreamObserver<com.example.grpc.proto.HelloRequest> requestStream;

            @Override
            public void beforeStart(ClientCallStreamObserver observer) {
                this.requestStream = observer;
                this.requestStream.setOnReadyHandler(new Runnable() {
                    Iterator<String> iterator = names().iterator();

                    @Override
                    public void run() {
                        while (requestStream.isReady()) {
                            if (iterator.hasNext()) {
                                String name = iterator.next();
                                com.example.grpc.proto.HelloRequest request = com.example.grpc.proto.HelloRequest.newBuilder().setName(name).build();
                                requestStream.onNext(request);
                            } else {
                                requestStream.onCompleted();
                            }
                        }
                    }
                });
            }

            @Override
            public void onNext(com.example.grpc.proto.HelloReply reply) {
                LOGGER.info("Receive an message from provider. message: {}", reply.getMessage());
                requestStream.request(1);
            }

            public void onError(Throwable throwable) {
                LOGGER.error("Failed to send data", throwable);
            }

            public void onCompleted() {
                LOGGER.info("All Done");
            }
        };

        greeterStub.sayHello(helloReplyStreamObserver);
    }

    private void greetBlockingService() {
        com.example.grpc.proto.HelloRequest request = com.example.grpc.proto.HelloRequest.newBuilder().setName("Sophia").build();
        greeterBlockingStub.sayHello(request);
    }

    private void greetBlockingErrorService() {
        com.example.grpc.proto.HelloRequest request = com.example.grpc.proto.HelloRequest.newBuilder().setName("Tony").build();
        greeterBlockingErrorStub.sayHello(request);
    }
}
