package com.berniesanders.connect.gson;

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
        private List<T> hits;
    }

    public List<T> getHits() {
        return hits.hits;
    }
}
