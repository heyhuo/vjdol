package com.valcano.vjdol.dao;

import com.valcano.vjdol.dbutil.MybatisUtil;
import com.valcano.vjdol.entity.Admin;
import com.valcano.vjdol.mapper.AdminMapper;
import org.apache.ibatis.session.SqlSession;


import java.io.IOException;
import java.util.List;

public class AdminDao implements AdminMapper {
    @Override
    public void insertAdmin(Admin admin) {
        SqlSession session = null;
        try {
            //1、创建SqlSession对象
            session = MybatisUtil.getSqlSession();

            //2、执行命令
            int rows = session.insert("insertAdmin", admin);

            //3、SqlSession提交
            session.commit();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4、SqlSession关闭
            if (session != null)
                session.close();
        }
    }

    @Override
    public void insertAdminCatchId(Admin admin) throws Exception {
        SqlSession session = null;
        try {
            //1、创建SqlSession对象
            session = MybatisUtil.getSqlSession();

            //2、执行命令
            int rows = session.insert("insertAdminCatchId", admin);

            //3、SqlSession提交
            session.commit();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4、SqlSession关闭
            if (session != null)
                session.close();
        }
    }

    @Override
    public int updateAdmin(Admin admin) throws Exception {
        SqlSession session = null;
        try {
            //1、创建SqlSession对象
            session = MybatisUtil.getSqlSession();

            //2、执行命令
            session.update("updateAdmin", admin);

            //3、SqlSession提交
            session.commit();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4、SqlSession关闭
            if (session != null)
                session.close();
        }
        return 0;
    }

    @Override
    public void deleteById(int id) {
        SqlSession session = null;
        try {
            //1、创建SqlSession对象
            session = MybatisUtil.getSqlSession();

            //2、执行命令
            int rows = session.insert("deleteById", id);

            //3、SqlSession提交
            session.commit();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4、SqlSession关闭
            if (session != null)
                session.close();
        }
    }

    @Override
    public List<Admin> selectAllAdmin() {
        List<Admin> adminList = null;
        SqlSession session = null;
        try {
            //1、创建SqlSession对象
            session = MybatisUtil.getSqlSession();

            //2、执行命令
            adminList = session.selectList("selectAllAdmin");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4、SqlSession关闭
            if (session != null)
                session.close();
        }
        return adminList;
    }

    @Override
    public Admin selectAdminById(int id) {
        Admin admin = null;
        SqlSession session = null;
        try {
            //1、创建SqlSession对象
            session = MybatisUtil.getSqlSession();

            //2、执行命令
            admin = session.selectOne("selectAdminById", id);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4、SqlSession关闭
            if (session != null)
                session.close();
        }
        return admin;
    }

    @Override
    public Admin selectAdminByName(String name) {
        Admin admin = null;
        SqlSession session = null;
        try {
            //1、创建SqlSession对象
            session = MybatisUtil.getSqlSession();

            //2、执行命令
            admin = session.selectOne("selectAdminByName", name);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4、SqlSession关闭
            if (session != null)
                session.close();
        }
        return admin;
    }

    @Override
    public List<Admin> selectAdminByCondition(Admin admin) {
        List<Admin> adminList = null;
        SqlSession session = null;
        try {
            //1、创建SqlSession对象
            session = MybatisUtil.getSqlSession();

            //2、执行命令
            adminList = session.selectList("selectAdminByCondition", admin);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4、SqlSession关闭
            if (session != null)
                session.close();
        }
        return adminList;
    }
}
