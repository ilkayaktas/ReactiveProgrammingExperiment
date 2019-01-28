package com.ilkayaktas;

import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;

/**
 * Created by aselsan on 28.01.2019 at 09:27.
 */

public class App {
    public static void main(String[] args) throws InterruptedException {

        createLateMultiSubscriber();

    }

    static void createObservable(){
        Observable<String> obs = Observable.create(emitter -> {
            for (int i = 0; i < 10; i++) {
                emitter.onNext(String.valueOf(System.currentTimeMillis()));
                Thread.sleep(100);
            }
            emitter.onComplete();

        });


        obs.map(s -> "Subscriber_1: "+s)
                .subscribe(System.out::println,
                        System.out::println);

        obs.map(s -> "Subscriber_2: "+s)
                .subscribe(System.out::println);

    }

    static void createMultiSubscriber(){
        ConnectableObservable obs = Observable.create(emitter -> {
            for (int i = 0; i < 10; i++) {
                emitter.onNext(String.valueOf(System.currentTimeMillis()));
                Thread.sleep(100);
            }

        }).publish();

        obs.map(s -> "Subscriber_1: "+s).subscribe(System.out::println);
        obs.map(s -> "Subscriber_2: "+s).subscribe(System.out::println);
        obs.connect(); // trigger emitting of elements.

//        When all subscribers are ready and then call connect() Below code will not work.
//        obs.map(s -> "Subscriber_3: "+s).subscribe(System.out::println);
//        obs.connect();
    }

    static void createLateMultiSubscriber() throws InterruptedException {
        Observable obs = Observable.create(emitter -> {
            for (int i = 0; i < 10; i++) {
                emitter.onNext(String.valueOf(System.currentTimeMillis()));
                Thread.sleep(300);
            }

        }).doOnNext(o -> System.out.println("Buffering... " + o))
                .publish()
                .autoConnect(3);

        Thread.sleep(700);
        obs.map(s -> "Subscriber_1: "+s).subscribe(System.out::println);
        Thread.sleep(400);
        obs.map(s -> "Subscriber_2: "+s).subscribe(System.out::println);
        Thread.sleep(400);
        obs.map(s -> "Subscriber_3: "+s).subscribe(System.out::println);

    }

}
