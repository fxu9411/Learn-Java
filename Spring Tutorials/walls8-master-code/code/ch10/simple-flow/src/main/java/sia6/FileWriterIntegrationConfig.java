//tag::fileWriterFlow[]
//tag::fileWriterJavaConfig[]
package sia6;

import java.io.File;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//end::fileWriterJavaConfig[]
//end::fileWriterFlow[]
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;
//tag::fileWriterJavaConfig[]
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
//tag::fileWriterFlow[]
//end::fileWriterJavaConfig[]
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
//end::fileWriterFlow[]
//tag::fileWriterJavaConfig[]
import org.springframework.integration.file.FileWritingMessageHandler;
//end::fileWriterJavaConfig[]
//tag::fileWriterFlow[]
import org.springframework.integration.file.dsl.Files;
//tag::fileWriterJavaConfig[]
import org.springframework.integration.file.support.FileExistsMode;
//end::fileWriterFlow[]
import org.springframework.integration.transformer.GenericTransformer;
//end::fileWriterJavaConfig[]
//tag::fileWriterFlow[]

//tag::fileWriterJavaConfig[]

@Configuration
public class FileWriterIntegrationConfig {

//end::fileWriterJavaConfig[]

  //end::fileWriterFlow[]

  @Profile("xmlconfig")
  @Configuration
  @ImportResource("classpath:/filewriter-config.xml")
  public static class XmlConfiguration {}

  @Profile("javaconfig")
  //tag::fileWriterJavaConfig[]
  @Bean
  @Transformer(inputChannel="textInChannel",                  <!--1-->
               outputChannel="fileWriterChannel")
  public GenericTransformer<String, String> upperCaseTransformer() {
    return text -> text.toUpperCase();
  }

  //end::fileWriterJavaConfig[]
  @Profile("javaconfig")
  //tag::fileWriterJavaConfig[]
  @Bean
  @ServiceActivator(inputChannel="fileWriterChannel")
  public FileWritingMessageHandler fileWriter() {             <!--2-->
    FileWritingMessageHandler handler =
        new FileWritingMessageHandler(new File("/tmp/sia6/files"));
    handler.setExpectReply(false);
    handler.setFileExistsMode(FileExistsMode.APPEND);
    handler.setAppendNewLine(true);
    return handler;
  }

  //end::fileWriterJavaConfig[]

  //
  // DSL Configuration
  //
  @Profile("javadsl")
//tag::fileWriterFlow[]
  @Bean
  public IntegrationFlow fileWriterFlow() {
    return IntegrationFlows
        .from(MessageChannels.direct("textInChannel"))         // <1>
        .<String, String>transform(t -> t.toUpperCase())       // <2>
        .handle(Files                                          // <3>
            .outboundAdapter(new File("/tmp/sia6/files"))
            .fileExistsMode(FileExistsMode.APPEND)
            .appendNewLine(true))
        .get();
  }

//end::fileWriterFlow[]

  /*
//tag::fileWriterFlow_withChannel[]
  @Bean
  public IntegrationFlow fileWriterFlow() {
    return IntegrationFlows
        .from(MessageChannels.direct("textInChannel"))
        .<String, String>transform(t -> t.toUpperCase())
        .channel(MessageChannels.direct("FileWriterChannel"))
        .handle(Files
            .outboundAdapter(new File("/tmp/sia6/files"))
            .fileExistsMode(FileExistsMode.APPEND)
            .appendNewLine(true))
        .get();
  }
  //end::fileWriterFlow_withChannel[]
   */

//tag::fileWriterFlow[]
//tag::fileWriterJavaConfig[]
}
//end::fileWriterJavaConfig[]
//end::fileWriterFlow[]
