package com.company;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

public class FileCreator extends Thread {
    private final CountDownLatch countDownLatch;
    private static final Object objectLock = new Object();

    public FileCreator(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        while (countDownLatch.getCount() != 0) {
            Path path = Paths.get(FileUtils.BASE_PATH + " " + UUID.randomUUID() + ".txt");
            try {
                Files.write(path, lines(), StandardCharsets.UTF_8);
                countDownLatch.countDown();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private Collection<String> lines() {
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            String string = UUID.randomUUID().toString();
            stringList.add(string);
        }
        return stringList;
    }
}
