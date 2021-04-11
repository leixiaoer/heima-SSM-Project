package Cn.Lei.Service.Impl;

import Cn.Lei.Dao.ISysLogDao;
import Cn.Lei.Domain.SysLog;
import Cn.Lei.Service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SysLogServiceImpl implements ISysLogService {

    @Autowired
    private ISysLogDao sysLogDao;

    /**
     * 添加日志数据
     * @param log
     * @throws Exception
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,readOnly = false)
    public void save(SysLog log) throws Exception {
        sysLogDao.save(log);
    }

    /**
     * 查询所有日志
     * @return
     * @throws Exception
     */
    @Override
    public List<SysLog> findAll() throws Exception {
        return sysLogDao.findAll();
    }
}
