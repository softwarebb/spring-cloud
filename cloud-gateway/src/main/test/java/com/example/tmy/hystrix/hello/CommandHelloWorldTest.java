package com.example.tmy.hystrix.hello;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import rx.Observable;
import rx.Observer;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class CommandHelloWorldTest {

    /**
     * 理解 excute和queue和toObservable和observe,
     * command只能包装出一个observable, observable可以包装出多个observable
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void testSync() throws ExecutionException, InterruptedException {
        CommandHelloWorld commandHelloWorld = new CommandHelloWorld("tmy");
        // 阻塞，底层实现是queue().get()
        String s = commandHelloWorld.execute();
        // 阻塞等价execute
//        String s1 = commandHelloWorld.queue().get();
//        String s2 = commandHelloWorld.toObservable().toBlocking().toFuture().get();
//        String s3 = commandHelloWorld.observe().toBlocking().toFuture().get();
        log.info(s);
    }

    @Test
    public void testAsync() throws ExecutionException, InterruptedException, TimeoutException {
        CommandHelloWorld commandHelloWorld = new CommandHelloWorld("tmy");
        // 异步
        Future<String> future = commandHelloWorld.queue();
        if (future.isDone()) {
            log.info("future done", future.get());
        } else {
            log.info("future gone", future.get(1000L, TimeUnit.MILLISECONDS));
        }
    }

    @Test
    public void testReactive() {
        CommandHelloWorld commandHelloWorld = new CommandHelloWorld("tmy");
        // 先执行run或construct再注册
        Observable<String> observeAysnc = commandHelloWorld.observe();
        // 异步只有结果，忽略完成和异常信号
        observeAysnc.subscribe(s -> log.info("action result = {}", s));
        // 异步有结果，完成和异常信号常
        observeAysnc.subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                log.info("yeah, completed");
            }

            @Override
            public void onError(Throwable e) {
                log.error("no, there is some problems.", e);
            }

            @Override
            public void onNext(String s) {
                log.info("observer res = {}", s);
            }
        });
    }

    @Test
    public void testColdAndHotObservable() {
        // 先注册到Observe再执行run或者construct
        Observable<String> hot = new CommandHelloWorld("cold").toObservable();
        // 先执行run或者construct，在注册到Observe
        Observable<String> cold = new CommandHelloWorld("hot").observe();
    }

}