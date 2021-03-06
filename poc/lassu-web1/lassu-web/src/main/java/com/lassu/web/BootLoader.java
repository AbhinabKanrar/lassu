/**
 * 
 */
package com.lassu.web;

import static reactor.bus.selector.Selectors.$;

import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jms.JmsAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import com.lassu.common.util.CommonConstant;
import com.lassu.service.ApplicationServiceContext;
import com.lassu.service.security.PostAuthConsumer;

import reactor.Environment;
import reactor.bus.EventBus;
import reactor.core.config.DispatcherType;

/**
 * @author abhinab
 *
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.lassu.web")
@Import(ApplicationServiceContext.class)
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class, JmsAutoConfiguration.class })
public class BootLoader implements CommandLineRunner {

	private volatile static EventBus EVENT_BUS = null;
	private static final int AVAILABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();
	public static int REACTOR_THREAD_COUNT;

	static {
		try {
			REACTOR_THREAD_COUNT = Integer
					.parseInt(System.getProperty("reactor.threads", String.valueOf(AVAILABLE_PROCESSORS)));
		} catch (Exception e) {
			REACTOR_THREAD_COUNT = AVAILABLE_PROCESSORS;
		}
	}

	public static EventBus getEventBus() {
		while (EVENT_BUS == null) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
		}
		return EVENT_BUS;
	}

	@Bean
	Environment env() {
		return Environment.initializeIfEmpty().assignErrorJournal();
	}

	@Bean
	EventBus createEventBus(Environment env) {
		EventBus evBus = EventBus.create(env, Environment.newDispatcher(REACTOR_THREAD_COUNT, REACTOR_THREAD_COUNT,
				DispatcherType.THREAD_POOL_EXECUTOR));
		EVENT_BUS = evBus;
		return EVENT_BUS;
	}

	@Autowired
	private EventBus eventBus;

	@Autowired
	private PostAuthConsumer postAuthConsumer;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.boot.CommandLineRunner#run(java.lang.String[])
	 */
	@Override
	public void run(String... args) throws Exception {
		eventBus.on($(CommonConstant.COMPONENT_POSTAUTH_PROCESSOR), postAuthConsumer);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(BootLoader.class, args);
	}

	@PreDestroy
	void shutdownBus() {
		Environment.terminate();
	}

}
