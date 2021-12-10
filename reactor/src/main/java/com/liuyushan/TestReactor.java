package com.liuyushan;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

/**
 * @author lys
 * 2021/11/14
 */
@SpringBootTest
public class TestReactor {

    @Test
    public void test() {
        String[] strings = {"hello", "world"};
        Flux<String> just = Flux.just(strings);
        just.subscribe(t -> System.out.println(t));
    }

    @Test
    public void mergeFlux() {
        Flux<String> source1 = Flux.just("hello", "world");
        Flux<String> source2 = Flux.just("hi", "ted");

        Flux<String> merge = source1.mergeWith(source2);
        merge.subscribe(System.out::println);
    }

    @Test
    public void zipFlux() {
        Flux<String> source1 = Flux.just("hello", "world");
        Flux<String> source2 = Flux.just("hi", "ted");

        Flux<Tuple2<String, String>> zip = source1.zipWith(source2);
        zip.subscribe(tuple -> {
            System.out.println(tuple.getT1() + " -> " + tuple.getT2());
        });
    }


    @Test
    public void skipFlux() {
        Flux<String> source1 = Flux.just("hello", "world", "hi", "ted");

        Flux<String> skip = source1.skip(2);
        skip.subscribe(System.out::println);
    }

    @Test
    public void mapFlux() {
        Flux<String> source1 = Flux.just("hello", "world", "hi", "ted");

        Flux<String> skip = source1.map(s -> s + " is mapped");
        skip.subscribe(System.out::println);
    }

    @Test
    public void anyFlux() {
        Flux<String> source1 = Flux.just("hello", "world", "ted");

        Mono<Boolean> mono = source1.any(s -> s.contains("e"));
        mono.subscribe(System.out::println);
    }

    @Test
    public void create() {
        Flux.create((t) -> {
            t.next("create");
            t.next("create1");
            t.complete();
        }).subscribe(System.out::println);
    }

    @Test
    public void crea(){
        Mono.create(sink -> sink.success("Hello")).subscribe(System.out::println);
    }

}
