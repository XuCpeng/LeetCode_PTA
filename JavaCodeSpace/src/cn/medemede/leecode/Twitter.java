package cn.medemede.leecode;

import java.util.*;

class TweetNode {
    Integer tweetId;
    Integer timeStamp;
    TweetNode next;

    public TweetNode(Integer tweetId, Integer timeStamp) {
        this.tweetId = tweetId;
        this.timeStamp = timeStamp;
        this.next = null;
    }

    public TweetNode() {
        this.next = null;
    }
}

class User {
    Integer userId;
    Set<Integer> followed;
    TweetNode head;

    public User(Integer userId) {
        this.userId = userId;
        followed = new HashSet<>();
        followed.add(userId);
        head = new TweetNode();
    }

    public void post(Integer tweetId, Integer timeStamp) {
        TweetNode tweet = new TweetNode(tweetId, timeStamp);
        tweet.next = head.next;
        head.next = tweet;
    }

    public void follow(Integer userId) {
        followed.add(userId);
    }

    public void unfollow(Integer userId) {
        followed.remove(userId);
    }
}

public class Twitter {
    private Integer timeStamp = 0;
    HashMap<Integer, User> users;

    /**
     * Initialize your data structure here.
     */
    public Twitter() {
        this.users = new HashMap<>();
    }

    /**
     * Compose a new tweet.
     */
    public void postTweet(int userId, int tweetId) {
        users.computeIfAbsent(userId, k -> new User(userId));
        users.get(userId).post(tweetId, timeStamp);
        timeStamp++;
    }

    /**
     * Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent.
     */
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> res = new ArrayList<>();
        if (users.containsKey(userId)) {
            PriorityQueue<TweetNode> pq = new PriorityQueue<>((a, b) -> b.timeStamp - a.timeStamp);
            for (Integer followeeId : users.get(userId).followed) {
                TweetNode tweet = users.get(followeeId).head.next;
                while (tweet != null) {
                    pq.add(tweet);
                    tweet = tweet.next;
                }
            }
            int size = pq.size();
            for (int i = 0; i < 10 && i < size; i++) {
                res.add(pq.poll().tweetId);
            }
        }
        return res;
    }

    /**
     * Follower follows a followee. If the operation is invalid, it should be a no-op.
     */
    public void follow(int followerId, int followeeId) {
        users.computeIfAbsent(followerId, k -> new User(followerId));
        users.computeIfAbsent(followeeId, k -> new User(followeeId));
        users.get(followerId).follow(followeeId);
    }

    /**
     * Follower unfollows a followee. If the operation is invalid, it should be a no-op.
     */
    public void unfollow(int followerId, int followeeId) {
        if (users.containsKey(followerId)) {
            users.get(followerId).unfollow(followeeId);
        }
    }



    public static void main(String[] args) {
        Twitter twitter = new Twitter();

// 用户1发送了一条新推文 (用户id = 1, 推文id = 5).
        twitter.postTweet(1, 5);

// 用户1的获取推文应当返回一个列表，其中包含一个id为5的推文.
        System.out.println(twitter.getNewsFeed(1));

// 用户1关注了用户2.
        twitter.follow(1, 2);

// 用户2发送了一个新推文 (推文id = 6).
        twitter.postTweet(2, 6);

// 用户1的获取推文应当返回一个列表，其中包含两个推文，id分别为 -> [6, 5].
// 推文id6应当在推文id5之前，因为它是在5之后发送的.
        System.out.println(twitter.getNewsFeed(1));

// 用户1取消关注了用户2.
        twitter.unfollow(1, 2);

// 用户1的获取推文应当返回一个列表，其中包含一个id为5的推文.
// 因为用户1已经不再关注用户2.
        System.out.println(twitter.getNewsFeed(1));
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */
