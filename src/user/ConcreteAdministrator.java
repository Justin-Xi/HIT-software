package user;

import java.util.HashMap;
import java.util.Map;

public class ConcreteAdministrator implements Administrator {

    private final String name;
    private String keyWord;
    private final String character ="Administrator";
    private final Map<String,Boolean> permissions = new HashMap<>();

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
    public void SetOthersPermissions(User user,String str,boolean flag) {
        user.setPermissions(str,flag);
    }

    @Override
    public void setPermissions(String str, boolean flag) {
            permissions.put(str,flag);
    }

    @Override
    public Map<String,Boolean> getPermissions() {
        Map<String,Boolean> copy = new HashMap<>();
        copy.putAll(permissions);
        return copy;
    }

    @Override
    public void changeKeyWord(String oldKeyWord, String newKeyWord) {
        if(!oldKeyWord.equals(keyWord))
            System.out.println("原密码有误");
        else
            keyWord = newKeyWord;
    }

}
