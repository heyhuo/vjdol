package com.valcano.vjdol.mapper;

import com.valcano.vjdol.entity.Admin;

import java.util.List;

public interface AdminMapper {
    /**
     * 新增用户
     *
     * @param admin
     * @return
     * @throws Exception
     */
    public void insertAdmin(Admin admin) throws Exception;

    /**
     * 获取新插入用户的id
     *
     * @param admin
     * @throws Exception
     */
    public void insertAdminCatchId(Admin admin) throws Exception;

    /**
     * 更新用户
     *
     * @param admin
     * @return
     * @throws Exception
     */
    public int updateAdmin(Admin admin) throws Exception;

    /**
     * 删除
     *
     * @param id
     */
    public void deleteById(int id);

    /**
     * 查询所有管理员
     *
     * @return
     */
    public List<Admin> selectAllAdmin();

    /**
     * 通过id查询指定管理员
     *
     * @return
     */

    public Admin selectAdminById(int id);

    /**
     * 通过name查询指定管理员
     *
     * @return
     */
    public Admin selectAdminByName(String name);

    public List<Admin> selectAdminByCondition(Admin admin);

}
