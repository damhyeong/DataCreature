package com.example.datacreature.service;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.command.LogContainerCmd;
import com.github.dockerjava.api.model.Frame;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.command.LogContainerResultCallback;
import com.github.dockerjava.okhttp.OkDockerHttpClient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.util.concurrent.TimeUnit;

@Service
@Setter
@Getter
public class DockerService {
    private DockerClient dockerClient;

//    @Autowired
//    public DockerService(DockerClient dockerClient){
//        this.dockerClient = dockerClient;
//    }

    public DockerClient createDockerClient(){
        DockerClient dockerClient = DockerClientBuilder.getInstance()
                .withDockerHttpClient(new OkDockerHttpClient.Builder()
                        .dockerHost(URI.create("unix:///var/run/docker.sock"))
                        .build())
                .build();

        return dockerClient;
    }

    public String executeCode(String userCode) throws InterruptedException {

        String fileName = "UserCode";
        saveToFile(fileName, userCode);

        //Docker Image를 기반으로 컨테이너 생성
        CreateContainerResponse container = dockerClient.createContainerCmd("openjdk:8")
                .withCmd("java", "-c", userCode)
                .exec();

        // 컨테이너 시작
        dockerClient.startContainerCmd(container.getId()).exec();

        // 결과 가져오기
        String result = getContainerLogs(container.getId());

        dockerClient.removeContainerCmd(container.getId()).exec();

        return result;
    }

    public String getContainerLogs(String containerId) throws InterruptedException {
        LogContainerCmd logContainerCmd = dockerClient.logContainerCmd(containerId)
                .withStdOut(true)
                .withStdErr(true)
                .withFollowStream(true)
                .withTailAll();

        StringBuilder logBuilder = new StringBuilder();
        logContainerCmd.exec(new LogContainerResultCallback() {
            @Override
            public void onNext(Frame frame) {
                logBuilder.append(new String(frame.getPayload()));
            }
        }).awaitCompletion(30, TimeUnit.SECONDS);

        return logBuilder.toString();
    }

    public void saveToFile(String filename, String code) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            writer.write(code);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            // 적절한 예외 처리
        }
    }
}

