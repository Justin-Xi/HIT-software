package user;

import system.RetailSystem;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * 用户类的接口
 *
 * 这个接口主要是用来创造用户，每个人在登录通用零售批发管理系统的时候都需要去注册用户
 * 对于用户的注册要求有：不能重复的用户名，密码还有身份，其中身份所需要的类我已经在文
 * 件夹state中创建完成，只需要创建类直接使用就行
 *
 */
public interface User {

    /**
     * 创建一个用户
     * @param name 不能重复的用户名
     * @param keyWord 密码
     * @param character 身份
     * @return 一个刚刚建立的用户
     */
    static User createUser(String name, String keyWord, String character){
        if(character.equals("Administrator"))
            return Administrator.createUser(name,keyWord);
        else
            return Others.createOthers(name, keyWord, character);
    }

    /**
     * 获得用户名字
     * @return 用户名
     */
    String getUserName();

    /**
     * 获得密码
     * @return 密码
     */
    String getKeyWord();

    /**
     * 获得身份
     * @return 身份
     */
    String getCharacter();

    /**
     * 重写的等价方法
     * @param user 要比较的用户
     * @return 若相等，则返回true；反之返回false
     */
    boolean equals(User user);

    /**
     * 重写的哈希值方法
     * @param user 要比较的用户
     * @return 返回哈希值
     */
    int hashCode(User user);

    /**
     * 用于设置个人用户的权限
     */
    void setPermissions(Map<String ,Boolean> permissions);

    /**
     * 获取个人用户权限
     */
    Map<String,Boolean> getPermissions();

    /**
     * 可以在系统中添加某个用户，除管理者以外，其他用户默认无此权限
     * @param system 系统
     * @param user 要添加的用户
     */
    void add(RetailSystem system, User user);

    /**
     * 可以在系统中删除某个用户，除管理者以外，其他用户默认无此权限
     * @param system 系统
     * @param user 要删除的用户
     */
    void delete(RetailSystem system, User user);

    /**
     * 获得用户的列表，除管理者以外，其他用户默认无此权限
     * @param system 系统
     * @return 用户列表
     */
    List<User> getList(RetailSystem system);
}
