package user;


import system.RetailSystem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConcreteAdministrator implements Administrator {

    private final String name;
    private final String keyWord;
    private final String character ="Administrator";
    private Map<String,Boolean> permissions = new HashMap<>();

    public ConcreteAdministrator(String name, String keyWord){
        this.name = name;
        this.keyWord = keyWord;
        permissions.put("添加用户",true);
        permissions.put("删除用户",true);
        permissions.put("用户列表",true);
        permissions.put("更改用户权限",true);
    }

    @Override
    public String getUserName() {
        return name;
    }

    @Override
    public String getKeyWord() {
        return keyWord;
    }

    @Override
    public String getCharacter() {
        return character;
    }

    @Override
    public boolean equals(User user) {
        return name.equals(user.getUserName());
    }

    @Override
    public int hashCode(User user) {
        return Integer.parseInt(user.getUserName());
    }

    @Override
    public void setPermissions(Map<String, Boolean> permissions) {
        this.permissions = permissions;
    }

    @Override
    public Map<String,Boolean> getPermissions() {
        Map<String,Boolean> copy = new HashMap<>();
        copy.putAll(permissions);
        return copy;
    }

    @Override
    public void SetPermissions(User user,String str,boolean flag) {
        Map<String,Boolean> p = user.getPermissions();
        p.put(str,flag);
        user.setPermissions(p);
    }

    @Override
    public void add(RetailSystem system , User user) {
        if(permissions.get("添加用户"))
            System.out.println("您没有权限添加用户");
        else{
            system.add(user);
            System.out.println("用户"+user.getUserName()+"添加成功");
        }
    }

    @Override
    public void delete(RetailSystem system, User user) {
        if(permissions.get("删除用户"))
            System.out.println("您没有权限删除用户");
        else{
            system.delete(user);
            System.out.println("用户"+user.getUserName()+"删除成功");
        }
    }

    @Override
    public List<User> getList(RetailSystem system) {
        if(permissions.get("用户列表")) {
            System.out.println("您没有权限查看用户列表");
            return null;
        }
        else{
            return system.getUsers();
        }
    }


}
