package com.example.calculatortest;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CalculatorClient {
    CalculatorServiceGrpc.CalculatorServiceBlockingStub stub;
    ManagedChannel channel;

    private static final Logger logger = LoggerFactory.getLogger(CalculatorClient.class);

    public static void main(String[] args) {
        int a = 10;

        CalculatorClient calculatorClient = new CalculatorClient();
        Output output = calculatorClient.stub.findFactorial(Input.newBuilder().setNumber(a).build());
//        logger.info(String.valueOf(output.getResult()));
        System.out.println(output.getResult());
    }

    public CalculatorClient() {
        channel = ManagedChannelBuilder.forAddress("localhost", 9090)
                .usePlaintext()
                .build();
        stub = CalculatorServiceGrpc.newBlockingStub(channel);
    }
}
