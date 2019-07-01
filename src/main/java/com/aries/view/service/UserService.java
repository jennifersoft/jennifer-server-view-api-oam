package com.aries.view.service;

import com.aries.view.domain.LoginUserPolicy;
import com.aries.view.domain.User;
import com.aries.view.domain.UserPasswordPolicy;

import java.util.List;
import java.util.Map;

public interface UserService
{
    void save(User user);

    void modify(User modifyUser);

    void remove(String id);

    List<User> list();

    void saveLanguage(String id, String language);

    void saveThemeName(String id, String themeName);

    void savePasswordPolicy(UserPasswordPolicy policy);

    UserPasswordPolicy getPasswordPolicy();

    void saveLoginUserPolicy(LoginUserPolicy policy);

    LoginUserPolicy getLoginUserPolicy();

    boolean checkUserId(String id);

    boolean checkUserPasswordPolicy(String password);

    boolean exist(String id);

    void changePassword(User user);

    User authenticate(String id, String password);

    User authenticateEncrypt(String id, String encryptPassword);

    User find(String id);

    void addLoginFailCount(String id);

    void resetLoginFailCount(String id);

    @Deprecated
    void updateCheckAlarmDate(String id);

    void changePasswordDate(String id);

    void changePasswordDate(String id, long time);

    String getPassword(String id);

    void addBookmark(String userId, String menuUrl, String menuCode, short order);

    void removeBookmark(String userId, String menuUrl);

    void removeBookmarkByUserDefineDashboard(String menuUrl);

    Map<Short, Map<String, Object>> getBookmarks(String userId);

    boolean isActiveBookmark(String userId, String menuUrl, Map paramMap);
}
