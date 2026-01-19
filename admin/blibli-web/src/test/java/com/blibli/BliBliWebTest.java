package com.blibli;

import com.blibli.entity.ExamTest;
import com.blibli.utils.RedisService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

@SpringBootTest
public class BliBliWebTest {
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    RedisService redisService;

    public static final String REDIS_KEY = "test-key";
    @Test
    void contextLoads() {
        System.out.println(111);
    }

    @Test
    void demo1() throws JsonProcessingException {
        ExamTest testBuilder = ExamTest.builder().username("aaa").password("bbb").build();


        ObjectMapper mapper = new ObjectMapper();

        String valueAsString = mapper.writeValueAsString(testBuilder);

        redisTemplate.opsForValue().set(REDIS_KEY, valueAsString, 10, TimeUnit.MINUTES);

        String redis_result =(String) redisTemplate.opsForValue().get(REDIS_KEY);

        System.out.println("redis_result = " + redis_result);


        redisService.set(REDIS_KEY, valueAsString, 1000 * 60 * 60, TimeUnit.SECONDS);


        String redis_result1 = (String)redisService.get(REDIS_KEY);
        System.out.println("redis_result1 = " + redis_result1);


    }

    @Test
    void testDemo2() throws IOException {


        HashOperations<String, Object, Object> hashOperations = redisTemplate.opsForHash();

        hashOperations.put(REDIS_KEY, "name", "TOIM");
        hashOperations.put(REDIS_KEY, "age", 20);

        Object name = hashOperations.get(REDIS_KEY, "name");
        System.out.println("name = " + name);
    }

    @Test
    void testDemo3() throws IOException {
        ListOperations listOperations = redisTemplate.opsForList();

        listOperations.leftPush("a2", "hello");
        listOperations.leftPush("a2", "world");
        listOperations.leftPush("a2", "goodbye");

        Object pop = listOperations.leftPop("a2");
        System.out.println("pop = " + pop);

        Object pop1 = listOperations.rightPop("a2");
        System.out.println("pop1 = " + pop1);

        System.out.println("listOperations = " + listOperations.size("a2"));

        List list = listOperations.range("a2", 0, listOperations.size("a2"));

        for (Object item: list
        ) {
            System.out.println("item = " + item);
        }
    }
}
