package com.example.tmy.hystrix.hello;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixObservableCommand;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.observables.AsyncOnSubscribe;
import rx.schedulers.Schedulers;

/**
 * @description: desc
 * @author: mengyuetang
 * @email: mengyuetang@sohu-inc.com
 * @date: 2021/12/16 17:09
 */
public class ObservableCommandFallback extends HystrixObservableCommand<String> {

    private final String name;

    public ObservableCommandFallback(String name) {
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
                    subscriber.onError(new Exception(name+" and fail"));
                }
            }
        }).subscribeOn(Schedulers.io());
    }
    @Override
    protected Observable<String> resumeWithFallback(){

        return Observable.create(new AsyncOnSubscribe<String, String>() {
            @Override
            protected String generateState() {
                return name + "-state";
            }

            @Override
            protected String next(String state, long requested, Observer<Observable<? extends String>> observer) {
//                observer.onNext(name);
                observer.onCompleted();
                return name + "-state";
            }
        });
    }
}
