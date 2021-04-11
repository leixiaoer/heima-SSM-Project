package Cn.Lei.Service;

import Cn.Lei.Domain.SysLog;

import java.util.List;

public interface ISysLogService {


    /**
     * 添加日志
     * @param log
     * @throws Exception
     */
    public void save(SysLog log) throws Exception;

    /**
     * 查询所有
     * @return
     * @throws Exception
     */
    public List<SysLog> findAll() throws Exception;
}
