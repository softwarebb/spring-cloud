package com.example.tmy.hystrix.hello;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class CommandFallbackTest {
    @Test
    public void testBadRequestException() throws InterruptedException, ExecutionException, TimeoutException {
        String res = new CommandFallback("try fail").execute();
        log.info(res);
//        assert res.equals("try fail and get fallback");
    }

    @Test
    public void testCommandFallbackWithCircleCutBreaker() {
        for (int i = 0; i < 50; i++) {
            new CommandFallbackWithCircleCutBreaker(i)
        }
    }


}