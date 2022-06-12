package mvc.mvc.models;

import mvc.mvc.bean.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import mvc.mvc.bean.Shop;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Repository
public class ShopModel {

    @Autowired
    DataSource dataSource; //look to application-context.xml bean id='dataSource' definition

    private JdbcTemplate jdbcTemplate;
    @PostConstruct
    public void init() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Category> getAllCategories() {
        final String QUERY_SQL = "SELECT * FROM CATEGORY ORDER BY ID";
        List<Category> categories = this.jdbcTemplate.query(QUERY_SQL, new CategoryRowMapper());
        return categories;
    }

    public List<Shop> getAllShops() {
        final String QUERY_SQL = "SELECT S.ID, S.SHOPNAME, S.ADDRESS, S.PHONE, S.EMAIL, S.LINK, S.DESCRIPTION, " +
                "S.CATEGORYID, C.CATEGORYNAME FROM SHOP AS S " +
                "JOIN CATEGORY AS C ON S.CATEGORYID = C.ID " +
                "ORDER BY S.ID";
        List<Shop> shops = this.jdbcTemplate.query(QUERY_SQL, new ShopRowMapper());
        return shops;
    }

    //DELETE
    public boolean deleteShop(int id) {
        final String DELETE_SQL = "DELETE FROM SHOP WHERE ID = ?";
        int result = jdbcTemplate.update(DELETE_SQL,new Object[]{id});
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

    //GET
    public Shop getShop(int id) {
        final String SELECT_SQL = "SELECT S.ID, S.SHOPNAME, S.ADDRESS, S.PHONE, S.EMAIL, S.LINK, S.DESCRIPTION, " +
                "S.CATEGORYID, C.CATEGORYNAME FROM SHOP AS S " +
                "JOIN CATEGORY AS C ON S.CATEGORYID = C.ID " +
                "WHERE ID = ?";
        Shop shop = (Shop) jdbcTemplate.queryForObject(SELECT_SQL, new Object[]{id}, new ShopRowMapper());
        return shop;
    }

    //UPDATE
    public boolean updateShop(Shop shop)  {
        final String UPDATE_SQL = "UPDATE SHOP SET SHOPNAME = ?, ADDRESS = ?, PHONE = ?, " +
                "EMAIL = ?, LINK = ?, DESCRIPTION = ?, CATEGORYID = ? WHERE ID = ?";
        int result = jdbcTemplate.update(UPDATE_SQL,new Object[]{shop.getName(),
                shop.getAddress(), shop.getPhone(), shop.getEmail(), shop.getLink(),
                shop.getDescription(), shop.getCategory().getId(), shop.getId()});
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

    //CREATE
    public boolean createShop(Shop shop)  {
        System.out.println("HumanModel: updateUserEnable called");
        final String UPDATE_SQL = "INSERT INTO SHOP (SHOPNAME, ADDRESS, PHONE, " +
                "EMAIL, LINK, DESCRIPTION, CATEGORYID) VALUES (?, ?, ?, ?, ?, ?, ?)";
        int result = jdbcTemplate.update(UPDATE_SQL,new Object[]{shop.getName(),
                shop.getAddress(), shop.getPhone(), shop.getEmail(), shop.getLink(),
                shop.getDescription(), shop.getCategory().getId()});
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

    public List<Shop> searchShops(String shopName, int categoryId, String address) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT S.ID, S.SHOPNAME, S.ADDRESS, S.PHONE, S.EMAIL, S.LINK, S.DESCRIPTION, " +
                "S.CATEGORYID, C.CATEGORYNAME FROM SHOP AS S " +
                "JOIN CATEGORY AS C ON S.CATEGORYID = C.ID ");
        StringBuilder filter = new StringBuilder();
        List<Object> params = new ArrayList<Object>();
        filter.append("WHERE ");
        if(!shopName.equals("")){
            filter.append("S.SHOPNAME LIKE ? AND ");
            params.add("%"+shopName+"%");
        }
        if(categoryId != 0){
            filter.append("S.CATEGORYID = ? AND ");
            params.add(categoryId);
        }
        if(!address.equals("")){
            filter.append("S.ADDRESS LIKE ? AND ");
            params.add("%"+address+"%");
        }
        if(filter.length()>7) {
            filter.delete(filter.length() - 5, filter.length() - 1);
            query.append(filter);
        }

        query.append("ORDER BY S.ID");

        List<Shop> shops = this.jdbcTemplate.query(query.toString(), params.toArray(), new ShopRowMapper());
        return shops;
    }

    public class CategoryRowMapper implements RowMapper<Category> {

        @Override
        public Category mapRow(ResultSet rs, int rowNum) throws SQLException {

            Category category = new Category();
            category.setId(rs.getInt("ID"));
            category.setName(rs.getString("CATEGORYNAME"));

            return category;
        }
    }

    public class ShopRowMapper implements RowMapper<Shop> {

        @Override
        public Shop mapRow(ResultSet rs, int rowNum) throws SQLException {

            Shop shop = new Shop();
            shop.setId(rs.getInt("ID"));
            shop.setName(rs.getString("SHOPNAME"));
            shop.setAddress(rs.getString("ADDRESS"));
            shop.setPhone(rs.getString("PHONE"));
            shop.setEmail(rs.getString("EMAIL"));
            shop.setLink(rs.getString("LINK"));
            shop.setDescription(rs.getString("DESCRIPTION"));
            shop.setCategory(new Category(rs.getInt("CATEGORYID"), rs.getString("CATEGORYNAME")));

            return shop;
        }
    }
}
