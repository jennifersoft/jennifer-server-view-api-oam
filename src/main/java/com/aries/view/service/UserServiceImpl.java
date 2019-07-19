package com.aries.view.service;

import com.aries.view.domain.LoginUserPolicy;
import com.aries.view.domain.User;
import com.aries.view.domain.UserPasswordPolicy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService
{
    private static List<User> users = new ArrayList<>();

    public UserServiceImpl() {
        User user = new User();
        user.setId("guest");
        user.setName("Guest");
        user.setPassword("1234");
        user.setGroupId("guest");
        user.setDept("R&D");
        users.add(user);

        User user2 = new User();
        user2.setId("monitor");
        user2.setName("Monitor");
        user2.setPassword("4321");
        user2.setGroupId("guest");
        user2.setDept("Design");
        users.add(user2);
    }

    @Override
    public void save(User user) {
        users.add(user);
    }

    @Override
    public void modify(User modifyUser) {
        User user = find(modifyUser.getId());
        String pwd = modifyUser.getPassword();

        // 새로운 비밀번호로 변경할 때
        if (!pwd.equals(""))
        {
            modifyUser.setChangePwdDt("" + System.currentTimeMillis());
            modifyUser.setPassword("encoded:" + pwd);

            // 기존의 정보 변경할 때
        }
        else
        {
            modifyUser.setPassword(user.getPassword());
        }

        if (!user.getGroupId().equals("") && modifyUser.getGroupId() == null)
        {
            modifyUser.setGroupId(user.getGroupId());
        }

        for(int i = 0; i < users.size(); i++) {
            if(users.get(i).getId().equals(modifyUser.getId())) {
                users.set(i, modifyUser);
            }
        }
    }

    @Override
    public void remove(String id) {
        users.remove(find(id));
        throw new RuntimeException("Test");
    }

    @Override
    public List<User> list() {
        return users;
    }

    @Override
    public void saveLanguage(String id, String language) {

    }

    @Override
    public void saveThemeName(String id, String themeName) {

    }

    @Override
    public void savePasswordPolicy(UserPasswordPolicy policy) {

    }

    @Override
    public UserPasswordPolicy getPasswordPolicy() {
        return null;
    }

    @Override
    public void saveLoginUserPolicy(LoginUserPolicy policy) {

    }

    @Override
    public LoginUserPolicy getLoginUserPolicy() {
        return null;
    }

    @Override
    public boolean checkUserId(String id) {
        return false;
    }

    @Override
    public boolean checkUserPasswordPolicy(String password) {
        return false;
    }

    @Override
    public boolean exist(String id) {
        for(int i = 0; i < users.size(); i++) {
            if(users.get(i).getId().equals(id)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void changePassword(User user) {

    }

    @Override
    public User authenticate(String id, String password) {
        return null;
    }

    @Override
    public User authenticateEncrypt(String id, String encryptPassword) {
        return null;
    }

    @Override
    public User find(String id) {
        for(int i = 0; i < users.size(); i++)
        {
            User user = users.get(i);
            if(user.getId().equals(id)) {
                return user;
            }
        }

        return null;
    }

    @Override
    public void addLoginFailCount(String id) {

    }

    @Override
    public void resetLoginFailCount(String id) {

    }

    @Override
    public void updateCheckAlarmDate(String id) {

    }

    @Override
    public void changePasswordDate(String id) {

    }

    @Override
    public void changePasswordDate(String id, long time) {

    }

    @Override
    public String getPassword(String id) {
        return null;
    }

    @Override
    public void addBookmark(String userId, String menuUrl, String menuCode, short order) {

    }

    @Override
    public void removeBookmark(String userId, String menuUrl) {

    }

    @Override
    public void removeBookmarkByUserDefineDashboard(String menuUrl) {

    }

    @Override
    public Map<Short, Map<String, Object>> getBookmarks(String userId) {
        return null;
    }

    @Override
    public boolean isActiveBookmark(String userId, String menuUrl, Map paramMap) {
        return false;
    }
}
