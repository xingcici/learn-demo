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

public class GreeterBlockingErrorServiceImpl extends com.example.grpc.proto.GreeterBlockingErrorGrpc.GreeterBlockingErrorImplBase {
    @Override
    public void sayHello(com.example.grpc.proto.HelloRequest request, StreamObserver<com.example.grpc.proto.HelloReply> responseObserver) {
        responseObserver.onNext(com.example.grpc.proto.HelloReply.newBuilder().setMessage("Hi," + request.getName()).build());
        responseObserver.onCompleted();
    }
}
