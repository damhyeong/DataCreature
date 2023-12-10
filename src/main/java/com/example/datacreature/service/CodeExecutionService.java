package com.example.datacreature.service;

import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

import javax.tools.*;
import java.io.*;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;

import static java.net.URI.*;

@Service
public class CodeExecutionService {
    public void runUserCode(String userCode, String className){

        // .java file에 소스를 저장한다.
        File root = new File("./java");
        File sourceFile = new File(root, "test/Test.java");
        boolean isDirectory = sourceFile.getParentFile().mkdirs();
        try{
            Files.write(sourceFile.toPath(), userCode.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e){
            e.printStackTrace();
        }

        // 소스 파일을 컴파일한다.
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        compiler.run(null, null, null, sourceFile.getPath());

        /* 클래스 로더의 선언, 그리고 컴파일 된 클래스 실행 */
        URL url;
        // URL 에러 감싸주기
        try {
            url = root.toURI().toURL();
        } catch (MalformedURLException e) {
            System.out.println("컴파일 클래스 경로 생성 중 에러 발생 ");
            throw new RuntimeException(e);
        }

        // 해당 URL에서 실행 할 디폴트 설정의 ClassLoader 생성
        URLClassLoader classLoader = null;
        try{
            classLoader = URLClassLoader.newInstance(new URL[] {url});
        }catch (NullPointerException e){
            System.out.println("URLClassLoader로 실행 경로에 클래스 로더 생성 중 에러 발생");
            e.printStackTrace();
        }

        Class<?> cls = null;
        try{
            cls = Class.forName("test.Test", true, classLoader); // print("hello");
        } catch (ClassNotFoundException e){
            System.out.println("사용자의 클래스 파일이 Test가 아니거나, 경로 설정이 잘못되었을 것임.");
            e.printStackTrace();
        }

        Object instance = null;
        try{
            instance = cls.newInstance(); // print("world");
        } catch (InstantiationException | IllegalAccessException e){
            e.printStackTrace();
        }
    }
    public void testRunUserCode(){
        String userCode = "package test; public class Test { static { System.out.println(\"hello\"); } public Test() { System.out.println(\"world\"); } }";

        System.out.println("testRunUserCode() Start!!");

        // .java file에 소스를 저장한다.
        File root = new File("./java");
        File sourceFile = new File(root, "test/Test.java");
        boolean isDirectory = sourceFile.getParentFile().mkdirs();
        try{
            Files.write(sourceFile.toPath(), userCode.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e){
            e.printStackTrace();
        }

        // 소스 파일을 컴파일한다.
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        compiler.run(null, null, null, sourceFile.getPath());

        /* 클래스 로더의 선언, 그리고 컴파일 된 클래스 실행 */
        URL url;
        // URL 에러 감싸주기
        try {
            url = root.toURI().toURL();
        } catch (MalformedURLException e) {
            System.out.println("컴파일 클래스 경로 생성 중 에러 발생 ");
            throw new RuntimeException(e);
        }

        // 해당 URL에서 실행 할 디폴트 설정의 ClassLoader 생성
        URLClassLoader classLoader = null;
        try{
            classLoader = URLClassLoader.newInstance(new URL[] {url});
        }catch (NullPointerException e){
            System.out.println("URLClassLoader로 실행 경로에 클래스 로더 생성 중 에러 발생");
            e.printStackTrace();
        }

        Class<?> cls = null;
        try{
            cls = Class.forName("test.Test", true, classLoader); // print("hello");
        } catch (ClassNotFoundException e){
            System.out.println("사용자의 클래스 파일이 Test가 아니거나, 경로 설정이 잘못되었을 것임.");
            e.printStackTrace();
        }

        Object instance = null;
        try{
            instance = cls.newInstance(); // print("world");
        } catch (InstantiationException | IllegalAccessException e){
            e.printStackTrace();
        }
        System.out.println("성공");
    }
}

