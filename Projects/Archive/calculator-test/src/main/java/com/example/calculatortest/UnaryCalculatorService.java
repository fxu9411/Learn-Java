package com.example.calculatortest;

import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@GrpcService
public class UnaryCalculatorService extends CalculatorServiceGrpc.CalculatorServiceImplBase {
    private static Logger logger = LoggerFactory.getLogger(UnaryCalculatorService.class);

    public static void main(String[] args) throws IOException {
        ServerBuilder.forPort(6565)
                .addService(new UnaryCalculatorService())
                .build()
                .start();
//        logger.info("Server start at 6565");
        System.out.println("Server start at 6565");
        while (true) {}
    }

    @Override
    public void findFactorial(Input request, StreamObserver<Output> responseObserver) {
        int input = request.getNumber();
        long result = this.factorial(input);
        Output output = Output.newBuilder()
                .setResult(result)
                .build();
        responseObserver.onNext(output);
        responseObserver.onCompleted();
    }

    private long factorial(int number){
        if(number == 0)
            return 1;
        return number * factorial(number - 1);
    }
}
