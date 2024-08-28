package com.site.blog.my.core.service;

import com.site.blog.my.core.Entity.AdminUser;

public interface AdminUserService {

    // 验证用户凭证并返回对应的用户对象
    AdminUser login(String userName, String password);

    /**
     * 通过用户ID获取用户详细信息
     *
     * @param loginUserId 用户的唯一标识
     * @return 返回用户的详细信息
     */
    AdminUser getUserDetailById(Integer loginUserId);

    /**
     * 更新用户的登录密码
     *
     * @param loginUserId 用户的唯一标识
     * @param originalPassword 现有密码
     * @param newPassword 新设置的密码
     * @return 返回密码更新的成功状态
     */
    Boolean updatePassword(Integer loginUserId, String originalPassword, String newPassword);

    /**
     * 更新用户的登录名称和昵称
     *
     * @param loginUserId 用户的唯一标识
     * @param loginUserName 新的登录名称
     * @param nickName 新的昵称
     * @return 返回名称更新的成功状态
     */
    Boolean updateName(Integer loginUserId, String loginUserName, String nickName);

}
