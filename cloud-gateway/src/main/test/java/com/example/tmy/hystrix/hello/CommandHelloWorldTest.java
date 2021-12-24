package com.example.tmy.hystrix.hello;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CommandHelloWorldTest {

    @Test
    public void testSync() throws ExecutionException, InterruptedException {
        assertEquals("Hello world!", new CommandHelloWorld("tmy").execute());
        assertEquals("Hello world!", new ObservableCommandHelloWorld("tmy").observe());
    }

}