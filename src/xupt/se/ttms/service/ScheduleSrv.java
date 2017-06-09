package xupt.se.ttms.service;


import xupt.se.ttms.dao.ScheduleDAO;
import xupt.se.ttms.idao.iScheduleDAO;

public class ScheduleSrv
{

    private iScheduleDAO schedDAO = new ScheduleDAO();

    public int add(String schedid, Object studioid, Object playid, String time, String price)
    {
        return schedDAO.insert(schedid, studioid, playid, time, price);
    }

    public int modify(String schedid, Object studioid, Object playid, String time, String price)
    {
        return schedDAO.update(schedid, studioid, playid, time, price);
    }

    public int delete(String ID)
    {
        return schedDAO.delete(ID);
    }

    public Object select(Object[][] obj)
    {
        return schedDAO.select(obj);
    }

    public void findByID(Object[] obj)
    {
        schedDAO.findByID(obj);
    }

    public void findPlayByID(Object[] obj)
    {
        schedDAO.findPlayByID(obj);
    }

}
