package com.ilkayaktas;

import com.ilkayaktas.clean.presenter.ThePresenter;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.observables.ConnectableObservable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by aselsan on 28.01.2019 at 09:27.
 */

public class App {
    public static void main(String[] args){

        createCleanArch();

    }

    private static void createCleanArch() {
        /*
        Observable<String> businessLogic = Observable.just("Bir", "İki", "Üç");

        UseCase<String> useCase = new UseCase(businessLogic);

        useCase.execute(new BusinessLogicResultExecutor<>()); // Compose business logic and result executor in usecase

        businessLogic.subscribe();
         */

        ThePresenter thePresenter = new ThePresenter();
        thePresenter.doDidDone();

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

    static void createPublishSubscriber(){
        PublishSubject<String> publisher = PublishSubject.create();

        publisher.onNext("First message");

        publisher.subscribe(new CustomObserver<>("1 "));

        publisher.onNext("Second message");
        publisher.onNext("Third message");

        publisher.subscribe(new CustomObserver<>("2 "));

        publisher.onNext("Fourth message");
        publisher.onNext("Fifth message");

        publisher.subscribe(new CustomObserver<>("3 "));

        publisher.onNext("Sixth message");
        publisher.onNext("Seventh message");

  //      publisher.onError(new Throwable("Error"));
        publisher.onComplete();
    }

}

class CustomObserver<T> implements Observer<T>{
    private String name;

    public CustomObserver(String name) {
        this.name = name;
    }

    @Override
    public void onSubscribe(Disposable d) {
        System.out.println(name+" Subscribed!");
    }

    @Override
    public void onNext(T t) {
        System.out.println(name+" "+t.toString());
    }

    @Override
    public void onError(Throwable e) {
        System.out.println(name+" "+e.getLocalizedMessage());
    }

    @Override
    public void onComplete() {
        System.out.println(name+" "+"Finished!");
    }
}
