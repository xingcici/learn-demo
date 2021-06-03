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

package com.example.grpc.provider.service;

import io.grpc.stub.StreamObserver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GreeterServiceImpl extends com.example.grpc.proto.GreeterGrpc.GreeterImplBase {

    private static final Logger LOGGER = LogManager.getLogger(GreeterServiceImpl.class);

    @Override
    public StreamObserver<com.example.grpc.proto.HelloRequest> sayHello(final StreamObserver<com.example.grpc.proto.HelloReply> responseObserver) {
        StreamObserver<com.example.grpc.proto.HelloRequest> requestStreamObserver = new StreamObserver<com.example.grpc.proto.HelloRequest>() {

            @Override
            public void onNext(com.example.grpc.proto.HelloRequest request) {
                LOGGER.info("Receive an message from client. Message: {}", request.getName());
                responseObserver.onNext(com.example.grpc.proto.HelloReply.newBuilder().setMessage("Hi," + request.getName()).build());
            }

            @Override
            public void onError(Throwable throwable) {
                responseObserver.onError(throwable);
            }

            @Override
            public void onCompleted() {
                LOGGER.info("End the stream.");
                responseObserver.onCompleted();
            }
        };
        return requestStreamObserver;
    }
}
