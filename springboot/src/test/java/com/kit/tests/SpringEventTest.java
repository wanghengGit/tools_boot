package com.kit.tests;

import com.kit.config.EventConfig;
import com.kit.event.TestEvent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringEventTest {
    @Test
    public void test_listener() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(EventConfig.class);
        System.out.println("发布事件");
        TestEvent event = new TestEvent(new Object(), "事件1");
        context.publishEvent(event);
        context.close();
    }
}
