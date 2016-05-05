package com.berniesanders.connect.gson;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;

import java.util.List;

public class HitsResponse<T> {
    private boolean timed_out;
    private long took;
    private ShardData _shards;
    private HitsData<T> hits;

    public static class ShardData {
        private int failed;
        private int successful;
        private int total;
    }

    public static class HitsData<T> {
        private float max_score;
        private int total;
        private List<Hit<T>> hits;
    }

    public static class Hit<T> {
        private String _id;
        private String _type;
        private String _index;
        private double _score;
        private T _source;
    }

    public List<T> getHits() {
        return Stream.of(hits.hits)
                .map(hit -> hit._source)
                .collect(Collectors.toList());
    }
}
