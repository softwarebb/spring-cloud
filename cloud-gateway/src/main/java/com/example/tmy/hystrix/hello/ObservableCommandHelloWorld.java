package com.example.tmy.hystrix.hello;

import com.google.common.collect.Lists;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixObservableCommand;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.schedulers.Schedulers;

import java.util.HashMap;
import java.util.List;

/**
 * @description: desc
 * @author: mengyuetang
 * @email: mengyuetang@sohu-inc.com
 * @date: 2021/12/16 17:09
 */
public class ObservableCommandHelloWorld extends HystrixObservableCommand<String> {

    private final String name;

    public ObservableCommandHelloWorld(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("HelloGroup"));
        this.name = name;
    }

    @Override
    protected Observable<String> construct() {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onNext("hello");
                    subscriber.onNext(name);
                    subscriber.onNext("`s first subscriber.");
                    subscriber.onCompleted();
                }
            }
        }).subscribeOn(Schedulers.io());
    }
}
