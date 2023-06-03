package system;

import user.User;

import java.util.ArrayList;
import java.util.List;

public class ConcreteSystem implements RetailSystem {

    private final List<User> userList = new ArrayList<>();
    private final List<String> rightList = new ArrayList<String>(){{
        add("添加用户");
        add("删除用户");
        add("用户列表");
        add("更改用户权限");
    }};

    @Override
    public void add(User user) {
        if(userList.contains(user))
            System.out.println("用户已经存在");
        else
            userList.add(user);
    }

    @Override
    public void delete(User user) {
        if(!userList.contains(user))
            System.out.println("用户不存在");
        else
            userList.remove(user);
    }

    @Override
    public List<User> getUsers() {
        List<User> copy = new ArrayList<>();
        copy.addAll(userList);
        return copy;
    }

    @Override
    public List<String> getPermissions() {
        List<String> copy = new ArrayList<>();
        copy.addAll(rightList);
        return copy;
    }
}
