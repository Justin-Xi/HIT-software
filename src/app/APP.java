package app;

import system.RetailSystem;
import user.User;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class APP {

    /**
     * 用户注册的方法
     * @param userList 用户列表
     * @return 一个注册好的用户
     */
    public static User register(List<User> userList){
        String line2;
        boolean flag;
        while(true) {
            flag = true;
            System.out.print("请输入用户名:");
            Scanner scanner2 = new Scanner(System.in);
            line2 = scanner2.next();
            Pattern pattern = Pattern.compile("[A-Za-z0-9]+");
            Matcher matcher = pattern.matcher(line2);
            if(!matcher.matches())
                System.out.println("用户名不符合格式，请重新输入");
            else {
                for (User u : userList) {
                    if (u.getUserName().equals(line2)) {
                        System.out.println("用户名重复，请重新输入");
                        flag = false;
                    }
                }
                if (flag)
                    break;
            }
        }
        String line3,line4;
        while(true) {
            System.out.print("请输入密码:");
            Scanner scanner3 = new Scanner(System.in);
            line3 = scanner3.next();
            Pattern pattern = Pattern.compile("[A-Za-z0-9]+");
            Matcher matcher = pattern.matcher(line3);
            if(!matcher.matches())
                System.out.println("密码不符合格式，请重新输入");
            else
                break;
        }
        while(true) {
            System.out.print("请输入身份(Supplier/Client/Courier):");
            Scanner scanner4 = new Scanner(System.in);
            line4 = scanner4.next();
            Pattern pattern = Pattern.compile("Supplier|Client|Courier");
            Matcher matcher = pattern.matcher(line4);
            if(!matcher.matches())
                System.out.println("身份不符合格式，请重新输入");
            else
                break;
        }
        return User.createUser(line2, line3, line4);

    }

    /**
     * 用户登录的方法
     * @param userList 用户列表
     * @return 登录用户的信息
     */
    public static User log(List<User> userList){
        User user = null;
        while(true) {
            System.out.print("请输入用户名:");
            Scanner scanner2 = new Scanner(System.in);
            String line2 = scanner2.next();
            for (User u : userList) {
                if (u.getUserName().equals(line2))
                    user = u;
            }
            if (user == null)
                System.out.println("用户不存在，请重新输入");
            else
                break;
        }
        while(true) {
            System.out.print("请输入密码:");
            Scanner scanner3 = new Scanner(System.in);
            String line3 = scanner3.next();
            if (user.getKeyWord().equals(line3))
                break;
            else
                System.out.println("密码错误，请重新输入");
        }
        return user;
    }

    /**
     * 删除某个用户的操作
     * @param system 系统
     */
    public static void delete(RetailSystem system){
        System.out.print("请输入用户名:");
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();
        List<User> userList = system.getUsers();
        boolean flag = false;
        for(User user:userList){
            if(user.getUserName().equals(s)) {
                system.delete(user);
                flag = true;
                System.out.println("成功删除用户"+user.getUserName());
            }
        }
        if(!flag)
            System.out.println("用户不存在!");
    }

    /**
     * 更改用户密码
     * @param user 用户
     * @param oldKey 旧密码
     * @param newKey 新密码
     */
    public static void changeKeyWord(User user,String oldKey,String newKey){
        user.changeKeyWord(oldKey,newKey);
    }

    /**
     * 用户可操作功能方法
     * @param system 系统
     * @param user 用户
     */
    public static void function(RetailSystem system,User user){
        List<String> list = system.getPermissions();
        Map<String,Boolean> permission = user.getPermissions();
        List<User> userList = system.getUsers();
        while(true) {
            String in;
            while(true){
                System.out.println("功能列表:");
                for (String i : list)
                    System.out.println(1 + list.indexOf(i) + "." + i + permission.get(i));
                System.out.println("5.退出功能");
                Scanner scanner = new Scanner(System.in);
                in = scanner.next();
                Pattern p = Pattern.compile("[1-5]");
                Matcher matcher = p.matcher(in);
                if (!matcher.matches())
                    System.out.println("输入错误！重新输入 1-5 ");
                else
                    break;
            }
            if(in.equals("5"))
                break;
            int num = Integer.parseInt(in);
            switch (num){
                case 1:
                    if(!permission.get(list.get(num-1)))
                        System.out.println("您无此权限进行操作，请联系管理员获得权限");
                    else{
                        User user1 = register(system.getUsers());
                        system.add(user1);
                    }
                    break;
                case 2:
                    if(!permission.get(list.get(num-1)))
                        System.out.println("您无此权限进行操作，请联系管理员获得权限");
                    else{
                        delete(system);
                    }
                    break;
                case 3:
                    if(!permission.get(list.get(num-1)))
                        System.out.println("您无此权限进行操作，请联系管理员获得权限");
                    else{
                        for(User user1:userList)
                            System.out.println("用户名:"+user1.getUserName()+"密码:"+user1.getKeyWord()+"身份:"+user1.getCharacter());
                    }
                    break;
                case 4:
                    if(!permission.get(list.get(num-1)))
                        System.out.println("您无此权限进行操作，请联系管理员获得权限");
                    else{
                        User user1 = null;
                        while(true) {
                            System.out.print("请输入用户名:");
                            Scanner scanner1 = new Scanner(System.in);
                            String line1 = scanner1.next();
                            for (User user2 : userList) {
                                if (user2.getUserName().equals(line1))
                                    user1 = user2;
                            }
                            if(user1 != null)
                                break;
                            else
                                System.out.println("用户不存在！");
                        }
                        while(true){
                            String line1;
                            Map<String,Boolean> map = user1.getPermissions();
                            while(true){
                                System.out.println("请输入权限更改:");
                                for (String i : list)
                                    System.out.println(1 + list.indexOf(i) + "." + i + map.get(i));
                                System.out.println("5.退出权限修改");
                                Scanner scanner1 = new Scanner(System.in);
                                line1 = scanner1.next();
                                Pattern p = Pattern.compile("[1-5]");
                                Matcher matcher = p.matcher(in);
                                if (!matcher.matches())
                                    System.out.println("输入错误！重新输入 1-5 ");
                                else
                                    break;
                            }
                            if(line1.equals("5"))
                                break;
                            int num1 = Integer.parseInt(line1);
                            String i = list.get(num1-1);
                            boolean flag = map.get(i);
                            user1.setPermissions(i,!flag);
                        }
                    }
                    break;
                default:
                    break;
            }
            userList = system.getUsers();
            permission = user.getPermissions();
        }
    }

    /**
     * 客户端运行的主程序
     * @param args 略
     */
    public static void main(String[] args) {

        //创造一个系统，及所需要的数据
        RetailSystem system = RetailSystem.emptySystem();
        FileOperation.readFile(system);
        List<User> userList = system.getUsers();
        User user;

        //客户端开始进入的界面
        System.out.println("欢迎来到零售批发管理系统，请选择:");
        String line1;
        while(true){
            System.out.println("1.注册新用户 2.登录");
            Scanner scanner1 = new Scanner(System.in);
            line1 = scanner1.nextLine();
            Pattern p = Pattern.compile("[12]");
            Matcher matcher = p.matcher(line1);
            if (!matcher.matches())
                System.out.println("输入错误！重新输入 1 或 2 ");
            else
                break;
        }

        //注册程序
        if(line1.equals("1")) {
            user = register(userList);
            system.add(user);
            userList = system.getUsers();
            System.out.println("注册成功，正在进入系统......");
        }
        //登录程序
        else{
            user = log(userList);
            System.out.println("登录成功，正在进入系统");
        }

        //系统的具体应用
        System.out.println("欢迎使用通用零售批发管理系统，"+user.getCharacter()+" "+user.getUserName());
        while(true) {
            System.out.println("功能如下:");
            System.out.println("1.更改密码 2.用户信息");
            System.out.println("3.功能列表 4.切换用户");
            System.out.println("5.退出系统");
            String line4;
            while (true) {
                System.out.print("请输入编号:");
                Scanner scanner4 = new Scanner(System.in);
                line4 = scanner4.next();
                Pattern p = Pattern.compile("[1-5]");
                Matcher matcher = p.matcher(line4);
                if (!matcher.matches())
                    System.out.println("输入错误！重新输入 1 - 5 ");
                else
                    break;
            }
            if(line4.equals("5")){
                System.out.println("欢迎下次使用");
                FileOperation.writeFile(system);
                break;
            }
            switch (Integer.parseInt(line4)) {
                case 1:
                    String line5;
                    while (true) {
                        System.out.print("请输入旧密码:");
                        Scanner scanner5 = new Scanner(System.in);
                        line5 = scanner5.next();
                        if (!line5.equals(user.getKeyWord()))
                            System.out.println("输入错误！请重新输入");
                        else
                            break;
                    }
                    System.out.print("请输入新密码:");
                    Scanner scanner6 = new Scanner(System.in);
                    String line6 = scanner6.next();
                    changeKeyWord(user,line5,line6);
                    System.out.println("密码更换成功");
                    break;
                case 2:
                    System.out.println("用户名:"+user.getUserName());
                    System.out.println("身份:"+user.getCharacter());
                    System.out.println("目前权限有:");
                    List<String> list = system.getPermissions();
                    Map<String,Boolean> permission = user.getPermissions();
                    for(String i:list)
                        System.out.println(1+list.indexOf(i)+"."+i+permission.get(i));
                    break;
                case 3:
                    function(system,user);
                    break;
                case 4:
                    User user1 = log(userList);
                    user = user1;
                    break;
                default:
                    break;
            }
            userList = system.getUsers();
        }
    }
}
