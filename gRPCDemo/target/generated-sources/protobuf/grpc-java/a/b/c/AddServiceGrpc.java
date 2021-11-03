package a.b.c;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.41.0)",
    comments = "Source: add.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class AddServiceGrpc {

  private AddServiceGrpc() {}

  public static final String SERVICE_NAME = "grpc.AddService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<a.b.c.AddRequest,
      a.b.c.AddReply> getAddMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "add",
      requestType = a.b.c.AddRequest.class,
      responseType = a.b.c.AddReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<a.b.c.AddRequest,
      a.b.c.AddReply> getAddMethod() {
    io.grpc.MethodDescriptor<a.b.c.AddRequest, a.b.c.AddReply> getAddMethod;
    if ((getAddMethod = AddServiceGrpc.getAddMethod) == null) {
      synchronized (AddServiceGrpc.class) {
        if ((getAddMethod = AddServiceGrpc.getAddMethod) == null) {
          AddServiceGrpc.getAddMethod = getAddMethod =
              io.grpc.MethodDescriptor.<a.b.c.AddRequest, a.b.c.AddReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "add"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  a.b.c.AddRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  a.b.c.AddReply.getDefaultInstance()))
              .setSchemaDescriptor(new AddServiceMethodDescriptorSupplier("add"))
              .build();
        }
      }
    }
    return getAddMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static AddServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AddServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AddServiceStub>() {
        @java.lang.Override
        public AddServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AddServiceStub(channel, callOptions);
        }
      };
    return AddServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static AddServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AddServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AddServiceBlockingStub>() {
        @java.lang.Override
        public AddServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AddServiceBlockingStub(channel, callOptions);
        }
      };
    return AddServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static AddServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AddServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AddServiceFutureStub>() {
        @java.lang.Override
        public AddServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AddServiceFutureStub(channel, callOptions);
        }
      };
    return AddServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class AddServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void add(a.b.c.AddRequest request,
        io.grpc.stub.StreamObserver<a.b.c.AddReply> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAddMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getAddMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                a.b.c.AddRequest,
                a.b.c.AddReply>(
                  this, METHODID_ADD)))
          .build();
    }
  }

  /**
   */
  public static final class AddServiceStub extends io.grpc.stub.AbstractAsyncStub<AddServiceStub> {
    private AddServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AddServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AddServiceStub(channel, callOptions);
    }

    /**
     */
    public void add(a.b.c.AddRequest request,
        io.grpc.stub.StreamObserver<a.b.c.AddReply> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAddMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class AddServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<AddServiceBlockingStub> {
    private AddServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AddServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AddServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public a.b.c.AddReply add(a.b.c.AddRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAddMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class AddServiceFutureStub extends io.grpc.stub.AbstractFutureStub<AddServiceFutureStub> {
    private AddServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AddServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AddServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<a.b.c.AddReply> add(
        a.b.c.AddRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAddMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_ADD = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AddServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(AddServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_ADD:
          serviceImpl.add((a.b.c.AddRequest) request,
              (io.grpc.stub.StreamObserver<a.b.c.AddReply>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class AddServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    AddServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return a.b.c.AddServiceProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("AddService");
    }
  }

  private static final class AddServiceFileDescriptorSupplier
      extends AddServiceBaseDescriptorSupplier {
    AddServiceFileDescriptorSupplier() {}
  }

  private static final class AddServiceMethodDescriptorSupplier
      extends AddServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    AddServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (AddServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new AddServiceFileDescriptorSupplier())
              .addMethod(getAddMethod())
              .build();
        }
      }
    }
    return result;
  }
}
