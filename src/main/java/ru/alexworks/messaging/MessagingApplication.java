package ru.alexworks.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.dsl.Transformers;
import org.springframework.integration.scheduling.PollerMetadata;
import org.springframework.messaging.MessageChannel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

@IntegrationComponentScan
@SuppressWarnings({ "resource", "Duplicates", "InfiniteLoopStatement" })
@ComponentScan
@Configuration
@EnableIntegration
public class MessagingApplication {


	@Bean
	public QueueChannel caterpillarChannel() {
		return MessageChannels.queue(10).get();
	}

	@Bean
	public PublishSubscribeChannel butterflyChannel() {
		return MessageChannels.publishSubscribe().get();
	}

	@Bean
	public IntegrationFlow transformFlow() {
		return IntegrationFlows.from("caterpillarChannel")
				.handle("butterflyService", "grow")

				.channel("butterflyChannel")
				.get();
	}

	@Bean(name = PollerMetadata.DEFAULT_POLLER)
	public PollerMetadata poller() {
		return Pollers.fixedRate(100).maxMessagesPerPoll(2).get();
	}


	public static void main(String[] args) throws Exception {

		AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(MessagingApplication.class);

		InterfaceGetway interfaceGetway = ctx.getBean(InterfaceGetway.class);
		ForkJoinPool pool = ForkJoinPool.commonPool();

		while (true) {
			Thread.sleep(7000);

			pool.execute(() -> {
				Caterpillar caterpillar1 = new Caterpillar("asa");
				List<Caterpillar> items = new ArrayList<>();
				items.add(caterpillar1);
				Collection<Butterfly> butterflies = interfaceGetway.process(items);
				System.out.println("Ready butterflies: " + butterflies.stream()
						.map(Butterfly::getName)
						.collect(Collectors.joining(",")));
			});
		}

	}

}