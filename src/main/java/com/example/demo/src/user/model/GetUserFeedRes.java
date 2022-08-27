package com.example.demo.src.user.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class GetUserFeedRes {
    private boolean _isMyFeed; // 내가 보는 피드와 다른 사람이 보는 피드 내용이 다르다
    private GetUserInfoRes getUserInfo;
    private List<GetUserPostsRes> getUserPosts;

    public GetUserFeedRes(int userIdx, String name, String nickName, String email) {
    }
}
