package com.sunli.decembermultiple.shell_frame.network;

public class ApiUtils {
    //一级查询
    public static final String GET_URL_FIRST_CATEGORY = "commodity/v1/findFirstCategory";
    //二级查询
    public static final String GET_URL_SECOND_CATEGORY = "commodity/v1/findSecondCategory?firstCategoryId=%s";
    //用户注册
    public static final String POST_URL_USER_REGISTER = "user/v1/register";
    //用户登录
    public static final String POST_URL_USER_LOGIN = "user/v1/login";
    //用户修改昵称
    public static final String PUT_URL_USER_MODIFY_USER_NICK = "user/verify/v1/modifyUserNick";
    //用户修改用户密码
    public static final String PUT_URL_USER_MODIFY_USER_PWD = "user/verify/v1/modifyUserPwd";
    //用户上传头像
    public static final String POST_URL_USER_MODIFY_HEAD_PIC = "user/verify/v1/modifyHeadPic";
    //根据用户ID查询用户信息
    public static final String GET_URL_USER_GET_USER_BYID = "user/verify/v1/getUserById";
    //收货地址列表
    public static final String GET_URL_USER_RECYCLE_ADDRESS_LIST = "user/verify/v1/receiveAddressList";
    //新增收货地址
    public static final String POST_URL_USER_ADD_RECYCLE_ADDRESS_LIST ="user/verify/v1/addReceiveAddress";
    //设置默认收货地址
    public static final String POST_URL_USER_SET_DEFAULT_RECYCLE_ADDRESS_LIST = "user/verify/v1/setDefaultReceiveAddress";
    //修改收货信息
    public static final String PUT_URL_USER_CHANGE_RECYCLE_ADDRESS = "user/verify/v1/changeReceiveAddress";
    //查询用户钱包
    public static final String GET_URL_USER_FIND_USER_WALLET = "user/verify/v1/changeReceiveAddress";

}
