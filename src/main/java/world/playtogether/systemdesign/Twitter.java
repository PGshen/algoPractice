package world.playtogether.systemdesign;

import java.util.*;

/**
 * <project> algoPractice
 *
 * <p> 推特
 *
 * @author penggs
 * @since 2021-04-27 15:46
 */
public class Twitter {
    Map<Integer, User> userMap = new HashMap<>();

    /** Initialize your data structure here. */
    public Twitter() {

    }

    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        if (!userMap.containsKey(userId)) {
            userMap.put(userId, new User(userId));
        }
        User user = userMap.get(userId);
        user.post(tweetId);
    }

    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> res = new ArrayList<>();
        if (!userMap.containsKey(userId)) return res;
        Set<Integer> followed = userMap.get(userId).followee;
        // 优先级队列，大顶堆
        PriorityQueue<Tweet> priorityQueue = new PriorityQueue<>(followed.size(), (a, b) -> Math.toIntExact(b.timestamp - a.timestamp));
        for(Integer followedId: followed) {
            Tweet tweet = userMap.get(followedId).head;
            if (tweet == null) continue;
            priorityQueue.offer(tweet);
        }
        while (!priorityQueue.isEmpty()) {
            if (res.size() == 10) break;
            Tweet tweet = priorityQueue.poll();
            res.add(tweet.tweetId);
            if (tweet.next != null) priorityQueue.offer(tweet.next);
        }
        return res;
    }

    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        if (userMap.containsKey(followerId)) {
            User user = userMap.get(followerId);
            user.follow(followeeId);
        }
    }

    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if (userMap.containsKey(followerId)) {
            User user = userMap.get(followerId);
            user.unfollow(followeeId);
        }
    }

    static class User {
        private int userId;
        private HashSet<Integer> followee;
        private Tweet head;

        public User(int userId) {
            followee = new HashSet<>();
            head = null;
            this.userId = userId;
            follow(userId); // 关注自己
        }

        public void follow(int userId) {
            followee.add(userId);
        }

        public void unfollow(int userId) {
            if (userId != this.userId) {
                followee.remove(userId);
            }
        }

        public void post(int tweetId) {
            Tweet tweet = new Tweet(tweetId, System.currentTimeMillis());
            tweet.next = head;
            head = tweet;
        }
    }

    // 用链表的形式保存，按发布顺序
    static class Tweet{
        private int tweetId;
        private long timestamp;
        private Tweet next;

        public Tweet(int tweetId, long timestamp) {
            this.tweetId = tweetId;
            this.timestamp = timestamp;
        }
    }
}