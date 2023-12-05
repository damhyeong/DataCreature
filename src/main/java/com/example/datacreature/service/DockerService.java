package com.example.datacreature.service;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.command.LogContainerCmd;
import com.github.dockerjava.api.model.Frame;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.command.LogContainerResultCallback;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Setter
@NoArgsConstructor
public class DockerService {
    private DockerClient dockerClient;

//    @Autowired
//    public DockerService(){
//        this.dockerClient = createDockerClient();
//    }

    public DockerClient createDockerClient(){
        return DockerClientBuilder.getInstance().build();
    }

    public String executeCode(String userCode) throws InterruptedException {
        //Docker Image를 기반으로 컨테이너 생성
        CreateContainerResponse container = dockerClient.createContainerCmd("java:8")
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
}

