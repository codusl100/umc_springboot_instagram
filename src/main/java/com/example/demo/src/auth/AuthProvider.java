package com.example.demo.src.auth;


import com.example.demo.config.BaseException;
import com.example.demo.src.post.PostDao;
import com.example.demo.src.post.model.GetPostsRes;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;
import static com.example.demo.config.BaseResponseStatus.USERS_EMPTY_USER_ID;

//Provider : Read의 비즈니스 로직 처리
@Service
public class AuthProvider {

    private final PostDao postDao;
    private final JwtService jwtService;


    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public AuthProvider(PostDao postDao, JwtService jwtService) {
        this.postDao = postDao;
        this.jwtService = jwtService;
    }


    public List<GetPostsRes> retrievePosts(int userIdxByJwt) throws BaseException {

        if (checkUserExist(userIdxByJwt) == 0) {
            throw new BaseException(USERS_EMPTY_USER_ID);
        }
        try {
            List<GetPostsRes> getPosts = postDao.selectPosts(userIdxByJwt);
            return getPosts;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }


    public int checkUserExist(int userIdx) throws BaseException {
        try {
            return postDao.checkUserExist(userIdx);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }

    }

    public int checkPostExist(int postIdx) throws BaseException {
        try {
            return postDao.checkPostExist(postIdx);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }

    }
}