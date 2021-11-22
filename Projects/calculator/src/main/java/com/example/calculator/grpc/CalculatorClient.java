package com.example.calculator.grpc;

import com.example.calculator.CalculatorServiceGrpc;
import com.example.calculator.Input;
import com.example.calculator.Output;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class CalculatorClient {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090)
                .usePlaintext()
                .build();
        CalculatorServiceGrpc.CalculatorServiceBlockingStub stub = CalculatorServiceGrpc.newBlockingStub(channel);

        Output calcOutput = stub.findFactorial(Input.newBuilder().setNumber(10).build());

        System.out.println(calcOutput.getResult());

        channel.shutdown();
    }
}
