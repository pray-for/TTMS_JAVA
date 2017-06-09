package xupt.se.ttms.idao;


public interface iScheduleDAO
{
    public int insert(String schedid, Object studioid, Object playid, String time, String price);

    public int update(String schedid, Object studioid, Object playid, String time, String price);

    public int delete(String ID);

    public int select(Object[][] obj);

    public int findPlayByID(Object[] obj);

    public int findByID(Object[] obj);
}
