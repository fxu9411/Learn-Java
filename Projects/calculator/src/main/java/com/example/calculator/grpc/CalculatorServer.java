package com.example.calculator.grpc;

import com.example.calculator.CalculatorServiceGrpc;
import com.example.calculator.Input;
import com.example.calculator.Output;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

import java.io.IOException;

@GrpcService
public class CalculatorServer extends CalculatorServiceGrpc.CalculatorServiceImplBase {

    public static void main(String[] args) throws IOException, InterruptedException {

        // build gRPC server
        Server server = ServerBuilder.forPort(9090)
                .addService(new CalculatorServer())
                .build();

        // start
        server.start();
        System.out.println("Server start at 9090");

        // shutdown hook
//        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
//            System.out.println("gRPC server is shutting down!");
//            server.shutdown();
//        }));

        server.awaitTermination();
//        while (true) {}
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