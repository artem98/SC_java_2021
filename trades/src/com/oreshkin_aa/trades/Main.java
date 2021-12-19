package com.oreshkin_aa.trades;

import java.nio.file.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        final String fileName = "trades.txt";
        try (Stream<String> linesStream = Files.lines(Paths.get(fileName))) {
            Stream<PaperResultData> resultDataStream = linesStream
                    .map(LineData::makeLineData)
                    .filter(Objects::nonNull)
                    .filter(lineData -> lineData.secBoard.equals("TQBR") || lineData.secBoard.equals("FQBR"))
                    .collect(Collectors.groupingBy(LineData::getSecCode))
                    .entrySet().stream()
                    .map(entry -> {
                        var lineDataList = entry.getValue();
                        Stream<LineData> stream = lineDataList.stream();
                        return stream.collect(() -> new PaperResultData(entry.getKey()),
                                PaperResultData::consumeLineData,
                                (paperResultData, paperResultData2) -> {
                                });
                    });

            System.out.println("Top 10 best papers:");
            AtomicLong count = new AtomicLong();
            resultDataStream
                    .peek(data -> count.addAndGet(1))
                    .sorted(PaperResultData::compareTo)
                    .map(IndexedResult::new)
                    .filter(indexedResult -> indexedResult.index <= 10 ||  indexedResult.index >= count.longValue() - 10)
                    .forEach(indexedResult -> {
                        System.out.println(indexedResult);
                        if(indexedResult.index == 10)
                            System.out.println("\nTop 10 worst papers:");
                    });

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    private static class IndexedResult {
        public PaperResultData data;
        public int index;

        public IndexedResult(PaperResultData data) {
            this.data = data;
            this.index = currentIndex++;
        }

        static int currentIndex = 0;

        public void clear() {
            currentIndex = 0;
        }

        @Override
        public String toString() {
            return "Top " + index + data.toString();
        }
    }
}
