package tds.practice.SimpleCalculator.controller;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/basic")
public class CalculatorServer extends CalculatorServiceGrpc.CalculatorServiceImplBase {
    public static void main(String[] args) throws IOException {
        ServerBuilder.forPort(9999)
                .addService(new CalculatorServer())
                .build()
                .start();
        System.out.println("Server start at port 9999");
        while (true) {
        }
    }

    @RequestMapping("/calc")
    public void calc(CalcRequest calcRequest, StreamObserver<CalcReply> calcReplyStreamObserver) {
        double res = myCalc(calcRequest.getA(), calcRequest.getB(), calcRequest.getC());
        calcReplyStreamObserver.onNext(CalcReply.newBuilder().setRes(res).build());
        calcReplyStreamObserver.onCompleted();
    }

    private double myCalc(double a, String b, double c) {
        switch (b) {
            case "+":
                return a + c;
            case "-":
                return a - c;
            case "*":
                return a * c;
            case "\\":
                return a / c;
            default:
                System.out.println("Invalid Number or Operand");
                return 0;
        }
    }
}
