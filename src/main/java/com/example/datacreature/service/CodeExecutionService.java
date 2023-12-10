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
            if(sourceFile.exists())
                System.out.println("SourceFile이 이미 존재함");
            else
                System.out.println("SourceFile이 정상적으로 없음.");

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
        String userCode = "package test; " + "import java.io.*; import java.util.*;" +
                "public class Test { static { System.out.println(\"hello\"); } public Test() throws IOException { " +
                "System.out.println(\"world\"); " +
                "BufferedReader br = new BufferedReader(new InputStreamReader(System.in));" +
                "String str = br.readLine();" +
                "System.out.println(str);" +
                "} " +
                "}";

        System.out.println("testRunUserCode() Start!!");

        // .java file에 소스를 저장한다.
        File root = new File("./java");
        File sourceFile = new File(root, "test/Test.java");

        //
        File mustRemoveClassFile = new File("./java/test/Test.class");

        boolean isDirectory = sourceFile.getParentFile().mkdirs();
        try{
            // 이미 제작 된 SourceFile이 있다면, 삭제해야 한다. 최신 코드가 오류가 나더라도, 정상적으로 수행되기 때문.
            if(sourceFile.exists())
                System.out.println("SourceFile이 이미 존재함.");
            else
                System.out.println("SourceFile이 정상적으로 존재하지 않음.");

            // class 파일이 이미 존재한다면, 최신 코드의 오류가 적용되지 않는다.
            if(mustRemoveClassFile.exists())
                System.out.println("SourceFile의 결과물 .class 파일이 이미 존재함.");
            else
                System.out.println("SourceFile의 결과물 .class 파일이 정상적으로 존재하지 않음.");

            Files.write(sourceFile.toPath(), userCode.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e){
            e.printStackTrace();
        }

        // 소스 파일을 컴파일한다.
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

        String inputText = "Example InputText!";
        InputStream originalSystemIn = System.in; // 원래의 System.in을 보존하기 위함.

        PrintStream originalSystemOut = System.out; // 원래의 System.out 을 보존하기 위함.

        // 출력을 캡쳐하기 위한 ByteArrayOutputStream 생성
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream newOut = new PrintStream(baos);

        // 동적 생성 된 파일에 console에 입력하는 것과 같은 기능을 하기 위해 제작.
        System.setIn(new ByteArrayInputStream(inputText.getBytes()));
        System.setOut(newOut);

        // 정상적 컴파일 될 시 0 반환, 아니라면 다른 숫자 반환됨.
        int result = compiler.run(System.in, System.out, null, sourceFile.getPath());

        // compiler.run 실행 시, return 0 시 정상적 컴파일 완료, 그 외 숫자 반환 시 오류가 발생됐다는 뜻.
        if(result != 0){
            System.out.println("컴파일 실패");
            return;
        }


        /* 클래스 로더의 선언, 그리고 컴파일 된 클래스 실행 */
        URL url;
        // URL 에러 감싸주기
        try {
            url = root.toURI().toURL();
        } catch (MalformedURLException e) {
            System.out.println("컴파일 클래스 경로 생성 중 에러 발생 ");
            System.setIn(originalSystemIn);
            throw new RuntimeException(e);
        }

        // 해당 URL에서 실행 할 디폴트 설정의 ClassLoader 생성
        URLClassLoader classLoader = null;
        try{
            classLoader = URLClassLoader.newInstance(new URL[] {url});
        }catch (NullPointerException e){
            System.out.println("URLClassLoader로 실행 경로에 클래스 로더 생성 중 에러 발생");
            System.setIn(originalSystemIn);
            e.printStackTrace();
        }

        Class<?> cls = null;
        try{
            cls = Class.forName("test.Test", true, classLoader); // print("hello");
        } catch (ClassNotFoundException e){
            System.out.println("사용자의 클래스 파일이 Test가 아니거나, 경로 설정이 잘못되었을 것임.");
            System.setIn(originalSystemIn);
            e.printStackTrace();
        }

        Object instance = null;
        try{
            instance = cls.newInstance(); // print("world");
        } catch (InstantiationException | IllegalAccessException e){
            System.setIn(originalSystemIn);
            System.out.println("메서드 인스턴스 생성 중 오류.");
            e.printStackTrace();
        }

        // 출력 캡처
        System.out.flush();
        String capturedOutput = baos.toString();

        //System.in and System.out을 원상태로 복구한다.
        System.setIn(originalSystemIn);
        System.setOut(originalSystemOut);

        System.out.println("성공");
        System.out.println("캡쳐된 output : " + capturedOutput);

        /// 예시 Output을 repository에서 가져오고, capturedOutput과 동일하다면, return 정답.
        /// 틀렸다면, return 실패

        try{
            sourceFile.delete();
            mustRemoveClassFile.delete();
        } catch (SecurityException e){
            e.printStackTrace();
        }
    }
}

