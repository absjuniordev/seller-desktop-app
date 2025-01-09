package db;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.SellerDao;

public class DatabaseInitializer {

    public static void initializeDatabase() {
        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
        departmentDao.createTable();
        
    	SellerDao sellerDao = DaoFactory.createSellerDao();
		sellerDao.createTable();
    }
}
