package com.valcano.vjdol.sandBox;

import com.valcano.vjdol.constant.ConstantParameter;
import com.valcano.vjdol.dao.ExamPaperDao;
import com.valcano.vjdol.entity.ExamPaper;
import com.valcano.vjdol.sandBox.dto.Problem;

import java.io.FilePermission;
import java.lang.reflect.ReflectPermission;
import java.security.Permission;
import java.security.SecurityPermission;
import java.util.PropertyPermission;
import java.util.logging.LoggingPermission;

public class SandboxSecurityManager extends SecurityManager {
    private Problem problem;

    ExamPaperDao paperDao = new ExamPaperDao();

    public SandboxSecurityManager(Problem problem) {
        this.problem = problem;
    }

    /**
     * 防止非法退出导致服务器宕机
     *
     * @param status
     */
    @Override
    public void checkExit(int status) {
        if (status != ConstantParameter.EXIT_VALUE) {
            paperDao.updateResult(problem.getRunId(), problem.getStuNo(), "", "答案错误");
            throw new RuntimeException("非法退出，不允许退出虚拟机");
        }
        super.checkExit(status);
    }

    @Override
    public void checkPermission(Permission perm) {
        conformPermissionToSandbox(perm);

    }

    @Override
    public void checkPermission(Permission perm, Object context) {
        conformPermissionToSandbox(perm);
    }

    /**
     * 只给与必要的权限（比如读取，获取某些信息等），避免提交者进行非法操作。
     *
     * @param perm
     */
    private void conformPermissionToSandbox(Permission perm){
        if (perm instanceof SecurityPermission) {
            if (perm.getName().startsWith("getProperty")) {
                return;
            }
        } else if (perm instanceof PropertyPermission) {
            if (perm.getActions().equals("read")) {
                return;
            }
        } else if (perm instanceof FilePermission) {
            if (perm.getActions().equals("read")) {
                return;
            }
        } else if (perm instanceof RuntimePermission
                || perm instanceof ReflectPermission
                || perm instanceof LoggingPermission) {
            return;
        }
    }
}
