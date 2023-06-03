package user;

import java.util.Map;

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
            return Administrator.createAdministrator(name,keyWord);
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
     * 用于设置用户权限，以权限名称和权限是否开启传入
     * @param str 权限名称，且必须是用户可以操作的权限
     * @param flag 权限是否开启
     */
    void setPermissions(String str,boolean flag);

    /**
     * 获取个人用户权限
     */
    Map<String,Boolean> getPermissions();

    /**
     * 用于更改用户密码的方法
     * @param oldKeyWord 旧密码
     * @param newKeyWord 新密码
     */
    void changeKeyWord(String oldKeyWord,String newKeyWord);
}
